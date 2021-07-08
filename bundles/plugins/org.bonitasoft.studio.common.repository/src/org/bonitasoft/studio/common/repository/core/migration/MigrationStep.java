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

import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public interface MigrationStep {

    MigrationReport run(IProject project, IProgressMonitor monitor) throws CoreException;

    boolean appliesTo(String sourceVersion);

    default Model loadMavenModel(IProject project) throws CoreException {
        var model = new MavenProjectHelper().getMavenModel(project);
        if (model == null) {
            throw new CoreException(new Status(IStatus.ERROR, MigrationStep.class,
                    String.format("Cannot load maven model (%s file not found)",
                            project.getFile("pom.xml").getLocation().toFile().getAbsolutePath())));
        }
        return model;
    }

    default void saveMavenModel(Model model, IProject project) throws CoreException {
        new MavenProjectHelper().saveModel(project, model);
    }
}
