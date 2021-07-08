/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.common.repository.core.maven.contribution;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import org.apache.maven.artifact.Artifact;
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
    private final ArtifactRepository targetRepository;
    private File internalRepositoryRootFolder;

    public MavenLocalRepositoryContributor(final File internalRepositoryRootFolder,
            final ArtifactRepository targetRepository,
            final DependencyCatalog catalog,
            final MavenInstallFileOperation installCommand) {
        this.targetRepository = targetRepository;
        this.catalog = catalog;
        this.installCommand = installCommand;
        this.internalRepositoryRootFolder = internalRepositoryRootFolder;
    }

    public void execute() throws IOException, CoreException {
        BonitaStudioLog.info(String.format("Configuring %s maven repository...", targetRepository.getId()),
                CommonRepositoryPlugin.PLUGIN_ID);
        Instant start = Instant.now();
        var embeddedArtifacts = catalog.parseDependencies();
        for (final Artifact artifact : embeddedArtifacts) {
            final Artifact foundArtifact = targetRepository.find(artifact);
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
        BonitaStudioLog.info(String.format("Required dependencies installed in %s maven repository in %ss",
                targetRepository.getId(), Duration.between(start, Instant.now()).getSeconds()),
                CommonRepositoryPlugin.PLUGIN_ID);
    }

    protected File toArtifactFile(final Artifact artifact) {
        return new File(internalRepositoryRootFolder, targetRepository.pathOf(artifact));
    }

    protected File toPomFile(final Artifact artifact) {
        File file = new File(internalRepositoryRootFolder, targetRepository.pathOf(artifact));
        if (!file.exists()) {
            throw new IllegalStateException(
                    String.format("No file found for artifact %s in studio embedded repository", artifact));
        }
        if (file.getName().endsWith(".pom")) {
            return file;
        }
        return new File(internalRepositoryRootFolder, targetRepository.pathOf(artifact).replace(".jar", ".pom"));
    }

}
