/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.connector.model.definition.wizard;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionNameResolver;
import org.bonitasoft.studio.model.expression.Expression;

import com.google.common.base.Strings;


public class ConnectorInputNameResolver implements ExpressionNameResolver {

    private final String inputName;

    public ConnectorInputNameResolver(String inputName) {
        this.inputName = inputName;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.viewer.ExpressionNameResolver#getName(org.bonitasoft.studio.model.expression.Expression)
     */
    @Override
    public String getName(Expression expression) {
        if (Strings.isNullOrEmpty(expression.getName())) {
            if (ExpressionConstants.SCRIPT_TYPE.equals(expression.getType())) {
                return String.format("%s()", inputName);
            }
            return "";
        }
        return expression.getName();
    }

}
