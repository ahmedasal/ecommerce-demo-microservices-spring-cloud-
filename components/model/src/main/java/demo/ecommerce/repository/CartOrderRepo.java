package demo.ecommerce.repository;

import demo.ecommerce.model.CartOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartOrderRepo extends JpaRepository<CartOrder, Long> {
    CartOrder findById(long id);
    @Modifying
    @Query("delete from CartOrder c where c.id=:id")
    void deleteCartOrder(@Param("id") long id);
}
