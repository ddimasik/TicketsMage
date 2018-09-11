package common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassengerDTO {

    private int ticketId;
    private String name;
    private String surname;
    private Object birthday;
}
