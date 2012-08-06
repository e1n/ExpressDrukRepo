package pl.expressdruk.web.admin.product;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.StringValidator;
import pl.expressdruk.entities.Product;
import pl.expressdruk.web.admin.forms.AddProductForm;

/**
 *
 * @author e1n
 */
public class ProductPage extends WebPage {

    private static final long serialVersionUID = 1L;
    
    private Product product = new Product();

    public ProductPage(PageParameters parameters) {
        super(parameters);
        
        Form<Product> form = new AddProductForm("form", new CompoundPropertyModel<Product>(product));
        FeedbackPanel feedback = new FeedbackPanel("feedback");
        
        add(feedback);
        add(form);       
    }
}
