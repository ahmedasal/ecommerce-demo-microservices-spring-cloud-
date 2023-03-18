package demo.ecommerce.model;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
@Document(indexName = "product")
public class Product implements Serializable {
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
    @Field(type = FieldType.Text)
    private String title;

    private String description;
    @Column(name = "cost_price")
    private BigDecimal costPrice;

    private BigDecimal earning;

    private String brand;
    private String upc;
    private String sku;
    @Column(name = "home_page_existence")
    private boolean homePageExistence;
    //sku and (npn and gtin)
    //ubc or barcode

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", merchant=" + merchant +
                ", imageUrl='" + imageUrl + '\'' +
                ", quantity='" + quantity + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", costPrice=" + costPrice +
                ", earning=" + earning +
                ", brand='" + brand + '\'' +
                ", upc='" + upc + '\'' +
                ", sku='" + sku + '\'' +
                '}';
    }
}
