package com.jsonmack.datastructures.node.singly_linkedlist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class SinglyLinkedListTest {

    //TODO break this up into multiple tests that are not dependent on each other
    //TODO think critically about edge cases
    @Test
    public void assertNode() {
        SinglyLinkedNode<Integer> head = new SinglyLinkedNode<>(0);

        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>(head);

        List<Integer> expected = IntStream.range(1, 10)
                .sorted()
                .boxed()
                .collect(Collectors.toList());

        expected.forEach(singlyLinkedList::add);

        Assertions.assertEquals(head, singlyLinkedList.getHead());
        Assertions.assertEquals(0, head.value());
        Assertions.assertEquals(10, singlyLinkedList.getSize());
        Assertions.assertNull(singlyLinkedList.getTail().next());
        Assertions.assertEquals(9, singlyLinkedList.getTail().value());
    }

}
