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
package org.bonitasoft.studio.exporter.form;

import org.bonitasoft.engine.operation.OperatorType;
import org.bonitasoft.forms.client.model.ActionType;
import org.bonitasoft.studio.common.ExpressionConstants;

public class ActionTypeResolver {

    public static ActionType toActionType(final String operatorType) {
        if (OperatorType.DOCUMENT_CREATE_UPDATE.name().equals(operatorType)) {
            return ActionType.ASSIGNMENT;
        }
        if (ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR.equals(operatorType)) {
            return ActionType.ASSIGNMENT;
        }
        // it's the left operand that tell if it's a string index to set
        if (OperatorType.STRING_INDEX.name().equals(operatorType)) {
            return ActionType.ASSIGNMENT;
        }
        // it's the left operand that tell if it's a string index to set
        if (ExpressionConstants.CREATE_BUSINESS_DATA_OPERATOR.equals(operatorType)) {
            return ActionType.ASSIGNMENT;
        }
        if (ExpressionConstants.BUSINESS_DATA_JAVA_SETTER_OPERATOR.equals(operatorType)) {
            return ActionType.JAVA_METHOD;
        }
        if (ExpressionConstants.ATTACH_EXISTING_BUSINESS_DATA.equals(operatorType)) {
            return ActionType.ASSIGNMENT;
        }
        return ActionType.valueOf(operatorType);
    }

}
