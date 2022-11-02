package org.example;

public class ILoggerFactoryAdapter implements org.apache.rocketmq.shaded.org.slf4j.ILoggerFactory {
    private final org.slf4j.ILoggerFactory delegate;

    public ILoggerFactoryAdapter(org.slf4j.ILoggerFactory delegate) {
        this.delegate = delegate;
    }

    @Override
    public org.apache.rocketmq.shaded.org.slf4j.Logger getLogger(String name) {
        return new LoggerAdapter(delegate.getLogger(name));
    }
}
