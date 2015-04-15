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
package org.bonitasoft.studio.pagedesigner.ui.property.section.control;

import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.FormMappingBuilder.aFormMapping;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.pagedesigner.core.repository.WebPageFileStore;
import org.bonitasoft.studio.pagedesigner.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class PageDesignerMappingCompositeTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();
    @Mock
    private RepositoryAccessor repositoryAccessor;
    @Mock
    private IEclipsePreferences preferenceStore;
    @Mock
    private FormReferenceExpressionValidator formReferenceExpressionValidator;
    @Mock
    private WebPageRepositoryStore webPageRepositoryStore;
    @Mock
    private WebPageFileStore selectedPage;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        when(repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class)).thenReturn(webPageRepositoryStore);
    }

    @Test
    public void should_open_file_store_on_edit() throws Exception {
        final PageDesignerMappingComposite pageDesignerMappingComposite = makeComposite();
        when(webPageRepositoryStore.getChild("a-page-id")).thenReturn(selectedPage);

        pageDesignerMappingComposite.editForm(new WritableValue(aFormMapping().havingTargetForm(anExpression().withContent("a-page-id")).build(),
                FormMapping.class));

        verify(selectedPage).open();
    }

    private PageDesignerMappingComposite makeComposite() {
        return new PageDesignerMappingComposite(realmWithDisplay.createComposite(),
                new TabbedPropertySheetWidgetFactory(), preferenceStore, repositoryAccessor,
                formReferenceExpressionValidator);
    }
}
