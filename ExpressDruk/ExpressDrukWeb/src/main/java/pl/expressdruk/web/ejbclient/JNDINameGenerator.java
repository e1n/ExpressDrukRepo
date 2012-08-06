package pl.expressdruk.web.ejbclient;

/**
 *
 * @author e1n
 */
public interface JNDINameGenerator
{

    public <T> String getForClass(Class<T> ejbClassOrInterface);

}
