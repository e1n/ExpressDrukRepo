package pl.expressdruk.web.admin.productparameter.model;

import java.util.List;
import org.apache.wicket.model.LoadableDetachableModel;
import pl.expressdruk.entities.ProductParameter;
import pl.expressdruk.web.ejbclient.ExpressdrukBeanAccessFacade;

/**
 *
 * @author e1n
 */
public class AllProductParametersLDM extends LoadableDetachableModel<List<? extends ProductParameter>> {

    @Override
    protected List<? extends ProductParameter> load() {
        return new ExpressdrukBeanAccessFacade().getAllProductParameters();
    }
}
