package common.controller;

import common.service.TrainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrainsController {

    @Autowired
    private TrainsService trainsService;

    @GetMapping(name = "allTrains")
    public String showAllTtains(Model model) {
        model.addAttribute("trains", trainsService.findAll());
        return "fragments/allTrainsFragment";
    }


}
