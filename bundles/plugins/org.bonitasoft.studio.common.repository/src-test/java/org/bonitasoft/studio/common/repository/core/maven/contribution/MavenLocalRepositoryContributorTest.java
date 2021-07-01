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


import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.Arrays;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.bonitasoft.studio.common.repository.core.maven.MavenInstallFileOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MavenLocalRepositoryContributorTest {

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

    @Before
    public void setUp() throws Exception {
        contributor = spy(
                new MavenLocalRepositoryContributor(rootFolder, localRepository, catalog, installFileCommand));
        doReturn(pomFile).when(contributor).toPomFile(notNull(Artifact.class));
        doReturn(artifactFile).when(contributor).toArtifactFile(notNull(Artifact.class));
    }

    @Test
    public void should_install_artifact_not_in_local_repository() throws Exception {
        final Artifact artifactToInstall = mock(Artifact.class);
        final File file = new File("artifactFile");
        when(artifactToInstall.getFile()).thenReturn(file);
        when(artifactFile.exists()).thenReturn(true);
        when(catalog.parseDependencies()).thenReturn(Arrays.asList(artifactToInstall));
        when(localRepository.find(artifactToInstall)).thenReturn(null);

        contributor.execute();

        verify(installFileCommand).installFile(anyString(), anyString(), anyString(), anyString(), anyString(),
                any(File.class), any(File.class));
    }

    @Test
    public void should_not_install_artifact_already_in_local_repository() throws Exception {
        final Artifact artifactToInstall = mock(Artifact.class);
        final File file = mock(File.class);
        when(file.exists()).thenReturn(true);
        when(artifactToInstall.getFile()).thenReturn(file);
        when(catalog.parseDependencies()).thenReturn(Arrays.asList(artifactToInstall));
        when(localRepository.find(artifactToInstall)).thenReturn(artifactToInstall);

        contributor.execute();

        verify(installFileCommand, never()).installFile(anyString(), anyString(), anyString(), anyString(), anyString(),
                any(File.class), any(File.class));
    }

}
