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

import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ListExpression;

/**
 * @author Romain Bioteau
 */
public class ListExpressionBuilder {

    private final ListExpression listExpression;

    private ListExpressionBuilder(final ListExpression tableExpression) {
        this.listExpression = tableExpression;
    }

    public static ListExpressionBuilder aListExpression() {
        return new ListExpressionBuilder(ExpressionFactory.eINSTANCE.createListExpression());
    }

    public ListExpressionBuilder havingExpressions(ExpressionBuilder... expressionBuilders) {
        for (final ExpressionBuilder expBuilder : expressionBuilders) {
            listExpression.getExpressions().add(expBuilder.build());
        }
        return this;
    }

    public ListExpression build() {
        return listExpression;
    }

}
