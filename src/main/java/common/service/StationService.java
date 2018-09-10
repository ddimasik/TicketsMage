package common.service;

import common.model.Station;
import common.repository.StationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class StationService {

//    private final Logger logger = LoggerFactory.getLogger(StationService.class);
//
    @Autowired
    private StationsRepository stationsRepository;

    @Transactional(readOnly=true)
    public Station findById(Integer id) {
        return stationsRepository.findById(id);
    }

    @Transactional
    public void saveOrUpdate(Station station) {
        if (findById(station.getId())==null) {
            stationsRepository.save(station);
        } else {
            stationsRepository.update(station);
        }
    }

    @Transactional
    public void add(Station station) {stationsRepository.save(station);}

    @Transactional
    public void removeStation(int id) {
        stationsRepository.delete(findById(id));
    }

    @Transactional(readOnly=true)
    public List<Station> findAll() {
        return stationsRepository.findAll();
    }

    @Transactional
    public void addAll(Collection<Station> stations) {
        for (Station station : stations) {
            stationsRepository.save(station);
        }
    }

//    @Transactional(readOnly=true)
//    public List<Station> findByNameIs(String name) {
//        return stationsRepository.findByNameIs(name);
//    }
//
//    @Transactional(readOnly=true)
//    public List<Station> findByNameContainingIgnoreCase(String searchString) {
//        return stationsRepository.findByNameContainingIgnoreCase(searchString);
//    }
}
