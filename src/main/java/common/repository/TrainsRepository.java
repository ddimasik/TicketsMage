package common.repository;

import common.model.TrainEntity;

import java.util.List;

public interface TrainsRepository {
    List<TrainEntity> getAllTrains();

    void addTrain(TrainEntity trainEntity);

    TrainEntity findById(Integer id );

    boolean findByName(String name);

    void delete(TrainEntity trainEntity);

}
