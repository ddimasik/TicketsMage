package common.repository;

import common.model.PassengerEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PassengerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void addPassenger(PassengerEntity passengerEntity){
        entityManager.persist(passengerEntity);
    }

    public PassengerEntity findById(int id){return entityManager.find(PassengerEntity.class, id);}
}
