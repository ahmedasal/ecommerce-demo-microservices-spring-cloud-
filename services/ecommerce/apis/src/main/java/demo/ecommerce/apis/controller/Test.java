package demo.ecommerce.apis.controller;

import demo.ecommerce.apis.service.TaxService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping
@AllArgsConstructor
public class Test {

    private final TaxService taxService;


    @GetMapping("/test1")
    public BigDecimal msg1(){
       return taxService.getTaxesRatio();
    }
}
