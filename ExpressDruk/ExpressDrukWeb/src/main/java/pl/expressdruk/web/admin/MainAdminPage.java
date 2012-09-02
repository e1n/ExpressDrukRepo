
package pl.expressdruk.web.admin;

import org.apache.wicket.devutils.debugbar.DebugBar;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import pl.expressdruk.web.admin.product.ProductPage;
import pl.expressdruk.web.admin.productparameter.ProductParameterPage;
import pl.expressdruk.web.admin.productparameterproductvalue.ProductParameterProductValuePage;
import pl.expressdruk.web.admin.productparametervalue.ProductParameterValuePage;

/**
 *
 * @author e1n
 */
public class MainAdminPage extends WebPage {

    public MainAdminPage(PageParameters parameters) {
        super(parameters);
        DebugBar debugBar = new DebugBar("debug");
        add(debugBar);
        add(new Link("add_product_link") {

            @Override
            public void onClick() {
               setResponsePage(ProductPage.class); 
            }
        });
        add(new Link("add_product_parameter_link") {

            @Override
            public void onClick() {
               setResponsePage(ProductParameterPage.class); 
            }
        });
        add(new Link("add_product_parameter_value_link") {

            @Override
            public void onClick() {
               setResponsePage(ProductParameterValuePage.class); 
            }
        });
       add(new Link("add_product_parameter_product_value_link"){

            @Override
            public void onClick() {
                setResponsePage(ProductParameterProductValuePage.class);
            }
        });
       
    }

    
}
