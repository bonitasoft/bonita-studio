/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
import java.util.stream.Stream;

import javax.xml.bind.JAXBException;

import org.bonitasoft.engine.profile.ProfilesParser;
import org.bonitasoft.engine.profile.xml.ProfilesNode;
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
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.google.common.io.ByteStreams;

public class ApplicationProfileFileStore extends AbstractFileStore {

    private final ProfilesParser profilesParser = new ProfilesParser();

    public ApplicationProfileFileStore(String fileName, IRepositoryStore<? extends IRepositoryFileStore> parentStore) {
        super(fileName, parentStore);
    }

    @Override
    public ProfilesNode getContent() throws ReadFileStoreException {
        try (InputStream inputStream = getResource().getContents()) {
            return profilesParser.convert(new String(ByteStreams.toByteArray(inputStream)));
        } catch (JAXBException | IOException | CoreException e) {
            throw new ReadFileStoreException("Failed to load profile model", e);
        }
    }

    @Override
    public Image getIcon() {
        return getParentStore().getIcon();
    }

    @Override
    protected void doSave(Object content) {
        checkArgument(content instanceof ProfilesNode, "Only instance of %s are supported", ProfilesNode.class);
        try {
            final byte[] xmlContent = profilesParser.convert((ProfilesNode) content).getBytes();
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
        } catch (JAXBException | IOException | CoreException e) {
            BonitaStudioLog.error("Failed to save profile model", e);
        }
    }

    @Override
    protected IWorkbenchPart doOpen() {
        try {
            return IDE.openEditor(getActivePage(), getResource());
        } catch (final PartInitException e) {
            throw new RuntimeException("Failed to open profile", e);
        }
    }

    @Override
    protected void doClose() {
        IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (activeWorkbenchWindow != null && activeWorkbenchWindow.getActivePage() != null) {
            IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
            Stream.of(activePage.getEditorReferences())
                    .filter(editorRef -> {
                        try {
                            return getName().contentEquals(editorRef.getEditorInput().getName());
                        } catch (PartInitException e) {
                            throw new RuntimeException("an error occured while trying to close a profile", e);
                        }
                    })
                    .forEach(editorRef -> activePage.closeEditor(editorRef.getEditor(true), false));
        }
    }

    protected IWorkbenchPage getActivePage() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    }

    @Override
    public IFile getResource() {
        return (IFile) super.getResource();
    }

}
