/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.designer.core.resources;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.extension.ILockedResourceStatus;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.designer.core.repository.WebFragmentRepositoryStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.designer.core.repository.WebWidgetRepositoryStore;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.restlet.Response;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

/**
 * @author Romain Bioteau
 */
public class WorkspaceServerResource extends ServerResource {

    public static final String ACTION_ATTRIBUTE = "action";
    protected static final String FILEPATH_ATTRIBUTE = "filePath";
    protected static final String GET_LOCK_STATUS = "lockStatus";
    private static boolean enabled = true;

    private String action;
    private IRepository repository;
    private String filePath;
    private RepositoryNotifier repositoryNotifier;
    private LockStatusOperationFactory lockStatusOperationFactory;

    @Override
    public void doInit() {
        super.doInit();
        repository = currentRepository();
        repositoryNotifier = newRepositoryNotifier();
        lockStatusOperationFactory = newLockStatusOperationFactory();
        action = getAttribute(ACTION_ATTRIBUTE);
        if (action == null) {
            throw new IllegalArgumentException("action attribute is missing from request");
        }
        filePath = getAttribute(FILEPATH_ATTRIBUTE);
    }

    public static void disable() {
        enabled = false;
    }

    public static void enable() {
        new WorkspaceJob("Refreshing UID resources...") {
            
            @Override
            public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
                AbstractRepository currentRepository = RepositoryManager.getInstance().getCurrentRepository();
                WebPageRepositoryStore pageRepositoryStore = currentRepository.getRepositoryStore(WebPageRepositoryStore.class);
                pageRepositoryStore.getResource().refreshLocal(IResource.DEPTH_INFINITE, monitor);
                WebFragmentRepositoryStore fragmentRepositoryStore = RepositoryManager.getInstance().getCurrentRepository().getRepositoryStore(WebFragmentRepositoryStore.class);
                fragmentRepositoryStore.getResource().refreshLocal(IResource.DEPTH_INFINITE, monitor);
                WebWidgetRepositoryStore widgetRepositoryStore = RepositoryManager.getInstance().getCurrentRepository().getRepositoryStore(WebWidgetRepositoryStore.class);
                widgetRepositoryStore.getResource().refreshLocal(IResource.DEPTH_INFINITE, monitor);
                AbstractFileStore.refreshExplorerView();
                return org.eclipse.core.runtime.Status.OK_STATUS;
            }
        }.schedule();
        enabled = true;
    }

    protected LockStatusOperationFactory newLockStatusOperationFactory() {
        return new LockStatusOperationFactory();
    }

    protected RepositoryNotifier newRepositoryNotifier() {
        return new RepositoryNotifier(repository);
    }

    protected IRepository currentRepository() {
        return RepositoryManager.getInstance().getCurrentRepository();
    }

    protected String getFilePath() {
        return filePath;
    }

    protected IRepository getRepository() {
        return repository;
    }

    protected String getAction() {
        return action;
    }

    @Post("text/plain")
    public void dispatch(final String filePath) throws ResourceNotFoundException, LockedResourceException {
        if (enabled) {
            checkArgument(!isNullOrEmpty(filePath), "filePath is null or empty");
            final IRepositoryFileStore fileStore = toFileStore(filePath);
            repositoryNotifier.dispatch(WorkspaceAPIEvent.valueOf(action), fileStore);
        }
    }

    @Get
    public String getLockStatus() throws ResourceNotFoundException {
        checkArgument(GET_LOCK_STATUS.equals(action), "Unsupported action attribute " + action);
        checkArgument(!isNullOrEmpty(filePath), "filePath is not set");
        try {
            final ILockedResourceStatus scanLock = lockStatusOperationFactory.newLockStatusOperation().scanLock(
                    toFileStore(urlDecode(filePath, "UTF-8")).getResource());
            if (scanLock.isLocalyLocked()) {
                return LockStatus.LOCKED_BY_ME.name();
            } else if (scanLock.isLockedByOther()) {
                return LockStatus.LOCKED_BY_OTHER.name();
            }
        } catch (final InvocationTargetException | CoreException e) {
            logException("Failed to retrieve lock status", e);
        } catch (final LockStatusOperationNotFound e) {
            return LockStatus.UNLOCKED.name();
        }
        return LockStatus.UNLOCKED.name();
    }

    protected String urlDecode(final String path, final String encoding) {
        String decodedPath = null;
        try {
            decodedPath = URLDecoder.decode(filePath, "UTF-8");
        } catch (final UnsupportedEncodingException e) {
            BonitaStudioLog.error(String.format("Unsupported Encoding: %s", encoding), e);
        }
        return decodedPath;
    }

    protected IRepositoryFileStore toFileStore(final String filePath) {
        try {
            return repository.asRepositoryFileStore(new File(filePath).toPath(), true);
        } catch (final IOException | CoreException e) {
            return null;
        }
    }

    @Override
    protected void doCatch(final Throwable throwable) {
        super.doCatch(throwable);
        final Response response = getResponse();
        if (response != null) {
            final Throwable cause = throwable.getCause();
            if (cause instanceof ResourceNotFoundException) {
                response.setStatus(Status.CLIENT_ERROR_NOT_FOUND, cause);
            }
            if (cause instanceof LockedResourceException) {
                response.setStatus(Status.CLIENT_ERROR_LOCKED, cause);
            }
        }
        logException("WorkspaceServerResource internal error", throwable);
    }

    protected void logException(final String message, final Throwable throwable) {
        BonitaStudioLog.error(message, throwable);
    }

}
