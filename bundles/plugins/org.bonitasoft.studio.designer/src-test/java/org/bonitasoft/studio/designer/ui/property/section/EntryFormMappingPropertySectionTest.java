/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.ui.property.section;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class EntryFormMappingPropertySectionTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Mock
    private IEclipsePreferences preferenceStore;

    @Mock
    private PageFlowAdaptableSelectionProvider selectionProvider;

    @Mock
    private TabbedPropertySheetPage tabbedPropertySheetPage;

    @InjectMocks
    private EntryFormMappingPropertySection section;

    @Mock
    private RepositoryAccessor repositoryAccessor;

    @Mock
    private IWorkbenchPart part;

    @Mock
    private WebPageRepositoryStore webPageStore;

    @Mock
    private IWorkspace workspace;

    @Test
    public void should_create_a_FormMappingGroup() throws Exception {
        final Composite parent = realmWithDisplay.createComposite();
        doReturn(new TabbedPropertySheetWidgetFactory()).when(tabbedPropertySheetPage).getWidgetFactory();
        doReturn(webPageStore).when(repositoryAccessor).getRepositoryStore(WebPageRepositoryStore.class);

        section.createContent(parent);

        assertThat(parent.getChildren()).hasSize(1);

        section.dispose();
    }

    @Test
    public void should_have_PageFlow_FormMapping_feature() throws Exception {
        assertThat(section.getFormMappingFeature()).isEqualTo(ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPING);
    }

    @Test
    public void should_setSelection_on_SelectionProvider() throws Exception {
        final StructuredSelection selection = new StructuredSelection(new EObjectAdapter(aPool().build()));
        section.setInput(part, selection);

        verify(selectionProvider).setSelection(selection);
    }
}
