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
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.store.SourceRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;


/**
 * @author Romain Bioteau
 *
 */
public class ImportConnectorArchiveOperation {

    private File zipFile;
    private final FilenameFilter implFileFilter = new FilenameFilter() {

        @Override
        public boolean accept(File file, String name) {
            return name.endsWith("."+ConnectorImplRepositoryStore.CONNECTOR_IMPL_EXT);
        }
    };
    private final FilenameFilter defFileFilter = new FilenameFilter() {

        @Override
        public boolean accept(File file, String name) {
            return name.endsWith("."+ConnectorDefRepositoryStore.CONNECTOR_DEF_EXT);
        }
    };
    private final FilenameFilter messageFileFilter = new FilenameFilter() {

        @Override
        public boolean accept(File file, String name) {
            return name.endsWith(".properties") && !name.equals(ExportConnectorArchiveOperation.DESCRIPTOR_FILE);
        }
    };
    private final FilenameFilter imageFileFilter = new FilenameFilter() {

        @Override
        public boolean accept(File file, String name) {
            return name.endsWith(".png")
                    || name.endsWith(".jpg")
                    || name.endsWith(".gif")
                    || name.endsWith(".jpeg");
        }
    };

    public void setFile(File file) {
        zipFile = file ;
    }

    public IStatus run(IProgressMonitor monitor){
        if(zipFile == null){
            return ValidationStatus.error("input file not set") ;
        }
        File tmp = new File(ProjectUtil.getBonitaStudioWorkFolder(),"tmpImportConnectorDir") ;
        tmp.delete();
        tmp.mkdir() ;
        try{
        	PlatformUtil.unzipZipFiles(zipFile,tmp, monitor) ;
        }catch(Exception e){
        	BonitaStudioLog.error(e);
        }

        IStatus status = readManifest(tmp) ;
        if(status.getSeverity() != IStatus.OK){
            PlatformUtil.delete(tmp, monitor) ;
            return status ;
        }

        importConnectorDefinition(tmp) ;

        importConnectorImplementation(tmp) ;

        PlatformUtil.delete(tmp, monitor) ;

        return Status.OK_STATUS;
    }

    private IStatus readManifest(File tmp) {
        File manifest = new File(tmp,ExportConnectorArchiveOperation.DESCRIPTOR_FILE) ;
        if(!manifest.exists()){
            return ValidationStatus.error("Descriptor file not found") ;
        }

        try{
            Properties p = new Properties() ;
            final FileInputStream fis = new FileInputStream(manifest) ;
            p.load(fis) ;

            final String version = p.getProperty(ExportConnectorArchiveOperation.VERSION) ;
            if(version == null || version.isEmpty()){
                return ValidationStatus.error("Version is missing in the descriptor") ;
            }

            final String type = p.getProperty(ExportConnectorArchiveOperation.TYPE) ;
            if(type == null || type.isEmpty()){
                return ValidationStatus.error("Type is missing in the descriptor") ;
            }

            IStatus status = checkTypeIsValid(type) ;
            if(status.getSeverity() != IStatus.OK){
                return status ;
            }

        }catch (Exception e) {
            return ValidationStatus.error("Cannot read the descriptor file") ;
        }
        return Status.OK_STATUS ;
    }

    protected IStatus checkTypeIsValid(String type) {
        if(!ExportConnectorArchiveOperation.CONNECTOR_TYPE.equals(type)){
            return ValidationStatus.error("This is not a connector archive") ;
        }
        return Status.OK_STATUS;
    }

    private void importConnectorImplementation(File tmpDir) {
        final File[] files = tmpDir.listFiles(implFileFilter) ;
        if(files != null && files.length == 1){
            final File implFile = files[0] ;
            final IRepositoryStore implStore = getImplementationStore() ;
            try{
                final FileInputStream fis = new FileInputStream(implFile) ;
                implStore.importInputStream(implFile.getName(), fis) ;
                fis.close() ;
            }catch (Exception e) {
                BonitaStudioLog.error(e) ;
            }

            IRepositoryFileStore implFileStore = implStore.getChild(implFile.getName()) ;
            ConnectorImplementation impl = (ConnectorImplementation) implFileStore.getContent() ;
            if(impl.isHasSources()){
                final String implJarName = NamingUtils.toConnectorImplementationFilename(impl.getImplementationId(), impl.getImplementationVersion(), false)+".jar" ;
                importImplementationSources(tmpDir,implJarName) ;
            }
            importImplementationDependencies(tmpDir,impl);
        }
    }

    protected IRepositoryStore getImplementationStore() {
        return RepositoryManager.getInstance().getRepositoryStore(ConnectorImplRepositoryStore.class);
    }

    private void importConnectorDefinition(File tmpDir) {
        final File[] files = tmpDir.listFiles(defFileFilter) ;
        if(files != null && files.length == 1){
            final File defFile = files[0] ;
            final IRepositoryStore defStore = getDefinitionStore() ;
            IRepositoryFileStore fileStore = null ;
            try{
                final FileInputStream fis = new FileInputStream(defFile) ;
                fileStore = defStore.importInputStream(defFile.getName(), fis) ;
                fis.close() ;
            }catch (Exception e) {
                BonitaStudioLog.error(e) ;
            }
            importIcons(tmpDir) ;
            importMessages(tmpDir) ;
            if(fileStore != null){
                importDefinitionDependencies(tmpDir, (ConnectorDefinition) fileStore.getContent()) ;
            }
        }
    }

