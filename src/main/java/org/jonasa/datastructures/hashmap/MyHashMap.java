package org.jonasa.datastructures.hashmap;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class MyHashMap<T, U> {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private List<List<Entry<T, U>>> entries;
    private int size = 0;

    public MyHashMap() {
        this.entries = new ArrayList<>(DEFAULT_INITIAL_CAPACITY);
        for (int i = 0; i < DEFAULT_INITIAL_CAPACITY; i++) {
            entries.add(new LinkedList<>());
        }
    }

    public int size() {
        return size;
    }

    public U put(T key, U value) {
        log.info("PUTTING ...");
        Key<T> entryKey = new Key<>(key);
        Value<U> entryValue = new Value<>(value);
        int index = entryKey.hashCode() % (capacity() - 1);
        log.info("INDEX = hashcode % capacity - 1 ({} % {}) = {}", entryKey.hashCode(), (capacity() - 1), index);
        List<Entry<T, U>> entryList = entries.get(index);

        for (Entry<T, U> entry : entryList) {
            if (Objects.equals(entryKey, entry.getKey())) {
                Value<U> oldValue = entry.getValue();
                entry.setValue(entryValue);
                log.info("FOUND EXISTING VALUE {}, REPLACING...", oldValue);
                return oldValue.getValue();
            }
        }
        if (++size >= capacity() * 0.75) resize();
        Entry<T, U> entry = new Entry<>(entryKey, entryValue);
        log.info("ENTRY HASH {}", entry.hashCode());

        entryList.add(new Entry<>(entryKey, entryValue));
        log.info("NO EXISTING VALUE FOUND, PUT COMPLETE");
        return null;
    }

    public U get(T key) {
        log.info("GETTING...");
        log.info("INDEX = hashcode % capacity ({} % {}) = {}", Objects.hash(key), (capacity() - 1), (Objects.hash(key) % capacity()));
        List<Entry<T, U>> list = entries.get(Objects.hash(key) % (capacity() - 1));

        for (Entry<T, U> entry : list) {
            if (entry.getKey().equals(new Key<T>(key))) {
                return entry.getValue().getValue();
            }
        }
        return null;
    }

    public U remove(T key) {
        List<Entry<T, U>> list = entries.get(Objects.hash(key) % (capacity() - 1));
        for (Entry<T, U> entry : list) {
            if (entry.getKey().equals(new Key<T>(key))) {
                Value<U> oldValue = entry.getValue();
                list.remove(entry);
                size--;
                return oldValue.getValue();
            }
        }
        return null;
    }

    public boolean containsKey(T key) {
        Key<T> entryKey = new Key<>(key);
        List<Entry<T, U>> list = entries.get(entryKey.hashCode() % (capacity() - 1));
        for (Entry<T, U> entry : list) {
            if (entry.getKey().equals(key)) return true;
        }
        return false;
    }

    private void resize() {
        int newCapacity = capacity() * 2 + 1;
        log.info("RESIZING capacity from {} to {}...", capacity(), newCapacity);
        List<List<Entry<T, U>>> newEntryList = new ArrayList<>(newCapacity);
        for (int i = 0; i < newCapacity; i++) {
            newEntryList.add(new LinkedList<>());
        }
        entries.forEach(list -> list.forEach(entry -> newEntryList
                .get(entry.hashCode() % (newCapacity - 1)).add(entry)));

        this.entries = newEntryList;
    }

    private int capacity() {
        return entries.size();
    }
}
