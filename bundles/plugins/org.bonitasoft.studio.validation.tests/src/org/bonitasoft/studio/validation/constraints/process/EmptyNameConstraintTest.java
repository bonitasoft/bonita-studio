/**
 * Copyright (C) 2014-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.validation.constraints.process;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class EmptyNameConstraintTest {

    private EmptyNameConstraint emptyNameConstraint;
    @Mock
    private IValidationContext ctx;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        when(ctx.createSuccessStatus()).thenReturn(ValidationStatus.ok());
        when(ctx.createFailureStatus(any(Object.class))).thenReturn(ValidationStatus.error(""));

        emptyNameConstraint = new EmptyNameConstraint();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_accept_non_empty_names_without_special_characters() throws Exception {
        when(ctx.getTarget()).thenReturn(aTask().withName("My name is not empty and have no special characters").build());

        final IStatus iStatus = emptyNameConstraint.performBatchValidation(ctx);

        assertThat(iStatus.isOK()).isTrue();
    }

    @Test
    public void should_fail_empty_names_for_flow_element() throws Exception {
        when(ctx.getTarget()).thenReturn(aTask().withName("").build());

        final IStatus iStatus = emptyNameConstraint.performBatchValidation(ctx);

        assertThat(iStatus.isOK()).isFalse();
    }

    @Test
    public void should_fail_too_long_names_for_sequence_flow_element() throws Exception {
        final SequenceFlow sequenceFlow = ProcessFactory.eINSTANCE.createSequenceFlow();
        sequenceFlow.setName(aStringWithLength(256));
        when(ctx.getTarget()).thenReturn(sequenceFlow);

        final IStatus iStatus = emptyNameConstraint.performBatchValidation(ctx);

        assertThat(iStatus.isOK()).isFalse();
    }

    @Test
    public void should_accept_empty_names_for_sequence_flow_element() throws Exception {
        when(ctx.getTarget()).thenReturn(ProcessFactory.eINSTANCE.createSequenceFlow());

        final IStatus iStatus = emptyNameConstraint.performBatchValidation(ctx);

        assertThat(iStatus.isOK()).isTrue();
    }

    @Test
    public void should_accept_empty_names_for_text_annotation_element() throws Exception {
        when(ctx.getTarget()).thenReturn(ProcessFactory.eINSTANCE.createTextAnnotation());

        final IStatus iStatus = emptyNameConstraint.performBatchValidation(ctx);

        assertThat(iStatus.isOK()).isTrue();
    }

    @Test
    public void should_fail_names_with_special_characters() throws Exception {
        when(ctx.getTarget()).thenReturn(aTask().withName("hello#").build());

        final IStatus iStatus = emptyNameConstraint.performBatchValidation(ctx);

        assertThat(iStatus.isOK()).isFalse();
    }

    @Test
    public void should_fail_names_with_reserved_keywords() throws Exception {
        final String[] reservedKeywordsToTest = new String[] { "content", "Content", "api", "API", "theme", "Theme" };
        for (final String reservedKeywordToTest : reservedKeywordsToTest) {
            when(ctx.getTarget()).thenReturn(aTask().withName(reservedKeywordToTest).build());

            final IStatus iStatus = emptyNameConstraint.performBatchValidation(ctx);

            assertThat(iStatus.isOK()).isFalse();
        }
    }

    @Test
    public void should_fail_task_name_with_space_column() throws Exception {
        when(ctx.getTarget()).thenReturn(aTask().withName("Hello : Romain").build());

        final IStatus iStatus = emptyNameConstraint.performBatchValidation(ctx);

        assertThat(iStatus.isOK()).isFalse();
    }

    private static String aStringWithLength(final int length) {
        final StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append("a");
        }
        return sb.toString();
    }
}
