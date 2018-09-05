package common.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TrainDTO {

    private int id;

    private int capacity;

    private String name;

    private StationDTO [] stationIdDTO;

    private int [] stationId;

    private String [] timeOnStation;

    private String [] startSt;

}
