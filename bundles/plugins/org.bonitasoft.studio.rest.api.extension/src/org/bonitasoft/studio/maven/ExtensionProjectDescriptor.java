/**
 * Copyright (C) 2019 BonitaSoft S.A.
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
package org.bonitasoft.studio.maven;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.maven.project.MavenProject;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.project.IMavenProjectRegistry;

public class ExtensionProjectDescriptor {

    protected IProject project;

    public ExtensionProjectDescriptor() {
    }

    public ExtensionProjectDescriptor(final IProject project) {
        this.project = project;
    }

    protected IMavenProjectRegistry mavenProjectRegistry() {
        return MavenPlugin.getMavenProjectRegistry();
    }

    public IProject getProject() {
        return project;
    }

    public String getName() {
        return project != null ? project.getName() : null;
    }

    protected void ensureProjectOpen() {
        if (project != null && !project.isOpen()) {
            try {
                project.open(AbstractRepository.NULL_PROGRESS_MONITOR);
            } catch (final CoreException e) {
                BonitaStudioLog.error("Failed to open project " + getName(), e);
            }
        }
    }

    public Optional<MavenProject> getMavenProject() {
        return Optional.ofNullable(project)
                .map(p -> MavenPlugin.getMavenProjectRegistry().getProject(p))
                .filter(Objects::nonNull)
                .map(facade -> {
                    try {
                        return facade.getMavenProject(AbstractRepository.NULL_PROGRESS_MONITOR);
                    } catch (CoreException e) {
                        BonitaStudioLog.error(e);
                        return null;
                    }
                })
                .filter(Objects::nonNull);
    }

    public String getArtifactId() {
        return getMavenProject().map(MavenProject::getArtifactId).orElse(null);
    }

    public String getVersion() {
        return getMavenProject().map(MavenProject::getVersion).orElse(null);
    }

    public List<IFile> getFilesToOpen() {
        ensureProjectOpen();
        var main = project.getFolder("src/main");
        List<IFile> sourceAndPropertiesFiles = new ArrayList<>(2);
        try {
            main.accept(resource -> {
                if (resource instanceof IFile file) {
                    if (file.getName().endsWith(".groovy") || file.getName().endsWith(".java")) {
                        sourceAndPropertiesFiles.add(0, file);
                    } else if (file.getName().endsWith(".properties")) {
                        sourceAndPropertiesFiles.add(file);
                    }
                    return false;
                }
                return true;
            });
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
        }
        return sourceAndPropertiesFiles;
    }

    public String getDescription() {
        return getMavenProject().map(MavenProject::getDescription).orElse(null);
    }

    public String getDisplayName() {
        return getMavenProject().map(MavenProject::getName).orElse(null);
    }

    public String getGroupId() {
        return getMavenProject().map(MavenProject::getGroupId).orElse(null);
    }

    public String getClassifier() {
        return null;
    }

}
