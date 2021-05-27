package com.jsonmack.datastructures.binary_tree;

/**
 * @author Jason MacKeigan
 *
 * Contains a parent, a left child, and a right child. The parent, left, or right can be null. If all are null, or
 * parent is null, it is the root node.
 */
public class BinaryTreeNode<T> {

    /**
     * The parent of this node, if any. A node without a parent is the parent, or root, node.
     *
     * @return the parent or root node, or null if root node.
     */
    private final BinaryTreeNode<T> parent;

    /**
     * The value of this node. The hashcode of the object will be what determines its position in the tree.
     */
    private final T value;

    /**
     * The left child, which is 'less' than the right.
     */
    private BinaryTreeNode<T> left;

    /**
     * The right child, which is 'greater' than the left.
     */
    private BinaryTreeNode<T> right;

    public BinaryTreeNode(BinaryTreeNode<T> parent, T value) {
        this.parent = parent;
        this.value = value;
    }

    void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    public BinaryTreeNode<T> getParent() {
        return parent;
    }

    public T getValue() {
        return value;
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }
}
