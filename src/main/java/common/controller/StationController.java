package common.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import common.model.Station;
import common.service.StationService;
import common.validator.StationFormValidator;

@Controller
public class StationController {
//    private final Logger logger = LoggerFactory.getLogger(StationController.class);

    @Autowired
    private StationService stationService;

    @Autowired
    StationFormValidator stationFormValidator;

    //Set a form validator
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(stationFormValidator);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        return "redirect:/stations";
    }

    @RequestMapping(value = "/stations", method = RequestMethod.GET)
    public String showAllStations(Model model) {
 //       logger.debug("showAllStations()");
        model.addAttribute("stations", stationService.findAll());
        return "stations/list";
    }

    @RequestMapping(value = "/stations/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") int id, Model model) {
//        logger.debug("showUser() id: {}", id);
//
        Station station = stationService.findById(id);
        if (station == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Station not found");
        }
        model.addAttribute("station", station);
        return "stations/show";
    }

    @RequestMapping(value = "/stations/add", method = RequestMethod.GET)
    public String showAddUserForm(Model model) {
 //       logger.debug("showAddUserForm()");
        Station station = new Station();
        // set default value
        station.setName("Gadjukino");
        model.addAttribute("stationForm", station);
        populateDefaultModel(model);
        return "stations/stationform";
    }

    @RequestMapping(value = "/stations/{id}/update", method = RequestMethod.GET)
    public String showUpdateUserForm(@PathVariable("id") int id, Model model) {
//        logger.debug("showUpdateUserForm() : {}", id);
        Station station = stationService.findById(id);
        model.addAttribute("stationForm", station);
        populateDefaultModel(model);
        return "stations/stationform";
    }

//    // save or update user
//    // 1. @ModelAttribute bind form value
//    // 2. @Validated form validator
//    // 3. RedirectAttributes for flash value
    @RequestMapping(value = "/stations", method = RequestMethod.POST)
    public String saveOrUpdateUser(@ModelAttribute("stationForm") @Validated Station station,
                                   BindingResult result, Model model,
                                   final RedirectAttributes redirectAttributes) {
//        logger.debug("saveOrUpdateUser() : {}", station);
//        logger.debug("result : {}", result.getAllErrors());
//        logger.debug("model : {}",model);
        if (result.hasErrors()) {
//            logger.debug("was error!");
            populateDefaultModel(model);
            return "stations/stationform";
        } else {

             //Add message to flash scope
            redirectAttributes.addFlashAttribute("css", "success");
            if(station.isNew()){
                redirectAttributes.addFlashAttribute("msg", "Station added successfully!");
            }else{
                redirectAttributes.addFlashAttribute("msg", "Station updated successfully!");
            }

            stationService.saveOrUpdate(station);

            // POST/REDIRECT/GET
            return "redirect:/stations/" + station.getId();
            // POST/FORWARD/GET
            // return "user/list";
        }
    }
//
    // delete user
    @RequestMapping(value = "/stations/{id}/delete", method = RequestMethod.POST)
    public String deleteUser(@PathVariable("id") int id,
                             final RedirectAttributes redirectAttributes) {
//        logger.debug("deleteUser() : {}", id);
        stationService.removeStation(id);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Station is deleted!");
        return "redirect:/stations";

    }
    private void populateDefaultModel(Model model) {
     //   List<String> stationNames = new ArrayList<String>();
     //   stationNames.add("Default city");
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
