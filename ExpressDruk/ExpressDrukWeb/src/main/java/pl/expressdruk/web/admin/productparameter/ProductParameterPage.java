
package pl.expressdruk.web.admin.productparameter;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import pl.expressdruk.entities.Product;
import pl.expressdruk.entities.ProductParameter;
import pl.expressdruk.web.admin.forms.AddProductForm;
import pl.expressdruk.web.admin.forms.AddProductParameterForm;

/**
 *
 * @author e1n
 */
public class ProductParameterPage extends WebPage {

    ProductParameter productParameter = new ProductParameter();
    
    public ProductParameterPage(PageParameters parameters) {
        super(parameters);
        
        add(new FeedbackPanel("feedback"));
        add(new AddProductParameterForm("form", new CompoundPropertyModel<ProductParameter>(productParameter)));
    }
    
}
