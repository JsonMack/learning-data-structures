package com.jsonmack.datastructures.doubly_linkedlist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Jason MacKeigan
 */
class DoublyLinkedListTest {

    @Test
    public void assertAdd() {
        DoublyLinkedList<Integer> doublyLinkedList = createFromRange(0, 4);

        Assertions.assertEquals(4, doublyLinkedList.getSize());
    }

    @Test
    public void assertIterator() {
        DoublyLinkedList<Integer> doublyLinkedList = createFromRange(0, 4);

        ListIterator<Integer> iterator = doublyLinkedList.iterator();

        Queue<Integer> expected = IntStream.range(0, 4)
                .boxed()
                .collect(Collectors.toCollection(ArrayDeque::new));

        while (iterator.hasNext()) {
            Integer node = iterator.next();

            Assertions.assertEquals(expected.poll(), node);
        }
    }

    @Test
    public void assertIteratorNextFailedIfEmpty() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        Assertions.assertThrows(NoSuchElementException.class, () -> list.iterator().next());
    }

    @Test
    public void assertIteratorPreviousFailedIfEmpty() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        Assertions.assertThrows(NoSuchElementException.class, () -> list.iterator().previous());
    }

    @Test
    public void assertIteratorSizeOneNextValid() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>(1);

        Assertions.assertEquals(1, list.iterator().next());
    }

    @Test
    public void assertIteratorPrevious() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        list.add(1);
        list.add(2);

        ListIterator<Integer> iterator = list.iterator();

        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertNotNull(iterator.next());
        Assertions.assertTrue(iterator.hasPrevious());
        Assertions.assertNotNull(iterator.previous());
    }

    @Test
    public void assertContains() {
        Assertions.assertTrue(new DoublyLinkedList<>(1).contains(1));
    }

    @Test
    public void assertDoesntContain() {
        Assertions.assertFalse(new DoublyLinkedList<>(1).contains(2));
    }

    @Test
    public void assertRemoveExists() {
        Assertions.assertTrue(new DoublyLinkedList<>(1).remove(1));
    }

    @Test
    public void assertRemoveNonExistent() {
        Assertions.assertFalse(new DoublyLinkedList<>().remove(1));
    }

    private DoublyLinkedList<Integer> createFromRange(int fromInclusive, int toExclusive) {
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();

        IntStream.range(fromInclusive, toExclusive)
                .boxed()
                .forEachOrdered(doublyLinkedList::add);

        return doublyLinkedList;
    }


}
