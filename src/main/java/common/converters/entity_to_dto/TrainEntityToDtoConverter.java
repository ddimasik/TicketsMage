package common.converters.entity_to_dto;

import common.dto.TrainDTO;
import common.model.TrainEntity;

public interface TrainEntityToDtoConverter {

   TrainDTO convert(TrainEntity trainEntity);

}
