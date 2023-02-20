package demo.ecommerce.service;



import demo.ecommerce.model.Product;
import demo.ecommerce.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public Product createProduct(Product product) {

        Product p =  productRepo.save(product);
        return p;
    }


    public Product getProductById(Long Id) {
        Product product =productRepo.getReferenceById(Id);
        return product != null ? product : null;
    }

    public void updateProduct(Product product) {
        Product productFromDB = getProductById(product.getId());
        productFromDB.setBrand(product.getBrand());
        productFromDB.setDescription(product.getDescription());
        productFromDB.setTitle(product.getTitle());
        productFromDB.setBrand(product.getBrand());
        productFromDB.setSku(product.getSku());
        productFromDB.setUpc(product.getUpc());
        productRepo.save(productFromDB);
    }
    public void deleteProduct(Product product) {
        Product productFromDB = getProductById(product.getId());
        productRepo.delete(productFromDB);
    }







}
