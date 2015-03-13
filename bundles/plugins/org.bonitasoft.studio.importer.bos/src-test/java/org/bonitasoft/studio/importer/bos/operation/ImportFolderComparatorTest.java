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
package org.bonitasoft.studio.importer.bos.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.eclipse.core.resources.IResource;
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
public class ImportFolderComparatorTest {

    private ImportFolderComparator comparator;

    @Mock
    private IResource diagramResource;

    @Mock
    private IResource otherResource;

    @Mock
    private IResource libResource;

    @Mock
    private IResource srcResource;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        comparator = new ImportFolderComparator();
        when(diagramResource.getName()).thenReturn("diagrams");
        when(otherResource.getName()).thenReturn("other");
        when(libResource.getName()).thenReturn("lib");
        when(srcResource.getName()).thenReturn("src-filter");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shoud_diagram_folder_be_in_first_position() throws Exception {
        assertThat(comparator.compare(diagramResource, otherResource)).isEqualTo(-1);
        assertThat(comparator.compare(otherResource, diagramResource)).isEqualTo(1);
        assertThat(comparator.compare(diagramResource, libResource)).isEqualTo(-1);
        assertThat(comparator.compare(libResource, diagramResource)).isEqualTo(1);
        assertThat(comparator.compare(diagramResource, srcResource)).isEqualTo(-1);
        assertThat(comparator.compare(srcResource, diagramResource)).isEqualTo(1);
    }

    @Test
    public void shoud_lib_folder_be_in_second_position() throws Exception {
        assertThat(comparator.compare(libResource, otherResource)).isEqualTo(-1);
        assertThat(comparator.compare(otherResource, libResource)).isEqualTo(1);

        assertThat(comparator.compare(diagramResource, libResource)).isEqualTo(-1);
        assertThat(comparator.compare(libResource, diagramResource)).isEqualTo(1);

        assertThat(comparator.compare(libResource, srcResource)).isEqualTo(-1);
        assertThat(comparator.compare(srcResource, libResource)).isEqualTo(1);
    }

    @Test
    public void shoud_folder_containg_src_be_in_third_position() throws Exception {
        assertThat(comparator.compare(srcResource, otherResource)).isEqualTo(-1);
        assertThat(comparator.compare(otherResource, srcResource)).isEqualTo(1);

        assertThat(comparator.compare(diagramResource, srcResource)).isEqualTo(-1);
        assertThat(comparator.compare(srcResource, diagramResource)).isEqualTo(1);

        assertThat(comparator.compare(libResource, srcResource)).isEqualTo(-1);
        assertThat(comparator.compare(srcResource, libResource)).isEqualTo(1);
    }
}
