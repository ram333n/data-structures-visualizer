package com.prokopchuk.lab_2.data_structures.builders.interfaces;

import com.prokopchuk.lab_2.data_structures.nodes.AbstractBinaryTreeNode;

public interface IBinaryTreeNodeBuilder<T, Node extends AbstractBinaryTreeNode<T, Node>, B> extends IBaseBuilder<T, Node, IBinaryTreeNodeBuilder<T, Node, B>> {
    B setLeft(Node left);
    B setRight(Node right);
    B setParent(Node parent);
}
