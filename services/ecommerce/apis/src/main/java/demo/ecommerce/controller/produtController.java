package demo.ecommerce.controller;

import demo.ecommerce.dto.Mapper;
import demo.ecommerce.dto.ProductDTO;
import demo.ecommerce.model.Product;
import demo.ecommerce.model.Merchant;
import demo.ecommerce.repository.ProductRepo;

import demo.ecommerce.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/product")
public class produtController {
    @PersistenceContext
    EntityManager em;
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
