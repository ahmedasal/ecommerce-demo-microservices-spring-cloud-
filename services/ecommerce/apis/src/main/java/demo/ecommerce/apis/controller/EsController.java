//package demo.ecommerce.apis.controller;
//
//import demo.ecommerce.apis.elasticSearchRepo.ProductsElasticSearchRepo;
//import demo.ecommerce.apis.service.EsService;
//import demo.ecommerce.dto.ProductDTO;
//import demo.ecommerce.model.Product;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/es")
//@AllArgsConstructor
//public class EsController {
//    private final EsService esService;
//    private final ProductsElasticSearchRepo productsElasticSearchRepo;
//
//    @PostMapping
//    public String save(@RequestBody Product product) {
//        esService.save(product);
//        return "product saved successfully";
//    }
//
//    @GetMapping("/{id}")
//    public Product getProductById (@PathVariable long id) {
//        return productsElasticSearchRepo.findById(id);
//    }
//
//
//
//}
