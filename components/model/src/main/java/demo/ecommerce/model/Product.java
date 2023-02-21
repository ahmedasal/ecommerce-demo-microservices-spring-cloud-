package demo.ecommerce.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    public Product(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;
    private String imageUrl;
    private String quantity;

    private String title;

    private String description;
    @Column(name = "cost_price")
    private BigDecimal costPrice;

    private BigDecimal earning;

    private String brand;
    private String upc;
    private String sku;
    //sku and (npn and gtin)
    //ubc or barcode
}
