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

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

public class FileStoreArtifact implements Artifact {

    protected IRepositoryFileStore fStore;
    private Object parent;

    public FileStoreArtifact(Object parent, IRepositoryFileStore fStore) {
        this.parent = parent;
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

    @Override
    public Object getParent() {
        return parent;
    }
    
    public IRepositoryFileStore getFileStore(){
        return fStore;
    }

}
