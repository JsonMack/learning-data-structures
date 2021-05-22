package com.jsonmack.datastructures.node.singly_linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Jason MacKeigan
 */
public class SinglyLinkedList<T> implements Iterable<SinglyLinkedNode<T>> {

    private final SinglyLinkedNode<T> head;

    private SinglyLinkedNode<T> tail;

    private int size;

    public SinglyLinkedList(SinglyLinkedNode<T> head) {
        this.head = head;
        this.tail = head;
        this.size = 1;
    }

    public void add(T value) {
        SinglyLinkedNode<T> node = new SinglyLinkedNode<>();

        node.setValue(value);

        tail.setNext(node);
        tail = node;
        size++;
    }

    public SinglyLinkedNode<T> getHead() {
        return head;
    }

    public SinglyLinkedNode<T> getTail() {
        return tail;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<SinglyLinkedNode<T>> iterator() {
        return new SinglyLinkedListIterator<T>(head);
    }

    private static final class SinglyLinkedListIterator<T> implements Iterator<SinglyLinkedNode<T>> {

        private SinglyLinkedNode<T> current;

        private SinglyLinkedListIterator(SinglyLinkedNode<T> current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public SinglyLinkedNode<T> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            SinglyLinkedNode<T> next = current;

            current = current.next();

            return next;
        }
    }
}
