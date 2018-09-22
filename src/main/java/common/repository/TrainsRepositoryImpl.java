package common.repository;

import common.model.TrainEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class TrainsRepositoryImpl implements TrainsRepository {

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
    public boolean findByName(String name){
        return !entityManager.createQuery("Select t FROM TrainEntity t where t.name = :name", TrainEntity.class)
                .setParameter("name",name).getResultList().isEmpty();
    }

    @Override
    public void delete(TrainEntity trainEntity){
        entityManager.remove(trainEntity);
    }

}
