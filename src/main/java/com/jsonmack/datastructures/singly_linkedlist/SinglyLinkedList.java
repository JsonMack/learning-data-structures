package com.jsonmack.datastructures.singly_linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Jason MacKeigan
 */
public class SinglyLinkedList<T> implements Iterable<Node<T>> {

    private final Node<T> head;

    private Node<T> tail;

    private int size;

    public SinglyLinkedList(Node<T> head) {
        this.head = head;
        this.tail = head;
        this.size = 1;
    }

    public void add(T value) {
        Node<T> node = new Node<>();

        node.setValue(value);

        tail.setNext(node);
        tail = node;
        size++;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<Node<T>> iterator() {
        return new SinglyLinkedListIterator<T>(head);
    }

    private static final class SinglyLinkedListIterator<T> implements Iterator<Node<T>> {

        private Node<T> current;

        private SinglyLinkedListIterator(Node<T> current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Node<T> next() {
            if (current == null) {
                throw new NoSuchElementException("Could not find next element, something went wrong.");
            }
            Node<T> next = current;

            current = current.getNext();

            return next;
        }
    }
}
