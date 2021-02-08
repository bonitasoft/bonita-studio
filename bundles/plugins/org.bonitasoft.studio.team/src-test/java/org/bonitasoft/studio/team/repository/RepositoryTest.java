package org.bonitasoft.studio.team.repository;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.extension.ExtensionContextInjectionFactory;
import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent;
import org.bonitasoft.studio.common.repository.filestore.FileStoreChangeEvent.EventType;
import org.bonitasoft.studio.common.repository.jdt.JDTTypeHierarchyManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.team.core.SVNLockManager;
import org.bonitasoft.studio.team.operations.ReleaseResourcesLockOperation;
import org.bonitasoft.studio.team.operations.ScanResourcesLockOperation;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.swt.widgets.Display;
import org.eclipse.team.svn.core.resource.IRepositoryResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryTest {

    Repository sharedRepository;

    @Mock
    IRepositoryFileStore fileStore;

    @Mock
    IResource resource;

    @Mock
    ScanResourcesLockOperation scan;

    @Mock
    ReleaseResourcesLockOperation releaseOp;

    @Mock
    IWorkspace workspace;

    @Mock
    IRepositoryResource repResource;

    @Mock
    SVNLockManager lockManager;

    @Mock
    private ExtensionContextInjectionFactory extensionContextInjectionFactory;
    @Mock
    private JDTTypeHierarchyManager jdtManager;
    @Mock
    private IProject project;
    @Mock
    private IEventBroker eventBroker;

    @Before
    public void setUp() throws Exception, CoreException {
        sharedRepository = spy(new Repository(workspace, project, extensionContextInjectionFactory, jdtManager, eventBroker, true));
        doReturn(lockManager).when(sharedRepository).createLockManager();
        when(lockManager.runScanResourcesLockOperation(any(IResource.class))).thenReturn(scan);
        when(fileStore.getResource()).thenReturn(resource);
        sharedRepository.initializeLockManager();
    }

    @Test
    public void test_ask_for_release_lock_when_closing_diagram_in_manual_mode() throws Exception {
        when(scan.isLocalyLocked()).thenReturn(true);
        doReturn(false).when(sharedRepository).isAutoShare();
        doNothing().when(lockManager).releaseLock(eq(resource), eq(false), notNull(Display.class));

        sharedRepository.postClose(fileStore, new FileStoreChangeEvent(EventType.POST_CLOSE, fileStore, null));
        verify(lockManager).releaseLock(eq(resource), eq(false), notNull(Display.class));
    }

    @Test
    public void test_do_release_lock_when_closing_diagram_in_auto_mode()
            throws InvocationTargetException, InterruptedException, CoreException {
        when(scan.isLocalyLocked()).thenReturn(true);
        doReturn(true).when(sharedRepository).isAutoShare();
        sharedRepository.postClose(fileStore, new FileStoreChangeEvent(EventType.POST_CLOSE, fileStore, null));
        verify(lockManager).releaseLock(eq(resource), eq(true), notNull(Display.class));
    }

}
