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

}
