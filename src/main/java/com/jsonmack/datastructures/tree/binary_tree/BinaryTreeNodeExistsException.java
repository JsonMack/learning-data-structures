package com.jsonmack.datastructures.tree.binary_tree;

/**
 * @author Jason MacKeigan
 *
 * Thrown when a node already exists.
 */
public class BinaryTreeNodeExistsException extends RuntimeException {

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public BinaryTreeNodeExistsException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public BinaryTreeNodeExistsException(String message) {
        super(message);
    }

}
