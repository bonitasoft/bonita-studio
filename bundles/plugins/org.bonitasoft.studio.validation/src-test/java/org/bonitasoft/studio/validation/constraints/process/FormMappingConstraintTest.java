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
package org.bonitasoft.studio.validation.constraints.process;

import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.FormMappingBuilder.aFormMapping;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.model.process.FormMappingType;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class FormMappingConstraintTest {

    @Spy
    private FormMappingConstraint formMappingConstraint;
    @Mock
    private IValidationContext ctx;
    @Mock
    private RepositoryAccessor repositoryAccessor;
    @Mock
    private WebPageRepositoryStore webPageRepositoryStore;
    @Mock
    private WebPageFileStore fileStore;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        doReturn(repositoryAccessor).when(formMappingConstraint).getRepositoryAccessor();
        doReturn(webPageRepositoryStore).when(repositoryAccessor).getRepositoryStore(WebPageRepositoryStore.class);
        doReturn(ValidationStatus.ok()).when(ctx).createSuccessStatus();
        doReturn(ValidationStatus.error("error")).when(ctx).createFailureStatus(any(Object.class));
    }

    @Test
    public void should_return_an_error_if_form_doesnt_exists_in_repository() throws Exception {
        //Given
        doReturn(
                aTask().withName("t1").havingFormMapping(aFormMapping().havingTargetForm(anExpression().withContent("an_id_that_doesnt_exists")))
                        .build().getFormMapping()).when(ctx).getTarget();

        //When
        final IStatus status = formMappingConstraint.performBatchValidation(ctx);

        //Then
        assertThat(status).isNotOK();
    }

    @Test
    public void should_return_a_valid_status_if_form_exists_in_repository() throws Exception {
        //Given
        doReturn(aFormMapping().havingTargetForm(anExpression().withContent("an_id_that_exists")).build()).when(ctx).getTarget();
        doReturn(fileStore).when(webPageRepositoryStore).getChild("an_id_that_exists", true);

        //When
        final IStatus status = formMappingConstraint.performBatchValidation(ctx);

        //Then
        assertThat(status).isOK();
    }

    @Test
    public void should_return_a_valid_status_if_no_target_form_on_mapping() throws Exception {
        //Given
        doReturn(aFormMapping().havingTargetForm(anExpression()).build()).when(ctx).getTarget();

        //When
        final IStatus status = formMappingConstraint.performBatchValidation(ctx);

        //Then
        assertThat(status).isOK();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_an_IllegalArgumentException_if_target_is_not_a_FormMapping() throws Exception {
        //Given
        doReturn(anExpression().build()).when(ctx).getTarget();

        //When
        formMappingConstraint.performBatchValidation(ctx);
    }


    @Test
    public void should_return_an_error_status_if_a_form_mapping_has_a_targetForm_name_in_several_places_in_a_process_but_with_different_id() throws Exception {
        //Given
        final Pool pool = aPool()
                .havingFormMapping(aFormMapping().withType(FormMappingType.INTERNAL).havingTargetForm(anExpression().withName("newForm").withContent("id1")))
                .havingElements(
                        aTask().havingFormMapping(
                                aFormMapping().withType(FormMappingType.INTERNAL)
                                        .havingTargetForm(anExpression().withName("newForm").withContent("id2"))))
                .build();
        doReturn(fileStore).when(webPageRepositoryStore).getChild("id1", true);
        doReturn(pool.getFormMapping()).when(ctx).getTarget();

        //When
        final IStatus status = formMappingConstraint.performBatchValidation(ctx);

        //Then
        assertThat(status).isNotOK();
    }

    @Test
    public void should_return_a_valid_status_if_a_form_mapping_has_a_targetForm_name_in_several_places_in_a_process_with_same_id() throws Exception {
        //Given
        final Pool pool = aPool().withName("MyProcess")
                .havingFormMapping(aFormMapping().withType(FormMappingType.INTERNAL).havingTargetForm(anExpression().withName("newForm").withContent("id1")))
                .havingElements(
                        aTask().withName("Task1").havingFormMapping(
                                aFormMapping().withType(FormMappingType.INTERNAL).havingTargetForm(anExpression().withName("newForm").withContent("id1"))))
                .build();
        doReturn(fileStore).when(webPageRepositoryStore).getChild("id1", true);
        doReturn(pool.getFormMapping()).when(ctx).getTarget();

        //When
        final IStatus status = formMappingConstraint.performBatchValidation(ctx);

        //Then
        assertThat(status).isOK();
    }

}
