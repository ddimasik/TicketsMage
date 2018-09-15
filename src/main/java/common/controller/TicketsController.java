package common.controller;

import common.dto.PassengerDTO;
import common.dto.SearchDTO;
import common.dto.TicketDTO;
import common.model.TicketEntity;
import common.service.PassengerService;
import common.service.StationService;
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

    @Autowired
    private StationService stationService;


    /** bookingResult can be either thicketId or -1 that means that no ticket is available */
    @PostMapping(value = "/trains/bookTicket/{id}")
    public String bookTicket(@PathVariable("id") int trainId, @ModelAttribute("searchResultFragment") SearchDTO searchDTO, Model model ){
        //TODO чот я не поняла, зачем ты передаешь searchDTO, выглядит не очень

        int bookingResult = ticketService.bookTicketOnTrain(trainId, searchDTO);

        if (bookingResult != -1){
            model.addAttribute("msg", "");
            model.addAttribute("ticketId", bookingResult);
            model.addAttribute("PassengerDTO", new PassengerDTO());
            return "fragments/bookTicketFragment";
        } else {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "No available tickets, try changing search, please.");
            model.addAttribute("searchTrainFragment", new SearchDTO());
            model.addAttribute("stations",stationService.findAll());
            return "fragments/searchTrainFragment";
        }
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
