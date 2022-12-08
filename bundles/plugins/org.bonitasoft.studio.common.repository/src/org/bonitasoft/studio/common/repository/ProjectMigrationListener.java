/**
 * Copyright (C) 2022 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.repository;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.ImportBonitaProjectOperation;
import org.bonitasoft.studio.common.repository.core.migration.BonitaProjectMigrator;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.egit.core.GitProvider;
import org.eclipse.egit.core.internal.util.ResourceUtil;
import org.eclipse.egit.ui.internal.branch.BranchOperationUI;
import org.eclipse.egit.ui.internal.dialogs.CheckoutDialog;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ResetCommand.ResetType;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.team.core.RepositoryProvider;
import org.eclipse.ui.PlatformUI;

/**
 * Listen to project descriptor (.project) Bonita version changes.
 * When version is not compatible:
 * * If migration is possible suggest to migrate project content
 * * If not, either suggest to switch to another branch (when is connected) or switch project.
 */
public class ProjectMigrationListener implements IResourceChangeListener, IResourceDeltaVisitor {

    public boolean migrationDialogOpened = false;

    @PostConstruct
    public void subscribe() {
        ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
    }

    @PreDestroy
    public void unsubscribe() {
        ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
    }

    @Override
    public void resourceChanged(IResourceChangeEvent event) {
        try {
            event.getDelta().accept(this);
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
        }
    }

    @Override
    public boolean visit(IResourceDelta delta) throws CoreException {
        if (delta.getProjectRelativePath().equals(Path.fromOSString(IProjectDescription.DESCRIPTION_FILE_NAME))
                && isBonitaProject(delta)) {
            checkVersion(delta.getResource().getProject());
            return false;
        }
        return true;
    }

    private void checkVersion(IProject project) throws CoreException {
        var version = BonitaProjectMigrator.readBonitaVersion(
                project.getFile(IProjectDescription.DESCRIPTION_FILE_NAME).getLocation().toFile().toPath());
        if (!ProductVersion.CURRENT_VERSION.equals(version)) {
            if (ProductVersion.canBeMigrated(version)) {
                openMigrationDialog(project);
            } else if (!ProductVersion.sameMinorVersion(version)) {
                openErrorDialog(project, version);
            }
        }
    }

    protected void openErrorDialog(IProject project, String version) {
        Display.getDefault().asyncExec(() -> MessageDialog
                .openError(Display.getDefault().getActiveShell(),
                        Messages.repositoryError,
                        String.format(Messages.repositoryError, project.getName(),
                                version,
                                ProductVersion.CURRENT_VERSION)));
    }

    protected void openMigrationDialog(IProject project) {
        if (RepositoryProvider.getProvider(project, GitProvider.ID) != null) {
            openGitMigrationDialog(project);
        } else {
            Display.getDefault()
                    .asyncExec(() -> {
                        var shell = newShell();
                        MessageDialog.open(MessageDialog.INFORMATION,
                                shell,
                                Messages.migrationTitle,
                                String.format(Messages.mustMigrationMsg,
                                        ProductVersion.CURRENT_VERSION),
                                SWT.NONE, Messages.migrate);
                        runMigrationInDialog(shell, project);
                    });
        }
    }

