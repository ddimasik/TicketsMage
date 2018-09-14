package common.converters.dto_to_entity;

import common.dto.PassengerDTO;
import common.model.PassengerEntity;

public interface PassengerDtoToEntityConverter {
    PassengerEntity convert(PassengerDTO passengerDTO);
}
