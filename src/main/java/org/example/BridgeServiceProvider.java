package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import org.apache.rocketmq.shaded.org.slf4j.ILoggerFactory;
import org.apache.rocketmq.shaded.org.slf4j.IMarkerFactory;
import org.apache.rocketmq.shaded.org.slf4j.spi.MDCAdapter;
import org.apache.rocketmq.shaded.org.slf4j.spi.SLF4JServiceProvider;

public class BridgeServiceProvider implements SLF4JServiceProvider {
    private static List<org.slf4j.spi.SLF4JServiceProvider> findServiceProviders() {
        ServiceLoader<org.slf4j.spi.SLF4JServiceProvider> serviceLoader =
            ServiceLoader.load(org.slf4j.spi.SLF4JServiceProvider.class);
        List<org.slf4j.spi.SLF4JServiceProvider> providerList = new ArrayList<>();
        for (org.slf4j.spi.SLF4JServiceProvider provider : serviceLoader) {
            providerList.add(provider);
        }
        return providerList;
    }

    private static org.slf4j.spi.SLF4JServiceProvider findServiceProvider() {
        return findServiceProviders().get(0);
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
