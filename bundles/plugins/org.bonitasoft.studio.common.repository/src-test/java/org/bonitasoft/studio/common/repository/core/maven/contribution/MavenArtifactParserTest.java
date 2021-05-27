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


import static org.mockito.Mockito.verify;

import java.nio.file.Paths;

import org.apache.maven.artifact.factory.DefaultArtifactFactory;
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

        parser.parse(Paths.get("org", "bonitasoft", "web", "rest-api-extension-archetype", "1.0.2-SNAPSHOT",
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

        parser.parse(Paths.get("org", "bonitasoft", "web", "bonita-web-extensions", "7.2.0-SNAPSHOT",
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
