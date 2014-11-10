/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.validation.constraints.process;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.validation.IValidationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Romain Bioteau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class EndingPageflowConstraintTest {

    @Mock
    private IValidationContext context;

    @Spy
    private EndingPageflowConstraint constraintUnderTest;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        when(context.createSuccessStatus()).thenReturn(Status.OK_STATUS);
        when(context.createFailureStatus(anyObject())).thenReturn(new Status(IStatus.ERROR, "unknown", ""));
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldPerformBatchValidation_ReturnValidStatus_For_Pageflow_With_SubmitButton() throws Exception {
        final Task pageflow = ProcessFactory.eINSTANCE.createTask();
        final Form formWithSubmitButton = FormFactory.eINSTANCE.createForm();
        formWithSubmitButton.getWidgets().add(FormFactory.eINSTANCE.createSubmitFormButton());
        pageflow.getForm().add(formWithSubmitButton);
        when(context.getTarget()).thenReturn(pageflow);
        assertThat(constraintUnderTest.performBatchValidation(context).isOK()).isTrue();
    }

    @Test
    public void shouldPerformBatchValidation_ReturnValidStatus_For_Pageflow_With_SubmitButtonInGroup() throws Exception {
        final Task pageflow = ProcessFactory.eINSTANCE.createTask();
        final Form formWithSubmitButton = FormFactory.eINSTANCE.createForm();
        final Group group = FormFactory.eINSTANCE.createGroup();
        group.getWidgets().add(FormFactory.eINSTANCE.createSubmitFormButton());
        formWithSubmitButton.getWidgets().add(group);
        pageflow.getForm().add(formWithSubmitButton);
        when(context.getTarget()).thenReturn(pageflow);
        assertThat(constraintUnderTest.performBatchValidation(context).isOK()).isTrue();
    }

    @Test
    public void shouldPerformBatchValidation_ReturnValidStatus_For_Pageflow_With_Group_And_SubmitButton() throws Exception {
        final Task pageflow = ProcessFactory.eINSTANCE.createTask();
        final Form formWithSubmitButton = FormFactory.eINSTANCE.createForm();
        final Group group = FormFactory.eINSTANCE.createGroup();
        formWithSubmitButton.getWidgets().add(group);
        formWithSubmitButton.getWidgets().add(FormFactory.eINSTANCE.createSubmitFormButton());
        pageflow.getForm().add(formWithSubmitButton);
        when(context.getTarget()).thenReturn(pageflow);
        assertThat(constraintUnderTest.performBatchValidation(context).isOK()).isTrue();
    }

    @Test
    public void shouldPerformBatchValidation_ReturnErrorStatus_For_Pageflow_Without_SubmitButton() throws Exception {
        final Task pageflow = ProcessFactory.eINSTANCE.createTask();
        final Form formWithSubmitButton = FormFactory.eINSTANCE.createForm();
        pageflow.getForm().add(formWithSubmitButton);
        when(context.getTarget()).thenReturn(pageflow);
        assertThat(constraintUnderTest.performBatchValidation(context).isOK()).isFalse();
    }

}
