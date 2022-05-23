package com.prokopchuk.lab_2.data_structures.nodes;

public class ListNode<T> extends BaseNode<T> {
    protected ListNode<T> next;

    public ListNode<T> getNext() {
        return next;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }
}
