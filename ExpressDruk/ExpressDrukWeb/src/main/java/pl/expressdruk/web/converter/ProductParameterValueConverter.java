
package pl.expressdruk.web.converter;

import com.google.common.base.Strings;
import java.util.Locale;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;
import pl.expressdruk.entities.ProductParameterValue;

/**
 *
 * @author e1n
 */
public class ProductParameterValueConverter implements IConverter<ProductParameterValue> {
    protected static final String LACK_OF_VALUE_ERR_KEY = "lackOfValue";

    public ProductParameterValue convertToObject(String string, Locale locale) {
        if (Strings.isNullOrEmpty(string)) {
            error(string, LACK_OF_VALUE_ERR_KEY);
        }
        ProductParameterValue ppv = new ProductParameterValue();
        ppv.setValue(string);
        return ppv;
    }

    public String convertToString(ProductParameterValue c, Locale locale) {
        if (Strings.isNullOrEmpty(c.getValue())) {
            error(c.getValue(), LACK_OF_VALUE_ERR_KEY);
        }
        return c.getValue();
    }
    
    private void error(String value, String errorKey) {
        ConversionException convEx = new ConversionException(String.format("Error during conversion of value %s", value));
        convEx.setResourceKey(errorKey);
        convEx.setConverter(this);
        convEx.setSourceValue(value);
        throw convEx;
    }

}
