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
        if (value == null) {
            throw new IllegalArgumentException("Value is null.");
        }
        this.root = new BinaryTreeNode<T>(null, value);
    }

    public void add(T value) {
        insert(value, root);
    }

    /**
     * Inserts a new node with the given value at the next possible leaf from root. This
     * uses the {@link Object#hashCode()} function to determine a OH NO
     *
     * @param value
     * @param node
     */
    private void insertFrom(T value, BinaryTreeNode<T> node) {
        if (node == null) {
            throw new IllegalArgumentException("Node cannot be null.");
        }
        BinaryTreeNode<T> left = node.getLeft();

        BinaryTreeNode<T> right = node.getRight();

        int nodeHashCode = node.hashCode();

        int valueHashCode = value.hashCode();

        if (nodeHashCode == valueHashCode) {
            throw new BinaryTreeNodeExistsException();
        }
        if (valueHashCode < nodeHashCode) {
            if (left != null) {
                insert(value, left);
                return;
            }
            BinaryTreeNode<T> newNode = new BinaryTreeNode<>(node, value);

            node.setLeft(newNode);
        } else {
            if (right != null) {
                insert(value, right);
                return;
            }
            BinaryTreeNode<T> newNode = new BinaryTreeNode<>(node, value);

            node.setRight(newNode);
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
            BinaryTreeNode<T> left = next.left;

            if (left != null) {
                nodes.add(left);
            }
            BinaryTreeNode<T> right = next.right;

            if (right != null) {
                nodes.add(right);
            }
            return next.getValue();
        }

    }
}
