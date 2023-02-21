package demo.ecommerce.repository;

import demo.ecommerce.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepo extends JpaRepository<Merchant, Long> {
    Merchant findById(long id);
}
