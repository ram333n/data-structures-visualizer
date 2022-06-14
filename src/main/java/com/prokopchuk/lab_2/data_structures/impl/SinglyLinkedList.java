package com.prokopchuk.lab_2.data_structures.impl;

import com.prokopchuk.lab_2.data_structures.builders.impl.ListNodeBuilder;
import com.prokopchuk.lab_2.data_structures.iterators.ListIterator;
import com.prokopchuk.lab_2.data_structures.nodes.ListNode;

import java.util.Iterator;

public class SinglyLinkedList<T extends Comparable<T>> implements DataStructure<T> {
    private ListNode<T> head;

    @Override
    public void insert(T value) {
        ListNode<T> toInsert = new ListNodeBuilder<T>().setValue(value).setNext(head).build();
        head = toInsert;
    }

    @Override
    public boolean search(T value) {
        ListNode<T> current = head;
        while(current != null) {
            if(current.getValue().equals(value)) {
                return true;
            }
            current = current.getNext();
        }

        return false;
    }

    @Override
    public boolean delete(T value) {
        if(head != null && head.getValue().equals(value)) {
            head = head.getNext();
            return true;
        }

        ListNode<T> current = head, prev = null;
        while(current != null && !current.getValue().equals(value)) {
            prev = current;
            current = current.getNext();
        }

        if(current == null) {
            return false;
        }
        prev.setNext(current.getNext());
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<T>(head);
    }

    public ListNode<T> getHead() {
        return head;
    }
}
