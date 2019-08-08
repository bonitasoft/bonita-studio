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
package org.bonitasoft.studio.designer.core.expression;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.ContractBuilder.aContract;
import static org.bonitasoft.studio.model.process.builders.FormMappingBuilder.aFormMapping;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.FormScope;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.operation.CreateUIDArtifactOperation;
import org.bonitasoft.studio.designer.core.operation.NewFormOperationFactoryDelegate;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.progress.IProgressService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateNewFormProposalListenerTest implements BonitaPreferenceConstants {

    @Mock
    private IEclipsePreferences preferenceStore;

    @Mock
    private IProgressService progressService;

    @Mock
    private RepositoryAccessor repositoryAccessor;

    @Mock
    private CreateUIDArtifactOperation createFormOperation;

    @Mock
    private WebPageRepositoryStore formRepository;

    private WebPageFileStore formFileStore;

    @Mock
    private PageDesignerURLFactory pageDesignerURLFactory;

    private CreateNewFormProposalListener createNewFormProposal;
    @Mock
    private NewFormOperationFactoryDelegate operationFactory;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        doReturn(formRepository).when(repositoryAccessor).getRepositoryStore(WebPageRepositoryStore.class);
        formFileStore = mock(WebPageFileStore.class);
        when(formFileStore.getUUID()).thenReturn("page-id");
        doReturn(formFileStore).when(formRepository).getChild("page-id");

        createNewFormProposal = spy(
                new CreateNewFormProposalListener(pageDesignerURLFactory, progressService, repositoryAccessor,
                        operationFactory));

        when(createFormOperation.getNewArtifactId()).thenReturn("page-id");
        when(preferenceStore.get(CONSOLE_HOST, DEFAULT_HOST)).thenReturn(DEFAULT_HOST);
        when(preferenceStore.getInt(CONSOLE_PORT, DEFAULT_PORT)).thenReturn(DEFAULT_PORT);
        doReturn(createFormOperation).when(createNewFormProposal).doCreateFormOperation(eq(pageDesignerURLFactory),
                any(Contract.class), any(FormScope.class));
    }

    @Test
    public void should_handleEvent_returns_new_pageid_and_open_page_designer_with_new_id() throws Exception {
        final EObject context = aContract().in(aTask().withName("step1")).build();

        final String pageId = createNewFormProposal.handleEvent(context, null);

        assertThat(pageId).isEqualTo("page-id");
        verify(formFileStore).open();
    }

    @Test
    public void should_force_page_name_to_newForm() throws Exception {
        //Given
        final Task task = aTask().withName("Step1").havingFormMapping(aFormMapping()).havingContract(aContract())
                .build();

        //When
        createNewFormProposal.handleEvent(task.getFormMapping(), null);

        //Then
        verify(createNewFormProposal).doCreateFormOperation(eq(pageDesignerURLFactory),
                any(Contract.class),
                any(FormScope.class));
    }

    @Test
    public void should_be_relevant_for_form_reference_selection() throws Exception {
        assertThat(createNewFormProposal.isRelevant(null, new StructuredSelection(
                ExpressionBuilder.anExpression().withExpressionType(ExpressionConstants.FORM_REFERENCE_TYPE).build())))
                        .isTrue();
    }

    @Test
    public void should_not_be_relevant() throws Exception {
        assertThat(createNewFormProposal.isRelevant(null, new StructuredSelection(
                ExpressionBuilder.anExpression().withExpressionType(ExpressionConstants.CONSTANT_TYPE).build())))
                        .isFalse();
    }

}
