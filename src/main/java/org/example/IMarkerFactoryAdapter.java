package org.example;

import org.apache.rocketmq.shaded.org.slf4j.IMarkerFactory;
import org.apache.rocketmq.shaded.org.slf4j.Marker;

public class IMarkerFactoryAdapter implements IMarkerFactory {
    private final org.slf4j.IMarkerFactory delegate;

    public IMarkerFactoryAdapter(org.slf4j.IMarkerFactory delegate) {
        this.delegate = delegate;
    }

    @Override
    public Marker getMarker(String name) {
        return new MarkerAdapter(delegate.getMarker(name));
    }

    @Override
    public boolean exists(String name) {
        return delegate.exists(name);
    }

    @Override
    public boolean detachMarker(String name) {
        return delegate.detachMarker(name);
    }

    @Override
    public Marker getDetachedMarker(String name) {
        return new MarkerAdapter(delegate.getDetachedMarker(name));
    }
}
