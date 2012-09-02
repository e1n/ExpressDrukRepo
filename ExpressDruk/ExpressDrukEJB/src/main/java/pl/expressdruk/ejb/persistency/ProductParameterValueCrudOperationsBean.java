package pl.expressdruk.ejb.persistency;

import com.google.common.base.Preconditions;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import pl.expressdruk.ejb.remote.interfaces.ProductParameterValueCrudOperationsBeanRemote;
import pl.expressdruk.entities.ProductParameter;
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

    @Override
    public List<ProductParameterValue> getAll() {
        try {
            Query q = em.createNamedQuery(ProductParameterValue.GET_ALL_QUERY);
            List<ProductParameterValue> productParameterValues = q.getResultList();
            return productParameterValues;
        } catch (PersistenceException ex) {
            throw ex;
        }
    }

    @Override
    public List<ProductParameterValue> getForGivenProductParameter(ProductParameter productParameter) {
        Preconditions.checkNotNull(productParameter, "productParameter argument can't be null !");
        Preconditions.checkArgument(productParameter.getId() > 0, "proudctParameter must have correct ID !");
        try {
            Query q = em.createNamedQuery(ProductParameterValue.GET_FOR_GIVEN_PRODUCT_PARAM_QUERY);
            q.setParameter("product_parameter", productParameter);
            List<ProductParameterValue> productParameterValues = q.getResultList();
            return productParameterValues;
        } catch (PersistenceException ex) {
            throw ex;
        }
    }
}
