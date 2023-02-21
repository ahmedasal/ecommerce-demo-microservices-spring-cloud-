package demo.ecommerce.service;

import demo.ecommerce.dto.CartItemDTO;
import demo.ecommerce.model.CartItem;
import demo.ecommerce.model.CartOrder;
import demo.ecommerce.model.Customer;
import demo.ecommerce.model.Product;
import demo.ecommerce.repository.CartItemRepo;
import demo.ecommerce.repository.CartOrderRepo;
import demo.ecommerce.repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CartOrderService {
    @PersistenceContext
    EntityManager em;
    @Autowired
    CartItemRepo cartItemRepo;
    @Autowired
    TaxService taxService;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    CartOrderRepo cartOrderRepo;

    //TODO
    @Transactional
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
            CartItem cartItem = createCartItem(cartItemDTO.getProductId(), cartItemDTO.getQuantity(), cartOrder.getId());
            totalPrice = totalPrice.add(cartItem.getTotalPrice());
            cartItemsList.add(cartItem);
        }
        cartOrder.setTotalPrice(totalPrice);
//        cartOrderRepo.save(cartOrder);
        return cartOrder;
    }

    public CartItem createCartItem(long productId, int quantity, long cartOrderId) {
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(quantity);
        cartItem.setCostPrice(getCostPrice(productId));
        cartItem.setEarning(getEarning(productId));
        cartItem.setShipping(new BigDecimal(50));
        cartItem.setTaxesRatio(taxService.getTaxesRatio());
        cartItem.setTotalTaxes(calculateCartItemTotalTax(cartItem));
        cartItem.setSubTotal(calculateSubTotal(cartItem));
        cartItem.setTotalPrice(calculateTotalPrice(cartItem));
        cartItem.setProduct(new Product(productId));
        cartItem.setCartOrder(new CartOrder(cartOrderId));
        cartItem = cartItemRepo.save(cartItem);
        return cartItem;
    }


    public BigDecimal getCostPrice(long productId) {
        Object costPrice = em.createQuery("select p.costPrice from Product p where p.id = :id").setParameter("id", productId).getSingleResult();
        return (BigDecimal) costPrice;
    }

    public BigDecimal getEarning(long productId) {
        Object earning = em.createQuery("select p.earning from Product p where p.id = :id").setParameter("id", productId).getSingleResult();
        return (BigDecimal) earning;
    }

    //for all quantity
    private BigDecimal calculateCartItemTotalTax(CartItem cartItem) {
        return calculateSubTotalWithoutShipping(cartItem).multiply(taxService.getTaxesRatio());
    }

    //for all quantity
    private BigDecimal calculateSubTotal(CartItem cartItem) {
        return ((cartItem.getCostPrice().add(cartItem.getEarning())).add(cartItem.getShipping())).multiply(BigDecimal.valueOf(cartItem.getQuantity()));
    }
    private BigDecimal calculateSubTotalWithoutShipping(CartItem cartItem) {
        return ((cartItem.getCostPrice().add(cartItem.getEarning()))).multiply(BigDecimal.valueOf(cartItem.getQuantity()));
    }

    //for all quantity
    private BigDecimal calculateTotalPrice(CartItem cartItem) {
        return calculateSubTotal(cartItem).add(calculateCartItemTotalTax(cartItem));
    }

    //TODO I will add this feature to calculate shipping for every country
    public BigDecimal calculateShipping(String country, String government) {
        return new BigDecimal(10);
    }











}
