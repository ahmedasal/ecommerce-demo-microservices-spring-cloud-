package demo.ecommerce.controller;

import demo.ecommerce.dto.CartItemDTO;
import demo.ecommerce.model.CartOrder;
import demo.ecommerce.service.CartOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
@AllArgsConstructor
public class OrderController {
    private final CartOrderService cartOrderService;
    @GetMapping("/order")
    public ResponseEntity<CartOrder> createOrder(@RequestBody List<CartItemDTO> cartItems) {
       return ResponseEntity.ok(cartOrderService.createOrder(cartItems));
    }
}
