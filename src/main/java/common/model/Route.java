package common.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @ElementCollection
    public List<Station> stationsList;

    public Route(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Station> getStationsList() {
        return stationsList;
    }

    public void setStationsList(List<Station> stationsList) {
        this.stationsList = stationsList;
    }






}
