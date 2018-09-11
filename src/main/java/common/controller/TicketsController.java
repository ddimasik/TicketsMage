package common.controller;

import common.dto.PassengerDTO;
import common.dto.SearchDTO;
import common.dto.TicketDTO;
import common.model.TicketEntity;
import common.service.TicketEntityToDtoConverter;
import common.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TicketsController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketEntityToDtoConverter ticketEntityToDtoConverter;


    @PostMapping(value = "/trains/bookTicket/{id}")
    public String bookTicket(@PathVariable("id") int trainId, @ModelAttribute("searchResultFragment") SearchDTO searchDTO, Model model ){
        TicketEntity ticketEntity = ticketService.bookTicketOnTrain(trainId, searchDTO);
        model.addAttribute("ticketId", ticketEntity.getId());
        model.addAttribute("PassengerDTO", new PassengerDTO());
        return "fragments/bookTicketFragment";
    }

    @PostMapping(value = "/trains/buyTicket")
    public String buyTicket(@ModelAttribute("buyTicket") PassengerDTO passengerDTO, Model model){

        TicketEntity ticketEntity = ticketService.addPassengerToTicket(passengerDTO);
        //TicketDTO ticketDTO = ticketEntityToDtoConverter.convert(ticketEntity);
        model.addAttribute("passengerDTO",passengerDTO);
        return "fragments/ticketPaperFragment";
    }

    @PostMapping(value = "/trains/cancelTicket")
    public String cancelTicket(@ModelAttribute("cancelTicket") Model model, @PathVariable("ticketId") int ticketId){

        return "fragments/cancelPaper";
    }


}
