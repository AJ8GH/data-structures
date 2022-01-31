package org.jonasa.datastructures.hashmap;

import java.util.Objects;

public class Entry<T, U> {
    private final Key<T> key;
    private Value<U> value;

    public Entry(Key<T> key, Value<U> value) {
        this.key = key;
        this.value = value;
    }

    public Key<T> getKey() {
        return key;
    }

    public Value<U> getValue() {
        return value;
    }

    public void setValue(Value<U> value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entry)) return false;
        Entry<?, ?> that = (Entry<?, ?>) o;
        return Objects.equals(getKey(), (that.getKey()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
