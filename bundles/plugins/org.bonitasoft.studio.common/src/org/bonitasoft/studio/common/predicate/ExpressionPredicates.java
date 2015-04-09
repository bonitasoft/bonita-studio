/**
 * Copyright (C) 2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.predicate;

import org.bonitasoft.studio.model.expression.Expression;

import com.google.common.base.Predicate;

/**
 * @author Romain Bioteau
 */
public class ExpressionPredicates {

    public static Predicate<Expression> withExpressionType(final String expressionType) {
        return new Predicate<Expression>() {

            @Override
            public boolean apply(final Expression input) {
                return expressionType.equals(input.getType());
            }
        };
    }

    public static Predicate<Expression> withReturnType(final String returnType) {
        return new Predicate<Expression>() {

            @Override
            public boolean apply(final Expression input) {
                return returnType.equals(input.getReturnType());
            }
        };
    }

}
