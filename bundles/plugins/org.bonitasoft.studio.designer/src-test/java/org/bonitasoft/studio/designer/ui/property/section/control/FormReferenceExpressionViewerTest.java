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
package org.bonitasoft.studio.designer.ui.property.section.control;

import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.FormMappingBuilder.aFormMapping;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.model.expression.assertions.ExpressionAssert;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ToolBar;
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
    private FormReferenceExpressionValidator formReferenceExpressionValidator;
    @Mock
    private WebPageRepositoryStore webPageRepositoryStore;
    @Mock
    private WebPageFileStore selectedPage;
    @Mock
    private IWorkspace workspace;
    @Mock
    private CreateOrEditFormProposalListener createOrEditNewFormProposalListener;
    @Mock
    private TabbedPropertySheetWidgetFactory widgetFactory;
    @Mock
    ToolBar toolBar;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        when(repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class)).thenReturn(webPageRepositoryStore);
        when(repositoryAccessor.getWorkspace()).thenReturn(workspace);
        InternalMappingComposite composite = makeComposite();
        when(widgetFactory.createComposite(any())).thenReturn(composite);
    }

    @Test
    public void should_call_createOrEditListener_when_editing_an_existing_form() throws Exception {
        final FormReferenceExpressionViewer formReferenceExpressionViewer = new FormReferenceExpressionViewer(
                makeComposite(), SWT.BORDER, widgetFactory,
                webPageRepositoryStore, createOrEditNewFormProposalListener);

        final FormMapping mapping = aFormMapping().havingTargetForm(anExpression().withContent("a-page-id")).build();
        formReferenceExpressionViewer.setInput(mapping);
        when(createOrEditNewFormProposalListener.handleEvent(mapping, null, null)).thenReturn(null);
        when(webPageRepositoryStore.getChild("a-page-id", true)).thenReturn(selectedPage);
        formReferenceExpressionViewer.editControlSelected(toolBar, null, editingDomain());

        Mockito.verify(createOrEditNewFormProposalListener).handleEvent(mapping, null, null);
        Mockito.verify(webPageRepositoryStore, Mockito.never()).refresh();
        ExpressionAssert.assertThat(mapping.getTargetForm()).hasContent("a-page-id");
    }

    @Test
    public void should_call_createOrEditListener_and_update_expression_content_when_creating_a_new_form() throws Exception {
        final FormReferenceExpressionViewer formReferenceExpressionViewer = new FormReferenceExpressionViewer(
                makeComposite(), SWT.BORDER,
                widgetFactory,
                webPageRepositoryStore, createOrEditNewFormProposalListener);
        final FormMapping mapping = aFormMapping().havingTargetForm(anExpression()).build();
        formReferenceExpressionViewer.setInput(mapping);

        when(createOrEditNewFormProposalListener.handleEvent(mapping, null, null)).thenReturn("a-new-page-id");
        when(webPageRepositoryStore.getChild("a-new-page-id", true)).thenReturn(selectedPage);
        formReferenceExpressionViewer.editControlSelected(toolBar, null, editingDomain());

        ExpressionAssert.assertThat(mapping.getTargetForm()).hasContent("a-new-page-id");
    }

    private TransactionalEditingDomain editingDomain() {
        return new TransactionalEditingDomainImpl(new ProcessItemProviderAdapterFactory());
    }

    private InternalMappingComposite makeComposite() {
        return new InternalMappingComposite(realmWithDisplay.createComposite(),
                new TabbedPropertySheetWidgetFactory(), repositoryAccessor,
                formReferenceExpressionValidator, createOrEditNewFormProposalListener);
    }
}
