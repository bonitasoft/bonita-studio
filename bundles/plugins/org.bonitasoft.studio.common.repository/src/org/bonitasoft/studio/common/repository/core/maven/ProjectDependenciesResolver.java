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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.Dependency;
import org.apache.maven.project.MavenProject;
import org.eclipse.aether.graph.DependencyNode;
import org.eclipse.aether.graph.DependencyVisitor;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.project.IMavenProjectFacade;

@Creatable
public class ProjectDependenciesResolver {

    public List<Artifact> getCompileDependencies(IProject project, IProgressMonitor monitor) throws CoreException {
        MavenProject mavenProject = getMavenProject(project, monitor);
        if(mavenProject == null) {
            return Collections.emptyList();
        }
        List<Dependency> dependencies = mavenProject.getDependencies();
        return mavenProject.getArtifacts().stream()
                .filter(artifact -> Artifact.SCOPE_COMPILE.equals(artifact.getScope()) || Artifact.SCOPE_RUNTIME.equals(artifact.getScope()))
                .filter(artifact -> isDirectDependency(dependencies, artifact))
                .filter(artifact -> artifact.getFile() != null && artifact.getFile().exists())
                .collect(Collectors.toList());
    }

    private boolean isDirectDependency(List<Dependency> dependencies, Artifact artifact) {
        return dependencies.stream()
                .anyMatch(dep -> Objects.equals(dep.getGroupId(), artifact.getGroupId())
                        && Objects.equals(dep.getArtifactId(), artifact.getArtifactId())
                        && Objects.equals(dep.getVersion(), artifact.getVersion())
                        && Objects.equals(dep.getType(), artifact.getType())
                        && Objects.equals(dep.getClassifier(), artifact.getClassifier())
                        && Objects.equals(dep.getScope(), artifact.getScope()));
    }

    public Optional<Artifact> findCompileDependency(String fileName, IProject project, IProgressMonitor monitor)
            throws CoreException {
        MavenProject mavenProject = getMavenProject(project, monitor);
        if(mavenProject == null) {
            return Optional.empty();
        }
        return mavenProject.getArtifacts().stream()
                .filter(artifact -> Artifact.SCOPE_COMPILE.equals(artifact.getScope()) || Artifact.SCOPE_RUNTIME.equals(artifact.getScope()))
                .filter(artifact -> artifact.getFile() != null && artifact.getFile().exists())
                .filter(artifact -> Objects.equals(fileName, artifact.getFile().getName()))
                .findFirst();
    }

    public List<Artifact> getTransitiveDependencies(IProject project, Artifact artifact, IProgressMonitor monitor)
            throws CoreException {
        MavenProject mavenProject = getMavenProject(project, monitor);
        if(mavenProject == null) {
            return Collections.emptyList();
        }
        IMavenProjectFacade mavenProjectFacade = MavenPlugin.getMavenProjectRegistry().getProject(project);
        DependencyNode node = MavenPlugin.getMavenModelManager()
                .readDependencyTree(mavenProjectFacade, mavenProject, Artifact.SCOPE_COMPILE_PLUS_RUNTIME, monitor);
        ArtifactVisitor artifactVisitor = new ArtifactVisitor(artifact);
        node.accept(artifactVisitor);
        DependencyNode artifactNode = artifactVisitor.getDependencyNode();
        Collector collector = new Collector(mavenProject);
        artifactNode.getChildren()
                .stream()
                .forEach(n -> n.accept(collector));
        return collector.getResult();
    }

    private MavenProject getMavenProject(IProject project, IProgressMonitor monitor) throws CoreException {
        IMavenProjectFacade projectFacade = MavenPlugin.getMavenProjectRegistry().getProject(project);
        if (projectFacade == null) {
            return null;
        }
        return projectFacade.getMavenProject(monitor);
    }

    class ArtifactVisitor implements DependencyVisitor {

        private Artifact artifact;
        private DependencyNode matchingNode;

        ArtifactVisitor(Artifact artifact) {
            this.artifact = artifact;
        }

        DependencyNode getDependencyNode() {
            return matchingNode;
        }

        @Override
        public boolean visitEnter(DependencyNode node) {
            org.eclipse.aether.artifact.Artifact a = node.getArtifact();
            if (a != null && Objects.equals(artifact.getGroupId(), a.getGroupId())
                    && Objects.equals(artifact.getArtifactId(), a.getArtifactId())
                    && Objects.equals(artifact.getVersion(), a.getVersion())) {
                matchingNode = node;
                return false;
            }
            return true;
        }

        @Override
        public boolean visitLeave(DependencyNode node) {
            return true;
        }

    }

    class Collector implements DependencyVisitor {

        private MavenProject project;
        private List<Artifact> result = new ArrayList<>();

        Collector(MavenProject project) {
            this.project = project;
        }

        List<Artifact> getResult() {
            return result;
        }

        @Override
        public boolean visitEnter(DependencyNode node) {
            org.eclipse.aether.artifact.Artifact a = node.getArtifact();
            if (a != null) {
                Artifact artifact = project.getArtifactMap()
                        .get(a.getGroupId() + ":" + node.getArtifact().getArtifactId());
                if (artifact != null && 
                        (Artifact.SCOPE_COMPILE.equals(artifact.getScope()) || Artifact.SCOPE_RUNTIME.equals(artifact.getScope()))) {
                    result.add(artifact);
                }
            }
            return true;
        }

        @Override
        public boolean visitLeave(DependencyNode node) {
            return true;
        }

    }

}
