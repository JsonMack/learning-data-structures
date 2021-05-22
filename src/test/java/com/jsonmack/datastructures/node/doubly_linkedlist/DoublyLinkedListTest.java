package com.jsonmack.datastructures.node.doubly_linkedlist;

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

        ListIterator<DoublyLinkedNode<Integer>> iterator = doublyLinkedList.iterator();

        Queue<Integer> expected = IntStream.range(0, 4)
                .boxed()
                .collect(Collectors.toCollection(ArrayDeque::new));

        while (iterator.hasNext()) {
            DoublyLinkedNode<Integer> node = iterator.next();

            Assertions.assertEquals(expected.poll(), node.value());
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
    public void assertIteratorNextFailedIfSizeOne() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>(new DoublyLinkedNode<>(1));

        Assertions.assertThrows(NoSuchElementException.class, () -> list.iterator().next());
    }

    @Test
    public void assertIteratorPrevious() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        list.add(1);
        list.add(2);

        ListIterator<DoublyLinkedNode<Integer>> iterator = list.iterator();

        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertNotNull(iterator.next());
        Assertions.assertTrue(iterator.hasPrevious());
        Assertions.assertNotNull(iterator.previous());
    }

    private DoublyLinkedList<Integer> createFromRange(int fromInclusive, int toExclusive) {
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();

        IntStream.range(fromInclusive, toExclusive)
                .boxed()
                .forEachOrdered(doublyLinkedList::add);

        return doublyLinkedList;
    }


}
