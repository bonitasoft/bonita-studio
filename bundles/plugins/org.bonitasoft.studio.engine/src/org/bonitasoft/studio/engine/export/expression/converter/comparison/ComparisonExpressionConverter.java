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
package org.bonitasoft.studio.engine.export.expression.converter.comparison;

import org.bonitasoft.engine.expression.ExpressionBuilder;
import org.bonitasoft.engine.expression.InvalidExpressionException;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.condition.conditionModel.Operation_Compare;
import org.bonitasoft.studio.condition.conditionModel.Operation_NotUnary;
import org.bonitasoft.studio.condition.conditionModel.Unary_Operation;
import org.bonitasoft.studio.condition.ui.expression.ComparisonExpressionLoadException;
import org.bonitasoft.studio.condition.ui.expression.XtextComparisonExpressionLoader;
import org.bonitasoft.studio.engine.export.expression.converter.IExpressionConverter;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Romain Bioteau
 */
public class ComparisonExpressionConverter implements IExpressionConverter {

    private final XtextComparisonExpressionLoader expressionLoader;

    public ComparisonExpressionConverter(final XtextComparisonExpressionLoader expressionLoader) {
        this.expressionLoader = expressionLoader;
    }

    @Override
    public boolean appliesTo(final Expression expression) {
        return expression != null && ExpressionConstants.CONDITION_TYPE.equals(expression.getType());
    }

    @Override
    public org.bonitasoft.engine.expression.Expression convert(final Expression expression) throws InvalidExpressionException {
        final ExpressionBuilder expressionBuilder = new ExpressionBuilder();
        final String name = getExpressionName(expression);
        final String content = expression.getContent();
        Operation_Compare compare = null;
        try {
            compare = expressionLoader.loadConditionExpression(content, expression.eContainer());
            if (compare == null || compare.getOp() == null) {
                throw new ComparisonExpressionLoadException("Failed to load comparison expression: " + expression);
            }
            final EObject op = compare.getOp();
            if (op instanceof Unary_Operation) {
                return createExpressionForUnaryOperation(expression, expressionBuilder, name, op);
            } else if (op instanceof org.bonitasoft.studio.condition.conditionModel.Operation) {
                return createExpressionForBinaryOperation(expression, expressionBuilder, name, op);
            } else {
                throw new IllegalStateException("Unsupported Condition operation type: " + op);
            }
        } catch (final ComparisonExpressionLoadException e) {
            throw new InvalidExpressionException("Failed to load comparison expression");
        }

    }

    protected org.bonitasoft.engine.expression.Expression createExpressionForBinaryOperation(final Expression expression,
            final ExpressionBuilder expressionBuilder, final String name, final EObject op) throws InvalidExpressionException {
        final org.bonitasoft.studio.condition.conditionModel.Expression rightExp = ((org.bonitasoft.studio.condition.conditionModel.Operation) op)
                .getRight();
        final org.bonitasoft.studio.condition.conditionModel.Expression leftExp = ((org.bonitasoft.studio.condition.conditionModel.Operation) op)
                .getLeft();
        final String operator = new OperatorSwitch().doSwitch(op);

        final org.bonitasoft.engine.expression.Expression rightExpression = new ExpressionConditionModelSwitch(expression).doSwitch(rightExp);
        if (rightExpression == null) {
            throw new InvalidExpressionException("Condition expression " + name + " failed to export right operand: " + rightExp.toString());
        }
        final org.bonitasoft.engine.expression.Expression leftExpression = new ExpressionConditionModelSwitch(expression).doSwitch(leftExp);
        if (leftExpression == null) {
            throw new InvalidExpressionException("Condition expression " + name + " failed to export left operand: " + leftExp.toString());
        }
        return expressionBuilder.createComparisonExpression(name, leftExpression, operator, rightExpression);
    }

    protected org.bonitasoft.engine.expression.Expression createExpressionForUnaryOperation(final Expression expression,
            final ExpressionBuilder expressionBuilder, final String name, final EObject op) throws InvalidExpressionException {
        final org.bonitasoft.studio.condition.conditionModel.Expression conditionExp = ((Unary_Operation) op).getValue();
        final org.bonitasoft.engine.expression.Expression engineExpression = new ExpressionConditionModelSwitch(expression).doSwitch(conditionExp);
        if (engineExpression == null) {
            throw new InvalidExpressionException("Condition expression " + name + " convertion has failed");
        }
        if (op instanceof Operation_NotUnary) {
            return expressionBuilder.createLogicalComplementExpression(name, engineExpression);
        } else {
            return engineExpression;
        }
    }

    protected String getExpressionName(final Expression expression) {
        String name = expression.getName();
        if (name == null || name.isEmpty()) {
            name = "<empty-name>";
        }
        return name;
    }

}
