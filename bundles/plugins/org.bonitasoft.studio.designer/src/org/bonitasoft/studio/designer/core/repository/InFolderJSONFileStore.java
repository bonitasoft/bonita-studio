/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.core.repository;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.preferences.browser.OpenBrowserOperation;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.json.JSONObject;

/**
 * @author Romain Bioteau
 *         Give access to the json file contained in the current file store represented by a folder
 */
public class InFolderJSONFileStore extends NamedJSONFileStore {

    private static final String JSON_EXTENSION = ".json";

    public InFolderJSONFileStore(final String folderName, final IRepositoryStore parentStore) {
        super(folderName, parentStore);
    }

    @Override
    public IFolder getResource() {
        return getParentStore().getResource().getFolder(getName());
    }
    
    @Override
    protected JSONObject doGetContent() throws ReadFileStoreException {
        if (!getResource().exists()) {
            throw new ReadFileStoreException(getResource().getLocation() + " does not exists.");
        }
        final IFile jsonFile = getJSONIFile();
        if (!jsonFile.exists()) {
            throw new ReadFileStoreException(jsonFile.getLocation() + " does not exists.");
        }
        return toJSONObject(jsonFile);
    }
    
    @Override
    protected InputStream openInputStream() throws CoreException {
        return getJSONIFile().getContents();
    }

    public IFile getJSONIFile() {
        return getResource().getFile(getName() + JSON_EXTENSION);
    }

    protected PageDesignerURLFactory urlFactory() {
        return new PageDesignerURLFactory(InstanceScope.INSTANCE.getNode(BonitaStudioPreferencesPlugin.PLUGIN_ID));
    }

    protected OpenBrowserOperation openBrowserOperation(final URL url) throws MalformedURLException {
        return new OpenBrowserOperation(url);
    }
}
