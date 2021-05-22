package com.jsonmack.datastructures.node.doubly_linkedlist;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * @author Jason MacKeigan
 */
public class DoublyLinkedList<T> implements Iterable<DoublyLinkedNode<T>> {

    private DoublyLinkedNode<T> head;

    private DoublyLinkedNode<T> tail;

    private int size;

    public DoublyLinkedList(DoublyLinkedNode<T> head) {
        this.head = head;
        this.tail = head;
        this.size = 1;
    }

    public void add(T value) {
        DoublyLinkedNode<T> node = new DoublyLinkedNode<>(value);

        tail.setNext(node);
        tail = node;
        size++;
    }

    @Override
    public ListIterator<DoublyLinkedNode<T>> iterator() {
        return new DoublyLinkedListIterator<>(head, size);
    }

    private static final class DoublyLinkedListIterator<T> implements ListIterator<DoublyLinkedNode<T>> {

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
        public DoublyLinkedNode<T> next() {
            DoublyLinkedNode<T> next = node;

            node = node.next();
            index++;

            return next;
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public DoublyLinkedNode<T> previous() {
            DoublyLinkedNode<T> previous = node;

            node = node.previous();
            index--;

            return previous;
        }

        @Override
        public int nextIndex() {
            return index + 1;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        @Override
        public void set(DoublyLinkedNode<T> tDoublyLinkedNode) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(DoublyLinkedNode<T> tDoublyLinkedNode) {
            throw new UnsupportedOperationException();
        }


    }
}
