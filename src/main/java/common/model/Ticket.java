package common.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class Ticket {

    @Getter
    @Setter
    int id;

    @Getter
    @Setter
//    TrainEntity train;
    int train_id;

    @Getter
    @Setter
    int passenger_id;

    @Setter
    @Getter
    int startStation_id;

    @Getter
    @Setter
    int endStation_id;

    @Setter
    @Getter
    LocalDateTime dateTime;

}
