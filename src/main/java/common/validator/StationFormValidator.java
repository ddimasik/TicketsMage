package common.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import common.model.Station;
import common.service.StationService;

@Component
public class StationFormValidator implements Validator {

    @Autowired
    StationService stationService;

    //@Override
    public boolean supports(Class<?> clazz) {
        return Station.class.equals(clazz);
    }

    //@Override
    public void validate(Object target, Errors errors) {

        Station station = (Station) target;

        //if(station.getId()==null){errors.rejectValue("id", "Empty.stationForm.id");}

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.stationForm.name");
    }
}

