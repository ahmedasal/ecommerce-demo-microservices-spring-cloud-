package demo.ecommerce.apis.service;

import demo.ecommerce.model.Tax;
import demo.ecommerce.repository.TaxRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;


@Service
@AllArgsConstructor
public class TaxService {
    private final TaxRepo taxRepo;
    @PersistenceContext
    EntityManager em;

    public BigDecimal getTaxesRatio() {
        BigDecimal x = new BigDecimal(0);
        Collection<BigDecimal> taxes = em.createQuery("select t.taxAmount from Tax t ").getResultList();
        Iterator itr = taxes.iterator();
        while (itr.hasNext()) {
            x = x.add((BigDecimal) itr.next());
        }
        return x;
    }
    public void addTax(Tax tax ){
        taxRepo.save(tax);
    }
}
