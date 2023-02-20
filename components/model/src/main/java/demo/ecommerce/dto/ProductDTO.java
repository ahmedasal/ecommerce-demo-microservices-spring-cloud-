package demo.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class ProductDTO {
    private long id;
    private String title;
    private String description;
    private BigDecimal costPrice;

    private BigDecimal earning;
    private String brand;
    private String imageUrl;
    private String quantity;
    private String upc;
    private String sku;
}
