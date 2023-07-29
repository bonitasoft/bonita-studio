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
package org.bonitasoft.studio.expression.editor.operation;

import org.bonitasoft.bpm.model.expression.Operation;
import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.eclipse.core.databinding.observable.value.IObservableValue;

public class DefaultReturnTypeResolver {

    private final IObservableValue operationObservable;

    public DefaultReturnTypeResolver(final IObservableValue operationObservable) {
        this.operationObservable = operationObservable;
    }

    public String guessRightOperandReturnType() {
        Operation operation = (Operation) operationObservable.getValue();
        final String type = operation.getOperator().getType();
        if (ExpressionConstants.SET_DOCUMENT_OPERATOR.equals(type)) {
            return ExpressionConstants.DOCUMENT_VALUE_RETURN_TYPE;
        }
        if (ExpressionConstants.JAVA_METHOD_OPERATOR.equals(type) && !operation.getOperator().getInputTypes().isEmpty()) {
            return operation.getOperator().getInputTypes().get(0);
        }
        return operation.getLeftOperand().getReturnType();
    }
}
