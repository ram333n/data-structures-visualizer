package com.prokopchuk.lab_2.ui.visitors;

import com.prokopchuk.lab_2.data_structures.nodes.AbstractBinaryTreeNode;
import com.prokopchuk.lab_2.data_structures.nodes.ListNode;

public interface IVisitor<T> {
    void visitTree(AbstractBinaryTreeNode startNode);
    void visitList(ListNode<T> startNode);
    void calculateTreeNodeSize(int length);
    void calculateListNodeSize(int length);
}
