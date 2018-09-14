package common.controller;

import common.dto.PassengerDTO;
import common.dto.SearchDTO;
import common.dto.TicketDTO;
import common.model.TicketEntity;
import common.service.PassengerService;
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
    private PassengerService passengerService;

    @Autowired
    private TicketEntityToDtoConverter ticketEntityToDtoConverter;


    @PostMapping(value = "/trains/bookTicket/{id}")
    public String bookTicket(@PathVariable("id") int trainId, @ModelAttribute("searchResultFragment") SearchDTO searchDTO, Model model ){
        //TODO чот я не поняла, зачем ты передаешь searchDTO, выглядит не очень
        TicketEntity ticketEntity = ticketService.bookTicketOnTrain(trainId, searchDTO);
        model.addAttribute("msg", "");
        model.addAttribute("ticketId", ticketEntity.getId());
        model.addAttribute("PassengerDTO", new PassengerDTO());
        return "fragments/bookTicketFragment";
    }

    @PostMapping(value = "/trains/buyTicket")
    public String buyTicket(@ModelAttribute("buyTicket") PassengerDTO passengerDTO, Model model){

        if (ticketService.validate(passengerDTO)){
            TicketDTO ticketDTO = ticketService.buyTicket(passengerDTO);
            model.addAttribute("ticketDTO",ticketDTO);
            return "fragments/ticketPaperFragment";

        } else {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "This passenger already is on the train. Enter another data, please.");
            model.addAttribute("ticketId", passengerDTO.getTicketId());
            model.addAttribute("PassengerDTO", passengerDTO);
            return "fragments/bookTicketFragment";
        }
    }

    @PostMapping(value = "/trains/cancelTicket")
    public String cancelTicket(@ModelAttribute("cancelTicket") Model model, @PathVariable("ticketId") int ticketId){

        return "fragments/cancelPaper";
    }


}
