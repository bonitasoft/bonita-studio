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

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.builders.OperationBuilder.anOperation;
import static org.bonitasoft.studio.model.form.builders.TextFormFieldBuilder.aTextFormField;

import org.junit.Test;

public class ValidableOperationTest {

    @Test
    public void should_not_validate_readonly_widget_output_operation() throws Exception {
        final boolean shouldValidateOperation = ValidableOperation.shouldValidateOperation(aTextFormField().readOnly().havingOutputOperation(anOperation())
                .build()
                .getAction());

        assertThat(shouldValidateOperation).isFalse();
    }

    @Test
    public void should_validate_not_readonly_widget_output_operation() throws Exception {
        final boolean shouldValidateOperation = ValidableOperation.shouldValidateOperation(aTextFormField().havingOutputOperation(anOperation()).build()
                .getAction());

        assertThat(shouldValidateOperation).isTrue();
    }

    @Test
    public void should_validate_operation_not_in_a_widget() throws Exception {
        final boolean shouldValidateOperation = ValidableOperation.shouldValidateOperation(anOperation().build());

        assertThat(shouldValidateOperation).isTrue();
    }
}
