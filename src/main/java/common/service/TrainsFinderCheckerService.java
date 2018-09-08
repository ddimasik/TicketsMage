package common.service;

import common.model.PassengerEntity;
import common.model.Station;
import common.model.TrainEntity;

import java.time.LocalDateTime;

public class TrainsFinderCheckerService {
    TrainEntity findTrain(Station startStation, Station endStation, LocalDateTime startTime){
        return new TrainEntity();
    }

    boolean checkCapacity(TrainEntity trainEntity){
        return false;
    }

    public boolean checkPresenceOfPassenger(TrainEntity trainEntity, PassengerEntity passengerEntity){
        return false;
    }

    boolean checkTime(TrainEntity trainEntity, Station startStation){
        return false;
    }


}
