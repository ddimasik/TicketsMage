package common.validator;


import common.dto.TrainDTO;
import common.repository.TrainsRepository;
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

    //TODO как задать вместимость в одном месте? вынести в адиминку? а как в validation.message передать?
    private static final int MAX_CAP = 1001;
    private static final int MAX_NAME_LEN = 33;
    private static final int MIN_MINUTES_BETWEEN_TWO_STATIONS = 30;

    private final Logger logger = LoggerFactory.getLogger(TrainFormValidator.class);

    @Autowired
    private TrainsService trainsService;

    @Autowired
    private TrainsRepository trainsRepository;

    @Override
    public boolean supports(Class<?> aClass) {  return TrainDTO.class.equals(aClass); }

    @Override
    public void validate(Object target, Errors errors) {

        TrainDTO trainDTO = (TrainDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name", "empty.train.name");

        if (trainDTO.getName().length() > MAX_NAME_LEN){
            logger.debug("Too long name {}", trainDTO.getName());
            errors.rejectValue("name", "too.long.train.name");
        }

        if (trainsRepository.findByName(trainDTO.getName())){
            logger.debug("Train with name {} already exists", trainDTO.getName());
            errors.rejectValue("name", "train.name.already.exists");
        }

        if(trainDTO.getCapacity() <= 0 || trainDTO.getCapacity() > MAX_CAP){
            logger.debug("Set wrong capacity {}", trainDTO.getCapacity());
            errors.rejectValue("capacity", "Wrong.train.capacity");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"startDateTime", "empty.start.datetime");

        if(trainDTO.getStartSt() <= 0 ){
            logger.debug("Set wrong start station {}", trainDTO.getStartSt());
            errors.rejectValue("startSt", "empty.start.station");
        }

        int minutesSumm = 0;
        if (trainDTO.getMinutesFromStartStn().length > 0 && trainDTO.getStationId().length > 0) {
            for (int i = 0; i < trainDTO.getMinutesFromStartStn().length; i++){
                if (trainDTO.getMinutesFromStartStn()[i] != 0 && trainDTO.getMinutesFromStartStn()[i] < MIN_MINUTES_BETWEEN_TWO_STATIONS){
                    logger.debug("Wrong minutes from start {}", trainDTO.getMinutesFromStartStn()[i]);
                    errors.reject("minutesFromStartStn","wrong.minutes.from.start");
                    break;
                }
                minutesSumm += trainDTO.getMinutesFromStartStn()[i];
            }
        } else {
            logger.debug("Empty station or minutes list {}", trainDTO);
            errors.reject("empty.stationsMinutes.list");
        }


        if (minutesSumm <= 0) {
            logger.debug("Wrong full route time {}", minutesSumm);
            errors.reject("wrong.full.route.time", "wrong.full.route.time");

        }

    }
}
