package common.service;

import common.model.Train;
import common.repository.TrainsDAO;
import common.repository.TrainsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrainsService {

    @Autowired
    TrainsDAO trainsDAO;

    @Transactional
    public List<Train> findAll(){
        return trainsDAO.getAllTrains();
    }

    @Transactional
    public void addTrain(Train train){
        trainsDAO.addTrain(train);
    }
}
