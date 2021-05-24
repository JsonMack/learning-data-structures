package com.jsonmack.datastructures.tree.binary_tree;

import java.util.function.Consumer;

/**
 * The rules are as follows;
 *
 * If the new nodes value is lower than the current node, go to the left child
 * If the new nodes value is greater than the current node, go to the right child
 * If the current node is null, then we're at a 'leaf node', so we insert the new node in that position
 * @author Jason MacKeigan
 */
public class BinaryTree<T> {

    private BinaryTreeNode<T> root;

    public void add(T value) {
        if (root == null) {
            root = new BinaryTreeNode<>(null, value);
            return;
        }
        addIfAbsent(value, root);
    }

    public void traverse(Consumer<T> action) {

    }

    /**
     * @param value
     *            the value to be added, if it does not exist.
     * @param node
     *            the node to start from when searching for the same, or a new leaf node.
     * @return the next leaf node, or an exception is thrown if one doesn't exist. If one cannot be found, then we assume that
     *         the size of this tree is 2^32-1 which is unrealistic, right? Might need to check for this. Learn about
     *         height/size of tree?
     */
    private void addIfAbsent(T value, BinaryTreeNode<T> node) {
        BinaryTreeNode<T> left = node.getLeft();

        BinaryTreeNode<T> right = node.getRight();

        int nodeHashCode = node.hashCode();

        int valueHashCode = value.hashCode();

        if (nodeHashCode == valueHashCode) {
            throw new BinaryTreeNodeExistsException();
        }
        if (valueHashCode < nodeHashCode) {
            if (left != null) {
                addIfAbsent(value, left);
                return;
            }
            BinaryTreeNode<T> newNode = new BinaryTreeNode<>(node, value);

            node.setLeft(newNode);
        } else {
            if (right != null) {
                addIfAbsent(value, right);
                return;
            }
            BinaryTreeNode<T> newNode = new BinaryTreeNode<>(node, value);

            node.setRight(newNode);
        }
        throw new IllegalStateException("Unable to add to tree.");
    }

}
