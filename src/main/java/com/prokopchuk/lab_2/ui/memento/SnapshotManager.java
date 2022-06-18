package com.prokopchuk.lab_2.ui.memento;

import java.util.LinkedList;
import java.util.ListIterator;

public class SnapshotManager {
    private LinkedList<Snapshot> snapshots = new LinkedList<>();
    private ListIterator<Snapshot> lastVersion;
    private ListIterator<Snapshot> current;


    public void addSnapshot(Snapshot toAdd) {
        snapshots.add(toAdd);
        if(lastVersion == null) {
            lastVersion = snapshots.listIterator();
        } else {
            //lastVersion.next();
        }
        System.out.println(snapshots.size());
        current = lastVersion;
    }

    public Snapshot undo() {
        if(current == null || !current.hasPrevious()) {
            return null;
        }

        return current.previous();
    }

    public Snapshot redo() {
        if(current == null || !current.hasNext()) {
            return null;
        }

        return current.next();
    }

    public void clear() {
        snapshots.clear();
    }

    public Snapshot getLastVersion() {
        return snapshots.getLast();
    }
}
