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

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.repository.ArtifactRepositoryPolicy;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.IMaven;

public class MavenRepositories {

    private static final String INTERNAL_REPOSITORY = "internal-repository";
    private static final String LOCAL_REPOSITORY_ID = "local";

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
            var policy = new ArtifactRepositoryPolicy();
            policy.setUpdatePolicy(ArtifactRepositoryPolicy.UPDATE_POLICY_NEVER);
            internalRepository.setReleaseUpdatePolicy(policy);
            internalRepository.setSnapshotUpdatePolicy(policy);
            return internalRepository;
        } catch (MalformedURLException | CoreException e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    public static ArtifactRepository bundledRepository() {
        try {
            File rootFolder = getBundledRepositoryRootFolder();
            if (rootFolder == null) {
                BonitaStudioLog.warning("No local repository bundled with Studio binary",
                        CommonRepositoryPlugin.PLUGIN_ID);
                return null;
            }
            ArtifactRepository internalRepository = maven().createArtifactRepository(LOCAL_REPOSITORY_ID,
                    URLDecoder.decode(rootFolder.toURI().toURL().toString(), StandardCharsets.UTF_8));
            if (!LOCAL_REPOSITORY_ID.equals(internalRepository.getId())) { // Check if the repository is mirrored 
                internalRepository = internalRepository.getMirroredRepositories().stream()
                        .filter(repo -> LOCAL_REPOSITORY_ID.equals(repo.getId()))
                        .findFirst()
                        .orElseThrow();
            }
            var policy = new ArtifactRepositoryPolicy();
            policy.setUpdatePolicy(ArtifactRepositoryPolicy.UPDATE_POLICY_NEVER);
            internalRepository.setReleaseUpdatePolicy(policy);
            internalRepository.setSnapshotUpdatePolicy(policy);
            return internalRepository;
        } catch (Exception e) {
            BonitaStudioLog.error(e);
            return null;
        }
    }

    public static File getBundledRepositoryRootFolder() throws IOException {
        URL repositoryURL = CommonRepositoryPlugin.getDefault().getBundle().getResource("/repository/");
        if (repositoryURL != null) {
            return new File(FileLocator.toFileURL(repositoryURL).getFile());
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
    
    public static String[] listBonitaRuntimeBomVersions() throws IOException{
        File rootFolder = MavenRepositories.getBundledRepositoryRootFolder();
        if(rootFolder == null) {
            return new String[0];
        }
        File bomArtifactFolder = rootFolder.toPath()
            .resolve("org")
            .resolve("bonitasoft")
            .resolve("runtime")
            .resolve("bonita-runtime-bom")
            .toFile();
        
        return bomArtifactFolder.list();
    }

}
