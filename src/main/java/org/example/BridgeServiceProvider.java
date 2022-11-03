package org.example;

import java.lang.reflect.Method;
import org.apache.rocketmq.shaded.org.slf4j.ILoggerFactory;
import org.apache.rocketmq.shaded.org.slf4j.IMarkerFactory;
import org.apache.rocketmq.shaded.org.slf4j.spi.MDCAdapter;
import org.apache.rocketmq.shaded.org.slf4j.spi.SLF4JServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BridgeServiceProvider implements SLF4JServiceProvider {

    private static final Logger logger = LoggerFactory.getLogger(BridgeServiceProvider.class);

    private static org.slf4j.spi.SLF4JServiceProvider findServiceProvider() {
        logger.debug("Try to find unshaded SLF4j service provider.");
        try {
            final Method method = LoggerFactory.class.getDeclaredMethod("getProvider");
            method.setAccessible(true);
            final org.slf4j.spi.SLF4JServiceProvider provider =
                (org.slf4j.spi.SLF4JServiceProvider) method.invoke(null);
            logger.debug("Found unshaded SLF4j service provider.");
            return provider;
        } catch (Throwable t) {
            // Should never reach here.
            throw new RuntimeException("Failed to find unshaded SLF4j service provider.", t);
        }
    }

    public ILoggerFactory getLoggerFactory() {
        return new ILoggerFactoryAdapter(findServiceProvider().getLoggerFactory());
    }

    public IMarkerFactory getMarkerFactory() {
        return new IMarkerFactoryAdapter(findServiceProvider().getMarkerFactory());
    }

    public MDCAdapter getMDCAdapter() {
        return new MDCAdapterAdapter(findServiceProvider().getMDCAdapter());
    }

    public String getRequestedApiVersion() {
        return findServiceProvider().getRequestedApiVersion();
    }

    public void initialize() {
        findServiceProvider().initialize();
    }
}
