package common.service;

import common.model.Train;
import common.repository.TrainsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class TrainsService {

    @Autowired
    private TrainsRepository trainsRepository;

    public List<Train> findAll(){
        return trainsRepository.listTrains();
    }

    public void addTrain(Train train){
        trainsRepository.addTrain(train);
    }
}
