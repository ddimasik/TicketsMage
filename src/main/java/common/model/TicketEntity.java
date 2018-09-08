package common.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
@Setter
@Getter
public class TicketEntity{

    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private int trainId;

    @Column
    private int passengerId;

    @Column
    private int startStationId;

    @Column
    private int endStationId;


}
