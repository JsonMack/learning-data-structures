package com.jsonmack.datastructures.singly_linkedlist;

import com.jsonmack.datastructures.doubly_linkedlist.Node;

/**
 * @author Jason MacKeigan
 *
 * Not thread safe.
 */
public class SinglyLinkedNode<T> implements Node<T> {

    private SinglyLinkedNode<T> next;

    private T value;

    public SinglyLinkedNode(T value, SinglyLinkedNode<T> next) {
        this.value = value;
        this.next = next;
    }

    public SinglyLinkedNode(T value) {
        this(value, null);
    }

    public SinglyLinkedNode() {
        this(null, null);
    }

    public void setNext(SinglyLinkedNode<T> next) {
        this.next = next;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public SinglyLinkedNode<T> next() {
        return next;
    }

    @Override
    public T value() {
        return value;
    }
}
