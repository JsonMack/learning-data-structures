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

    /**
     * Creates a new BinaryTreeNode object with a given parent and value. The parent
     * is either the root node, null if it is the parent, or some nth child of root.
     *
     * @param parent
     *            null for root, root, or child of root.
     * @param value
     *            the value associated with this node. This cannot be null.
     */
    public BinaryTreeNode(BinaryTreeNode<T> parent, T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null.");
        }
        this.parent = parent;
        this.value = value;
    }

    /**
     * Sets the left node for this node equal to the given parameter.
     *
     * @param left
     *            the value to be assigned to the left node.
     */
    void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    /**
     * Sets the right node for this node equal to the given parameter.
     *
     * @param right
     *            the value to be assigned to the right node.
     */
    void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    /**
     * The parent node, if any. This can be null if this is the root node.
     *
     * @return the parent node.
     */
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
