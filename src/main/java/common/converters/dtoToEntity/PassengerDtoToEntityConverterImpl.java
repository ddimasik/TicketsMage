package common.converters.dtoToEntity;

import common.dto.PassengerDTO;
import common.model.PassengerEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Transactional
public class  PassengerDtoToEntityConverterImpl implements PassengerDtoToEntityConverter, Converter<PassengerDTO, PassengerEntity> {

    @Override
    public PassengerEntity convert(PassengerDTO passengerDTO) {
        PassengerEntity passengerEntity = new PassengerEntity();
        passengerEntity.setName(passengerDTO.getName());
        passengerEntity.setSurname(passengerDTO.getSurname());
        passengerEntity.setBirthday(LocalDate.parse(passengerDTO.getBirthday().toString()));
        return passengerEntity;
    }
}
