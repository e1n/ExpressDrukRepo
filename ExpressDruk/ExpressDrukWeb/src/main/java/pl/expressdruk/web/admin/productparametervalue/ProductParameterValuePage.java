package pl.expressdruk.web.admin.productparametervalue;

import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import pl.expressdruk.entities.ProductParameter;
import pl.expressdruk.entities.ProductParameterValue;
import pl.expressdruk.web.admin.productparameter.model.AllProductParametersLDM;
import pl.expressdruk.web.ejbclient.ExpressdrukBeanAccessFacade;

/**
 *
 * @author e1n
 */
public class ProductParameterValuePage extends WebPage {

    private ProductParameter productParameter;
    private ProductParameterValue productParameterValue;

    public ProductParameterValuePage(PageParameters parameters) {
        super(parameters);

        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        Form<?> form = new Form<Void>("form") {

            @Override
            protected void onSubmit() {
                productParameterValue.setProductParameter(productParameter);
                new ExpressdrukBeanAccessFacade().saveProductParameterValue(productParameterValue);
                info("Product parameter value saved with success !");
            }
        };
        add(feedbackPanel);
        
        final TextField<ProductParameterValue> prodParamValTxtField = new TextField<ProductParameterValue>(
                "product_param_val", new PropertyModel<ProductParameterValue>(this, "productParameterValue")); 
        prodParamValTxtField.setRequired(true);
        form.add(prodParamValTxtField);

        DropDownChoice<ProductParameter> prodParamDropDown = new DropDownChoice<ProductParameter>(
                "product_param", new PropertyModel<ProductParameter>(this, "productParameter"), new AllProductParametersLDM(), new ChoiceRenderer<ProductParameter>("name", "id")) {

            @Override
            protected void onSelectionChanged(ProductParameter newSelection) {
                prodParamValTxtField.clearInput();
                prodParamValTxtField.getModel().setObject(null);
            }

            @Override
            protected boolean wantOnSelectionChangedNotifications() {
                return true;
            }
        };
        prodParamDropDown.setNullValid(false);
        prodParamDropDown.setRequired(true);
        prodParamDropDown.setLabel(Model.of("Product Parameter"));
        form.add(prodParamDropDown);  

        add(form);

    }
}
