package pl.expressdruk.ejb.persistency;

import java.util.List;
import javax.ejb.Local;
import pl.expressdruk.entities.ProductParameter;

/**
 *
 * @author e1n
 */
@Local
public interface ProductParameterCrudOperationsBeanLocal {
    
    public void persist(ProductParameter productParameter);
    
    public List<ProductParameter> getAll();
    
}
