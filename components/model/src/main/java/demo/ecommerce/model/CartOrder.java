package demo.ecommerce.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carts_orders")
public class CartOrder {
    public CartOrder(long id) {
        this.id = id;
    }

    public static enum OrderStatus {REFUND, CONFIRMED, SHIPPED, BENDING}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany(mappedBy = "cartOrder")
    List<CartItem> items;
    private BigDecimal totalPrice;
    private Date date;
    private Date createDate;
    private OrderStatus status; //enum
}
