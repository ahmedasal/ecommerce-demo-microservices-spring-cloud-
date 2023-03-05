package demo.ecommerce.apis.service;

import demo.ecommerce.dto.CartItemDTO;
import demo.ecommerce.model.CartItem;
import demo.ecommerce.model.CartOrder;
import demo.ecommerce.model.Customer;
import demo.ecommerce.model.Product;
import demo.ecommerce.repository.CartItemRepo;
import demo.ecommerce.repository.CartOrderRepo;
import demo.ecommerce.repository.CustomerRepo;
import demo.ecommerce.repository.ProductRepo;
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
public class OrderService {
    @PersistenceContext
    EntityManager em;
    @Autowired
    CartItemRepo cartItemRepo;
    @Autowired
    TaxService taxService;

    @Autowired
    CartOrderRepo cartOrderRepo;
    @Autowired
    MerchantService merchantService;
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    private ProductRepo productRepo;


    @Transactional
    public String createOrder(List<CartItemDTO> cartItems) {
        CartOrder cartOrder = new CartOrder();
        BigDecimal totalPrice = new BigDecimal(0);
        cartOrder.setCreateDate(new Date());
        cartOrder.setCustomer(customerRepo.findById(1));
        cartOrder.setStatus(CartOrder.OrderStatus.CONFIRMED); //pending
        cartOrder.setItems(new ArrayList<>());
        // cartOrder = cartOrderRepo.save(cartOrder);

        Iterator itr = cartItems.iterator();
        while (itr.hasNext()) {
            CartItemDTO cartItemDTO = (CartItemDTO) itr.next();
            CartItem item = createCartItem(cartItemDTO.getProductId(), cartItemDTO.getQuantity(), cartOrder);
            totalPrice = totalPrice.add(item.getTotalPrice());
            cartOrder.getItems().add(item);
        }

        cartOrder.setTotalPrice(totalPrice);
        cartOrderRepo.save(cartOrder);
        return "cartOrder added successfully";
    }


    public CartItem createCartItem(long productId, int quantity, CartOrder cartOrder) {
        Product product = productRepo.findById(productId);
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(quantity);
        cartItem.setCostPrice(product.getCostPrice());
        cartItem.setEarning(product.getEarning());
        cartItem.setShipping(new BigDecimal(50));
        cartItem.setTaxesRatio(taxService.getTaxesRatio());
        cartItem.setTotalTaxes(
                product.getCostPrice().add(product.getEarning())
                        .multiply(BigDecimal.valueOf(quantity))
                        .multiply(taxService.getTaxesRatio())
        );
        cartItem.setSubTotal(
                product.getCostPrice().add(product.getEarning())
                        .multiply(BigDecimal.valueOf(quantity))
        );
        cartItem.setTotalPrice(cartItem.getSubTotal().add(cartItem.getShipping()).add(cartItem.getTotalTaxes()));
        cartItem.setProduct(product);
        cartItem.setCartOrder(cartOrder);
        // return cartItemRepo.save(cartItem);
        return cartItem;
    }


    //get cost price from database table of product
//    public BigDecimal getCostPrice(long productId) {
//        Object costPrice = em.createQuery("select p.costPrice from Product p where p.id = :id").setParameter("id", productId).getSingleResult();
//        return (BigDecimal) costPrice;
//    }
//    //get earning from database table of product
//    public BigDecimal getEarning(long productId) {
//        Object earning = em.createQuery("select p.earning from Product p where p.id = :id").setParameter("id", productId).getSingleResult();
//        return (BigDecimal) earning;
//    }

    //for all quantity
//    private BigDecimal calculateCartItemTotalTax(CartItem cartItem) {
//        return calculateSubTotalWithoutShipping(cartItem).multiply(taxService.getTaxesRatio());
//    }

    //for all quantity
//    private BigDecimal calculateSubTotal(CartItem cartItem) {
//        return ((cartItem.getCostPrice().add(cartItem.getEarning())).add(cartItem.getShipping())).multiply(BigDecimal.valueOf(cartItem.getQuantity()));
//    }

//    private BigDecimal calculateSubTotalWithoutShipping(CartItem cartItem) {
//        return ((cartItem.getCostPrice().add(cartItem.getEarning()))).multiply(BigDecimal.valueOf(cartItem.getQuantity()));
//    }

    //for all quantity
//    private BigDecimal calculateTotalPrice(CartItem cartItem) {
//        return calculateSubTotal(cartItem).add(calculateCartItemTotalTax(cartItem));
//    }

    //TODO I will add this feature to calculate shipping for every country
    public BigDecimal calculateShipping(String country, String government) {
        return new BigDecimal(10);
    }


}
