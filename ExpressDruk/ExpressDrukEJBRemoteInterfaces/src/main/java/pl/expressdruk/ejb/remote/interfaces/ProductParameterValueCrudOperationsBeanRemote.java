package pl.expressdruk.ejb.remote.interfaces;

import javax.ejb.Remote;
import pl.expressdruk.entities.ProductParameterValue;

/**
 *
 * @author e1n
 */
@Remote
public interface ProductParameterValueCrudOperationsBeanRemote {
 
    public void persist(ProductParameterValue parameterValue);
    
}
