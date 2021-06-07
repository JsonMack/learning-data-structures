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

    /**
     * Attempts to add a node to the tail end of the list. If the head is null, this value becomes
     * the head.
     *
     * @param value
     *            the value we want to add to the linked list. Duplicates can exists. This method should
     *            never fail. I won't say 'wont'. Oh.
     */
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

    /**
     * Removes the value from the linked list by stitching together the previous node, if any,
     * and the next node, if any. This effectively seek for the node, and rips it out of the
     * doubly linked list.
     *
     * @param value
     *            the value to remove, which may not exist.
     */
    public void remove(T value) {
        DoublyLinkedNode<T> node = find(head, value);

        if (node == null) {
            return;
        }
        removeNode(node);
    }

    /**
     * Removes the node from the linked list. This assumes that the node is not null and is
     * intended to only be used internally when this is the case since a node can be null.
     *
     * @param node
     *            the node to remove.
     */
    void removeNode(DoublyLinkedNode<T> node) {
        DoublyLinkedNode<T> previous = node.previous();

        DoublyLinkedNode<T> next = node.next();

        if (previous != null) {
            previous.setNext(next);
        }
        if (next != null) {
            next.setPrevious(previous);
        }
    }

    /**
     * Determines if the value can be found anywhere in the list, starting at the head
     * node.
     *
     * @param value
     *            the value we're determining exists or not.
     * @return true if the value exists, otherwise false.
     */
    public boolean contains(T value) {
        return find(head, value) != null;
    }

    /**
     * Compares the current nodes value against the parameter, and seeks to the next
     * node, if not null, comparing values again. This continues until next is null.
     *
     * @param node
     *            the current node we're comparing the value against. this may be null.
     * @param value
     *            the value we're seeking for, to check if it exists.
     * @return the node if it can be found, or null if it cannot.
     */
    private DoublyLinkedNode<T> find(DoublyLinkedNode<T> node, T value) {
        if (node == null) {
            return null;
        }
        if (node.value() == value) {
            return node;
        }
        return find(node.next(), value);
    }

    public int getSize() {
        return size;
    }

    @Override
    public ListIterator<T> iterator() {
        return new DoublyLinkedListIterator(head, size);
    }

    private final class DoublyLinkedListIterator implements ListIterator<T> {

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
            if (node == null) {
                throw new NoSuchElementException();
            }
            index--;
            removeNode(node);
            //TODO properly implement remove
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
