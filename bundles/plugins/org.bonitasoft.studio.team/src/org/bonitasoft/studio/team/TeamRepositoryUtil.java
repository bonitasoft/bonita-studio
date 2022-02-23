/*******************************************************************************
 * Copyright (C) 2009, 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.team.i18n.Messages;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

/**
 * @author Baptiste Mesta
 */
public class TeamRepositoryUtil {

    public final static int NOT_LOCK = 0;
    public final static int LOCK_BY_OTHER = 1;
    public final static int LOCK_BY_ME = 2;
    public static final String LEGACY_LAST_RESTORE = "LAST_RESTORE";
    public static final String LAST_RESTORE = ".LAST_RESTORE";
    private static final String PROJECT_FILE = ".project";

    public static void switchToRepository(final String newWorkspaceFolder,
            final IProgressMonitor monitor) {
        switchToRepository(newWorkspaceFolder, true, false, monitor);
    }

    public static synchronized void switchToRepository(
            final String repositoryName,
            final boolean startUpdateJob,
            final boolean migrateExistingContent,
            IProgressMonitor monitor) {
        switchToRepository(repositoryName, startUpdateJob, migrateExistingContent, true, monitor);
    }

    public static synchronized void switchToRepository(
            final String repositoryName,
            final boolean startUpdateJob,
            final boolean migrateExistingContent,
            final boolean stopServerBeforeSwitching,
            IProgressMonitor monitor) {
        if (monitor == null) {
            monitor = AbstractRepository.NULL_PROGRESS_MONITOR;
        }
        IRepository currentRepository = RepositoryManager.getInstance()
                .getCurrentRepository();
        if (Objects.equals(currentRepository.getName(), repositoryName)) {
            return;
        }
        currentRepository.close();
        try {
            WorkspaceModifyOperation workspaceModifyOperation = new WorkspaceModifyOperation() {

                @Override
                protected void execute(final IProgressMonitor monitor)
                        throws CoreException, InvocationTargetException, InterruptedException {
                    monitor.beginTask(Messages.team_switchingProject,
                            IProgressMonitor.UNKNOWN);
                    monitor.subTask(Messages.team_openingNewWorkspace);
                    BonitaStudioLog.info("Switching repository to "
                            + repositoryName, TeamPlugin.PLUGIN_ID);
                    RepositoryManager.getInstance().setRepository(repositoryName, migrateExistingContent, monitor);
                    // update studio name
                    final AbstractRepository currentRepo = RepositoryManager.getInstance().getCurrentRepository();
                    BonitaStudioLog.info(
                            "Repository switched to " + currentRepo.getName(),
                            TeamPlugin.PLUGIN_ID);
                }
            };
            workspaceModifyOperation.run(monitor);
            Display.getDefault().asyncExec(
                    () -> {
                        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().resetPerspective();
                        PlatformUtil.openIntroIfNoOtherEditorOpen();
                        AbstractFileStore.refreshExplorerView();
                    });
        } catch (final InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
        } 

    }

    public static boolean isRepositoryOnLine() {
        return !TeamRepositoryUtil.isLocalRepository()
                && RepositoryManager.getInstance().getCurrentRepository().isOnline();
    }


    public static boolean isLocalRepository() {
        final AbstractRepository currentRepository = RepositoryManager.getInstance().getCurrentRepository();
        if (currentRepository.getProject().isOpen()) {
            return !currentRepository.isShared();
        }
        return false;
    }

    public static boolean isLocalRepository(final String repositoryName) {
        final IRepository repository = RepositoryManager.getInstance()
                .getRepository(repositoryName);
        return !repository.isShared();
    }


    public static void setAutoShare(final String repositoryName,
            final boolean autoShare) {
        TeamPlugin.getDefault().getPreferenceStore()
                .setValue(repositoryName, autoShare);
    }

    public static List<IRepository> getSharedRepositories() {
        final List<IRepository> result = new ArrayList<IRepository>();
        for (final IRepository repo : RepositoryManager.getInstance()
                .getAllRepositories()) {
            if (repo.isShared()) {
                result.add(repo);
            }
        }
        return result;
    }


}
