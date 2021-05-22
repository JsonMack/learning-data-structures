package com.jsonmack.datastructures.node.doubly_linkedlist;

import com.jsonmack.datastructures.node.BidirectionalNode;

/**
 * @author Jason MacKeigan
 */
public class DoublyLinkedNode<T> implements BidirectionalNode<T> {

    private DoublyLinkedNode<T> previous;

    private DoublyLinkedNode<T> next;

    private T value;

    public DoublyLinkedNode(DoublyLinkedNode<T> previous, DoublyLinkedNode<T> next, T value) {
        this.previous = previous;
        this.next = next;
        this.value = value;
    }

    public DoublyLinkedNode(T value) {
        this(null, null, value);
    }

    public void setPrevious(DoublyLinkedNode<T> previous) {
        this.previous = previous;
    }

    public void setNext(DoublyLinkedNode<T> next) {
        this.next = next;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public DoublyLinkedNode<T> previous() {
        return previous;
    }

    @Override
    public T value() {
        return value;
    }

    @Override
    public DoublyLinkedNode<T> next() {
        return next;
    }

}
