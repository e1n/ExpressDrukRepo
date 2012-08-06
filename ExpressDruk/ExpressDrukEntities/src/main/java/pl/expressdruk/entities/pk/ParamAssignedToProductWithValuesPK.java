package pl.expressdruk.entities.pk;

import java.io.Serializable;

/**
 *
 * @author e1n
 */
public class ParamAssignedToProductWithValuesPK implements Serializable {

    private int product;
    private int productParameter; 
    
    public ParamAssignedToProductWithValuesPK() {
    }

    public ParamAssignedToProductWithValuesPK(int product, int productParameter) {
        this.product = product;
        this.productParameter = productParameter;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getProductParameter() {
        return productParameter;
    }

    public void setProductParameter(int productParameter) {
        this.productParameter = productParameter;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ParamAssignedToProductWithValuesPK other = (ParamAssignedToProductWithValuesPK) obj;
        if (this.product != other.product) {
            return false;
        }
        if (this.productParameter != other.productParameter) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.product;
        hash = 97 * hash + this.productParameter;
        return hash;
    }
    
    
    
}
