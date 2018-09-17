package common.service;

import common.dto.SearchDTO;
import common.model.RouteEntity;
import common.model.Station;
import common.model.TrainEntity;
import common.repository.RouteRepository;
import common.repository.TrainsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class RouteService {

    private static final int TIME_IN_MINUTES_BETWEEN_NOW_AND_START_STATION = 10;

    private final Logger logger = LoggerFactory.getLogger(RouteService.class);

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private TrainsRepository trainsRepository;


    public void decreaseFreeSeats(int trainId, SearchDTO searchDTO){

        List<RouteEntity> routesList = findRouteOfTrainForTicket(trainId, searchDTO);

        for (RouteEntity routeEntity : routesList) {
            routeEntity.setFreeSeatsOnStn(routeEntity.getFreeSeatsOnStn() - 1);
            routeRepository.persistChangedRoute(routeEntity);
            logger.debug("Decreased free seats on station {} for train {} and {}", routeEntity.getStationId(), trainId, searchDTO);
        }
    }


    public boolean checkIfTimeNowEarlierThanTimeOnStartStation(int trainId, SearchDTO searchDTO){

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime timeOnStartStation = routeRepository.findTimeTrainOnStation(trainsRepository.findById(trainId), searchDTO.getStartStationId());

        logger.debug("Time check was {} for time now {} and time on start station {}", now.plusMinutes(TIME_IN_MINUTES_BETWEEN_NOW_AND_START_STATION).isBefore(timeOnStartStation), now, timeOnStartStation );

        return now.plusMinutes(TIME_IN_MINUTES_BETWEEN_NOW_AND_START_STATION).isBefore(timeOnStartStation);
    }

    public boolean checkIfIsFreeSeatOnEachStation(int trainId, SearchDTO searchDTO) {

        List<RouteEntity> routesList = findRouteOfTrainForTicket(trainId, searchDTO);

        for (RouteEntity routeEntity : routesList) {
            if (routeEntity.getFreeSeatsOnStn() <= 0){
                    logger.debug("No free seats for train {} and {}", trainId, searchDTO);
                    return false;
            }
        }
        return true;
    }

    public void createRoute(TrainEntity trainEntity, Station station, int minutesFromStartStn, int freeSeatsOnStn){
        RouteEntity routeEntity = new RouteEntity();
        routeEntity.setTrainEntityId(trainEntity.getId());
        routeEntity.setStationId(station.getId());
        routeEntity.setMinutesFromStartStn(minutesFromStartStn);
        routeEntity.setFreeSeatsOnStn(freeSeatsOnStn);
        routeRepository.addRoute(routeEntity);
    }

    public List<RouteEntity> findRouteOfTrain(TrainEntity trainEntity){
        return routeRepository.findRouteOfTrain(trainEntity.getId());
    }

    public List<RouteEntity> findRouteOfTrainWithSearchConditions(int trainId, SearchDTO searchDTO){

        List<RouteEntity> resultRoutesList = new LinkedList<>();

        TrainEntity trainEntity = trainsRepository.findById(trainId);
        LocalDateTime trainStart = trainEntity.getStartDateTime();

        LocalDateTime searchStart = LocalDateTime.parse(searchDTO.getStartDateTime().toString());
        LocalDateTime searchEnd = LocalDateTime.parse(searchDTO.getEndDateTime().toString());

        List<RouteEntity> routesList = routeRepository.findRouteOfTrain(trainId);
        for (RouteEntity routeEntity : routesList) {

            LocalDateTime timeForTrainOnStation = trainStart.plusMinutes(routeEntity.getMinutesFromStartStn());

            if (timeForTrainOnStation.equals(searchStart) || timeForTrainOnStation.isAfter(searchStart)
                    && timeForTrainOnStation.isBefore(searchEnd)){
                resultRoutesList.add(routeEntity);
            }
        }
        return resultRoutesList;
    }

    public List<RouteEntity> findRouteOfTrainForTicket(int trainId, SearchDTO searchDTO){

        List<RouteEntity> resultRoutesList = new LinkedList<>();

        TrainEntity trainEntity = trainsRepository.findById(trainId);
        LocalDateTime trainStart = trainEntity.getStartDateTime();

        LocalDateTime searchStart = routeRepository.findTimeTrainOnStation(trainEntity, searchDTO.getStartStationId());
        LocalDateTime searchEnd = routeRepository.findTimeTrainOnStation(trainEntity, searchDTO.getEndStationId());

        List<RouteEntity> routesList = routeRepository.findRouteOfTrain(trainId);
        for (RouteEntity routeEntity : routesList) {

            LocalDateTime timeForTrainOnStation = trainStart.plusMinutes(routeEntity.getMinutesFromStartStn());

            if (timeForTrainOnStation.equals(searchStart) || timeForTrainOnStation.isAfter(searchStart)
                    && timeForTrainOnStation.isBefore(searchEnd)){
                resultRoutesList.add(routeEntity);
            }
        }
        return resultRoutesList;
    }



    public List<RouteEntity> findAll(){
        return routeRepository.findAll();
    }

    public void delete(RouteEntity routeEntity){
        routeRepository.delete(routeEntity);
    }

}
