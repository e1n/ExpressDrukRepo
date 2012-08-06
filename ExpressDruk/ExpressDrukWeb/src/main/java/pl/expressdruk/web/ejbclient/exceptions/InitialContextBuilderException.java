
package pl.expressdruk.web.ejbclient.exceptions;

/**
 *
 * @author e1n
 */
public class InitialContextBuilderException extends Exception {

    public InitialContextBuilderException(Throwable thrwbl) {
        super(thrwbl);
    }

    public InitialContextBuilderException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public InitialContextBuilderException(String string) {
        super(string);
    }

    public InitialContextBuilderException() {
    }
    
}
