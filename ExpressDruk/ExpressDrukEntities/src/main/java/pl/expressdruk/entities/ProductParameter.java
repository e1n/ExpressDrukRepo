package pl.expressdruk.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author e1n
 */
@Entity
@Table(name="product_parameter", uniqueConstraints=@UniqueConstraint(name="product_parameter_name_column_unique_idx", columnNames="name"))
@Access(AccessType.PROPERTY)
public class ProductParameter implements Serializable {

    private int id;
    private String name;
    private Set<ProductParameterValue> productParameterValues;

    public ProductParameter() {
    }

    public ProductParameter(String name) {
        this.name = name;
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
    
    @Basic(fetch= FetchType.EAGER, optional=false)
    @Column(name="name", nullable=false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy="productParameter", cascade= CascadeType.ALL, orphanRemoval=true)
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
        final ProductParameter other = (ProductParameter) obj;
        if (this.getId() != other.getId()) {
            return false;
        }
        if ((this.getName() == null) ? (other.getName() != null) : !this.getName().equals(other.getName())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.getId();
        hash = 73 * hash + (this.getName() != null ? this.getName().hashCode() : 0);
        return hash;
    }
    
    
    
}
