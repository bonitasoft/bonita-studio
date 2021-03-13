/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.common.repository.core.maven.contribution;

import static com.google.common.base.Preconditions.checkState;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.installer.ArtifactInstallationException;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.core.maven.MavenInstallFileOperation;
import org.eclipse.core.runtime.CoreException;

public class MavenLocalRepositoryContributor {

    public static final String BONITA_ARTIFACT_VERSION = ProductVersion.mavenVersion();

    private final MavenInstallFileOperation installCommand;
    private final DependencyCatalog catalog;
    private final ArtifactRepository localRepository;
    private final ArtifactRepository internalRepository;

    public MavenLocalRepositoryContributor(final ArtifactRepository internalRepository,
            final ArtifactRepository localRepository,
            final DependencyCatalog catalog,
            final MavenInstallFileOperation installCommand) {
        this.localRepository = localRepository;
        this.catalog = catalog;
        this.installCommand = installCommand;
        this.internalRepository = internalRepository;
    }

    public void execute() throws IOException, CoreException, ArtifactInstallationException {
        BonitaStudioLog.info("Configuring local m2 repository...", CommonRepositoryPlugin.PLUGIN_ID);
        for (final Artifact artifact :  catalog.parseDependencies()) {
            final Artifact foundArtifact = localRepository.find(artifact);
            if (foundArtifact == null || !foundArtifact.getFile().exists()) {
                final File artifactFile = toArtifactFile(artifact);
                if (artifactFile.exists()) {
                    installCommand.installFile(artifact.getGroupId(),
                            artifact.getArtifactId(),
                            artifact.getVersion(),
                            artifact.getType(),
                            artifact.getClassifier(),
                            artifactFile,
                            toPomFile(artifact));
                } else {
                    BonitaStudioLog.warning(
                            String.format("File %s for artifact %s not found in studio internal repository",
                                    artifactFile, artifact),
                            CommonRepositoryPlugin.PLUGIN_ID);
                }
            }
        }
    }

    protected File toArtifactFile(final Artifact artifact) throws MalformedURLException, UnsupportedEncodingException {
        return new File(repositoryRootFolder(), internalRepository.pathOf(artifact));
    }

    protected File repositoryRootFolder() throws UnsupportedEncodingException, MalformedURLException {
        return new File(URLDecoder.decode(new URL(internalRepository.getUrl()).getFile(), "UTF-8"));
    }

    protected File toPomFile(final Artifact artifact) throws MalformedURLException, UnsupportedEncodingException {
        File file = new File(repositoryRootFolder(), internalRepository.pathOf(artifact));
        checkState(file.exists(),
                String.format("No file found for artifact %s in studio internal repository", artifact));
        if (file.getName().endsWith(".pom")) {
            return file;
        }
        file = new File(repositoryRootFolder(), internalRepository.pathOf(artifact).replace(".jar", ".pom"));
        return file;
    }

}
