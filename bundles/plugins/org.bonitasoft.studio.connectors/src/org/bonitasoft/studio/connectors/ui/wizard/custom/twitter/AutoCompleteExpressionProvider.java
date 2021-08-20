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
package org.bonitasoft.studio.connectors.ui.wizard.custom.twitter;

import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.emf.ecore.EObject;

public class AutoCompleteExpressionProvider implements
        IExpressionNatureProvider {

    private final List<String> additionalItems;

    public AutoCompleteExpressionProvider(final List<String> additionalItems) {
        this.additionalItems = additionalItems;
    }

    @Override
    public Expression[] getExpressions(final EObject context) {
        final Expression[] expressionTable = new Expression[additionalItems.size()];
        for (int i = 0; i < additionalItems.size(); i++) {
            expressionTable[i] = ExpressionHelper.createConstantExpression(
                    additionalItems.get(i), additionalItems.get(i),
                    String.class.getName());
        }
        return expressionTable;
    }

}
