package demo.ecommerce.dto;

import demo.ecommerce.model.Customer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartItemDTO {
    private long productId;
    private int quantity;
}
