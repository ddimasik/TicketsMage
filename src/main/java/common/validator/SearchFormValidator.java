package common.validator;

import common.dto.SearchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

@Component
public class SearchFormValidator implements Validator {

    private static Logger logger = LoggerFactory.getLogger(SearchFormValidator.class);

    @Override
    public boolean supports(Class<?> aClass) {return SearchDTO.class.equals(aClass);}

    @Override
    public void validate(Object target, Errors errors) {

        SearchDTO searchDTO = (SearchDTO) target;

        if (searchDTO.getStartDateTime() == "" ){
            logger.debug("Empty start time");
            errors.rejectValue("startDateTime", "empty.start.datetime");
            return;
        }

        if (searchDTO.getEndDateTime() == "" ){
            logger.debug("Empty end time");
            errors.rejectValue("endDateTime", "empty.end.datetime");
            return;
        }

        if (LocalDateTime.parse(searchDTO.getStartDateTime().toString()).isAfter(LocalDateTime.parse(searchDTO.getEndDateTime().toString()))
            || LocalDateTime.parse(searchDTO.getStartDateTime().toString()).isEqual(LocalDateTime.parse(searchDTO.getEndDateTime().toString()))){
            logger.debug("Start time set AFTER end time");
            errors.rejectValue("startDateTime", "start.time.after.end.time");
        }

        if (searchDTO.getStartStationId() == searchDTO.getEndStationId()){
            logger.debug("Same stations {} {} in query", searchDTO.getStartStationId(), searchDTO.getEndStationId());
            errors.rejectValue("endStationId", "stations.are.same");
        }

    }
}
