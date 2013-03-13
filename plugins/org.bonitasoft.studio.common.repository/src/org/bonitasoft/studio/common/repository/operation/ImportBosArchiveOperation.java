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
package org.bonitasoft.studio.common.repository.operation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.bonitasoft.studio.common.Pair;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Display;


/**
 * @author Romain Bioteau
 *
 */
public class ImportBosArchiveOperation {

    private static final String TMP_IMPORT_PROJECT = "tmpImport";
    private IStatus status;
    private String archiveFile;
    private Set<String> resourceToOpen ;

    public IStatus run(IProgressMonitor monitor){
        status = Status.OK_STATUS ;

        Assert.isNotNull(archiveFile) ;
        final File archive =  new File(archiveFile) ;
        Assert.isTrue(archive.exists()) ;

        try {
            IContainer container = createTempProject(archive,monitor);

            final Map<String,IRepositoryStore<? extends IRepositoryFileStore>> repositoryMap = new HashMap<String,IRepositoryStore<? extends IRepositoryFileStore>>();
            final List<IRepositoryStore<? extends IRepositoryFileStore>> allRepositories = RepositoryManager.getInstance().getCurrentRepository().getAllStores() ;
            FileActionDialog.activateYesNoToAll() ;
            for (IRepositoryStore<? extends IRepositoryFileStore> repository : allRepositories) {
                repositoryMap.put(repository.getName(),repository);
            }

            boolean  isValid = false ;
            while(container != null && !isValid) {
                IResource lastVisited = null ;
                for(IResource r : container.members(IResource.FOLDER)){
                    if(repositoryMap.get(r.getName()) != null){
                        isValid = true;
                        break;
                    }
                    lastVisited = r ;
                }
                if(isValid){
                    break ;
                }
                if(lastVisited instanceof IFolder){
                    container = (IFolder) lastVisited ;
                }else{
                    container = null ;
                }
            }

            if(!isValid){
                return new Status(IStatus.ERROR,CommonRepositoryPlugin.PLUGIN_ID,Messages.invalidArchive);
            }

            Properties manifestProperties = getManifestInfo(container);
            if(manifestProperties != null){
                final String version = manifestProperties.getProperty(ExportBosArchiveOperation.VERSION);
                String toOpen = manifestProperties.getProperty(ExportBosArchiveOperation.TO_OPEN);
                String[] array = toOpen.split(",");
                resourceToOpen = new HashSet<String>(Arrays.asList(array));
            }
            FileActionDialog.activateYesNoToAll() ;
            final IResource[] folders = container.members(IContainer.FOLDER);
            final List<IResource> folderSortedList = new ArrayList<IResource>(Arrays.asList(folders));
            final Comparator<IResource> importFolderComparator = new ImportFolderComparator<IResource>();
            Collections.sort(folderSortedList, importFolderComparator);
            for(final IResource folder : folderSortedList){
                Display.getDefault().syncExec(new Runnable() {

                    @Override
                    public void run() {
                        try{
                            if(folder instanceof IFolder){
                                Pair<IRepositoryStore<? extends IRepositoryFileStore>, IFolder> pair = findRepository(repositoryMap,(IFolder) folder) ;
                                if(pair == null){
                                    for(final IResource subFolder : ((IContainer) folder).members(IContainer.FOLDER)){
                                        if(subFolder instanceof IFolder){
                                            pair = findRepository(repositoryMap,(IFolder) subFolder) ;
                                            if(pair != null){
                                                importRepositoryStore(pair);
                                            }
                                        }
                                    }
                                }else if(pair != null){
                                    importRepositoryStore(pair);
                                }
                            }
                        }catch (Exception e) {
                            BonitaStudioLog.error(e) ;
                        }
                    }

                    protected void importRepositoryStore(Pair<IRepositoryStore<? extends IRepositoryFileStore>, IFolder> pair) throws CoreException {
                        IFolder storeFolder  = pair.getSecond() ;
                        IRepositoryStore<? extends IRepositoryFileStore> repository  = pair.getFirst() ;
                        for(IResource child : storeFolder.members()){
                            final String filename =  child.getName();
                            final boolean openAfterImport = (resourceToOpen != null && resourceToOpen.contains(filename)) || resourceToOpen == null ;
                            IRepositoryFileStore fileStore = repository.importIResource(filename,child);
                            if(fileStore != null && openAfterImport){
                                fileStore.open();
                            }
                        }
                    }
                }) ;
            }
            FileActionDialog.deactivateYesNoToAll() ;
            RepositoryManager.getInstance().getCurrentRepository().refresh(monitor);
        } catch (Exception e) {
            BonitaStudioLog.error(e);
        }finally{
            IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot() ;
            IProject container = root.getProject(TMP_IMPORT_PROJECT) ;
            if(container.exists()){
                try {
                    container.close( Repository.NULL_PROGRESS_MONITOR);
                    container.refreshLocal(IResource.DEPTH_ZERO,  Repository.NULL_PROGRESS_MONITOR);
                    container.delete(true, true, Repository.NULL_PROGRESS_MONITOR);
                    container.refreshLocal(IResource.DEPTH_ZERO,  Repository.NULL_PROGRESS_MONITOR);
                } catch (CoreException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }


        return  status;
    }

    protected IContainer createTempProject(final File archive,IProgressMonitor monitor) throws CoreException {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot() ;
        IProject container = root.getProject(TMP_IMPORT_PROJECT) ;
        if(container.exists()){
            try {
            	container.refreshLocal(IResource.DEPTH_ZERO,  Repository.NULL_PROGRESS_MONITOR);
                container.delete(true, true, Repository.NULL_PROGRESS_MONITOR);
                container.refreshLocal(IResource.DEPTH_ZERO,  Repository.NULL_PROGRESS_MONITOR);
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        container.create(Repository.NULL_PROGRESS_MONITOR) ;
        container.open(Repository.NULL_PROGRESS_MONITOR) ;

        PlatformUtil.unzipZipFiles(archive, container.getLocation().toFile(), monitor) ;
        container.refreshLocal(IResource.DEPTH_INFINITE, Repository.NULL_PROGRESS_MONITOR) ;
        return container;
    }

    private Properties getManifestInfo(IContainer container) {
        IFile file = container.getFile(Path.fromOSString(ExportBosArchiveOperation.BOS_ARCHIVE_MANIFEST));
        if(file.exists()){
            final Properties p = new Properties();
            InputStream contents = null;
            try {
                contents = file.getContents();
                p.load(contents);
            } catch (Exception e) {
                BonitaStudioLog.error(e);
                return null;
            }finally{
                if(contents != null){
                    try {
                        contents.close();
                    } catch (IOException e) {
                        BonitaStudioLog.error(e);
                    }
                }
            }
            return  p;
        }
        return null;
    }

    private Pair<IRepositoryStore<? extends IRepositoryFileStore>, IFolder> findRepository(Map<String, IRepositoryStore<? extends IRepositoryFileStore>> map,IFolder folder) {
        final String path = folder.getProjectRelativePath().removeFirstSegments(1).toOSString();
        final IRepositoryStore<? extends IRepositoryFileStore> store = map.get(path) ;
        if(store != null){
            return new Pair<IRepositoryStore<? extends IRepositoryFileStore>, IFolder>(store, folder) ;
        }

        return null ;
    }

    public IStatus getStatus(){
        return status ;
    }


    public void setArchiveFile(String archiveFile){
        this.archiveFile = archiveFile ;
    }
}
