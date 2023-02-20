package demo.ecommerce.repository;

import demo.ecommerce.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface customerRepo extends JpaRepository<Customer, Long> {

}
