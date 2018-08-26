package service;

import common.model.Passenger;
import common.model.Station;
import common.model.Train;
import common.service.TicketsGodService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TicketsGodServiceTests {

    static Train train = new Train();
    static Passenger passenger = new Passenger();
    static Station startStation = new Station();
    static Station endtStation = new Station();

    @Before
    public void setUp(){
        train.setId(42);
        passenger.setId(33);
    }

    @Test
    public void CreateTicketNotSuccess(){
        TicketsGodService ticketsGodService = new TicketsGodService();
        Assert.assertFalse(ticketsGodService.createTicket(train, startStation, endtStation, passenger));
    }
}
