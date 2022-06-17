package com.prokopchuk.lab_2.ui;

import com.prokopchuk.lab_2.data_structures.impl.DataStructure;
import com.prokopchuk.lab_2.ui.visitors.DrawData;
import com.prokopchuk.lab_2.ui.visitors.DrawVisitor;
import javafx.geometry.Dimension2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class CanvasPrinter<T> {
    private Canvas canvas;
    private GraphicsContext gc;
    private DrawVisitor<T> visitor;

    public CanvasPrinter(Canvas canvas) {
        this.canvas = canvas;
        this.visitor = new DrawVisitor<>(canvas.getWidth(), canvas.getHeight());
        this.gc = canvas.getGraphicsContext2D();
    }

    public void clearCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void printEdges() {
        for(Dimension2D[] edge : visitor.getEdgesPoints()) {
            gc.beginPath();
            gc.moveTo(edge[0].getWidth(), edge[0].getHeight());
            gc.lineTo(edge[1].getWidth(), edge[1].getHeight());
            gc.stroke();
        }
    }

    private void printNodes() {
        final double nodeSize = visitor.getNodeSize();
        double fontWidth = nodeSize / 4;
        Font font = new Font(nodeSize / 2);
        System.out.println(font.getSize());
        gc.setFont(font);

        for(DrawData<T> drawData : visitor.getNodesData()) {
            Dimension2D centerPoint = drawData.getCenterPoint();
            String text = drawData.getValue().toString();

            gc.setFill(drawData.getDrawNodeColor());
            gc.fillOval(centerPoint.getWidth() - nodeSize / 2, centerPoint.getHeight() - nodeSize / 2, nodeSize, nodeSize);
            gc.setFill(Color.YELLOWGREEN);

            double offset = (nodeSize - (4 - text.length()) * fontWidth) / 2 - nodeSize / 2;
            System.out.println(nodeSize);
            System.out.println(offset);
            gc.fillText(text, centerPoint.getWidth() + offset, centerPoint.getHeight() + nodeSize / 4, nodeSize);
        }
    }

    public void draw() {
        printEdges();
        printNodes();
    }

    public void setStructure(DataStructure<T> structure) {
        structure.visit(visitor);
        draw();
    }
}
