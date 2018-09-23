package common.controller;

import common.dto.TrainDTO;
import common.model.TrainEntity;
import common.converters.entity_to_dto.TrainEntityToDtoConverter;
import common.service.PassengerService;
import common.service.StationService;
import common.service.TrainsService;
import common.validator.TrainFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/** List, create, change, remove trains via web-interface */

@Controller
public class TrainsController {

    private static final String STATIONS_LIST = "stations";
    private static final String DANGER = "danger";

    @Autowired
    private TrainsService trainsService;

    @Autowired
    private StationService stationService;

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private TrainEntityToDtoConverter trainEntityToDtoConverter;

    @Autowired
    private TrainFormValidator trainFormValidator;


    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(trainFormValidator);
    }

    @GetMapping(value = "/allTrains")
    public String showAllTrains(Model model) {

        List<TrainDTO> trainDTOList = new LinkedList<>();
        for (TrainEntity trainEntity : trainsService.findAll()) {
            trainDTOList.add(trainEntityToDtoConverter.convert(trainEntity));
        }
        model.addAttribute("trains", trainDTOList);
        return "fragments/allTrainsFragment";
    }

    @GetMapping(value = "/trains/add")
    public String showTrain(Model model) {
        model.addAttribute("trainFragment", new TrainDTO());
        model.addAttribute(STATIONS_LIST, stationService.findAll());
        return "fragments/trainFragment";
    }

    @GetMapping(value = "/trains/{id}/passengers")
    public String showPassengers(@PathVariable("id") int id, Model model) {

        model.addAttribute("trainId", id);
        model.addAttribute("trainName", trainsService.findById(id).getName());

        if (!passengerService.findAllByTrain(id).isEmpty()){
            model.addAttribute("passengers", passengerService.findAllByTrain(id));
            return "fragments/passengersOfTrainFragment";
        }

        List<TrainDTO> trainDTOList = new LinkedList<>();
        for (TrainEntity trainEntity : trainsService.findAll()) {
            trainDTOList.add(trainEntityToDtoConverter.convert(trainEntity));
        }
        model.addAttribute("trains", trainDTOList);
        model.addAttribute("css", DANGER);
        model.addAttribute("msg", "No passengers present on train ");

        return "fragments/allTrainsFragment";
    }

    @PostMapping(value = "/allTrains")
    public String addTrain(@ModelAttribute("trainFragment") @Validated TrainDTO trainDTO, BindingResult result, Model model){

        if (result.hasErrors()){
            for (ObjectError error : result.getAllErrors()) {
                if (error.getDefaultMessage() != null && error.getDefaultMessage().equals("wrong.full.route.time")){
                    model.addAttribute("msg", "Wrong route times: should be zero or greater than 30 and less than 300");
                } else {
                    model.addAttribute("msg", "Error detected, check field with warning");
                }
            }
            model.addAttribute("css", DANGER);
            model.addAttribute("startDateTime", trainDTO.getStartDateTime().toString());
            model.addAttribute(STATIONS_LIST, stationService.findAll());
            return "fragments/trainFragment";
        }
        trainsService.addTrain(trainDTO);
        return "redirect:/allTrains";
    }

    @PostMapping(value = "/trains/{id}/delete")
    public String deleteTrain(@PathVariable("id") int id) {
        trainsService.removeTrain(id);
        return "redirect:/allTrains";
    }
}
