package common.repository;

import common.dto.PassengerDTO;
import common.model.PassengerEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDate;

@Repository
public class PassengerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    //TODO разделить на два метода, конвертить в конвертере
    public PassengerEntity createPassengerFromDTO(PassengerDTO passengerDTO){
        PassengerEntity passengerEntity = new PassengerEntity();
        passengerEntity.setName(passengerDTO.getName());
        passengerEntity.setSurname(passengerDTO.getSurname());
        passengerEntity.setBirthday(LocalDate.parse(passengerDTO.getBirthday().toString()));
        entityManager.persist(passengerEntity);
        return passengerEntity;
    }

    public PassengerEntity findByDTO(PassengerDTO passengerDTO){
        //TODO add surname and birthday
        TypedQuery<PassengerEntity> query = entityManager.createQuery("select p from PassengerEntity p " +
                "where p.name = :name", PassengerEntity.class);
        return query.setParameter("name", passengerDTO.getName()).getSingleResult();
    }


    public void addPassenger(PassengerEntity passengerEntity){
        entityManager.persist(passengerEntity);
    }

    public PassengerEntity findById(int id){return entityManager.find(PassengerEntity.class, id);}
}
