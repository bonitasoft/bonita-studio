/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.pagedesigner.core.expression;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.FormMappingBuilder.aFormMapping;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.pagedesigner.core.PageDesignerURLFactory;
import org.bonitasoft.studio.pagedesigner.core.operation.CreateFormOperation;
import org.bonitasoft.studio.pagedesigner.core.repository.WebPageFileStore;
import org.bonitasoft.studio.pagedesigner.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
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
    private CreateFormOperation createFormOperation;

    @Mock
    private WebPageRepositoryStore formRepository;

    @Mock
    private WebPageFileStore formFileStore;

    @Mock
    private PageDesignerURLFactory pageDesignerURLFactory;

    private CreateNewFormProposalListener createNewFormProposal;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        doReturn(formRepository).when(repositoryAccessor).getRepositoryStore(WebPageRepositoryStore.class);
        doReturn(formFileStore).when(formRepository).getChild("page-id.json");

        createNewFormProposal = spy(new CreateNewFormProposalListener(pageDesignerURLFactory, progressService, repositoryAccessor));
        doReturn(createFormOperation).when(createNewFormProposal).doCreateFormOperation(any(PageDesignerURLFactory.class));
        when(createFormOperation.getNewPageId()).thenReturn("page-id");
        when(preferenceStore.get(CONSOLE_HOST, DEFAULT_HOST)).thenReturn(DEFAULT_HOST);
        when(preferenceStore.getInt(CONSOLE_PORT, DEFAULT_PORT)).thenReturn(DEFAULT_PORT);
    }

    @Test
    public void should_handleEvent_returns_new_pageid_and_open_page_designer_with_new_id() throws Exception {
        //When
        final String pageId = createNewFormProposal.handleEvent(null, null);

        //Then
        assertThat(pageId).isEqualTo("page-id");
        verify(progressService).busyCursorWhile(any(CreateFormOperation.class));
        verify(formFileStore).open();
    }

    @Test
    public void should_set_form_ame_on_CreateFormOperation() throws Exception {
        //Given
        final Task task = aTask().withName("Step1").havingFormMapping(aFormMapping()).build();

        //When
        createNewFormProposal.handleEvent(task.getFormMapping(), null);

        //Then
        verify(createFormOperation).setFormName("Step1");
    }

    @Test
    public void should_prefix_form_name_for_overview_form() throws Exception {
        //Given
        final Pool pool = aPool().withName("Pool1").havingOverviewFormMapping(aFormMapping()).build();

        //When
        createNewFormProposal.handleEvent(pool.getOverviewFormMapping(), null);

        //Then
        verify(createFormOperation).setFormName("Pool1Overview");
    }

    @Test
    public void should_rewrite_form_name_for_names_with_illegal_characters() throws Exception {
        //Given
        final Task task = aTask().withName("Step1 & Stép2").havingFormMapping(aFormMapping()).build();

        //When
        createNewFormProposal.handleEvent(task.getFormMapping(), null);

        //Then
        verify(createFormOperation).setFormName("Step1___Step2");
    }

}
