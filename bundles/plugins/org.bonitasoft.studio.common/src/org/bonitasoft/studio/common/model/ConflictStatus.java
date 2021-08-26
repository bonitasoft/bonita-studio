package org.bonitasoft.studio.common.model;

public enum ConflictStatus {
    SAME_CONTENT(0), NONE(1), CONFLICTING(2);

    private int priority;

    private ConflictStatus(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

}
