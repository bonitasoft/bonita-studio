/**
 * Copyright (C) 2021 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.repository.core.maven;

import java.util.Objects;
import java.util.Optional;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class UpdateDependencyVersionOperation extends MavenModelOperation {

    private final String groupId;
    private final String artifactId;
    private final String newVersion;

    public UpdateDependencyVersionOperation(String groupId,
            String artifactId,
            String version) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.newVersion = version;
    }

    @Override
    public void run(IProgressMonitor monitor) throws CoreException {
        Model model = readModel(getCurrentProject());

        Optional<Dependency> dependencyToUpdate = model.getDependencies()
                .stream()
                .filter(dep -> Objects.equals(dep.getGroupId(), groupId))
                .filter(dep -> Objects.equals(dep.getArtifactId(), artifactId))
                .findFirst();

        if (dependencyToUpdate.isPresent()) {
            Dependency dependencyUpdated = new Dependency();
            dependencyUpdated.setArtifactId(artifactId);
            dependencyUpdated.setGroupId(groupId);
            dependencyUpdated.setVersion(newVersion);
            dependencyUpdated.setClassifier(dependencyToUpdate.get().getClassifier());
            dependencyUpdated.setScope(dependencyToUpdate.get().getScope());
            dependencyUpdated.setType(dependencyToUpdate.get().getType());

            model.removeDependency(dependencyToUpdate.get());
            model.addDependency(dependencyUpdated);
            saveModel(getCurrentProject(), model, monitor);
        }
    }

}
