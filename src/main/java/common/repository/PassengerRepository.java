package common.repository;

import common.converters.dto_to_entity.PassengerDtoToEntityConverter;
import common.dto.PassengerDTO;
import common.model.PassengerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

//TODO повесить на репозтиториях @Transactional(propagation = Propagation.MANDATORY
@Repository
public class PassengerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PassengerDtoToEntityConverter passengerDtoToEntityConverter;

    public List<PassengerEntity> findPassengerByDTO(PassengerDTO passengerDTO){
        TypedQuery<PassengerEntity> query = entityManager.createQuery("select p from PassengerEntity p where " +
                 "p.name = :name and " +
                 "p.surname = :surname and " +
                 "p.birthday = :birthday" , PassengerEntity.class);
        return query.setParameter("name", passengerDTO.getName())
                                                         .setParameter("surname", passengerDTO.getSurname())
                                                         .setParameter("birthday", LocalDate.parse(passengerDTO.getBirthday().toString())).getResultList();
    }

    //TODO лучше назвать savePassengerFromDTO, чтобы было понятно, что ты аффектишь базу
    public PassengerEntity createPassengerFromDTO(PassengerDTO passengerDTO){
        PassengerEntity passengerEntity = passengerDtoToEntityConverter.convert(passengerDTO);
        entityManager.persist(passengerEntity);
        return passengerEntity;
    }

    public void addPassenger(PassengerEntity passengerEntity){
        entityManager.persist(passengerEntity);
    }

    public PassengerEntity findById(int id){return entityManager.find(PassengerEntity.class, id);}
}
