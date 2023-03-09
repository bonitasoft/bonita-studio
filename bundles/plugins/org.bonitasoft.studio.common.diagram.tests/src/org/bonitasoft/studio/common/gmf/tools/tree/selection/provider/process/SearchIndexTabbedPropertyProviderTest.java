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

import org.bonitasoft.studio.model.process.ProcessFactory;
import org.junit.Test;

public class SearchIndexTabbedPropertyProviderTest {

    @Test
    public void should_return_general_viewId() throws Exception {
        final SearchIndexTabbedPropertyProvider provider = new SearchIndexTabbedPropertyProvider();

        assertThat(provider.viewId()).isEqualTo("org.bonitasoft.studio.views.properties.process.general");
    }

    @Test
    public void should_return_search_index_tab_id() throws Exception {
        final SearchIndexTabbedPropertyProvider provider = new SearchIndexTabbedPropertyProvider();

        assertThat(provider.tabId(null)).isEqualTo("tab.index");
    }

    @Test
    public void should_appliesTo_SearchIndex() throws Exception {
        final SearchIndexTabbedPropertyProvider provider = new SearchIndexTabbedPropertyProvider();

        assertThat(provider.appliesTo(ProcessFactory.eINSTANCE.createSearchIndex(), null)).isTrue();
    }

    @Test
    public void should_not_appliesTo_other_element_type() throws Exception {
        final SearchIndexTabbedPropertyProvider provider = new SearchIndexTabbedPropertyProvider();

        assertThat(provider.appliesTo(null, null)).isFalse();
    }
}
