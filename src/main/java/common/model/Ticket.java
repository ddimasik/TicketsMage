package common.model;

public class Ticket {
    int Id;
    Train train;
    Passenger passenger;
    Station startStation;
    Station endStation;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Station getStartStation() {
        return startStation;
    }

    public void setStartStation(Station startStation) {
        this.startStation = startStation;
    }

    public Station getEndStation() {
        return endStation;
    }

    public void setEndStation(Station endStation) {
        this.endStation = endStation;
    }

    void showAllPassengers(Train train){


    }

}
