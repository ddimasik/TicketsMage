package common.controller;

import common.dto.TrainDTO;
import common.model.TrainEntity;
import common.service.EntityToDtoConverter;
import common.service.StationService;
import common.service.TrainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/** List, create, change, remove trains via web-interface */

@Controller
public class TrainsController {

    @Autowired
    private TrainsService trainsService;

    @Autowired
    private StationService stationService;

    @Autowired
    private EntityToDtoConverter entityToDtoConverter;

    @GetMapping(value = "/allTrains")
    @Transactional
    public String showAllTrains(Model model) {

        List<TrainDTO> trainDTOList = new LinkedList<>();
        for (TrainEntity trainEntity : trainsService.findAll()) {
            trainDTOList.add(entityToDtoConverter.convert(trainEntity));
        }
        model.addAttribute("trains", trainDTOList);
        return "fragments/allTrainsFragment";
    }

    @GetMapping(value = "/trains/add")
    public String showTrain(Model model) {
        model.addAttribute("trainFragment", new TrainDTO());
        model.addAttribute("stations", stationService.findAll());
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
