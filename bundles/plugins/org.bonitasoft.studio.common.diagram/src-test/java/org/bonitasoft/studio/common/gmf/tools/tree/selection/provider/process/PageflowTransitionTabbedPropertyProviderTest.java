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
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.PageflowTransitionBuilder.aPageflowTransition;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;

import org.junit.Test;

public class PageflowTransitionTabbedPropertyProviderTest {

    @Test
    public void should_return_application_viewId() throws Exception {
        final PageflowTransitionTabbedPropertyProvider provider = new PageflowTransitionTabbedPropertyProvider();

        assertThat(provider.viewId()).isEqualTo("org.bonitasoft.studio.views.properties.application");
    }

    @Test
    public void should_return_overview_form_tab_id_for_overview_pageflow_transition() throws Exception {
        final PageflowTransitionTabbedPropertyProvider provider = new PageflowTransitionTabbedPropertyProvider();

        assertThat(provider.tabId(aPageflowTransition().inOverview(aPool()).build())).isEqualTo("tab.forms.overview");
    }

    @Test
    public void should_return_overview_form_tab_id_for_entry_pageflow_transition() throws Exception {
        final PageflowTransitionTabbedPropertyProvider provider = new PageflowTransitionTabbedPropertyProvider();

        assertThat(provider.tabId(aPageflowTransition().in(aPool()).build())).isEqualTo("tab.forms.entry");
    }

    @Test
    public void should_return_tab_index_for_pageflow_transition_section() throws Exception {
        final PageflowTransitionTabbedPropertyProvider provider = new PageflowTransitionTabbedPropertyProvider();

        assertThat(provider.tabIndex()).isEqualTo(0);
    }

    @Test
    public void should_appliesTo_PageflowTransition() throws Exception {
        final PageflowTransitionTabbedPropertyProvider provider = new PageflowTransitionTabbedPropertyProvider();

        assertThat(provider.appliesTo(aPageflowTransition().build(), null)).isTrue();
    }

    @Test
    public void should_appliesTo_PageflowTransition_child() throws Exception {
        final PageflowTransitionTabbedPropertyProvider provider = new PageflowTransitionTabbedPropertyProvider();

        assertThat(provider.appliesTo(aPageflowTransition().havingCondition(anExpression()).build().getCondition(), null)).isTrue();
    }

    @Test
    public void should_not_appliesTo_element_not_in_a_PageflowTransition() throws Exception {
        final PageflowTransitionTabbedPropertyProvider provider = new PageflowTransitionTabbedPropertyProvider();

        assertThat(provider.appliesTo(aPool().build(), null)).isFalse();
    }
}
