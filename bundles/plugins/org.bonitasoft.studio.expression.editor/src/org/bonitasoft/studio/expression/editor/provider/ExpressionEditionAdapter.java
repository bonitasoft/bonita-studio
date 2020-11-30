/**
 * Copyright (C) 2020 BonitaSoft S.A.
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
package org.bonitasoft.studio.expression.editor.provider;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;

public class ExpressionEditionAdapter {

    private ExpressionEditionAdapter() {
    }

    public static Expression adapt(Expression expression) {
        String type = expression.getType();
        if (ExpressionConstants.CONSTANT_TYPE.equals(type)) {
            return adaptConstantExpression(expression);
        }
        return expression;
    }

    private static Expression adaptConstantExpression(Expression expression) {
        expression.setType(ExpressionConstants.SCRIPT_TYPE);
        String content = expression.getContent() != null ? expression.getContent() : "";
        String returnType = expression.getReturnType();
        if (String.class.getName().equals(returnType)) {
            expression.setContent(String.format("'%s'", content));
        } else if (isADateReturnType(returnType)) {
            expression.setContent(String.format("%s.parse('%s')", returnType, content));
        }
        return expression;
    }

    private static boolean isADateReturnType(String returnType) {
        return LocalDate.class.getName().equals(returnType)
                || LocalDateTime.class.getName().equals(returnType)
                || OffsetDateTime.class.getName().equals(returnType);
    }

}
