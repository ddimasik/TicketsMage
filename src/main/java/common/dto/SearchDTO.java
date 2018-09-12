package common.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//TODO    SearchDTO лучше переименовать, чтобы было понятно, что это В САМОМ ДЕЛЕ параметры поиска поезда

public class SearchDTO {

    private int startStationId;

    private int endStationId;

    //TODO debug the model
    private Object startDateTime;

    private Object endDateTime;

}
