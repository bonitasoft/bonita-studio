/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.core.repository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.store.AbstractFolderRepositoryStore;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;


public abstract class WebArtifactRepositoryStore<T extends IRepositoryFileStore> extends AbstractFolderRepositoryStore<T> {

    @Override
    public List<T> getChildren() {
        refresh();

        final List<T> result = new ArrayList<>();
        final IFolder folder = getResource();
        if(!folder.exists() || !folder.isAccessible()) {
            return result;
        }
        try {
            for (final IResource r : folder.members()) {
                if (!r.isHidden() && !r.getName().startsWith(".") && r instanceof IFolder) {
                    IResource jsonDescriptorFile = ((IFolder) r).findMember(r.getName() + ".json");
                    if (jsonDescriptorFile != null && jsonDescriptorFile.exists()) {
                        result.add(createRepositoryFileStore(r.getName()));
                    }
                }
            }
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
        return result;
    }

    @Override
    public T getChild(final String folderName, boolean force) {
        if (folderName != null) {
            final IFolder folder = getResource().getFolder(folderName);
            if(force) {
                refresh(folder);
            }
            IResource jsonDescriptorFile = folder.findMember(folderName + ".json");
            if (folder.exists() && jsonDescriptorFile != null && jsonDescriptorFile.exists()) {
                return createRepositoryFileStore(folderName);
            }
        }
        return null;
    }
    
    @Override
    public IStatus validate(String filename, InputStream inputStream) {
        if (filename != null & filename.endsWith(".json")) {
            return new UIDModelValidator(String.format(org.bonitasoft.studio.common.Messages.incompatibleModelVersion, filename)).validate(inputStream);
        }
        return super.validate(filename, inputStream);
    }

}
