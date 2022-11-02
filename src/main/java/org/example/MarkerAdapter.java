package org.example;

import java.util.Iterator;
import org.apache.rocketmq.shaded.org.slf4j.Marker;

public class MarkerAdapter implements Marker {
    final org.slf4j.Marker delegate;

    public MarkerAdapter(org.slf4j.Marker delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getName() {
        return delegate.getName();
    }

    @Override
    public void add(Marker reference) {
        final MarkerAdapter marker = (MarkerAdapter) reference;
        delegate.add(marker.delegate);
    }

    @Override
    public boolean remove(Marker reference) {
        final MarkerAdapter marker = (MarkerAdapter) reference;
        return delegate.remove(marker.delegate);
    }

    @Override
    public boolean hasChildren() {
        return delegate.hasReferences();
    }

    @Override
    public boolean hasReferences() {
        return delegate.hasReferences();
    }

    @Override
    public Iterator<Marker> iterator() {
        final Iterator<org.slf4j.Marker> iterator = delegate.iterator();
        return new Iterator<Marker>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Marker next() {
                return new MarkerAdapter(iterator.next());
            }

            @Override
            public void remove() {
                iterator.remove();
            }
        };
    }

    @Override
    public boolean contains(Marker other) {
        final MarkerAdapter marker = (MarkerAdapter) other;
        return delegate.contains(marker.delegate);
    }

    @Override
    public boolean contains(String name) {
        return delegate.contains(name);
    }
}
