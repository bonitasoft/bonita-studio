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

import java.io.IOException;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
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
public class WebPageFileStore extends AbstractFileStore {

    private static final String FORM_NAME_KEY = "name";
    private static final String FORM_ID_KEY = "id";

    public WebPageFileStore(final String fileName, final IRepositoryStore<? extends IRepositoryFileStore> parentStore) {
        super(fileName, parentStore);
    }

    public String getId() {
        final JSONObject jSonObject = getContent();
        if (jSonObject == null) {
            throw new IllegalAccessError(String.format("Invalid JSON file %s.", getName()));
        }
        try {
            return jSonObject.getString(FORM_ID_KEY);
        } catch (final JSONException e) {
            throw new IllegalAccessError(String.format("Failed to retrieve Form id in JSON file %s, with key %s.", getName(), FORM_NAME_KEY));
        }
    }

    @Override
    public String getDisplayName() {
        final JSONObject jSonObject = getContent();
        if (jSonObject == null) {
            throw new IllegalAccessError(String.format("Invalid JSON file %s.", getName()));
        }
        try {
            return jSonObject.getString(FORM_NAME_KEY);
        } catch (final JSONException e) {
            throw new IllegalAccessError(String.format("Failed to retrieve Form name in JSON file %s, with key %s.", getName(), FORM_NAME_KEY));
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#getIcon()
     */
    @Override
    public Image getIcon() {
        return null;
    }

    @Override
    public IFile getResource() {
        return (IFile) super.getResource();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#getContent()
     */
    @Override
    public JSONObject getContent() {
        if (getResource() != null && getResource().exists()) {
            try {
                return new org.json.JSONObject(Files.toString(getResource().getLocation().toFile(), Charsets.UTF_8));
            } catch (final JSONException e) {
                BonitaStudioLog.error(e);
            } catch (final IOException e) {
                BonitaStudioLog.error(e);
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doSave(java.lang.Object)
     */
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
