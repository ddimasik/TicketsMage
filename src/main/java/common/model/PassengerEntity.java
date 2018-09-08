package common.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "passengers")
@Setter
@Getter
public class PassengerEntity {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private LocalDate birthday;
}
