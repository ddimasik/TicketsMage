package common.repository;

import common.model.TrainEntity;

import java.util.List;

public interface TrainsRepository {
    List<TrainEntity> getAllTrains();

    void addTrain(TrainEntity trainEntity);

    TrainEntity findById(Integer id );

    void delete(TrainEntity trainEntity);

}
