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

import static com.google.common.base.Preconditions.checkState;

import java.io.IOException;

import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.eclipse.core.resources.IFile;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

/**
 * @author Romain Bioteau
 */
public class JSONFileStore extends AbstractFileStore<JSONObject> {

    private JSONObject content;

    public JSONFileStore(final String fileName, final IRepositoryStore parentStore) {
        super(fileName, parentStore);
    }

    @Override
    protected JSONObject getUnsafeContent() throws ReadFileStoreException {
        if(content == null) {
            content = super.getUnsafeContent();
        }
        return content;
    }
    
    protected String getStringAttribute(final String attribute) throws JSONException, ReadFileStoreException {
        return getUnsafeContent().getString(attribute);
    }

    protected boolean getBooleanAttribute(final String attribute) throws JSONException, ReadFileStoreException {
        return getUnsafeContent().getBoolean(attribute);
    }

    @Override
    public Image getIcon() {
        return null;
    }
    
    @Override
    protected JSONObject doGetContent() throws ReadFileStoreException {
        checkState(getResource() instanceof IFile && getResource().exists());
        return toJSONObject((IFile) getResource());
    }

    protected JSONObject toJSONObject(final IFile jsonFile) throws ReadFileStoreException {
        try {
            return new org.json.JSONObject(Files.toString(jsonFile.getLocation().toFile(), Charsets.UTF_8));
        } catch (JSONException | IOException e) {
            throw new ReadFileStoreException(String.format("Failed to retrieve JSON content from %s", jsonFile.getName()), e);
        }
    }

    @Override
    protected void doSave(final Object content) {

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doOpen()
     */
    @Override
    protected IWorkbenchPart doOpen() {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doClose()
     */
    @Override
    protected void doClose() {

    }

}
