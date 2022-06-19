package com.prokopchuk.lab_2.ui.memento;

import com.prokopchuk.lab_2.ui.visitors.DrawData;
import javafx.geometry.Dimension2D;

import java.util.LinkedList;

public class Snapshot<T> {
    private LinkedList<DrawData<T>> nodesData;
    private LinkedList<Dimension2D[]> edgesPoints;

    public Snapshot(){
        nodesData = new LinkedList<>();
        edgesPoints = new LinkedList<>();
    }

    public Snapshot(LinkedList<DrawData<T>> nodesData, LinkedList<Dimension2D[]> edgesPoints) {
        this.nodesData = (LinkedList<DrawData<T>>) nodesData.clone();
        this.edgesPoints = (LinkedList<Dimension2D[]>) edgesPoints.clone();
    }

    public LinkedList<DrawData<T>> getNodesData() {
        return nodesData;
    }

    public LinkedList<Dimension2D[]> getEdgesPoints() {
        return edgesPoints;
    }
}
