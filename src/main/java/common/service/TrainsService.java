package common.service;

import common.dto.SearchDTO;
import common.dto.TrainDTO;
import common.model.RouteEntity;
import common.model.Station;
import common.model.TrainEntity;
import common.repository.StationsRepository;
import common.repository.TrainsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

//TODO повесить на сервисах @Transactional
@Service
@Transactional
public class TrainsService {

    @Autowired
    private TrainsRepository trainsRepository;

    @Autowired
    private StationsRepository stationsRepository;

    @Autowired
    private RouteService routeService;

    public TrainEntity findById(Integer id) {
        return trainsRepository.findById(id);
    }

    public List<TrainEntity> findAll(){
        return trainsRepository.getAllTrains();
    }

    public void addTrain(TrainDTO trainDTO){
        TrainEntity trainEntity = new TrainEntity();
        trainEntity.setName(trainDTO.getName());
        trainEntity.setCapacity(trainDTO.getCapacity());
        trainEntity.setStartSt(trainDTO.getStartSt());

        String startDateTimeString = trainDTO.getStartDateTime().toString().replace('T',' ');
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        trainEntity.setStartDateTime(LocalDateTime.parse(startDateTimeString, formatter));
        trainsRepository.addTrain(trainEntity);
        routeService.createRoute(trainEntity, stationsRepository.findById(trainEntity.getStartSt()), 0, trainDTO.getCapacity());

        for (int i = 0; i < stationsRepository.findAll().size(); i++){
            if (trainDTO.getMinutesFromStartStn()[i] != 0){
                Station station = stationsRepository.findById(trainDTO.getStationId()[i]);
                int time = trainDTO.getMinutesFromStartStn()[i];
                routeService.createRoute(trainEntity, station, time, trainDTO.getCapacity());
            }
        }
    }

    public LocalDateTime trainPassStationOnDateTime(RouteEntity routeEntity){
        return trainsRepository.findById(routeEntity.getTrainEntityId())
                .getStartDateTime()
                .plusMinutes(routeEntity
                        .getMinutesFromStartStn());
    }

    /** Suitable train: goes through start & end stations, has seat, bound in time*/
    public List<TrainDTO> findSuitableTrains(SearchDTO searchDTO){
        LocalDateTime searchStartStnDateTime = LocalDateTime.parse(searchDTO.getStartDateTime().toString());
        LocalDateTime searchEndStnDateTime = LocalDateTime.parse(searchDTO.getEndDateTime().toString());

        Set<RouteEntity> routeEntitySet = routeService.findAll().stream()
                //TODO check freeSeats AFTER finding correct route
                .filter(routeEntity -> routeEntity.getFreeSeatsOnStn() > 0 || routeEntity.getStationId() == searchDTO.getEndStationId() )
                .filter(routeEntity -> routeEntity.getStationId() == searchDTO.getStartStationId() ||
                        routeEntity.getStationId() == searchDTO.getEndStationId())
                .filter(routeEntity ->
                        (
                            (trainPassStationOnDateTime(routeEntity).equals(searchStartStnDateTime)) ||
                            (trainPassStationOnDateTime(routeEntity).isBefore(searchStartStnDateTime))
                        ) ||
                        (
                            (trainPassStationOnDateTime(routeEntity).isAfter(searchEndStnDateTime)) ||
                            (trainPassStationOnDateTime(routeEntity).equals(searchEndStnDateTime))
                        )
                )
                .collect(Collectors.toCollection( () -> new TreeSet<>(Comparator.comparing(RouteEntity::getTrainEntityId))));

        List<TrainDTO> trainDTOList = new LinkedList<>();
        for (RouteEntity routeEntity: routeEntitySet) {
            TrainDTO trainDTO = new TrainDTO();
            trainDTO.setId(routeEntity.getTrainEntityId());
            trainDTO.setName(trainsRepository.findById(routeEntity.getTrainEntityId()).getName());
            trainDTOList.add(trainDTO);
        }
        return trainDTOList;
    }

    public void removeTrain(int id){
        List<RouteEntity> routeEntityList = routeService.findAll();
        for (RouteEntity routeEntity: routeEntityList){
            if (routeEntity.getTrainEntityId() == id){
                routeService.delete(routeEntity);
            }
        }
        trainsRepository.delete(findById(id));
    }
}
