package common.model;

import java.time.LocalDateTime;
import java.util.Map;

public class Train {
    int Id;
    int Capacity;
    Map<Station, LocalDateTime> route;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    public Map<Station, LocalDateTime> getRoute() {
        return route;
    }

    public void setRoute(Map<Station, LocalDateTime> route) {
        this.route = route;
    }

    void showTimeTable(){

    }

    void showStTimeTable(Train train){

    }

    void showAllTrains(){

    }


}
