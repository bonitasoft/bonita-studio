/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.document.core.repository;

import java.io.InputStream;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

/**
 * @author Romain Bioteau
 *
 */
public class DocumentFileStore extends AbstractFileStore<InputStream> {


    public DocumentFileStore(String fileName, DocumentRepositoryStore parentStore){
        super(fileName, parentStore);
    }


    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#getDisplayName()
     */
    @Override
    public String getDisplayName() {
        return getName();
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#getIcon()
     */
    @Override
    public Image getIcon() {
        return null;
    }

    @Override
    protected InputStream doGetContent() throws ReadFileStoreException {
        try {
            return getResource().getContents() ;
        } catch (CoreException e) {
            BonitaStudioLog.error(e) ;
        }
        return null ;
    }


    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#rename(java.lang.String)
     */
    @Override
    public void renameLegacy(String newName) {

    }


    @Override
    protected void doSave(Object content) {


    }


    @Override
    protected IWorkbenchPart doOpen() {
        try {
            return IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), getResource());
        } catch (PartInitException e) {
            return null;
        }
    }


    @Override
    protected void doClose() {


    }
    
    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#getResource()
     */
    @Override
    public IFile getResource() {
        return (IFile) super.getResource();
    }

}
