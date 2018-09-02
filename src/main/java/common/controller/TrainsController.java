package common.controller;

import common.dto.TrainDTO;
import common.model.Station;
import common.model.TrainEntity;
import common.service.StationService;
import common.service.TrainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** List, create, change, remove trains via web-interface */

@Controller
public class TrainsController {

    @Autowired
    private TrainsService trainsService;

    @Autowired
    private StationService stationService;

//    @GetMapping(value = "/")
//    public String index(Model model) {
//        return "redirect:/allTrains";
//    }

//    @GetMapping(value = "/allTrains")
//    public String showAllTrains(Model model) {
//        model.addAttribute("trains", trainsService.findAll());
//        return "fragments/allTrainsFragment";
//    }

    @GetMapping(value = "/allTrains")
    @Transactional
    public String showAllTrains(Model model) {
        List<TrainDTO> trainDTOList = new LinkedList<>();
        for (TrainEntity t: trainsService.findAll()) {
            TrainDTO trainDTO = new TrainDTO();
            trainDTO.setId(t.getId());
            trainDTO.setName(t.getName());
            trainDTO.setCapacity(t.getCapacity());
            int routes_quantity = t.route.size();
            int [] st_ids = new int[routes_quantity];
            String [] st_time = new String[routes_quantity];
            int j = 0;
            for (Map.Entry<Station, LocalTime> me : t.route.entrySet()) {
                st_ids [j] = me.getKey().getId();
                st_time [j] = me.getValue().toString();
                j++;
            }
            trainDTO.setStation_id(st_ids);
            trainDTO.setTime_on_station(st_time);
            trainDTOList.add(trainDTO);
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
