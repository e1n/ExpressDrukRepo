package pl.expressdruk.web.ejbclient;

import static com.google.common.base.Preconditions.checkNotNull;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import pl.expressdruk.web.ejbclient.exceptions.UnableToLocateEjbException;

/**
 *
 * @author e1n
 */
public abstract class EjbLocator<T> {

    private InitialContext ctx;
    private JNDINameGenerator jndiNameGenerator;

    public EjbLocator(InitialContext ctx, JNDINameGenerator jndiNameGenerator) {
        checkNotNull(ctx, "InitialContext can't be null !");
        checkNotNull(jndiNameGenerator, "JNDINameGenerator can't be null !");
        this.ctx = ctx;
        this.jndiNameGenerator = jndiNameGenerator;
    }

    private String getJNDIName(Class<T> beanIface) {
        return jndiNameGenerator.getForClass(beanIface);
    }

    public T locate(Class<T> beanIface) throws UnableToLocateEjbException {
        try {
            return (T) ctx.lookup(getJNDIName(beanIface));
        } catch (NamingException ex) {
            throw new UnableToLocateEjbException(String.format("Can't find ejb for name: %s", getJNDIName(beanIface)), ex);
        }
    }
}
