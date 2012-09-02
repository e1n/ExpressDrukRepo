package pl.expressdruk.ejb.remote.interfaces;

import java.util.List;
import javax.ejb.Remote;
import pl.expressdruk.entities.ProductParameter;
import pl.expressdruk.entities.ProductParameterValue;

/**
 *
 * @author e1n
 */
@Remote
public interface ProductParameterValueCrudOperationsBeanRemote {
 
    public void persist(ProductParameterValue parameterValue);
    
    public List<ProductParameterValue> getAll();
    
    public List<ProductParameterValue> getForGivenProductParameter(ProductParameter productParameter);
}
