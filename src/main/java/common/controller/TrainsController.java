package common.controller;

import common.model.TrainEntity;
import common.service.TrainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/** List, create, change, remove trains via web-interface */

@Controller
public class TrainsController {

    @Autowired
    private TrainsService trainsService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        return "redirect:/allTrains";
    }

    @RequestMapping(value = "/allTrains", method = RequestMethod.GET)
    public String showAllTrains(Model model) {
        model.addAttribute("trains", trainsService.findAll());
        return "fragments/allTrainsFragment";
    }

    @RequestMapping(value = "/trains/add", method = RequestMethod.GET)
    public String showTrain(Model model) {
        model.addAttribute("trainFragment", new TrainEntity());
        return "fragments/trainFragment";
    }

    @RequestMapping(value = "/allTrains", method = RequestMethod.POST)
    public String addTrain(@ModelAttribute("trainFragment") TrainEntity trainEntity, Model model){
        trainsService.addTrain(trainEntity);
        return "redirect:/allTrains";
    }


}
