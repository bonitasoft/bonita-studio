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
import java.nio.file.Path;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.factory.DefaultArtifactFactory;

public class MavenArtifactParser {

    private final DefaultArtifactFactory artifactFactory;

    public MavenArtifactParser(final DefaultArtifactFactory artifactFactory) {
        this.artifactFactory = artifactFactory;
    }

    public Artifact parse(Path artifactPath) {
        final String filename = artifactPath.getFileName().toString();
        final String type = filename.endsWith(".jar") ? "jar" : "pom";
        return artifactFactory.createArtifactWithClassifier(resolveGroupId(artifactPath),
                resolveArtifactId(artifactPath),
                resolveArtifactVersion(artifactPath),
                type,
                resolveClassifier(filename));
    }

    private String resolveGroupId(final Path artifactPath) {
        final Path groupPath = artifactPath.subpath(0, artifactPath.getNameCount() - 3);
        return groupPath.toString().replace(File.separator, ".");
    }

    private String resolveClassifier(final String filename) {
        if (filename.endsWith("-sources.jar")) {
            return "sources";
        }
        if (filename.endsWith("-javadoc.jar")) {
            return "javadoc";
        }
        if (filename.endsWith("-noaop.jar")) {
            return "noaop";
        }
        if (filename.endsWith("-no_aop.jar")) {
            return "no_aop";
        }
        return null;
    }

    private String resolveArtifactVersion(final Path artifactPath) {
        return artifactPath.getName(artifactPath.getNameCount() - 2).toString();
    }

    private String resolveArtifactId(final Path artifactPath) {
        return artifactPath.getName(artifactPath.getNameCount() - 3).toString();
    }

}
