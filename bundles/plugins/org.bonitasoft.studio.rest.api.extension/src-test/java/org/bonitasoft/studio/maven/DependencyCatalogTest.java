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
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.net.URLDecoder;
import java.nio.file.Path;

import org.apache.maven.artifact.Artifact;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DependencyCatalogTest {

    @Mock
    private MavenArtifactParser parser;

    @Test
    public void should_pare_csv_catalog_file() throws Exception {
        final DependencyCatalog dependencyCatalog = newDependencyCatalog()
                .parse(new File(URLDecoder.decode(DependencyCatalogTest.class.getResource("/dependencies.csv").getFile(),"UTF-8")));

        assertThat(dependencyCatalog).isNotNull();
        assertThat(dependencyCatalog.getDependencies()).hasSize(26);
    }

    private DependencyCatalog newDependencyCatalog() {
        when(parser.parse(notNull(Path.class))).thenReturn(mock(Artifact.class));
        return new DependencyCatalog(new File("root"), parser);
    }

}
