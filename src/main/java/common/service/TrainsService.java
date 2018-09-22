package common.service;

import common.converters.entity_to_dto.TrainEntityToDtoConverter;
import common.dto.SearchDTO;
import common.dto.TrainDTO;
import common.model.RouteEntity;
import common.model.Station;
import common.model.TrainEntity;
import common.repository.RouteRepository;
import common.repository.StationsRepository;
import common.repository.TrainsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

//TODO повесить на сервисах @Transactional
@Service
@Transactional
public class TrainsService {

    @Autowired
    private TrainsRepository trainsRepository;

    @Autowired
    private StationsRepository stationsRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private RouteService routeService;

    @Autowired
    private TrainEntityToDtoConverter trainEntityToDtoConverter;

    public int addition(int a, int b){
        return a + b;
    }

    public List<TrainDTO> findTrainsPassingStationIdAndReturnTrainDTOs(int id){

        List<TrainDTO> trainDTOList = new LinkedList<>();
        for (TrainEntity trainEntity : routeRepository.findTrainsPassingStationId(id)) {
            trainDTOList.add(trainEntityToDtoConverter.convert(trainEntity));
        }
        return trainDTOList;
    }

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

        for (int i = 0; i < stationsRepository.findAll().size(); i++){
            if (trainDTO.getMinutesFromStartStn()[i] != 0){
                Station station = stationsRepository.findById(trainDTO.getStationId()[i]);
                int time = trainDTO.getMinutesFromStartStn()[i];
                if (station.getId() == trainDTO.getStartSt()){
                    time = 0;
                }
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

        List<TrainDTO> trainDTOList = new LinkedList<>();

        List<TrainEntity> trainEntityList = trainsRepository.getAllTrains();
        for (TrainEntity trainEntity: trainEntityList){

            if (routeRepository.checkIfRouteIsCorrect(trainEntity, searchDTO)
             && routeRepository.checkIfRouteTimeIsCorrect(trainEntity, searchDTO)
             && routeService.checkIfIsFreeSeatOnEachStation(trainEntity.getId(), searchDTO)
                    //TODO эту проверку надо убрать из поиск и поставить в покупку
             && routeService.checkIfTimeNowEarlierThanTimeOnStartStation(trainEntity.getId(), searchDTO)){
                trainDTOList.add(trainEntityToDtoConverter.convert(trainEntity));
            }
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
