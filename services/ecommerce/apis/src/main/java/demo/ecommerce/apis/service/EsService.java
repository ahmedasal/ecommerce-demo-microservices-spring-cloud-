package demo.ecommerce.apis.service;

import demo.ecommerce.apis.elasticSearchRepo.ProductsElasticSearchRepo;
import demo.ecommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EsService {
    @Autowired
    ProductsElasticSearchRepo productsElasticSearchRepo;

    public void save(Product product) {
        productsElasticSearchRepo.save(product);
    }
}
