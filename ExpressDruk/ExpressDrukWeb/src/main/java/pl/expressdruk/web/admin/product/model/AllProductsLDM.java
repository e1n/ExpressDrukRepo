
package pl.expressdruk.web.admin.product.model;

import java.util.List;
import org.apache.wicket.model.LoadableDetachableModel;
import pl.expressdruk.entities.Product;
import pl.expressdruk.web.ejbclient.ExpressdrukBeanAccessFacade;

/**
 *
 * @author e1n
 */
public class AllProductsLDM extends LoadableDetachableModel<List<? extends Product>> {

    @Override
    protected List<? extends Product> load() {
        return new ExpressdrukBeanAccessFacade().getAllProducts();
    }

}
