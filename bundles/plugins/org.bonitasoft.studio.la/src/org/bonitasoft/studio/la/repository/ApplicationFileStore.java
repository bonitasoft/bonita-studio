/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.repository;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBException;

import org.bonitasoft.engine.business.application.exporter.ApplicationNodeContainerConverter;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.xml.sax.SAXException;

import com.google.common.io.ByteStreams;

public class ApplicationFileStore extends AbstractFileStore {

    private final ApplicationNodeContainerConverter applicationNodeContainerConverter = new ApplicationNodeContainerConverter();

    public ApplicationFileStore(String fileName, IRepositoryStore<? extends IRepositoryFileStore> parentStore) {
        super(fileName, parentStore);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#getContent()
     */
    @Override
    public ApplicationNodeContainer getContent() throws ReadFileStoreException {
        try (InputStream inputStream = getResource().getContents()) {
            return applicationNodeContainerConverter.unmarshallFromXML(ByteStreams.toByteArray(inputStream));
        } catch (CoreException | JAXBException | IOException | SAXException e) {
            throw new ReadFileStoreException("Failed to load application model", e);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doSave(java.lang.Object)
     */
    @Override
    protected void doSave(Object content) {
        checkArgument(content instanceof ApplicationNodeContainer,
                String.format("Only instance of %s are supported",
                        ApplicationNodeContainer.class));
        try {
            final byte[] xmlContent = applicationNodeContainerConverter
                    .marshallToXML((ApplicationNodeContainer) content);
            try (ByteArrayInputStream is = new ByteArrayInputStream(xmlContent)) {
                final IFile resource = getResource();
                if (!resource.exists()) {
                    resource.create(is, IResource.FORCE,
                            Repository.NULL_PROGRESS_MONITOR);
                } else {
                    resource.setContents(is, IResource.KEEP_HISTORY | IResource.FORCE,
                            Repository.NULL_PROGRESS_MONITOR);
                }
            }
        } catch (JAXBException | IOException | SAXException | CoreException e) {
            BonitaStudioLog.error("Failed to save application model", e);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#getResource()
     */
    @Override
    public IFile getResource() {
        return (IFile) super.getResource();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doClose()
     */
    @Override
    protected void doClose() {
        //Nothing to do here
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IDisplayable#getIcon()
     */
    @Override
    public Image getIcon() {
        return getParentStore().getIcon();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doOpen()
     */
    @Override
    protected IWorkbenchPart doOpen() {
        try {
            return IDE.openEditor(getActivePage(), getResource());
        } catch (final PartInitException e) {
            throw new RuntimeException("Failed to open application descriptor.", e);
        }
    }

    protected IWorkbenchPage getActivePage() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    }

}
