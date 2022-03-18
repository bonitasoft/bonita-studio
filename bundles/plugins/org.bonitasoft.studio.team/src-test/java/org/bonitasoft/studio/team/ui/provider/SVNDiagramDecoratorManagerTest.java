package org.bonitasoft.studio.team.ui.provider;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.team.jobs.AbstractUpdateLockStatusJob;
import org.bonitasoft.studio.team.operations.ScanResourcesLockOperation.LockStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.ui.dialogs.FilteredTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SVNDiagramDecoratorManagerTest {

    SVNDiagramDecoratorManager manager;

    @Mock
    FilteredTree tree;

    @Mock
    AbstractUpdateLockStatusJob job;

    @Mock
    DiagramFileStore diagram1;

    @Mock
    DiagramFileStore diagram2;

    @Mock
    IFile resource1;

    @Mock
    IFile resource2;

    @Mock
    DiagramRepositoryStore diagramRepository;

    @Mock
    private IWizardContainer wizardContainer;

    @Before
    public void setUp() throws Exception {
        final List<DiagramFileStore> selectedDiagrams = new ArrayList<DiagramFileStore>();
        when(diagramRepository.getChildren()).thenReturn(selectedDiagrams);
        manager = spy(new SVNDiagramDecoratorManager(tree, diagramRepository, wizardContainer));
        manager.setUpdateLockStatusJob(job);
        when(diagram1.getResource()).thenReturn(resource1);
        when(diagram2.getResource()).thenReturn(resource2);
        selectedDiagrams.add(diagram1);
        selectedDiagrams.add(diagram2);

        final StructuredSelection selection = new StructuredSelection(selectedDiagrams);
        doReturn(selection).when(manager).getTreeSelection();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test_isSelectionIsLocked_returnTrue() {
        final IStatus status = Status.OK_STATUS;
        doReturn(status).when(manager).getJobResult(job);
        final Map<IResource, LockStatus> lockStatusMap = new HashMap<IResource, LockStatus>();
        lockStatusMap.put(resource1, LockStatus.OTHER_LOCKED);
        lockStatusMap.put(resource2, LockStatus.LOCALLY_LOCKED);
        doReturn(lockStatusMap).when(manager).getLockStatus();
        final boolean result = manager.isSelectionIsLocked();
        assertTrue(result);
    }

    @Test
    public void test_isSelectionIsLocked_returnFalse() {
        final IStatus status = Status.OK_STATUS;
        doReturn(status).when(manager).getJobResult(job);
        final Map<IResource, LockStatus> lockStatusMap = new HashMap<IResource, LockStatus>();
        lockStatusMap.put(resource1, LockStatus.NOT_LOCKED);
        lockStatusMap.put(resource2, LockStatus.LOCALLY_LOCKED);
        doReturn(lockStatusMap).when(manager).getLockStatus();
        final boolean result = manager.isSelectionIsLocked();
        assertFalse(result);
    }

    @Test
    public void test_isSelectionIsLocked_returnTrue_whenNoSelection() {
        when(manager.getTreeSelection()).thenReturn(null);
        final boolean result = manager.isSelectionIsLocked();
        assertTrue(result);
    }

    @Test
    public void test_isSelectionIsLocked_returnFalse_WhenNoJob() {
        when(manager.getUpdateLockStatusJob()).thenReturn(null);
        final boolean result = manager.isSelectionIsLocked();
        assertFalse(result);
    }

}
