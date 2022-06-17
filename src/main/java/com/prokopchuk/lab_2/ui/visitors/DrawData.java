package com.prokopchuk.lab_2.ui.visitors;

import com.prokopchuk.lab_2.ui.DrawNodeColor;
import javafx.geometry.Dimension2D;
import javafx.scene.paint.Color;

public class DrawData<T> {
    private Dimension2D centerPoint;
    private T value;
    private Color drawNodeColor;

    public DrawData(Dimension2D centerPoint, T value, Color drawNodeColor) {
        this.centerPoint = centerPoint;
        this.value = value;
        this.drawNodeColor = drawNodeColor;
    }

    public Dimension2D getCenterPoint() {
        return centerPoint;
    }

    public void setCenterPoint(Dimension2D centerPoint) {
        this.centerPoint = centerPoint;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Color getDrawNodeColor() {
        return drawNodeColor;
    }

    public void setDrawNodeColor(Color drawNodeColor) {
        this.drawNodeColor = drawNodeColor;
    }
}
