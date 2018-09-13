package common.converters.dtoToEntity;

import common.dto.PassengerDTO;
import common.model.PassengerEntity;

public interface PassengerDtoToEntityConverter {
    PassengerEntity convert(PassengerDTO passengerDTO);
}
