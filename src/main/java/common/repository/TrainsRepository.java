package common.repository;

import common.model.Train;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class TrainsRepository implements TrainsDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Train> getAllTrains() {
        List<Train> trains = entityManager.createQuery("Select t FROM Train t", Train.class).getResultList();
        return trains;
    }

    @Override
    public void addTrain(Train train) {

    }
}
