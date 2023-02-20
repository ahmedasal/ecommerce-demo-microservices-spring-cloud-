package demo.ecommerce.dto;

import demo.ecommerce.model.Product;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    //from productDTO to product as model(domain)
    public Product convertFromProductDTOToProductModel(ProductDTO productDTO) {
        Product p = new Product();
        p.setId(productDTO.getId());
        p.setDescription(productDTO.getDescription());
        p.setTitle(productDTO.getTitle());
        p.setBrand(productDTO.getBrand());
        p.setSku(productDTO.getSku());
        p.setUpc(productDTO.getUpc());
        p.setImageUrl(productDTO.getImageUrl());
        p.setQuantity(productDTO.getQuantity());
        p.setEarning(productDTO.getEarning());
        p.setCostPrice(productDTO.getCostPrice());
        return p;
    }
    public ProductDTO convertFromProductModelToProductDTO(Product p) {
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
