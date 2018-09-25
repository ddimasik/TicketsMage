package common.controller;

import common.dto.PassengerDTO;
import common.dto.SearchDTO;
import common.dto.TicketDTO;
import common.service.StationService;
import common.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class TicketsController {

    private static final String TICKET_ID = "ticketId";
    private static final String PASSENGER_DTO = "PassengerDTO";
    private static final String FRAGMENTS_BOOK_TICKET_FRAGMENT = "fragments/bookTicketFragment";
    private static final String DANGER = "danger";


    @Autowired
    private TicketService ticketService;

    @Autowired
    private  StationService stationService;

    /** bookingResult can be either thicketId or -1 that means that no ticket is available */
    @PostMapping(value = "/trains/bookTicket/{id}")
    public String bookTicket(@PathVariable("id") int trainId, @ModelAttribute("searchResultFragment") SearchDTO searchDTO, Model model ){
        //TODO чот я не поняла, зачем ты передаешь searchDTO, выглядит не очень

        int bookingResult = ticketService.bookTicketOnTrain(trainId, searchDTO);

        if (bookingResult != -1){
            model.addAttribute("msg", "");
            model.addAttribute(TICKET_ID, bookingResult);
            model.addAttribute(PASSENGER_DTO, new PassengerDTO());
            return FRAGMENTS_BOOK_TICKET_FRAGMENT;
        } else {
            model.addAttribute("css", DANGER);
            model.addAttribute("msg", "No available tickets, try changing search, please.");
            model.addAttribute("searchTrainFragment", new SearchDTO());
            model.addAttribute("stations",stationService.findAll());
            return "fragments/searchTrainFragment";
        }
    }

    @PostMapping(value = "/trains/buyTicket")
    public String buyTicket(@ModelAttribute("buyTicket") PassengerDTO passengerDTO, Model model){

        if (passengerDTO.getName().isEmpty()
            || passengerDTO.getSurname().isEmpty()
            || passengerDTO.getBirthday().toString().isEmpty()){
            model.addAttribute("css", DANGER);
            model.addAttribute("msg", "Fill all fields");
            model.addAttribute(TICKET_ID, passengerDTO.getTicketId());
            model.addAttribute(PASSENGER_DTO, passengerDTO);
            return FRAGMENTS_BOOK_TICKET_FRAGMENT;
        }


        if (ticketService.validate(passengerDTO)){
            TicketDTO ticketDTO = ticketService.buyTicket(passengerDTO);
            model.addAttribute("ticketDTO",ticketDTO);
            return "fragments/ticketPaperFragment";

        } else {
            model.addAttribute("css", DANGER);
            model.addAttribute("msg", "This passenger is already on the train. Enter another data, please.");
            model.addAttribute(TICKET_ID, passengerDTO.getTicketId());
            model.addAttribute(PASSENGER_DTO, passengerDTO);
            return FRAGMENTS_BOOK_TICKET_FRAGMENT;
        }
    }

    @PostMapping(value = "/trains/cancelTicket")
    public String cancelTicket(@ModelAttribute("cancelTicket") Model model, @PathVariable(TICKET_ID) int ticketId){

        return "fragments/cancelPaper";
    }


}
