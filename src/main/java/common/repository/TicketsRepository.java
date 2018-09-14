package common.repository;

import common.model.PassengerEntity;
import common.model.TicketEntity;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class TicketsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public boolean checkIfPassengerExistsOnTrain(PassengerEntity passengerEntity, int ticketId){
        TicketEntity ticketEntityForCheck = findById(ticketId);
        TypedQuery<TicketEntity> query = entityManager.createQuery("select t from TicketEntity t where " +
                "t.passengerId = :passengerId and " +
                "t.trainId = :trainId", TicketEntity.class);
        List<TicketEntity> ticketEntityList = query.setParameter("passengerId", passengerEntity.getId())
                                         .setParameter("trainId", ticketEntityForCheck.getTrainId()).getResultList();
        return (!ticketEntityList.isEmpty());
    }

    public void addTicket(TicketEntity ticketEntity){entityManager.persist(ticketEntity);}

    public void updateTicket(TicketEntity ticketEntity){entityManager.merge(ticketEntity);}

    public TicketEntity findById(int id){return entityManager.find(TicketEntity.class, id);}
}
