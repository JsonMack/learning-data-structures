package com.jsonmack.datastructures.hashtable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Jason MacKeigan
 */
public class HashtableTest {

    @Test
    public void assertPutSuccessful() {
        Hashtable<Integer, Integer> table = new Hashtable<>();

        Assertions.assertNotNull(table.put(1, 1));
        Assertions.assertEquals(1, table.getSize());
    }

    @Test
    public void assertPutDuplicateReturnsExisting() {
        Hashtable<Integer, Object> table = new Hashtable<>();

        Object value = new Object();

        Assertions.assertEquals(value, table.put(1, value));
        Assertions.assertEquals(value, table.put(1, value));
        Assertions.assertEquals(1,  table.getSize());
    }

    @Test
    public void assertPutUniqueSuccessful() {
        Hashtable<Integer, Integer> table = new Hashtable<>();

        Assertions.assertNotNull(table.put(1, 1));
        Assertions.assertNotNull(table.put(2, 1));
        Assertions.assertEquals(2, table.getSize());
    }

    @Test
    public void assertPutUpdate() {
        Hashtable<Integer, Object> table = new Hashtable<>();

        int key = 1;

        Object first = new Object();

        Object updated = new Object();

        Assertions.assertEquals(first, table.put(key, first));
        Assertions.assertEquals(updated, table.put(key, updated));
    }

    @Test
    public void assertGetExistingNotNull() {
        Hashtable<Integer, Integer> table = new Hashtable<>();

        table.put(1, 1);

        Assertions.assertNotNull(table.get(1));
    }

    @Test
    public void assertGetNonExistingFailed() {
        Assertions.assertNull(new Hashtable<Integer, Integer>().get(0));
    }

}
