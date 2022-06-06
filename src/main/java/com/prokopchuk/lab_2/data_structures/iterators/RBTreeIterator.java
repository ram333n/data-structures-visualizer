package com.prokopchuk.lab_2.data_structures.iterators;

import com.prokopchuk.lab_2.data_structures.nodes.RBTreeNode;

public class RBTreeIterator<T> extends AbstractBinaryTreeIterator<T, RBTreeNode<T>> {
    RBTreeIterator(RBTreeNode<T> node) {
        super(node);
    }
}
