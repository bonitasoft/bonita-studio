/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.migration;

import static org.bonitasoft.studio.common.repository.core.migration.handler.MigrateProjectHandler.MIGRATE_PROJECT_COMMAND_ID;
import static org.bonitasoft.studio.common.repository.core.migration.handler.MigrateProjectHandler.MIGRATE_PROJECT_COMMAND_MONITOR_VAR;
import static org.bonitasoft.studio.common.repository.core.migration.handler.MigrateProjectHandler.MIGRATE_PROJECT_COMMAND_PATH_PARAM;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.migration.dependencies.operation.DependenciesUpdateOperationFactory;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.bonitasoft.studio.common.repository.core.migration.step.ApplicationModuleConfigurationStep;
import org.bonitasoft.studio.common.repository.core.migration.step.BdmAssemblyConfigurationStep;
import org.bonitasoft.studio.common.repository.core.migration.step.BdmModelArtifactMigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.step.BonitaProjectParentVersionStep;
import org.bonitasoft.studio.common.repository.core.migration.step.CleanParentStep;
import org.bonitasoft.studio.common.repository.core.migration.step.CommunityToEnterpriseMigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.step.CreatePomMigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.step.DeleteProjectSettingsMigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.step.ExtensionsModuleMigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.step.GitIgnoreMigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.step.Java17UpdateStep;
import org.bonitasoft.studio.common.repository.core.migration.step.JavaDependenciesMigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.step.MultiModuleMigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.step.ProvidedGroovyScriptRemovedStep;
import org.bonitasoft.studio.common.repository.core.migration.step.RemoveFlattenPluginExecutionStep;
import org.bonitasoft.studio.common.repository.core.migration.step.RemoveLegacyFolderStep;
import org.bonitasoft.studio.common.repository.core.migration.step.ReportingAppUpdateMigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.step.SplitGroovyAllIntoModulesStep;
import org.bonitasoft.studio.common.repository.core.migration.step.UpdateProjectDescriptionMigrationStep;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;;

public class BonitaProjectMigrator {

    // Becareful to keep a relevant step order in the list
    // Some steps can depends on previous steps execution
    private static final List<MigrationStep> STEPS = List.of(
            new CreatePomMigrationStep(),
            new RemoveLegacyFolderStep(),
            new SplitGroovyAllIntoModulesStep(),
            new JavaDependenciesMigrationStep(DependenciesUpdateOperationFactory.get()),
            new BdmModelArtifactMigrationStep(true),
            new MultiModuleMigrationStep(),
            new GitIgnoreMigrationStep(),
            new DeleteProjectSettingsMigrationStep(),
            new UpdateProjectDescriptionMigrationStep(),
            new CleanParentStep(),
            new ExtensionsModuleMigrationStep(),
            new ProvidedGroovyScriptRemovedStep(),
            new BonitaProjectParentVersionStep(),
            new ApplicationModuleConfigurationStep(),
            new BdmAssemblyConfigurationStep(),
            new RemoveFlattenPluginExecutionStep(),
            new Java17UpdateStep(),
            new ReportingAppUpdateMigrationStep());

    // Post migration steps are steps that must be run after
    // all previous steps from the STEPS list has been executed
    // to ensure the state of the project layout.
    private static final List<MigrationStep> POST_STEPS = List.of(
            new CommunityToEnterpriseMigrationStep(),
            MigrationStep.lookup("UidMigrationStep")); //$NON-NLS-1$

    public static Stream<MigrationStep> getAllSteps() {
        return Stream.concat(STEPS.stream(), POST_STEPS.stream());
    }

    private Path project;

    public BonitaProjectMigrator(Path project) {
        this.project = project;
    }

    public BonitaProjectMigrator(IProject project) {
        this(project.getLocation().toFile().toPath());
    }

    public MigrationReport run(IProgressMonitor monitor) throws CoreException {
        monitor.beginTask(Messages.migrating, IProgressMonitor.UNKNOWN);

        try {
            ICommandService commandServ = PlatformUI.getWorkbench().getService(ICommandService.class);
            Command cmd = commandServ.getCommand(MIGRATE_PROJECT_COMMAND_ID);
            var paramCmd = ParameterizedCommand.generateCommand(cmd,
                    Map.of(MIGRATE_PROJECT_COMMAND_PATH_PARAM, project.toString()));
            IHandlerService handlerServ = PlatformUI.getWorkbench().getService(IHandlerService.class);
            IEvaluationContext parentContext = handlerServ.createContextSnapshot(false);
            IEvaluationContext ctx = new EvaluationContext(parentContext, parentContext.getDefaultVariable());
            ctx.addVariable(MIGRATE_PROJECT_COMMAND_MONITOR_VAR, monitor);
            Object result = handlerServ.executeCommandInContext(paramCmd, null, ctx);
            if (result instanceof MigrationReport report) {
                return report;
            }
        } catch (ExecutionException | NotDefinedException | NotEnabledException | NotHandledException e) {
            BonitaStudioLog.error(e);
            if (e instanceof ExecutionException && e.getCause() instanceof CoreException core) {
                // unwrap the core exception
                throw core;
            } else {
                throw new CoreException(Status.error(Messages.projectMigrationFailed, e));
            }
        }
        return MigrationReport.emptyReport();
    }

    public String readBonitaVersion() throws CoreException {
        var projectDescriptor = project.resolve(IProjectDescription.DESCRIPTION_FILE_NAME);
        return readBonitaVersion(projectDescriptor);
    }

    public static String readBonitaVersion(Path projectDescriptor) throws CoreException {
        if (!Files.exists(projectDescriptor)) {
            throw new CoreException(Status.error(Messages.projectMigrationNoDescriptor));
        }
        var version = readDescriptor(projectDescriptor).getComment();
        if (Strings.isNullOrEmpty(version)) {
            throw new CoreException(
                    Status.error(String.format(Messages.projectMigrationInvalidDescriptor, projectDescriptor)));
        }
        return version;
    }

    public static IProjectDescription readDescriptor(Path projectDescriptor) throws CoreException {
        try (var is = Files.newInputStream(projectDescriptor)) {
            return ResourcesPlugin.getWorkspace().loadProjectDescription(is);
        } catch (IOException e) {
            throw new CoreException(Status.error(Messages.projectMigrationCantReadDescriptor, e));
        }
    }

}
