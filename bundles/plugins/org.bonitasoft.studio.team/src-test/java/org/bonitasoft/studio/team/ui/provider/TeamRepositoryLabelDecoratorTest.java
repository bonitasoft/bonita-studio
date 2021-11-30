/*******************************************************************************
 * Copyright (C) 2009-2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.provider;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.operations.ScanResourcesLockOperation.LockStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class TeamRepositoryLabelDecoratorTest {

    @Mock
    private DiagramFileStore fileStore;

    @Mock
    private IFile resource;

    @Spy
    private TeamRepositoryLabelDecorator labelDecorator;


    @Test
    public void testDecorateTextWithLockOwnerForOtherLocked() {
        when(fileStore.getResource()).thenReturn(resource);
        final Map<IResource, LockStatus> lockStatus = new HashMap<IResource, LockStatus>();
        lockStatus.put(resource, LockStatus.OTHER_LOCKED);
        labelDecorator.setLockStatus(lockStatus);
        final Map<IResource,String> lockOwners = new HashMap<IResource,String>();
        lockOwners.put(resource, "bill.jobs");
        labelDecorator.setLockOwners(lockOwners);
        final String text = labelDecorator.decorateText("", fileStore);
        assertEquals(text, " (" + Messages.bind(Messages.lockedBy, "bill.jobs") + ")");
    }

    @Test
    public void testDecorateTextWithLockOwnerForLocalLock() {
        when(fileStore.getResource()).thenReturn(resource);
        final Map<IResource, LockStatus> lockStatus = new HashMap<IResource, LockStatus>();
        lockStatus.put(resource, LockStatus.LOCALLY_LOCKED);
        labelDecorator.setLockStatus(lockStatus);
        final Map<IResource, String> lockOwners = new HashMap<IResource, String>();
        lockOwners.put(resource, "bill.jobs");
        labelDecorator.setLockOwners(lockOwners);
        final String text = labelDecorator.decorateText("", fileStore);
        assertEquals(text, " (" + Messages.bind(Messages.lockedBy, "bill.jobs") + ")");
    }

    @Test
    public void testDecorateTextWithLockOwnerForStolenLock() {
        when(fileStore.getResource()).thenReturn(resource);
        final Map<IResource, LockStatus> lockStatus = new HashMap<IResource, LockStatus>();
        lockStatus.put(resource, LockStatus.STOLEN);
        labelDecorator.setLockStatus(lockStatus);
        final Map<IResource, String> lockOwners = new HashMap<IResource, String>();
        lockOwners.put(resource, "bill.jobs");
        labelDecorator.setLockOwners(lockOwners);
        final String text = labelDecorator.decorateText("", fileStore);
        assertEquals(text, " (" + Messages.bind(Messages.lockedBy, "bill.jobs") + ")");
    }

    @Test
    public void testDecoarateTextWithNoLockOwner() {
        when(fileStore.getResource()).thenReturn(resource);
        final Map<IResource, LockStatus> lockStatus = new HashMap<IResource, LockStatus>();
        lockStatus.put(resource, LockStatus.NOT_LOCKED);
        labelDecorator.setLockStatus(lockStatus);
        final String text = labelDecorator.decorateText("", fileStore);
        assertEquals(text, "");
    }

    @Test
    public void testDecorateTextWithBrokenLock() {
        when(fileStore.getResource()).thenReturn(resource);
        final Map<IResource, LockStatus> lockStatus = new HashMap<IResource, LockStatus>();
        lockStatus.put(resource, LockStatus.BROKEN);
        labelDecorator.setLockStatus(lockStatus);
        final String text = labelDecorator.decorateText("", fileStore);
        assertEquals(text, "");
    }

    @Test
    public void testUpdateLockOwner() {
        labelDecorator.updateLockOwner(resource, "bill.jobs");
        assertEquals("bill.jobs", labelDecorator.getLockOwner(resource));
    }

    @Test
    public void testUpdateLockStatus() {
        labelDecorator.updateLockStatus(resource, LockStatus.OTHER_LOCKED);
        assertEquals(LockStatus.OTHER_LOCKED, labelDecorator.getLockStatus(resource));
    }




}
