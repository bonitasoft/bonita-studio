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
package org.bonitasoft.studio.connectors.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.filestore.PackageFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.store.SourceRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.IDefinitionRepositoryStore;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.internal.wizards.datatransfer.ArchiveFileExportOperation;
import org.osgi.framework.Bundle;


/**
 * @author Romain Bioteau
 *
 */
public class ExportConnectorArchiveOperation {

	public static final String CLASSPATH_DIR = "classpath";
	public static final String DESCRIPTOR_FILE = "descriptor.properties";
	public static final String VERSION = "bos.version";
	public static final String TYPE = "type";
	public static final String CONNECTOR_TYPE = "connector";
	public static final String SRC_DIR = "src";

	private ConnectorImplementation impl;
	private ConnectorImplementation implBackup;
	private boolean includeSources;
	private boolean addDependencies;
	private ArrayList<IResource> cleanAfterExport;
	private String destPath;
	private Set<String> ignoredLibs = new HashSet<String>();

	public void setImplementation(ConnectorImplementation impl) {
		this.impl = impl ;
	}

	public void setIncludeSources(boolean includeSrouces){
		includeSources = includeSrouces;
	}

	public void setAddDependencies(boolean addDependencies){
		this.addDependencies = addDependencies;
	}

	public void setTargetPath(String destPath){
		this.destPath = destPath ;
	}

	public IStatus run(IProgressMonitor progressMonitor){
		progressMonitor.beginTask(Messages.exporting, IProgressMonitor.UNKNOWN) ;
		IStatus status = Status.OK_STATUS  ;
		try{
			cleanAfterExport = new ArrayList<IResource>() ;
			final SourceRepositoryStore sourceStore = getSourceStore() ;
			final IRepositoryStore implStore = getImplementationStore() ;

			List<IResource> resourcesToExport = new ArrayList<IResource>() ;
			IFolder classpathFolder = implStore.getResource().getFolder(CLASSPATH_DIR) ;
			if(classpathFolder.exists()){
				classpathFolder.delete(true, Repository.NULL_PROGRESS_MONITOR) ;
			}

			classpathFolder.create(true, true, Repository.NULL_PROGRESS_MONITOR) ;
			resourcesToExport.add(classpathFolder) ;
			cleanAfterExport.add(classpathFolder) ;

			status =  addImplementationJar(impl,classpathFolder,sourceStore,implStore,resourcesToExport) ;
			if(status.getSeverity() != IStatus.OK){
				return status;
			}
			addArchiveDescriptor(sourceStore,resourcesToExport) ;

			if(addDependencies){
				status = addDependencies(impl,classpathFolder) ;
				if(status.getSeverity() != IStatus.OK){
					return status;
				}
			}

			addConnectorImplementation(impl,resourcesToExport,includeSources) ;
			addConnectorDefinition(impl,resourcesToExport) ;

			final ArchiveFileExportOperation operation =  new ArchiveFileExportOperation(null,resourcesToExport,destPath) ;
			operation.setUseCompression(true) ;
			operation.setUseTarFormat(false) ;
			operation.setCreateLeadupStructure(false) ;
			operation.run(progressMonitor) ;
			if( !operation.getStatus().isOK()){
				return operation.getStatus() ;
			}
		}catch (CoreException e) {
			BonitaStudioLog.error(e) ;
			return ValidationStatus.error(e.getMessage(), e) ;
		} catch (InvocationTargetException e) {
			BonitaStudioLog.error(e) ;
			return ValidationStatus.error(e.getMessage(), e) ;
		} catch (InterruptedException e) {
			BonitaStudioLog.error(e) ;
			return ValidationStatus.error(e.getMessage(), e) ;
		} catch (FileNotFoundException e) {
			BonitaStudioLog.error(e) ;
			return ValidationStatus.error(e.getMessage(), e) ;
		}finally{
			if(implBackup != null){
				final IRepositoryStore store = getImplementationStore();
				IRepositoryFileStore implFile = store.getChild(NamingUtils.toConnectorImplementationFilename(implBackup.getImplementationId(), implBackup.getImplementationVersion(), true)) ;
				implFile.save(implBackup) ;
			}

			for(IResource r : cleanAfterExport){
				if(r.exists()){
					try {
						r.delete(true, Repository.NULL_PROGRESS_MONITOR) ;
					} catch (CoreException e) {
						BonitaStudioLog.error(e) ;
					}
				}
			}
		}

		return status;
	}

