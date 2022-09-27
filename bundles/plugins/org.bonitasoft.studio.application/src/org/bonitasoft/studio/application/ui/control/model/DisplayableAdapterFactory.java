/**
 * Copyright (C) 2022 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.smartImport.SmartImportableModel;
import org.bonitasoft.studio.common.repository.model.smartImport.SmartImportableUnit;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.eclipse.core.runtime.IAdapterFactory;

/**
 * Adapts many objects to {@link IDisplayable}
 */
public class DisplayableAdapterFactory implements IAdapterFactory {

    @Override
    public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
        if (adapterType.isAssignableFrom(IDisplayable.class)) {
            if (adaptableObject instanceof Artifact) {
                Artifact artifact = (Artifact) adaptableObject;
                ArtifactDisplayable artifactDisplayable = new ArtifactDisplayable(artifact);
                return (T) artifactDisplayable;
            } else if (adaptableObject instanceof IRepositoryFileStore) {
                IRepositoryFileStore repositoryFileStore = (IRepositoryFileStore) adaptableObject;
                RepositoryFileStoreDisplayable repositoryfileDisplayable = new RepositoryFileStoreDisplayable(
                        repositoryFileStore);
                return (T) repositoryfileDisplayable;
            } else if (adaptableObject instanceof IRepositoryStore<?>) {
                RepositoryStoreDisplayable repositoryfileDisplayable = new RepositoryStoreDisplayable(
                        (IRepositoryStore<?>) adaptableObject);
                return (T) repositoryfileDisplayable;
            }
            // these classes used to implement IPresentable, now replaced by IDisplayable
            else if (adaptableObject instanceof SmartImportableModel) {
                // use repository file store as a middle-man, but only for the name, do not use its icon...
                /*
                 * Note : This is legacy-compatible behavior.
                 * But would it make sense to use the same icon as the store ?
                 */
                IRepositoryFileStore store = ((SmartImportableModel) adaptableObject)
                        .getAdapter(IRepositoryFileStore.class);
                IDisplayable storeDisplay = getAdapter(store, IDisplayable.class);
                IDisplayable displayable = storeDisplay::getDisplayName;
                return (T) displayable;
            } else if (adaptableObject instanceof SmartImportableUnit) {
                IDisplayable displayable = ((SmartImportableUnit) adaptableObject)::getName;
                return (T) displayable;
            }
        }

        return null;
    }

    @Override
    public Class<?>[] getAdapterList() {
        return new Class[] { IDisplayable.class };
    }

}
