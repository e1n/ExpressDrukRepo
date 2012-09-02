package pl.expressdruk.ejb.persistency;

import java.util.List;
import javax.ejb.Local;
import pl.expressdruk.entities.ProductParameter;
import pl.expressdruk.entities.ProductParameterValue;

/**
 *
 * @author e1n
 */
@Local
public interface ProductParameterValueCrudOperationsBeanLocal {
    
    public void persist(ProductParameterValue parameterValue);
    
    public List<ProductParameterValue> getAll();
    
    public List<ProductParameterValue> getForGivenProductParameter(ProductParameter productParameter);
    
}
