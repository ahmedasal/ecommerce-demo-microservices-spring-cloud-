package demo.ecommerce.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cart_order_id")
    private CartOrder cartOrder;
    private int quantity;
    @Column(name = "cost_price")
    private BigDecimal costPrice;
    private BigDecimal earning;
    private BigDecimal shipping;
    @Column(name = "taxes_ratio")
    private BigDecimal taxesRatio;
    @Column(name = "total_taxes")
    private BigDecimal totalTaxes;
    @Column(name = "total_price")
    private BigDecimal totalPrice; //per all quantity
    @Column(name = "subtotal_price")
    private BigDecimal SubTotal; //per all quantity

}
