package common.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "routes")
@Setter
@Getter
public class RouteEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "time")
    private LocalTime time;

    @Column
    private int trainEntityId;

    @Column
    private int stationId;

}
