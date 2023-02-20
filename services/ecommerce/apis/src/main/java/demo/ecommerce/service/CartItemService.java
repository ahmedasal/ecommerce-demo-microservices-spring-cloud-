package demo.ecommerce.service;

import demo.ecommerce.model.*;
import demo.ecommerce.repository.CartItemRepo;
import demo.ecommerce.repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CartItemService {
    @PersistenceContext
    EntityManager em;
    private final CartItemRepo cartItemRepo;
    private final TaxService taxService;
    ProductRepo productRepo;

    public CartItem createCartItem(long productId, int quantity, long cartOrderId) {
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(quantity);
        cartItem.setCostPrice(getCostPrice(productId, em));
        cartItem.setEarning(getEarning(productId, em));
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


    public BigDecimal getCostPrice(long productId, EntityManager em) {
        Object costPrice = em.createQuery("select p.costPrice from Product p where p.id = :id").setParameter("id", productId).getSingleResult();
        return (BigDecimal) costPrice;
    }

    public BigDecimal getEarning(long productId, EntityManager em) {
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
