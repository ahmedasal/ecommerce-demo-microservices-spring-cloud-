package demo.ecommerce.model;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    private long id;
    @OneToOne
    @JoinColumn(name = "cart_order_id")
    private CartOrder cartOrder;
    @Column(name = "transactional_id")
    private long transactionalId;
    @Column(name = "card_no")
    private String CardNo;
    @Column(name = "amount")

    private BigDecimal amount;
}
