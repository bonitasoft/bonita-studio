package org.bonitasoft.studio.application.ui.control.model;

import org.bonitasoft.studio.common.repository.model.IDisplayable;

public abstract class Artifact implements IDisplayable {

    private Object parent;

    public Artifact(Object parent) {
        this.parent = parent;
    }
    
    public Object getParent() {
        return parent;
    }

    public abstract String getName();
}
