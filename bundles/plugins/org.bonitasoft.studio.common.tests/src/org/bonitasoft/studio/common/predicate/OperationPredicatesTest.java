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
package org.bonitasoft.studio.common.predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.common.predicate.OperationPredicates.withLeftOperand;
import static org.bonitasoft.studio.common.predicate.OperationPredicates.withRightOperand;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.expression.builders.OperationBuilder.anOperation;

import org.junit.Test;

public class OperationPredicatesTest {

    @Test
    public void assert_operation_has_a_left_operand() throws Exception {
        assertThat(withLeftOperand().apply(anOperation().havingLeftOperand(anExpression().withContent("not empty").withName("not empty")).build())).isTrue();
    }

    @Test
    public void assert_operation_has_not_a_left_operand_if_expression_is_null() throws Exception {
        assertThat(withLeftOperand().apply(anOperation().build())).isFalse();
    }

    @Test
    public void assert_operation_has_not_a_left_operand_if_expression_has_empty_name() throws Exception {
        assertThat(withLeftOperand().apply(anOperation().havingLeftOperand(anExpression().withContent("not empty").withName("")).build())).isFalse();
    }

    @Test
    public void assert_operation_has_not_a_left_operand_if_expression_has_null_name() throws Exception {
        assertThat(withLeftOperand().apply(anOperation().havingLeftOperand(anExpression().withContent("not empty").withName(null)).build())).isFalse();
    }

    @Test
    public void assert_operation_has_not_a_left_operand_if_expression_has_empty_content() throws Exception {
        assertThat(withLeftOperand().apply(anOperation().havingLeftOperand(anExpression().withContent("").withName("not empty")).build())).isFalse();
    }

    @Test
    public void assert_operation_has_not_a_left_operand_if_expression_has_null_content() throws Exception {
        assertThat(withLeftOperand().apply(anOperation().havingLeftOperand(anExpression().withContent("").withName("not empty")).build())).isFalse();
    }

    @Test
    public void assert_operation_has_a_right_operand() throws Exception {
        assertThat(withRightOperand().apply(anOperation().havingRightOperand(anExpression().withContent("not empty").withName("not empty")).build())).isTrue();
    }

    @Test
    public void assert_operation_has_not_a_right_operand_if_expression_is_null() throws Exception {
        assertThat(withRightOperand().apply(anOperation().build())).isFalse();
    }

    @Test
    public void assert_operation_has_not_a_right_operand_if_expression_has_empty_name() throws Exception {
        assertThat(withRightOperand().apply(anOperation().havingLeftOperand(anExpression().withContent("not empty").withName("")).build())).isFalse();
    }

    @Test
    public void assert_operation_has_not_a_right_operand_if_expression_has_empty_content() throws Exception {
        assertThat(withRightOperand().apply(anOperation().havingLeftOperand(anExpression().withContent("").withName("not empty")).build())).isFalse();
    }

    @Test
    public void assert_operation_has_not_a_right_operand_if_expression_has_null_content() throws Exception {
        assertThat(withRightOperand().apply(anOperation().havingLeftOperand(anExpression().withContent(null).withName("not empty")).build())).isFalse();
    }

    @Test
    public void assert_operation_has_not_a_right_operand_if_expression_has_null_name() throws Exception {
        assertThat(withRightOperand().apply(anOperation().havingLeftOperand(anExpression().withContent("not empty").withName(null)).build())).isFalse();
    }
}
