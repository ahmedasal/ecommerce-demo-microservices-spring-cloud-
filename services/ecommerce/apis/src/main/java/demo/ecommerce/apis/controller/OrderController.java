package demo.ecommerce.apis.controller;

import demo.ecommerce.dto.CartItemDTO;
import demo.ecommerce.apis.service.OrderService;
import demo.ecommerce.repository.CartOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping("/api")
public class OrderController {
    @PersistenceContext
    EntityManager em;
    @Autowired
    CartOrderRepo cartOrderRepo;
    @Autowired
    OrderService orderService;


    @GetMapping("/test")
    public void test() {
        System.out.println("hello after alot of work");

    }
    @PostMapping("/order")
//    @RolesAllowed("customer")
    public ResponseEntity<String> createOrder(@RequestBody List<CartItemDTO> cartItems) {
        return ResponseEntity.ok(orderService.createOrder(cartItems));
    }


    @GetMapping("/delete/{id}")
    @Transactional
    public ResponseEntity deleteOrder(@PathVariable long id) {
        cartOrderRepo.deleteCartOrder(id);
        return ResponseEntity.ok("hello");
    }
}
