package common.repository;

import common.model.PassengerEntity;
import common.model.TicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;

@Repository
public class TicketsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PassengerRepository passengerRepository;

    public boolean checkIfPassengerExistsOnTrain(PassengerEntity passengerEntity, int ticketId){
        TicketEntity ticketEntityForCheck = findById(ticketId);
        TypedQuery<TicketEntity> query = entityManager.createQuery("select t from TicketEntity t where " +
                "t.passengerId = :passengerId and " +
                "t.trainId = :trainId", TicketEntity.class);
        List<TicketEntity> ticketEntityList = query.setParameter("passengerId", passengerEntity.getId())
                                         .setParameter("trainId", ticketEntityForCheck.getTrainId()).getResultList();
        return (!ticketEntityList.isEmpty());
    }

    public List<PassengerEntity> findAllPassengersInTrain(int id){

        List<PassengerEntity> passengerEntityList = new LinkedList<>();

        Query query = entityManager.createQuery("select t from TicketEntity t where t.trainId = :id", TicketEntity.class);
        List<TicketEntity> ticketEntityList = query.setParameter("id", id).getResultList();
        for (TicketEntity ticketEntity : ticketEntityList) {
            passengerEntityList.add(passengerRepository.findById(ticketEntity.getPassengerId()));
        }
        return passengerEntityList;
    }



    public void addTicket(TicketEntity ticketEntity){entityManager.persist(ticketEntity);}

    public void updateTicket(TicketEntity ticketEntity){entityManager.merge(ticketEntity);}

    public TicketEntity findById(int id){return entityManager.find(TicketEntity.class, id);}
}
