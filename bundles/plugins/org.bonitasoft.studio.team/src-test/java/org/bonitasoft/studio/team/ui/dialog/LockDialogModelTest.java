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
package org.bonitasoft.studio.team.ui.dialog;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.team.core.SVNLockManager;
import org.bonitasoft.studio.team.operations.ScanResourcesLockOperation.LockStatus;
import org.bonitasoft.studio.team.repository.Repository;
import org.bonitasoft.studio.team.ui.provider.TeamRepositoryLabelDecorator;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.team.svn.core.resource.IRepositoryResource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Romain Bioteau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class LockDialogModelTest {

    @Mock
    private Repository repository;

    @Mock
    private SVNLockManager lockManager;

    @Mock
    private TeamRepositoryLabelDecorator decorator;

    private LockDialogModel lockDialogModel;

    @Mock
    private IRepositoryFileStore fileStore;

    @Mock
    private IFile file;

    @Mock
    private IRepositoryResource repositoryResource;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        lockDialogModel = spy(new LockDialogModel(repository, lockManager, decorator));
        doReturn(repositoryResource).when(lockDialogModel).asRepositoryFile(any(IResource.class));
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_handleLockSelection_add_resource_to_lock_resource() throws Exception {
        when(fileStore.getResource()).thenReturn(file);

        lockDialogModel.handleLockSelection(fileStore);

        assertThat(lockDialogModel.getResourcesToLock()).contains(fileStore);
    }

    @Test
    public void should_handleLockSelection_update_lock_status_to_LOCALLY_LOCKED() throws Exception {
        when(fileStore.getResource()).thenReturn(file);

        lockDialogModel.handleLockSelection(fileStore);

        verify(decorator).updateLockStatus(file, LockStatus.LOCALLY_LOCKED);
    }

    @Test
    public void should_handleLockSelection_update_lock_owner() throws Exception {
        when(fileStore.getResource()).thenReturn(file);
        when(repository.getUser()).thenReturn("romain");

        lockDialogModel.handleLockSelection(fileStore);

        verify(decorator).updateLockOwner(file, "romain");
    }

    @Test
    public void should_handleUnlockSelection_add_resource_to_unlock_resource() throws Exception {
        when(fileStore.getResource()).thenReturn(file);

        lockDialogModel.handleUnlockSelection(fileStore);

        assertThat(lockDialogModel.getResourcesToUnlock()).contains(fileStore);
    }

    @Test
    public void should_handleUnlockSelection_add_resource_to_lock_resource_if_steal_lock_on_opened_diagram() throws Exception {
        when(fileStore.getResource()).thenReturn(file);
        doReturn(true).when(lockDialogModel).openEditModeConfirmation(fileStore);
        doReturn(true).when(lockDialogModel).isResourceIsOpened(fileStore);

        lockDialogModel.handleUnlockSelection(fileStore);

        assertThat(lockDialogModel.getResourcesToLock()).contains(fileStore);
    }

    @Test
    public void should_handleUnlockSelection_update_lock_status_if_steal_lock_on_opened_diagram() throws Exception {
        when(fileStore.getResource()).thenReturn(file);
        doReturn(true).when(lockDialogModel).openEditModeConfirmation(fileStore);
        doReturn(true).when(lockDialogModel).isResourceIsOpened(fileStore);

        lockDialogModel.handleUnlockSelection(fileStore);

        verify(decorator).updateLockStatus(file, LockStatus.LOCALLY_LOCKED);
    }

    @Test
    public void should_handleUnlockSelection_update_lock_owner_if_steal_lock_on_opened_diagram() throws Exception {
        when(fileStore.getResource()).thenReturn(file);
        doReturn(true).when(lockDialogModel).openEditModeConfirmation(fileStore);
        doReturn(true).when(lockDialogModel).isResourceIsOpened(fileStore);
        when(repository.getUser()).thenReturn("romain");

        lockDialogModel.handleUnlockSelection(fileStore);

        verify(decorator).updateLockOwner(file, "romain");
    }

    @Test
    public void should_handleUnlockSelection_update_lock_status_if_repository_resource_is_null() throws Exception {
        when(fileStore.getResource()).thenReturn(file);
        doReturn(null).when(lockDialogModel).asRepositoryFile(file);

        lockDialogModel.handleUnlockSelection(fileStore);

        verify(decorator).updateLockStatus(file, LockStatus.NOT_LOCKED);
    }

    @Test
    public void should_applyChanges_call_lockManager_to_release_locks_on_resources() throws Exception {
        when(fileStore.getResource()).thenReturn(file);

        lockDialogModel.addResourceToUnlock(fileStore);

        lockDialogModel.applyChanges();

        verify(lockManager).releaseLock(fileStore);
        verify(lockManager).runBreakLockOperation(new IResource[] { file });
    }

    @Test
    public void should_applyChanges_call_lockManager_to_gain_lock_on_resources() throws Exception {
        lockDialogModel.addResourceToLock(fileStore);

        lockDialogModel.applyChanges();

        verify(lockManager).lock(fileStore);
    }

    @Test
    public void should_applyChanges_call_lockManager_to_refresh_all_locks() throws Exception {
        lockDialogModel.applyChanges();

        verify(lockManager).refreshLocks();
    }

    @Test
    public void should_isLockEnabled_return_true_if_selection_is_NOT_LOCKED() throws Exception {
        when(fileStore.getResource()).thenReturn(file);
        when(decorator.getLockStatus(file)).thenReturn(LockStatus.NOT_LOCKED);

        assertThat(lockDialogModel.isLockEnabled(new StructuredSelection(fileStore))).isTrue();
    }

    @Test
    public void should_isLockEnabled_return_true_if_selection_is_BROKEN() throws Exception {
        when(fileStore.getResource()).thenReturn(file);
        when(decorator.getLockStatus(file)).thenReturn(LockStatus.BROKEN);

        assertThat(lockDialogModel.isLockEnabled(new StructuredSelection(fileStore))).isTrue();
    }

    @Test
    public void should_isLockEnabled_return_true_if_selection_is_null() throws Exception {
        when(fileStore.getResource()).thenReturn(file);
        when(decorator.getLockStatus(file)).thenReturn(null);

        assertThat(lockDialogModel.isLockEnabled(new StructuredSelection(fileStore))).isTrue();
    }

    @Test
    public void should_isLockEnabled_return_false_if_selection_is_null() throws Exception {
        assertThat(lockDialogModel.isLockEnabled(null)).isFalse();
    }

    @Test
    public void should_isUnlockEnabled_return_false_if_selection_is_null() throws Exception {
        assertThat(lockDialogModel.isUnLockEnabled(null)).isFalse();
    }

    @Test
    public void should_isUnlockEnabled_return_true_if_selection_is_LOCALLY_LOCKED() throws Exception {
        when(fileStore.getResource()).thenReturn(file);
        doReturn(false).when(lockDialogModel).isResourceIsOpened(fileStore);
        when(decorator.getLockStatus(file)).thenReturn(LockStatus.LOCALLY_LOCKED);

        assertThat(lockDialogModel.isUnLockEnabled(new StructuredSelection(fileStore))).isTrue();
    }

    @Test
    public void should_isUnlockEnabled_return_true_if_selection_is_STOLEN() throws Exception {
        when(fileStore.getResource()).thenReturn(file);
        doReturn(true).when(lockDialogModel).isResourceIsOpened(fileStore);
        when(decorator.getLockStatus(file)).thenReturn(LockStatus.STOLEN);

        assertThat(lockDialogModel.isUnLockEnabled(new StructuredSelection(fileStore))).isTrue();
    }

    @Test
    public void should_isUnlockEnabled_return_false_if_selection_is_LOCALLY_LOCKED_and_resource_is_opened() throws Exception {
        when(fileStore.getResource()).thenReturn(file);
        when(decorator.getLockStatus(file)).thenReturn(LockStatus.LOCALLY_LOCKED);
        doReturn(true).when(lockDialogModel).isResourceIsOpened(fileStore);

        assertThat(lockDialogModel.isUnLockEnabled(new StructuredSelection(fileStore))).isFalse();
    }

    @Test
    public void should_isUnlockEnabled_return_false_if_selection_is_OTHER_LOCKED_and_resource_is_opened() throws Exception {
        when(fileStore.getResource()).thenReturn(file);
        when(decorator.getLockStatus(file)).thenReturn(LockStatus.OTHER_LOCKED);
        doReturn(false).when(lockDialogModel).isResourceIsOpened(fileStore);

        assertThat(lockDialogModel.isUnLockEnabled(new StructuredSelection(fileStore))).isTrue();
    }

}