    private Shell newShell() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    }

    private void runMigrationInDialog(Shell shell, IProject project) {
        try {
            new ProgressMonitorDialog(shell)
                    .run(true, false, monitor -> {
                        try {
                            migrateProject(project, monitor);
                        } catch (CoreException | MigrationException e) {
                            throw new InvocationTargetException(e);
                        }
                    });
        } catch (InvocationTargetException | InterruptedException e) {
            CommonRepositoryPlugin.getDefault().openErrorDialog(
                    Display.getDefault().getActiveShell(),
                    Messages.migrationFailedMessage, e);
        }
    }

    private void migrateProject(IProject project, IProgressMonitor monitor)
            throws CoreException, MigrationException {
        monitor.beginTask(Messages.migrating, IProgressMonitor.UNKNOWN);
        var projectRoot = project.getLocation().toFile();
        var migrator = new BonitaProjectMigrator(projectRoot.toPath());
        if (migrator.requireCleanImport()) {
            var repository = RepositoryManager.getInstance().getCurrentRepository().orElse(null);
            if (repository != null) {
                repository.close(new NullProgressMonitor());
            }
            RepositoryManager.getInstance().setCurrentRepository(null);
            BonitaProject.getRelatedProjects(projectRoot.getName()).stream()
                    .forEach(p -> {
                        try {
                            p.delete(false, true, new NullProgressMonitor());
                        } catch (CoreException e) {
                            BonitaStudioLog.error(e);
                        }
                    });
            var op = new ImportBonitaProjectOperation(projectRoot);
            op.run(new NullProgressMonitor());
            var report = op.getReport();
            var newProject = op.getProject();
            var currentRepo = RepositoryManager.getInstance().switchToRepository(
                    newProject.getName(),
                    new NullProgressMonitor());
            try {
                currentRepo.migrate(report, SubMonitor.convert(monitor));
            } catch (CoreException | MigrationException e) {
                new InvocationTargetException(e);
            }
        } else {
            var repository = RepositoryManager.getInstance().getCurrentRepository().orElse(null);
            var report = migrator.run(new NullProgressMonitor());
            if (repository != null) {
                repository.migrate(report, SubMonitor.convert(monitor));
            }
        }
    }

    @SuppressWarnings("restriction")
    private void openGitMigrationDialog(IProject project) {
        Display.getDefault().asyncExec(() -> {
            if (!migrationDialogOpened) {
                migrationDialogOpened = true;
                var shell = newShell();
                boolean migrate = MessageDialog.open(MessageDialog.INFORMATION,
                        shell,
                        Messages.migrationTitle,
                        String.format(Messages.mustMigrationMsg,
                                ProductVersion.CURRENT_VERSION),
                        SWT.NONE, Messages.migrate, Messages.switchIntoNewbranch) == 0; // default index is 0 -> migrate
                migrationDialogOpened = false;
                if (migrate) {
                    runMigrationInDialog(shell, project);
                    // Commit migration changes
                    var bonitaProject = Adapters.adapt(
                            RepositoryManager.getInstance().getCurrentRepository().orElseThrow(), BonitaProject.class);
                    try {
                        bonitaProject.commitAll(String.format("Bonita '%s' automated migration",
                                ProductVersion.CURRENT_VERSION), new NullProgressMonitor());
                    } catch (CoreException e) {
                        BonitaStudioLog.error(e);
                    }
                } else {
                    // open switch branch dialog
                    // if user leaves or click cancel, then re open migration dialog
                    var gitRepository = ResourceUtil.getRepository(project);
                    CheckoutDialog checkoutDialog = new CheckoutDialog(shell,
                            gitRepository);
                    if (checkoutDialog.open() == CheckoutDialog.OK) {
                        try {
                            PlatformUI.getWorkbench().getProgressService().run(true, false, monitor -> {
                                try {
                                    gitReset(gitRepository);
                                } catch (CoreException e) {
                                    BonitaStudioLog.error(e);
                                }
                            });
                        } catch (InvocationTargetException | InterruptedException e) {
                            BonitaStudioLog.error(e);
                        }

                        BranchOperationUI
                                .checkout(gitRepository, checkoutDialog.getRefName())
                                .start();
                    } else {
                        openGitMigrationDialog(project);
                    }
                }
            }
        });
    }

    private void gitReset(Repository repository) throws CoreException {
        try (Git git = new Git(repository)) {
            git.reset()
                    .setMode(ResetType.HARD)
                    .call();
        } catch (GitAPIException e) {
            throw new CoreException(Status.error("Failed to clean", e));
        }
    }

    private boolean isBonitaProject(IResourceDelta delta) throws CoreException {
        var project = delta.getResource().getProject();
        return project.isAccessible()
                && !Objects.equals(project.getName(), "server_configuration")
                && delta.getResource().exists()
                // New project layout
                && (project.getFolder(BonitaProject.APP_MODULE).exists()
                        // Legacy project layout
                        || (project.hasNature(BonitaProjectNature.NATURE_ID)
                                && Strings.hasText(project.getDescription().getComment())));
    }

}
