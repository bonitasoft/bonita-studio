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
import java.util.Objects;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.repository.BuildScheduler;
import org.bonitasoft.studio.common.repository.core.maven.model.GAV;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;

public class RemoveDependencyOperation extends MavenModelOperation {

    private final List<Dependency> dependenciesToRemove;

    public RemoveDependencyOperation(List<Dependency> dependenciesToRemove) {
        this.dependenciesToRemove = dependenciesToRemove;
    }

    public RemoveDependencyOperation(Dependency dependencyToRemove) {
        this(List.of(dependencyToRemove));
    }

    public RemoveDependencyOperation(String groupId, String artifactId, String version, String scope) {
        this(createDependency(groupId, artifactId, version, scope));
    }

    @Override
    public void run(IProgressMonitor monitor) throws CoreException {
        // BPA-834: serialize against the "build" job rule so this never reads the project's MavenProject
        // while a concurrent business-archive/analyze execution (which now resolves dependencies in-process,
        // sharing m2e's embedded Maven session since bonita-project-maven-plugin 2.1.6) is mutating it.
        BuildScheduler.<Void, CoreException>callWithBuildRule(() -> {
            doRun(monitor);
            return null;
        }, monitor);
    }

    private void doRun(IProgressMonitor monitor) throws CoreException {
        IProject currentProject = getCurrentProject();
        var mavenProject = MavenProjectHelper.getMavenProject(currentProject);
        if (mavenProject == null) {
            throw new CoreException(Status.error(String.format(
                    "Cannot remove dependencies from project %s: its Maven model could not be resolved by m2e.",
                    currentProject.getName())));
        }
        Model model = readModel(currentProject);

        for (Dependency dep : dependenciesToRemove) {
            var removed = model.getDependencies()
                    .removeIf(dependency -> new GAV(dependency).isSameAs(new GAV(dep)));
            if (!removed && Objects.equals(mavenProject.getGroupId(), dep.getGroupId())
                    && Objects.equals(mavenProject.getVersion(), dep.getVersion())) {
                var depWithProperties = dep.clone();
                depWithProperties.setGroupId("${project.groupId}");
                depWithProperties.setVersion("${project.version}");
                removed = model.getDependencies()
                        .removeIf(dependency -> Objects.equals(new GAV(dependency), new GAV(depWithProperties)));
            }
            var deleteFromStore = dep.clone();
            if ("${project.groupId}".equals(deleteFromStore.getGroupId())) {
                deleteFromStore.setGroupId(mavenProject.getGroupId());
            }
            if ("${project.version}".equals(deleteFromStore.getVersion())) {
                deleteFromStore.setVersion(mavenProject.getVersion());
            }
            getLocalStore().remove(deleteFromStore);
            modelUpdated = modelUpdated || removed;
        }

        saveModel(currentProject, model, monitor);
    }

}
