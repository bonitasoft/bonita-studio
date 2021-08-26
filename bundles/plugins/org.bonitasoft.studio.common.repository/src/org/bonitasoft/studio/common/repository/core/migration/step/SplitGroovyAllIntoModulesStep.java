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

import org.bonitasoft.studio.common.repository.core.maven.model.DefaultPluginVersions;
import org.bonitasoft.studio.common.repository.core.maven.model.MavenDependency;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectDefaultConfiguration;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.osgi.framework.Version;

public class SplitGroovyAllIntoModulesStep implements MigrationStep {

    @Override
    public MigrationReport run(IProject project, IProgressMonitor monitor) throws CoreException {
        var model = loadMavenModel(project);

        if (model.getDependencies().removeIf(has(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, "groovy-all"))) {
            ProjectDefaultConfiguration.PROVIDED_DEPENDENCIES
                    .stream()
                    .map(MavenDependency::toProvidedDependency)
                    .filter(has(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID))
                    .forEach(model.getDependencies()::add);

            saveMavenModel(model, project);
        }
        return MigrationReport.emptyReport();
    }

    @Override
    public boolean appliesTo(String sourceVersion) {
        return Version.parseVersion(sourceVersion).compareTo(new Version("7.13.0")) >= 0;
    }

}
