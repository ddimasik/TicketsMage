package common.controller;

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

    @Autowired
    private StationService stationService;

    @Autowired
    StationFormValidator stationFormValidator;

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
        Station station = new Station();
        station.setName("Gadjukino");
        model.addAttribute("stationForm", station);
        populateDefaultModel(model);
        return "stations/stationform";
    }

    @GetMapping(value = "/stations/{id}/update")
    public String showUpdateUserForm(@PathVariable("id") int id, Model model) {
        Station station = stationService.findById(id);
        model.addAttribute("stationForm", station);
        populateDefaultModel(model);
        return "stations/stationform";
    }

    @PostMapping(value = "/stations")
    public String saveOrUpdateUser(@ModelAttribute("stationForm") @Validated Station station,
                                   BindingResult result, Model model,
                                   final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            populateDefaultModel(model);
            return "stations/stationform";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if(station.isNew()){
                redirectAttributes.addFlashAttribute("msg", "Station added successfully!");
            }else{
                redirectAttributes.addFlashAttribute("msg", "Station updated successfully!");
            }
            stationService.saveOrUpdate(station);

            return "redirect:/stations/" + station.getId();
        }
    }

    @PostMapping(value = "/stations/{id}/delete")
    public String deleteUser(@PathVariable("id") int id,
                             final RedirectAttributes redirectAttributes) {
        stationService.removeStation(id);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Station is deleted!");
        return "redirect:/stations";

    }
    private void populateDefaultModel(Model model) {
        model.addAttribute("name", "Default city");

    }


}


























//
//import java.util.Map;
//
//import common.model.Station;
//import common.service.StationService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//@Controller
//public class StationController {
//
//    @Autowired
//    private StationService stationService;
//
//    @RequestMapping("/index")
//    public String listContacts(Map<String, Object> map) {
//
//        map.put("station", new Station());
//        map.put("stationsList", stationService.findAll());
//
//        return "station";
//    }
//
//    @RequestMapping("/")
//    public String home() {
//        return "redirect:/index";
//    }
//
//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    public String addStation(@ModelAttribute("station") Station station, BindingResult result) {
//
//        stationService.add(station);
//
//        return "redirect:/index";
//    }
//
//    @RequestMapping("/delete/{stationId}")
//    public String deleteStation(@PathVariable("stationId") Long stationId) {
//
//        stationService.removeStation(stationId);
//
//        return "redirect:/index";
//    }
//
//    @RequestMapping("/edit/{stationId}")
//    public String editStation(@ModelAttribute("station") Station station) {
//
//        //stationService.removeStation(stationId);
//
//        return "redirect:/index";
//    }
//}
