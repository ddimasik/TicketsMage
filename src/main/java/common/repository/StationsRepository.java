package common.repository;

import common.model.Station;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
@Repository
public class StationsRepository {

//    public List<Station> findByNameContainingIgnoreCase(String searchString);
//    @Query("select p from Station p where p.name = :name")
//    public List<Station> findByNameIs(@Param("name") String name);
//    @Query("select p from Station p where p.id = :id")
//    public Station findById(@Param("id") Integer id);

    @PersistenceContext
    private EntityManager entityManager;

    public List<Station> findAll() {
        return entityManager.createQuery("Select t FROM Station t", Station.class).getResultList();
    }

    public Station findByNameIs(String name){
        TypedQuery<Station> query = entityManager.createQuery("select s from Station s where s.name = :name", Station.class);
        return  query.setParameter("name", name).getSingleResult();
    }

    public Station findById(Integer id){
        if (id == null){
            return null;
        } else {
            return entityManager.find(Station.class, id);
        }
    }

    public void save(Station station){entityManager.persist(station);}

    public void update(Station station) {entityManager.merge(station);}

    public void delete(Station station){
        entityManager.remove(station);
    }

}