	protected IStatus addImplementationJar(ConnectorImplementation implementation, IFolder classpathFolder, SourceRepositoryStore sourceStore, IRepositoryStore implStore, List<IResource> resourcesToExport) throws CoreException {
		final String connectorJarName = NamingUtils.toConnectorImplementationFilename(impl.getImplementationId(), impl.getImplementationVersion(), false)  +".jar";
		final IFile jarFile = classpathFolder.getFile(Path.fromOSString(connectorJarName)) ;
		String qualifiedClassName = impl.getImplementationClassname() ;
		String packageName ="" ;
		if(qualifiedClassName.indexOf(".")!= -1){
			packageName = qualifiedClassName.substring(0, qualifiedClassName.lastIndexOf(".")) ;
		}
		final PackageFileStore file =  (PackageFileStore) sourceStore.getChild(packageName) ;
		if(file != null){
			file.exportAsJar(jarFile.getLocation().toFile().getAbsolutePath(), false) ;
			if(includeSources){
				IFolder srcFolder = implStore.getResource().getFolder(SRC_DIR) ;
				if(srcFolder.exists()){
					srcFolder.delete(true, Repository.NULL_PROGRESS_MONITOR) ;
				}
				srcFolder.create(true, true, Repository.NULL_PROGRESS_MONITOR) ;
				cleanAfterExport.add(srcFolder) ;

				IPath path = file.getResource().getFullPath().makeRelativeTo(sourceStore.getResource().getFullPath()) ;
				IFolder newFolder = srcFolder.getFolder(path) ;
				newFolder.getLocation().toFile().getParentFile().mkdirs() ;
				srcFolder.refreshLocal(IResource.DEPTH_INFINITE, Repository.NULL_PROGRESS_MONITOR) ;
				file.getResource().copy(newFolder.getFullPath(),true, Repository.NULL_PROGRESS_MONITOR) ;


				resourcesToExport.add(srcFolder) ;
			}
		}

		return Status.OK_STATUS ;
	}

	protected IRepositoryStore getImplementationStore() {
		return RepositoryManager.getInstance().getRepositoryStore(ConnectorImplRepositoryStore.class);
	}



	protected IRepositoryStore getDefinitionStore() {
		return RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
	}


	protected SourceRepositoryStore getSourceStore() {
		return (SourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ConnectorSourceRepositoryStore.class);
	}

