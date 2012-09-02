package pl.expressdruk.web.admin.productparametervalue.model;

import com.google.common.collect.Lists;
import java.util.List;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import pl.expressdruk.entities.ProductParameter;
import pl.expressdruk.entities.ProductParameterValue;
import pl.expressdruk.web.ejbclient.ExpressdrukBeanAccessFacade;

/**
 *
 * @author e1n
 */
public class ProductParameterValueForProductParamLDM extends LoadableDetachableModel<List<? extends ProductParameterValue>> {

    private final PropertyModel<ProductParameter> productParameter;

    public ProductParameterValueForProductParamLDM(PropertyModel<ProductParameter> productParameter) {
        this.productParameter = productParameter;
    }

    @Override
    protected List<? extends ProductParameterValue> load() {
        if (productParameter.getObject() != null) {
            return new ExpressdrukBeanAccessFacade().getProductParameterValuesForGivenProductParameter(productParameter.getObject());
        }
        return Lists.newLinkedList();
    }
}
