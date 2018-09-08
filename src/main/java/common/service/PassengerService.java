package common.service;

import common.model.PassengerEntity;
import common.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

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
