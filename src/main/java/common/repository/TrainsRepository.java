package common.repository;

import common.model.Train;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class TrainsRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addTrain(Train train) {
        getSession().save(train);
        //logger.info("Person saved successfully, Person Details="+p);
    }

    @SuppressWarnings("unchecked")
    public List<Train> listTrains() {
        return getSession().createQuery("from Trains").list();
    }
}
