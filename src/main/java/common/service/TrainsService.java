package common.service;

import common.dto.TrainDTO;
import common.model.TrainEntity;
import common.repository.TrainsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrainsService {

    @Autowired
    TrainsDAO trainsDAO;

    @Transactional
    public TrainEntity findById(Integer id) {
        return trainsDAO.findById(id);
    }

    @Transactional
    public List<TrainEntity> findAll(){
        return trainsDAO.getAllTrains();
    }

    @Transactional
    public void addTrain(TrainDTO trainDTO){
        TrainEntity trainEntity = new TrainEntity();
        trainEntity.setName(trainDTO.getName());
        trainEntity.setCapacity(trainDTO.getCapacity());
        trainsDAO.addTrain(trainEntity);
    }

    @Transactional
    public void removeTrain(int id){
        trainsDAO.delete(findById(id));
    }


}
