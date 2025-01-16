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
package org.bonitasoft.studio.common.repository.core.migration.step;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginExecution;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.StepDescription;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.osgi.framework.Version;

public class BdmAssemblyConfigurationStep implements MigrationStep {

    @Override
    public StepDescription getDescription() {
        return new StepDescription(Messages.bdmAssemblyConfigurationMigrationTitle,
                Messages.bdmAssemblyConfigurationMigrationDescription);
    }

    @Override
    public MigrationReport run(Path project, IProgressMonitor monitor) throws CoreException {
        monitor.subTask(Messages.bdmAssemblyConfigurationMigrationTitle);
        BonitaStudioLog.info(String.format("Starting %s...", BdmAssemblyConfigurationStep.class.getName()));
        var report = new MigrationReport();
        var bdmModelModule = project.resolve(BonitaProject.BDM_MODULE).resolve("model");
        if (Files.exists(bdmModelModule)) {
            var model = loadMavenModel(bdmModelModule);
            var build = model.getBuild();
            build.addPlugin(assemblyPlugin());
            saveMavenModel(model, bdmModelModule);
            report.updated("Bdm model module build configuration has been updated to support Maven build.");
        }
        var bdmDaoClientModule = project.resolve(BonitaProject.BDM_MODULE).resolve("dao-client");
        if (Files.exists(bdmDaoClientModule)) {
            var model = loadMavenModel(bdmDaoClientModule);
            var dependencies = model.getDependencies();
            var dependency = javaxPersistenceDependency();
            dependencies.add(dependency);
            saveMavenModel(model, bdmDaoClientModule);
            report.added(String.format("`%s:%s` provided dependency has been added to the BDM dao-client module.",
                    dependency.getGroupId(),
                    dependency.getArtifactId()));
        }
        BonitaStudioLog.info(String.format("%s completed.", BdmAssemblyConfigurationStep.class.getName()));
        return report;
    }

    private Dependency javaxPersistenceDependency() {
        var dependency = new Dependency();
        dependency.setGroupId("javax.persistence");
        dependency.setArtifactId("javax.persistence-api");
        dependency.setScope(Artifact.SCOPE_PROVIDED);
        return dependency;
    }

    private Plugin assemblyPlugin() {
        var plugin = new Plugin();
        plugin.setArtifactId("maven-assembly-plugin");
        var execution = new PluginExecution();
        execution.setId("bdm-descriptor-archive");
        execution.setGoals(List.of("single"));
        plugin.addExecution(execution);
        return plugin;
    }

    @Override
    public boolean appliesToVersion(String sourceVersion) {
        return Version.parseVersion(sourceVersion).compareTo(new Version("8.0.0")) >= 0
                && Version.parseVersion(sourceVersion).compareTo(new Version("9.0.0")) < 0;
    }

}
