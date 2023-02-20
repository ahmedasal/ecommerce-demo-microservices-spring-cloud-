package demo.ecommerce.service;

import demo.ecommerce.dto.CartItemDTO;
import demo.ecommerce.model.CartItem;
import demo.ecommerce.model.CartOrder;
import demo.ecommerce.model.Customer;
import demo.ecommerce.repository.CartOrderRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CartOrderService {
    private final CartItemService cartItemService;
    private final CartOrderRepo cartOrderRepo;

    public CartOrder createOrder(List<CartItemDTO> cartItems) {
        CartOrder cartOrder = new CartOrder();
        BigDecimal totalPrice = new BigDecimal(0);
        List<CartItem> cartItemsList = new ArrayList<>();
        cartOrder.setCreateDate(new Date());
        cartOrder.setCustomer(new Customer(1));
        cartOrder.setStatus(CartOrder.OrderStatus.CONFIRMED);
        cartOrder = cartOrderRepo.save(cartOrder);
        Iterator itr = cartItems.iterator();
        while (itr.hasNext()) {
            CartItemDTO cartItemDTO = (CartItemDTO) itr.next();
            CartItem cartItem = cartItemService.createCartItem(cartItemDTO.getProductId(), cartItemDTO.getQuantity(), cartOrder.getId());
            totalPrice = totalPrice.add(cartItem.getTotalPrice());
            cartItemsList.add(cartItem);
        }
        cartOrder.setTotalPrice(totalPrice);
//        cartOrderRepo.save(cartOrder);
        return cartOrder;
    }


}
