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
package org.bonitasoft.studio.validators.repository;

import java.io.IOException;
import java.net.URL;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ui.IWorkbenchPart;


/**
 * @author Romain Bioteau
 *
 */
public class URLValidatorDescriptorFileStore extends ValidatorDescriptorFileStore {

    private final URL url;

    public URLValidatorDescriptorFileStore(URL url, IRepositoryStore<? extends EMFFileStore> store) {
        super(url.toString(), store);
        this.url = url ;
    }

    @Override
    protected Resource doCreateEMFResource() {
        if(url != null){
            try {
                URI uri = URI.createFileURI(FileLocator.toFileURL(url).getFile()) ;
                return getParentStore().getEditingDomain().getResourceSet().getResource(uri,true) ;
            }  catch (IOException e) {
                BonitaStudioLog.error(e) ;
            }
        }
        return null ;
    }


    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryFileStore#getName()
     */
    @Override
    public String getName() {
        String file = url.getFile() ;
        String[] segments = file.split("/") ;
        return segments[segments.length -1] ;
    }


    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doSave(java.lang.Object)
     */
    @Override
    protected void doSave(Object content) {


    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doOpen()
     */
    @Override
    protected IWorkbenchPart doOpen() {
        return null;
    }

    @Override
    public boolean canBeShared() {
        return false;
    }

    @Override
    public boolean canBeExported() {
        return false ;
    }

    @Override
    public boolean isReadOnly() {
        return true;
    }

    @Override
    public boolean isShared() {
        return false;
    }

    @Override
    public void setReadOnly(boolean readOnly) {

    }
}
