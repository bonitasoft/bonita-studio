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

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.maven.model.DefaultPluginVersions;
import org.bonitasoft.studio.common.repository.core.maven.model.MavenDependency;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectDefaultConfiguration;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.StepDescription;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.osgi.framework.Version;

public class SplitGroovyAllIntoModulesStep implements MigrationStep {

    @Override
    public StepDescription getDescription() {
        return new StepDescription(Messages.splitGroovyAllMigrationTitle, Messages.splitGroovyAllMigrationDescription);
    }

    @Override
    public MigrationReport run(Path project, IProgressMonitor monitor) throws CoreException {
        monitor.subTask(Messages.splitGroovyAllMigrationTitle);
        BonitaStudioLog.info(String.format("Starting %s...", SplitGroovyAllIntoModulesStep.class.getName()));
        var model = loadMavenModel(project);

        if (model.getDependencies().removeIf(has(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, "groovy-all"))) {
            ProjectDefaultConfiguration.PROVIDED_DEPENDENCIES
                    .stream()
                    .map(MavenDependency::toDependency)
                    .filter(has(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID))
                    .forEach(model.getDependencies()::add);

            saveMavenModel(model, project);
            BonitaStudioLog.info("groovy-all artifact dependency has been replaced with Groovy module's artifact");
        }
        BonitaStudioLog.info(String.format("%s completed.", SplitGroovyAllIntoModulesStep.class.getName()));
        return MigrationReport.emptyReport();
    }

    @Override
    public boolean appliesToVersion(String sourceVersion) {
        return Version.parseVersion(sourceVersion).compareTo(new Version("7.13.0")) >= 0;
    }

    @Override
    public boolean appliesToProject(Path projectRoot) throws CoreException {
        var model = loadMavenModel(projectRoot);
        return model.getDependencies().stream()
                .anyMatch(has(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, "groovy-all"));
    }

}
