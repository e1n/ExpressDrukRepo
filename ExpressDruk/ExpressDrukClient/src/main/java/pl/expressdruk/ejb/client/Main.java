package pl.expressdruk.ejb.client;

import javax.ejb.EJB;
import pl.expressdruk.ejb.remote.interfaces.ProductCrudOperationsBeanRemote;
import pl.expressdruk.entities.Product;

/**
 * Enterprise Application Client main class.
 *
 */
public class Main {
    
    @EJB
    private static ProductCrudOperationsBeanRemote productCrudBean;
    
    public static void main( String[] args ) {
        System.out.println( "Hello World I am e1n's Expressdruk Enterprise Application Client!");
        
        Product prod = new Product("O yeah !", "To kurcze pieczaczki dziala ! Expressdruk bedzie potentatem !");
        productCrudBean.persist(prod);
        System.out.println("Ok po logowaniu !");
    }
}
