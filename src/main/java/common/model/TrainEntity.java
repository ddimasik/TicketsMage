package common.model;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

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

    @ElementCollection
    @CollectionTable(name = "routes")
    public Map<Station, LocalTime> route = new LinkedHashMap<>();


}
