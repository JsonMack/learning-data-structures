package com.jsonmack.datastructures.node;

/**
 * @author Jason MacKeigan
 */
public interface BidirectionalNode<T> extends Node<T> {

    Node<T> previous();

}
