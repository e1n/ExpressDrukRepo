package pl.expressdruk.entities.app;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import pl.expressdruk.entities.ParamAssignedToProductWithValues;
import pl.expressdruk.entities.Product;
import pl.expressdruk.entities.ProductParameter;
import pl.expressdruk.entities.ProductParameterValue;

/**
 *
 * @author e1n
 */
public class App {
    public static void main(String[] args) {
        EntityManagerFactory entitFactory = Persistence.createEntityManagerFactory("openjpa");
        EntityManager em = entitFactory.createEntityManager();
        
        
        Product p1 = createProduct("P2", "21desc");
        ProductParameter pp1 = createProdParam("papier");
        ProductParameter pp2 = createProdParam("format");
        EntityTransaction trans = em.getTransaction();
        
        trans.begin();
        
        em.persist(p1);
        em.persist(pp1);
        p1 = em.merge(p1);
        pp1 = em.merge(pp1);
        em.persist(pp2);

        trans.commit();
        
        ProductParameterValue ppv1 = createProductParameterValue("dupa", pp1);
        ProductParameterValue ppv2 = createProductParameterValue("dupa2", pp1);
        
        ProductParameterValue ppv3 = createProductParameterValue("cyc1", pp2);
        ProductParameterValue ppv4 = createProductParameterValue("cyc2", pp2);
        
        em.getTransaction().begin();
        em.persist(ppv1);
        em.persist(ppv2);
        em.persist(ppv3);
        em.persist(ppv4);
        em.getTransaction().commit();
        
        
        em.getTransaction().begin();
        em.refresh(pp1);
        for (ProductParameterValue val : pp1.getProductParameterValues()) {
            System.out.println(val.getValue());
        }
        em.getTransaction().commit();
        
        
        em.getTransaction().begin();
        em.refresh(p1);
        em.refresh(pp1);
        em.refresh(pp2);
        em.refresh(ppv3);
        
        ParamAssignedToProductWithValues patpwv = new ParamAssignedToProductWithValues(pp1, p1, pp1.getProductParameterValues());
        ParamAssignedToProductWithValues patpwv1 = new ParamAssignedToProductWithValues(pp2, p1, Sets.newHashSet(ppv3));
        
        em.persist(patpwv);
        em.persist(patpwv1);
        
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        em.refresh(p1);
        Map<ProductParameter, ParamAssignedToProductWithValues> assignedParamValues = p1.getAssignedParamsValues();
        for (ProductParameter key : assignedParamValues.keySet()) {
            System.out.println("key: " + key.getName());
            
            for (ProductParameterValue val : assignedParamValues.get(key).getProductParameterValues()) {
                System.out.println("value: " + val.getValue());
            }
        }
        em.getTransaction().commit();
        
        em.close();
        entitFactory.close();
    }
    
    public static Product createProduct(String name, String desc) {
        return new Product(name, desc);
    }
    public static ProductParameter createProdParam(String name) {
        return new ProductParameter(name);
    }
    public static ProductParameterValue createProductParameterValue(String value, ProductParameter pp) {
        return new ProductParameterValue(pp, value);
    }
}
