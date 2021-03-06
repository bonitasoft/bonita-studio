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

import java.util.Objects;
import java.util.Optional;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class RemoveDependencyOperation extends MavenModelOperation {

    private final String groupId;
    private final String artifactId;
    private final String version;
    private String type = "jar";
    private String classifier;

    public RemoveDependencyOperation(String groupId,
            String artifactId,
            String version) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }

    public RemoveDependencyOperation(Dependency dep) {
        this(dep.getGroupId(), dep.getArtifactId(), dep.getVersion());
        this.type = dep.getType();
        this.classifier = dep.getClassifier();
    }

    @Override
    public void run(IProgressMonitor monitor) throws CoreException {
        Model model = readModel(getCurrentProject());

        Dependency dependency = new Dependency();
        dependency.setArtifactId(artifactId);
        dependency.setGroupId(groupId);
        dependency.setVersion(version);
        dependency.setClassifier(classifier);
        dependency.setType(type);

        Optional<Dependency> result = model.getDependencies().stream()
                .filter(existingDep -> sameGAV(existingDep, dependency))
                .findFirst();
        if (result.isPresent()) {
            model.getDependencies().remove(result.get());
            saveModel(getCurrentProject(), model);
            getProjectDependenciesStore().analyze(monitor);
        }
    }

    private boolean sameGAV(Dependency existingDep, Dependency dependency) {
        return Objects.equals(existingDep.getGroupId(), dependency.getGroupId())
                && Objects.equals(existingDep.getArtifactId(), dependency.getArtifactId())
                && Objects.equals(existingDep.getVersion(), dependency.getVersion())
                && Objects.equals(existingDep.getClassifier(), dependency.getClassifier())
                && Objects.equals(existingDep.getType(), dependency.getType());
    }

    public String getClassifier() {
        return classifier;
    }

    public void setClassifier(String classifier) {
        this.classifier = classifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
