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
package org.bonitasoft.studio.businessobject.core.repository;

import java.util.stream.Stream;

import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.model.IDeployable;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ITenantResource;
import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

public abstract class AbstractBDMFileStore<T> extends AbstractFileStore<T> implements IDeployable, ITenantResource {

    public AbstractBDMFileStore(String fileName, final IRepositoryStore<? extends AbstractBDMFileStore> store) {
        super(fileName, store);
    }

    @Override
    public IFile getResource() {
        return (IFile) super.getResource();
    }

    @Override
    protected IWorkbenchPart doOpen() {
        try {
            return IDE.openEditor(getActivePage(), getResource());
        } catch (final PartInitException e) {
            throw new RuntimeException("Failed to open file", e);
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
                            throw new RuntimeException("an error occured while trying to close the file", e);
                        }
                    })
                    .forEach(editorRef -> activePage.closeEditor(editorRef.getEditor(true), false));
        }
    }

    protected IWorkbenchPage getActivePage() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    }

}
