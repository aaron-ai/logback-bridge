package org.example;

import java.lang.reflect.Method;
import org.apache.rocketmq.shaded.org.slf4j.ILoggerFactory;
import org.apache.rocketmq.shaded.org.slf4j.IMarkerFactory;
import org.apache.rocketmq.shaded.org.slf4j.spi.MDCAdapter;
import org.apache.rocketmq.shaded.org.slf4j.spi.SLF4JServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BridgeServiceProvider implements SLF4JServiceProvider {
    private static final Logger logger = LoggerFactory.getLogger(BridgeServiceProvider.class);

    private final org.slf4j.spi.SLF4JServiceProvider delegate;

    public BridgeServiceProvider() {
        logger.debug("Try to find unshaded SLF4j service provider.");
        try {
            final Method method = LoggerFactory.class.getDeclaredMethod("getProvider");
            method.setAccessible(true);
            this.delegate = (org.slf4j.spi.SLF4JServiceProvider) method.invoke(null);
            logger.debug("Found unshaded SLF4j service provider.");
        } catch (Throwable t) {
            // Should never reach here.
            throw new RuntimeException("Failed to find unshaded SLF4j service provider.", t);
        }

    }

    public ILoggerFactory getLoggerFactory() {
        return new ILoggerFactoryAdapter(delegate.getLoggerFactory());
    }

    public IMarkerFactory getMarkerFactory() {
        return new IMarkerFactoryAdapter(delegate.getMarkerFactory());
    }

    public MDCAdapter getMDCAdapter() {
        return new MDCAdapterAdapter(delegate.getMDCAdapter());
    }

    public String getRequestedApiVersion() {
        return delegate.getRequestedApiVersion();
    }

    public void initialize() {
        delegate.initialize();
    }
}
