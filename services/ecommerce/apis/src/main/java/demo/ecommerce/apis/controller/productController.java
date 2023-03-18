package demo.ecommerce.apis.controller;

import demo.ecommerce.dto.ProductDTO;
import demo.ecommerce.model.Product;
import demo.ecommerce.model.Merchant;
import demo.ecommerce.repository.ProductRepo;
import demo.ecommerce.apis.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class productController {
    @Autowired
    MerchantService productService;
    @Autowired
    ProductRepo productRepo;
    @PostMapping("/add")
    public void addProduct(@RequestBody ProductDTO productDTO) {
        Product p = productDTO.toEntity();
        p.setMerchant(new Merchant(1));
        productService.createProduct(p);
    }

    @PutMapping("/update")
    public void updateProduct(@RequestBody ProductDTO productDTO){
        productService.updateProduct(productDTO);
    }

    @GetMapping("/pk/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
        Optional<Product> productOpt = productRepo.findById(id);
        if(productOpt.isPresent()) {
            return ResponseEntity.ok(ProductDTO.fromEntity(productOpt.get()));
        }
        return ResponseEntity.notFound().build();
    }


}
