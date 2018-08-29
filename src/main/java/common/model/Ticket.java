package common.model;

import lombok.Getter;
import lombok.Setter;

public class Ticket {

    @Getter
    @Setter
    int Id;

    @Getter
    @Setter
    int Train_id;

    @Getter
    @Setter
    int Passenger_id;

    @Setter
    @Getter
    int StartStation_id;

    @Getter
    @Setter
    int EndStation_id;

}
