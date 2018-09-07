package common.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "routes")
@Setter
@Getter
public class RouteEntity {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private int trainEntityId;

    @Column
    private int stationId;

    @Column
    private int minutesFromStartStn;

    @Column
    private int freeSeatsOnStn;

}
