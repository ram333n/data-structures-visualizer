package com.prokopchuk.lab_2.ui;

import com.prokopchuk.lab_2.data_structures.impl.DataStructure;
import com.prokopchuk.lab_2.data_structures.nodes.AbstractBinaryTreeNode;
import javafx.geometry.Dimension2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light;

import java.util.Collections;
import java.util.LinkedList;

public class CanvasPrinter {
    private Canvas canvas;
    private DataStructure<Integer> structure;
    private int nodeSize = 50;
    private LinkedList<Dimension2D> nodesPoints = new LinkedList<>();
    private LinkedList<Dimension2D[]> edgesPoints = new LinkedList<>();

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
        //draw nodes, using circles ...

        if(!node.getLeft().isLeaf()) {
            drawTreeNode(node.getLeft(), xStart, x, point, curLevel + 1);
        }

        if(!node.getRight().isLeaf()) {
            drawTreeNode(node.getRight(), x, xEnd, point, curLevel + 1);
        }
    }

    private void drawTree() {
        //TODO : change (int)
        //drawTreeNode((AbstractBinaryTreeNode) structure.getStartNode(), 0, (int)canvas.getWidth(), null, 0);
    }


    public void setStructure(DataStructure<Integer> structure) {
        this.structure = structure;
    }
}
