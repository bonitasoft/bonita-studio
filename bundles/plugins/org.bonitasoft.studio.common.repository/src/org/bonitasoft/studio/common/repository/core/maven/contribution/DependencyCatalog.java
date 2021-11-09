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
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

import org.apache.maven.artifact.Artifact;

public class DependencyCatalog {

    private final MavenArtifactParser parser;
    private final File rootFolder;

    public DependencyCatalog(final File rootFolder, final MavenArtifactParser parser) {
        this.parser = parser;
        this.rootFolder = rootFolder;
    }

    public Set<Artifact> parseDependencies() throws IOException {
        Set<Artifact> result = new HashSet<>();
        Files.walkFileTree(rootFolder.toPath(), new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                    throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException {
                if (file.getFileName().toString().endsWith(".jar")
                        || file.getFileName().toString().endsWith(".pom")
                        || file.getFileName().toString().endsWith(".zip")) {
                    Artifact artifact = parser.parse(rootFolder.toPath().relativize(file));
                    result.add(artifact);
                }
                return FileVisitResult.CONTINUE;
            }
        });

        return result;
    }
}
