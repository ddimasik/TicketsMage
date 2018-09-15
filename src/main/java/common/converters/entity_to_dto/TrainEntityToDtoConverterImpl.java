package common.converters.entity_to_dto;

import common.dto.TrainDTO;
import common.model.RouteEntity;
import common.model.TrainEntity;
import common.repository.StationsRepository;
import common.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public class TrainEntityToDtoConverterImpl implements TrainEntityToDtoConverter, Converter<TrainEntity, TrainDTO> {

    @Autowired
    private RouteService routeService;

    @Autowired
    private StationsRepository stationsRepository;

    @Override
    public TrainDTO convert(TrainEntity trainEntity) {
        TrainDTO trainDTO = new TrainDTO();
        trainDTO.setId(trainEntity.getId());
        trainDTO.setName(trainEntity.getName());
        trainDTO.setCapacity(trainEntity.getCapacity());
        trainDTO.setStartDateTime(trainEntity.getStartDateTime());
        int routesQuantity = routeService.findRouteOfTrain(trainEntity).size();
        int [] stIds = new int[routesQuantity];
        int [] stTime = new int[routesQuantity];
        String [] stationName = new String[routesQuantity];
        Object [] timeOnStation = new Object[routesQuantity];
        int j = 0;
        for (RouteEntity re : routeService.findRouteOfTrain(trainEntity)) {
            stIds[j] = re.getStationId();
            stTime[j] = re.getMinutesFromStartStn();
            stationName[j] = stationsRepository.findById(re.getStationId()).getName();
            timeOnStation[j] = trainEntity.getStartDateTime().plusMinutes(re.getMinutesFromStartStn());
            j++;
        }
        trainDTO.setStationId(stIds);
        trainDTO.setMinutesFromStartStn(stTime);
        trainDTO.setStationName(stationName);
        trainDTO.setTimeOnStation(timeOnStation);
        return trainDTO;

    }
}
