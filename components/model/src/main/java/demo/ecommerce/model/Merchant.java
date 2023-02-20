package demo.ecommerce.model;

import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sellers")
public class Merchant {
    public Merchant(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String street;
    private String city;
    private String country;
    private String government;
    @Column(name = "flat_no")
    private String flatNo;
}
