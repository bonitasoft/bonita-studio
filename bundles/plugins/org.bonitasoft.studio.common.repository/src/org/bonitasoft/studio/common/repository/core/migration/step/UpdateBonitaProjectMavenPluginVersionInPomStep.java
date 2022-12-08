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
import java.util.Objects;
import java.util.Properties;

import org.bonitasoft.studio.common.repository.core.maven.model.DefaultPluginVersions;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Version;

public class UpdateBonitaProjectMavenPluginVersionInPomStep implements MigrationStep {

    @Override
    public MigrationReport run(Path project, IProgressMonitor monitor) throws CoreException {
        var model = loadParentMavenModel(project);
        Properties properties = model.getProperties();
        String pluginVersionPropertyName = DefaultPluginVersions.BONITA_PROJECT_MAVEN_PLUGIN_ARTIFACT_ID + ".version";
        if (!properties.containsKey(pluginVersionPropertyName)) {
            throw new CoreException(new Status(IStatus.ERROR,
                    UpdateBonitaProjectMavenPluginVersionInPomStep.class,
                    String.format("The %s property has not been found and cannot be updated.",
                            pluginVersionPropertyName)));
        }
        var oldVersion = properties.getProperty(pluginVersionPropertyName);
        var currentVersion = DefaultPluginVersions.BONITA_PROJECT_MAVEN_PLUGIN_DEFAULT_VERSION;
        if (!Objects.equals(oldVersion, currentVersion)) {
            var report = new MigrationReport();
            properties.setProperty(pluginVersionPropertyName, currentVersion);
            saveMavenModel(model, project);
            report.updated(String.format("`%s` has been updated from `%s` to `%s`.",
                    pluginVersionPropertyName, 
                    oldVersion,
                    currentVersion));
            return report;
        }
        return MigrationReport.emptyReport();
    }

    @Override
    public boolean appliesTo(String sourceVersion) {
        return Version.parseVersion(sourceVersion).compareTo(new Version("7.13.0")) >= 0;
    }

}
