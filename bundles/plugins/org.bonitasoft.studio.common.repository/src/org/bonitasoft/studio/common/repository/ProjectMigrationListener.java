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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.ImportBonitaProjectOperation;
import org.bonitasoft.studio.common.repository.core.migration.BonitaProjectMigrator;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReportWriter;
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
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.statushandlers.StatusManager;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

/**
 * Listen to project descriptor (.project) Bonita version changes.
 * When version is not compatible:
 * * If migration is possible suggest to migrate project content
 * * If not, either suggest to switch to another branch (when is connected) or switch project.
 */
public class ProjectMigrationListener implements IResourceChangeListener, IResourceDeltaVisitor {

    private AtomicBoolean migrationInProgess = new AtomicBoolean(false);

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
        if (migrationInProgess.getAndSet(true)) {
            return;
        }
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

    private boolean runMigrationInDialog(Shell shell, IProject project) {
        try {
            new ProgressMonitorDialog(shell)
                    .run(true, false, monitor -> {
                        try {
                            migrateProject(project, monitor);
                        } catch (CoreException | MigrationException e) {
                            throw new InvocationTargetException(e);
                        }
                    });
            return true;
        } catch (InvocationTargetException e) {
            if (e.getTargetException() != null) {
                var exception = e.getTargetException();
                if (exception instanceof CoreException ce) {
                    var ex = ce.getStatus().getException();
                    Display.getDefault().asyncExec(() -> CommonRepositoryPlugin.getDefault().openErrorDialog(
                            Display.getDefault().getActiveShell(),
                            ce.getStatus().getMessage(), ex != null ? ex : ce));
                } else {
                    Display.getDefault().asyncExec(() -> CommonRepositoryPlugin.getDefault().openErrorDialog(
                            Display.getDefault().getActiveShell(),
                            Messages.migrationFailedMessage, e.getTargetException()));
                }
            } else {
                Display.getDefault().asyncExec(() -> CommonRepositoryPlugin.getDefault().openErrorDialog(
                        Display.getDefault().getActiveShell(),
                        Messages.migrationFailedMessage, e));

            }
            return false;
        } catch (InterruptedException e) {
            Display.getDefault().asyncExec(() -> CommonRepositoryPlugin.getDefault().openErrorDialog(
                    Display.getDefault().getActiveShell(),
                    Messages.migrationFailedMessage, e));
            return false;
        }
    }

    private void migrateProject(IProject project, IProgressMonitor monitor)
            throws CoreException, MigrationException {
        monitor.beginTask(Messages.migrating, IProgressMonitor.UNKNOWN);
        monitor.subTask(Messages.prepareProjectForMigration);
        var projectRoot = project.getLocation().toFile();
        var bonitaProject = RepositoryManager.getInstance().getCurrentProject().orElse(null);
        if (bonitaProject != null) {
            // First close the project to avoid file locking
            // on windows when copying project to tmp folder
            bonitaProject.close(monitor);

            java.nio.file.Path tmpProjectFolder = null;
            try {
                tmpProjectFolder = Files.createTempDirectory(project.getName() + "-migration-tmp");
                Files.deleteIfExists(tmpProjectFolder);
                FileUtil.copyDirectory(projectRoot.toPath(), tmpProjectFolder);

                // Migrate first
                var migrator = new BonitaProjectMigrator(tmpProjectFolder);
                migrator.run(monitor);

                // If migration is successful, remove existing project in
                // workspace and import the migrated version from tmp folder
                bonitaProject.delete(monitor);

                var op = new ImportBonitaProjectOperation(tmpProjectFolder.toFile());
                op.run(monitor);
                var report = op.getReport();
                bonitaProject = op.getBonitaProject();
                bonitaProject.open(monitor);
                var currentRepo = RepositoryManager.getInstance().getCurrentRepository().orElse(null);
                if (currentRepo != null) {
                    currentRepo.migrate(report, SubMonitor.convert(monitor));
                }
            } catch (IOException e) {
                throw new CoreException(Status.error("Failed to backup current branch state.", e));
            } finally {
                if (tmpProjectFolder != null) {
                    try {
                        FileUtil.deleteDir(tmpProjectFolder);
                    } catch (IOException e) {
                        BonitaStudioLog.error(e);
                    }
                }
                // In case of error during migration
                // Make sure the project is reopened
                var currentRepository = RepositoryManager.getInstance().getCurrentRepository().orElse(null);
                if (currentRepository != null && !currentRepository.isLoaded()) {
                    bonitaProject.open(monitor);
                }
            }
        }
    }

    private void openGitMigrationDialog(IProject project) {
        Display.getDefault().asyncExec(() -> {
            var shell = newShell();
            boolean migrate = MessageDialog.open(MessageDialog.INFORMATION,
                    shell,
                    Messages.migrationTitle,
                    String.format(Messages.mustMigrationMsg,
                            ProductVersion.CURRENT_VERSION),
                    SWT.NONE, Messages.migrate, Messages.switchIntoNewbranch) == 0; // default index is 0 -> migrate
            if (migrate) {
                var success = runMigrationInDialog(shell, project);
                migrationInProgess.set(false);
                if (success) {
                    // Commit migration changes
                    var bonitaProject = Adapters.adapt(
                            RepositoryManager.getInstance().getCurrentRepository().orElseThrow(),
                            BonitaProject.class);
                    try {
                        bonitaProject.commitAll(String.format("Bonita '%s' automated migration",
                                ProductVersion.CURRENT_VERSION), new NullProgressMonitor());
                    } catch (CoreException e) {
                        StatusManager.getManager().handle(e, CommonRepositoryPlugin.PLUGIN_ID);
                    }
                    var currentRepository = RepositoryManager.getInstance().getCurrentRepository().orElse(null);
                    if (currentRepository != null) {
                        var reportFile = currentRepository.getProject()
                                .getFile(MigrationReportWriter.DEFAULT_REPORT_FILE_NAME);
                        if (reportFile.exists()) {
                            try {
                                IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(),
                                        reportFile);
                            } catch (PartInitException e) {
                                BonitaStudioLog.error(e);
                            }
                        }
                    }
                } else {
                    openCheckoutBranchDialog(project, shell);
                }
            } else {
                migrationInProgess.set(false);
                openCheckoutBranchDialog(project, shell);
            }
        });
    }

    private void openCheckoutBranchDialog(IProject project, Shell shell) {
        // open switch branch dialog
        // if user leaves or click cancel, then re open migration dialog
        var gitRepository = ResourceUtil.getRepository(project);
        CheckoutDialog checkoutDialog = new CheckoutDialog(shell,
                gitRepository);
        if (checkoutDialog.open() == CheckoutDialog.OK) {
            migrationInProgess.set(true);
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
            migrationInProgess.set(false);
        } else {
            openGitMigrationDialog(project);
        }
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
                && ((project.getFolder(BonitaProject.APP_MODULE).exists()
                        && Strings.hasText(project.getDescription().getComment()))
                        // Legacy project layout
                        || (project.hasNature(BonitaProjectNature.NATURE_ID)
                                && Strings.hasText(project.getDescription().getComment())));
    }

}
