package com.jsonmack.datastructures.singly_linkedlist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class NodeTest {

    @Test
    public void assertNode() {
        Node<Integer> head = new Node<>(0);

        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>(head);

        List<Integer> expected = IntStream.range(1, 10)
                .sorted()
                .boxed()
                .collect(Collectors.toList());

        expected.forEach(singlyLinkedList::add);

        Assertions.assertEquals(head, singlyLinkedList.getHead());
        Assertions.assertEquals(0, head.getValue());
        Assertions.assertEquals(10, singlyLinkedList.getSize());
        Assertions.assertNull(singlyLinkedList.getTail().getNext());
        Assertions.assertEquals(9, singlyLinkedList.getTail().getValue());
    }

}
