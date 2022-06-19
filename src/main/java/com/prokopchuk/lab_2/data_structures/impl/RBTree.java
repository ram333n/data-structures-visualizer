package com.prokopchuk.lab_2.data_structures.impl;

import com.prokopchuk.lab_2.data_structures.builders.impl.RBTreeNodeBuilder;
import com.prokopchuk.lab_2.data_structures.iterators.RBTreeIterator;
import com.prokopchuk.lab_2.data_structures.nodes.RBTreeNodeColor;
import com.prokopchuk.lab_2.data_structures.nodes.RBTreeNode;

import java.util.Iterator;

public class RBTree<T extends Comparable<T>> extends AbstractBinaryTree<T, RBTreeNode<T>> {
    public RBTree() {
        nilNode = new RBTreeNode<T>();
        nilNode.setColor(RBTreeNodeColor.BLACK);
        root = nilNode;
    }

    @Override
    protected RBTreeNode<T> createNodeToInsert(T value) {
        return new RBTreeNodeBuilder<T>().setValue(value).setColor(RBTreeNodeColor.RED).setLeft(nilNode).setRight(nilNode).build();
    }

    @Override
    protected void insertFixup(RBTreeNode<T> toInsert) {
        RBTreeNode<T> y;
        while(toInsert.getParent().getColor() == RBTreeNodeColor.RED) {
            if(toInsert.getParent() == toInsert.getParent().getParent().getLeft()) {
                y = toInsert.getParent().getParent().getRight();
                if(y.getColor() == RBTreeNodeColor.RED) {
                    toInsert.getParent().setColor(RBTreeNodeColor.BLACK);
                    y.setColor(RBTreeNodeColor.BLACK);
                    toInsert.getParent().getParent().setColor(RBTreeNodeColor.RED);
                    toInsert = toInsert.getParent().getParent();
                } else {
                    if(toInsert == toInsert.getParent().getRight()) {
                        toInsert = toInsert.getParent();
                        rotateLeft(toInsert);
                    }

                    toInsert.getParent().setColor(RBTreeNodeColor.BLACK);
                    toInsert.getParent().getParent().setColor(RBTreeNodeColor.RED);
                    rotateRight(toInsert.getParent().getParent());
                }
            } else {
                y = toInsert.getParent().getParent().getLeft();
                if(y.getColor() == RBTreeNodeColor.RED) {
                    toInsert.getParent().setColor(RBTreeNodeColor.BLACK);
                    y.setColor(RBTreeNodeColor.BLACK);
                    toInsert.getParent().getParent().setColor(RBTreeNodeColor.RED);
                    toInsert = toInsert.getParent().getParent();
                } else {
                    if(toInsert == toInsert.getParent().getLeft()) {
                        toInsert = toInsert.getParent();
                        rotateRight(toInsert);
                    }

                    toInsert.getParent().setColor(RBTreeNodeColor.BLACK);
                    toInsert.getParent().getParent().setColor(RBTreeNodeColor.RED);
                    rotateLeft(toInsert.getParent().getParent());
                }
            }
        }

        root.setColor(RBTreeNodeColor.BLACK);
    }

    private void deleteFixup(RBTreeNode<T> x) {
        RBTreeNode<T> w;
        while (x != root && x.getColor() == RBTreeNodeColor.BLACK) {
            if (x == x.getParent().getLeft()) {
                w = x.getParent().getRight();

                if (w.getColor() == RBTreeNodeColor.RED) {
                    w.setColor(RBTreeNodeColor.BLACK);
                    x.getParent().setColor(RBTreeNodeColor.RED);
                    rotateLeft(x.getParent());
                    w = x.getParent().getRight();
                }

                if (w.getLeft().getColor() == RBTreeNodeColor.BLACK && w.getRight().getColor() == RBTreeNodeColor.BLACK) {
                    w.setColor(RBTreeNodeColor.RED);
                    x = x.getParent();
                } else {
                    if (w.getRight().getColor() == RBTreeNodeColor.BLACK) {
                        w.getLeft().setColor(RBTreeNodeColor.BLACK);
                        w.setColor(RBTreeNodeColor.RED);
                        rotateRight(w);
                        w = x.getParent().getRight();
                    }

                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(RBTreeNodeColor.BLACK);
                    w.getRight().setColor(RBTreeNodeColor.BLACK);
                    rotateLeft(x.getParent());
                    x = root;
                }
            } else {
                w = x.getParent().getLeft();

                if (w.getColor() == RBTreeNodeColor.RED) {
                    w.setColor(RBTreeNodeColor.BLACK);
                    x.getParent().setColor(RBTreeNodeColor.RED);
                    rotateRight(x.getParent());
                    w = x.getParent().getLeft();
                }

                if (w.getRight().getColor() == RBTreeNodeColor.BLACK && w.getLeft().getColor() == RBTreeNodeColor.BLACK) {
                    w.setColor(RBTreeNodeColor.RED);
                    x = x.getParent();
                } else {
                    if (w.getLeft().getColor() == RBTreeNodeColor.BLACK) {
                        w.getRight().setColor(RBTreeNodeColor.BLACK);
                        w.setColor(RBTreeNodeColor.RED);
                        rotateLeft(w);
                        w = x.getParent().getLeft();
                    }

                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(RBTreeNodeColor.BLACK);
                    w.getLeft().setColor(RBTreeNodeColor.BLACK);
                    rotateRight(x.getParent());
                    x = root;
                }
            }
        }
        x.setColor(RBTreeNodeColor.BLACK);
    }

    @Override
    public boolean delete(T value) {
        RBTreeNode<T> toRemove = find(value);

        if(toRemove == nilNode) {
            return false;
        }

        RBTreeNode<T> y = toRemove, x;
        RBTreeNodeColor yOriginalColor = y.getColor();

        if(toRemove.getLeft() == nilNode) {
            x = toRemove.getRight();
            transplant(toRemove, toRemove.getRight());
        } else if(toRemove.getRight() == nilNode) {
            x = toRemove.getLeft();
            transplant(toRemove, toRemove.getLeft());
        } else {
            y = toRemove.getRight().getLeftest();
            yOriginalColor = y.getColor();
            x = y.getRight();

            if(y.getParent() == toRemove) {
                x.setParent(y);
            } else {
                transplant(y, y.getRight());
                y.setRight(toRemove.getRight());
                y.getRight().setParent(y);
            }

            transplant(toRemove, y);
            y.setLeft(toRemove.getLeft());
            y.getLeft().setParent(y);
            y.setColor(toRemove.getColor());
        }

        if(yOriginalColor == RBTreeNodeColor.BLACK) {
            deleteFixup(x);
        }

        --length;
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new RBTreeIterator<T>(root.getLeftest());
    }
}
