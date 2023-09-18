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
import java.util.Objects;

import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.maven.model.DefaultPluginVersions;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.osgi.framework.Version;

public class RemoveFlattenPluginExecutionStep implements MigrationStep {

	@Override
	public MigrationReport run(Path project, IProgressMonitor monitor) throws CoreException {
		var report = new MigrationReport();
		var bdmModule = project.resolve(BonitaProject.BDM_MODULE);
		if (Files.exists(bdmModule)) {
			var model = loadMavenModel(bdmModule);
			var build = model.getBuild();
			if(build.getPlugins().removeIf(p -> Objects.equals(p.getArtifactId(), DefaultPluginVersions.FLATTEN_MAVEN_PLUGIN))) {
			    saveMavenModel(model, bdmModule);
	            report.removed("The `flatten-maven-plugin` executions have been removed from the Bdm parent module. They are now inherited from the Bonita project parent.");
			}
		}
		return report;
	}

	@Override
	public boolean appliesTo(String sourceVersion) {
		return Version.parseVersion(sourceVersion).compareTo(new Version("8.0.0")) >= 0
				&& Version.parseVersion(sourceVersion).compareTo(new Version("9.0.0")) < 0;
	}

}
