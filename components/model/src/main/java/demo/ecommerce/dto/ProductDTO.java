package demo.ecommerce.dto;

import demo.ecommerce.model.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;


import java.io.Serializable;
import java.math.BigDecimal;

@RedisHash("home-page-products")
@Getter
@Setter
public class ProductDTO implements Serializable {
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

    public Product toEntity() {
        return toEntity(null);
    }
    public Product toEntity(Product p) {
        if(p == null)
           p = new Product();
        p.setId(this.getId());
        p.setDescription(this.getDescription());
        p.setTitle(this.getTitle());
        p.setBrand(this.getBrand());
        p.setSku(this.getSku());
        p.setUpc(this.getUpc());
        p.setImageUrl(this.getImageUrl());
        p.setQuantity(this.getQuantity());
        p.setEarning(this.getEarning());
        p.setCostPrice(this.getCostPrice());
        return p;
    }
    public static ProductDTO fromEntity(Product p) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(p.getId());
        productDTO.setDescription(p.getDescription());
        productDTO.setTitle(p.getTitle());
        productDTO.setBrand(p.getBrand());
        productDTO.setSku(p.getSku());
        productDTO.setUpc(p.getUpc());
        productDTO.setImageUrl(p.getImageUrl());
        productDTO.setQuantity(p.getQuantity());
        productDTO.setEarning(p.getEarning());
        productDTO.setCostPrice(p.getCostPrice());
        return productDTO;
    }

}
