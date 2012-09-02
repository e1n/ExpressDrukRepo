package pl.expressdruk.ejb.remote.interfaces;

import java.util.List;
import javax.ejb.Remote;
import pl.expressdruk.entities.Product;

/**
 *
 * @author e1n
 */
@Remote
public interface ProductCrudOperationsBeanRemote {
    
    public void persist(Product product);
    
    public List<Product> getAll();
    
    public void merge(Product product);
    
}
