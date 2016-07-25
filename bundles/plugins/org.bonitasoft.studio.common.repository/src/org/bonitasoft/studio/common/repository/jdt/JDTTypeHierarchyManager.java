/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.repository.jdt;

import java.util.WeakHashMap;

import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent;
import org.bonitasoft.studio.common.repository.model.IFileStoreChangeListener;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaModelException;

public class JDTTypeHierarchyManager implements IFileStoreChangeListener {

    private final WeakHashMap<IType, ITypeHierarchy> typeHierarchies = new WeakHashMap<IType, ITypeHierarchy>();

    protected boolean isAJarResource(final IResource resource) {
        return "jar".equals(resource.getFileExtension());
    }

    public ITypeHierarchy getTypeHierarchy(final IType type) throws JavaModelException {
        ITypeHierarchy res = typeHierarchies.get(type);
        if (res == null) {
            res = createTypeHierarchy(type);
        }
        return res;
    }

    protected ITypeHierarchy createTypeHierarchy(final IType type) throws JavaModelException {
        final ITypeHierarchy res = type.newTypeHierarchy(null);
        typeHierarchies.put(type, res);
        return res;
    }

    protected void clearCache() {
        typeHierarchies.clear();
    }

    @Override
    public void handleFileStoreEvent(final FileStoreChangeEvent event) {
        final IRepositoryFileStore fileStore = event.getFileStore();
        if (fileStore != null) {
            final IResource resource = fileStore.getResource();
            if (resource != null && isAJarResource(resource)) {
                clearCache();
            }
        }
    }

}
