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
package org.bonitasoft.studio.contract.core;

import org.bonitasoft.engine.expression.Expression;
import org.bonitasoft.engine.expression.ExpressionBuilder;
import org.bonitasoft.engine.expression.ExpressionType;
import org.bonitasoft.engine.expression.InvalidExpressionException;
import org.bonitasoft.engine.operation.Operation;
import org.bonitasoft.engine.operation.OperationBuilder;
import org.bonitasoft.engine.operation.OperatorType;
import org.bonitasoft.studio.common.DataUtil;
import org.bonitasoft.studio.contract.core.exception.OperationCreationException;
import org.bonitasoft.studio.model.process.ContractInputMapping;
import org.bonitasoft.studio.model.process.Data;

/**
 * @author Romain Bioteau
 *
 */
public class ContractMappingFactory {

    private final OperationBuilder operationBuilder;

    public ContractMappingFactory() {
        operationBuilder = new OperationBuilder();
    }

    public Operation createOperation(final ContractInputMapping mapping) throws OperationCreationException {
        if (mapping == null) {
            throw new IllegalArgumentException("mapping is null");
        }
        final Data data = mapping.getData();
        if (data == null) {
            throw new IllegalArgumentException("invalid mapping, data is null");
        }

        final OperationBuilder newInstance = operationBuilder.createNewInstance();
        newInstance.setLeftOperand(data.getName(), ExpressionType.TYPE_VARIABLE.name());
        try {
            newInstance.setRightOperand(createRightOperand(mapping));
        } catch (final InvalidExpressionException e) {
            throw new OperationCreationException("Failed to create right operand expression", e);
        }
        newInstance.setType(OperatorType.ASSIGNMENT);
        return newInstance.done();
    }

    protected Expression createRightOperand(final ContractInputMapping mapping) throws InvalidExpressionException {
        final ExpressionBuilder expBuilder = new ExpressionBuilder();
        final Data data = mapping.getData();
        final ExpressionBuilder newInstance = expBuilder.createNewInstance("storeInputIn" + data.getName());
        newInstance.setExpressionType(ExpressionType.TYPE_VARIABLE);
        newInstance.setContent(data.getName());
        newInstance.setReturnType(DataUtil.getTechnicalTypeFor(data));
        return newInstance.done();
    }

}
