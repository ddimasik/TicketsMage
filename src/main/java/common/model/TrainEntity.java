package common.model;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "trains")
public class TrainEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    @Getter
    int id;

    @Column(name = "capacity")
    @Setter
    @Getter
    int capacity;

    @Column(name = "name")
    @Setter
    @Getter
    String name;


    @Column(name = "valenki")
    @Setter
    @Getter
    String valenki;



}
