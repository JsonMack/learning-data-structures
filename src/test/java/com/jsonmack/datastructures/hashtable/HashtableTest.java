package com.jsonmack.datastructures.hashtable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Jason MacKeigan
 */
public class HashtableTest {

    @Test
    public void assertPutDuplicateFailed() {
        Hashtable<Integer, Integer> table = new Hashtable<>();

        Assertions.assertTrue(table.put(1, 1));
        Assertions.assertFalse(table.put(1, 1));
    }

    @Test
    public void assertPutUniqueSuccessful() {
        Hashtable<Integer, Integer> table = new Hashtable<>();

        Assertions.assertTrue(table.put(1, 1));
        Assertions.assertTrue(table.put(2, 1));
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
