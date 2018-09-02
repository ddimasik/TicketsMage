package common.repository;

import common.model.TrainEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class TrainsDAOImpl implements TrainsDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TrainEntity> getAllTrains() {
        return entityManager.createQuery("Select t FROM TrainEntity t", TrainEntity.class).getResultList();
    }

    @Override
    public void addTrain(TrainEntity trainEntity) {
        entityManager.persist(trainEntity);
    }

    @Override
    public TrainEntity findById(Integer id){
        return entityManager.find(TrainEntity.class, id);
    }

    @Override
    public void delete(TrainEntity trainEntity){
        entityManager.remove(trainEntity);
    }
}
