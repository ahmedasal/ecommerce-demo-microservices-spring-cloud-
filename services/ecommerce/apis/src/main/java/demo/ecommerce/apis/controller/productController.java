package demo.ecommerce.apis.controller;

import demo.ecommerce.dto.Mapper;
import demo.ecommerce.dto.ProductDTO;
import demo.ecommerce.model.Product;
import demo.ecommerce.model.Merchant;
import demo.ecommerce.repository.ProductRepo;

import demo.ecommerce.apis.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class productController {
    @Autowired
    MerchantService productService;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    Mapper mapper;
    @PostMapping("/addproduct")
    public void addProduct(@RequestBody ProductDTO productDTO) {
        Product p = mapper.convertFromProductDTOToProductModel(productDTO);
        p.setMerchant(new Merchant(1));
        productService.createProduct(p);
    }

    @PatchMapping("/updateproduct")
    public void updateProduct(@RequestBody ProductDTO productDTO){
        Product p = mapper.convertFromProductDTOToProductModel(productDTO);
        p.setMerchant(new Merchant(1));
        productService.updateProduct(p);
    }

}
