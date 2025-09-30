/**
 * Copyright (C) 2025 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.migration.handler;

import java.nio.file.Path;
import java.util.Optional;

import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.migration.BonitaProjectMigrator;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.bonitasoft.studio.common.repository.core.migration.ui.MigrationStepWizardPage;
import org.bonitasoft.studio.common.repository.core.migration.ui.ProjectMigrationWizard;
import org.bonitasoft.studio.common.repository.core.migration.ui.ProjectMigrationWizardDialog;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import jakarta.inject.Named;

/**
 * Handles the project migration.
 */
public class MigrateProjectHandler {

    /** Command ID */
    public static final String MIGRATE_PROJECT_COMMAND_ID = "org.bonitasoft.studio.common.repository.migrateProjectCommand"; //$NON-NLS-1$

    /** Command parameter for the project path */
    public static final String MIGRATE_PROJECT_COMMAND_PATH_PARAM = "org.bonitasoft.studio.common.repository.migrateProjectCommand.path"; //$NON-NLS-1$

    /** Context variable for the progress monitor */
    public static final String MIGRATE_PROJECT_COMMAND_MONITOR_VAR = "org.bonitasoft.studio.common.repository.migrateProjectCommand.monitor"; //$NON-NLS-1$

    protected Path project;

    @Execute
    public MigrationReport execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell activeShell,
            IEvaluationContext evalContext, @Named(MIGRATE_PROJECT_COMMAND_PATH_PARAM) String path)
            throws ExecutionException {
        // we don't really need evalContext and the monitor
        this.project = Path.of(path);
        BonitaStudioLog.info("Starting project migration at " + project);
        String sourceVersion;
        try {
            sourceVersion = readBonitaVersion();
        } catch (CoreException e) {
            // execution exception will be unwrapped from injection exception
            throw new ExecutionException(Messages.projectMigrationCancelled, e);
        }
        BonitaStudioLog.info("Migrating project from version " + sourceVersion);

        var wiz = new ProjectMigrationWizard(project);

        var allSteps = BonitaProjectMigrator.getAllSteps().toList();
        for (var step : allSteps) {
            if (Strings.hasText(sourceVersion) && step.appliesToVersion(sourceVersion)) {
                wiz.addPage(new MigrationStepWizardPage(step));
            }
        }

        var shell = Optional.ofNullable(activeShell).filter(s -> !s.isDisposed())
                .orElseGet(() -> Display.getDefault()
                        .syncCall(() -> PlatformUI.getWorkbench().getModalDialogShellProvider().getShell()));
        var open = shell.getDisplay().syncCall(() -> new ProjectMigrationWizardDialog(shell, wiz).open());
        if (open == IDialogConstants.OK_ID) {
            return wiz.getReport();
        } else {
            IStatus cancelStatus = new Status(IStatus.CANCEL, getClass(), Messages.projectMigrationCancelled);
            // execution exception will be unwrapped from injection exception
            throw new ExecutionException(Messages.projectMigrationCancelled, new CoreException(cancelStatus));
        }
    }

    public String readBonitaVersion() throws CoreException {
        var projectDescriptor = project.resolve(IProjectDescription.DESCRIPTION_FILE_NAME);
        return BonitaProjectMigrator.readBonitaVersion(projectDescriptor);
    }

}
