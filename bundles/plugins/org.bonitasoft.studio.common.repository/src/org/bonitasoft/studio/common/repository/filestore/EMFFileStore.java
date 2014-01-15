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
package org.bonitasoft.studio.common.repository.filestore;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 *
 */
public abstract class EMFFileStore extends AbstractFileStore implements IRepositoryFileStore {

    protected Resource eResource;


    public EMFFileStore(String fileName,IRepositoryStore<? extends EMFFileStore> store){
        super(fileName,store) ;
    }

    protected Resource doCreateEMFResource(){
        URI uri = URI.createFileURI(getFileStorePath()) ;
        try{
            final EditingDomain editingDomain  = getParentStore().getEditingDomain();
            if(new File(uri.toFileString()).exists()){
                return editingDomain.getResourceSet().getResource(uri,true) ;
            }else{
                return editingDomain.getResourceSet().createResource(uri) ;
            }
        }catch (Exception e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

	protected String getFileStorePath() {
		return getParentStore().getResource().getLocation().toFile().getAbsolutePath()+File.separatorChar+getName();
	}

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryFileStore#getContent()
     */
    @Override
    public synchronized Object getContent() {
        final Resource eResource = getEMFResource() ;
        if(eResource != null){
            if(!eResource.isLoaded()){
                try {
                    eResource.load(Collections.EMPTY_MAP) ;
                } catch (IOException e) {
                    BonitaStudioLog.error(e) ;
                }
            }
            if(!eResource.getContents().isEmpty()){
                return eResource.getContents().get(0);
            }
        }
        return null;
    }

    @Override
    protected void doDelete() {
    	doClose();
    	final Resource eResource = getEMFResource() ;
    	if(eResource != null){
    		if(eResource.isLoaded()){
    			eResource.unload() ;
    		}
    		try {
    			eResource.delete(Collections.EMPTY_MAP) ;
    		} catch (IOException e) {
    			BonitaStudioLog.error(e) ;
    			
    		}
    	} else {
    		try {
				getResource().delete(true, new NullProgressMonitor());
			} catch (CoreException e) {
			
				BonitaStudioLog.error(e);
			}
    	}
    }

    @Override
    protected void doClose() {
        if(eResource != null && eResource.isLoaded()){
            eResource.unload();
        }
    }


    public Resource getEMFResource() {
        if(eResource == null){
            eResource = doCreateEMFResource() ;
        }
        return eResource;
    }

    public AdapterFactoryLabelProvider getLabelProvider() {
        return getParentStore().getLabelProvider();
    }

    @Override
    public String getDisplayName() {
        return getLabelProvider().getText(getContent());
    }

    @Override
    public AbstractEMFRepositoryStore<? extends IRepositoryFileStore> getParentStore() {
        return (AbstractEMFRepositoryStore<? extends IRepositoryFileStore>) super.getParentStore();
    }

    @Override
    public Image getIcon() {
        return getLabelProvider().getImage(getContent());
    }

    @Override
    public IFile getResource() {
        return getParentStore().getResource().getFile(getName());
    }

}