package com.prokopchuk.lab_2.data_structures.tests;

import com.prokopchuk.lab_2.data_structures.factories.DataStructuresFactory;
import com.prokopchuk.lab_2.data_structures.impl.*;
import com.prokopchuk.lab_2.data_structures.nodes.Color;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class DataStructures {
    LinkedList<Integer> getTraversalData(DataStructure<Integer> dataStructure) {
        LinkedList<Integer> result = new LinkedList<>();
        Iterator<Integer> it = dataStructure.iterator();
        while(it.hasNext()) {
            result.add(it.next());
        }

        return result;
    }

    @Test
    void testIterators() {
        SinglyLinkedList<Integer> list = (SinglyLinkedList<Integer>) DataStructuresFactory.getInstance().<Integer>create(StructureType.SINGLY_LINKED_LIST);
        BSTree<Integer> bstree = (BSTree<Integer>) DataStructuresFactory.getInstance().<Integer>create(StructureType.BSTREE);
        RBTree<Integer> rbtree = (RBTree<Integer>) DataStructuresFactory.getInstance().<Integer>create(StructureType.RBTREE);

        list.insert(3);
        list.insert(1);
        list.insert(2);
        list.insert(4);
        list.insert(0);
        list.insert(5);

        LinkedList<Integer> expectedList = new LinkedList<>() {{add(5); add(0); add(4); add(2); add(1); add(3);}};
        assertEquals(expectedList, getTraversalData(list));
        Collections.sort(expectedList);

        for(Integer i : list) {
            bstree.insert(i);
            rbtree.insert(i);
        }

        assertEquals(expectedList, getTraversalData(bstree));
        assertEquals(expectedList, getTraversalData(rbtree));
    }

    @Test
    void testSinglyLinkedList() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        list.insert(4);
        list.insert(2);
        list.insert(1);
        list.insert(3);

        assertEquals(3, list.getHead().getValue());

        assertTrue(list.search(4));
        assertTrue(list.search(2));
        assertTrue(list.search(1));
        assertTrue(list.search(3));
        assertFalse(list.search(0));
        assertFalse(list.search(45));

        assertTrue(list.delete(2) && list.delete(3) && list.delete(4) && !list.delete(42));
        assertEquals(1, list.getHead().getValue());
        assertNull(list.getHead().getNext());
    }

    @Test
    void testBSTree() {
        BSTree<Integer> tree = new BSTree<>();

        tree.insert(5);
        tree.insert(2);
        tree.insert(10);
        tree.insert(1);
        tree.insert(3);
        tree.insert(7);
        tree.insert(15);

        assertTrue(tree.search(5) && tree.search(15) && tree.search(3) && !tree.search(22) && !tree.search(-42));

        assertTrue(tree.delete(15));
        assertTrue(tree.getRoot().getRight().getRight().isLeaf());

        assertTrue(tree.delete(10));
        assertEquals(7, tree.getRoot().getRight().getValue());

        assertTrue(tree.delete(2));
        assertNull(tree.getRoot().getLeft().getRight().getValue());
        assertEquals(3, tree.getRoot().getLeft().getValue());
        assertEquals(1, tree.getRoot().getLeft().getLeft().getValue());
    }

    @Test
    void testRBTreeInsert1() {
        RBTree<Integer> tree = new RBTree<>();

        tree.insert(5);
        tree.insert(2);
        tree.insert(10);
        tree.insert(1);
        tree.insert(3);
        tree.insert(7);
        tree.insert(15);
        tree.insert(16);

        assertEquals(Color.BLACK, tree.getRoot().getColor());
        assertEquals(Color.RED, tree.getRoot().getRight().getColor());
        assertEquals(Color.BLACK, tree.getRoot().getLeft().getColor());

        tree.insert(3);
        assertEquals(Color.RED, tree.getRoot().getLeft().getColor());
    }

    @Test
    void testRBTreeInsert2() {
        RBTree<Integer> tree = new RBTree<>();

        for(int i = 0; i < 10; ++i) {
            tree.insert(i);
        }

        assertEquals(Color.RED, tree.getRoot().getRight().getRight().getColor());
        assertEquals(3, tree.getRoot().getValue());
        assertEquals(Color.BLACK, tree.getRoot().getLeft().getColor());
        assertEquals(Color.BLACK, tree.getRoot().getRight().getColor());
    }

    @Test
    void testRBTreeDelete() {
        RBTree<Integer> tree = new RBTree<>();

        for(int i = 0; i < 10; ++i) {
            tree.insert(i);
        }

        for(int i = 1; i < 10; i *= 2) {
            assertTrue(tree.delete(i)); //1, 2, 4, 8
            assertEquals(Color.BLACK, tree.getRoot().getColor());
        }

        assertEquals(Color.BLACK, tree.getRoot().getLeft().getColor());
        assertEquals(Color.RED, tree.getRoot().getRight().getColor());
        assertEquals(Color.RED, tree.getRoot().getLeft().getLeft().getColor());
    }

    @Test
    void testUniqueDataStructure() {
        UniqueDataStructure<Integer> list = new UniqueDataStructure<>(StructureType.SINGLY_LINKED_LIST);
        UniqueDataStructure<Integer> bstree = new UniqueDataStructure<>(StructureType.BSTREE);
        UniqueDataStructure<Integer> rbtree = new UniqueDataStructure<>(StructureType.RBTREE);

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                list.insert(i);
                bstree.insert(i);
                rbtree.insert(i);
            }
        }

        LinkedList<Integer> listTraversal = new LinkedList<>();
        LinkedList<Integer> bstreeTraversal = new LinkedList<>();
        LinkedList<Integer> rbtreeTraversal = new LinkedList<>();
        LinkedList<Integer> expected = new LinkedList<>(){{add(2); add(1); add(0);}};

        for(Integer i : list) {
            listTraversal.add(i);
        }

        for(Integer i : bstree) {
            bstreeTraversal.add(i);
        }

        for(Integer i : rbtree) {
            rbtreeTraversal.add(i);
        }

        assertEquals(expected, listTraversal);
        Collections.reverse(listTraversal);
        assertTrue(listTraversal.equals(bstreeTraversal) && bstreeTraversal.equals(rbtreeTraversal));
    }
}
