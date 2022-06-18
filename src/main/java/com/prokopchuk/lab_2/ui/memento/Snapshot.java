package com.prokopchuk.lab_2.ui.memento;

import javafx.scene.canvas.Canvas;

public class Snapshot {
    private Canvas canvas;

    public Snapshot(Canvas canvas) {
        this.canvas = canvas;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
