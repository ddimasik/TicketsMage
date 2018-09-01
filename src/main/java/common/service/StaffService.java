package common.service;

import common.model.Station;
import common.model.TrainEntity;

import java.time.LocalDateTime;
import java.util.Map;

public class StaffService {
    void createStation(String station){

    }
    TrainEntity createTrain (Map<Station, LocalDateTime> route, int Capacity){
        return  new TrainEntity();
    }

}
