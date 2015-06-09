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
package org.bonitasoft.studio.model.expression.builders;

import org.bonitasoft.engine.operation.OperatorType;
import org.bonitasoft.studio.model.Buildable;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;

/**
 * @author Romain Bioteau
 */
public class OperatorBuilder {

    private final Operator operator;

    private OperatorBuilder(final Operator operator) {
        this.operator = operator;
    }

    public static OperatorBuilder anOperator() {
        return new OperatorBuilder(ExpressionFactory.eINSTANCE.createOperator());
    }

    public static OperatorBuilder anAssignmentOperator() {
        return new OperatorBuilder(ExpressionFactory.eINSTANCE.createOperator()).withType(OperatorType.ASSIGNMENT.name());
    }

    public OperatorBuilder withType(final String type) {
        operator.setType(type);
        return this;
    }

    public OperatorBuilder withExpression(final String expression) {
        operator.setExpression(expression);
        return this;
    }

    public OperatorBuilder havingInputTypes(final String... inputTypes) {
        if (inputTypes != null) {
            for (final String type : inputTypes) {
                operator.getInputTypes().add(type);
            }
        }
        return this;
    }

    public OperatorBuilder in(final Buildable<? extends Operation> operationBuildable) {
        operationBuildable.build().setOperator(operator);
        return this;
    }

    public Operator build() {
        return operator;
    }

}
