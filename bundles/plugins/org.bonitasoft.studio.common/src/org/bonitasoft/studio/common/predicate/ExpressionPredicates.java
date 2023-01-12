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

import java.util.Objects;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.Element;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

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

    public static Predicate<Expression> withReferencedElement(final EObject referencedElement) {
        return new Predicate<Expression>() {

            @Override
            public boolean apply(final Expression input) {
                return isElementIsReferencedInScript(input, referencedElement);
            }
        };
    }

    public static java.util.function.Predicate<Expression> containingReferencedElement(final EObject referencedElement) {
        return exp -> isElementIsReferencedInScript(exp, referencedElement);
    }

    private static boolean isElementIsReferencedInScript(final Expression expr, final EObject element) {
        if (element instanceof ContractInput && expr.getReferencedElements().stream()
                .filter(ContractInput.class::isInstance)
                .map(ContractInput.class::cast)
                .map(ContractInput::getName)
                .anyMatch(((ContractInput) element).getName()::equals)) {
            return true;
        }

        if (expr.getReferencedElements().stream()
                .anyMatch(dep -> EcoreUtil.equals(element, dep))) {
            return true;
        }

        for (final EObject o : expr.getReferencedElements()) {
            if (element instanceof Element && o instanceof Element && ModelHelper.isSameElement(element, o)) {
                return !ModelHelper.isReferencedElementIsInExpression(expr);
            } else {
                if (element instanceof Parameter && o instanceof Parameter
                        && ((Parameter) element).getName().equals(((Parameter) o).getName())) {
                    return !ModelHelper.isReferencedElementIsInExpression(expr);
                }
            }
        }
        return false;
    }

    public static Predicate<Expression> withName(final String name) {
        return new Predicate<Expression>() {

            @Override
            public boolean apply(final Expression input) {
                return Objects.equals(name, input.getName());
            }
        };
    }
}
