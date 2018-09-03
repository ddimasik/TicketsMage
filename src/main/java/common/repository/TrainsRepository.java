package common.repository;

import common.model.Station;
import common.model.TrainEntity;

import java.time.LocalTime;
import java.util.List;

public interface TrainsRepository {
    List<TrainEntity> getAllTrains();

    void addTrain(TrainEntity trainEntity);

    TrainEntity findById(Integer id );

    void delete(TrainEntity trainEntity);

    void addStationToRoute(TrainEntity trainEntity, Station station, LocalTime time);
}
