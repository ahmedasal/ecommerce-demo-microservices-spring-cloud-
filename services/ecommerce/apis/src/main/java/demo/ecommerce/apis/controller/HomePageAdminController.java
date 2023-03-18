package demo.ecommerce.apis.controller;
import demo.ecommerce.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import demo.ecommerce.apis.redis.RedisRepo;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomePageAdminController {

    @Autowired
    RedisRepo repo;


    @PostMapping
    public String saveProduct(@RequestBody ProductDTO p) {
        repo.save(p);
        return "saved successfully";
    }

    @GetMapping
    public List<ProductDTO> getProducts() {
        List<ProductDTO> products = new ArrayList<>();
        repo.findAll().forEach(products::add);
        return products;
    }

    @GetMapping("/{id}")
    public String deleteProduct(@PathVariable long id) {
        repo.deleteById(id);
        return "product deleted successfully";
    }


}


//    @GetMapping
//    @Cacheable("products")
//    public List<ProductDTO> getHomePageProducts() {
//        List<Product> products = repo.findByHomePageExistence(true);
//        System.out.println("I go for database");
//        return products.stream()
//                .map(p -> mapper.convertFromProductModelToProductDTO(p))
//                .collect(Collectors.toList());
//    }
//    @GetMapping
//    @Cacheable("products")
//    public List<ProductDTO> getHomePageProducts() {
//        List<Product> products = repo.findByHomePageExistence(true);
//        System.out.println("I go for database");
//        return products.stream()
//                .map(p -> mapper.convertFromProductModelToProductDTO(p))
//                .collect(Collectors.toList());
//    }
//
//
//    @Transactional
//    @CacheEvict(value = "products" , allEntries = true)
//    @GetMapping("/{id}")
//    public ProductDTO addProductToHomePage(@PathVariable long id) {
//        Product p =repo.findById(id);
//        System.out.println("hello world");
//        if(!p.isHomePageExistence()) {
//            p.setHomePageExistence(true);
//        }
//            return mapper.convertFromProductModelToProductDTO(p);
//    }
//    @Transactional
//    @CacheEvict(value = "products" , allEntries = true)
//    @GetMapping("remove/{id}")
//    public ProductDTO removeProductFromHomepage(@PathVariable long id) {
//        Product p =repo.findById(id);
//        if(p.isHomePageExistence()) {
//            p.setHomePageExistence(false);
//        }
//        return mapper.convertFromProductModelToProductDTO(p);
//    }