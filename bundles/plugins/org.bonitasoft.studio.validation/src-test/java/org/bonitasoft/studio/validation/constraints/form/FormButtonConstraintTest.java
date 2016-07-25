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
package org.bonitasoft.studio.validation.constraints.form;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.form.SubmitFormButton;
import org.bonitasoft.studio.model.form.TextAreaFormField;
import org.bonitasoft.studio.model.form.ViewForm;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
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
public class FormButtonConstraintTest {

    @Mock
    private IValidationContext context;

    @Spy
    private FormButtonConstraint constraintUnderTest;

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
    public void shouldPerformBatchValidation_returns_ValidStatus_ForEntryFormWith_SubmitButton() throws Exception {
        final Pool pageFlow = ProcessFactory.eINSTANCE.createPool();
        final Form formWithSubmittButton = FormFactory.eINSTANCE.createForm();
        final SubmitFormButton b = FormFactory.eINSTANCE.createSubmitFormButton();
        formWithSubmittButton.getWidgets().add(b);
        pageFlow.getForm().add(formWithSubmittButton);
        when(context.getTarget()).thenReturn(formWithSubmittButton);
        final IStatus status = constraintUnderTest.performBatchValidation(context);
        assertThat(status.isOK()).isTrue();
    }

    @Test
    public void shouldPerformBatchValidation_returns_ValidStatus_ForEntryFormWith_SubmitButton_In_A_Group() throws Exception {
        final Pool pageFlow = ProcessFactory.eINSTANCE.createPool();
        final Form formWithSubmittButton = FormFactory.eINSTANCE.createForm();
        final SubmitFormButton b = FormFactory.eINSTANCE.createSubmitFormButton();
        final Group g = FormFactory.eINSTANCE.createGroup();
        g.getWidgets().add(b);
        formWithSubmittButton.getWidgets().add(g);
        pageFlow.getForm().add(formWithSubmittButton);
        when(context.getTarget()).thenReturn(formWithSubmittButton);
        final IStatus status = constraintUnderTest.performBatchValidation(context);
        assertThat(status.isOK()).isTrue();
    }

    @Test
    public void shouldPerformBatchValidation_returns_ErrorStatus_ForEntryFormWithout_SubmitButton_or_NextButton() throws Exception {
        final Pool pageFlow = ProcessFactory.eINSTANCE.createPool();
        final Form formWithSubmittButton = FormFactory.eINSTANCE.createForm();
        final TextAreaFormField b = FormFactory.eINSTANCE.createTextAreaFormField();
        formWithSubmittButton.getWidgets().add(b);
        pageFlow.getForm().add(formWithSubmittButton);
        when(context.getTarget()).thenReturn(formWithSubmittButton);
        final IStatus status = constraintUnderTest.performBatchValidation(context);
        assertThat(status.isOK()).isFalse();
    }

    @Test
    public void shouldPerformBatchValidation_returns_ErrorStatus_ForOverviewFormWith_SubmitButton() throws Exception {
        final Pool pageFlow = ProcessFactory.eINSTANCE.createPool();
        final ViewForm formWithSubmittButton = FormFactory.eINSTANCE.createViewForm();
        final SubmitFormButton b = FormFactory.eINSTANCE.createSubmitFormButton();
        formWithSubmittButton.getWidgets().add(b);
        pageFlow.getRecapForms().add(formWithSubmittButton);
        when(context.getTarget()).thenReturn(b);
        final IStatus status = constraintUnderTest.performBatchValidation(context);
        assertThat(status.isOK()).isFalse();
    }

    @Test
    public void shouldPerformBatchValidation_returns_ValidStatus_ForOverviewFormWithout_SubmitButton() throws Exception {
        final Pool pageFlow = ProcessFactory.eINSTANCE.createPool();
        final ViewForm formWithoutSubmittButton = FormFactory.eINSTANCE.createViewForm();
        final TextAreaFormField b = FormFactory.eINSTANCE.createTextAreaFormField();
        formWithoutSubmittButton.getWidgets().add(b);
        pageFlow.getRecapForms().add(formWithoutSubmittButton);
        when(context.getTarget()).thenReturn(b);
        final IStatus status = constraintUnderTest.performBatchValidation(context);
        assertThat(status.isOK()).isTrue();
    }

}
