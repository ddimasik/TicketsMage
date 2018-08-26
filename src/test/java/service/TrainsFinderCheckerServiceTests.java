package service;

import common.model.Passenger;
import common.model.Ticket;
import common.model.Train;
import common.service.TrainsFinderCheckerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TrainsFinderCheckerServiceTests {

    static Train train = new Train();
    static Passenger passenger = new Passenger();
    static Ticket ticket = new Ticket();

    @Before
    public void setUp(){
        train.setId(42);
        passenger.setId(33);
        ticket.setPassenger(passenger);
        ticket.setTrain(train);
    }

    @Test
    public void PassengerIsAlreadyOnTrain(){
        TrainsFinderCheckerService trainsFinderCheckerService = new TrainsFinderCheckerService();
        trainsFinderCheckerService.checkPresenceOfPassenger(train,passenger);
        Assert.assertEquals(ticket.getTrain(), train);
        Assert.assertEquals(ticket.getPassenger(), passenger);

    }

}
