/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven;

import static org.assertj.core.api.Assertions.assertThat;
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
import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.bonitasoft.studio.businessobject.maven.MavenInstallFileCommand;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MavenLocalRepositoryContributorTest {

    private MavenLocalRepositoryContributor contributor;
    @Mock
    private MavenInstallFileCommand installFileCommand;
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

    @Before
    public void setUp() throws Exception {
        contributor = spy(
                new MavenLocalRepositoryContributor(internalRepository, localRepository, catalog, installFileCommand));
        doReturn(new File("dependencies.csv")).when(catalog).getCatalogFile();
        doReturn(pomFile).when(contributor).toPomFile(notNull(Artifact.class));
        doReturn(artifactFile).when(contributor).toArtifactFile(notNull(Artifact.class));
    }

    @Test
    public void should_install_artifact_not_in_local_repository() throws Exception {
        final Artifact artifactToInstall = mock(Artifact.class);
        final File file = new File("artifactFile");
        when(artifactToInstall.getFile()).thenReturn(file);
        when(artifactFile.exists()).thenReturn(true);
        when(catalog.getDependencies()).thenReturn(Arrays.asList(artifactToInstall));
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
        when(catalog.getDependencies()).thenReturn(Arrays.asList(artifactToInstall));
        when(localRepository.find(artifactToInstall)).thenReturn(artifactToInstall);

        contributor.execute();

        verify(installFileCommand, never()).installFile(anyString(), anyString(), anyString(), anyString(), anyString(),
                any(File.class), any(File.class));
    }

    @Test
    public void should_url_decode_repository_url_to_retrieve_root_folder() throws Exception {
        when(internalRepository.getUrl()).thenReturn(new File("/path/with & special characters").toURI().toURL().toString());

        final File repositoryRootFolder = contributor.repositoryRootFolder();

        assertThat(repositoryRootFolder.toPath().endsWith(Paths.get("path", "with & special characters"))).isTrue();
    }
}
