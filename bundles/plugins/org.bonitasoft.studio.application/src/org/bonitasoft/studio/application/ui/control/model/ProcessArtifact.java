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
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.model.edit.ProcessEditPlugin;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

public class ProcessArtifact implements VersionedArtifact {

    private List<ArtifactVersion> processVerions = new ArrayList<>();
    private String name;
    private RepositoryStore parent;

    public ProcessArtifact(RepositoryStore parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addVersion(Pool process, DiagramFileStore fStore) {
        processVerions.add(new ProcessVersion(this, process, fStore));
        Collections.sort(processVerions);
    }

    @Override
    public List<ArtifactVersion> getVersions() {
        return processVerions;
    }

    @Override
    public String toString() {
        return name;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ProcessArtifact) {
            return Objects.equals(((ProcessArtifact) obj).getName(), getName());
        }
        return super.equals(obj);
    }
    
    @Override
    public RepositoryStore getParent() {
        return parent;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getName(),getParent().getResource().getLocation().toString());
    }

    public String getDisplayName() {
        return toString();
    }

    @Override
    public Image getIcon() {
        return ExtendedImageRegistry.INSTANCE.getImage(ProcessEditPlugin.INSTANCE.getPluginResourceLocator().getImage("full/obj16/Pool"));
    }

    @Override
    public StyledString getStyledString() {
        return new StyledString(getDisplayName());
    }

    public ArtifactVersion getLatestVersion() {
        if(processVerions.isEmpty()) {
            throw new IllegalStateException(String.format("No version found for process %s", getName()));
        }
        return processVerions.get(0);
    }

    @Override
    public boolean hasSingleVersion() {
        return processVerions.size() == 1;
    }

}
