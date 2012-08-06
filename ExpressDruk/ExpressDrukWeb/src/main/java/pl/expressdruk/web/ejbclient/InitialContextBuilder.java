
package pl.expressdruk.web.ejbclient;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import pl.expressdruk.web.ejbclient.exceptions.InitialContextBuilderException;

/**
 *
 * @author e1n
 */
class InitialContextBuilder {

    private final Properties ctxParams = new Properties();
    
    {
        ctxParams.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        ctxParams.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
        ctxParams.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
    }
    
    public InitialContextBuilder setHostIPAddress(String ip) {
        ctxParams.setProperty("org.omg.CORBA.ORBInitialHost", ip);
        return this;
    }
    
    public InitialContextBuilder setHostPort(String port) {
        ctxParams.setProperty("org.omg.CORBA.ORBInitialPort", port);
        return this;
    }
    
    private boolean isHostMissing() {
        return ctxParams.getProperty("org.omg.CORBA.ORBInitialHost", null) == null;
    }
    
    private boolean isPortMissing() {
        return ctxParams.getProperty("org.omg.CORBA.ORBInitialPort", null) == null;
    }
        

    public InitialContext build() throws InitialContextBuilderException {
        if (isHostMissing() || isPortMissing()) {
            throw new InitialContextBuilderException("Host and port settings must be passed to builder !");
        }
        try {
            InitialContext ic = new InitialContext(ctxParams);
            return ic;
        } catch (NamingException ex) {
            throw new InitialContextBuilderException(ctxParams.toString());
        }     
    }    
        
}
