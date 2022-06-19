package com.prokopchuk.lab_2.ui.memento;

import java.util.ArrayList;

public class SnapshotManager<T> {
    private ArrayList<Snapshot<T>> snapshots = new ArrayList<>();
    private int current = 0;


    public void addSnapshot(Snapshot<T> toAdd) {
        snapshots.add(toAdd);

        System.out.println(snapshots.size());
        current = snapshots.size() - 1;
    }

    public Snapshot<T> undo() {
        if(current == 0) {
            return null;
        }

        return snapshots.get(--current);
    }

    public Snapshot<T> redo() {
        if(current >= snapshots.size() - 1) {
            return null;
        }

        return snapshots.get(++current);
    }

    public void clear() {
        snapshots.clear();
        current = 0;
    }
}
