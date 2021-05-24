package com.jsonmack.datastructures.binary_tree;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * The rules are as follows;
 *
 * If the new nodes value is lower than the current node, go to the left child
 * If the new nodes value is greater than the current node, go to the right child
 * If the current node is null, then we're at a 'leaf node', so we insert the new node in that position
 *
 * @author Jason MacKeigan
 */
public class BinaryTree<T> {

    private BinaryTreeNode<T> root;

    public void add(T value) {
        if (root == null) {
            root = new BinaryTreeNode<>(null, value);
            return;
        }
        insert(value, root);
    }

    private void insert(T value, BinaryTreeNode<T> node) {
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
        throw new IllegalStateException("Unable to add to tree.");
    }
}
