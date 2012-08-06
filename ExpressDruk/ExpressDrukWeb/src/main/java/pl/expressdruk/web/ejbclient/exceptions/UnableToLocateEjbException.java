package pl.expressdruk.web.ejbclient.exceptions;

/**
 *
 * @author e1n
 */
public class UnableToLocateEjbException extends Exception {

    public UnableToLocateEjbException(Throwable thrwbl) {
        super(thrwbl);
    }

    public UnableToLocateEjbException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public UnableToLocateEjbException(String string) {
        super(string);
    }

    public UnableToLocateEjbException() {
    }
    
}
