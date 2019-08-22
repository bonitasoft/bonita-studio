package org.bonitasoft.studio.application.ui.control.model;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.views.ProjectExplorerViewerComparator;
import org.bonitasoft.studio.common.repository.model.IDisplayable;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

public class RepositoryStore implements IDisplayable,Comparable<RepositoryStore> {

    List<Artifact> artifacts = new ArrayList<>();
    private IRepositoryStore<? extends IRepositoryFileStore> store;
    
    public RepositoryStore(IRepositoryStore<? extends IRepositoryFileStore> store) {
        this.store = store;
    }

    public IResource getResource() {
        return store.getResource();
    }
    
    public void add(Artifact artifact) {
        artifacts.add(artifact);
    }
    
    public List<Artifact> getArtifacts(){
        return artifacts;
    }

    @Override
    public String getDisplayName() {
        return store instanceof DiagramRepositoryStore ? Messages.processes : store.getDisplayName();
    }

    @Override
    public Image getIcon() {
        return store.getIcon();
    }

    @Override
    public StyledString getStyledString() {
        return new StyledString(getDisplayName());
    }
    
    public String getName() {
        return store.getName();
    }

    @Override
    public int compareTo(RepositoryStore o) {
        return Integer.compare(ProjectExplorerViewerComparator.REPO_STORE_ORDER.get(getName()), ProjectExplorerViewerComparator.REPO_STORE_ORDER.get(o.getName()));
    }
}
