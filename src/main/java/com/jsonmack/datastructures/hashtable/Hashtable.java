package com.jsonmack.datastructures.hashtable;

import com.jsonmack.datastructures.support.FixedSizeList;
import com.jsonmack.datastructures.doubly_linkedlist.DoublyLinkedList;

import java.util.Iterator;
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
    public Hashtable(int bucketSize) {
        this.bucketSize = bucketSize;
        this.buckets = new FixedSizeList<>(bucketSize);
    }

    public Hashtable() {
        this(16);
    }

    /**
     * Inserts a new key and the associated value, or updates the value if the key exists. If the key exists,
     * the value is updated. If the key and value are the same, i.e equals, then no update is made.
     *
     * @param key
     *            the key that is unique and what is associated with the value.
     * @param value
     *            the value that is not unique and associated with the key.
     * @throws IllegalArgumentException
     *            thrown if the key or value is null.
     * @return The value returned is either the new value, or the existing value if the values are equal.
     *
     */
    //TODO add remove functionality to DoublyLinkedList ListIterator implementation so that
    //     we can ensure that HashtableEntry can remain immutable, allowing the existing value
    //     to be updated by replacing the entire entry instead of mutating it. This is more expensive
    //     with memory and performance, but the tradeoff is immutability so i'll take that loss.
    public V put(K key, V value) {
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

        if (bucket == null) {
            bucket = new DoublyLinkedList<>(new HashtableEntry<>(key, value));
            buckets.set(bucketIndex, bucket);
            return value;
        }
        Iterator<HashtableEntry<K, V>> iterator = bucket.iterator();

        while (iterator.hasNext()) {
            HashtableEntry<K, V> entry = iterator.next();

            if (!entry.getKey().equals(key)) {
                continue;
            }

        }
        for (HashtableEntry<K, V> existing : bucket) {
            if (!existing.getKey().equals(key)) {
                continue;
            }
            if (existing.getValue().equals(value)) {
                return value;
            }

        }
        bucket.add();
        return true;
    }

    /**
     * Retrieves the value for the given key, if one exists. If one does not exist, null is returned.
     *
     * @param key
     *            the unique key for a given value.
     * @throws IllegalArgumentException
     *            thrown if the key passed is null.
     * @return the value for the key, or null if the key does not exist.
     */
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
