package common.service;

import common.dto.TrainDTO;
import common.model.Station;
import common.model.TrainEntity;
import common.repository.StationsRepository;
import common.repository.TrainsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
public class TrainsService {

    @Autowired
    private TrainsRepository trainsRepository;

    @Autowired
    private StationsRepository stationsRepository;

    public TrainEntity findById(Integer id) {
        return trainsRepository.findById(id);
    }

    public List<TrainEntity> findAll(){
        return trainsRepository.getAllTrains();
    }

    public void addTrain(TrainDTO trainDTO){
        TrainEntity trainEntity = new TrainEntity();
        trainEntity.setName(trainDTO.getName());
        trainEntity.setCapacity(trainDTO.getCapacity());
        trainsRepository.addTrain(trainEntity);
        for (int i = 0; i < stationsRepository.findAll().size(); i++){
            if (trainDTO.getTimeOnStation()[i] != ""){
                Station station = stationsRepository.findById(trainDTO.getStationId()[i]);
                LocalTime time = LocalTime.parse(trainDTO.getTimeOnStation()[i]);
                addStationToRoute(trainEntity, station, time);
            }
        }
    }

    public void removeTrain(int id){
        trainsRepository.delete(findById(id));
    }

    public void addStationToRoute(TrainEntity trainEntity, Station station, LocalTime time){
        trainsRepository.addStationToRoute(trainEntity, station, time);

    }


}
