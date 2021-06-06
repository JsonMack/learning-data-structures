package com.jsonmack.datastructures.hashtable;

import com.jsonmack.datastructures.support.FixedSizeList;
import com.jsonmack.datastructures.doubly_linkedlist.DoublyLinkedList;

import java.util.List;

/**
 * This structure stores key-value pairs by associating the key to a given bucket. The bucket
 * that the key belongs to is predictable, and that key should always point to the same bucket.
 * This can be done if the {@link K} object implements the equals/hashcode contract.
 *
 * I've written this to make use of my existing structure, {@link DoublyLinkedList<V>}. This will
 * allow us to keep a reference of the first, and last nodes. We can use the last to  compare the
 * next insert if size > 0. Why? No idea.
 *
 * @param <K>
 *           The type of object that will be stored as the key. This object must be unique and properly
 *           implement the {@link Object#equals(Object)} and {@link #hashCode()} functions.
 * @param <V>
 *           The type of object that will be stored in this Hashtable and used as the value. Unlike
 *           the key, this does not have to be unique.
 *
 * <b>Based on:</b>https://en.wikipedia.org/w/index.php?title=Hash_table&oldid=1023994339
 * @author Jason MacKeigan
 */
public class Hashtable<K, V> {

    /**
     * A collection of variable-sized {@link DoublyLinkedList} objects where we store
     * associated values.
     */
    private final List<DoublyLinkedList<HashtableEntry<K, V>>> buckets;

    /**
     * The size, or number of, buckets that exist. This is used to determine the size of the container
     * that holds each of the values.
     */
    private final int bucketSize;

    /**
     * Creates a new hash table with a given bucket size equal to that of the passed parameter. This determines
     * how many individual buckets there are.
     *
     * @param bucketSize
     *            the amount of buckets that should be available by default.
     */
    @SuppressWarnings("unchecked")
    public Hashtable(int bucketSize) {
        this.bucketSize = bucketSize;
        this.buckets = new FixedSizeList<>(bucketSize);
    }

    public Hashtable() {
        this(16);
    }

    public boolean put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null.");
        }
        if (value == null) {
            throw new IllegalArgumentException("Value is null.");
        }
        int bucketIndex = findBucketIndex(key);

        if (bucketIndex > buckets.size()) {
            throw new IllegalStateException("Bucket index exceeds bucket size.");
        }
        DoublyLinkedList<HashtableEntry<K, V>> bucket = buckets.get(bucketIndex);

        HashtableEntry<K, V> entry = new HashtableEntry<>(key, value);

        if (bucket == null) {
            bucket = new DoublyLinkedList<>(entry);
            buckets.set(bucketIndex, bucket);
            return true;
        }
        for (HashtableEntry<K, V> existing : bucket) {
            if (existing.equals(entry)) {
                return false;
            }
        }
        bucket.add(entry);
        return true;
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        int bucketIndex = findBucketIndex(key);

        DoublyLinkedList<HashtableEntry<K, V>> bucket = buckets.get(bucketIndex);

        if (bucket == null) {
            return null;
        }
        for (HashtableEntry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Calculates the bucket index based on the {@link Object#hashCode()} of the key. This is equal to
     * {@link Object#hashCode()} % {@link #bucketSize}.
     *
     * @param key
     *            the key for the associated value.
     * @return the index of the bucket in the {@link #buckets} list.
     */
    private int findBucketIndex(K key) {
        return Math.max(0, key.hashCode() % bucketSize);
    }


}
