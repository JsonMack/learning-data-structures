package com.jsonmack.datastructures.binary_tree;

import java.util.*;

/**
 * The rules are as follows;
 *
 * If the new nodes value is lower than the current node, go to the left child
 * If the new nodes value is greater than the current node, go to the right child
 * If the current node is null, then we're at a 'leaf node', so we insert the new node in that position
 *
 * @author Jason MacKeigan
 */
public class BinaryTree<T> implements Iterable<T> {

    private final BinaryTreeNode<T> root;

    public BinaryTree(T value) {
        this.root = new BinaryTreeNode<T>(null, value);
    }

    public void add(T value) {
        insertFrom(value, root);
    }

    /**
     * Inserts a new node with the given value at the next possible leaf from root. This
     * uses the {@link Object#hashCode()} function and collisions are disregarded as the
     * objects being equal.
     *
     * @param value
     *            TODO
     * @param node
     *            TODO
     */
    private void insertFrom(T value, BinaryTreeNode<T> node) {
        if (node == null) {
            throw new IllegalArgumentException("Node cannot be null.");
        }
        int nodeHashCode = node.getValue().hashCode();

        int valueHashCode = value.hashCode();

        if (nodeHashCode == valueHashCode) {
            throw new BinaryTreeNodeExistsException();
        }
        if (valueHashCode < nodeHashCode) {
            BinaryTreeNode<T> left = node.getLeft();

            if (left != null) {
                insertFrom(value, left);
                return;
            }
            node.setLeft(new BinaryTreeNode<>(node, value));
        } else {
            BinaryTreeNode<T> right = node.getRight();

            if (right != null) {
                insertFrom(value, right);
                return;
            }
            node.setRight(new BinaryTreeNode<>(node, value));
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator<>(root);
    }

    /**
     * Iterates over the binary tree, starting at the root node. This maintains a queue with
     * FIFO ordering. If the node has a left, that value is added first, then the value of the
     * right node. I believe this is referred to as breadth-first traversal or level order traversal,
     * don't quote me though, uh oh.
     *
     * @param <T>
     *      the type of element that we will be iterating over.
     */
    private static final class BinaryTreeIterator<T> implements Iterator<T> {

        private final Queue<BinaryTreeNode<T>> nodes;

        public BinaryTreeIterator(BinaryTreeNode<T> node) {
            this.nodes = new ArrayDeque<>(Collections.singleton(node));
        }

        @Override
        public boolean hasNext() {
            return !nodes.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            BinaryTreeNode<T> next = nodes.poll();

            if (next == null) {
                throw new NoSuchElementException();
            }
            BinaryTreeNode<T> left = next.getLeft();

            if (left != null) {
                nodes.add(left);
            }
            BinaryTreeNode<T> right = next.getRight();

            if (right != null) {
                nodes.add(right);
            }
            return next.getValue();
        }

    }
}
