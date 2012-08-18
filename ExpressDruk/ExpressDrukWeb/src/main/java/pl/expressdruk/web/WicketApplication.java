package pl.expressdruk.web;

import org.apache.wicket.ConverterLocator;
import org.apache.wicket.IConverterLocator;
import org.apache.wicket.protocol.http.WebApplication;
import pl.expressdruk.entities.ProductParameterValue;
import pl.expressdruk.web.admin.product.ProductPage;
import pl.expressdruk.web.admin.productparameter.ProductParameterPage;
import pl.expressdruk.web.admin.productparametervalue.ProductParameterValuePage;
import pl.expressdruk.web.converter.ProductParameterValueConverter;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see pl.expressdruk.web.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{    	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<ProductParameterValuePage> getHomePage()
	{
		return ProductParameterValuePage.class;
	}
        
        

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

		// add your configuration here
                
	}

    @Override
    protected IConverterLocator newConverterLocator() {
        ConverterLocator locator = (ConverterLocator)super.newConverterLocator();
        locator.set(ProductParameterValue.class, new ProductParameterValueConverter());
        return locator;
    }
        
        
}
