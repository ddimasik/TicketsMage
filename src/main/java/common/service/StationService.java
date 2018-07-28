package common.service;

import common.model.Station;
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
public class StationService {

    private final Logger logger = LoggerFactory.getLogger(StationService.class);

    @Autowired
    private StationRepository stationRepository;

    public Station findById(Integer id) {
        return stationRepository.findById(id);
    }

    public void saveOrUpdate(Station station) {

        if (findById(station.getId())==null) {
            stationRepository.save(station);
        } else {
            update(station);
        }

    }

    void update(Station station){
        Station stToSave = new Station();

        stToSave.name = station.getName();
        logger.debug(stToSave.name);
        stToSave.id = station.getId();
        logger.debug(stToSave.id.toString());

        stationRepository.save(stToSave);



    }

    @Transactional
    public void add(Station station) {
        stationRepository.save(station);
    }

    @Transactional
    public void removeStation(int id) {
        stationRepository.delete(findById(id));
    }


    @Transactional(readOnly=true)
    public List<Station> findAll() {
        return stationRepository.findAll();
    }

    @Transactional
    public void addAll(Collection<Station> stations) {
        for (Station station : stations) {
            stationRepository.save(station);
        }
    }

    @Transactional(readOnly=true)
    public List<Station> findByNameIs(String name) {
        return stationRepository.findByNameIs(name);
    }

    @Transactional(readOnly=true)
    public List<Station> findByNameContainingIgnoreCase(String searchString) {
        return stationRepository.findByNameContainingIgnoreCase(searchString);
    }
}
