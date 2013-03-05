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
package org.bonitasoft.studio.diagram.custom.repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore;
import org.bonitasoft.studio.diagram.custom.Activator;
import org.bonitasoft.studio.diagram.custom.Messages;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 *
 */
public class ApplicationResourceRepositoryStore extends AbstractRepositoryStore {

    private static final String STORE_NAME = "application_resources" ;

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#createRepositoryFileStore(java.lang.String)
     */
    @Override
    public IRepositoryFileStore createRepositoryFileStore(String processUUID) {
    	if(processUUID.contains(".")){
    		return null;
    	}
    	IFile f = getResource().getFile(processUUID);
    	if(f.exists() && f.getLocation().toFile().isFile()){
    		return null;
    	}
        return new ApplicationResourceFileStore(processUUID, this);
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#canImportBARResource(java.lang.String)
     */
    public boolean canImportBARResource(String resourceName) {
        return false;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getName()
     */
    public String getName() {
        return STORE_NAME;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getDisplayName()
     */
    public String getDisplayName() {
        return Messages.applicationResources;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getIcon()
     */
    public Image getIcon() {
        return Pics.getImage("resources.gif",Activator.getDefault());
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getCompatibleExtensions()
     */
    public Set<String> getCompatibleExtensions() {
        return null;
    }

    @Override
    public IRepositoryFileStore getChild(String processUUID) {
        if(processUUID != null){
            IFolder folder = getResource().getFolder(processUUID) ;
            if(!folder.isSynchronized(IResource.DEPTH_INFINITE) && folder.isAccessible()){
                try {
                    folder.refreshLocal(IResource.DEPTH_INFINITE, Repository.NULL_PROGRESS_MONITOR) ;
                } catch (CoreException e) {
                    BonitaStudioLog.error(e) ;
                }
            }
            if(folder.exists()){
                return createRepositoryFileStore(processUUID) ;
            }
        }
        return null ;
    }

    @Override
    public List<IRepositoryFileStore> getChildren() {
        refresh() ;

        List<IRepositoryFileStore> result = new ArrayList<IRepositoryFileStore>() ;
        IFolder folder = getResource();
        try {
            for(IResource r : folder.members()){
                if(!r.isHidden() && !r.getName().startsWith(".")){ //Hoping that .DS_STORE & .svn are hidden resources
                    result.add(createRepositoryFileStore(r.getName())) ;
                }
            }
        } catch (CoreException e) {
            BonitaStudioLog.error(e) ;
        }
        return result;
    }

    public File getFileInProject(String path) {
        return getResource().getLocation().append(path).toFile();
    }

    public IFile getIFileInProject(String path) {
        Path ipath = new Path(path);
        return getResource().getFile(ipath) ;
    }

    @Override
    protected IRepositoryFileStore doImportIResource(String fileName, IResource resource) {
        try{
            if(resource instanceof IFile){
                return doImportInputStream(fileName, ((IFile) resource).getContents()) ;
            }else if(resource instanceof IFolder){
                IPath path = getResource().getFullPath().append(fileName) ;
                IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot() ;
                IFolder targetFolder = root.getFolder(path);
                if(targetFolder.exists()){
                    String fileNameLabel = fileName;
                    final String processUUID = fileName ;
                    final DiagramRepositoryStore diagramStore = (DiagramRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
                    final AbstractProcess process = diagramStore.getProcessByUUID(processUUID);
                    if(process != null){
                        fileNameLabel =Messages.bind(Messages.applicationResourcesFor,process.getName() +" ("+process.getVersion()+")");
                    }
                    if(FileActionDialog.overwriteQuestion(fileNameLabel)){
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

}
