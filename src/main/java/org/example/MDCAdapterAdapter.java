package org.example;

import java.util.Deque;
import java.util.Map;
import org.apache.rocketmq.shaded.org.slf4j.spi.MDCAdapter;

public class MDCAdapterAdapter implements MDCAdapter {
    private final org.slf4j.spi.MDCAdapter delegate;

    public MDCAdapterAdapter(org.slf4j.spi.MDCAdapter delegate) {
        this.delegate = delegate;
    }

    @Override
    public void put(String key, String val) {
        delegate.put(key, val);
    }

    @Override
    public String get(String key) {
        return delegate.get(key);
    }

    @Override
    public void remove(String key) {
        delegate.remove(key);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public Map<String, String> getCopyOfContextMap() {
        return delegate.getCopyOfContextMap();
    }

    @Override
    public void setContextMap(Map<String, String> contextMap) {
        delegate.setContextMap(contextMap);
    }

    @Override
    public void pushByKey(String key, String value) {
        delegate.pushByKey(key, value);
    }

    @Override
    public String popByKey(String key) {
        return delegate.popByKey(key);
    }

    @Override
    public Deque<String> getCopyOfDequeByKey(String key) {
        return delegate.getCopyOfDequeByKey(key);
    }

    @Override
    public void clearDequeByKey(String key) {
        delegate.clearDequeByKey(key);
    }
}
