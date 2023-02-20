package demo.ecommerce.repository;

import demo.ecommerce.model.CartOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartOrderRepo extends JpaRepository<CartOrder, Long> {
}
