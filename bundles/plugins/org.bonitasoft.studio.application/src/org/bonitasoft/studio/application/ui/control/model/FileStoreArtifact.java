package org.bonitasoft.studio.application.ui.control.model;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

public class FileStoreArtifact extends Artifact {

    protected IRepositoryFileStore fStore;

    public FileStoreArtifact(Object parent, IRepositoryFileStore fStore) {
        super(parent);
        this.fStore = fStore;
    }

    @Override
    public String getDisplayName() {
        return fStore.getDisplayName();
    }

    @Override
    public Image getIcon() {
        return fStore.getIcon();
    }

    @Override
    public StyledString getStyledString() {
        return fStore.getStyledString();
    }
    
    @Override
    public String getName() {
        return fStore.getName();
    }
    
    @Override
    public String toString() {
        return fStore.getResource().getLocation().toString();
    }

}
