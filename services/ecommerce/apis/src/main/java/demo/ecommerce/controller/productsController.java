package demo.ecommerce.controller;

import demo.ecommerce.dto.ProductDTO;
import demo.ecommerce.service.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class productsController {
    private final ProductsService productsService;
    @GetMapping("/allproducts")
    public ResponseEntity<List<ProductDTO> > getAllProducts() {
        return ResponseEntity.ok(productsService.getAllProducts());
    }
}
