package common.service;

import common.dto.TrainDTO;
import common.model.Station;
import common.model.TrainEntity;
import common.repository.StationsRepository;
import common.repository.TrainsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class TrainsService {

    @Autowired
    private TrainsRepository trainsRepository;

    @Autowired
    private StationsRepository stationsRepository;

    @Autowired
    private RouteService routeService;

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
        trainEntity.setStartSt(trainDTO.getStartSt());

        String startDateTimeString = trainDTO.getStartDateTime().toString().replace('T',' ');
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        trainEntity.setStartDateTime(LocalDateTime.parse(startDateTimeString, formatter));
        trainsRepository.addTrain(trainEntity);

        for (int i = 0; i < stationsRepository.findAll().size(); i++){
            if (trainDTO.getMinutesFromStartStn()[i] != 0){
                Station station = stationsRepository.findById(trainDTO.getStationId()[i]);
                int time = trainDTO.getMinutesFromStartStn()[i];
                routeService.createRoute(trainEntity, station, time);
            }
        }
    }

    public void removeTrain(int id){
        trainsRepository.delete(findById(id));
    }

}
