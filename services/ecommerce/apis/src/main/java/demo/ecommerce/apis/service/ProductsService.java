//package demo.ecommerce.apis.service;
//
//import demo.ecommerce.dto.ProductDTO;
//import demo.ecommerce.model.Product;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@AllArgsConstructor
//public class ProductsService {
//    @PersistenceContext
//    EntityManager em;
//
//    public List<ProductDTO> getAllProducts() {
//        List<ProductDTO> productDTOS = new ArrayList<>();
//        List<Product> products = em.createQuery("select p from Product p").getResultList();
//        for (Product p : products){
//            ProductDTO productDTO = mapper.convertFromProductModelToProductDTO(p);
//            productDTOS.add(productDTO);
//        }
//        return productDTOS.size() > 0 ? productDTOS : null;
//    }
//}
