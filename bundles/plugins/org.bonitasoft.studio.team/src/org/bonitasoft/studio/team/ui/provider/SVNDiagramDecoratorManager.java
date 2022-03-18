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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.team.ui.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.provider.FileStoreLabelProvider;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.team.jobs.AbstractUpdateLockStatusJob;
import org.bonitasoft.studio.team.operations.ScanResourcesLockOperation;
import org.bonitasoft.studio.team.operations.ScanResourcesLockOperation.LockStatus;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.FilteredTree;

/**
 * @author aurelie
 *
 */
public class SVNDiagramDecoratorManager {

    FilteredTree tree;
    private TeamRepositoryLabelDecorator decorator;
    private final ArrayList<IResource> resources;
    private Map<IResource, LockStatus> lockstatus;
    private AbstractUpdateLockStatusJob job;
    private final DiagramRepositoryStore diagramSotre;
    private final IWizardContainer wizardContainer;

    public SVNDiagramDecoratorManager(final FilteredTree tree, final IWizardContainer wizardContainer) {
        this(tree, (DiagramRepositoryStore) RepositoryManager.getInstance().getCurrentRepository()
                .getRepositoryStore(DiagramRepositoryStore.class), wizardContainer);

    }

    public SVNDiagramDecoratorManager(final FilteredTree tree, final DiagramRepositoryStore repositoryStore, final IWizardContainer wizardContainer) {
        diagramSotre = repositoryStore;
        this.tree = tree;
        resources = new ArrayList<IResource>();
        for (final IRepositoryFileStore file : diagramSotre.getChildren()) {
            resources.add(file.getResource());
        }
        this.wizardContainer = wizardContainer;
    }

    public void refreshLockDecorators() {
        decorator = new TeamRepositoryLabelDecorator();
        tree.getViewer().setLabelProvider(new DecoratingLabelProvider(new FileStoreLabelProvider(), decorator));
        lockstatus = new HashMap<IResource, LockStatus>();
        job = new AbstractUpdateLockStatusJob("update lock decoration", resources, lockstatus) {

            @Override
            protected void updateUI(final ScanResourcesLockOperation op) {
                decorator.setLockOwners(op.getLockOwners());
                decorator.setLockStatus(op.getStatusMap());
            }

            @Override
            protected IJobChangeListener createAddJobChangeListener() {
                return new JobChangeAdapter() {
                    @Override
                    public void done(final IJobChangeEvent event) {
                        Display.getDefault().asyncExec(new Runnable() {

                            @Override
                            public void run() {
                                if (!tree.isDisposed()) {
                                    tree.getViewer().refresh();
                                    wizardContainer.updateButtons();
                                }
                            }
                        });
                    }
                };
            }
        };
        job.configureJob();
        job.schedule();
    }

    public boolean isSelectionIsLocked() {
        final StructuredSelection selection = getTreeSelection();
        final AbstractUpdateLockStatusJob job = getUpdateLockStatusJob();

        if (selection != null && job != null && getJobResult(job) != null && getJobResult(job).equals(Status.OK_STATUS)) {
            for (final Object o : selection.toList()) {
                final LockStatus lock = getLockStatus().get(((DiagramFileStore) o).getResource());
                if (lock != null && (LockStatus.OTHER_LOCKED.name().equals(lock.name()) || LockStatus.STOLEN.name().equals(lock.name()))) {
                    return true;
                }
            }
            return false;
        }
        if (job == null) {
            return false;
        }
        return true;
    }

    protected StructuredSelection getTreeSelection() {
        return (StructuredSelection) tree.getViewer().getSelection();
    }

    public Map<IResource, LockStatus> getLockStatus() {
        if (lockstatus != null) {
            return lockstatus;
        } else {
            return new HashMap<IResource, LockStatus>();
        }
    }

    protected AbstractUpdateLockStatusJob getUpdateLockStatusJob() {
        return job;
    }

    public void setUpdateLockStatusJob(final AbstractUpdateLockStatusJob job) {
        this.job = job;
    }

    protected IStatus getJobResult(final AbstractUpdateLockStatusJob job) {
        return job.getResult();
    }

}
