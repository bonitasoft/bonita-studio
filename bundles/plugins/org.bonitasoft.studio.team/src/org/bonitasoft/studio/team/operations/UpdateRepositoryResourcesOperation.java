/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.operations;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.team.core.SharedResourceSynchronizer;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.repository.Repository;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.ViewIntroAdapterPart;

/**
 * @author Baptiste Mesta
 */
public class UpdateRepositoryResourcesOperation implements IRunnableWithProgress {

    private final SharedResourceSynchronizer sharedResourcesSynchronizer;

    public UpdateRepositoryResourcesOperation(final IResource[] resources) {
        sharedResourcesSynchronizer = new SharedResourceSynchronizer(resources);
    }

    public UpdateRepositoryResourcesOperation(final IResource resource) {
        this(new IResource[] { resource });
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        if (monitor == null) {
            monitor = AbstractRepository.NULL_PROGRESS_MONITOR;
        }
        monitor.beginTask(Messages.updatingRepository, IProgressMonitor.UNKNOWN);
        final IRepository currentRepository = RepositoryManager.getInstance().getCurrentRepository();
        if (currentRepository instanceof Repository) {
            ((Repository) currentRepository).stopUpdateJob();
        }
        sharedResourcesSynchronizer.handleConflictingResources(monitor);
        sharedResourcesSynchronizer.update(monitor);

        if (PlatformUI.isWorkbenchRunning()
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .getActivePart() instanceof ViewIntroAdapterPart) {
            PlatformUtil.openIntro();
        }

        if (currentRepository instanceof Repository) {
            ((Repository) currentRepository).startUpdateJob();
        }
    }

}
