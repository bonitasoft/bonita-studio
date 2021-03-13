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
package org.bonitasoft.studio.common.repository.core.maven;

import java.util.List;
import java.util.Optional;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.GAV;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class RemoveDependencyOperation extends MavenModelOperation {

    private final List<Dependency> dependenciesToRemove;

    public RemoveDependencyOperation(List<Dependency> dependenciesToRemove) {
        this.dependenciesToRemove = dependenciesToRemove;
    }
    
    public RemoveDependencyOperation(Dependency dependencyToRemove) {
        this(List.of(dependencyToRemove));
    }
    
    public RemoveDependencyOperation(String groupId,
            String artifactId,
            String version) {
        this(createDependency(groupId, artifactId, version, null));
    }

    @Override
    public void run(IProgressMonitor monitor) throws CoreException {
        Model model = readModel(getCurrentProject());
     
        dependenciesToRemove.stream().forEach( dependency -> {
            Optional<Dependency> result = model.getDependencies().stream()
                    .filter(existingDep -> new GAV(existingDep).equals(new GAV(dependency)))
                    .findFirst();
            if (result.isPresent()) {
                model.getDependencies().remove(result.get());
                modelUpdated = true;
            }
        });

        saveModel(getCurrentProject(), model, monitor);
    }

}
