package common.controller;

import common.service.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TicketsController {

    @Autowired
    private TicketsService ticketsService;

    @RequestMapping(value = "/allTickets", method = RequestMethod.GET)
    public String showAllTickets(Model model) {
        model.addAttribute("tickets", ticketsService.findAll());
        return "fragments/allTicketsFragment";
    }
}
