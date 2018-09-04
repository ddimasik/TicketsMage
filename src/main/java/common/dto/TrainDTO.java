package common.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TrainDTO {

    int id;

    int capacity;

    String name;

    StationDTO [] stationIdDTO;

    int [] stationId;

    String [] timeOnStation;

    String [] startSt;

}
