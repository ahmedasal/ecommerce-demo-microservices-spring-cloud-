package demo.ecommerce.apis.controller;

import demo.ecommerce.dto.CartItemDTO;
import demo.ecommerce.model.CartOrder;
import demo.ecommerce.apis.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping("/order")
    public ResponseEntity<String> createOrder(@RequestBody List<CartItemDTO> cartItems) {
      return ResponseEntity.ok(orderService.createOrder(cartItems));
    }
}
