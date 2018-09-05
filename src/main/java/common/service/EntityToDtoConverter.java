package common.service;

import common.dto.TrainDTO;
import common.model.TrainEntity;

public interface EntityToDtoConverter  {

   TrainDTO convert(TrainEntity trainEntity);

}
