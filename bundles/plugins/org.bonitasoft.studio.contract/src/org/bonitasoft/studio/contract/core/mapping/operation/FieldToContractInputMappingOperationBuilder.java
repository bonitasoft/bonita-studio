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
package org.bonitasoft.studio.contract.core.mapping.operation;

import java.util.Collection;

import javax.inject.Inject;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.expression.FieldToContractInputMappingExpressionBuilder;
import org.bonitasoft.studio.expression.editor.filter.ExpressionReturnTypeFilter;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.jdt.core.JavaModelException;

@Creatable
public class FieldToContractInputMappingOperationBuilder {

    private final ExpressionReturnTypeFilter expressionReturnTypeFilter;
    private final FieldToContractInputMappingExpressionBuilder expressionBuilder;

    @Inject
    public FieldToContractInputMappingOperationBuilder(final FieldToContractInputMappingExpressionBuilder expressionBuilder,
            final ExpressionReturnTypeFilter expressionReturnTypeFilter) {
        this.expressionReturnTypeFilter = expressionReturnTypeFilter;
        this.expressionBuilder = expressionBuilder;
    }

    public Operation toOperation(final BusinessObjectData data, final FieldToContractInputMapping mapping)
            throws OperationCreationException {
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        operation.setLeftOperand(ExpressionHelper.createVariableExpression(data));
        operation.setOperator(operator(mapping, data));
        try {
            operation.setRightOperand(expressionBuilder.toExpression(data, mapping, false));
        } catch (final BusinessObjectInstantiationException | JavaModelException e) {
            throw new OperationCreationException("Failed to create right operand expression", e);
        }
        if (!typesMatches(operation)) {
            throw new OperationCreationException(String.format("Expected setter parameter type (%s) does not match expression type (%s)"
                    , operation.getOperator().getInputTypes().get(0), operation.getRightOperand().getReturnType()));
        }
        return operation;
    }

    private boolean typesMatches(final Operation operation) {
        final String expectedType = operation.getOperator().getInputTypes().get(0);
        final String returnType = operation.getRightOperand().getReturnType();
        return expressionReturnTypeFilter.compatibleReturnTypes(expectedType, returnType);
    }

    private Operator operator(final FieldToContractInputMapping mapping, final BusinessObjectData data) {
        final Operator operator = ExpressionFactory.eINSTANCE.createOperator();
        operator.setType(ExpressionConstants.JAVA_METHOD_OPERATOR);
        operator.getInputTypes().add(data.isMultiple() ? Collection.class.getName() : mapping.getFieldType());
        operator.setExpression(data.isMultiple() ? "addAll" : mapping.getSetterName());
        return operator;
    }

}
