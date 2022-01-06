/*******************************************************************************
 * Copyright (C) 2014 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.handler;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.team.operations.ScanResourcesLockOperation.LockStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.ui.IEditorReference;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommitWorkspaceHandlerTest {

    @Mock
    private DiagramRepositoryStore diagramRepositoryStore;

    @Mock
    private DiagramFileStore file1;

    @Mock
    private DiagramFileStore file2;

    @Mock
    private IFile resource1;

    @Mock
    private IFile resource2;

    @Mock
    private IEditorReference editor;

    private CommitWorkspaceHandler handler;

    @Before
    public void setUp() throws Exception {
        handler = spy(new CommitWorkspaceHandler());
        final List<DiagramFileStore> files = new ArrayList<DiagramFileStore>();
        files.add(file1);
        files.add(file2);
        when(diagramRepositoryStore.getChildren()).thenReturn(files);
        when(file1.getResource()).thenReturn(resource1);
        when(file1.isReadOnly()).thenReturn(false);
        when(file2.isReadOnly()).thenReturn(true);

    }

    @Test
    public void test_getResourcesToScanReturnSomething() {
        doReturn(editor).when(handler).getOpenedEditor(file1);
        final List<DiagramFileStore> diagramsToReopen = new ArrayList<DiagramFileStore>();
        final IResource[] resources = handler.getResourcesToScan(diagramRepositoryStore, diagramsToReopen);
        assertEquals(diagramsToReopen.size(), 1);
        assertEquals(diagramsToReopen.get(0), file1);
        assertEquals(resources.length, 1);
        assertEquals(resources[0], resource1);
    }

    @Test
    public void test_reopenDiagramIfNeeded() {
        final Map<IResource, LockStatus> statusMap = new HashMap<IResource, LockStatus>();
        final List<DiagramFileStore> diagramsToReopen = new ArrayList<DiagramFileStore>();
        diagramsToReopen.add(file1);
        final LockStatus lockStatus = LockStatus.NOT_LOCKED;
        statusMap.put(resource1, lockStatus);
        doNothing().when(handler).lockResource(any(DiagramFileStore.class));
        doReturn(true).when(handler).isKeepLocks();
        handler.handleResourceLockStatus(statusMap, diagramsToReopen);
        verify(handler).lockResource(file1);;
    }

}
