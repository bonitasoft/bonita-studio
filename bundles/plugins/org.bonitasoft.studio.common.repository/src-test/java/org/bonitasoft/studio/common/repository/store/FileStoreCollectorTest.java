/**
 * Copyright (C) 2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.store;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import org.eclipse.core.resources.IResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class FileStoreCollectorTest {

    @Mock
    private IResource root;
    @Mock
    private IResource procResource;
    @Mock
    private IResource hiddenResource;
    @Mock
    private IResource dotStartingResource;

    @Test
    public void should_collect_resources_with_defined_extension() throws Exception {
        //Given
        final FileStoreCollector fileStoreCollector = new FileStoreCollector(root, "proc");
        doReturn("proc").when(procResource).getFileExtension();

        //When
        final boolean visited = fileStoreCollector.visit(procResource);

        //Then
        assertThat(visited).isTrue();
        assertThat(fileStoreCollector.toList()).contains(procResource);
    }

    @Test
    public void should_collect_all_visible_resources_if_not_extension_is_defined() throws Exception {
        //Given
        final FileStoreCollector fileStoreCollector = new FileStoreCollector(root, null);
        doReturn("proc").when(procResource).getFileExtension();
        doReturn("myDiagram").when(procResource).getName();
        when(hiddenResource.isHidden()).thenReturn(true);
        when(dotStartingResource.getName()).thenReturn(".svn");

        //When
        fileStoreCollector.visit(procResource);
        fileStoreCollector.visit(hiddenResource);
        fileStoreCollector.visit(dotStartingResource);

        //Then
        assertThat(fileStoreCollector.toList()).contains(procResource).doesNotContain(hiddenResource, dotStartingResource);
    }

    @Test
    public void should_skip_hidden_resources() throws Exception {
        //Given
        final FileStoreCollector fileStoreCollector = new FileStoreCollector(root, null);
        when(hiddenResource.isHidden()).thenReturn(true);
        when(dotStartingResource.getName()).thenReturn(".svn");

        //When
        final boolean visitHiddenResource = fileStoreCollector.visit(hiddenResource);
        fileStoreCollector.visit(dotStartingResource);

        //Then
        assertThat(visitHiddenResource).isFalse();
    }

    @Test
    public void should_skip_dot_starting_resources() throws Exception {
        //Given
        final FileStoreCollector fileStoreCollector = new FileStoreCollector(root, null);
        when(dotStartingResource.getName()).thenReturn(".svn");

        //When
        final boolean visitHiddenResource = fileStoreCollector.visit(dotStartingResource);

        //Then
        assertThat(visitHiddenResource).isFalse();
    }

    @Test
    public void should_not_collect_unrelevant_extension() throws Exception {
        //Given
        final FileStoreCollector fileStoreCollector = new FileStoreCollector(root, "png");
        doReturn("proc").when(procResource).getFileExtension();

        //When
        fileStoreCollector.visit(procResource);

        //Then
        assertThat(fileStoreCollector.toList()).doesNotContain(procResource);
    }

    @Test
    public void should_visit_root_folder() throws Exception {
        //Given
        final FileStoreCollector fileStoreCollector = new FileStoreCollector(root);

        //When
        final boolean visited = fileStoreCollector.visit(root);

        //Then
        assertThat(visited).isTrue();
    }
}
