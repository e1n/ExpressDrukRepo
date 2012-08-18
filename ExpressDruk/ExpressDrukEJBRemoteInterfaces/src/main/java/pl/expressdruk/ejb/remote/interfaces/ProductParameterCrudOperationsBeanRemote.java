
package pl.expressdruk.ejb.remote.interfaces;

import java.util.List;
import javax.ejb.Remote;
import pl.expressdruk.entities.ProductParameter;

/**
 *
 * @author e1n
 */
@Remote
public interface ProductParameterCrudOperationsBeanRemote {
    
    public void persist(ProductParameter productParameter);
    
    public List<ProductParameter> getAll();

}
