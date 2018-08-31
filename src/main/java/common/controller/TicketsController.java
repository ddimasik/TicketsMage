package common.controller;

import common.service.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TicketsController {

//    @Autowired
//    private TicketsService ticketsService;
//
//    @GetMapping(path = "/allTickets")
//    public String showAllTickets(Model model) {
//        model.addAttribute("tickets", ticketsService.findAll());
//        return "fragments/allTicketsFragment";
//    }
}
