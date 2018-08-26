package common.service;

import common.model.Station;
import common.model.Train;

import java.time.LocalDateTime;
import java.util.Map;

public class StaffService {
    void createStation(String station){

    }
    Train createTrain (Map<Station, LocalDateTime> route, int Capacity){
        return  new Train();
    }

}
