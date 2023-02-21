package demo.ecommerce.repository;

import demo.ecommerce.model.CartOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.security.SecureRandom;

@Repository
public interface CartOrderRepo extends JpaRepository<CartOrder, Long> {
    CartOrder findById(long id);
}
