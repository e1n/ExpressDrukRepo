package pl.expressdruk.entities;

import static com.google.common.base.Preconditions.*;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author e1n
 */
@NamedQueries({
    @NamedQuery(name=ProductParameterValue.GET_ALL_QUERY, query="SELECT ppv FROM ProductParameterValue ppv ORDER BY ppv id ASC"),
    @NamedQuery(name=ProductParameterValue.GET_FOR_GIVEN_PRODUCT_PARAM_QUERY, query="SELECT ppv FROM ProductParameterValue ppv WHERE ppv.productParameter = :product_parameter")
})
@Entity
@Table(name="product_parameter_value")
@Access(AccessType.PROPERTY)
public class ProductParameterValue implements Serializable {
    
    public static final String GET_ALL_QUERY = "ProductParameterValue.getAll";
    public static final String GET_FOR_GIVEN_PRODUCT_PARAM_QUERY = "ProductParameterValue.getForGivenProductParamQuery";
    
    private int id;
    private ProductParameter productParameter;
    private String value;

    public ProductParameterValue() {
        
    }
    
    public ProductParameterValue(ProductParameter productParameter, String value) {
        checkNotNull(productParameter, "ProductParameter must not be null !");
        checkNotNull(value, "parameter value must not be null !");
        this.productParameter = productParameter;
        this.value = value;
    }
    
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, optional=false)
    @JoinColumn(name="product_parameter_id", referencedColumnName="id", nullable=false)
    public ProductParameter getProductParameter() {
        return productParameter;
    }

    public void setProductParameter(ProductParameter productParameter) {
        this.productParameter = productParameter;
    }
    
    @Basic(fetch= FetchType.EAGER,optional=false)
    @Column(name="value", nullable=false)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductParameterValue other = (ProductParameterValue) obj;
        if (this.getId() != other.getId()) {
            return false;
        }
        if (this.getProductParameter() != other.getProductParameter() && (this.getProductParameter() == null || !this.getProductParameter().equals(other.getProductParameter()))) {
            return false;
        }
        if ((this.getValue() == null) ? (other.getValue() != null) : !this.getValue().equals(other.getValue())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.getId();
        hash = 89 * hash + (this.getProductParameter() != null ? this.getProductParameter().hashCode() : 0);
        hash = 89 * hash + (this.getValue() != null ? this.getValue().hashCode() : 0);
        return hash;
    }

    
    
    
}
