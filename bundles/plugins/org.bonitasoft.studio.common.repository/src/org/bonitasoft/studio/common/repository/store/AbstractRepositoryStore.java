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
package org.bonitasoft.studio.common.repository.store;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.filestore.RepositoryFileStoreComparator;
import org.bonitasoft.studio.common.repository.model.IFileStoreContribution;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.swt.widgets.Display;


/**
 * @author Romain Bioteau
 *
 */
public abstract class AbstractRepositoryStore<T extends IRepositoryFileStore> implements IRepositoryStore<T> {

	private static final String CLASS = "class";
	private IFolder folder ;
	private IRepository repository;

	@Override
	public void createRepositoryStore(IRepository repository){
		this.repository = repository ;
		IProject project = repository.getProject() ;
		this.folder = project.getFolder(getName()) ;
		if(!this.folder.exists()){
			try {
				if(!this.folder.getParent().exists()){
					this.folder.getLocation().toFile().mkdirs() ;
					this.folder.getParent().refreshLocal(IResource.DEPTH_ONE, Repository.NULL_PROGRESS_MONITOR) ;
				}else{
					this.folder.create(true, true, Repository.NULL_PROGRESS_MONITOR) ;
				}
				processDefaultContribution() ;
			} catch (CoreException e) {
				BonitaStudioLog.error(e) ;
			}
		}
	}

	private void processDefaultContribution() {
		IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements("org.bonitasoft.studio.repository.fileContribution") ;
		for(IConfigurationElement element : elements){
			IFileStoreContribution contribution;
			try {
				contribution = (IFileStoreContribution) element.createExecutableExtension(CLASS);
				if(contribution.appliesTo(this)){
					contribution.execute(this) ;
				}
			} catch (CoreException e) {
				BonitaStudioLog.error(e) ;
			}
		}
	}

