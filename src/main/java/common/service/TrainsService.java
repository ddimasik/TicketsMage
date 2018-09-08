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
import java.util.LinkedList;
import java.util.List;

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

        for (int i = 0; i < stationsRepository.findAll().size(); i++){
            if (trainDTO.getMinutesFromStartStn()[i] != 0){
                Station station = stationsRepository.findById(trainDTO.getStationId()[i]);
                int time = trainDTO.getMinutesFromStartStn()[i];
                routeService.createRoute(trainEntity, station, time, trainDTO.getCapacity());
            }
        }
    }

    public List<TrainEntity> findAllTrainsPassingStartStation(SearchDTO searchDTO){
        List<TrainEntity> trainEntityList = new LinkedList<>();
        List<RouteEntity> routeEntityList = routeService.findAll();
        for (RouteEntity routeEntity: routeEntityList) {
            if (routeEntity.getStationId() ==  searchDTO.getStartStn()){
                if (routeEntity.getFreeSeatsOnStn() > 0){
                    if (!trainEntityList.contains(trainsRepository.findById(routeEntity.getTrainEntityId()))){
                        trainEntityList.add(trainsRepository.findById(routeEntity.getTrainEntityId()));
                    }
                }
            }
        }
        return trainEntityList;
    }

    public List<TrainEntity> findAllTrainsPassingEndStation(SearchDTO searchDTO, List<TrainEntity> startTrainsList){
        List<TrainEntity> fullEntityList = new LinkedList<>();
        for (TrainEntity trainEntity: startTrainsList) {
            List<RouteEntity> routeEntityList = routeService.findRouteOfTrain(trainEntity);
            for (RouteEntity routeEntity: routeEntityList) {
                if (routeEntity.getStationId() != searchDTO.getStartStn() ||
                    routeEntity.getStationId() == searchDTO.getEndStn()){
                    if (!fullEntityList.contains(trainEntity)){
                        fullEntityList.add(trainEntity);
                    }
                }
            }
        }
        return fullEntityList;
    }

    public List<TrainDTO> findSuitableTrains(SearchDTO searchDTO){
        List<TrainDTO> trainDTOList = new LinkedList<>();
        List<TrainEntity> startTrainsList = findAllTrainsPassingStartStation(searchDTO);
        List<TrainEntity> fullTrainsList = findAllTrainsPassingEndStation(searchDTO, startTrainsList);
        for (TrainEntity trainEntity:fullTrainsList) {
            TrainDTO trainDTO = new TrainDTO();
            trainDTO.setId(trainEntity.getId());
            trainDTO.setName(trainEntity.getName());
            trainDTOList.add(trainDTO);
        }
        return trainDTOList;
    }

    public void removeTrain(int id){
        trainsRepository.delete(findById(id));
    }

}
