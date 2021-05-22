package com.jsonmack.datastructures.node;

import java.text.Bidi;

/**
 * @author Jason MacKeigan
 */
public interface BidirectionalNode<T> extends Node<T> {

    BidirectionalNode<T> previous();

}
