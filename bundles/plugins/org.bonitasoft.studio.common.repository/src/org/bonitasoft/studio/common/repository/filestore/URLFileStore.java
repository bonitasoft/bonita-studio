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
package org.bonitasoft.studio.common.repository.filestore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.Set;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;

import com.google.common.io.ByteStreams;

/**
 * @author Romain Bioteau
 */
public class URLFileStore implements IRepositoryFileStore {

    private final URL url;
    private final IRepositoryStore<?> store;

    public URLFileStore(final URL url, final IRepositoryStore<?> store) {
        this.url = url;
        this.store = store;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryFileStore#getName()
     */
    @Override
    public String getName() {
        final String file = url.getFile();
        final String[] segments = file.split("/");
        return segments[segments.length - 1];
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryFileStore#getDisplayName()
     */
    @Override
    public String getDisplayName() {
        return getName();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryFileStore#getIcon()
     */
    @Override
    public Image getIcon() {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryFileStore#getParentStore()
     */
    @Override
    public IRepositoryStore<?> getParentStore() {
        return store;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryFileStore#getContent()
     */
    @Override
    public InputStream getContent() {
        try {
            return url.openStream();
        } catch (final IOException e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryFileStore#getFile()
     */
    @Override
    public IFile getResource() {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryFileStore#isShared()
     */
    @Override
    public boolean isShared() {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryFileStore#open()
     */
    @Override
    public IWorkbenchPart open() {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryFileStore#close()
     */
    @Override
    public void close() {

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryFileStore#delete()
     */
    @Override
    public void delete() {

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryFileStore#rename(java.lang.String)
     */
    @Override
    public void renameLegacy(final String newName) {

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryFileStore#save(java.lang.Object)
     */
    @Override
    public void save(final Object content) {

    }

    public URL getUrl() {
        return url;
    }

    @Override
    public boolean isReadOnly() {
        return true;
    }

    @Override
    public void setReadOnly(final boolean readOnly) {

    }

    @Override
    public boolean canBeShared() {
        return false;
    }

    @Override
    public IStatus export(final String targetAbsoluteFilePath) {
        try (InputStream is = getContent()) {
            if (is != null) {
                File to = new File(targetAbsoluteFilePath);
                to.mkdirs();
                try (FileOutputStream fos = new FileOutputStream(to)) {
                    FileUtil.copy(is, fos);
                    return ValidationStatus.ok();
                }
            }
            return ValidationStatus.error(String
                    .format(Messages.failedToRetrieveResourceToExport, url.toString()));
        } catch (IOException e) {
            BonitaStudioLog.error(e);
            return ValidationStatus.error(Messages.exportFailed, e);
        }
    }

    @Override
    public boolean canBeExported() {
        return false;
    }

    @Override
    public Set<IResource> getRelatedResources() {
        return Collections.emptySet();
    }

    @Override
    public Set<IRepositoryFileStore> getRelatedFileStore() {
        return Collections.emptySet();
    }

    @Override
    public byte[] toByteArray() throws IOException {
        try (final InputStream inputStream = getContent();) {
            return ByteStreams.toByteArray(inputStream);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IDisplayable#getStyledString()
     */
    @Override
    public StyledString getStyledString() {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#canBeDeleted()
     */
    @Override
    public boolean canBeDeleted() {
        return false;
    }

}
