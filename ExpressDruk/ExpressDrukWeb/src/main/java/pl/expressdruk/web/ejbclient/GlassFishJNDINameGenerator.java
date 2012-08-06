package pl.expressdruk.web.ejbclient;

import com.google.common.base.Joiner;
import static com.google.common.base.Preconditions.*;
import org.apache.wicket.util.string.Strings;

/**
 *
 * @author e1n
 */
class GlassFishJNDINameGenerator implements JNDINameGenerator {
//    "java:global/MyNewsService/KeyValueCacheEJB!pl.e1n.tutorial.ejb.remote.KeyValueCacheEJBRemote";
//     java:global[/<app-name>]/<module-name>/<bean-name>!<fully-qualified-interface-name>

    private String appName;
    private String moduleName;
    private String beanName;

    private GlassFishJNDINameGenerator() {
    }

    public static GlassFishJNDINameGenerator forAppAndModuleNames(String appName, String moduleName) {
        checkNotNull(appName, "App name can't be null !");
        checkNotNull(moduleName, "Module name can't be null !");
        
        GlassFishJNDINameGenerator jNDINameGenerator = new GlassFishJNDINameGenerator();
        jNDINameGenerator.appName = appName;
        jNDINameGenerator.moduleName = moduleName;

        return jNDINameGenerator;
    }

    public static GlassFishJNDINameGenerator forModuleAndBeanNames(String moduleName) {
        checkNotNull(moduleName, "Module name can't be null !");
        
        GlassFishJNDINameGenerator jNDINameGenerator = new GlassFishJNDINameGenerator();
        jNDINameGenerator.moduleName = moduleName;
        
        return jNDINameGenerator;
    }
    
    @Override
    public <T> String getForClass(Class<T> ejbIface) {
        checkNotNull(ejbIface, "ejb interface can't be null !");
        checkArgument(ejbIface.isInterface(), "ejb interface is not interface but class !");
        checkArgument(ejbIface.getSimpleName().endsWith("Local") || ejbIface.getSimpleName().endsWith("Remote"),
                "ejb interface name must end with Local or Remote");
        
        this.beanName = extractBeanName(ejbIface);
        
        return getCompleteJNDIUrl(ejbIface.getName());
    }
    
    private <T> String extractBeanName(Class<T> ejbIface) {
        if (ejbIface.getSimpleName().endsWith("Local")) {
            return Strings.stripEnding(ejbIface.getSimpleName(), "Local");
        }
        return Strings.stripEnding(ejbIface.getSimpleName(), "Remote");
    }

    private String getCompleteJNDIUrl(String ejbIfaceName) {
        return Joiner.on("!").skipNulls().join(getJNDIUrlBase(), ejbIfaceName);
    }

    private String getJNDIUrlBase() {
        return Joiner.on("/").skipNulls().join("java:global", appName, moduleName, beanName);
    }
}