	@Override
	public final T importInputStream(String fileName,InputStream inputStream) {
		Assert.isNotNull(inputStream);
		Assert.isNotNull(fileName);
		InputStream newIs = null;
		try {
			newIs = handlePreImport(fileName,inputStream);
		} catch (final MigrationException e) {
			if(!FileActionDialog.getDisablePopup()){
				Display.getDefault().syncExec(new Runnable() {

					@Override
					public void run() {
						new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.migrationFailedTitle, Messages.migrationFailedMessage,e).open();
					}
				});
			}
			return null;
		} catch (final IOException e) {
			if(!FileActionDialog.getDisablePopup()){
				Display.getDefault().syncExec(new Runnable() {

					@Override
					public void run() {
						new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.importedFileIsInvalidTitle, Messages.bind(Messages.importedFileIsInvalid, e.getMessage()),e).open();
					}
				});
			}
			return null;
		}
		
		
		
		if(newIs == null){
			return null;
		}
		final T store = doImportInputStream(fileName,newIs) ;
		return store;
	}

	@Override
	public final T importIResource(String fileName,IResource resource) {
		IResource newResource = handlePreImport(fileName,resource) ;
		final T store = doImportIResource(fileName,newResource) ;
		return store;
	}

	protected T doImportIResource(String fileName,IResource resource) {
		try{
			if(resource instanceof IFile){
				return importInputStream(fileName, ((IFile) resource).getContents()) ;
			}else if(resource instanceof IFolder){
				IPath path = getResource().getFullPath().append(fileName) ;
				IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot() ;
				IFolder targetFolder = root.getFolder(path);
				if(targetFolder.exists()){
					if(FileActionDialog.overwriteQuestion(fileName)){
						targetFolder.delete(true, Repository.NULL_PROGRESS_MONITOR) ;
					}else{
						return createRepositoryFileStore(fileName);
					}
				}
				resource.copy(getResource().getFullPath().append(fileName), true, Repository.NULL_PROGRESS_MONITOR) ;
			}
		}catch (Exception e) {
			BonitaStudioLog.error(e) ;
		}
		return createRepositoryFileStore(fileName);
	}

	/**
	 * Handler use to call migration action if needed
	 * @param fileName
	 * @param resource
	 * @return A the IResource with a migrated content
	 */
	 protected IResource handlePreImport(String fileName, IResource resource) {
		 return resource;
	 }

	 /**
	  * Handler use to call migration action if needed
	  * @param fileName
	  * @param inputStream
	  * @return A new InputStream with a migrated content
	  */
	 protected InputStream handlePreImport(String fileName, InputStream inputStream) throws MigrationException, IOException{
		 return inputStream ;
	 }

	 /**
	  * 
	  * @param fileName
	  * @param inputStream , read and closed inside this method
	  * @return IRepositoryFileStore
	  */
	 protected T doImportInputStream(String fileName, InputStream inputStream) {
		 final IFile file = getResource().getFile(fileName);
		 try{
			 if(file.exists()){
				 if(FileActionDialog.overwriteQuestion(fileName)){
					 file.setContents(inputStream, true, false, Repository.NULL_PROGRESS_MONITOR);
				 }else{
					 inputStream.close();
					 return createRepositoryFileStore(fileName);
				 }
			 } else {
				 File f = file.getLocation().toFile();
				 if(!f.getParentFile().exists()){
					 f.getParentFile().mkdirs();
					 refresh();
				 }
				 file.create(inputStream, true, Repository.NULL_PROGRESS_MONITOR);
			 }
		 }catch(Exception e){
			 BonitaStudioLog.error(e) ;
		 }
		 return createRepositoryFileStore(fileName) ;
	 }

	 @Override
	 public IFolder getResource() {
		 return folder;
	 }

	 @Override
	 public List<T> getChildren() {

		 refresh() ;

		 List<T> result = new ArrayList<T>() ;
		 IFolder folder = getResource();
		 try {
			 for(IResource r : folder.members()){
				 if(getCompatibleExtensions() == null){
					 if(!r.isHidden() && !r.getName().startsWith(".")){
						 final T repositoryFileStore = createRepositoryFileStore(r.getName());
						 if(repositoryFileStore != null){
							 result.add(repositoryFileStore) ;
						 }
					 }
				 }else if(r.getFileExtension() != null && getCompatibleExtensions().contains(r.getFileExtension())){
					 final T repositoryFileStore = createRepositoryFileStore(r.getName());
					 if(repositoryFileStore != null){
						 result.add(repositoryFileStore) ;
					 }
				 }
			 }
		 } catch (CoreException e) {
			 BonitaStudioLog.error(e) ;
		 }
		 Collections.sort(result, new RepositoryFileStoreComparator()) ;
		 return result;
	 }

	 @Override
	 public T getChild(String fileName) {
		 Assert.isNotNull(fileName) ;
		 IFile file = getResource().getFile(fileName) ;
		 if(!file.isSynchronized(IResource.DEPTH_ONE) && file.isAccessible()){
			 try {
				 file.refreshLocal(IResource.DEPTH_ONE, Repository.NULL_PROGRESS_MONITOR) ;
			 } catch (CoreException e) {
				 BonitaStudioLog.error(e) ;
			 }
		 }
		 if(file.exists()){
			 return createRepositoryFileStore(fileName) ;
		 }

		 return null ;
	 }

	 @Override
	 public void refresh() {
		 if(!folder.isSynchronized(IResource.DEPTH_INFINITE)){
			 try {
				 folder.refreshLocal(IResource.DEPTH_INFINITE, Repository.NULL_PROGRESS_MONITOR) ;
			 } catch (CoreException e1) {
				 BonitaStudioLog.error(e1) ;
			 }
		 }
	 }

	 @Override
	 public boolean isShared() {
		 return canBeShared() && repository.isShared();
	 }

	 @Override
	 public boolean canBeShared() {
		 return true;
	 }

	 @Override
	 public boolean canBeExported() {
		 return true;
	 }

	 @Override
	 public abstract T createRepositoryFileStore(String fileName);

	 @Override
	 public void migrate() throws CoreException, MigrationException {
		 for(IRepositoryFileStore fs : getChildren()){
			 if(!fs.isReadOnly() && fs.canBeShared()){
				 final IResource r = fs.getResource();
				 if(r instanceof IFile && r.exists()){
					 final IFile iFile = (IFile) r;
					 final InputStream is = iFile.getContents();
					 InputStream newIs;
					try {
						newIs = handlePreImport(r.getName(), is);
						 if(!is.equals(newIs)){
							 iFile.setContents(newIs, IResource.FORCE, Repository.NULL_PROGRESS_MONITOR);
							 iFile.refreshLocal(IResource.DEPTH_ONE, Repository.NULL_PROGRESS_MONITOR);
						 }
					} catch (IOException e) {
						throw new MigrationException("Cannot migrate resource "+r.getName() +" (not a valid file)", new Exception());
					}
					
				 }else{
					 throw new MigrationException("Cannot migrate resource "+r.getName() +" (not a file)", new Exception());
				 }
			 }
		 }
	 }


}
