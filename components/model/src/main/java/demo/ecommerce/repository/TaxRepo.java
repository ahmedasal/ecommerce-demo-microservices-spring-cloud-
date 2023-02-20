package demo.ecommerce.repository;

import demo.ecommerce.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxRepo extends JpaRepository<Tax, Long> {
}
