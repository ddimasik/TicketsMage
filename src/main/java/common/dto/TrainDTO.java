package common.dto;

import lombok.Getter;
import lombok.Setter;



public class TrainDTO {

    @Setter @Getter
    int id;

    @Setter @Getter
    int capacity;

    @Setter @Getter
    String name;

    @Setter @Getter
    int [] stationId;

    @Setter @Getter
    String [] timeOnStation;

    @Setter @Getter
    String [] startSt;

}
