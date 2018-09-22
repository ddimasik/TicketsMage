package common.validator;

import common.dto.StationDTO;
import common.repository.StationsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class StationFormValidator implements Validator {

    private static final int MAX_NAME_LEN = 33;

    private final Logger logger = LoggerFactory.getLogger(StationFormValidator.class);

    @Autowired
    private StationsRepository stationsRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return StationDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        StationDTO stationDTO = (StationDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.stationForm.name");

        if (stationDTO.getName().length() > MAX_NAME_LEN){
            logger.debug("Too long name {}", stationDTO.getName());
            errors.rejectValue("name", "too.long.station.name");
        }

        if (stationsRepository.findByName(stationDTO.getName())){
            logger.debug("Station with name {} already exists", stationDTO.getName());
            errors.rejectValue("name", "station.name.already.exists");
        }


    }
}

