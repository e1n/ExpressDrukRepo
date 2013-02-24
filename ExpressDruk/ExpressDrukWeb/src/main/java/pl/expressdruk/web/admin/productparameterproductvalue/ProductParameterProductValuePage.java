package pl.expressdruk.web.admin.productparameterproductvalue;

import com.google.common.collect.Sets;
import java.util.Map;
import org.apache.wicket.devutils.debugbar.DebugBar;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import pl.expressdruk.entities.ParamAssignedToProductWithValues;
import pl.expressdruk.entities.Product;
import pl.expressdruk.entities.ProductParameter;
import pl.expressdruk.entities.ProductParameterValue;
import pl.expressdruk.web.admin.product.model.AllProductsLDM;
import pl.expressdruk.web.admin.productparameter.model.AllProductParametersLDM;
import pl.expressdruk.web.admin.productparametervalue.model.ProductParameterValueForProductParamLDM;
import pl.expressdruk.web.ejbclient.ExpressdrukBeanAccessFacade;

/**
 *
 * @author e1n
 */
public class ProductParameterProductValuePage extends WebPage {

    private Product product;
    private ProductParameter productParameter;
    private PropertyModel<ProductParameter> productParameterModel;
    private ProductParameterValue productParameterValue;

    public ProductParameterProductValuePage(PageParameters parameters) {
        super(parameters);

        productParameterModel = new PropertyModel<ProductParameter>(this, "productParameter");
        
        DebugBar debugBar = new DebugBar("debug");
        add(debugBar);

        add(new FeedbackPanel("feedback"));

        Form<Void> form = new Form<Void>("form") {

            @Override
            protected void onSubmit() {
                super.onSubmit();
                Map<ProductParameter, ParamAssignedToProductWithValues> assignedParamsAndVals = product.getAssignedParamsValues();
                if (assignedParamsAndVals.containsKey(productParameter)) {
                    ParamAssignedToProductWithValues valsForParam = assignedParamsAndVals.get(productParameter);
                    valsForParam.getProductParameterValues().add(productParameterValue);
                } else {
                    ParamAssignedToProductWithValues valsForParam = new ParamAssignedToProductWithValues(
                            productParameter, product, Sets.newHashSet(productParameterValue));
                    assignedParamsAndVals.put(productParameter, valsForParam);
                }
                product.setAssignedParamsValues(assignedParamsAndVals);
                new ExpressdrukBeanAccessFacade().mergeProduct(product);
                info("product parameter and value added to product sucessfuly");
            }
        };

        DropDownChoice<Product> productDropDown = new DropDownChoice<Product>("product_choice",
                new PropertyModel<Product>(this, "product"),
                new AllProductsLDM(),
                new ChoiceRenderer<Product>("name", "id"));
        
        DropDownChoice<ProductParameter> productParamDropDown = new DropDownChoice<ProductParameter>("product_param_choice",
                productParameterModel,
                new AllProductParametersLDM(),
                new ChoiceRenderer<ProductParameter>("name", "id")) {

            @Override
            protected void onSelectionChanged(ProductParameter newSelection) {
                super.onSelectionChanged(newSelection);
                productParameterValue = null;
            }

            @Override
            protected boolean wantOnSelectionChangedNotifications() {
                return true;
            }
        };
        
        DropDownChoice<ProductParameterValue> productParamValDropDown = new DropDownChoice<ProductParameterValue>("product_param_val_choice",
                new PropertyModel<ProductParameterValue>(this, "productParameterValue"),
                new ProductParameterValueForProductParamLDM(productParameterModel),
                new ChoiceRenderer<ProductParameterValue>("value", "id"));

        add(form);
        form.add(productDropDown);
        form.add(productParamDropDown);
        form.add(productParamValDropDown);
    }
}
