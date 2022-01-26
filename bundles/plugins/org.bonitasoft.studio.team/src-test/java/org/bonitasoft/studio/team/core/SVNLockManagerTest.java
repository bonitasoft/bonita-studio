package org.bonitasoft.studio.team.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.team.operations.ScanResourcesLockOperation.LockStatus;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.widgets.Display;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SVNLockManagerTest {

    @Mock
    private IResource resource;

    private SVNLockManager lockManager;

    @Mock
    private DiagramRepositoryStore store;

    @Mock
    private IWorkspace workspace;
    @Mock
    private Display display;

    @Before
    public void setUp() throws Exception {
        lockManager = spy(new SVNLockManager(store));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testReleaseLock_whenAutoShare() throws CoreException {
        doNothing().when(lockManager).releaseLock(resource);
        lockManager.releaseLock(resource, true, display);
        verify(lockManager, atLeastOnce()).releaseLock(resource);
    }

    @Test
    public void testReleaseLock_whenNotAutoShare() throws CoreException {
        lockManager.releaseLock(resource, false, display);
        verify(display, atLeastOnce()).asyncExec(notNull());
    }

    @Test
    public void testNotReleaseLock_WhenOpenReleaseLockQuestion_isNo() throws CoreException {
        lockManager.releaseLock(resource, false, display);
        verify(lockManager, never()).releaseLock(resource);
    }

    @Test
    public void should_canBeLocked_if_LockStatus_is_NOT_LOCKED() throws CoreException {
        assertThat(SVNLockManager.canBeLocked(LockStatus.NOT_LOCKED)).isTrue();
    }

    @Test
    public void should_canBeLocked_if_LockStatus_is_BROKEN() throws CoreException {
        assertThat(SVNLockManager.canBeLocked(LockStatus.BROKEN)).isTrue();
    }

    @Test
    public void should_canBeLocked_if_LockStatus_is_null() throws CoreException {
        assertThat(SVNLockManager.canBeLocked(null)).isTrue();
    }

    @Test
    public void should_not_canBeLocked_if_LockStatus_is_LOCALLY_LOCKED() throws CoreException {
        assertThat(SVNLockManager.canBeLocked(LockStatus.LOCALLY_LOCKED)).isFalse();
    }

    @Test
    public void should_not_canBeLocked_if_LockStatus_is_STOLEN() throws CoreException {
        assertThat(SVNLockManager.canBeLocked(LockStatus.STOLEN)).isFalse();
    }

    @Test
    public void should_not_canBeLocked_if_LockStatus_is_OTHER_LOCKED() throws CoreException {
        assertThat(SVNLockManager.canBeLocked(LockStatus.OTHER_LOCKED)).isFalse();
    }

    @Test
    public void should_canBeUnLocked_if_LockStatus_is_OTHER_LOCKED() throws CoreException {
        assertThat(SVNLockManager.canBeUnlocked(LockStatus.OTHER_LOCKED)).isTrue();
    }

    @Test
    public void should_canBeUnLocked_if_LockStatus_is_STOLEN() throws CoreException {
        assertThat(SVNLockManager.canBeUnlocked(LockStatus.STOLEN)).isTrue();
    }

    @Test
    public void should_not_canBeUnLocked_if_LockStatus_is_null() throws CoreException {
        assertThat(SVNLockManager.canBeUnlocked(null)).isFalse();
    }

}
