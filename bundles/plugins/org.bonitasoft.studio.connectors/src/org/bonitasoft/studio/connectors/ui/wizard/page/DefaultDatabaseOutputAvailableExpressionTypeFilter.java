/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.connectors.ui.wizard.page;

import java.util.Objects;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.jface.viewers.Viewer;

public class DefaultDatabaseOutputAvailableExpressionTypeFilter extends AvailableExpressionTypeFilter {

    public DefaultDatabaseOutputAvailableExpressionTypeFilter() {
        super(new String[] {
                ExpressionConstants.CONSTANT_TYPE,
                ExpressionConstants.CONNECTOR_OUTPUT_TYPE,
                ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.PARAMETER_TYPE,
                ExpressionConstants.SCRIPT_TYPE,
                ExpressionConstants.DOCUMENT_TYPE
        });
    }

    @Override
    public boolean select(Viewer viewer, Object context, Object element) {
        return super.select(viewer, context, element)
                ? validateResultSetOutput(element)
                : false;
    }

    protected boolean validateResultSetOutput(Object element) {
        return element instanceof Output
                ? isResultsetOutputName(((Output) element).getName())
                : isExpressionConnectorOutputType(element)
                        ? isResultsetOutputName(((Expression) element).getName())
                        : true;
    }

    private boolean isExpressionConnectorOutputType(Object element) {
        return element instanceof Expression
                && Objects.equals(((Expression) element).getType(), ExpressionConstants.CONNECTOR_OUTPUT_TYPE);
    }

    private boolean isResultsetOutputName(String name) {
        return Objects.equals(DatabaseConnectorConstants.RESULTSET_OUTPUT, name);
    }

}
