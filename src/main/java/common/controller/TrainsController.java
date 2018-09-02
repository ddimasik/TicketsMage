package common.controller;

import common.dto.TrainDTO;
import common.service.TrainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/** List, create, change, remove trains via web-interface */

@Controller
public class TrainsController {

    @Autowired
    private TrainsService trainsService;

//    @GetMapping(value = "/")
//    public String index(Model model) {
//        return "redirect:/allTrains";
//    }

    @GetMapping(value = "/allTrains")
    public String showAllTrains(Model model) {
        model.addAttribute("trains", trainsService.findAll());
        return "fragments/allTrainsFragment";
    }

    @GetMapping(value = "/trains/add")
    public String showTrain(Model model) {
        model.addAttribute("trainFragment", new TrainDTO());
        return "fragments/trainFragment";
    }

    @PostMapping(value = "/allTrains")
    public String addTrain(@ModelAttribute("trainFragment") TrainDTO trainDTO, Model model){
        trainsService.addTrain(trainDTO);
        return "redirect:/allTrains";
    }

    @PostMapping(value = "/trains/{id}/delete")
    public String deleteTrain(@PathVariable("id") int id) {
        trainsService.removeTrain(id);
        return "redirect:/allTrains";
    }
}
