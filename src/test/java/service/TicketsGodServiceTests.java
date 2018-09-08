package service;

import common.model.PassengerEntity;
import common.model.Station;
import common.model.TrainEntity;
import common.service.TicketsGodService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TicketsGodServiceTests {

    static TrainEntity trainEntity = new TrainEntity();
    static PassengerEntity passengerEntity = new PassengerEntity();
    static Station startStation = new Station();
    static Station endtStation = new Station();

    @Before
    public void setUp(){
        //trainEntity.setId(42);
        passengerEntity.setId(33);
    }

    @Test
    public void CreateTicketNotSuccess(){
        TicketsGodService ticketsGodService = new TicketsGodService();
        Assert.assertFalse(ticketsGodService.createTicket(trainEntity, startStation, endtStation, passengerEntity));
    }
}
