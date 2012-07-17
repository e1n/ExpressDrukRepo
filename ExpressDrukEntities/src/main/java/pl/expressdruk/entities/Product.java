package pl.expressdruk.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.persistence.*;

/**
 *
 * @author e1n
 */
@Access(AccessType.PROPERTY)
@Entity
@Table(name="product", uniqueConstraints=@UniqueConstraint(name="product_name_col_unique_idx",columnNames="name"))
public class Product implements Serializable {
    
    private int id;
    private String name;
    private String description;
    private int version;
    private Map<ProductParameter,ParamAssignedToProductWithValues> assignedParamsValues;

    public Product() {
    }

    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    @Lob
    @Basic(fetch= FetchType.EAGER, optional=true)
    @Column(name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    
    @Version
    public int getVersion() {
        return version;
    }
    
    @OneToMany(mappedBy="product")
    @MapKey(name="productParameter")
    public Map<ProductParameter, ParamAssignedToProductWithValues> getAssignedParamsValues() {
        return assignedParamsValues;
    }

    public void setAssignedParamsValues(Map<ProductParameter, ParamAssignedToProductWithValues> assignedParamsValues) {
        this.assignedParamsValues = assignedParamsValues;
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.getId() != other.getId()) {
            return false;
        }
        if ((this.getName() == null) ? (other.getName() != null) : !this.getName().equals(other.getName())) {
            return false;
        }
        if ((this.getDescription() == null) ? (other.getDescription() != null) : !this.getDescription().equals(other.getDescription())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.getId();
        hash = 29 * hash + (this.getName() != null ? this.getName().hashCode() : 0);
        hash = 29 * hash + (this.getDescription() != null ? this.getDescription().hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + getId() + ", name=" + getName() + ", description=" + getDescription() + '}';
    }
    
}
