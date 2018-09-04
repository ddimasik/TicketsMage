package common.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "trains")
@Setter
@Getter
public class TrainEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "name")
    private String name;
}
