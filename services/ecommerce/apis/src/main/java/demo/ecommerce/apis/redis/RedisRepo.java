package demo.ecommerce.apis.redis;


import demo.ecommerce.dto.ProductDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisRepo extends CrudRepository<ProductDTO, Long> {

    void deleteById(long id);
}
