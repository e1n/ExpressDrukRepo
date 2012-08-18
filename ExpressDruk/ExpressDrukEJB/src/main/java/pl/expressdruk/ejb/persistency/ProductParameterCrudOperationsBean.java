package pl.expressdruk.ejb.persistency;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import pl.expressdruk.ejb.remote.interfaces.ProductParameterCrudOperationsBeanRemote;
import pl.expressdruk.entities.ProductParameter;

/**
 *
 * @author e1n
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProductParameterCrudOperationsBean implements ProductParameterCrudOperationsBeanRemote, ProductParameterCrudOperationsBeanLocal {

    @PersistenceContext(unitName = "expressdruk")
    private EntityManager em;

    @Override
    public void persist(ProductParameter productParameter) {
        try {
            em.persist(productParameter);
        } catch (PersistenceException ex) {
            throw ex;
        }
    }
    
    @Override
    public List<ProductParameter> getAll() {
        Query q = em.createNamedQuery(ProductParameter.GET_ALL_QUERY);
        List<ProductParameter> productParameters = q.getResultList();
        return productParameters;
    }
}
