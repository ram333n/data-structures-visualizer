package com.prokopchuk.lab_2.data_structures.tests;

import com.prokopchuk.lab_2.data_structures.impl.DataStructure;
import com.prokopchuk.lab_2.data_structures.impl.SinglyLinkedList;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;

public class DataStructures {
    LinkedList<Integer> getTraversalData(DataStructure<Integer> dataStructure) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        Iterator<Integer> it = dataStructure.iterator();
        while(it.hasNext()) {
            result.add(it.next());
        }

        return result;
    }

    @Test
    void testLinkedListIterator() {
        SinglyLinkedList<Integer> src = new SinglyLinkedList<Integer>();

        for(int i = 0; i < 5; ++i) {

        }
    }

}
