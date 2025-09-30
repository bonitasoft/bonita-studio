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

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.License;
import org.apache.maven.model.Parent;
import org.apache.maven.model.Plugin;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.maven.model.DefaultPluginVersions;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectDefaultConfiguration;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.StepDescription;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.osgi.framework.Version;

public class CleanParentStep implements MigrationStep {

    @Override
    public StepDescription getDescription() {
        return new StepDescription(Messages.cleanParentMigrationTitle, Messages.cleanParentMigrationDescription);
    }

    @Override
    public MigrationReport run(Path project, IProgressMonitor monitor) throws CoreException {
        monitor.subTask(Messages.cleanParentMigrationTitle);
        BonitaStudioLog.info(String.format("Starting %s...", CleanParentStep.class.getName()));
        var report = new MigrationReport();
        var model = loadParentMavenModel(project);
        var defaultConfiguration = new ProjectDefaultConfiguration(ProductVersion.BONITA_RUNTIME_VERSION);
        var properties = model.getProperties();
        defaultConfiguration.getProperties().keySet().forEach(property -> {
            if (properties.containsKey(property)) {
                properties.remove(property);
                report.removed(String.format(
                        "`%s` property has been removed from parent pom. This property is now inherited from the Bonita Project parent pom.",
                        property));
            }
        });
        var dependencyManagement = model.getDependencyManagement();
        if (dependencyManagement != null) {
            dependencyManagement.getDependencies().removeIf(this::isBonitaRuntimeBom);
            report.removed(
                    "`org.bonitasoft.runtime:bonita-runtime-bom` BOM has been removed from parent pom. This BOM is now inherited from the Bonita Project parent pom.");
            if (dependencyManagement.getDependencies().isEmpty()) {
                model.setDependencyManagement(null);
            }
        }
        var build = model.getBuild();
        if (build != null) {
            var pluginManagement = build.getPluginManagement();
            defaultConfiguration.getPlugins()
                    .forEach(plugin -> {
                        Plugin managedPlugin = plugin.toManagedPlugin();
                        if (pluginManagement.getPlugins().contains(managedPlugin)) {
                            pluginManagement.removePlugin(managedPlugin);
                            report.removed(String.format(
                                    "`%s:%s` plugin has been removed from parent pom. Its configuration is now inherited from the Bonita Project parent pom.",
                                    managedPlugin.getGroupId(), managedPlugin.getArtifactId()));
                        }
                    });
            if (pluginManagement.getPlugins().isEmpty()) {
                build.setPluginManagement(null);
            }
        }
        var parent = new Parent();
        parent.setGroupId(DefaultPluginVersions.BONITA_PROJECT_GROUP_ID);
        parent.setArtifactId(DefaultPluginVersions.BONITA_PROJECT_ARTIFACT_ID);
        parent.setVersion(ProductVersion.BONITA_RUNTIME_VERSION);
        model.setParent(parent);
        if (model.getLicenses().isEmpty()) {
            // Set empty license to avoid inheriting from the GPLv2 parent license
            model.setLicenses(List.of(new License()));
        }
        saveMavenModel(model, project);
        report.updated(
                "This project now depends on the Bonita project parent pom. This parent pom configures all the required plugins and dependencies versions for a given Bonita version.");
        BonitaStudioLog.info(String.format("%s completed.", CleanParentStep.class.getName()));
        return report;
    }

    private boolean isBonitaRuntimeBom(Dependency dep) {
        var bomDep = ProjectDefaultConfiguration.runtimeBOMImportDependency();
        return Objects.equals(bomDep.getGroupId(), dep.getGroupId())
                && Objects.equals(bomDep.getArtifactId(), dep.getArtifactId())
                && Objects.equals(bomDep.getScope(), dep.getScope())
                && Objects.equals(bomDep.getType(), dep.getType());
    }

    @Override
    public boolean appliesToVersion(String sourceVersion) {
        return Version.parseVersion(sourceVersion).compareTo(new Version("7.13.0")) >= 0
                && Version.parseVersion(sourceVersion).compareTo(new Version("9.0.0")) < 0;
    }

}
