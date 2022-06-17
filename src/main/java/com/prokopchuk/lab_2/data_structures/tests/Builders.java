package com.prokopchuk.lab_2.data_structures.tests;

import com.prokopchuk.lab_2.data_structures.builders.impl.BSTreeNodeBuilder;
import com.prokopchuk.lab_2.data_structures.builders.impl.ListNodeBuilder;
import com.prokopchuk.lab_2.data_structures.builders.impl.RBTreeNodeBuilder;
import com.prokopchuk.lab_2.data_structures.nodes.BSTreeNode;
import com.prokopchuk.lab_2.data_structures.nodes.RBTreeNodeColor;
import com.prokopchuk.lab_2.data_structures.nodes.ListNode;
import com.prokopchuk.lab_2.data_structures.nodes.RBTreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuilderTests {
    @Test
    void testListNodeBuilder() {
        ListNode<Integer> first = new ListNodeBuilder<Integer>().setValue(42).build();
        ListNode<Integer> head = new ListNodeBuilder<Integer>().setValue(0).setNext(first).build();

        assertEquals(42, first.getValue());
        assertEquals(0, head.getValue());
        assertEquals(head.getNext(), first);
    }

    @Test
    void testBSTreeNodeBuilder() {
        BSTreeNode<Integer> left = new BSTreeNodeBuilder<Integer>().setValue(-100).build();
        BSTreeNode<Integer> right = new BSTreeNodeBuilder<Integer>().setValue(200).build();
        BSTreeNode<Integer> parent = new BSTreeNodeBuilder<Integer>().setValue(1000).build();
        BSTreeNode<Integer> center = new BSTreeNodeBuilder<Integer>().setValue(0).setRight(right).setLeft(left).setParent(parent).build();

        assertEquals(left, center.getLeft());
        assertEquals(right, center.getRight());
        assertEquals(parent, center.getParent());

        assertEquals(-100, left.getValue());
        assertEquals(200, right.getValue());
        assertEquals(1000, parent.getValue());
        assertEquals(0, center.getValue());
    }

    @Test
    void testRBTreeNodeBuilder() {
        RBTreeNode<Integer> left = new RBTreeNodeBuilder<Integer>().setValue(-100).setColor(RBTreeNodeColor.BLACK).build();
        RBTreeNode<Integer> right = new RBTreeNodeBuilder<Integer>().setValue(200).setColor(RBTreeNodeColor.RED).build();
        RBTreeNode<Integer> parent = new RBTreeNodeBuilder<Integer>().setValue(1000).setColor(RBTreeNodeColor.RED).build();
        RBTreeNode<Integer> center = new RBTreeNodeBuilder<Integer>().setValue(0).setRight(right).setLeft(left).setParent(parent).setColor(RBTreeNodeColor.BLACK).build();

        assertEquals(left, center.getLeft());
        assertEquals(right, center.getRight());
        assertEquals(parent, center.getParent());

        assertEquals(-100, left.getValue());
        assertEquals(200, right.getValue());
        assertEquals(1000, parent.getValue());
        assertEquals(0, center.getValue());

        assertEquals(RBTreeNodeColor.BLACK, left.getColor());
        assertEquals(RBTreeNodeColor.RED, right.getColor());
        assertEquals(RBTreeNodeColor.RED, parent.getColor());
        assertEquals(RBTreeNodeColor.BLACK, center.getColor());
    }
}