	protected void addArchiveDescriptor(IRepositoryStore<IRepositoryFileStore> sourceStore, List<IResource> resourcesToExport) {
		IFile descFile =  sourceStore.getResource().getFile(DESCRIPTOR_FILE) ;
		if(descFile.exists()){
			try {
				descFile.delete(true, Repository.NULL_PROGRESS_MONITOR) ;
			} catch (CoreException e) {
				BonitaStudioLog.error(e) ;
			}
		}

		final Properties properties = new Properties() ;
		properties.put(VERSION, ProductVersion.CURRENT_VERSION) ;
		properties.put(TYPE,getArchiveType()) ;
		FileOutputStream out  = null;
		try{
			out = new FileOutputStream(descFile.getLocation().toFile()) ;
			properties.store(out, null) ;
			resourcesToExport.add(descFile) ;
			cleanAfterExport.add(descFile) ;
		}catch (Exception e) {
			BonitaStudioLog.error(e) ;
		}finally{
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					BonitaStudioLog.error(e);
				}
			}
			sourceStore.refresh();
		}

	}

	protected String getArchiveType() {
		return CONNECTOR_TYPE;
	}

	protected void addConnectorDefinition(ConnectorImplementation impl, List<IResource> resourcesToExport) throws FileNotFoundException, CoreException {
		final IRepositoryStore store = getDefinitionStore() ;
		ConnectorDefinition def = ((IDefinitionRepositoryStore) store).getDefinition(impl.getDefinitionId(), impl.getDefinitionVersion()) ;
		EMFFileStore file = (EMFFileStore) store.getChild(URI.decode(def.eResource().getURI().lastSegment())) ;

		if(file != null && !file.canBeShared()){
			File f = new File(file.getEMFResource().getURI().toFileString()) ;
			if(f.exists()){
				IFile defFile =  store.getResource().getFile(f.getName()) ;
				defFile.create(new FileInputStream(f), true, Repository.NULL_PROGRESS_MONITOR) ;
				resourcesToExport.add(defFile) ;
				cleanAfterExport.add(defFile) ;
			}
		}else if(file != null){
			resourcesToExport.add(file.getResource()) ;
		}

		addDefinitionIcons(resourcesToExport, store, def);
		addDefinitionPropertiesFile(resourcesToExport, store, def);
	}

	protected void addDefinitionPropertiesFile(List<IResource> resourcesToExport, final IRepositoryStore store, ConnectorDefinition def) {
		final DefinitionResourceProvider messageProvider = DefinitionResourceProvider.getInstance(store,getBundle()) ;
		List<File> propertiesFile = messageProvider.getExistingLocalesResource(def) ;
		for(File propertyFile : propertiesFile){
			String newFilename = propertyFile.getName() ;
			try {
				IFile f = store.getResource().getFile(newFilename) ;
				if(!f.exists()){
					FileInputStream fis = new FileInputStream(propertyFile) ;
					f.create(fis,true,Repository.NULL_PROGRESS_MONITOR) ;
					fis.close() ;
					cleanAfterExport.add(f) ;
				}
				if(!resourcesToExport.contains(f)){
					resourcesToExport.add(f) ;
				}
			} catch (Exception e) {
				BonitaStudioLog.error(e) ;
			}
		}
	}

	protected Bundle getBundle() {
		return ConnectorPlugin.getDefault().getBundle();
	}

	protected void addDefinitionIcons(List<IResource> resourcesToExport, final IRepositoryStore store, ConnectorDefinition def) {
		if(def.getIcon() != null){
			IFile iconFile  = store.getResource().getFile(Path.fromOSString(def.getIcon())) ;
			if(iconFile != null && iconFile.exists()){
				resourcesToExport.add(iconFile) ;
			}else{
				URL url = ConnectorPlugin.getDefault().getBundle().getResource(ConnectorDefRepositoryStore.STORE_NAME+"/"+def.getIcon()) ;
				if(url != null){
					try {
						IFile f = store.getResource().getFile(def.getIcon()) ;
						if(!f.exists()){
							InputStream is = url.openStream() ;
							f.create(is, true, Repository.NULL_PROGRESS_MONITOR) ;
							if(!resourcesToExport.contains(f)){
								resourcesToExport.add(f) ;
							}
							cleanAfterExport.add(f) ;
							is.close() ;
						}

					} catch (Exception e) {
						BonitaStudioLog.error(e) ;
					}
				}
			}
		}

		for(Category c : def.getCategory()){
			if(c.getIcon() != null ){
				IFile iconFile  = store.getResource().getFile(Path.fromOSString(c.getIcon())) ;
				if(iconFile != null  && iconFile.exists() ){
					if(!resourcesToExport.contains(iconFile)){
						resourcesToExport.add(iconFile) ;
					}
				}else{
					URL url = ConnectorPlugin.getDefault().getBundle().getResource(ConnectorDefRepositoryStore.STORE_NAME+"/"+c.getIcon()) ;
					if(url != null){
						try {
							IFile f = store.getResource().getFile(c.getIcon()) ;
							if(!f.exists()){
								InputStream is = url.openStream() ;
								f.create(is, true, Repository.NULL_PROGRESS_MONITOR) ;
								if(!resourcesToExport.contains(f)){
									resourcesToExport.add(f) ;
								}
								cleanAfterExport.add(f) ;
								is.close() ;
							}
						} catch (Exception e) {
							BonitaStudioLog.error(e) ;
						}
					}
				}
			}
		}
	}

	protected void addConnectorImplementation(ConnectorImplementation impl, List<IResource> resourcesToExport,boolean includeSources) throws FileNotFoundException, CoreException {
		final IRepositoryStore store = getImplementationStore() ;
		final EMFFileStore fileStore = (EMFFileStore) store.getChild(NamingUtils.toConnectorImplementationFilename(impl.getImplementationId(), impl.getImplementationVersion(), true)) ;
		if(!fileStore.canBeShared()){
			File f = new File(fileStore.getEMFResource().getURI().toFileString()) ;
			if(f.exists()){
				IFile implFile =  store.getResource().getFile(f.getName()) ;
				implFile.create(new FileInputStream(f), true, Repository.NULL_PROGRESS_MONITOR) ;
				resourcesToExport.add(implFile) ;
				cleanAfterExport.add(implFile) ;
			}

		}else{
			implBackup = EcoreUtil.copy(impl);
			String jarName = NamingUtils.toConnectorImplementationFilename(impl.getImplementationId(), impl.getImplementationVersion(), false) + ".jar" ;
			impl.getJarDependencies().getJarDependency().add(jarName) ;
			impl.setHasSources(includeSources) ;
			IRepositoryFileStore file = store.getChild(NamingUtils.toConnectorImplementationFilename(impl.getImplementationId(), impl.getImplementationVersion(), true)) ;
			file.save(EcoreUtil.copy(impl)) ;
			resourcesToExport.add(file.getResource()) ;
		}

	}

	protected IStatus addDependencies(ConnectorImplementation impl, IFolder classpathFolder) throws CoreException {
		final IDefinitionRepositoryStore store = (IDefinitionRepositoryStore) getDefinitionStore() ;
		final DependencyRepositoryStore depStore = (DependencyRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class) ;
		final DefinitionResourceProvider resourceProvider =  DefinitionResourceProvider.getInstance(getDefinitionStore(), getBundle()) ;

		ConnectorDefinition def = store.getDefinition(impl.getDefinitionId(), impl.getDefinitionVersion()) ;
		for(String jarName : def.getJarDependency()){
			if(ignoredLibs.contains(jarName)){
				continue ;
			}
			IRepositoryFileStore file = depStore.getChild(jarName) ;
			if(file != null){
				if(file.getResource().exists()){
					if(!classpathFolder.getFile(file.getName()).exists()){
						try {
							file.getResource().copy(classpathFolder.getFullPath().append(file.getName()), true, Repository.NULL_PROGRESS_MONITOR) ;
						} catch (CoreException e) {
							BonitaStudioLog.error(e) ;
						}
					}
				}
			}else{ //Search in provided jars
				InputStream is = resourceProvider.getDependencyInputStream(jarName) ;
			if(is != null){
				IFile jarFile = classpathFolder.getFile(jarName) ;
				if(!jarFile.exists()){
					jarFile.create(is, true, Repository.NULL_PROGRESS_MONITOR) ;
				}
			}else{
				return ValidationStatus.error(Messages.bind(Messages.implementationDepNotFound,jarName)) ;
			}
			}
		}
		for(String jarName : impl.getJarDependencies().getJarDependency()){
			if(ignoredLibs.contains(jarName)){
				continue ;
			}
			IRepositoryFileStore file = depStore.getChild(jarName) ;
			if(file != null){
				if(file.getResource().exists()){
					if(!classpathFolder.getFile(file.getName()).exists()){
						try {
							file.getResource().copy(classpathFolder.getFullPath().append(file.getName()), true, Repository.NULL_PROGRESS_MONITOR) ;
						} catch (CoreException e) {
							BonitaStudioLog.error(e) ;
						}
					}
				}
			}else{ //Search in provided jars
				InputStream is = resourceProvider.getDependencyInputStream(jarName) ;
			if(is != null){
				IFile jarFile = classpathFolder.getFile(jarName) ;
				if(!jarFile.exists()){
					jarFile.create(is, true, Repository.NULL_PROGRESS_MONITOR) ;
				}
			}else{
				return ValidationStatus.error(Messages.bind(Messages.implementationDepNotFound,jarName)) ;
			}
			}
		}
		return Status.OK_STATUS ;
	}

	public void addIgnoredDependencies(Set<String> ignoredLibs) {
		this.ignoredLibs  = ignoredLibs ;
	}

}
