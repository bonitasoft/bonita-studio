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
package org.bonitasoft.studio.common.repository.core.maven.repository;

import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import org.apache.maven.artifact.repository.ArtifactRepository;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.IMaven;

public class MavenRepositories {

    private static final String INTERNAL_REPOSITORY = "internal-repository";

    /**
     * Located in the studio workspace metadata. Used internally to determine if a dependency is accessible or not.
     */
    public static ArtifactRepository internalRepository() {
        try {
            ArtifactRepository internalRepository = maven().createArtifactRepository(INTERNAL_REPOSITORY,
                    URLDecoder.decode(internalRepositoryPath().toUri().toURL().toString(), StandardCharsets.UTF_8));
            if (!INTERNAL_REPOSITORY.equals(internalRepository.getId())) { // Check if the repository is mirrored 
                internalRepository = internalRepository.getMirroredRepositories().stream()
                        .filter(repo -> INTERNAL_REPOSITORY.equals(repo.getId()))
                        .findFirst()
                        .orElseThrow();
            }
            return internalRepository;
        } catch (MalformedURLException | CoreException e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    private static Path internalRepositoryPath() {
        IPath stateLocation = CommonRepositoryPlugin.getDefault().getStateLocation();
        return stateLocation.toFile().toPath().resolve(INTERNAL_REPOSITORY);
    }

    static IMaven maven() {
        return MavenPlugin.getMaven();
    }

}
