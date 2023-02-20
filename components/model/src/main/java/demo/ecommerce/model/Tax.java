package demo.ecommerce.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "taxes")
public class Tax {
    @Id
    private long id;
    @JoinColumn(name = "dsecription")
    private String description;
    @JoinColumn(name = "tax_amount")
    private BigDecimal taxAmount;


}
