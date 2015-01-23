/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.pagedesigner.core.resources;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.file.Path;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.extension.IGetLockStatusOperation;
import org.bonitasoft.studio.common.repository.extension.ILockedResourceStatus;
import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent;
import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent.EventType;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.workspace.common.LockStatus;
import org.bonitasoft.workspace.common.LockedResourceException;
import org.bonitasoft.workspace.common.ResourceNotFoundException;
import org.bonitasoft.workspace.common.WorkspaceAPIConstants;
import org.bonitasoft.workspace.common.WorkspaceAPIEvent;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;


/**
 * @author Romain Bioteau
 *
 */
public class WorkspaceServerResource extends ServerResource {

    public static final String ACTION_ATTRIBUTE = "action";
    public static final String FILEPATH_ATTRIBUTE = "filePath";

    private String action;

    private IRepository repository;
    private String filePath;

    @Override
    public void doInit() {
        super.doInit();
        repository = RepositoryManager.getInstance().getCurrentRepository();
        action = getAttribute(ACTION_ATTRIBUTE);
        if (action == null) {
            throw new IllegalArgumentException("action attribute is missing from request");
        }
        filePath = getAttribute(FILEPATH_ATTRIBUTE);
    }

    @Post
    public void dispatch(final String filePath) throws ResourceNotFoundException, LockedResourceException {
        if (filePath == null) {
            throw new IllegalArgumentException("filePath is null");
        }
        final WorkspaceAPIEvent event = WorkspaceAPIEvent.valueOf(action);
        final IRepositoryFileStore fileStore = toFileStore(new File(filePath).toPath());
        if (fileStore == null) {
            throw new ResourceNotFoundException(filePath);
        }
        switch (event) {
            case PRE_OPEN:
                preOpen(fileStore);
                break;
            case POST_CLOSE:
                postClose(fileStore);
                break;
            case POST_DELETE:
                postDelete(fileStore);
                break;
            case PRE_IMPORT:
                preImport();
                break;
            case POST_IMPORT:
                postImport();
                break;
            default:
                throw new IllegalStateException("Unknown action");
        }
    }

    @Get
    public String getLockStatus() throws ResourceNotFoundException {
        if (filePath == null) {
            throw new IllegalArgumentException("filePath is not set");
        }
        if (!WorkspaceAPIConstants.GET_LOCK_STATUS.equals(action)) {
            throw new IllegalArgumentException("Unsupported action attribute "+action);
        }
        try {
            final String decodedPath = URLDecoder.decode(filePath, "UTF-8");
            final Path path = new File(decodedPath).toPath();
            final IRepositoryFileStore fileStore = toFileStore(path);
            if (fileStore == null) {
                throw new ResourceNotFoundException(filePath);
            }
            final IGetLockStatusOperation op = getLockStatusOperation();
            final ILockedResourceStatus scanLock = op.scanLock(fileStore.getResource());
            if (scanLock.isLocalyLocked()) {
                return LockStatus.LOCKED_BY_ME.name();
            } else if (scanLock.isLockedByOther()) {
                return LockStatus.LOCKED_BY_OTHER.name();
            }
        } catch (final UnsupportedEncodingException e) {
            throw new ResourceNotFoundException(filePath);
        } catch (final InvocationTargetException e) {
            BonitaStudioLog.error(e);
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
        return LockStatus.UNLOCKED.name();
    }

    protected IGetLockStatusOperation getLockStatusOperation() {
        final IConfigurationElement[] configurationElements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(
                "org.bonitasoft.studio.common.repository.lockStatusProvider");
        for (final IConfigurationElement element : configurationElements) {
            try {
                return (IGetLockStatusOperation) element.createExecutableExtension("class");
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        return null;
    }

    @Override
    protected void doCatch(final Throwable throwable) {
        super.doCatch(throwable);
        if (throwable.getCause() instanceof ResourceNotFoundException) {
            getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND, throwable.getCause());
        }
        if (throwable.getCause() instanceof LockedResourceException) {
            getResponse().setStatus(Status.CLIENT_ERROR_LOCKED, throwable.getCause());
        }
    }

    protected void postImport() {
        repository.notifyFileStoreEvent(new FileStoreChangeEvent(EventType.POST_IMPORT, null));
    }

    protected void preImport() {
        repository.notifyFileStoreEvent(new FileStoreChangeEvent(EventType.PRE_IMPORT, null));
    }

    protected void postDelete(final IRepositoryFileStore fileStore) throws ResourceNotFoundException {
        repository.notifyFileStoreEvent(new FileStoreChangeEvent(EventType.POST_DELETE, fileStore));
    }

    protected void postClose(final IRepositoryFileStore fileStore) throws ResourceNotFoundException {
        repository.notifyFileStoreEvent(new FileStoreChangeEvent(EventType.POST_CLOSE, fileStore));
    }

    protected void preOpen(final IRepositoryFileStore fileStore) throws LockedResourceException, ResourceNotFoundException {
        repository.notifyFileStoreEvent(new FileStoreChangeEvent(EventType.PRE_OPEN, fileStore));
    }

    protected IRepositoryFileStore toFileStore(final Path path) throws ResourceNotFoundException {
        Path resolvedPath = path;
        if (!resolvedPath.isAbsolute()) {
            resolvedPath = repository.getProject().getLocation().toFile().toPath().resolve(resolvedPath);
        }
        if (!resolvedPath.toFile().exists()) {
            throw new ResourceNotFoundException(resolvedPath.toFile().getAbsolutePath());
        }

        final URI rawLocationURI = repository.getProject().getLocationURI();
        final URI relativize = rawLocationURI.relativize(resolvedPath.toUri());
        final IFile iResource = repository.getProject().getFile(org.eclipse.core.runtime.Path.fromOSString(relativize.toString()));
        return repository.asRepositoryFileStore(iResource);
    }

}
