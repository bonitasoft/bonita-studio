/**
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.explorer.filters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.core.resources.IFile;
import org.junit.Before;
import org.junit.Test;

public class FilesFilterTest {

    FilesFilter filter;

    @Before
    public void init() {
        filter = new FilesFilter();
    }

    @Test
    public void should_filter_project_file() {
        IFile file = mock(IFile.class);
        when(file.getName()).thenReturn(FilesFilter.PROJECT_FILE);

        assertThat(filter.select(null, null, file)).isFalse();
    }

    @Test
    public void should_filter_properties_file() {
        IFile file = mock(IFile.class);
        when(file.getName()).thenReturn(FilesFilter.BUILD_PROPERTIES_FILE);

        assertThat(filter.select(null, null, file)).isFalse();
    }

    @Test
    public void should_filter_classpath_file() {
        IFile file = mock(IFile.class);
        when(file.getName()).thenReturn(FilesFilter.CLASSPATH_FILE);

        assertThat(filter.select(null, null, file)).isFalse();
    }

    @Test
    public void should_accept_valid_file() {
        IFile file = mock(IFile.class);
        when(file.getName()).thenReturn("bom.xml");

        assertThat(filter.select(null, null, file)).isTrue();
    }

}