    private void importMessages(File tmpDir) {
        final File[] files = tmpDir.listFiles(messageFileFilter) ;
        if(files != null){
            IRepositoryStore defStore = getDefinitionStore() ;
            for(File messageFile : files){
                IFolder folder = defStore.getResource() ;
                IFile file = folder.getFile(messageFile.getName()) ;
                try{
                    if(file.exists() && FileActionDialog.confirmDeletionQuestion(messageFile.getName())){
                        file.delete(true, Repository.NULL_PROGRESS_MONITOR) ;
                    }
                    file.create(new FileInputStream(messageFile), true,  Repository.NULL_PROGRESS_MONITOR) ;
                }catch (Exception e) {
                    BonitaStudioLog.error(e) ;
                }
            }
        }
    }

    private void importIcons(File tmpDir) {
        final File[] files = tmpDir.listFiles(imageFileFilter) ;
        if(files != null){
            IRepositoryStore defStore = getDefinitionStore() ;
            for(File iconFile : files){
                IFolder folder = defStore.getResource() ;
                IFile file = folder.getFile(iconFile.getName()) ;
                try{
                    if(file.exists() && FileActionDialog.confirmDeletionQuestion(iconFile.getName())){
                        file.delete(true, Repository.NULL_PROGRESS_MONITOR) ;
                    }
                    file.create(new FileInputStream(iconFile), true,  Repository.NULL_PROGRESS_MONITOR) ;
                }catch (Exception e) {
                    BonitaStudioLog.error(e) ;
                }
            }
        }
    }

    protected IRepositoryStore getDefinitionStore() {
        return RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
    }

    private void importImplementationSources(File tmpDir, String implJarName) {
        final File srcDir = new File(tmpDir,ExportConnectorArchiveOperation.SRC_DIR) ;
        if(srcDir.exists()){
            List<File> files = new ArrayList<File>() ;
            findSourceFiles(srcDir,files) ;
            SourceRepositoryStore sourceStore = getSourceStore() ;
            for(File sourceFile : files){

                String className = getClassName(srcDir,sourceFile) ;

                try {
                    sourceStore.importInputStream(className, new FileInputStream(sourceFile)) ;
                } catch (FileNotFoundException e) {
                    BonitaStudioLog.error(e) ;
                }
            }


        }
    }

    private String getClassName(File root,File sourceFile) {
        return sourceFile.getAbsolutePath().substring(root.getAbsolutePath().length()+1, sourceFile.getAbsolutePath().length()) ;
    }

    private void findSourceFiles(File file, List<File> files) {
        if(file.isDirectory()){
            File[] children = file.listFiles() ;
            if(children != null){
                for(File child : children){
                    findSourceFiles(child, files) ;
                }
            }
        }else if(file.getName().endsWith(".java")){
            files.add(file) ;
        }

    }

    protected SourceRepositoryStore getSourceStore() {
        return (SourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ConnectorSourceRepositoryStore.class);
    }

    private void importDefinitionDependencies(File tmpDir,ConnectorDefinition def) {
        final File classpathDir = new File(tmpDir,ExportConnectorArchiveOperation.CLASSPATH_DIR) ;
        if(classpathDir.exists()){
            DependencyRepositoryStore depStore = (DependencyRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class) ;
            for(String jarName : def.getJarDependency()){
                final File jarFile = new File(classpathDir,jarName) ;
                if(jarFile.exists()){
                    try{
                        FileInputStream fis = new FileInputStream(jarFile) ;
                        depStore.importInputStream(jarName, fis) ;
                        fis.close() ;
                    }catch (Exception e) {
                        BonitaStudioLog.error(e) ;
                    }
                }
            }
        }

    }

    private void importImplementationDependencies(File tmpDir, ConnectorImplementation impl) {
        final File classpathDir = new File(tmpDir,ExportConnectorArchiveOperation.CLASSPATH_DIR) ;
        if(classpathDir.exists()){
            DependencyRepositoryStore depStore = (DependencyRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class) ;
            for(String jarName : impl.getJarDependencies().getJarDependency()){
            	if(isImplementationJar(jarName,impl) && impl.isHasSources()){
            		continue;
            	}
                final File jarFile = new File(classpathDir,jarName) ;
                if(jarFile.exists()){
                    try{
                        FileInputStream fis = new FileInputStream(jarFile) ;
                        depStore.importInputStream(jarName, fis) ;
                        fis.close() ;
                    }catch (Exception e) {
                        BonitaStudioLog.error(e) ;
                    }
                }
            }
        }
    }

	protected boolean isImplementationJar(String jarName,
			ConnectorImplementation impl) {
		return (NamingUtils.toConnectorImplementationFilename(impl.getImplementationId(), impl.getImplementationVersion(), false)+".jar").equals(jarName);
	}
}
