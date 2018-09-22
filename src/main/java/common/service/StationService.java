package common.service;

import common.dto.StationDTO;
import common.model.Station;
import common.repository.StationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class StationService {

    @Autowired
    private StationsRepository stationsRepository;

    public Station findById(Integer id) {
        return stationsRepository.findById(id);
    }

    @Transactional
    public void saveOrUpdate(StationDTO stationDTO) {
        Station station = new Station();
        station.setName(stationDTO.getName());
        stationsRepository.save(station);
    }

    @Transactional
    public void add(Station station) {stationsRepository.save(station);}

    @Transactional
    public void removeStation(int id) {
        stationsRepository.delete(findById(id));
    }

    public List<Station> findAll() {
        return stationsRepository.findAll();
    }

    @Transactional
    public void addAll(Collection<Station> stations) {
        for (Station station : stations) {
            stationsRepository.save(station);
        }
    }
}
