package com.prokopchuk.lab_2.ui.visitors;

import com.prokopchuk.lab_2.data_structures.nodes.*;
import com.prokopchuk.lab_2.ui.DrawNodeColor;
import javafx.geometry.Dimension2D;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public class DrawVisitor<T> implements IVisitor<T>{
    protected LinkedList<DrawData<T>> nodesData = new LinkedList<>();
    protected LinkedList<Dimension2D[]> edgesPoints = new LinkedList<>();
    protected double width;
    protected double height;
    protected double nodeSize = 30;
    protected int length;

    public DrawVisitor(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void setLength(int length) {
        this.length = length;
    }

    public double getNodeSize() {
        return nodeSize;
    }

    public LinkedList<DrawData<T>> getNodesData() {
        return nodesData;
    }

    public LinkedList<Dimension2D[]> getEdgesPoints() {
        return edgesPoints;
    }

    @Override
    public void visitTree(AbstractBinaryTreeNode startNode) {
        drawTreeNodes(startNode, 0, width, null, 0);
    }

    @Override
    public void visitList(ListNode<T> startNode) {
        drawListNodes(startNode, null, 1);
    }

    @Override
    public void calculateTreeNodeSize() {
        nodeSize = Math.min(height / length, width / Math.pow(2, length));
        if(nodeSize < 30) {
            nodeSize = 30;
        }
    }

    @Override
    public void calculateListNodeSize() {
        nodeSize = width / length;
        if(nodeSize < 30) {
            nodeSize = 30;
        }
    }

    private void drawTreeNodes(AbstractBinaryTreeNode node, double xStart, double xEnd, Dimension2D parentPoint, int curLevel) {
        if(node.isLeaf()) {
            return;
        }

        double x = (xEnd - xStart) / 2 + xStart;
        double y = curLevel * nodeSize + nodeSize / 2;

        Color color;
        if(node instanceof BSTreeNode<?>) {
            color = Color.BLUE;
        } else {
            color = ((RBTreeNode<?>) node).getColor() == RBTreeNodeColor.BLACK ? Color.BLACK : Color.RED;
        }

        Dimension2D point = new Dimension2D(x, y);

        if(parentPoint != null) {
            edgesPoints.add(new Dimension2D[]{parentPoint, point});
        }

        DrawData<T> toPush = new DrawData<T>(point, (T) node.getValue(), color);
        nodesData.add(toPush);

//        if(!node.getLeft().isLeaf()) {
//            drawTreeNodes(node.getLeft(), xStart, x, point, curLevel + 1);
//        }
//
//        if(!node.getRight().isLeaf()) {
//            drawTreeNodes(node.getRight(), x, xEnd, point, curLevel + 1);
//        }

        drawTreeNodes(node.getLeft(), xStart, x, point, curLevel + 1);
        drawTreeNodes(node.getRight(), x, xEnd, point, curLevel + 1);
    }

    private void drawListNodes(ListNode<T> node, Dimension2D parentPoint, int nodeCounter) {
        if(node == null) {
            return;
        }

        double x = width * nodeCounter / length;
        double y = height / 2 + + nodeSize / 2;

        Color color = Color.YELLOW;

        Dimension2D point = new Dimension2D(x, y);

        if(parentPoint != null) {
            edgesPoints.add(new Dimension2D[]{parentPoint, point});
        }

        DrawData<T> toPush = new DrawData<>(point, node.getValue(), color);
        drawListNodes(node.getNext(), point, nodeCounter + 1);
    }
}
