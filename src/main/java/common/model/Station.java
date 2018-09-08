package common.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "station")
public class Station {

    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @Column
    private String name;

    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public String toString() {
        return "Station [id=" + id + ", name=" + name + "]";
    }

}
