package demo.ecommerce.apis.service;



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


    public Product getProductById(long Id) {
        return productRepo.findById(Id);
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
