package common.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *  This is a data structure, so
 *  fields can be public. (Clean-Code)
 */
@Entity
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    public Long id;
    public String name;

    public Station() {
        //Default constructor needed for JPA.
    }
    public Station(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Station [id=" + id + ", name=" + name + "]";
    }

}
