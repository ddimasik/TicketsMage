package common.service;

import common.dto.PassengerDTO;
import common.model.PassengerEntity;
import common.repository.PassengerRepository;
import common.repository.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private TicketsRepository ticketsRepository;

    public List<PassengerDTO> findAllByTrain(int id){

        List<PassengerDTO> passengerDTOList = new LinkedList<>();
        if (!ticketsRepository.findAllPassengersInTrain(id).isEmpty()){
            for (PassengerEntity passengerEntity : ticketsRepository.findAllPassengersInTrain(id)){
                PassengerDTO passengerDTO = new PassengerDTO();
                passengerDTO.setName(passengerEntity.getName());
                passengerDTO.setSurname(passengerEntity.getSurname());
                passengerDTO.setBirthday(passengerEntity.getBirthday());
                passengerDTOList.add(passengerDTO);
            }
        }
        return passengerDTOList;

    }

    public void createPassenger(String name, String surname, LocalDate birthday){
        PassengerEntity passengerEntity = new PassengerEntity();
        passengerEntity.setName(name);
        passengerEntity.setSurname(surname);
        passengerEntity.setBirthday(birthday);
        passengerRepository.addPassenger(passengerEntity);
    }

    public PassengerEntity findById(int id){
        return passengerRepository.findById(id);
    }
}
