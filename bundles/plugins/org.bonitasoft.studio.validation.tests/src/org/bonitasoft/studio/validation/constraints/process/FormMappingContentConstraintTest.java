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
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.FormMappingType;
import org.bonitasoft.studio.model.process.builders.MainProcessBuilder;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.emf.validation.IValidationContext;
import org.junit.Test;

public class FormMappingContentConstraintTest {

    @Test
    public void should_fail_if_form_mapping_is_INTERNAL_and_has_no_target_overview_form() throws Exception {
        final FormMappingContentConstraint internalFormMappingConstraint = new FormMappingContentConstraint();

        final IValidationContext validationContext = aValidationContext(aPool()
                .havingOverviewFormMapping(aFormMapping().withType(FormMappingType.INTERNAL).havingTargetForm(anExpression()))
                .build().getOverviewFormMapping());
        internalFormMappingConstraint.performBatchValidation(validationContext);

        verify(validationContext).createFailureStatus(Messages.bind(Messages.emptyFormMappingWarning, Messages.overviewFormMapping));
    }

    @Test
    public void should_fail_if_form_mapping_is_INTERNAL_and_has_no_target_instantiation_form() throws Exception {
        final FormMappingContentConstraint internalFormMappingConstraint = new FormMappingContentConstraint();

        final IValidationContext validationContext = aValidationContext(aPool()
                .havingFormMapping(aFormMapping().withType(FormMappingType.INTERNAL).havingTargetForm(anExpression()))
                .build().getFormMapping());
        internalFormMappingConstraint.performBatchValidation(validationContext);

        verify(validationContext).createFailureStatus(Messages.bind(Messages.emptyFormMappingWarning, Messages.instantiationFormMapping));
    }

    @Test
    public void should_fail_if_form_mapping_is_INTERNAL_and_has_no_target_form_and_on_diagram() throws Exception {
        final FormMappingContentConstraint internalFormMappingConstraint = new FormMappingContentConstraint();

        final FormMapping formMapping = aFormMapping().withType(FormMappingType.INTERNAL).havingTargetForm(anExpression())
                .build();
        MainProcessBuilder.aMainProcess().havingFormMapping(formMapping);

        final IValidationContext validationContext = aValidationContext(formMapping);
        internalFormMappingConstraint.performBatchValidation(validationContext);

        verify(validationContext).createFailureStatus(Messages.formMappingAtDiagramLevel_ModelInconsistency);
    }

    @Test
    public void should_not_fail_if_form_mapping_is_INTERNAL_and_has_target_form() throws Exception {
        final FormMappingContentConstraint internalFormMappingConstraint = new FormMappingContentConstraint();

        final IValidationContext validationContext = aValidationContext(aFormMapping().withType(FormMappingType.INTERNAL)
                .havingTargetForm(anExpression().withContent("a_form_id"))
                .build());
        internalFormMappingConstraint.performBatchValidation(validationContext);

        verify(validationContext).createSuccessStatus();
    }

    @Test
    public void should_not_fail_if_form_mapping_is_not_INTERNAL() throws Exception {
        final FormMappingContentConstraint internalFormMappingConstraint = new FormMappingContentConstraint();

        final IValidationContext validationContext = aValidationContext(aFormMapping().withType(FormMappingType.NONE)
                .havingTargetForm(anExpression().withContent("a_form_id"))
                .build());
        internalFormMappingConstraint.performBatchValidation(validationContext);

        verify(validationContext).createSuccessStatus();
    }

    @Test
    public void should_return_an_error_status_if_not_url_set_for_URL_type() throws Exception {
        final FormMappingContentConstraint constraint = new FormMappingContentConstraint();

        final IValidationContext validationContext = aValidationContext(aFormMapping().withType(FormMappingType.URL).build());
        constraint.performBatchValidation(validationContext);

        verify(validationContext).createFailureStatus(Messages.invalidURLFormMapping);
    }

    private IValidationContext aValidationContext(final FormMapping formMapping) {
        final IValidationContext validationContext = mock(IValidationContext.class);
        when(validationContext.getTarget()).thenReturn(formMapping);
        return validationContext;
    }

}
