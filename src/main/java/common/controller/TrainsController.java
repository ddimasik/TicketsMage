package common.controller;

import common.dto.TrainDTO;
import common.model.RouteEntity;
import common.model.TrainEntity;
import common.service.RouteService;
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
    private RouteService routeService;

    @GetMapping(value = "/allTrains")
    @Transactional
    public String showAllTrains(Model model) {
        List<TrainDTO> trainDTOList = new LinkedList<>();
        for (TrainEntity traintEntity : trainsService.findAll()) {
            TrainDTO trainDTO = new TrainDTO();
            trainDTO.setId(traintEntity.getId());
            trainDTO.setName(traintEntity.getName());
            trainDTO.setCapacity(traintEntity.getCapacity());
            int routesQuantity = routeService.findRouteOfTrain(traintEntity).size();
            int [] stIds = new int[routesQuantity];
            String [] stTime = new String[routesQuantity];
            int j = 0;
            for (RouteEntity re : routeService.findRouteOfTrain(traintEntity)) {
                stIds[j] = re.getStationId();
                stTime[j] = re.getTime().toString();
                j++;
            }

            trainDTO.setStationId(stIds);
            trainDTO.setTimeOnStation(stTime);
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
