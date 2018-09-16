package common.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trains")
@Setter
@Getter
public class TrainEntity {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private int capacity;

    @Column
    private String name;

    @Column
    private int startSt;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDateTime;

    @Override
    public String toString() {
        return "TrainEntity{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", name='" + name + '\'' +
                ", startSt=" + startSt +
                ", startDateTime=" + startDateTime +
                '}';
    }
}
