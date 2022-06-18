package com.prokopchuk.lab_2.ui.memento;

import java.util.ArrayList;

public class SnapshotManager {
    private ArrayList<Snapshot> snapshots = new ArrayList<>();
    private int current = 0;


    public void addSnapshot(Snapshot toAdd) {
        snapshots.add(toAdd);

        System.out.println(snapshots.size());
        current = snapshots.size() - 1;
    }

    public Snapshot undo() {
        if(current == 0) {
            return null;
        }

        return snapshots.get(--current);
    }

    public Snapshot redo() {
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
