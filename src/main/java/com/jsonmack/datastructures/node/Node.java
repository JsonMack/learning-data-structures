package com.jsonmack.datastructures.node;

/**
 * @author Jason MacKeigan
 */
public interface Node<T> {

    T value();

    Node<T> next();

}
