package demo.ecommerce.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    public Customer(long id) {
        this.id = id;
    }

    @Id
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
