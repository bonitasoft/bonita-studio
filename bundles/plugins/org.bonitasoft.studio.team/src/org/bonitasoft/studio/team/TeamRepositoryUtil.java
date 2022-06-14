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
import java.util.Objects;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.team.i18n.Messages;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

/**
 * @author Baptiste Mesta
 */
public class TeamRepositoryUtil {

    private static final String PROJECT_FILE = ".project";

    public static void switchToRepository(final String newWorkspaceFolder,
            final IProgressMonitor monitor) {
        switchToRepository(newWorkspaceFolder, false, monitor);
    }

    public static synchronized void switchToRepository(
            final String repositoryName,
            final boolean migrateExistingContent,
            IProgressMonitor monitor) {
        if (monitor == null) {
            monitor = new NullProgressMonitor();
        }
        var currentRepository = RepositoryManager.getInstance()
                .getCurrentRepository();
        if (currentRepository.filter(repo -> Objects.equals(repo.getName(), repositoryName)).isPresent()) {
            return;
        }
        currentRepository.ifPresent(IRepository::close);
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
                    BonitaStudioLog.info(
                            "Repository switched to " + repositoryName,
                            TeamPlugin.PLUGIN_ID);
                }
            };
            workspaceModifyOperation.run(monitor);
            Display.getDefault().asyncExec(
                    PlatformUtil::openDashboardIfNoOtherEditorOpen);
            Display.getDefault().asyncExec(
                    AbstractFileStore::refreshExplorerView);
        } catch (final InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
        } 

    }

}
