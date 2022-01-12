package org.bonitasoft.studio.team.ui.provider;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.team.operations.ScanResourcesLockOperation;
import org.bonitasoft.studio.team.operations.ScanResourcesLockOperation.LockStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class SharedRepositoryTreeContentProviderTest {

    @Mock
    DiagramRepositoryStore diagramStore;


    SharedRepositoryTreeContentProvider provider;

    @Mock
    IFile resource;

    @Mock
    final DiagramFileStore file = new DiagramFileStore("sdfsdfsdf", diagramStore);

    @Mock
    private ScanResourcesLockOperation operation;


    @Spy
    private TeamRepositoryLabelDecorator decorator;



    @Before
    public void before() {
        provider = spy(new SharedRepositoryTreeContentProvider(decorator));
        doNothing().when(provider).scanResourcesLock(any(ScanResourcesLockOperation.class));
    }

    @Test
    public void testNotHasChildren() {
        assertFalse(provider.hasChildren(diagramStore));
    }

    @Test
    public void testNotCanBeShared() {
        final List<DiagramFileStore> list = new ArrayList<DiagramFileStore>();
        list.add(new DiagramFileStore("sdfsdfsdf", diagramStore));
        when(diagramStore.canBeShared()).thenReturn(false);
        assertFalse(provider.hasChildren(diagramStore));
    }

    @Test
    public void testHasChildren(){
        final List<DiagramFileStore> list = new ArrayList<DiagramFileStore>();
        list.add(new DiagramFileStore("sdfsdfsdf", diagramStore));
        when(diagramStore.canBeShared()).thenReturn(true);
        assertTrue(provider.hasChildren(diagramStore));
    }

    @Test
    public void testNotHasChildrenWhenElementIsNotARepository() {
        assertFalse(provider.hasChildren(new String()));
    }

    @Test
    public void testGetResources() {
        final List<DiagramFileStore> list = new ArrayList<DiagramFileStore>();
        list.add(file);
        when(diagramStore.getChildren()).thenReturn(list);
        when(file.getResource()).thenReturn(resource);
        when(file.canBeShared()).thenReturn(true);
        final List<IResource> resources = new ArrayList<IResource>();
        final List<IRepositoryFileStore> children = new ArrayList<IRepositoryFileStore>();
        provider.fillSharedResources(resources, children, diagramStore);
        assertFalse(resources.isEmpty());
        assertFalse(children.isEmpty());
    }

    @Test
    public void testUpdateLockDecorator() {
        final Map<IResource, LockStatus> lockStatus = new HashMap<IResource, LockStatus>();
        lockStatus.put(resource, LockStatus.OTHER_LOCKED);
        when(operation.getStatusMap()).thenReturn(lockStatus);
        final Map<IResource, String> lockOwners = new HashMap<IResource, String>();
        lockOwners.put(resource, "bill.jobs");
        when(operation.getLockOwners()).thenReturn(lockOwners);
        provider.updateLockDecorator(operation);
        verify(decorator).updateLockStatus(resource, LockStatus.OTHER_LOCKED);
        verify(decorator).updateLockOwner(resource, "bill.jobs");

    }

    @Test
    public void testGetChildren() {
        final List<DiagramFileStore> list = new ArrayList<DiagramFileStore>();
        list.add(file);
        when(diagramStore.getChildren()).thenReturn(list);
        when(file.getResource()).thenReturn(resource);
        when(file.canBeShared()).thenReturn(true);
        final Object[] children = provider.getChildren(diagramStore);
        assertFalse(children.length == 0);


    }

}
