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

import java.util.List;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class UpdateDependencyVersionOperation extends MavenModelOperation {

    private final List<Dependency> dependenciesToUpdate;

    public UpdateDependencyVersionOperation(List<Dependency> dependenciesToUpdate) {
        this.dependenciesToUpdate = dependenciesToUpdate;
    }
    
    public UpdateDependencyVersionOperation(Dependency dependencyToUpdate) {
        this(List.of(dependencyToUpdate));
    }
    
    public UpdateDependencyVersionOperation(String groupId,
            String artifactId,
            String version) {
        this(createDependency(groupId, artifactId, version, null));
    }

    @Override
    public void run(IProgressMonitor monitor) throws CoreException {
        Model model = readModel(getCurrentProject());

        dependenciesToUpdate.stream().forEach( dependency -> {
            Dependency dependencyToUpdate = helper.findDependency(model, dependency.getGroupId(), dependency.getArtifactId()).orElse(null);
            if (dependencyToUpdate != null) {
                Dependency dependencyUpdated = new Dependency();
                dependencyUpdated.setArtifactId(dependency.getArtifactId());
                dependencyUpdated.setGroupId(dependency.getGroupId());
                dependencyUpdated.setVersion(dependency.getVersion());
                dependencyUpdated.setClassifier(dependencyToUpdate.getClassifier());
                dependencyUpdated.setScope(dependencyToUpdate.getScope());
                dependencyUpdated.setType(dependencyToUpdate.getType());

                model.removeDependency(dependencyToUpdate);
                model.addDependency(dependencyUpdated);
                modelUpdated = true;
            }
        });        
      
        saveModel(getCurrentProject(), model, monitor);
    }

}
