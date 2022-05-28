package com.prokopchuk.lab_2.data_structures.builders.interfaces;

import com.prokopchuk.lab_2.data_structures.nodes.ListNode;

public interface IListNodeBuilder<T, Node extends ListNode<T>, B> extends IBaseBuilder<T, Node, IListNodeBuilder<T, Node, B>> {
    B setNext(Node next);
}
