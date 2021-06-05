package com.jsonmack.datastructures.doubly_linkedlist;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author Jason MacKeigan
 */
public class DoublyLinkedList<T> implements Iterable<T> {

    private DoublyLinkedNode<T> head;

    private DoublyLinkedNode<T> tail;

    private int size;

    private DoublyLinkedList(DoublyLinkedNode<T> head, DoublyLinkedNode<T> tail, int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Size cannot be less than zero.");
        }
        this.head = head;
        this.tail = tail;
        this.size = size;
    }

    private DoublyLinkedList(DoublyLinkedNode<T> node) {
        this(node, node, 1);
    }

    public DoublyLinkedList() {
        this(null, null, 0);
    }

    public DoublyLinkedList(T value) {
        this(new DoublyLinkedNode<>(value));
    }

    public void add(T value) {
        DoublyLinkedNode<T> node = new DoublyLinkedNode<>(value);

        if (head == null) {
            head = node;
        } else if (size == 1) {
            head.setNext(node);
        }

        if (tail != null) {
            tail.setNext(node);
        }
        tail = node;
        size++;
    }

    public int getSize() {
        return size;
    }

    @Override
    public ListIterator<T> iterator() {
        return new DoublyLinkedListIterator<>(head, size);
    }

    private static final class DoublyLinkedListIterator<T> implements ListIterator<T> {

        private final int size;

        private DoublyLinkedNode<T> node;

        private int index;

        private DoublyLinkedListIterator(DoublyLinkedNode<T> node, int size) {
            this.node = node;
            this.size = size;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            DoublyLinkedNode<T> next = node;

            node = node.next();
            index++;
            return next.value();
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            DoublyLinkedNode<T> previous = node;

            node = node.previous();
            index--;
            return previous.value();
        }

        @Override
        public int nextIndex() {
            return Math.min(size, index + 1);
        }

        @Override
        public int previousIndex() {
            return Math.max(-1, index - 1);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        @Override
        public void set(T tDoublyLinkedNode) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(T tDoublyLinkedNode) {
            throw new UnsupportedOperationException();
        }

    }
}
