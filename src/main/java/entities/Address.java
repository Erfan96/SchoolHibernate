package entities;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number", nullable = false, length = 7)
    private Integer number;

    @Column(name = "state", nullable = false, length = 20)
    private String state;

}
