package pl.expressdruk.web.ejbclient;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import pl.expressdruk.ejb.remote.interfaces.ProductCrudOperationsBeanRemote;
import pl.expressdruk.ejb.remote.interfaces.ProductParameterCrudOperationsBeanRemote;
import pl.expressdruk.ejb.remote.interfaces.ProductParameterValueCrudOperationsBeanRemote;
import pl.expressdruk.entities.Product;
import pl.expressdruk.entities.ProductParameter;
import pl.expressdruk.entities.ProductParameterValue;
import pl.expressdruk.web.ejbclient.exceptions.InitialContextBuilderException;
import pl.expressdruk.web.ejbclient.exceptions.UnableToLocateEjbException;

/**
 *
 * @author e1n
 */
public class ExpressdrukBeanAccessFacade {
    private static final Logger logger = Logger.getLogger(ExpressdrukBeanAccessFacade.class.getName());

    public void saveProduct(Product prod) {
        try {
            ProductCrudOperationsBeanRemote productCrudBean = locateBean(ProductCrudOperationsBeanRemote.class);
            productCrudBean.persist(prod);
        } catch (UnableToLocateEjbException ex) {
            logger.log(Level.SEVERE, "ProductCrudOperationsBean was not found", ex);
        }
    }
    
    public void mergeProduct(Product product) {
        try {
            ProductCrudOperationsBeanRemote productCrudBean = locateBean(ProductCrudOperationsBeanRemote.class);
        productCrudBean.merge(product);
        } catch (UnableToLocateEjbException ex) {
            logger.log(Level.SEVERE, "ProductCrudOperationsBean was not found", ex);
        } 
    }
    
    public List<Product> getAllProducts() {
        try {
            ProductCrudOperationsBeanRemote productCrudBean = locateBean(ProductCrudOperationsBeanRemote.class);
            return productCrudBean.getAll();
        } catch (UnableToLocateEjbException ex) {
            logger.log(Level.SEVERE, "ProductCrudOperationsBean was not found", ex);
        }
        return Lists.newLinkedList();
    }
    
    public void saveProductParameter(ProductParameter productParameter) {
        try {
            ProductParameterCrudOperationsBeanRemote prodParamCrudBean = locateBean(ProductParameterCrudOperationsBeanRemote.class);
            prodParamCrudBean.persist(productParameter);
        } catch (UnableToLocateEjbException ex) {
            logger.log(Level.SEVERE, "ProductParameterCrudOperationsBean was not found", ex);
        }
    }
    
    public List<ProductParameter> getAllProductParameters() {
        try {
            ProductParameterCrudOperationsBeanRemote prodParamCrudBean = locateBean(ProductParameterCrudOperationsBeanRemote.class);
            return prodParamCrudBean.getAll();
        } catch (UnableToLocateEjbException ex) {
            logger.log(Level.SEVERE, "ProductParameterCrudOperationsBean was not found", ex);
        }
        return Lists.newLinkedList();
    }
    
    public void saveProductParameterValue(ProductParameterValue productParameterValue) {
        try {
            ProductParameterValueCrudOperationsBeanRemote prodParamValueCrudBean = locateBean(ProductParameterValueCrudOperationsBeanRemote.class);
            prodParamValueCrudBean.persist(productParameterValue);
        } catch (UnableToLocateEjbException ex) {
            logger.log(Level.SEVERE, "ProductParameterValueCrudOperationsBeanRemote was not found", ex);
        }
    }
    
    public List<ProductParameterValue> getAllProductParameterValues() {
        try {
            ProductParameterValueCrudOperationsBeanRemote prodParamValueCrudBean = locateBean(ProductParameterValueCrudOperationsBeanRemote.class);
            return prodParamValueCrudBean.getAll(); 
        } catch (UnableToLocateEjbException ex) {
            logger.log(Level.SEVERE, "ProductParameterValueCrudOperationsBeanRemote was not found", ex);
        }
        return Lists.newLinkedList();
    }
    
    public List<ProductParameterValue> getProductParameterValuesForGivenProductParameter(ProductParameter productParameter) {
        try {
            ProductParameterValueCrudOperationsBeanRemote prodParamValueCrudBean = locateBean(ProductParameterValueCrudOperationsBeanRemote.class);
            return prodParamValueCrudBean.getForGivenProductParameter(productParameter);
        } catch (UnableToLocateEjbException ex) {
            logger.log(Level.SEVERE, "ProductParameterValueCrudOperationsBeanRemote was not found", ex);
        }
        return Lists.newLinkedList();
    }

    private JNDINameGenerator getJNDINameGenerator() {
        return GlassFishJNDINameGenerator.forAppAndModuleNames("ear-1.0-SNAPSHOT", "expressdruk-ejb");
    }

    private InitialContext getInitialContext() throws InitialContextBuilderException {
        InitialContext ctx = new InitialContextBuilder().setHostIPAddress("127.0.0.1").setHostPort("3700").build();
        return ctx;
    }

    private <T> T locateBean(Class<T> beanIface) throws UnableToLocateEjbException {
        InitialContext ic = null;
        try {
            
            ic = getInitialContext();
            ExpressDrukEjbLocator<T> locator =
                    new ExpressDrukEjbLocator<T>(ic, getJNDINameGenerator());
            
            return locator.locate(beanIface);
            
        } catch (UnableToLocateEjbException ex) {
            logger.log(Level.SEVERE, String.format("Unable to locate bean: %s", beanIface.getSimpleName()), ex);
            throw ex;
        } catch (InitialContextBuilderException ex) {
            logger.log(Level.SEVERE, "Exception during initail context creation", ex);
            throw new UnableToLocateEjbException(ex);
        } finally {
            if (ic != null) {
                try {
                    ic.close();
                } catch (NamingException ex) {
                    logger.log(Level.SEVERE , "Exception during initial context close", ex);
                }
            }
        }
    }
}
