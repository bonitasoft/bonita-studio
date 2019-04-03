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

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.ContractBuilder.aContract;
import static org.bonitasoft.studio.model.process.builders.FormMappingBuilder.aFormMapping;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.FormScope;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.operation.CreateUIDArtifactOperation;
import org.bonitasoft.studio.designer.core.operation.NewFormOperationFactoryDelegate;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.builders.TaskBuilder;
import org.eclipse.ui.progress.IProgressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateOrEditFormProposalListenerTest {

    @Mock
    private PageDesignerURLFactory pageDesignerURLFactory;
    @Mock
    private IProgressService progressService;
    @Mock
    private RepositoryAccessor repositoryAccessor;
    @Mock
    private CreateUIDArtifactOperation operation;
    @Mock
    private WebPageRepositoryStore pageStore;
    @Mock
    private WebPageFileStore fileStore;
    @Mock
    private NewFormOperationFactoryDelegate operationFactory;

    @Test
    public void should_execute_create_form_operation_if_new_form() throws Exception {
        final CreateOrEditFormProposalListener listener = spy(new CreateOrEditFormProposalListener(
                pageDesignerURLFactory, progressService, repositoryAccessor, operationFactory));
        when(operation.getNewArtifactId()).thenReturn("page-id");
        when(repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class)).thenReturn(pageStore);
        when(pageStore.getChild("page-id")).thenReturn(fileStore);
        when(fileStore.getUUID()).thenReturn("page-id");

        doReturn(operation).when(operationFactory).newCreateFormFromContractOperation(any(PageDesignerURLFactory.class),
                any(Contract.class),
                any(FormScope.class),
                any(RepositoryAccessor.class));
        
        final String newPageId = listener.handleEvent(
                TaskBuilder.aTask().havingFormMapping(aFormMapping().havingTargetForm(anExpression()))
                        .havingContract(aContract()).build()
                        .getFormMapping(),
                null);

        assertThat(newPageId).isEqualTo("page-id");
        verify(progressService).busyCursorWhile(operation);
        verify(fileStore).open();
    }

    @Test
    public void should_open_filStore_if_form_exists() throws Exception {
        final CreateOrEditFormProposalListener listener = spy(new CreateOrEditFormProposalListener(
                pageDesignerURLFactory, progressService, repositoryAccessor, operationFactory));
        when(repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class)).thenReturn(pageStore);
        when(pageStore.getChild("page-id")).thenReturn(fileStore);

        final String newPageId = listener.handleEvent(
                TaskBuilder.aTask()
                        .havingFormMapping(aFormMapping().havingTargetForm(anExpression().withContent("page-id")))
                        .havingContract(aContract())
                        .build()
                        .getFormMapping(),
                null);

        assertThat(newPageId).isNull();
        verify(fileStore).open();
    }
}
