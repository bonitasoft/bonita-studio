/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.maven.artifact.Artifact;

import com.google.common.io.Files;

public class DependencyCatalog {

    private static final String UTF_8 = "UTF-8";

    private final MavenArtifactParser parser;

    private final List<Artifact> dependencies = new ArrayList<>();

    private final File rootFolder;

    public DependencyCatalog(final File rootFolder, final MavenArtifactParser parser) {
        this.parser = parser;
        this.rootFolder = rootFolder;
    }

    public List<Artifact> getDependencies() {
        return dependencies;
    }

    public DependencyCatalog parse(final File catalogFile) throws IOException {
        checkArgument(catalogFile != null && catalogFile.exists());
        dependencies.clear();
        final String csvContent = Files.toString(catalogFile, Charset.forName(UTF_8));
        final StringTokenizer tokenizer = new StringTokenizer(csvContent, ";");
        while (tokenizer.hasMoreTokens()) {
            final String token = tokenizer.nextToken();
            dependencies.add(parser.parse(new File(token).toPath()));
        }
        return this;
    }

    public File getCatalogFile() {
        return new File(rootFolder, "dependencies.csv");
    }
}
