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
package org.bonitasoft.studio.validation.constraints.process;

import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.FormMappingBuilder.aFormMapping;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.FormMappingType;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.emf.validation.IValidationContext;
import org.junit.Test;

public class InternalFormMappingConstraintTest {

    @Test
    public void should_fail_if_form_mapping_is_INTERNAL_and_has_no_target_form() throws Exception {
        final InternalFormMappingConstraint internalFormMappingConstraint = new InternalFormMappingConstraint();

        final IValidationContext validationContext = aValidationContext(aFormMapping().withType(FormMappingType.INTERNAL).havingTargetForm(anExpression())
                .build());
        internalFormMappingConstraint.performBatchValidation(validationContext);

        verify(validationContext).createFailureStatus(Messages.emptyFormMappingWarning);
    }

    @Test
    public void should_not_fail_if_form_mapping_is_INTERNAL_and_has_target_form() throws Exception {
        final InternalFormMappingConstraint internalFormMappingConstraint = new InternalFormMappingConstraint();

        final IValidationContext validationContext = aValidationContext(aFormMapping().withType(FormMappingType.INTERNAL)
                .havingTargetForm(anExpression().withContent("a_form_id"))
                .build());
        internalFormMappingConstraint.performBatchValidation(validationContext);

        verify(validationContext).createSuccessStatus();
    }

    @Test
    public void should_not_fail_if_form_mapping_is_not_INTERNAL() throws Exception {
        final InternalFormMappingConstraint internalFormMappingConstraint = new InternalFormMappingConstraint();

        final IValidationContext validationContext = aValidationContext(aFormMapping().withType(FormMappingType.LEGACY)
                .havingTargetForm(anExpression().withContent("a_form_id"))
                .build());
        internalFormMappingConstraint.performBatchValidation(validationContext);

        verify(validationContext).createSuccessStatus();
    }

    private IValidationContext aValidationContext(final FormMapping formMapping) {
        final IValidationContext validationContext = mock(IValidationContext.class);
        when(validationContext.getTarget()).thenReturn(formMapping);
        return validationContext;
    }

}
