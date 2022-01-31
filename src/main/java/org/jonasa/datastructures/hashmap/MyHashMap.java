package org.jonasa.datastructures.hashmap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class MyHashMap<T, U> {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    List<LinkedList<Entry<T, U>>> entries;

    public MyHashMap() {
        this.entries = new ArrayList<>(DEFAULT_INITIAL_CAPACITY);
        for (int i = 0; i < DEFAULT_INITIAL_CAPACITY; i++) {
            entries.add(new LinkedList<>());
        }
    }

    // TODO - check capacity and add private expansion method
    public Value<U> put(T key, U value) {
        Key<T> entryKey = new Key<>(key);
        Value<U> entryValue = new Value<>(value);

        int index = size() % entryKey.hashCode();
        LinkedList<Entry<T, U>> list = entries.get(index);

        for (Entry<T, U> entry : list) {
            if (Objects.equals(entryKey, entry.getKey())) {
                Value<U> returnValue = entry.getValue();
                entry.setValue(entryValue);
                return returnValue;
            }
        }
        list.add(new Entry<>(entryKey, entryValue));
        return null;
    }

    public int size() {
        return entries.size();
    }
}
