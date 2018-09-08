package common.repository;

import common.model.TicketEntity;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TicketsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void addTicket(TicketEntity ticketEntity){
        entityManager.persist(ticketEntity);
    }

    public TicketEntity findById(int id){return entityManager.find(TicketEntity.class, id);}

}
