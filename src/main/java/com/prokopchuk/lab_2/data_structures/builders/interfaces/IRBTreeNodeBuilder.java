package com.prokopchuk.lab_2.data_structures.builders.interfaces;

import com.prokopchuk.lab_2.data_structures.nodes.Color;
import com.prokopchuk.lab_2.data_structures.nodes.AbstractBinaryTreeNode;

public interface IRBTreeNodeBuilder<T, Node extends AbstractBinaryTreeNode<T, Node>, B> extends IBinaryTreeNodeBuilder<T, Node, IRBTreeNodeBuilder<T, Node, B>> {
    B setColor(Color color);
}
