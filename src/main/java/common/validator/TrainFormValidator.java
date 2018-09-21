package common.validator;


import common.dto.TrainDTO;
import common.service.TrainsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TrainFormValidator implements Validator {

    private final Logger logger = LoggerFactory.getLogger(TrainFormValidator.class);


    @Autowired
    TrainsService trainsService;

    @Override
    public boolean supports(Class<?> aClass) {  return TrainDTO.class.equals(aClass); }


    @Override
    public void validate(Object target, Errors errors) {

        TrainDTO trainDTO = (TrainDTO) target;

        //if(station.getId()==null){errors.rejectValue("id", "Empty.stationForm.id");}
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.stationForm.name");

        if(trainDTO.getCapacity() <= 0){
            logger.debug("Set wrong capacity {}", trainDTO.getCapacity());
            errors.rejectValue("capacity", "Wrong.train.capacity");
        }

    }
}
