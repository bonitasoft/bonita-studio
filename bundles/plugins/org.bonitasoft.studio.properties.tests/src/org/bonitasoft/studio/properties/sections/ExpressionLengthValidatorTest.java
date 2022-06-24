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
package org.bonitasoft.studio.properties.sections;

import org.assertj.core.api.Assertions;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.properties.sections.userxp.ExpressionLengthValidator;
import org.junit.Test;


public class ExpressionLengthValidatorTest {

    @Test
    public void testValidate() {
        final ExpressionLengthValidator expressionLengthValidator = new ExpressionLengthValidator(5);
        final Expression inputExpression = ExpressionHelper.createConstantExpression("", String.class.getName());
        expressionLengthValidator.setInputExpression(inputExpression);
        Assertions.assertThat(expressionLengthValidator.validate("plop").isOK()).isTrue();
        Assertions.assertThat(expressionLengthValidator.validate("plopp").isOK()).isTrue();
        Assertions.assertThat(expressionLengthValidator.validate("ploppp").isOK()).isFalse();
    }

    @Test
    public void testIsRelevantForExpressionType() {
        final ExpressionLengthValidator expressionLengthValidator = new ExpressionLengthValidator(0);
        Assertions.assertThat(expressionLengthValidator.isRelevantForExpressionType(ExpressionConstants.CONSTANT_TYPE)).isTrue();
        Assertions.assertThat(expressionLengthValidator.isRelevantForExpressionType(ExpressionConstants.CONDITION_TYPE)).isFalse();
        Assertions.assertThat(expressionLengthValidator.isRelevantForExpressionType(ExpressionConstants.VARIABLE_TYPE)).isFalse();
    }

}
