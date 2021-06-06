package com.jsonmack.datastructures.support;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jason MacKeigan
 */
class FixedSizeListTest {

    @Test
    public void assertCannotBeNegativeSize() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new FixedSizeList<>(-1));
    }

    @Test
    public void assertSizeZeroPermitted() {
        Assertions.assertDoesNotThrow(() -> new FixedSizeList<>(0));
    }

    @Test
    public void assertAddProhibited() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> new FixedSizeList<>(1).add(1));
    }

    @Test
    public void assertRemoveProhibited() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> new FixedSizeList<>(1).remove(1));
    }

    @Test
    public void assertIteratorRemoveProhibited() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> new FixedSizeList<>(1).iterator().remove());
    }

    @Test
    public void assertSizeExpected() {
        Assertions.assertEquals(1, new FixedSizeList<>(1).size());
    }

}
