package com.jsonmack.datastructures.singly_linkedlist;

/**
 * @author Jason MacKeigan
 *
 * Not thread safe.
 */
public class Node<T> {

    private Node<T> next;

    private T value;

    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }

    public Node(T value) {
        this(value, null);
    }

    public Node() {
        this(null, null);
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }
}
