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

import java.util.List;

import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.bonitasoft.studio.common.repository.core.migration.step.CreatePomMigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.step.RemoveLegacyFolderStep;
import org.bonitasoft.studio.common.repository.core.migration.step.UpdateBonitaRuntimeVersionInPomStep;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class BonitaProjectMigrator {

    private static final List<MigrationStep> STEPS = List.of(
            new CreatePomMigrationStep(),
            new RemoveLegacyFolderStep(),
            new UpdateBonitaRuntimeVersionInPomStep());

    private IProject project;

    public BonitaProjectMigrator(IProject project) {
        this.project = project;
    }

    public MigrationReport run(IProgressMonitor monitor) throws CoreException {
        var report = new MigrationReport();
        for (var step : STEPS) {
            var sourceVersion = project.getDescription().getComment();
            if (Strings.hasText(sourceVersion) && step.appliesTo(sourceVersion)) {
                step.run(project, monitor).merge(report);
            }
        }
        return report;
    }

}
