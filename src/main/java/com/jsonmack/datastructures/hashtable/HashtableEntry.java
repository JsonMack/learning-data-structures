package com.jsonmack.datastructures.hashtable;

import java.util.Objects;

/**
 * @author Jason MacKeigan
 */
public class HashtableEntry<K, V> {

    private final K key;

    private final V value;

    private final int hashCode;

    public HashtableEntry(K key, V value) {
        this.key = key;
        this.value = value;
        this.hashCode = Objects.hash(key, value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof HashtableEntry) {
            HashtableEntry<?, ?> otherEntry = (HashtableEntry<?, ?>) other;

            return key.equals(otherEntry.key) && value.equals(otherEntry.value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
