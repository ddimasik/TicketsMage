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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public String name;

    public Station() {
        //Default constructor needed for JPA.
    }
    public Station(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public Integer getId() {
        return id;
    }

    //Check if this is for New of Update
    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public String toString() {
        return "Station [id=" + id + ", name=" + name + "]";
    }

}
