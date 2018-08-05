package common.service;

import common.model.Route;
import common.model.Station;
import common.repository.RouteRepository;
import common.repository.StationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * Service layer.
 * Specify transactional behavior and mainly
 * delegate calls to Repository.
 */
@Component
public class RouteService {

    private final Logger logger = LoggerFactory.getLogger(RouteService.class);

    @Autowired
    private RouteRepository routeRepository;

    public Route findById(Integer id) {return routeRepository.findById(id);
    }

    public List<Station> addStation(List stationsList, Station station){
        stationsList.add(station);
        return stationsList;
    }

    public List<Station> removeStation(List stationsList, Station station){
        stationsList.remove(station);
        return stationsList;
    }


    public void saveOrUpdate(Route route) {

        if (findById(route.getId())==null) {
            routeRepository.save(route);
        } else {
            //update(route);

        }

    }
//
//    void update(Route route){
//        Station stToSave = new Station();
//
//        stToSave.name = station.getName();
//        logger.debug(stToSave.name);
//        stToSave.id = station.getId();
//        logger.debug(stToSave.id.toString());
//
//        stationRepository.save(stToSave);
//
//
//
//    }
//
//    @Transactional
//    public void add(Station station) {
//        stationRepository.save(station);
//    }
//
//    @Transactional
//    public void removeStation(int id) {
//        stationRepository.delete(findById(id));
//    }
//
//
//    @Transactional(readOnly=true)
//    public List<Station> findAll() {
//        return stationRepository.findAll();
//    }
//
//    @Transactional
//    public void addAll(Collection<Station> stations) {
//        for (Station station : stations) {
//            stationRepository.save(station);
//        }
//    }
//
//    @Transactional(readOnly=true)
//    public List<Station> findByNameIs(String name) {
//        return stationRepository.findByNameIs(name);
//    }
//
//    @Transactional(readOnly=true)
//    public List<Station> findByNameContainingIgnoreCase(String searchString) {
//        return stationRepository.findByNameContainingIgnoreCase(searchString);
//    }
}
