package com.jsonmack.datastructures.binary_tree;

import java.util.*;

/**
 * This binary tree implementation was pretty off the cuff, like most structures i'm learning at the moment. The concept
 * behind this implementation is that it relies on the {@link Object#hashCode()} function heavily to determine equivalency.
 * That means that the {@link T} type of object being stored must either implement the equals hashcode contract or assume
 * that all instances are unique to some degree. This is not collision free as there are likely collisions with only 2^32-1
 * possible combinations for {@link Object#hashCode()}.
 *
 * <br><br>
 * The rules are as follows;
 * <ul>
 *     <li>The binary tree must have a root node.</li>
 *     <li>Nodes cannot have null values.</li>
 *     <li>Nodes can have at most 2 children, and at minimum 0 children.</li>
 *     <li>This doesn't use insertion between nodes, it uses the next available leaf.</li>
 *     <li>If the value of hashcode is less than the value of the hashcode at this node, go left, otherwise, go right.</li>
 *     <li>No node can exist if they have the same hashcode.</li>
 * </ul>
 *
 * @author Jason MacKeigan
 */
public class BinaryTree<T> implements Iterable<T> {

    /**
     * The root of the tree, this never should never be instantiated twice. This represents
     * the top or root of the binary tree. A root by default has a null parent, which is what
     * separates this node from other {@link BinaryTreeNode} children.
     */
    private final BinaryTreeNode<T> root;

    /**
     * Creates a new tree with an initial root node with the given value. The parent is null.
     *
     * @param value
     *            the value for the root node.
     */
    public BinaryTree(T value) {
        this.root = new BinaryTreeNode<T>(null, value);
    }

    /**
     * Adds a new value to the next open node, starting from the right. This only works if the value
     * does not exist. If it does, a new {@link BinaryTreeNodeExistsException} is thrown. The value
     * cannot be null.
     *
     * @param value
     *            the value we're trying to add, if it does not exist.
     */
    public void add(T value) {
        insertFrom(value, root);
    }

    /**
     * Determines if the value exists in this tree. This relies on the {@link Object#equals(Object)} ()} function
     * to check for equivalency.
     * 
     * @param value
     *            the value we're determining exists in this tree or not.
     * @return {@code true} if the node can be found using the {@link #containsAt(Object, BinaryTreeNode)} function. This
     *         starts at the root, goes to the lef, then the right. I think this is technically an in-order traversal search
     *         but I could be horribly wrong.
     */
    public boolean contains(T value) {
        return containsAt(value, root);
    }

    /**
     * Determines if the value exists at the given node. The hashcode value of the node is compared
     * to the hashcode of the passed value parameter. This the node is null, return false. If the
     * node is {@link Object#equals(Object)} to the other node, then true is returned. If not, then
     * this is called <b>recursively</b> on the left or right node, if they exist.
     *
     * @param value
     *            the value we're check exists or not.
     * @param node
     *            the node we're comparing the value of against.
     * @return {@code true} the value is equal to the other.
     */
    private boolean containsAt(T value, BinaryTreeNode<T> node) {
        if (node == null) {
            return false;
        }
        T nodeValue = node.getValue();

        if (nodeValue.equals(value)) {
            return true;
        }
        int valueHashCode = value.hashCode();

        int nodeHashCode = node.getValue().hashCode();

        return containsAt(value, valueHashCode < nodeHashCode ? node.getLeft() : node.getRight());
    }

    /**
     * Inserts a new node with the given value at the next possible leaf from root. This
     * uses the {@link Object#hashCode()} function and collisions are disregarded as the
     * objects being equal.
     *
     * @param value
     *            The value we're inserting into the tree.
     * @param node
     *            The current node we're starting to attempt to insert from.
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
