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
package org.bonitasoft.studio.designer.ui.property.section.control;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.FormMappingBuilder.aFormMapping;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.expression.CreateNewFormProposalListener;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.swt.SWT;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FormReferenceExpressionViewerTest {

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
    @Mock
    private IWorkspace workspace;
    @Mock
    private CreateNewFormProposalListener createNewFormProposalListener;
    @Mock
    private TabbedPropertySheetWidgetFactory widgetFactory;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        when(repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class)).thenReturn(webPageRepositoryStore);
        when(repositoryAccessor.getWorkspace()).thenReturn(workspace);
    }

    @Test
    public void should_open_file_store_on_edit() throws Exception {
        final FormReferenceExpressionViewer formReferenceExpressionViewer = new FormReferenceExpressionViewer(makeComposite(), SWT.BORDER, widgetFactory,
                webPageRepositoryStore);
        when(webPageRepositoryStore.getChild("a-page-id")).thenReturn(selectedPage);

        formReferenceExpressionViewer.createOReditForm(new WritableValue(aFormMapping().havingTargetForm(anExpression().withContent("a-page-id")).build(),
                FormMapping.class), createNewFormProposalListener, repositoryAccessor);

        verify(selectedPage).open();
    }

    @Test
    public void should_open_file_store_on_create() throws Exception {
        final FormReferenceExpressionViewer formReferenceExpressionViewer = Mockito.spy(new FormReferenceExpressionViewer(makeComposite(), SWT.BORDER,
                widgetFactory,
                webPageRepositoryStore));

        when(webPageRepositoryStore.getChild("a-page-id")).thenReturn(selectedPage);
        final FormMapping mapping = aFormMapping().havingTargetForm(anExpression()).build();
        when(createNewFormProposalListener.handleEvent(mapping, null)).thenReturn("a-page-id");
        doReturn(editingDomain()).when(formReferenceExpressionViewer).getEditingDomain(mapping);

        formReferenceExpressionViewer.createOReditForm(new WritableValue(mapping,
                FormMapping.class), createNewFormProposalListener, repositoryAccessor);

        assertThat(mapping.getTargetForm().hasContent()).isTrue();
    }

    private TransactionalEditingDomain editingDomain() {
        return new TransactionalEditingDomainImpl(new ProcessItemProviderAdapterFactory());
    }

    private InternalMappingComposite makeComposite() {
        return new InternalMappingComposite(realmWithDisplay.createComposite(),
                new TabbedPropertySheetWidgetFactory(), preferenceStore, repositoryAccessor,
                formReferenceExpressionValidator, createNewFormProposalListener);
    }
}
