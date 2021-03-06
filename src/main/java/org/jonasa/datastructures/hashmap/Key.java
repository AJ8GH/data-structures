package org.jonasa.datastructures.hashmap;

import java.util.Objects;

public class Key<T> {
    private final T key;
    private final int hashCode;

    public Key(T key) {
        this.key = key;
        this.hashCode = Objects.hash(key);
    }

    public T getKey() {
        return key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Key)) return false;
        Key<?> that = (Key<?>) o;
        return Objects.equals(key, that.getKey());
    }

    @Override
    public int hashCode() {
        return hashCode;
    }
}
