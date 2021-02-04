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
package org.bonitasoft.studio.common.predicate;

import static com.google.common.base.Preconditions.checkArgument;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.Operation;

import com.google.common.base.Predicate;

public class OperationPredicates {

    public static Predicate<Operation> withLeftOperand() {
        return new Predicate<Operation>() {

            @Override
            public boolean apply(final Operation operation) {
                return operation.getLeftOperand() != null
                        && withNameAndContent(operation.getLeftOperand());
            }

        };
    }

    public static Predicate<Operation> withRightOperand() {
        return new Predicate<Operation>() {

            @Override
            public boolean apply(final Operation operation) {
                return operation.getRightOperand() != null
                        && withNameAndContent(operation.getRightOperand());
            }
        };
    }

    private static boolean withNameAndContent(final Expression expression) {
        checkArgument(expression != null);
        return expression.hasContent() && expression.hasName();
    }
}
