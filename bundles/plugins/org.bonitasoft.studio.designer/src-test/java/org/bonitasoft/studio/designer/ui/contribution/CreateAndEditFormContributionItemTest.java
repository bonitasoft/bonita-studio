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
package org.bonitasoft.studio.designer.ui.contribution;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.ContractBuilder.aContract;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;
import static org.bonitasoft.studio.model.process.builders.FormMappingBuilder.aFormMapping;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.expression.CreateNewFormProposalListener;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.model.process.FormMappingType;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateAndEditFormContributionItemTest {

    @InjectMocks
    @Spy
    private CreateAndEditFormContributionItem contribution;

    @Mock
    private CreateNewFormProposalListener listener;

    @Mock
    private RepositoryAccessor repositoryAccessor;

    @Mock
    private ISelectionProvider selectionProvider;

    @Mock
    private WebPageRepositoryStore repositoryStore;

    @Mock
    private CreateNewFormProposalListener createNewFormListener;

    @Mock
    private WebPageFileStore webPageFileStore;

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        contribution.setSelectionProvider(selectionProvider);
    }

    @Test
    public void should_fillToolbar_with_a_toolItem() throws Exception {
        final Composite composite = realmWithDisplay.createComposite();
        final ToolBar toolbar = new ToolBar(composite, SWT.NONE);
        contribution.fill(toolbar, SWT.NONE);

        assertThat(toolbar.getItemCount()).isEqualTo(1);
    }

    @Test
    public void isEditable_should_return_false_if_selection_is_not_aPageFlow() {
        doReturn(new StructuredSelection()).when(selectionProvider)
                .getSelection();
        assertThat(contribution.isEditable()).isFalse();
    }

    @Test
    public void isEditable_should_return_true_if_selection_is_aPageFlow_withAFormMapping_withAForm_havingName() {
        doReturn(
                new StructuredSelection(aTask().havingFormMapping(
                        aFormMapping().havingTargetForm(anExpression().withName("newForm")).withType(FormMappingType.INTERNAL)).build())).when(
                selectionProvider)
                .getSelection();

        assertThat(contribution.isEditable()).isTrue();
    }

    @Test
    public void isInternalForm_should_return_True_if_FormMappingType_isDefinedTo_INTERNAL() {
        doReturn(
                new StructuredSelection(aTask().havingFormMapping(
                        aFormMapping().havingTargetForm(anExpression().withName("newForm")).withType(FormMappingType.INTERNAL)).build())).when(
                selectionProvider)
                .getSelection();

        assertThat(contribution.isInternalForm()).isTrue();
    }

    @Test
    public void isInternalForm_should_return_False_if_FormMappingType_isDefinedTo_URL() {
        doReturn(
                new StructuredSelection(aTask().havingFormMapping(
                        aFormMapping().havingTargetForm(anExpression()).withType(FormMappingType.URL)).build())).when(
                selectionProvider)
                .getSelection();

        assertThat(contribution.isInternalForm()).isFalse();
    }

    @Test
    public void isEditableShouldReturnTrue_after_calling_createForm() {
        doReturn(repositoryStore).when(repositoryAccessor).getRepositoryStore(WebPageRepositoryStore.class);
        final PageFlow pagefLow = aTask().havingFormMapping(
                aFormMapping().havingTargetForm(anExpression())).havingContract(aContract()).build();
        doReturn("newForm").when(createNewFormListener).handleEvent(pagefLow.getFormMapping(), null, null);
        doReturn(
                new StructuredSelection(pagefLow)).when(
                selectionProvider)
                .getSelection();

        doReturn(webPageFileStore).when(repositoryStore).getChild("newForm", true);
        doReturn("newName").when(webPageFileStore).getCustomPageName();
        doReturn(editingDomain()).when(contribution).getEditingDomain(pagefLow);
        doReturn(true).when(contribution).openHideEmptyContractDialog();
        contribution.createNewForm();

        assertThat(contribution.isInternalForm()).isTrue();

        assertThat(contribution.isEditable()).isTrue();
    }

    @Test
    public void shouldNotAsk_ToOpenUIDesigner_WhenContractIsNotEmpty() {
        doReturn(repositoryStore).when(repositoryAccessor).getRepositoryStore(WebPageRepositoryStore.class);
        final PageFlow pagefLow = aTask().havingFormMapping(
                aFormMapping().havingTargetForm(anExpression())).havingContract(aContract().havingInput(aContractInput())).build();
        doReturn("newForm").when(createNewFormListener).handleEvent(pagefLow.getFormMapping(), null, null);
        doReturn(
                new StructuredSelection(pagefLow)).when(
                selectionProvider)
                .getSelection();

        doReturn(webPageFileStore).when(repositoryStore).getChild("newForm", true);
        doReturn("newName").when(webPageFileStore).getDisplayName();
        doReturn(editingDomain()).when(contribution).getEditingDomain(pagefLow);
        contribution.createNewForm();
        verify(contribution, Mockito.never()).openHideEmptyContractDialog();
    }

    private EditingDomain editingDomain() {
        return new TransactionalEditingDomainImpl(new ProcessItemProviderAdapterFactory());
    }

    @Test
    public void should_return_FormMapping_typeName() {
        doReturn(
                new StructuredSelection(aTask().havingFormMapping(
                        aFormMapping().havingTargetForm(anExpression()).withType(FormMappingType.URL)).build())).when(
                selectionProvider)
                .getSelection();

        assertThat(contribution.getFormMappingTypeName()).isEqualTo(Messages.externalURL);
    }

}
