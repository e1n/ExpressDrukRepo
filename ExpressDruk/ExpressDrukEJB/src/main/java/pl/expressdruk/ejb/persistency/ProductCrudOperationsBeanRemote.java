package pl.expressdruk.ejb.persistency;

import javax.ejb.Remote;
import pl.expressdruk.entities.Product;

/**
 *
 * @author e1n
 */
@Remote
public interface ProductCrudOperationsBeanRemote {
    
    public void persist(Product product);
    
}
