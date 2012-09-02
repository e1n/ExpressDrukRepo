package pl.expressdruk.ejb.persistency;

import java.util.List;
import pl.expressdruk.ejb.remote.interfaces.ProductCrudOperationsBeanRemote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.*;
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
    
    @Override
    public void merge(Product product) {
        try {
            em.merge(product);
        } catch (PersistenceException ex) {
            throw ex;
        }
    }

    @Override
    public List<Product> getAll() {
        try {
            Query q = em.createNamedQuery(Product.GET_ALL_QUERY);
            return q.getResultList();
        } catch (PersistenceException ex) {
            throw ex;
        }
    }   
}
