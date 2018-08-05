package common.repository;

import common.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * This is the Data Access layer. Simple huh?
 * PLease note that no need for any dao implementation. This is an interface!
 */
@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    /** No need to define findAll() here, because
     *  inherited from JpaRepository with many other basic JPA operations.**/
    //public List<Station> findAll();

    /** spring-jpa-data understands this method name,
     *  because it supports the resolution of specific keywords inside method names. **/
//    public List<Route> findByNameContainingIgnoreCase(String searchString);

    /** You can define a JPA query.**/
//    @Query("select p from Station p where p.name = :name")
//    public List<Route> findByNameIs(@Param("name") String name);

    @Query("select p from Route p where p.id = :id")
    public Route findById(@Param("id") Integer id);

}
