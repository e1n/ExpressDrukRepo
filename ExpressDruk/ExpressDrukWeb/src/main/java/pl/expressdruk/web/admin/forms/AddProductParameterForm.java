
package pl.expressdruk.web.admin.forms;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.StringValidator;
import pl.expressdruk.entities.ProductParameter;
import pl.expressdruk.web.ejbclient.ExpressdrukBeanAccessFacade;

/**
 *
 * @author e1n
 */
public class AddProductParameterForm extends Form<ProductParameter>
{

    public AddProductParameterForm(String id, CompoundPropertyModel<ProductParameter> model) {
        super(id, model);
        
        TextField<String> textField = new TextField<String>("name");
        textField.add(StringValidator.maximumLength(255));
        textField.setRequired(true);
        textField.setLabel(Model.of("Name"));
        
        add(textField);
    }

    @Override
    protected void onSubmit() {
        info("yey form submitted successffully");
        new ExpressdrukBeanAccessFacade().saveProductParameter(getModelObject());
    }
    
    
}
