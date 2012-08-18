package pl.expressdruk.ejb.persistency;

import javax.ejb.Local;
import pl.expressdruk.entities.ProductParameterValue;

/**
 *
 * @author e1n
 */
@Local
public interface ProductParameterValueCrudOperationsBeanLocal {
    
    public void persist(ProductParameterValue parameterValue);
    
}
