package common.repository;

import common.dto.PassengerDTO;
import common.model.PassengerEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

@Repository
public class PassengerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public PassengerEntity createPassengerFromDTO(PassengerDTO passengerDTO){
        PassengerEntity passengerEntity = new PassengerEntity();
        passengerEntity.setName(passengerDTO.getName());
        passengerEntity.setSurname(passengerDTO.getSurname());
        passengerEntity.setBirthday(LocalDate.parse(passengerDTO.getBirthday().toString()));
        entityManager.persist(passengerEntity);
        return passengerEntity;
    }

    public void addPassenger(PassengerEntity passengerEntity){
        entityManager.persist(passengerEntity);
    }

    public PassengerEntity findById(int id){return entityManager.find(PassengerEntity.class, id);}
}
