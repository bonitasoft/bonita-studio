/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.repository.model;

import java.io.IOException;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Romain Bioteau
 */
public interface IRepositoryFileStore extends IDisplayable, IAdaptable {

    String getName();

    IRepositoryStore<? extends IRepositoryFileStore> getParentStore();

    Object getContent() throws ReadFileStoreException;

    IResource getResource();

    Set<IResource> getRelatedResources();

    Set<IRepositoryFileStore> getRelatedFileStore();

    boolean isShared();

    boolean isReadOnly();

    void setReadOnly(boolean readOnly);

    IWorkbenchPart open();

    void close();

    void delete();

    void renameLegacy(String newName);

    void save(Object content);

    boolean canBeShared();

    boolean canBeExported();

    IStatus export(String targetAbsoluteFilePath) throws IOException;

    byte[] toByteArray() throws IOException;

    boolean canBeDeleted();

    @Override
    default <T> T getAdapter(Class<T> adapter) {
        return null;
    }

}
