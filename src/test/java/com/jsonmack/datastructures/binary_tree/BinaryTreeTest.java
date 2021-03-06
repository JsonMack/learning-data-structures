package com.jsonmack.datastructures.binary_tree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Jason MacKeigan
 */
class BinaryTreeTest {

    @Test
    public void assertNullRootFailed() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new BinaryTree<>(null));
    }

    @Test
    public void assertDistinct() {
        BinaryTree<Integer> tree = new BinaryTree<>(0);

        Assertions.assertThrows(BinaryTreeNodeExistsException.class, () -> tree.add(0));
    }

    @Test
    public void assertIteratorOrder() {
        BinaryTree<Integer> tree = new BinaryTree<>(2);

        Queue<Integer> expectedOrder = IntStream.of(2, 1, 4, 5, 7)
                .boxed()
                .collect(Collectors.toCollection(ArrayDeque::new));

        IntStream.of(1, 4, 5, 7).forEach(tree::add);

        for (Integer integer : tree) {
            Assertions.assertEquals(expectedOrder.poll(), integer);
        }
    }

    @Test
    public void assertContainsExpected() {
        BinaryTree<Integer> tree = new BinaryTree<>(1);

        Queue<Integer> expectedOrder = IntStream.range(2, 5)
                .boxed()
                .collect(Collectors.toCollection(ArrayDeque::new));

        IntStream.range(2, 5)
                .forEach(tree::add);

        for (int expected : expectedOrder) {
            Assertions.assertTrue(tree.contains(expected));
        }
    }

    @Test
    public void assertDoesNotContainRoot() {
        Assertions.assertFalse(new BinaryTree<>(1).contains(2));
    }

    @Test
    public void assertDoesContainValueAtRoot() {
        Assertions.assertTrue(new BinaryTree<>(0).contains(0));
    }

}
