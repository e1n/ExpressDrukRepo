
package pl.expressdruk.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import pl.expressdruk.entities.pk.ParamAssignedToProductWithValuesPK;

/**
 *
 * @author e1n
 */
@Entity
@Table(name="product_parameter_product")
@Access(AccessType.FIELD)
@IdClass(ParamAssignedToProductWithValuesPK.class)
public class ParamAssignedToProductWithValues implements Serializable {

    @Id
    @ManyToOne(optional=false)
    @JoinColumn(name="product_parameter_id", referencedColumnName="id")
    private ProductParameter productParameter;
    @Id
    @ManyToOne(optional=false)
    @JoinColumn(name="product_id", referencedColumnName="id")
    private Product product;

    @OneToMany
    @JoinTable(name="product_parameter_product_value",
        joinColumns={
            @JoinColumn(name="product_parameter_id", referencedColumnName="product_parameter_id"),
            @JoinColumn(name="product_id", referencedColumnName="product_id")
        },
        inverseJoinColumns={
            @JoinColumn(name="product_parameter_value_id",referencedColumnName="id")
        }    
    )
    private Set<ProductParameterValue> productParameterValues;

    public ParamAssignedToProductWithValues() {
    }

    public ParamAssignedToProductWithValues(ProductParameter productParameter, Product product, Set<ProductParameterValue> productParameterValues) {
        this.productParameter = productParameter;
        this.product = product;
        this.productParameterValues = productParameterValues;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductParameter getProductParameter() {
        return productParameter;
    }

    public void setProductParameter(ProductParameter productParameter) {
        this.productParameter = productParameter;
    }

    public Set<ProductParameterValue> getProductParameterValues() {
        return productParameterValues;
    }

    public void setProductParameterValues(Set<ProductParameterValue> productParameterValues) {
        this.productParameterValues = productParameterValues;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ParamAssignedToProductWithValues other = (ParamAssignedToProductWithValues) obj;
        if (this.productParameter != other.productParameter && (this.productParameter == null || !this.productParameter.equals(other.productParameter))) {
            return false;
        }
        if (this.product != other.product && (this.product == null || !this.product.equals(other.product))) {
            return false;
        }
        if (this.productParameterValues != other.productParameterValues && (this.productParameterValues == null || !this.productParameterValues.equals(other.productParameterValues))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.productParameter != null ? this.productParameter.hashCode() : 0);
        hash = 67 * hash + (this.product != null ? this.product.hashCode() : 0);
        hash = 67 * hash + (this.productParameterValues != null ? this.productParameterValues.hashCode() : 0);
        return hash;
    }
    
    
    
}
