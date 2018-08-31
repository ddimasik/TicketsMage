package common.repository;

import common.model.Train;

import java.util.List;

public interface TrainsDAO {
    List<Train> getAllTrains();
    void addTrain(Train train);
}
