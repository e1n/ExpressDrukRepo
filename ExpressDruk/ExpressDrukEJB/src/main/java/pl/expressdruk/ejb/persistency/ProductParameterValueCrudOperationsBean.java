package pl.expressdruk.ejb.persistency;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import pl.expressdruk.ejb.remote.interfaces.ProductParameterValueCrudOperationsBeanRemote;
import pl.expressdruk.entities.ProductParameterValue;

/**
 *
 * @author e1n
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProductParameterValueCrudOperationsBean implements ProductParameterValueCrudOperationsBeanLocal, ProductParameterValueCrudOperationsBeanRemote {

    @PersistenceContext(unitName = "expressdruk")
    private EntityManager em;

    @Override
    public void persist(ProductParameterValue parameterValue) {
        try {
            em.persist(parameterValue);
        } catch (PersistenceException ex) {
            throw ex;
        }
    }
}
