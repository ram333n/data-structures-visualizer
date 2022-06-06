package com.prokopchuk.lab_2.data_structures.iterators;

import com.prokopchuk.lab_2.data_structures.nodes.BSTreeNode;

public class BSTreeIterator<T> extends AbstractBinaryTreeIterator<T, BSTreeNode<T>> {
    BSTreeIterator(BSTreeNode<T> node) {
        super(node);
    }
}
