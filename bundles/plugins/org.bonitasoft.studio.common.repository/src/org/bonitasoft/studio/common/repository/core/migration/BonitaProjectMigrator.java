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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;

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
            MigrationStep.lookup("UidMigrationStep"));

    private Path project;

    public BonitaProjectMigrator(Path project) {
        this.project = project;
    }

    public BonitaProjectMigrator(IProject project) {
        this(project.getLocation().toFile().toPath());
    }

    public MigrationReport run(IProgressMonitor monitor) throws CoreException {
        monitor.beginTask(Messages.migrating, IProgressMonitor.UNKNOWN);
        BonitaStudioLog.info("Starting project migration at " + project);
        var sourceVersion = readBonitaVersion();
        BonitaStudioLog.info("Migrating project from version " + sourceVersion);
        var report = new MigrationReport();
        
        var steps = gatherStepsForSourceVersion(sourceVersion);
        var subMonitor = SubMonitor.convert(monitor, steps.size());
        try {
            for (var step : steps) {
                if (step.appliesToProject(project)) {
                    step.run(project, subMonitor).merge(report);
                }
                subMonitor.worked(1);
            }
        } finally {
            subMonitor.done();
        }
        BonitaStudioLog.info("Project migration successfull.");
        return report;
    }

    private List<MigrationStep> gatherStepsForSourceVersion(String sourceVersion) {
        var steps = new ArrayList<MigrationStep>();
        for (var s : STEPS) {
            if (Strings.hasText(sourceVersion) && s.appliesToVersion(sourceVersion)) {
                steps.add(s);
            }
        }
        for (var s : POST_STEPS) {
            if (Strings.hasText(sourceVersion) && s.appliesToVersion(sourceVersion)) {
                steps.add(s);
            }
        }
        return steps;
    }

    public String readBonitaVersion() throws CoreException {
        var projectDescriptor = project.resolve(IProjectDescription.DESCRIPTION_FILE_NAME);
        return readBonitaVersion(projectDescriptor);
    }

    public static String readBonitaVersion(Path projectDescriptor) throws CoreException {
        if (!Files.exists(projectDescriptor)) {
            throw new CoreException(Status.error("Project descriptor not found !"));
        }
        var version = readDescriptor(projectDescriptor).getComment();
        if (Strings.isNullOrEmpty(version)) {
            throw new CoreException(
                    Status.error(String.format("%s is not a valid Bonita Project descriptor.", projectDescriptor)));
        }
        return version;
    }

    public static IProjectDescription readDescriptor(Path projectDescriptor) throws CoreException {
        try (var is = Files.newInputStream(projectDescriptor)) {
            return ResourcesPlugin.getWorkspace().loadProjectDescription(is);
        } catch (IOException e) {
            throw new CoreException(Status.error("Failed to read project descriptor.", e));
        }
    }

}
