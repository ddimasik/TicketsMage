package common.controller;

import common.dto.PassengerDTO;
import common.dto.SearchDTO;
import common.dto.TrainDTO;
import common.repository.TrainsRepository;
import common.service.StationService;
import common.service.TicketService;
import common.service.TrainsService;
import common.validator.SearchFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class SearchController {

    private static final String STATIONS_LIST = "stations";
    private static final String TRAIN_SEARCH_FRAGMENT = "fragments/searchTrainFragment";

    @Autowired
    private TrainsService trainsService;

    @Autowired
    private StationService stationService;

    @Autowired
    private SearchFormValidator searchFormValidator;

    @Autowired
    private TicketService ticketService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(searchFormValidator);
    }

    @GetMapping(value = "/trains/search")
    public String searchTrain(Model model){
        model.addAttribute("searchTrainFragment", new SearchDTO());
        model.addAttribute("startDateTime", LocalDateTime.now().toString());
        model.addAttribute("endDateTime", LocalDateTime.now().plusWeeks(1).toString());
        model.addAttribute(STATIONS_LIST,stationService.findAll());
        return TRAIN_SEARCH_FRAGMENT;
    }

    @GetMapping(value = "/trains/searchResult")
    public String searchResult(@ModelAttribute("searchTrainFragment") @Validated SearchDTO searchDTO, BindingResult result, Model model){

        if (result.hasErrors()){
            populateModel(searchDTO, model);
            return TRAIN_SEARCH_FRAGMENT;
        }

        List<TrainDTO> trainDTOList = trainsService.findSuitableTrains(searchDTO);
        if (trainDTOList.isEmpty()){
            model.addAttribute("css",  "danger");
            model.addAttribute("msg", "No available tickets, try changing search, please.");
            populateModel(searchDTO, model);
            return TRAIN_SEARCH_FRAGMENT;
        } else {
            model.addAttribute("searchResult", trainDTOList);
            model.addAttribute("searchDTO", searchDTO);
            return "fragments/searchResultFragment";
        }
    }


    private void populateModel(SearchDTO searchDTO, Model model){
        model.addAttribute("searchTrainFragment", searchDTO);
        if (searchDTO.getStartDateTime() != null){
            model.addAttribute("startDateTime", searchDTO.getStartDateTime().toString());
        }
        if (searchDTO.getEndDateTime() != null){
            model.addAttribute("endDateTime", searchDTO.getEndDateTime().toString());
        }
        model.addAttribute(STATIONS_LIST,stationService.findAll());
    }
}

