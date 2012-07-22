package pl.expressdruk.ejb.persistency;

import javax.ejb.Local;
import pl.expressdruk.entities.Product;

/**
 *
 * @author e1n
 */
@Local
public interface ProductCrudOperationsBeanLocal {
    
    public void persist(Product product);
    
}
