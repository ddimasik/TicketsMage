package common.service;

import common.dto.TrainDTO;
import common.model.Station;
import common.model.TrainEntity;
import common.repository.StationsRepository;
import common.repository.TrainsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class TrainsService {

    @Autowired
    TrainsDAO trainsDAO;

    @Autowired
    StationsRepository stationsRepository;

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
        for (int i = 0; i < stationsRepository.findAll().size(); i++){
            if (trainDTO.getTime_on_station()[i] != ""){
                Station station = stationsRepository.findById(trainDTO.getStation_id()[i]);
                LocalTime time = LocalTime.parse(trainDTO.getTime_on_station()[i]);
                addStationToRoute(trainEntity, station, time);
            }
        }
    }

    @Transactional
    public void removeTrain(int id){
        trainsDAO.delete(findById(id));
    }

    @Transactional
    public void addStationToRoute(TrainEntity trainEntity, Station station, LocalTime time){
        trainsDAO.addStationToRoute(trainEntity, station, time);

    }


}
