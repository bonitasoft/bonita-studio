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
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent.EventType;
import org.bonitasoft.studio.common.repository.model.IFileStoreChangeNotifier;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.progress.IProgressService;

import com.google.common.io.Files;

/**
 * @author Romain Bioteau
 */
public abstract class AbstractFileStore<T>
        implements IRepositoryFileStore<T>, IFileStoreChangeNotifier, IPartListener {

    public static final String ASK_ACTION_ON_CLOSE = "ASK_ACTION_ON_CLOSE";

    private String name;
    protected final IRepositoryStore<? extends IRepositoryFileStore<T>> store;
    private IWorkbenchPart activePart;
    private Map<String, Object> parameters;
    private RepositoryAccessor repositoryAccessor;
    private CommandExecutor commandExecutor = new CommandExecutor();

    public AbstractFileStore(final String fileName,
            final IRepositoryStore<? extends IRepositoryFileStore<T>> parentStore) {
        name = fileName;
        store = parentStore;
        initParameters();
    }

    @Override
    public T getContent() throws ReadFileStoreException {
        doCheckModelVersion();
        return doGetContent();
    }

    protected void doCheckModelVersion() throws ReadFileStoreException {
        if (getResource() != null && getResource().exists()) {
            try (InputStream is = openInputStream()) {
                IStatus status = getParentStore().validate(getName(), is);
                if (status.getSeverity() == IStatus.ERROR) {
                    throw new ReadFileStoreException(status.getMessage());
                }
            } catch (IOException | CoreException e) {
                throw new ReadFileStoreException(e.getMessage(), e);
            }
        }
    }

    protected InputStream openInputStream() throws CoreException {
        if (getResource() instanceof IFile) {
            return ((IFile) getResource()).getContents();
        } else {
            throw new IllegalStateException(String.format("Cannot open an InputStream for %s", getClass().getName()));
        }
    }

    protected abstract T doGetContent() throws ReadFileStoreException;

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

    @Override
    public boolean canBeDeleted() {
        return !isReadOnly();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IDisplayable#getStyledString()
     */
    @Override
    public StyledString getStyledString() {
        return new StyledString(getName());
    }

    public AbstractRepository getRepository() {
        return RepositoryManager.getInstance().getCurrentRepository();
    }

    @Override
    public IRepositoryStore<? extends IRepositoryFileStore<T>> getParentStore() {
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
        if (canBeDeleted()) {
            fireFileStoreEvent(new FileStoreChangeEvent(EventType.PRE_DELETE, this));
            doDelete();
            getParentStore().refresh();
            refreshExplorerView();
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
            IResource resource = getResource();
            boolean exists = resource.exists();
            checkParentExists(resource);
            doSave(content);
            try {
                getResource().refreshLocal(IResource.DEPTH_ZERO, AbstractRepository.NULL_PROGRESS_MONITOR);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
            if (!exists) {
                refreshExplorerView();
            }
            fireFileStoreEvent(new FileStoreChangeEvent(EventType.POST_SAVE, this));
        } else {
            Display.getDefault().syncExec(
                    () -> MessageDialog.openWarning(Display.getDefault().getActiveShell(), Messages.readOnlyFileTitle,
                            Messages.bind(Messages.readOnlyFileWarning, getDisplayName())));
        }
    }

    private void checkParentExists(IResource resource) {
        IContainer parent = resource.getParent();
        if(!parent.exists() && parent instanceof IFolder) {
            try {
                ((IFolder)parent).create(true, true, AbstractRepository.NULL_PROGRESS_MONITOR);
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    public static void refreshExplorerView() {
        Display.getDefault().asyncExec(() -> {
            if (PlatformUI.isWorkbenchRunning()
                    && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
                    && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null) {
                IViewPart viewPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .findView("org.bonitasoft.studio.application.project.explorer");
                if (viewPart != null) {
                    CommonViewer viewer = viewPart.getAdapter(CommonViewer.class);
                    if (viewer != null && !viewer.getTree().isDisposed()) {
                        viewer.refresh(true);
                    }
                }
            }
        });
    }

    @Override
    public IWorkbenchPart open() {
        final Display display = Display.getDefault();
        IStatus status = validate();
        switch (status.getSeverity()) {
            case IStatus.ERROR:
                MessageDialog.openError(display.getActiveShell(), Messages.invalidFile, status.getMessage());
                return null;
            case IStatus.WARNING:
                MessageDialog dialog = new MessageDialog(display.getActiveShell(),
                        Messages.migrationConfirmationTitle,
                        null,
                        Messages.migrationConfirmationMsg,
                        MessageDialog.WARNING,
                        0,
                        Messages.continueLabel,
                        IDialogConstants.CANCEL_LABEL);
                if (dialog.open() == Dialog.OK) {
                    try {
                        getParentStore().migrate(this, AbstractRepository.NULL_PROGRESS_MONITOR);
                    } catch (CoreException | MigrationException e) {
                        MessageDialog.openError(display.getActiveShell(), Messages.migrationError, e.getMessage());
                        return null;
                    }
                    break;
                }
                return null;
            default:
                break;
        }

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
    public void renameLegacy(final String newName) {
        if (!isReadOnly()) {
            if (getParentStore().getChild(newName, true) != null) {
                throw new IllegalArgumentException(newName + " already exists in this store");
            }
            try {
                getResource().move(getParentStore().getResource().getFullPath().append(newName), true,
                        AbstractRepository.NULL_PROGRESS_MONITOR);
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
                r.delete(true, AbstractRepository.NULL_PROGRESS_MONITOR);
            }
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
    }

    @Override
    public IStatus export(final String targetAbsoluteFilePath) throws IOException {
        checkWritePermission(new File(targetAbsoluteFilePath));
        final IResource file = getResource();
        if (file != null) {
            final File to = new File(targetAbsoluteFilePath);
            to.mkdirs();
            final File target = new File(to, file.getName());
            if (target.exists()) {
                if (FileActionDialog.overwriteQuestion(file.getName())) {
                    PlatformUtil.delete(target, AbstractRepository.NULL_PROGRESS_MONITOR);
                } else {
                    return ValidationStatus.cancel("");
                }
            }
            PlatformUtil.copyResource(to, file.getLocation().toFile(), AbstractRepository.NULL_PROGRESS_MONITOR);
            return ValidationStatus.ok();
        }
        return ValidationStatus.error(String.format(Messages.failedToRetrieveResourceToExport, getName()));
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
            partClosed();
        }
    }

    protected void partClosed() {
        //SUBCLASS MAY OVERRIDE THIS METHOD
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
            return getName().equals(((IRepositoryFileStore<?>) obj).getName())
                    && getParentStore().equals(((IRepositoryFileStore<?>) obj).getParentStore());
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
    public Set<IRepositoryFileStore<?>> getRelatedFileStore() {
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

    protected Object executeCommand(String command, Map<String, Object> parameters) {
        return commandExecutor.executeCommand(command, parameters);
    }

    public RepositoryAccessor getRepositoryAccessor() {
        if (repositoryAccessor == null) {
            repositoryAccessor = new RepositoryAccessor();
            repositoryAccessor.init();
        }
        return repositoryAccessor;
    }

    protected String stripExtension(String name, String extension) {
        return name.toLowerCase().endsWith(extension) ? name.replace(extension, "") : name;
    }

    public boolean isDirty() {
        return findOpenedEditor()
                .map(IEditorPart::isDirty)
                .orElse(false);
    }

    protected Optional<IEditorPart> findOpenedEditor() {
        return EditorFinder.findOpenedEditor(this, this::validateEditorInstance);
    }

    public void saveOpenedEditor() {
        EditorFinder.findOpenedEditor(this, this::validateEditorInstance)
                .ifPresent(editor -> editor.doSave(new NullProgressMonitor()));
    }

    /**
     * Should be overwritten by fileStores with dedicated editors!!
     */
    protected boolean validateEditorInstance(IEditorPart editor) {
        return true;
    }

    @Override
    public IStatus validate() {
        IResource resource = getResource();
        if (resource instanceof IFile) {
            try (InputStream is = openInputStream()) {
                return getParentStore().validate(resource.getName(), is);
            } catch (IOException | CoreException e) {
                BonitaStudioLog.error(e);
                return ValidationStatus.error("Failed to validate file model", e);
            }
        }
        return ValidationStatus.ok();
    }
}
