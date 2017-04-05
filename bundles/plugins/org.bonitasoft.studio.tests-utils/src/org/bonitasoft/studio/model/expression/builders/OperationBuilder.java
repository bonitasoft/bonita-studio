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

import org.bonitasoft.studio.model.Buildable;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.process.OperationContainer;

/**
 * @author Romain Bioteau
 */
public class OperationBuilder implements Buildable<Operation> {

    private final Operation operation;

    private OperationBuilder(final Operation operation) {
        this.operation = operation;
    }

    public static OperationBuilder anOperation() {
        return new OperationBuilder(ExpressionFactory.eINSTANCE.createOperation());
    }

    public OperationBuilder havingLeftOperand(final ExpressionBuilder leftOperand) {
        operation.setLeftOperand(leftOperand.build());
        return this;
    }

    public OperationBuilder havingRightOperand(final ExpressionBuilder rightOperand) {
        operation.setRightOperand(rightOperand.build());
        return this;
    }

    public OperationBuilder havingRightOperand(final Expression rightOperand) {
        operation.setRightOperand(rightOperand);
        return this;
    }

    public OperationBuilder havingOperator(final OperatorBuilder operator) {
        operation.setOperator(operator.build());
        return this;
    }

    public OperationBuilder in(final Buildable<? extends OperationContainer> buildable) {
        buildable.build().getOperations().add(operation);
        return this;
    }

    @Override
    public Operation build() {
        return operation;
    }

}
