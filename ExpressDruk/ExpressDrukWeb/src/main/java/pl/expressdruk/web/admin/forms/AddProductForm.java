
package pl.expressdruk.web.admin.forms;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.StringValidator;
import pl.expressdruk.entities.Product;
import pl.expressdruk.web.ejbclient.ExpressdrukBeanAccessFacade;

/**
 *
 * @author e1n
 */
public class AddProductForm extends Form<Product>
{

    public AddProductForm(String id, CompoundPropertyModel<Product> model) {
        super(id, model);

        TextField<String> nameField = new TextField<String>("name");
        nameField.setRequired(true);
        nameField.setLabel(Model.of("Name"));
        nameField.add(StringValidator.lengthBetween(3, 255));
        
        TextArea<String> descriptionField = new TextArea<String>("description");
        descriptionField.setRequired(false);
        descriptionField.setLabel(Model.of("Description"));
        descriptionField.add(StringValidator.maximumLength(8000));
        
        add(nameField);
        add(descriptionField);
    }

    @Override
    protected void onSubmit() {
        super.onSubmit();
        info("Following product was added: " + getModelObject().getName());
        new ExpressdrukBeanAccessFacade().saveProduct(getModelObject());
    }
    
    
    
}
