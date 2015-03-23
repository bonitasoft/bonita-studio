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
package org.bonitasoft.studio.pagedesigner.core.bar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.bonitasoft.studio.model.configuration.builders.ConfigurationBuilder.aConfiguration;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.FormMappingBuilder.aFormMapping;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.Collections;

import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.engine.bpm.bar.form.model.FormMappingModel;
import org.bonitasoft.engine.form.FormMappingType;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.emf.ecore.EObject;
import org.junit.Before;
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

    private Pool poolWithFormMappings;

    @Mock
    private CustomPageBarResourceFactory customPageBarResourceFactory;

    @Mock
    private BarResource processFormCustomPage;

    @Mock
    private BarResource taskFormCustomPage;

    @InjectMocks
    private FormBarResourceProvider formMappingBarResourceProvider;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        poolWithFormMappings = aPool().withName("Pool1").withVersion("1.0")
                .havingOverviewFormMapping(aFormMapping().external().withURL("http://www.bonitasoft.com"))
                .havingFormMapping(aFormMapping().internal().havingTargetForm(anExpression().withName("processForm").withContent("process-form-id")))
                .havingElements(
                        aTask().withName("Step1").havingFormMapping(
                                aFormMapping().havingTargetForm(anExpression().withName("StepForm").withContent("step-form-id"))))
                .build();

    }

    @Test(expected = NullPointerException.class)
    public void should_throw_NullPointerException_if_process_is_null() throws Exception {
        formMappingBarResourceProvider.addResourcesForConfiguration(new BusinessArchiveBuilder(), null, aConfiguration()
                .build(),
                Collections.<EObject> emptySet());
    }

    @Test
    public void should_add_formMapping_resource_in_bar() throws Exception {
        final FormMappingModel formMappingModel = formMappingBarResourceProvider.buildFormMappingModel(builder, poolWithFormMappings);

        formMappingBarResourceProvider.addResourcesForConfiguration(builder, poolWithFormMappings, aConfiguration()
                .build(),
                Collections.<EObject> emptySet());

        verify(builder).setFormMappings(formMappingModel);
        assertThat(formMappingModel.getFormMappings()).hasSize(3);
        assertThat(formMappingModel.getFormMappings()).extracting("external", "form", "type", "taskname")
                .contains(tuple(true, "http://www.bonitasoft.com", FormMappingType.PROCESS_OVERVIEW, null),
                        tuple(false, "custompage_Pool1--1.0--processForm", FormMappingType.PROCESS_START, null),
                        tuple(false, "custompage_Pool1--1.0--StepForm", FormMappingType.TASK, "Step1"));
    }

    @Test
    public void should_not_add_formMapping_resource_in_bar_if_mapping_is_invalid() throws Exception {
        final Pool pool = aPool().withName("Pool2").withVersion("2.0")
                .havingOverviewFormMapping(aFormMapping().external().withURL(""))
                .havingFormMapping(aFormMapping().internal().havingTargetForm(anExpression().withContent(null)))
                .havingElements(
                        aTask().withName("Step1").havingFormMapping(
                                aFormMapping().havingTargetForm(anExpression().withName("Step1").withContent("step-form-id"))))
                .build();

        final FormMappingModel formMappingModel = formMappingBarResourceProvider.buildFormMappingModel(builder, pool);

        formMappingBarResourceProvider.addResourcesForConfiguration(builder, pool, aConfiguration()
                .build(),
                Collections.<EObject> emptySet());

        verify(builder).setFormMappings(formMappingModel);
        assertThat(formMappingModel.getFormMappings()).hasSize(1);
        assertThat(formMappingModel.getFormMappings()).extracting("external", "form", "type", "taskname")
                .contains(
                        tuple(false, "custompage_Pool2--2.0--Step1", FormMappingType.TASK, "Step1"));
    }

    @Test
    public void should_add_form_custom_page_as_a_bar_resource() throws Exception {
        doReturn(processFormCustomPage).when(customPageBarResourceFactory).newBarResource("custompage_Pool1--1.0--processForm", "process-form-id");
        doReturn(taskFormCustomPage).when(customPageBarResourceFactory).newBarResource("custompage_Pool1--1.0--StepForm", "step-form-id");
        formMappingBarResourceProvider.addResourcesForConfiguration(builder, poolWithFormMappings, aConfiguration()
                .build(),
                Collections.<EObject> emptySet());

        verify(builder).addExternalResource(processFormCustomPage);
        verify(builder).addExternalResource(taskFormCustomPage);
    }
}
