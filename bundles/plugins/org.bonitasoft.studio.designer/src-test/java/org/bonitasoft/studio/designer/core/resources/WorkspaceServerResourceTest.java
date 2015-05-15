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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.nio.file.Path;

import org.bonitasoft.studio.common.repository.extension.IGetLockStatusOperation;
import org.bonitasoft.studio.common.repository.extension.ILockedResourceStatus;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.designer.core.resources.LockStatus;
import org.bonitasoft.studio.designer.core.resources.LockStatusOperationFactory;
import org.bonitasoft.studio.designer.core.resources.LockStatusOperationNotFound;
import org.bonitasoft.studio.designer.core.resources.LockedResourceException;
import org.bonitasoft.studio.designer.core.resources.RepositoryNotifier;
import org.bonitasoft.studio.designer.core.resources.ResourceNotFoundException;
import org.bonitasoft.studio.designer.core.resources.WorkspaceAPIEvent;
import org.bonitasoft.studio.designer.core.resources.WorkspaceServerResource;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.restlet.Response;
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class WorkspaceServerResourceTest {

    @Spy
    private WorkspaceServerResource workspaceServerResource;
    @Mock
    private IRepository repository;
    @Mock
    private IRepositoryFileStore fileStore;
    @Mock
    private RepositoryNotifier repositoryNotifier;
    @Mock
    private LockStatusOperationFactory locakStatusOperationFactory;

    @Mock
    private IGetLockStatusOperation locakStatusOperation;
    @Mock
    private ILockedResourceStatus lockStatus;
    @Mock
    private IResource resource;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        doReturn(repository).when(workspaceServerResource).currentRepository();
        doReturn(locakStatusOperationFactory).when(workspaceServerResource).newLockStatusOperationFactory();
        doReturn(WorkspaceServerResource.GET_LOCK_STATUS).when(workspaceServerResource).getAttribute("action");
        doReturn("a/file/path").when(workspaceServerResource).getAttribute("filePath");
        doReturn(locakStatusOperation).when(locakStatusOperationFactory).newLockStatusOperation();
        doNothing().when(workspaceServerResource).logException(anyString(), any(Throwable.class));
    }

    @Test
    public void should_retrieve_url_attributes_when_initializing_resource() throws Exception {
        doReturn("anAction").when(workspaceServerResource).getAttribute("action");
        doReturn("aFilePath").when(workspaceServerResource).getAttribute("filePath");

        workspaceServerResource.doInit();

        assertThat(workspaceServerResource.getFilePath()).isEqualTo("aFilePath");
        assertThat(workspaceServerResource.getAction()).isEqualTo("anAction");
        assertThat(workspaceServerResource.getRepository()).isSameAs(repository);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_an_IllegalArgumentException_when_action_attribute_is_null() throws Exception {
        doReturn(null).when(workspaceServerResource).getAttribute("action");

        workspaceServerResource.doInit();
    }

    @Test
    public void should_set_response_status_to_404_when_catching_a_ResourceNotFoundException() throws Exception {
        workspaceServerResource.setResponse(new Response(null));
        workspaceServerResource.doCatch(new ResourceException(new ResourceNotFoundException("")));

        final Response response = workspaceServerResource.getResponse();
        assertThat(response).isNotNull();
        final Status status = response.getStatus();
        assertThat(status).isNotNull();
        assertThat(status.getCode()).isEqualTo(Status.CLIENT_ERROR_NOT_FOUND.getCode());
    }

    @Test
    public void should_set_response_status_to_423_when_catching_a_LockedResourceException() throws Exception {
        workspaceServerResource.setResponse(new Response(null));
        workspaceServerResource.doCatch(new ResourceException(new LockedResourceException()));

        final Response response = workspaceServerResource.getResponse();
        assertThat(response).isNotNull();
        final Status status = response.getStatus();
        assertThat(status).isNotNull();
        assertThat(status.getCode()).isEqualTo(Status.CLIENT_ERROR_LOCKED.getCode());
    }

    @Test
    public void should_notify_repository_when_dispatching_a_post_request() throws Exception {
        doReturn(WorkspaceAPIEvent.PRE_OPEN.name()).when(workspaceServerResource).getAttribute("action");
        doReturn("").when(workspaceServerResource).getAttribute("filePath");
        doReturn(fileStore).when(repository).asRepositoryFileStore(notNull(Path.class));
        doReturn(repositoryNotifier).when(workspaceServerResource).newRepositoryNotifier();
        workspaceServerResource.doInit();

        workspaceServerResource.dispatch("a/file/path");
        verify(repositoryNotifier).dispatch(WorkspaceAPIEvent.PRE_OPEN, fileStore);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_an_IllegalArgumentException_if_filePath_is_null() throws Exception {
        doReturn(WorkspaceAPIEvent.PRE_OPEN.name()).when(workspaceServerResource).getAttribute("action");
        doReturn("").when(workspaceServerResource).getAttribute("filePath");
        workspaceServerResource.doInit();

        workspaceServerResource.dispatch(null);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void should_throw_a_ResourceNotFoundException_if_no_fileStore_is_found_for_the_givent_filePath() throws Exception {
        doReturn(WorkspaceAPIEvent.PRE_OPEN.name()).when(workspaceServerResource).getAttribute("action");
        doReturn("").when(workspaceServerResource).getAttribute("filePath");
        doReturn(null).when(repository).asRepositoryFileStore(notNull(Path.class));
        workspaceServerResource.doInit();

        workspaceServerResource.dispatch("aFilePath");
    }

    @Test(expected = ResourceNotFoundException.class)
    public void should_throw_a_ResourceNotFoundException_if_repository_throw_an_IOException() throws Exception {
        doReturn(WorkspaceAPIEvent.PRE_OPEN.name()).when(workspaceServerResource).getAttribute("action");
        doReturn("").when(workspaceServerResource).getAttribute("filePath");
        doThrow(new IOException()).when(repository).asRepositoryFileStore(notNull(Path.class));
        workspaceServerResource.doInit();

        workspaceServerResource.dispatch("aFilePath");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_an_IllegalArgumentException_if_get_action_is_invalid() throws Exception {
        doReturn("invalidAction").when(workspaceServerResource).getAttribute("action");
        workspaceServerResource.doInit();

        workspaceServerResource.getLockStatus();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_an_IllegalArgumentException_if_filePath_is_null_or_empty() throws Exception {
        doReturn("").when(workspaceServerResource).getAttribute("filePath");
        workspaceServerResource.doInit();

        workspaceServerResource.getLockStatus();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_an_ResourceNotFoundException_if_filePath_cannot_be_url_decoded() throws Exception {
        doReturn("an#bad%url:encoded path").when(workspaceServerResource).getAttribute("filePath");
        workspaceServerResource.doInit();

        workspaceServerResource.getLockStatus();
    }

    @Test
    public void should_have_an_unlocked_status_if_LockStatusOperationNotFound_is_thrown() throws Exception {
        doThrow(new LockStatusOperationNotFound()).when(locakStatusOperationFactory).newLockStatusOperation();
        workspaceServerResource.doInit();

        final String lockStatus = workspaceServerResource.getLockStatus();

        assertThat(LockStatus.valueOf(lockStatus)).isEqualTo(LockStatus.UNLOCKED);
    }

    @Test
    public void should_have_a_localy_locked_status() throws Exception {
        doReturn(fileStore).when(repository).asRepositoryFileStore(notNull(Path.class));
        doReturn(resource).when(fileStore).getResource();
        doReturn(lockStatus).when(locakStatusOperation).scanLock(resource);
        doReturn(true).when(lockStatus).isLocalyLocked();
        workspaceServerResource.doInit();

        final String lockStatus = workspaceServerResource.getLockStatus();

        assertThat(LockStatus.valueOf(lockStatus)).isEqualTo(LockStatus.LOCKED_BY_ME);
    }

    @Test
    public void should_have_a_locked_status() throws Exception {
        doReturn(fileStore).when(repository).asRepositoryFileStore(notNull(Path.class));
        doReturn(resource).when(fileStore).getResource();
        doReturn(lockStatus).when(locakStatusOperation).scanLock(resource);
        doReturn(true).when(lockStatus).isLockedByOther();
        workspaceServerResource.doInit();

        final String lockStatus = workspaceServerResource.getLockStatus();

        assertThat(LockStatus.valueOf(lockStatus)).isEqualTo(LockStatus.LOCKED_BY_OTHER);
    }

    @Test
    public void should_have_a_not_locked_status_if_resource_is_neither_localy_locked_nor_locked_by_other() throws Exception {
        doReturn(fileStore).when(repository).asRepositoryFileStore(notNull(Path.class));
        doReturn(resource).when(fileStore).getResource();
        doReturn(lockStatus).when(locakStatusOperation).scanLock(resource);
        doReturn(false).when(lockStatus).isLockedByOther();
        doReturn(false).when(lockStatus).isLocalyLocked();
        workspaceServerResource.doInit();

        final String lockStatus = workspaceServerResource.getLockStatus();

        assertThat(LockStatus.valueOf(lockStatus)).isEqualTo(LockStatus.UNLOCKED);
    }

    @Test
    public void should_have_a_not_locked_status_if_a_CoreException_is_thrown() throws Exception {
        doThrow(new CoreException(ValidationStatus.error(""))).when(locakStatusOperationFactory).newLockStatusOperation();
        workspaceServerResource.doInit();

        final String lockStatus = workspaceServerResource.getLockStatus();

        assertThat(LockStatus.valueOf(lockStatus)).isEqualTo(LockStatus.UNLOCKED);
    }

    @Test
    public void log_catched_throwable() throws Exception {
        final Throwable throwable = new Throwable();

        workspaceServerResource.doCatch(throwable);

        verify(workspaceServerResource).logException("WorkspaceServerResource interal error", throwable);
    }
}
