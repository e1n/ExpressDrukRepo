
package pl.expressdruk.web.ejbclient;

import javax.naming.InitialContext;

/**
 *
 * @author e1n
 */
public class ExpressDrukEjbLocator<T> extends EjbLocator<T> {

    public ExpressDrukEjbLocator(InitialContext ctx, JNDINameGenerator jndiNameGenerator) {
        super(ctx, jndiNameGenerator);
    }

}
