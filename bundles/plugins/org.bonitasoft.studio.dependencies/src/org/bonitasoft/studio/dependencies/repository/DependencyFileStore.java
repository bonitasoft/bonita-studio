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
package org.bonitasoft.studio.dependencies.repository;

import java.io.InputStream;
import java.util.Map;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.dependencies.DependenciesPlugin;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Romain Bioteau
 *
 */
public class DependencyFileStore extends AbstractFileStore {

	public DependencyFileStore(String fileName,IRepositoryStore<? extends IRepositoryFileStore> parentStore) {
		super(fileName, parentStore) ;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#getIcon()
	 */
	@Override
	public Image getIcon() {
		return Pics.getImage("jar.gif",DependenciesPlugin.getDefault());
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#getContent()
	 */
	@Override
	public InputStream getContent() {
		try {
			return getResource().getContents();
		} catch (CoreException e) {
			BonitaStudioLog.error(e) ;
		}
		return null ;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doSave(java.lang.Object)
	 */
	@Override
	protected void doSave(Object content) {
		if(content instanceof InputStream){
			try{
				if(getResource().exists()){
					getResource().setContents((InputStream)content, IResource.FORCE, Repository.NULL_PROGRESS_MONITOR) ;
				}else{
					getResource().create((InputStream)content, IResource.FORCE, Repository.NULL_PROGRESS_MONITOR) ;
				}
			}catch (Exception e) {
				BonitaStudioLog.error(e) ;
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doOpen()
	 */
	@Override
	protected IWorkbenchPart doOpen() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doClose()
	 */
	@Override
	protected void doClose() {

	}

	protected void doDelete() {
		try {
			IResource r = getResource() ;
			if(r != null && r.exists()){
				r.delete(true, Repository.NULL_PROGRESS_MONITOR) ;
				RepositoryManager.getInstance().getCurrentRepository().getJavaProject().getProject().refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
			}
		} catch (CoreException e) {
			BonitaStudioLog.error(e) ;
		}
	}

	public IFile getResource() {
		return getParentStore().getResource().getFile(getName());
	}

	@Override
	public DependencyRepositoryStore getParentStore() {
		return (DependencyRepositoryStore) super.getParentStore();
	}

	public boolean existsInRuntimeContainer(){
		DependencyRepositoryStore depRepositoryStore = getParentStore();
		Map<String, String> runtimeDependencies = depRepositoryStore.getRuntimeDependencies();
		String libName = depRepositoryStore.getLibName(getName());
		String libVersion = depRepositoryStore.getLibVersion(getName());
		if(runtimeDependencies.containsKey(libName)){
			return runtimeDependencies.get(libName).equals(libVersion) ; //same libname & same version
		}
		return false;
	}

	public boolean existsInRuntimeContainerWithAnotherVersion(){
		DependencyRepositoryStore depRepositoryStore = getParentStore();
		Map<String, String> runtimeDependencies = depRepositoryStore.getRuntimeDependencies();
		String libName = depRepositoryStore.getLibName(getName());
		String libVersion = depRepositoryStore.getLibVersion(getName());
		if(runtimeDependencies.containsKey(libName)){
			return !runtimeDependencies.get(libName).equals(libVersion) ; //same libname & !same version
		}
		return false;
	}

}
