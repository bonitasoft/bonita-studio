/**
 * Copyright (C) 2023 BonitaSoft S.A.
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
import java.util.Properties;

import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectDefaultConfiguration;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.osgi.framework.Version;

public class UpdateMavenPluginVersionInPomStep implements MigrationStep {

    @Override
    public MigrationReport run(Path project, IProgressMonitor monitor) throws CoreException {
        var model = loadParentMavenModel(project);
        Properties properties = model.getProperties();
        var report = new MigrationReport();
        var defaultConfiguration = new ProjectDefaultConfiguration(ProductVersion.BONITA_RUNTIME_VERSION);
        for (var plugin : defaultConfiguration.getPlugins()) {
            var versionProperty = plugin.getVersionPropertyName();
            var currentVersion = properties.getProperty(versionProperty);
            if (properties.containsKey(versionProperty) &&
                    new DefaultArtifactVersion(plugin.getVersion())
                            .compareTo(new DefaultArtifactVersion(currentVersion)) > 0) {
                properties.setProperty(versionProperty, plugin.getVersion());
                report.updated(String.format("`%s` has been updated from `%s` to `%s`", versionProperty, currentVersion,
                        plugin.getVersion()));
            } else if (!properties.containsKey(versionProperty)) {
                properties.setProperty(versionProperty, plugin.getVersion());
                var pluginManagement = model.getBuild().getPluginManagement();
                pluginManagement.addPlugin(plugin.toManagedPlugin());
                report.added(String.format("`%s:%s` has been added to the managed plug-ins list.", plugin.getGroupId(),
                        plugin.getArtifactId()));
            }
        }
        if (report.hasUpdates() || report.hasAdditions()) {
            saveMavenModel(model, project);
        }
        return report;
    }

    @Override
    public boolean appliesTo(String sourceVersion) {
        return Version.parseVersion(sourceVersion).compareTo(new Version("7.13.0")) >= 0;
    }

}
