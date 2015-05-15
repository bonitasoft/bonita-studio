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
package org.bonitasoft.studio.designer.core.bar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.bonitasoft.studio.model.configuration.builders.ConfigurationBuilder.aConfiguration;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.FormMappingBuilder.aFormMapping;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Collections;

import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.engine.bpm.bar.form.model.FormMappingDefinition;
import org.bonitasoft.engine.bpm.bar.form.model.FormMappingModel;
import org.bonitasoft.engine.form.FormMappingTarget;
import org.bonitasoft.engine.form.FormMappingType;
import org.bonitasoft.studio.designer.core.bar.CustomPageBarResourceFactory;
import org.bonitasoft.studio.designer.core.bar.FormBarResourceProvider;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class FormBarResourceProviderTest {

    @Mock
    private BusinessArchiveBuilder builder;

    @Mock
    private CustomPageBarResourceFactory customPageBarResourceFactory;

    @Mock
    private BarResource processFormCustomPage;

    @Mock
    private BarResource taskFormCustomPage;

    @InjectMocks
    private FormBarResourceProvider formMappingBarResourceProvider;

    @Test(expected = NullPointerException.class)
    public void should_throw_NullPointerException_if_process_is_null() throws Exception {
        formMappingBarResourceProvider.addResourcesForConfiguration(new BusinessArchiveBuilder(), null, aConfiguration()
                .build(),
                Collections.<EObject> emptySet());
    }

    @Test
    public void should_add_formMapping_resource_in_bar() throws Exception {
        //Given
        final Pool aPoolAndTaskWithFormMappings = aPoolAndTaskWithAllTypeOfFormMappings();
        final FormMappingModel formMappingModel = formMappingBarResourceProvider.newFormMappingModel(builder, aPoolAndTaskWithFormMappings);

        //When
        formMappingBarResourceProvider.addResourcesForConfiguration(builder, aPoolAndTaskWithFormMappings, aConfiguration()
                .build(),
                Collections.<EObject> emptySet());

        //Then
        verify(builder).setFormMappings(formMappingModel);
        assertThat(formMappingModel.getFormMappings()).hasSize(3);
        assertThat(formMappingModel.getFormMappings()).extracting("target", "form", "type", "taskname")
                .contains(tuple(FormMappingTarget.URL, "http://www.bonitasoft.com", FormMappingType.PROCESS_OVERVIEW, null),
                        tuple(FormMappingTarget.LEGACY, "", FormMappingType.PROCESS_START, null),
                        tuple(FormMappingTarget.INTERNAL, "custompage_StepForm", FormMappingType.TASK, "Step1"));
    }

    @Test
    public void should_not_add_formMapping_resource_in_bar_if_mapping_is_invalid() throws Exception {
        //Given
        final Pool pool = aPoolWithInvalidFormMapping();
        final FormMappingModel formMappingModel = formMappingBarResourceProvider.newFormMappingModel(builder, pool);

        //When
        formMappingBarResourceProvider.addResourcesForConfiguration(builder, pool, aConfiguration()
                .build(),
                Collections.<EObject> emptySet());

        //Then
        verify(builder).setFormMappings(formMappingModel);
        assertThat(formMappingModel.getFormMappings()).hasSize(1);
        assertThat(formMappingModel.getFormMappings()).extracting("target", "form", "type", "taskname")
                .contains(
                        tuple(FormMappingTarget.INTERNAL, "custompage_Step1", FormMappingType.TASK, "Step1"));
    }

    @Test
    public void should_add_form_custom_page_as_a_bar_resource() throws Exception {
        //Given
        doReturn(taskFormCustomPage).when(customPageBarResourceFactory).newBarResource("custompage_StepForm", "step-form-id");

        //When
        formMappingBarResourceProvider.addResourcesForConfiguration(builder, aPoolAndTaskWithAllTypeOfFormMappings(), aConfiguration()
                .build(),
                Collections.<EObject> emptySet());

        //Then
        verify(builder).addExternalResource(taskFormCustomPage);
    }

    @Test
    public void should_not_add_form_custom_page_if_target_form_is_empty() throws Exception {
        //When
        formMappingBarResourceProvider.addResourcesForConfiguration(builder, aPoolWithEmptyFormMappings(), aConfiguration()
                .build(),
                Collections.<EObject> emptySet());

        //Then
        verify(builder, never()).addExternalResource(any(BarResource.class));
    }

    @Test
    public void should_create_a_mapping_for_empty_internal_overview() throws Exception {
        final FormMappingModel formMappingModel = formMappingBarResourceProvider.newFormMappingModel(builder, aPoolWithEmptyOverviewInternalFormMappings());

        assertThat(formMappingModel.getFormMappings()).hasSize(1);
        assertThat(formMappingModel.getFormMappings().get(0)).isEqualToComparingFieldByField(
                new FormMappingDefinition("custompage_caseoverview", FormMappingType.PROCESS_OVERVIEW, FormMappingTarget.INTERNAL));
    }

    private Pool aPoolAndTaskWithAllTypeOfFormMappings() {
        return aPool()
                .withName("Pool1")
                .withVersion("1.0")
                .havingOverviewFormMapping(
                        aFormMapping().withType(org.bonitasoft.studio.model.process.FormMappingType.URL).withURL("http://www.bonitasoft.com"))
                .havingFormMapping(aFormMapping().withType(org.bonitasoft.studio.model.process.FormMappingType.LEGACY))
                .havingElements(
                        aTask().withName("Step1").havingFormMapping(
                                aFormMapping().havingTargetForm(anExpression().withName("StepForm").withContent("step-form-id"))))
                .build();
    }

    private Pool aPoolWithEmptyFormMappings() {
        return aPool()
                .withName("Pool1")
                .withVersion("1.0")
                .havingFormMapping(
                        aFormMapping().withType(org.bonitasoft.studio.model.process.FormMappingType.INTERNAL)
                                .havingTargetForm(anExpression().withContent("")))
                .build();
    }

    private Pool aPoolWithEmptyOverviewInternalFormMappings() {
        return aPool()
                .withName("Pool1")
                .withVersion("1.0")
                .havingOverviewFormMapping(
                        aFormMapping().withType(org.bonitasoft.studio.model.process.FormMappingType.INTERNAL)
                                .havingTargetForm(anExpression().withContent("")))
                .build();
    }

    private Pool aPoolWithInvalidFormMapping() {
        return aPool().withName("Pool2").withVersion("2.0")
                .havingOverviewFormMapping(aFormMapping().withType(org.bonitasoft.studio.model.process.FormMappingType.URL).withURL(""))
                .havingFormMapping(aFormMapping().havingTargetForm(anExpression().withContent(null)))
                .havingElements(
                        aTask().withName("Step1").havingFormMapping(
                                aFormMapping().havingTargetForm(anExpression().withName("Step1").withContent("step-form-id"))))
                .build();
    }

}
