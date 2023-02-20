package demo.ecommerce.controller;

import demo.ecommerce.service.CartItemService;
import demo.ecommerce.service.TaxService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

@RestController
@RequestMapping
@AllArgsConstructor
public class Test {
    private final CartItemService cartItemService;
    private final TaxService taxService;
    @PersistenceContext
    EntityManager em;
    @GetMapping("/test")
    public BigDecimal msg(){
       return cartItemService.getCostPrice(12, em);
    }
    @GetMapping("/test1")
    public BigDecimal msg1(){
       return taxService.getTaxesRatio();
    }
}
