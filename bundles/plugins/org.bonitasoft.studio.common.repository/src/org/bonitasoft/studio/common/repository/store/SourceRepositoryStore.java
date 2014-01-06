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

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.filestore.PackageFileStore;
import org.bonitasoft.studio.common.repository.filestore.RepositoryFileStoreComparator;
import org.bonitasoft.studio.common.repository.filestore.SourceFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.internal.core.SourceType;


/**
 * @author Romain Bioteau
 *
 */
public abstract class SourceRepositoryStore<T extends AbstractFileStore> extends AbstractRepositoryStore<T> {

	public static String SIGNATURE_FILE_NAME = "Generated_With_BOS";

	/**
	 * Handles the import of packages folder
	 */
	@Override
	protected T doImportIResource(String fileName,IResource resource) {
		try{
			if(resource instanceof IFile){
				return doImportInputStream(fileName, ((IFile) resource).getContents()) ;
			}else if(resource instanceof IFolder){
				List<IFile> sourceFiles = new ArrayList<IFile>() ;
				findParentPackage((IFolder) resource,sourceFiles) ;
				for(IFile sourceFile : sourceFiles){
					IPath path = sourceFile.getProjectRelativePath() ;
					IFile targetFile = RepositoryManager.getInstance().getCurrentRepository().getProject().getFile(path.removeFirstSegments(1));
					boolean skip = false ;
					if(targetFile.exists()){
						if(FileActionDialog.overwriteQuestion(targetFile.getName())){
							targetFile.delete(true, Repository.NULL_PROGRESS_MONITOR) ;
						}else{
							skip = true ;
						}
					}
					if(!skip){
						targetFile.getLocation().toFile().getParentFile().mkdirs() ;
						refresh() ;

						try{
							targetFile.create(new FileInputStream(sourceFile.getLocation().toFile()), true, Repository.NULL_PROGRESS_MONITOR) ;
						}catch (Exception e) {
							BonitaStudioLog.error(e) ;
						}
					}
				}
				return null;
			}
		}catch (Exception e) {
			BonitaStudioLog.error(e) ;
		}
		return createRepositoryFileStore(fileName);
	}

	@Override
	protected T doImportInputStream(String fileName, InputStream inputStream) {
		if(fileName.indexOf("/") == -1){
			T fileStore = super.doImportInputStream(fileName, inputStream);
			if(fileStore instanceof SourceFileStore){
				fileStore.save(null);//notify svn event
			}
			return fileStore;
		}
		String packageName = fileName.substring(0, fileName.lastIndexOf("/")) ;
		String className = fileName.substring(packageName.length()+1, fileName.length()) ;
		PackageFileStore packageStore = (PackageFileStore) getChild(packageName) ;
		if(packageStore == null){
			IFolder folder = getResource() ;
			IFolder packageFolder = folder.getFolder(packageName) ;
			if(!packageFolder.exists()){
				try {
					packageFolder.getLocation().toFile().mkdirs() ;
					packageFolder.refreshLocal(IResource.DEPTH_ONE, Repository.NULL_PROGRESS_MONITOR) ;
					packageStore = (PackageFileStore) getChild(packageName) ;
				} catch (CoreException e) {
					BonitaStudioLog.error(e) ;
				}
			}
		}

		IFolder packageFolder = packageStore.getResource() ;
		IFile file = packageFolder.getFile(className) ;
		if(file.exists() &&  FileActionDialog.overwriteQuestion(fileName)){
			try {
				file.delete(true, Repository.NULL_PROGRESS_MONITOR) ;
			} catch (CoreException e) {
				BonitaStudioLog.error(e);
			}
		}
		try {
			file.create(inputStream, true, Repository.NULL_PROGRESS_MONITOR) ;
		} catch (CoreException e) {
			BonitaStudioLog.error(e) ;
		}
		return createRepositoryFileStore(packageName);
	}

	private void findParentPackage(IFolder folder, List<IFile> sourceFiles) {
		try{
			for(IResource r : folder.members()){
				if(r instanceof IFile){
					if(!sourceFiles.contains(r)){
						sourceFiles.add((IFile) r) ;
					}
				}else if (r instanceof IFolder){
					findParentPackage((IFolder) r, sourceFiles) ;
				}
			}
		}catch (Exception e) {
			BonitaStudioLog.error(e) ;
		}
	}


	@Override
	public List<T> getChildren() {
		refresh() ;
		if(getCompatibleExtensions() != null){
			return super.getChildren() ;
		}
		List<T> result = new ArrayList<T>() ;
		IFolder folder = getResource();
		try {
			for(IResource r : folder.members()){
				addChildren(r,result)  ;
			}

		} catch (CoreException e) {
			BonitaStudioLog.error(e) ;
		}
		Collections.sort(result, new RepositoryFileStoreComparator()) ;
		return result;
	}

	private void addChildren(IResource r, List<T> result) throws CoreException {

		if(r instanceof IFolder && !r.isHidden() && !r.getName().startsWith(".")){
			if(containsSourceFile((IFolder) r)){
				IPackageFragment pk =  RepositoryManager.getInstance().getCurrentRepository().getJavaProject().findPackageFragment(r.getFullPath()) ;
				if(pk != null){
					result.add(createRepositoryFileStore( pk.getElementName())) ;
				}
			}
			for(IResource child : ((IFolder)r).members()){
				addChildren(child, result) ;
			}
		}
	}

	private boolean containsSourceFile(IFolder folder) throws CoreException {
		for(IResource res : folder.members()){
			if(res.getFileExtension() != null && (res.getFileExtension().equals("java") || res.getFileExtension().equals("grrovy"))){
				return true ;
			}
		}
		return false;
	}

	@Override
	public T getChild(String fileName) {
		if(fileName == null){
			return null ;
		}
		if(fileName.endsWith(".java") || fileName.endsWith(".groovy")){
			T fileStore = super.getChild(fileName);
			if(fileStore != null){
				return fileStore;
			}
		}
		try{
			IJavaProject javaProject = RepositoryManager.getInstance().getCurrentRepository().getJavaProject() ;
			IType javaType = javaProject.findType(fileName);
			if(javaType != null && javaType instanceof SourceType){
				return (T) new SourceFileStore(fileName, this) ;
			}else if(javaType == null){ //package name
				IPackageFragment packageFragment = javaProject.findPackageFragment(getResource().getFullPath().append(fileName.replace(".","/")));
				if(packageFragment != null){
					return (T) new PackageFileStore(fileName, this) ;
				}
			}
		}catch (Exception e) {
			BonitaStudioLog.error(e) ;
		}

		return null ;
	}
	
	@Override
	public void migrate() throws CoreException, MigrationException {
		// NOTHING TO MIGRATE
	}
}
