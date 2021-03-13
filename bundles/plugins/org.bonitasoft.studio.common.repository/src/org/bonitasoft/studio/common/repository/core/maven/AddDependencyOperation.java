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

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.GAV;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class AddDependencyOperation extends MavenModelOperation {

    private final List<Dependency> dependenciesToAdd;

    public AddDependencyOperation(List<Dependency> dependenciesToAdd) {
        this.dependenciesToAdd = dependenciesToAdd;
    }
    
    public AddDependencyOperation(Dependency dependencyToAdd) {
        this(List.of(dependencyToAdd));
    }
    
    public AddDependencyOperation(String groupId,
            String artifactId,
            String version) {
        this(createDependency(groupId, artifactId, version, null));
    }
    
    public AddDependencyOperation(String groupId,
            String artifactId,
            String version,
            String scope) {
        this(createDependency(groupId, artifactId, version, scope));
    }


    @Override
    public void run(IProgressMonitor monitor) throws CoreException {
        Model model = readModel(getCurrentProject());

        dependenciesToAdd.stream().forEach( dep -> {
            if (model.getDependencies()
                    .stream()
                    .noneMatch(existingDep -> new GAV(existingDep).equals(new GAV(dep)))) {
                model.getDependencies().add(dep);
                modelUpdated = true;
            }
        });

        saveModel(getCurrentProject(), model, monitor);
    }

}
