/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.gmf.tools.tree.selection.provider.process;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.model.process.AssociatedFile;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.junit.Test;

public class AssociatedFileTabbedPropertyProviderTest {

    @Test
    public void should_return_application_viewId() throws Exception {
        final AssociatedFileTabbedPropertyProvider provider = new AssociatedFileTabbedPropertyProvider();

        assertThat(provider.viewId()).isEqualTo("org.bonitasoft.studio.views.properties.application");
    }

    @Test
    public void should_return_looknfeel_tab_id_if_file_is_an_html_file() throws Exception {
        final AssociatedFileTabbedPropertyProvider provider = new AssociatedFileTabbedPropertyProvider();
        final AssociatedFile associatedFile = ProcessFactory.eINSTANCE.createAssociatedFile();
        associatedFile.setPath("index.html");

        assertThat(provider.tabId(associatedFile)).isEqualTo("tab.lookandfeel");
    }

    @Test
    public void should_return_resource_tab_id_if_file_is_not_an_html_file() throws Exception {
        final AssociatedFileTabbedPropertyProvider provider = new AssociatedFileTabbedPropertyProvider();
        final AssociatedFile associatedFile = ProcessFactory.eINSTANCE.createAssociatedFile();
        associatedFile.setPath("image.jpg");

        assertThat(provider.tabId(associatedFile)).isEqualTo("tab.resource");
    }

    @Test
    public void should_appliesTo_AssociatedFile() throws Exception {
        final AssociatedFileTabbedPropertyProvider provider = new AssociatedFileTabbedPropertyProvider();

        assertThat(provider.appliesTo(ProcessFactory.eINSTANCE.createAssociatedFile(), null)).isTrue();
        assertThat(provider.appliesTo(null, null)).isFalse();
    }
}
