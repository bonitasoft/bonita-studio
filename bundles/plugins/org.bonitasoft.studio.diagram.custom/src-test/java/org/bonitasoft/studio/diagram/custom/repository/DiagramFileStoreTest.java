/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.diagram.custom.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class DiagramFileStoreTest {

    private static final String DIAGRAM_FILE_NAME = "My Diagram-1.0.proc";

    @Mock
    private DiagramRepositoryStore store;

    @Mock
    private Repository repository;

    @Mock
    private IFile resource;

    @Mock
    private ProcessConfigurationRepositoryStore processConfStore;

    private DiagramFileStore diagramFileStore;

    @Mock
    private ProcessConfigurationFileStore processConfFStore;


    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        diagramFileStore = spy(new DiagramFileStore(DIAGRAM_FILE_NAME, store));
    }

    @Test
    public void should_returns_a_platform_resource_uri_pointing_the_resource_location() throws Exception {
        //Given
        when(resource.getFullPath()).thenReturn(Path.fromOSString("/aProject/diagrams/" + DIAGRAM_FILE_NAME));
        doReturn(resource).when(diagramFileStore).getResource();

        //When
        final URI resourceURI = diagramFileStore.getResourceURI();

        //Then
        assertThat(resourceURI.isPlatformResource()).isTrue();
        assertThat(URI.decode(resourceURI.lastSegment())).isEqualTo(DIAGRAM_FILE_NAME);
    }

    @Test
    public void should_have_related_fileStores_for_process_config() throws Exception {
        //Given
        doReturn(repository).when(diagramFileStore).getRepository();
        doReturn(Collections.singletonList(aPoolInAResourceWithUUID("aProcessUUID"))).when(diagramFileStore).getProcesses();
        when(repository.getRepositoryStore(ProcessConfigurationRepositoryStore.class)).thenReturn(processConfStore);
        when(processConfStore.getChild("aProcessUUID.conf")).thenReturn(processConfFStore);

        //When
        final Set<IRepositoryFileStore> relatedFileStore = diagramFileStore.getRelatedFileStore();

        //Then
        assertThat(relatedFileStore).contains(processConfFStore);
    }

    @Test
    public void should_not_contains_null_refrences_in_related_fileStores() throws Exception {
        //Given
        doReturn(repository).when(diagramFileStore).getRepository();
        doReturn(Collections.singletonList(aPoolInAResourceWithUUID("aProcessUUID"))).when(diagramFileStore).getProcesses();
        when(repository.getRepositoryStore(ProcessConfigurationRepositoryStore.class)).thenReturn(processConfStore);
        when(processConfStore.getChild("aProcessUUID.conf")).thenReturn(null);

        //When
        final Set<IRepositoryFileStore> relatedFileStore = diagramFileStore.getRelatedFileStore();

        //Then
        assertThat(relatedFileStore).doesNotContainNull();
    }

    @Test
    public void should_have_a_display_name() throws Exception {
        //Given
        when(resource.getLocation()).thenReturn(Path.fromOSString("/diagrams/" + DIAGRAM_FILE_NAME));
        doReturn(resource).when(diagramFileStore).getResource();

        //When
        final String displayName = diagramFileStore.getDisplayName();

        //Then
        assertThat(displayName).isEqualTo("My Diagram (1.0)");
    }

    private Pool aPoolInAResourceWithUUID(final String uuid) throws IOException {
        final XMIResourceImpl xmiResourceImpl = new XMIResourceImpl();
        final Pool pool = aPool().build();
        xmiResourceImpl.getContents().add(pool);
        xmiResourceImpl.setID(pool, uuid);
        return pool;
    }


}
