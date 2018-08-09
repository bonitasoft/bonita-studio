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

import org.eclipse.core.resources.IFolder;
import org.junit.Before;
import org.junit.Test;

public class FoldersFilterTest {

    FoldersFilter filter;

    @Before
    public void init() {
        filter = new FoldersFilter();
    }

    @Test
    public void should_filter_setting_folder() {
        IFolder folder = mock(IFolder.class);
        when(folder.getName()).thenReturn(FoldersFilter.SETTINGS_FOLDER);

        assertThat(filter.select(null, null, folder)).isFalse();
    }

    @Test
    public void should_filter_db_connector_folder() {
        IFolder folder = mock(IFolder.class);
        when(folder.getName()).thenReturn(FoldersFilter.DB_CONNECTOR_PROPERTIES_FOLDER);

        assertThat(filter.select(null, null, folder)).isFalse();
    }

    @Test
    public void should_filter_h2_folder() {
        IFolder folder = mock(IFolder.class);
        when(folder.getName()).thenReturn(FoldersFilter.H2_FOLDER);

        assertThat(filter.select(null, null, folder)).isFalse();
    }

    @Test
    public void should_filter_meta_inf_folder() {
        IFolder folder = mock(IFolder.class);
        when(folder.getName()).thenReturn(FoldersFilter.METAINF_FOLDER);

        assertThat(filter.select(null, null, folder)).isFalse();
    }

    @Test
    public void should_filter_process_conf_folder() {
        IFolder folder = mock(IFolder.class);
        when(folder.getName()).thenReturn(FoldersFilter.PROCESS_CONF_FOLDER);

        assertThat(filter.select(null, null, folder)).isFalse();
    }

    @Test
    public void should_filter_target_folder() {
        IFolder folder = mock(IFolder.class);
        when(folder.getName()).thenReturn(FoldersFilter.TARGET_FOLDER);

        assertThat(filter.select(null, null, folder)).isFalse();
    }

    @Test
    public void should_filter_template_folder() {
        IFolder folder = mock(IFolder.class);
        when(folder.getName()).thenReturn(FoldersFilter.TEMPLATE_FOLDER);

        assertThat(filter.select(null, null, folder)).isFalse();
    }

    @Test
    public void should_filter_xsd_folder() {
        IFolder folder = mock(IFolder.class);
        when(folder.getName()).thenReturn(FoldersFilter.XSD_FOLDER);

        assertThat(filter.select(null, null, folder)).isFalse();
    }

    @Test
    public void should_accept_valid_folder() {
        IFolder folder = mock(IFolder.class);
        when(folder.getName()).thenReturn("bdm");

        assertThat(filter.select(null, null, folder)).isTrue();
    }

}
