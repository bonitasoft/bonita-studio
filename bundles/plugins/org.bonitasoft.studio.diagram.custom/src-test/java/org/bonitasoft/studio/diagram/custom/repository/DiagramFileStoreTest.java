/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.custom.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Romain Bioteau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class DiagramFileStoreTest {

    private static final String DIAGRAM_FILE_NAME = "My Diagram - 1.0.proc";

    @Mock
    private DiagramRepositoryStore store;
    private DiagramFileStore diagramFileStore;
    @Mock
    private IFile resource;
    @Mock
    private IPath path;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        diagramFileStore = spy(new DiagramFileStore(DIAGRAM_FILE_NAME, store));
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_getResourceURI_returns_a_platform_resource_uri_pointing_the_resource_location() throws Exception {
        when(path.toOSString()).thenReturn("/aProject/diagrams/" + DIAGRAM_FILE_NAME);
        when(resource.getFullPath()).thenReturn(path);
        doReturn(resource).when(diagramFileStore).getResource();

        final URI resourceURI = diagramFileStore.getResourceURI();

        assertThat(resourceURI.isPlatformResource()).isTrue();
        assertThat(URI.decode(resourceURI.lastSegment())).isEqualTo(DIAGRAM_FILE_NAME);

    }

}
