package pl.expressdruk.ejb.persistency;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.PersistenceUnit;
import pl.expressdruk.entities.Product;

/**
 *
 * @author e1n
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProductCrudOperationsBean implements ProductCrudOperationsBeanRemote, ProductCrudOperationsBeanLocal {
    
    @PersistenceContext(unitName="expressdruk")
    private EntityManager em;

    @Override
    public void persist(Product product) {
        try {
            em.persist(product);
        } catch (PersistenceException ex) {
            throw ex;
        }
    }

    
    
}
