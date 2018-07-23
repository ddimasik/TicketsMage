package common.service;

import common.model.Station;
import common.repository.StationRepository;
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

    @Autowired
    private StationRepository stationRepository;

    @Transactional
    public void add(Station station) {
        stationRepository.save(station);
    }

    @Transactional
    public void removeStation(Long id) {
        stationRepository.delete(id);
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
