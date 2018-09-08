package common.repository;

import common.model.RouteEntity;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RouteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void addRoute(RouteEntity routeEntity){
        entityManager.persist(routeEntity);
        }

    public List<RouteEntity> findRouteOfTrain(int trainEntityId){
        Query q = entityManager.createQuery("Select r FROM RouteEntity r where r.trainEntityId = :trainEntityId", RouteEntity.class);
        return q.setParameter("trainEntityId",trainEntityId).getResultList();
    }

    public List<RouteEntity> findAll(){
        return entityManager.createQuery("Select r FROM RouteEntity r", RouteEntity.class).getResultList();
    }
}
