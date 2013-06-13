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
import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent.EventType;
import org.bonitasoft.studio.common.repository.model.IFileStoreChangeNotifier;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Romain Bioteau
 *
 */
public abstract class AbstractFileStore implements IRepositoryFileStore, IFileStoreChangeNotifier, IPartListener {

    private String name;
    private final IRepositoryStore<? extends IRepositoryFileStore> store;
    private IWorkbenchPart activePart;

    public AbstractFileStore(String fileName , IRepositoryStore<? extends IRepositoryFileStore> parentStore){
        name = fileName ;
        store = parentStore ;
    }

    @Override
    public String getName() {
        return name;
    }

    protected void setName(String name){
        this.name = name;
    }

    @Override
    public String getDisplayName() {
        if(getName().indexOf(".") != -1){
            String name = getName().substring(0, getName().lastIndexOf(".")) ;
            return name ;
        }
        return getName() ;
    }

    @Override
    public IRepositoryStore<? extends IRepositoryFileStore> getParentStore() {
        return store;
    }


    @Override
    public boolean isReadOnly() {
        IResource resource = getResource() ;
        return resource != null && resource.exists() && resource.getResourceAttributes() != null && resource.getResourceAttributes().isReadOnly();
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        IResource resource = getResource();
        if(resource != null && resource.exists()){
            ResourceAttributes resourceAttributes = resource.getResourceAttributes();
            if(resourceAttributes == null){
                resourceAttributes = new ResourceAttributes();
            }
            resourceAttributes.setReadOnly(readOnly);
            try {
                resource.setResourceAttributes(resourceAttributes);
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    @Override
    final public void delete() {
        if(!isReadOnly()){
            fireFileStoreEvent(new FileStoreChangeEvent(EventType.PRE_DELETE, this)) ;
            doDelete();
            getParentStore().refresh() ;
            fireFileStoreEvent(new FileStoreChangeEvent(EventType.POST_DELETE, this)) ;
        }
    }


    @Override
    public boolean canBeShared() {
        return true;
    }

    @Override
    public boolean isShared() {
        return getParentStore().isShared();
    }

    @Override
    final public void save(Object content) {
        if(!isReadOnly()){
            fireFileStoreEvent(new FileStoreChangeEvent(EventType.PRE_SAVE, this)) ;
            doSave(content) ;
            try {
                getResource().refreshLocal(IResource.DEPTH_ZERO,Repository.NULL_PROGRESS_MONITOR) ;
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
            fireFileStoreEvent(new FileStoreChangeEvent(EventType.POST_SAVE, this)) ;
        }else{
        	Display.getDefault().syncExec(new Runnable() {
				
				@Override
				public void run() {
					MessageDialog.openWarning(Display.getDefault().getActiveShell(), Messages.readOnlyFileTitle,Messages.bind(Messages.readOnlyFileWarning,getDisplayName()));
				}
			});
        }
    }


    @Override
    final public IWorkbenchPart open() {
        fireFileStoreEvent(new FileStoreChangeEvent(EventType.PRE_OPEN, this)) ;
        activePart = doOpen() ;
        if(activePart != null && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null && 	PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null){
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().addPartListener(this) ;
        }
        fireFileStoreEvent(new FileStoreChangeEvent(EventType.POST_OPEN, this)) ;
        return activePart ;
    }

    @Override
    final public void close() {
        fireFileStoreEvent(new FileStoreChangeEvent(EventType.PRE_CLOSE, this)) ;
        doClose() ;
        if(PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null &&
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null){
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().removePartListener(this) ;
        }
        fireFileStoreEvent(new FileStoreChangeEvent(EventType.POST_CLOSE, this)) ;
    }

    @Override
    public void rename(String newName) {
        if(!isReadOnly()){
            if(getParentStore().getChild(newName) != null){
                throw new IllegalArgumentException(newName + " already exists in this store");
            }
            try {
                getResource().move(getParentStore().getResource().getFullPath().append(newName), true, Repository.NULL_PROGRESS_MONITOR);
            } catch (CoreException e) {
                BonitaStudioLog.error(e) ;
            }
            setName(newName);
        }
    }

    protected void doDelete() {
        try {
            IResource r = getResource() ;
            if(r != null && r.exists()){
                r.delete(true, Repository.NULL_PROGRESS_MONITOR) ;
            }
        } catch (CoreException e) {
            BonitaStudioLog.error(e) ;
        }
    }

    @Override
    public void export(String targetAbsoluteFilePath) {
        IResource file = getResource() ;
        if(file != null){
            File to = new File(targetAbsoluteFilePath) ;
            to.mkdirs() ;
            File target = new File(to,file.getName()) ;
            if(target.exists()){
                if(FileActionDialog.overwriteQuestion(file.getName())){
                    PlatformUtil.delete(target,  Repository.NULL_PROGRESS_MONITOR) ;
                }else{
                    return ;
                }
            }
            PlatformUtil.copyResource(to,file.getLocation().toFile(), Repository.NULL_PROGRESS_MONITOR) ;

        }
    }

    protected IProgressService getProgressService(){
        return PlatformUI.getWorkbench().getProgressService() ;
    }

    protected abstract void doSave(Object content) ;

    protected abstract IWorkbenchPart doOpen() ;

    protected abstract void doClose() ;

    @Override
    final public void fireFileStoreEvent(FileStoreChangeEvent event) {
        IRepository repository = RepositoryManager.getInstance().getCurrentRepository() ;
        if(repository != null){
            repository.notifyFileStoreEvent(event) ;
        }
    }



    @Override
    public void partClosed(IWorkbenchPart part) {
        if(activePart != null){
            if(part.equals(activePart)){
                close() ;
            }
        }
    }


    @Override
    public void partOpened(IWorkbenchPart part) {}

    @Override
    public void partDeactivated(IWorkbenchPart part) {}

    @Override
    public void partActivated(IWorkbenchPart part) {}

    @Override
    public void partBroughtToTop(IWorkbenchPart part) {}

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof IRepositoryFileStore){
            return getName().equals(((IRepositoryFileStore) obj).getName()) && getParentStore().equals(((IRepositoryFileStore) obj).getParentStore()) ;
        }
        return super.equals(obj);
    }

    protected IWorkbenchPart getActivePart() {
        return activePart;
    }

    @Override
    public boolean canBeExported() {
        return true;
    }

    @Override
    public Set<IResource> getRelatedResources() {
        return new HashSet<IResource>();
    }
}
