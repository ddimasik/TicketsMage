package common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDTO {

    private int id;
    private int trainId;
    private int passengerId;
    private int startStationId;
    private int endStationId;
}
