package demo.ecommerce.repository;

import demo.ecommerce.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxRepo extends JpaRepository<Tax, Long> {
    Tax findById(long id);
}
