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
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.filestore.PackageFileStore;
import org.bonitasoft.studio.common.repository.model.IDefinitionRepositoryStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.common.repository.store.SourceRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
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
    private final Set<String> ignoredLibs = new HashSet<String>();

    public void setImplementation(final ConnectorImplementation impl) {
        this.impl = impl ;
    }

    public void setIncludeSources(final boolean includeSrouces){
        includeSources = includeSrouces;
    }

    public void setAddDependencies(final boolean addDependencies){
        this.addDependencies = addDependencies;
    }

    public void setTargetPath(final String destPath){
        this.destPath = destPath ;
    }

    public IStatus run(final IProgressMonitor progressMonitor){
        progressMonitor.beginTask(Messages.exporting, IProgressMonitor.UNKNOWN) ;
        IStatus status = Status.OK_STATUS  ;
        try{
            ignoredLibs.add(NamingUtils.toConnectorImplementationFilename(impl.getImplementationId(), impl.getImplementationVersion(),false)+".jar");
            cleanAfterExport = new ArrayList<IResource>() ;
            final SourceRepositoryStore sourceStore = getSourceStore();
            final IRepositoryStore implStore = getImplementationStore();

            final List<IResource> resourcesToExport = new ArrayList<IResource>() ;
            final IFolder classpathFolder = createClasspathFolder(implStore, resourcesToExport);

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

            final ArchiveFileExportOperation operation = executeArchiveFileExportOperation(progressMonitor, resourcesToExport);

            if( !operation.getStatus().isOK()){
                return operation.getStatus() ;
            }

            if(implBackup != null){
                final IRepositoryStore store = getImplementationStore();
                String fileName = NamingUtils.getEResourceFileName(implBackup, true);
                if(fileName == null){
                    fileName = NamingUtils.toConnectorImplementationFilename(implBackup.getImplementationId(), implBackup.getImplementationVersion(), true);
                }
                final IRepositoryFileStore implFile = store.getChild(fileName, true) ;
                if(implFile != null){
                    implFile.save(implBackup) ;
                }else{
                    return ValidationStatus.error(fileName + " not found in repository");
                }
            }

            cleanResourceAfterExport();
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
            return ValidationStatus.error(e.getMessage(), e);
        } catch (final InvocationTargetException e) {
            BonitaStudioLog.error(e);
            return ValidationStatus.error(e.getMessage(), e);
        } catch (final InterruptedException e) {
            BonitaStudioLog.error(e);
            return ValidationStatus.error(e.getMessage(), e);
        } catch (final FileNotFoundException e) {
            BonitaStudioLog.error(e);
            return ValidationStatus.error(e.getMessage(), e);
        }

        return status;
    }

    private ArchiveFileExportOperation executeArchiveFileExportOperation(final IProgressMonitor progressMonitor, final List<IResource> resourcesToExport)
            throws InvocationTargetException, InterruptedException {
        final ArchiveFileExportOperation operation =  new ArchiveFileExportOperation(null,resourcesToExport,destPath) ;
        operation.setUseCompression(true) ;
        operation.setUseTarFormat(false) ;
        operation.setCreateLeadupStructure(false) ;
        operation.run(progressMonitor) ;
        return operation;
    }

    private IFolder createClasspathFolder(final IRepositoryStore implStore, final List<IResource> resourcesToExport) throws CoreException {
        final IFolder classpathFolder = implStore.getResource().getFolder(CLASSPATH_DIR) ;
        if(classpathFolder.exists()){
            classpathFolder.delete(true, AbstractRepository.NULL_PROGRESS_MONITOR) ;
        }

        classpathFolder.create(true, true, AbstractRepository.NULL_PROGRESS_MONITOR) ;
        resourcesToExport.add(classpathFolder) ;
        cleanAfterExport.add(classpathFolder) ;
        return classpathFolder;
    }

    private void cleanResourceAfterExport() {
        for(final IResource r : cleanAfterExport){
            if(r.exists()){
                try {
                    r.delete(true, AbstractRepository.NULL_PROGRESS_MONITOR) ;
                } catch (final CoreException e) {
                    BonitaStudioLog.error(e) ;
                }
            }
        }
    }

    protected IStatus addImplementationJar(final ConnectorImplementation implementation, final IFolder classpathFolder, final SourceRepositoryStore sourceStore, final IRepositoryStore implStore, final List<IResource> resourcesToExport) throws CoreException, InvocationTargetException, InterruptedException {
        final String connectorJarName = NamingUtils.toConnectorImplementationFilename(implementation.getImplementationId(), implementation.getImplementationVersion(),false) +".jar";
        final IFile jarFile = classpathFolder.getFile(Path.fromOSString(connectorJarName)) ;
        final String qualifiedClassName = impl.getImplementationClassname() ;
        String packageName ="" ;
        if(qualifiedClassName.indexOf(".")!= -1){
            packageName = qualifiedClassName.substring(0, qualifiedClassName.lastIndexOf(".")) ;
        }
        final PackageFileStore file =  (PackageFileStore) sourceStore.getChild(packageName, true) ;
        if(file != null){
            file.exportAsJar(jarFile.getLocation().toFile().getAbsolutePath(), false) ;
            if(includeSources){
                final IFolder srcFolder = implStore.getResource().getFolder(SRC_DIR) ;
                if(srcFolder.exists()){
                    srcFolder.delete(true, AbstractRepository.NULL_PROGRESS_MONITOR) ;
                }
                srcFolder.create(true, true, AbstractRepository.NULL_PROGRESS_MONITOR) ;
                cleanAfterExport.add(srcFolder) ;

                final IPath path = file.getResource().getFullPath().makeRelativeTo(sourceStore.getResource().getFullPath()) ;
                final IFolder newFolder = srcFolder.getFolder(path) ;
                newFolder.getLocation().toFile().getParentFile().mkdirs() ;
                srcFolder.refreshLocal(IResource.DEPTH_INFINITE, AbstractRepository.NULL_PROGRESS_MONITOR) ;
                file.getResource().copy(newFolder.getFullPath(),true, AbstractRepository.NULL_PROGRESS_MONITOR) ;


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
        return RepositoryManager.getInstance().getRepositoryStore(ConnectorSourceRepositoryStore.class);
    }

    protected void addArchiveDescriptor(final IRepositoryStore<IRepositoryFileStore> sourceStore, final List<IResource> resourcesToExport) {
        final IFile descFile =  sourceStore.getResource().getFile(DESCRIPTOR_FILE) ;
        if(descFile.exists()){
            try {
                descFile.delete(true, AbstractRepository.NULL_PROGRESS_MONITOR) ;
            } catch (final CoreException e) {
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
        }catch (final Exception e) {
            BonitaStudioLog.error(e) ;
        }finally{
            if(out != null){
                try {
                    out.close();
                } catch (final IOException e) {
                    BonitaStudioLog.error(e);
                }
            }
            sourceStore.refresh();
        }

    }

    protected String getArchiveType() {
        return CONNECTOR_TYPE;
    }

    protected void addConnectorDefinition(final ConnectorImplementation impl, final List<IResource> resourcesToExport) throws FileNotFoundException, CoreException {
        final IRepositoryStore store = getDefinitionStore() ;
        final ConnectorDefinition def = ((IDefinitionRepositoryStore) store).getDefinition(impl.getDefinitionId(), impl.getDefinitionVersion()) ;
        final EMFFileStore file = (EMFFileStore) store.getChild(URI.decode(def.eResource().getURI().lastSegment()), true) ;

        if(file != null && !file.canBeShared()){
            final File f = new File(file.getEMFResource().getURI().toFileString()) ;
            if(f.exists()){
                final IFile defFile =  store.getResource().getFile(f.getName()) ;
                defFile.create(new FileInputStream(f), true, AbstractRepository.NULL_PROGRESS_MONITOR) ;
                resourcesToExport.add(defFile) ;
                cleanAfterExport.add(defFile) ;
            }
        }else if(file != null){
            resourcesToExport.add(file.getResource()) ;
        }

        addDefinitionIcons(resourcesToExport, store, def);
        addDefinitionPropertiesFile(resourcesToExport, store, def);
    }

    protected void addDefinitionPropertiesFile(final List<IResource> resourcesToExport, final IRepositoryStore store, final ConnectorDefinition def) {
        final DefinitionResourceProvider messageProvider = DefinitionResourceProvider.getInstance(store,getBundle()) ;
        final List<File> propertiesFile = messageProvider.getExistingLocalesResource(def) ;
        for(final File propertyFile : propertiesFile){
            final String newFilename = propertyFile.getName() ;
            try {
                final IFile f = store.getResource().getFile(newFilename) ;
                if(!f.exists()){
                    final FileInputStream fis = new FileInputStream(propertyFile) ;
                    f.create(fis,true,AbstractRepository.NULL_PROGRESS_MONITOR) ;
                    fis.close() ;
                    cleanAfterExport.add(f) ;
                }
                if(!resourcesToExport.contains(f)){
                    resourcesToExport.add(f) ;
                }
            } catch (final Exception e) {
                BonitaStudioLog.error(e) ;
            }
        }
    }

    protected Bundle getBundle() {
        return ConnectorPlugin.getDefault().getBundle();
    }

    protected void addDefinitionIcons(final List<IResource> resourcesToExport, final IRepositoryStore store, final ConnectorDefinition def) {
        if(def.getIcon() != null){
            final IFile iconFile  = store.getResource().getFile(Path.fromOSString(def.getIcon())) ;
            if(iconFile != null && iconFile.exists()){
                resourcesToExport.add(iconFile) ;
            }else{
                final URL url = ConnectorPlugin.getDefault().getBundle().getResource(ConnectorDefRepositoryStore.STORE_NAME+"/"+def.getIcon()) ;
                if(url != null){
                    try {
                        final IFile f = store.getResource().getFile(def.getIcon()) ;
                        if(!f.exists()){
                            final InputStream is = url.openStream() ;
                            f.create(is, true, AbstractRepository.NULL_PROGRESS_MONITOR) ;
                            if(!resourcesToExport.contains(f)){
                                resourcesToExport.add(f) ;
                            }
                            cleanAfterExport.add(f) ;
                            is.close() ;
                        }

                    } catch (final Exception e) {
                        BonitaStudioLog.error(e) ;
                    }
                }
            }
        }

        for(final Category c : def.getCategory()){
            if(c.getIcon() != null ){
                final IFile iconFile  = store.getResource().getFile(Path.fromOSString(c.getIcon())) ;
                if(iconFile != null  && iconFile.exists() ){
                    if(!resourcesToExport.contains(iconFile)){
                        resourcesToExport.add(iconFile) ;
                    }
                }else{
                    final URL url = ConnectorPlugin.getDefault().getBundle().getResource(ConnectorDefRepositoryStore.STORE_NAME+"/"+c.getIcon()) ;
                    if(url != null){
                        try {
                            final IFile f = store.getResource().getFile(c.getIcon()) ;
                            if(!f.exists()){
                                final InputStream is = url.openStream() ;
                                f.create(is, true, AbstractRepository.NULL_PROGRESS_MONITOR) ;
                                if(!resourcesToExport.contains(f)){
                                    resourcesToExport.add(f) ;
                                }
                                cleanAfterExport.add(f) ;
                                is.close() ;
                            }
                        } catch (final Exception e) {
                            BonitaStudioLog.error(e) ;
                        }
                    }
                }
            }
        }
    }

    protected void addConnectorImplementation(final ConnectorImplementation impl, final List<IResource> resourcesToExport,final boolean includeSources) throws FileNotFoundException, CoreException {
        final IRepositoryStore store = getImplementationStore() ;
        final String fileName = NamingUtils.getEResourceFileName(impl,true);
        final EMFFileStore fileStore = (EMFFileStore) store.getChild(fileName, true) ;
        if(!fileStore.canBeShared()){
            final File f = new File(fileStore.getEMFResource().getURI().toFileString()) ;
            if(f.exists()){
                final IFile implFile =  store.getResource().getFile(f.getName()) ;
                implFile.create(new FileInputStream(f), true, AbstractRepository.NULL_PROGRESS_MONITOR) ;
                resourcesToExport.add(implFile) ;
                cleanAfterExport.add(implFile) ;
            }
        }else{
            implBackup = EcoreUtil.copy(impl);
            final String jarName = NamingUtils.toConnectorImplementationFilename(impl.getImplementationId(),impl.getImplementationVersion(),false) + ".jar" ;
            if(!impl.getJarDependencies().getJarDependency().contains(jarName)){
                impl.getJarDependencies().getJarDependency().add(jarName) ;
            }
            impl.setHasSources(includeSources) ;
            final IRepositoryFileStore file = store.getChild(fileName, true) ;
            file.save(EcoreUtil.copy(impl)) ;
            resourcesToExport.add(file.getResource()) ;
        }

    }

    protected IStatus addDependencies(final ConnectorImplementation impl, final IFolder classpathFolder) throws CoreException {
        final IDefinitionRepositoryStore store = (IDefinitionRepositoryStore) getDefinitionStore() ;
        final DependencyRepositoryStore depStore = RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class) ;
        final DefinitionResourceProvider resourceProvider =  DefinitionResourceProvider.getInstance(getDefinitionStore(), getBundle()) ;

        final ConnectorDefinition def = store.getDefinition(impl.getDefinitionId(), impl.getDefinitionVersion()) ;
        for(final String jarName : def.getJarDependency()){
            if(ignoredLibs.contains(jarName)){
                continue ;
            }
            final IRepositoryFileStore file = depStore.getChild(jarName, true) ;
            if(file != null){
                if(file.getResource().exists()){
                    if(!classpathFolder.getFile(file.getName()).exists()){
                        try {
                            file.getResource().copy(classpathFolder.getFullPath().append(file.getName()), true, AbstractRepository.NULL_PROGRESS_MONITOR) ;
                        } catch (final CoreException e) {
                            BonitaStudioLog.error(e) ;
                        }
                    }
                }
            }else{ //Search in provided jars
                final InputStream is = resourceProvider.getDependencyInputStream(jarName) ;
                if(is != null){
                    final IFile jarFile = classpathFolder.getFile(jarName) ;
                    if(!jarFile.exists()){
                        jarFile.create(is, true, AbstractRepository.NULL_PROGRESS_MONITOR) ;
                    }
                }else{
                    return ValidationStatus.error(Messages.bind(Messages.implementationDepNotFound,jarName)) ;
                }
            }
        }
        for(final String jarName : impl.getJarDependencies().getJarDependency()){
            if(ignoredLibs.contains(jarName)){
                continue ;
            }
            final IRepositoryFileStore file = depStore.getChild(jarName, true) ;
            if(file != null){
                if(file.getResource().exists()){
                    if(!classpathFolder.getFile(file.getName()).exists()){
                        try {
                            file.getResource().copy(classpathFolder.getFullPath().append(file.getName()), true, AbstractRepository.NULL_PROGRESS_MONITOR) ;
                        } catch (final CoreException e) {
                            BonitaStudioLog.error(e) ;
                        }
                    }
                }
            }else{ //Search in provided jars
                final InputStream is = resourceProvider.getDependencyInputStream(jarName) ;
                if(is != null){
                    final IFile jarFile = classpathFolder.getFile(jarName) ;
                    if(!jarFile.exists()){
                        jarFile.create(is, true, AbstractRepository.NULL_PROGRESS_MONITOR) ;
                    }
                }else{
                    return ValidationStatus.error(Messages.bind(Messages.implementationDepNotFound,jarName)) ;
                }
            }
        }
        return Status.OK_STATUS ;
    }

    public void addIgnoredDependencies(final Set<String> ignoredLibs) {
        this.ignoredLibs.addAll(ignoredLibs) ;
    }

}
