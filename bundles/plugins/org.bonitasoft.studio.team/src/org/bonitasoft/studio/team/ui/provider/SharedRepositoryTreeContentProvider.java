/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.ui.viewer.RepositoryTreeContentProvider;
import org.bonitasoft.studio.team.operations.ScanResourcesLockOperation;
import org.bonitasoft.studio.team.operations.ScanResourcesLockOperation.LockStatus;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Romain Bioteau
 */
public class SharedRepositoryTreeContentProvider extends RepositoryTreeContentProvider {

    private final TeamRepositoryLabelDecorator decorator;
    private ScanResourcesLockOperation op;

    public SharedRepositoryTreeContentProvider(final TeamRepositoryLabelDecorator decorator) {
        this.decorator = decorator;
    }

    @Override
    public Object[] getChildren(final Object parentElement) {
        if (parentElement instanceof IRepositoryStore) {
            final List<IResource> resources = new ArrayList<IResource>();
            final List<IRepositoryFileStore> children = new ArrayList<IRepositoryFileStore>();
            fillSharedResources(resources, children, parentElement);
            if (!resources.isEmpty()) {
                op = new ScanResourcesLockOperation(resources.toArray(new IResource[] {}));
                scanResourcesLock(op);
                updateLockDecorator(op);
            }
            return children.toArray();
        }
        return super.getChildren(parentElement);
    }

    /**
     * @param op
     */
    protected void scanResourcesLock(final ScanResourcesLockOperation op) {
        try {
            RepositoryManager.getInstance().getCurrentRepository().getProject().getWorkspace().run(op, AbstractRepository.NULL_PROGRESS_MONITOR);
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
    }

    protected void updateLockDecorator(final ScanResourcesLockOperation op) {
        Map<IResource, LockStatus> lockstatus;
        lockstatus = op.getStatusMap();
        for (final Entry<IResource, LockStatus> lockstatu : lockstatus.entrySet()) {
            decorator.updateLockStatus(lockstatu.getKey(), lockstatu.getValue());
        }
        for (final Entry<IResource, String> lockOwner : op.getLockOwners().entrySet()) {
            decorator.updateLockOwner(lockOwner.getKey(), lockOwner.getValue());
        }
    }

    public void upDateLockDecorator() {
        updateLockDecorator(op);
    }

    protected void fillSharedResources(final List<IResource> resources, final List<IRepositoryFileStore> children, final Object parentElement) {
        for (final IRepositoryFileStore file : ((IRepositoryStore<? extends IRepositoryFileStore>) parentElement).getChildren()) {
            if (file.canBeShared()) {
                children.add(file);
                resources.add(file.getResource());
            }
        }
    }

    @Override
    public boolean hasChildren(final Object element) {
        if (element instanceof IRepositoryStore) {
            return !((IRepositoryStore<?>) element).isEmpty() && ((IRepositoryStore<?>) element).canBeShared();
        }
        return false;
    }

}
