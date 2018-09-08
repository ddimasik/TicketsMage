package common.controller;

import common.service.StationService;
import common.service.TicketService;
import common.service.TrainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TicketsController {

    @Autowired
    private TrainsService trainsService;

    @Autowired
    private StationService stationService;

    @Autowired
    private TicketService ticketService;


}
