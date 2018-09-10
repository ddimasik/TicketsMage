package common.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchDTO {

    private int startStn;

    private int endStn;

    //TODO debug the model
    private Object startDateTime;

    private Object endDateTime;

}
