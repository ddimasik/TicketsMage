package common.service;

import common.dto.TrainDTO;
import common.model.RouteEntity;
import common.model.TrainEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.transaction.annotation.Transactional;


//@Service
@Transactional
public class EntityToDtoConverterImpl implements EntityToDtoConverter, Converter<TrainEntity, TrainDTO> {

    @Autowired
    private RouteService routeService;

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
        int j = 0;
        for (RouteEntity re : routeService.findRouteOfTrain(trainEntity)) {
            stIds[j] = re.getStationId();
            stTime[j] = re.getMinutesFromStartStn();
            j++;
        }
        trainDTO.setStationId(stIds);
        trainDTO.setMinutesFromStartStn(stTime);
        return trainDTO;

    }
}
