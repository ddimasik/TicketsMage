package common.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchDTO {

    private int startStationId;

    private int endStationId;

    //TODO debug the model
    private Object startDateTime;

    private Object endDateTime;

}
