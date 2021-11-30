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
package org.bonitasoft.studio.diagram.custom.part;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.bonitasoft.studio.model.process.builders.SendTaskBuilder.aSendTask;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.junit.Test;

public class BonitaDataPropertiesBrowserPageTest {

    @Test
    public void should_select_first_tab_if_selection_is_not_an_activity() throws Exception {
        final BonitaDataPropertiesBrowserPage browserPage = mock(BonitaDataPropertiesBrowserPage.class);
        when(browserPage.defaultSelectedTabIndex(any(ISelection.class))).thenCallRealMethod();

        final int tabIndex = browserPage.defaultSelectedTabIndex(new StructuredSelection(new EObjectAdapter(aPool().build())));

        assertThat(tabIndex).isEqualTo(0);
    }

    @Test
    public void should_select_second_tab_if_selection_is_an_activity_but_not_a_sendTask() throws Exception {
        final BonitaDataPropertiesBrowserPage browserPage = mock(BonitaDataPropertiesBrowserPage.class);
        when(browserPage.defaultSelectedTabIndex(any(ISelection.class))).thenCallRealMethod();

        final int tabIndex = browserPage.defaultSelectedTabIndex(new StructuredSelection(new EObjectAdapter(aTask().build())));

        assertThat(tabIndex).isEqualTo(1);
    }

    @Test
    public void should_select_first_tab_if_selection_is_a_sendTask() throws Exception {
        final BonitaDataPropertiesBrowserPage browserPage = mock(BonitaDataPropertiesBrowserPage.class);
        when(browserPage.defaultSelectedTabIndex(any(ISelection.class))).thenCallRealMethod();

        final int tabIndex = browserPage.defaultSelectedTabIndex(new StructuredSelection(new EObjectAdapter(aSendTask().build())));

        assertThat(tabIndex).isEqualTo(0);
    }
}
