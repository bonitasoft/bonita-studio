/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.repository.core.maven.contribution;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.Set;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.bonitasoft.studio.common.repository.core.maven.MavenInstallFileOperation;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MavenLocalRepositoryContributorTest {

    private MavenLocalRepositoryContributor contributor;
    @Mock
    private MavenInstallFileOperation installFileCommand;
    @Mock
    private ArtifactRepository localRepository;
    @Mock
    private ArtifactRepository internalRepository;
    @Mock
    private DependencyCatalog catalog;
    @Mock
    private File pomFile;
    @Mock
    private File artifactFile;
    @Mock
    private File rootFolder;

    @BeforeEach
    public void setUp() throws Exception {
        contributor = spy(
                new MavenLocalRepositoryContributor(rootFolder, localRepository, catalog, installFileCommand));
        lenient().doReturn(pomFile).when(contributor).toPomFile(notNull());
        lenient().doReturn(artifactFile).when(contributor).toArtifactFile(notNull());
    }

    @Test
    void should_install_artifact_not_in_local_repository() throws Exception {
        final Artifact artifactToInstall = mock(Artifact.class);
        when(artifactFile.exists()).thenReturn(true);
        when(catalog.parseDependencies()).thenReturn(Set.of(artifactToInstall));
        when(localRepository.find(artifactToInstall)).thenReturn(null);

        contributor.execute(new NullProgressMonitor());

        verify(installFileCommand).installFile(any(), any(), any(), any(), any(),
                notNull(), any(), any());
    }

    @Test
    void should_not_install_artifact_already_in_local_repository() throws Exception {
        final Artifact artifactToInstall = mock(Artifact.class);
        final File file = mock(File.class);
        when(file.exists()).thenReturn(true);
        when(artifactToInstall.getFile()).thenReturn(file);
        when(catalog.parseDependencies()).thenReturn(Set.of(artifactToInstall));
        when(localRepository.find(artifactToInstall)).thenReturn(artifactToInstall);

        var monitor = new NullProgressMonitor();
        contributor.execute(monitor);

        verify(installFileCommand, never()).installFile(anyString(), anyString(), anyString(), anyString(), anyString(),
                any(File.class), any(File.class),eq(monitor));
    }

}
