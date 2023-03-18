package demo.ecommerce.apis.service;

import demo.ecommerce.dto.ProductDTO;
import demo.ecommerce.model.Product;
import demo.ecommerce.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {

    @Autowired
    ProductRepo productRepo;

    public Product createProduct(Product product) {
       return productRepo.save(product);
    }

    public void updateProduct(ProductDTO dto) {
        Product entity = productRepo.findById(dto.getId()).get();
        entity.setBrand(dto.getBrand());
        entity.setDescription(dto.getDescription());
        entity.setTitle(dto.getTitle());
        entity.setBrand(dto.getBrand());
        entity.setSku(dto.getSku());
        entity.setUpc(dto.getUpc());
        productRepo.save(entity);
    }
    public void deleteProduct(Long id) {
        Product entity = productRepo.findById(id).get();
        productRepo.delete(entity);
    }

}
