package common.repository;

import common.model.Station;
import common.model.Train;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * This is the Data Access layer. Simple huh?
 * PLease note that no need for any dao implementation. This is an interface!
 */
@Repository
//public interface StationsRepository extends JpaRepository<Station, Long> {
public class StationsRepository {

//    public List<Station> findByNameContainingIgnoreCase(String searchString);
//    @Query("select p from Station p where p.name = :name")
//    public List<Station> findByNameIs(@Param("name") String name);
//    @Query("select p from Station p where p.id = :id")
//    public Station findById(@Param("id") Integer id);

//    @Autowired
//    private SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;

//    private Session getSession() {
//        return sessionFactory.getCurrentSession();
//    }


}
