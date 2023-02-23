package demo.ecommerce.model;

import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "merchants")
public class Merchant {
    public Merchant(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "keycloak_id")
    private String keycloakId;
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
