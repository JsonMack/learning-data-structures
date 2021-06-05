package com.jsonmack.datastructures;

import java.util.*;

/**
 * @author Jason MacKeigan
 */
public class FixedSizeList<E> implements List<E> {

    private final List<E> elements;

    private final int size;

    public FixedSizeList(int size) {
        this.elements = new ArrayList<>(size);
        this.size = size;

        for (int i = 0; i < size; i++) {
            elements.add(null);
        }
    }

    @Override
    public E get(int index) {
        return elements.get(index);
    }

    @Override
    public E set(int index, E element) {
        return elements.set(index, element);
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException("Unsupported operation, list is a fixed size.");
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("Unsupported operation, list is a fixed size.");
    }

    @Override
    public int indexOf(Object o) {
        return elements.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return elements.lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return Collections.unmodifiableList(elements).listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return Collections.unmodifiableList(elements).listIterator(index);
    }


    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return elements.subList(fromIndex, toIndex);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return elements.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return Collections.unmodifiableList(elements).iterator();
    }

    @Override
    public Object[] toArray() {
        return elements.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return elements.toArray(a);
    }

    @Override
    public boolean add(E e) {
        throw new UnsupportedOperationException("Unsupported operation, list is a fixed size.");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Unsupported operation, list is a fixed size.");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return elements.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Unsupported operation, list is a fixed size.");
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Unsupported operation, list is a fixed size.");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Unsupported operation, list is a fixed size.");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Unsupported operation, list is a fixed size.");
    }

    @Override
    public void clear() {

    }
}
