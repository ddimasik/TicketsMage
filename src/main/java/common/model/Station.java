package common.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "station")
public class Station {

    @Id
    @GeneratedValue
    @Column(name = "id")
    @Getter
    @Setter
    Integer id;

    @Column(name = "name")
    @Setter
    @Getter
    String name;

    //Check if this is for New of Update
    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public String toString() {
        return "Station [id=" + id + ", name=" + name + "]";
    }

}
