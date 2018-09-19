/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.repository.filestore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent.EventType;
import org.bonitasoft.studio.common.repository.model.IDisplayable;
import org.bonitasoft.studio.common.repository.model.IFileStoreChangeNotifier;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

import com.google.common.io.Files;

/**
 * @author Romain Bioteau
 */
public abstract class AbstractFileStore
        implements IRepositoryFileStore, IFileStoreChangeNotifier, IPartListener, IDisplayable {

    public static final String ASK_ACTION_ON_CLOSE = "ASK_ACTION_ON_CLOSE";

    private String name;
    final IRepositoryStore<? extends IRepositoryFileStore> store;
    private IWorkbenchPart activePart;
    private Map<String, Object> parameters;

    public AbstractFileStore(final String fileName, final IRepositoryStore<? extends IRepositoryFileStore> parentStore) {
        name = fileName;
        store = parentStore;
        initParameters();
    }

    private void initParameters() {
        parameters = new HashMap<>();
        parameters.put(ASK_ACTION_ON_CLOSE, true);
    }

    @Override
    public String getName() {
        return name;
    }

    protected void setName(final String name) {
        this.name = name;
    }

    @Override
    public IResource getResource() {
        return getParentStore().getResource().getFile(getName());
    }

    @Override
    public String getDisplayName() {
        if (getName().indexOf('.') != -1) {
            return getName().substring(0, getName().lastIndexOf('.'));
        }
        return getName();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IDisplayable#getStyledString()
     */
    @Override
    public StyledString getStyledString() {
        return new StyledString(getName());
    }

    public Repository getRepository() {
        return RepositoryManager.getInstance().getCurrentRepository();
    }

    @Override
    public IRepositoryStore<? extends IRepositoryFileStore> getParentStore() {
        return store;
    }

    @Override
    public boolean isReadOnly() {
        final IResource resource = getResource();
        return resource != null && resource.exists() && resource.getResourceAttributes() != null
                && resource.getResourceAttributes().isReadOnly();
    }

    @Override
    public void setReadOnly(final boolean readOnly) {
        final IResource resource = getResource();
        if (resource != null && resource.exists()) {
            ResourceAttributes resourceAttributes = resource.getResourceAttributes();
            if (resourceAttributes == null) {
                resourceAttributes = new ResourceAttributes();
            }
            resourceAttributes.setReadOnly(readOnly);
            try {
                resource.setResourceAttributes(resourceAttributes);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    @Override
    public void delete() {
        if (!isReadOnly()) {
            fireFileStoreEvent(new FileStoreChangeEvent(EventType.PRE_DELETE, this));
            doDelete();
            getParentStore().refresh();
            fireFileStoreEvent(new FileStoreChangeEvent(EventType.POST_DELETE, this));
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

    /**
     * DON'T OVERRIDE , cannot put final for test purpose
     */
    @Override
    public void save(final Object content) {
        if (!isReadOnly()) {
            fireFileStoreEvent(new FileStoreChangeEvent(EventType.PRE_SAVE, this));
            doSave(content);
            try {
                getResource().refreshLocal(IResource.DEPTH_ZERO, Repository.NULL_PROGRESS_MONITOR);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
            fireFileStoreEvent(new FileStoreChangeEvent(EventType.POST_SAVE, this));
        } else {
            Display.getDefault().syncExec(
                    () -> MessageDialog.openWarning(Display.getDefault().getActiveShell(), Messages.readOnlyFileTitle,
                            Messages.bind(Messages.readOnlyFileWarning, getDisplayName())));
        }
    }

    @Override
    public IWorkbenchPart open() {
        final Display display = Display.getDefault();
        display.syncExec(PlatformUtil::hideIntroPart);
        final boolean[] done = new boolean[1];
        display.asyncExec(() -> {
            fireFileStoreEvent(new FileStoreChangeEvent(EventType.PRE_OPEN, this));
            try {
                activePart = doOpen();
            } catch (Throwable e) {
                done[0] = true;
            }
            registerPartListener(activePart, getActiveWindow());
            fireFileStoreEvent(new FileStoreChangeEvent(EventType.POST_OPEN, AbstractFileStore.this));
            done[0] = true;
        });
        while (!done[0]) {
            display.syncExec(display::readAndDispatch);
        }
        return activePart;
    }

    private void registerPartListener(IWorkbenchPart activePart, IWorkbenchWindow activeWindow) {
        if (activePart != null && activeWindow != null
                && activeWindow.getActivePage() != null) {
            activeWindow.getActivePage().addPartListener(this);
        }
    }

    private IWorkbenchWindow getActiveWindow() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    }

    @Override
    public void close() {
        fireFileStoreEvent(new FileStoreChangeEvent(EventType.PRE_CLOSE, this));
        doClose();
        if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null &&
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null) {
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().removePartListener(this);
        }
        fireFileStoreEvent(new FileStoreChangeEvent(EventType.POST_CLOSE, this, parameters));
    }

    @Override
    public void rename(final String newName) {
        if (!isReadOnly()) {
            if (getParentStore().getChild(newName) != null) {
                throw new IllegalArgumentException(newName + " already exists in this store");
            }
            try {
                getResource().move(getParentStore().getResource().getFullPath().append(newName), true,
                        Repository.NULL_PROGRESS_MONITOR);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
            fireFileStoreEvent(new FileStoreChangeEvent(EventType.POST_SAVE, this));
            setName(newName);
        }
    }

    protected void doDelete() {
        try {
            final IResource r = getResource();
            if (r != null && r.exists()) {
                r.delete(true, Repository.NULL_PROGRESS_MONITOR);
            }
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
    }

    @Override
    public void export(final String targetAbsoluteFilePath) throws IOException {
        checkWritePermission(new File(targetAbsoluteFilePath));
        final IResource file = getResource();
        if (file != null) {
            final File to = new File(targetAbsoluteFilePath);
            to.mkdirs();
            final File target = new File(to, file.getName());
            if (target.exists()) {
                if (FileActionDialog.overwriteQuestion(file.getName())) {
                    PlatformUtil.delete(target, Repository.NULL_PROGRESS_MONITOR);
                } else {
                    return;
                }
            }
            PlatformUtil.copyResource(to, file.getLocation().toFile(), Repository.NULL_PROGRESS_MONITOR);

        }
    }

    protected IProgressService getProgressService() {
        return PlatformUI.getWorkbench().getProgressService();
    }

    protected abstract void doSave(Object content);

    protected abstract IWorkbenchPart doOpen();

    protected abstract void doClose();

    @Override
    public void fireFileStoreEvent(final FileStoreChangeEvent event) {
        final IRepository repository = RepositoryManager.getInstance().getCurrentRepository();
        if (repository != null) {
            repository.handleFileStoreEvent(event);
        }
    }

    @Override
    public void partClosed(final IWorkbenchPart part) {
        if (Objects.equals(part, activePart)) {
            close();
        }
    }

    protected boolean checkWritePermission(File file) throws IOException {
        final String path = file.getAbsolutePath();
        if (!file.isDirectory()) {
            file = file.getParentFile();
        }
        if (file.canWrite()) {
            file = new File(path);
            return true;
        } else {
            throw new IOException(Messages.bind(Messages.writePermission, file.getAbsolutePath()));
        }
    }

    @Override
    public void partOpened(final IWorkbenchPart part) {
    }

    @Override
    public void partDeactivated(final IWorkbenchPart part) {
    }

    @Override
    public void partActivated(final IWorkbenchPart part) {
    }

    @Override
    public void partBroughtToTop(final IWorkbenchPart part) {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (activePart == null ? 0 : activePart.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (store == null ? 0 : store.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof IRepositoryFileStore) {
            return getName().equals(((IRepositoryFileStore) obj).getName())
                    && getParentStore().equals(((IRepositoryFileStore) obj).getParentStore());
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
        return new HashSet<>();
    }

    @Override
    public Set<IRepositoryFileStore> getRelatedFileStore() {
        return Collections.emptySet();
    }

    @Override
    public byte[] toByteArray() throws IOException {
        final IResource resource = getResource();
        if (resource.exists()) {
            final File file = resource.getLocation().toFile();
            if (file.isFile()) {
                return Files.toByteArray(file);
            }
        }
        throw new FileNotFoundException(String.format("%s not found", resource.getLocation().toOSString()));
    }

    public void setParameters(final Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }
}
