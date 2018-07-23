package common.controller;

import java.util.Map;

import common.model.Station;
import common.service.StationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StationController {

    @Autowired
    private StationService stationService;

    @RequestMapping("/index")
    public String listContacts(Map<String, Object> map) {

        map.put("station", new Station());
        map.put("stationsList", stationService.findAll());

        return "station";
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStation(@ModelAttribute("station") Station station, BindingResult result) {

        stationService.add(station);

        return "redirect:/index";
    }

    @RequestMapping("/delete/{stationId}")
    public String deleteStation(@PathVariable("stationId") Long stationId) {

        stationService.removeStation(stationId);

        return "redirect:/index";
    }

    @RequestMapping("/edit/{stationId}")
    public String editStation(@ModelAttribute("station") Station station) {

        //stationService.removeStation(stationId);

        return "redirect:/index";
    }
}
