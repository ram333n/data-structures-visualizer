package com.prokopchuk.lab_2.ui.visitors;

import com.prokopchuk.lab_2.data_structures.nodes.AbstractBinaryTreeNode;
import com.prokopchuk.lab_2.data_structures.nodes.BaseNode;
import com.prokopchuk.lab_2.data_structures.nodes.ListNode;
import javafx.geometry.Dimension2D;

import java.util.LinkedList;

public class DrawVisitor<T> implements IVisitor<T>{
    protected LinkedList<Dimension2D> nodesPoints = new LinkedList<>();
    protected LinkedList<Dimension2D[]> edgesPoints = new LinkedList<>();
    protected int width;
    protected int height;
    protected int nodeSize;

    public DrawVisitor(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public LinkedList<Dimension2D> getNodesPoints() {
        return nodesPoints;
    }

    public LinkedList<Dimension2D[]> getEdgesPoints() {
        return edgesPoints;
    }

    @Override
    public void visitTree(AbstractBinaryTreeNode startNode) {
        drawTreeNode(startNode, 0, width, null, 0);
    }

    @Override
    public void visitList(ListNode<T> startNode) {
        //TODO : implement it;
    }

    @Override
    public void calculateTreeNodeSize(int length) {
        nodeSize = (int) Math.min(height / length, width / Math.pow(2, length));
    }

    @Override
    public void calculateListNodeSize(int length) {
        //TODO : implement it;
    }

    private void drawTreeNode(AbstractBinaryTreeNode node, int xStart, int xEnd, Dimension2D parentPoint, int curLevel) {
        if(node.isLeaf()) {
            return;
        }

        int x = (xEnd - xStart) / 2 + xStart;
        int y = curLevel * nodeSize + nodeSize / 2;
        Dimension2D point = new Dimension2D(x, y);

        if(parentPoint != null) {
            edgesPoints.add(new Dimension2D[]{parentPoint, point});
        }

        nodesPoints.add(point);

        if(!node.getLeft().isLeaf()) {
            drawTreeNode(node.getLeft(), xStart, x, point, curLevel + 1);
        }

        if(!node.getRight().isLeaf()) {
            drawTreeNode(node.getRight(), x, xEnd, point, curLevel + 1);
        }
    }

}
