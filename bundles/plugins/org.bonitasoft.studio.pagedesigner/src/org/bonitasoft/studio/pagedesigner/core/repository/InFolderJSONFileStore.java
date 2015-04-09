/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.pagedesigner.core.repository;

import static com.google.common.base.Preconditions.checkState;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.json.JSONObject;

/**
 * @author Romain Bioteau
 */
public class InFolderJSONFileStore extends NamedJSONFileStore {

    private static final String JSON_EXTENSION = ".json";

    public InFolderJSONFileStore(final String folderName, final IRepositoryStore<? extends IRepositoryFileStore> parentStore) {
        super(folderName, parentStore);
    }

    @Override
    public IFolder getResource() {
        return getParentStore().getResource().getFolder(getName());
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#getContent()
     */
    @Override
    public JSONObject getContent() throws ReadFileStoreException {
        checkState(getResource().exists());
        final IFile jsonFile = getResource().getFile(getName() + JSON_EXTENSION);
        checkState(jsonFile.exists());
        return toJSONObject(jsonFile);
    }

}
