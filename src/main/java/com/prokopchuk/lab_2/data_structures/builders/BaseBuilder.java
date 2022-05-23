package com.prokopchuk.lab_2.data_structures.builders;

import com.prokopchuk.lab_2.data_structures.nodes.BaseNode;

public abstract class BaseBuilder<T, Node extends BaseNode<T>> {
    
    protected Node toBuild;


}
