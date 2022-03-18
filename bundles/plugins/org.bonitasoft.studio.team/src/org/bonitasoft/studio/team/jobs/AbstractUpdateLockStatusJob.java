package org.bonitasoft.studio.team.jobs;

import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.team.operations.ScanResourcesLockOperation;
import org.bonitasoft.studio.team.operations.ScanResourcesLockOperation.LockStatus;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;


public abstract class AbstractUpdateLockStatusJob extends Job {

    private final Map<IResource, LockStatus> lockstatus;
    private final List<IResource> resources;

    public AbstractUpdateLockStatusJob(final String name, final List<IResource> resources, final Map<IResource, LockStatus> lockstatus) {
        super(name);
        this.resources = resources;
        this.lockstatus = lockstatus;

    }

    @Override
    protected IStatus run(final IProgressMonitor arg0) {
        final ScanResourcesLockOperation op = new ScanResourcesLockOperation(resources.toArray(new IResource[] {}));

        try {
            RepositoryManager.getInstance().getCurrentRepository().getProject().getWorkspace().run(op, AbstractRepository.NULL_PROGRESS_MONITOR);
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
        lockstatus.putAll(op.getStatusMap());

        updateUI(op);

        return Status.OK_STATUS;
    }



    public void configureJob() {
        setSystem(true);
        setPriority(Job.SHORT);
        addJobChangeListener(createAddJobChangeListener());
    }

    abstract protected IJobChangeListener createAddJobChangeListener();

    abstract protected void updateUI(ScanResourcesLockOperation op);

    public Map<IResource, LockStatus> getLockStatus() {
        return lockstatus;
    }



}