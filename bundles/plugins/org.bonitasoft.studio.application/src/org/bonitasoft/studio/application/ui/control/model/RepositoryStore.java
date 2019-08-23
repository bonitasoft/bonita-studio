/**
 * Copyright (C) 2019 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
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
