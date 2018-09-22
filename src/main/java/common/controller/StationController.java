package common.controller;

import common.dto.StationDTO;
import common.service.TrainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import common.model.Station;
import common.service.StationService;
import common.validator.StationFormValidator;

@Controller
public class StationController {

    private static final String STATIONS_STATIONFORM = "stations/stationform";
    private static final String STATION_FORM = "stationForm";

    @Autowired
    private StationService stationService;

    @Autowired
    StationFormValidator stationFormValidator;

    @Autowired
    private TrainsService trainsService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(stationFormValidator);
    }

    @GetMapping(value = "/")
    public String index(Model model) {
        return "redirect:/stations";
    }

    @GetMapping(value = "/stations")
    public String showAllStations(Model model) {
        model.addAttribute("stations", stationService.findAll());
        return "stations/list";
    }

    @GetMapping(value = "/stations/{id}")
    public String showUser(@PathVariable("id") int id, Model model) {

        Station station = stationService.findById(id);
        if (station == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Station not found");
        }
        model.addAttribute("station", station);
        return "stations/show";
    }

    @GetMapping(value = "/stations/add")
    public String showAddUserForm(Model model) {
        model.addAttribute(STATION_FORM, new StationDTO());
        return STATIONS_STATIONFORM;
    }

    @GetMapping(value = "/stations/{id}/schedule")
    public String showStationShedule(@PathVariable("id") int id, Model model) {
        Station station = stationService.findById(id);
        model.addAttribute("scheduleSwitcher", station.getName());
        model.addAttribute("trains", trainsService.findTrainsPassingStationIdAndReturnTrainDTOs(id));

        return "fragments/allTrainsFragment";
    }

    @PostMapping(value = "/stations")
    public String saveOrUpdateUser(@ModelAttribute(STATION_FORM) @Validated StationDTO stationDTO,
                                   BindingResult result, Model model,
                                   final RedirectAttributes redirectAttributes) {


        if (result.hasErrors()) {

            model.addAttribute(STATION_FORM, stationDTO);
            return STATIONS_STATIONFORM;
        } else {
            stationService.saveOrUpdate(stationDTO);
            return "redirect:/stations/";
        }
    }

    @PostMapping(value = "/stations/{id}/delete")
    public String deleteUser(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
        stationService.removeStation(id);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Station is deleted!");
        return "redirect:/stations";
    }
}
