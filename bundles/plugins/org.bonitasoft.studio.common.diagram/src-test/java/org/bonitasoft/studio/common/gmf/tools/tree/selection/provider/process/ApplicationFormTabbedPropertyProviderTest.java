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
import static org.bonitasoft.studio.model.form.builders.FileWidgetBuilder.aFileWidget;
import static org.bonitasoft.studio.model.form.builders.FormBuilder.aForm;
import static org.bonitasoft.studio.model.form.builders.ViewFormBuilder.aViewForm;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.Mockito.when;

import org.eclipse.ui.IEditorReference;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationFormTabbedPropertyProviderTest {

    @Mock
    private IEditorReference processEditorRef;

    @Before
    public void setUp() throws Exception {
        when(processEditorRef.getId()).thenReturn("org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditorID");
    }

    @Test
    public void should_return_application_viewId() throws Exception {
        final ApplicationFormTabbedPropertyProvider provider = new ApplicationFormTabbedPropertyProvider();

        assertThat(provider.viewId()).isEqualTo("org.bonitasoft.studio.views.properties.application");
    }

    @Test
    public void should_return_overview_form_tab_id_for_view_forms() throws Exception {
        final ApplicationFormTabbedPropertyProvider provider = new ApplicationFormTabbedPropertyProvider();

        assertThat(provider.tabId(aViewForm().build())).isEqualTo("tab.forms.overview");
    }

    @Test
    public void should_return_entry_form_tab_id() throws Exception {
        final ApplicationFormTabbedPropertyProvider provider = new ApplicationFormTabbedPropertyProvider();

        assertThat(provider.tabId(null)).isEqualTo("tab.forms.entry");
    }

    @Test
    public void should_return_tab_index_for_form_section() throws Exception {
        final ApplicationFormTabbedPropertyProvider provider = new ApplicationFormTabbedPropertyProvider();

        assertThat(provider.tabIndex()).isEqualTo(0);
    }

    @Test
    public void should_appliesTo_Form() throws Exception {
        final ApplicationFormTabbedPropertyProvider provider = new ApplicationFormTabbedPropertyProvider();

        assertThat(provider.appliesTo(aForm().build(), processEditorRef)).isTrue();
    }

    @Test
    public void should_appliesTo_form_child() throws Exception {
        final ApplicationFormTabbedPropertyProvider provider = new ApplicationFormTabbedPropertyProvider();

        assertThat(provider.appliesTo(aForm().havingWidget(aFileWidget()).in(aTask()).build().getWidgets().get(0), processEditorRef)).isTrue();
    }

    @Test
    public void should_not_appliesTo_to_element_not_contained_in_a_form() throws Exception {
        final ApplicationFormTabbedPropertyProvider provider = new ApplicationFormTabbedPropertyProvider();

        assertThat(provider.appliesTo(aPool().build(), processEditorRef)).isFalse();
    }
}
