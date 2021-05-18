/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven;

import static org.mockito.Mockito.verify;

import java.nio.file.Paths;

import org.apache.maven.artifact.factory.DefaultArtifactFactory;
import org.bonitasoft.studio.maven.MavenArtifactParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MavenArtifactParserTest {

    @Mock
    private DefaultArtifactFactory artifactFactory;

    @Test
    public void should_parse_filename_to_artifact() throws Exception {
        final MavenArtifactParser parser = newParser();

        parser.parse(Paths.get("REPOSITORY_ROOT", "org", "bonitasoft", "web", "rest-api-extension-archetype", "1.0.2-SNAPSHOT",
                "rest-api-extension-archetype-1.0.2-SNAPSHOT.jar"));

        verify(artifactFactory).createArtifactWithClassifier("org.bonitasoft.web",
                "rest-api-extension-archetype",
                "1.0.2-SNAPSHOT",
                "jar",
                null);
    }

    @Test
    public void should_parse_filename_to_artifact_with_source_classifier() throws Exception {
        final MavenArtifactParser parser = newParser();

        parser.parse(Paths.get("REPOSITORY_ROOT", "org", "bonitasoft", "web", "bonita-web-extensions", "7.2.0-SNAPSHOT",
                "bonita-web-extensions-1.0.2-SNAPSHOT-sources.jar"));

        verify(artifactFactory).createArtifactWithClassifier("org.bonitasoft.web",
                "bonita-web-extensions",
                "7.2.0-SNAPSHOT",
                "jar",
                "sources");
    }

    private MavenArtifactParser newParser() {
        return new MavenArtifactParser(artifactFactory);
    }
}
