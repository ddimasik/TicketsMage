package common.service;

import common.model.RouteEntity;
import common.model.Station;
import common.model.TrainEntity;
import common.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public void createRoute(TrainEntity trainEntity, Station station, int minutesFromStartStn){
        RouteEntity routeEntity = new RouteEntity();
        routeEntity.setTrainEntityId(trainEntity.getId());
        routeEntity.setStationId(station.getId());
        routeEntity.setMinutesFromStartStn(minutesFromStartStn);
        routeRepository.addRoute(routeEntity);
    }

    public List<RouteEntity> findRouteOfTrain(TrainEntity trainEntity){
        return routeRepository.findRouteOfTrain(trainEntity.getId());
    }
}
