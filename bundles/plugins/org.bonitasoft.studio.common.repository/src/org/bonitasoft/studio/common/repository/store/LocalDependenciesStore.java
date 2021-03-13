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
package org.bonitasoft.studio.common.repository.store;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.GAV;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class LocalDependenciesStore {

    private IProject project;
    public static final String NAME = ".store";

    public LocalDependenciesStore(IProject project) {
        this.project = project;
    }

    public DependencyLookup install(DependencyLookup dependencyLookup) throws CoreException {
        if (dependencyLookup.getStatus() == DependencyLookup.Status.FOUND || dependencyLookup.getFile() == null) {
            return dependencyLookup;
        }
        File dependencyFile = dependencyLookup.getFile();
        if (!dependencyFile.isFile()) {
            throw new CoreException(new Status(IStatus.ERROR, getClass(),
                    String.format("Cannot install %s dependency. %s is not a file.",
                            dependencyFile.getName(),
                            dependencyLookup.getFileName())));
        }
        if (!project.isAccessible()) {
            throw new CoreException(new Status(IStatus.ERROR, getClass(),
                    String.format("Cannot install %s dependency. %s is not accessible.",
                            dependencyFile.getName(),
                            project.getName())));
        }
        Path targetFolder = project.getLocation().toFile().toPath()
                .resolve(NAME)
                .resolve(dependencyLookup.getGroupId().replace(".", "/"))
                .resolve(dependencyLookup.getArtifactId())
                .resolve(dependencyLookup.getVersion());
        try {
            Files.createDirectories(targetFolder);
            if (!targetFolder.toFile().exists()) {
                throw new CoreException(new Status(IStatus.ERROR, getClass(),
                        String.format("Cannot install %s dependency. Failed to create store folders.",
                                dependencyFile.getName())));
            }
            Files.copy(dependencyFile.toPath(), 
                    targetFolder.resolve(fileName(dependencyLookup)),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new CoreException(new Status(IStatus.ERROR, getClass(),
                    String.format("Cannot install %s dependency.",
                            dependencyFile.getName()),
                    e));
        } finally {
            dependencyLookup.deleteCopy();
        }
        project.getFolder(NAME).refreshLocal(IResource.DEPTH_INFINITE, AbstractRepository.NULL_PROGRESS_MONITOR);
        return dependencyLookup;
    }

    private String fileName(DependencyLookup dependencyLookup) {
        GAV gav = dependencyLookup.getGAV();
        if(gav.getClassifier() != null) {
            return String.format("%s-%s-%s.%s", gav.getArtifactId(),
                    gav.getVersion(),
                    gav.getClassifier(),
                    gav.getType());
        }
        return String.format("%s-%s.%s", gav.getArtifactId(),
                gav.getVersion(),
                gav.getType());
    }

}
