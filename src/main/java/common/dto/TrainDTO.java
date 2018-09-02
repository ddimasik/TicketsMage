package common.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;


public class TrainDTO {

    @Setter @Getter
    int id;

    @Setter @Getter
    int capacity;

    @Setter @Getter
    String name;

    @Setter @Getter
    int [] station_id;

    @Setter @Getter
    String [] time_on_station;

    @Setter @Getter
    String [] start_st;

}
