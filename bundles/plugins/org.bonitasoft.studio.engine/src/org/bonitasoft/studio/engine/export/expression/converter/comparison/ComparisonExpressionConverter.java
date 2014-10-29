/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.engine.export.expression.converter.comparison;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.engine.expression.ExpressionBuilder;
import org.bonitasoft.engine.expression.InvalidExpressionException;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.condition.conditionModel.Operation_Compare;
import org.bonitasoft.studio.condition.conditionModel.Operation_NotUnary;
import org.bonitasoft.studio.condition.conditionModel.Unary_Operation;
import org.bonitasoft.studio.condition.scoping.ConditionModelGlobalScopeProvider;
import org.bonitasoft.studio.condition.ui.internal.ConditionModelActivator;
import org.bonitasoft.studio.engine.export.expression.converter.IExpressionConverter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Data;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.resource.SynchronizedXtextResourceSet;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.StringInputStream;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;

import com.google.inject.Injector;

/**
 * @author Romain Bioteau
 *
 */
public class ComparisonExpressionConverter implements IExpressionConverter {


    @Override
    public boolean appliesTo(final Expression expression) {
        return expression != null && ExpressionConstants.CONDITION_TYPE.equals(expression.getType());
    }

    @Override
    public org.bonitasoft.engine.expression.Expression convert(final Expression expression) throws InvalidExpressionException {
        final ExpressionBuilder expressionBuilder = new ExpressionBuilder();
        final String name = getExpressionName(expression);
        final String content = expression.getContent();
        final Operation_Compare compare = parseConditionExpression(content, expression.eContainer());
        if (compare != null && compare.getOp() != null) {
            final EObject op = compare.getOp();
            if (op instanceof Unary_Operation) {
                return createExpressionForUnaryOperation(expression, expressionBuilder, name, op);
            } else if (op instanceof org.bonitasoft.studio.condition.conditionModel.Operation) {
                return createExpressionForBinaryOperation(expression, expressionBuilder, name, op);
            }
        }
        return null;

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
        if (op instanceof Operation_NotUnary) {
            return expressionBuilder.createLogicalComplementExpression(name, engineExpression);
        } else {
            return engineExpression;
        }
    }

    public Operation_Compare parseConditionExpression(final String content, final EObject context) {
        final Injector injector = ConditionModelActivator.getInstance().getInjector(
                ConditionModelActivator.ORG_BONITASOFT_STUDIO_CONDITION_CONDITIONMODEL);
        final IResourceValidator xtextResourceChecker = injector.getInstance(IResourceValidator.class);
        final XtextResourceSet resourceSet = new SynchronizedXtextResourceSet();
        final XtextResource resource = (XtextResource) resourceSet.createResource(URI.createURI("somefile.cmodel"));
        try {
            resource.load(new StringInputStream(content, "UTF-8"), Collections.emptyMap());
        } catch (final UnsupportedEncodingException e1) {
            BonitaStudioLog.error(e1);
        } catch (final IOException e1) {
            BonitaStudioLog.error(e1);
        }
        final EList<EObject> contents = resource.getContents();
        if (contents.isEmpty()) {
            return null;
        }
        final ConditionModelGlobalScopeProvider globalScopeProvider = injector.getInstance(ConditionModelGlobalScopeProvider.class);
        final List<String> accessibleObjects = new ArrayList<String>();
        for (final Data d : ModelHelper.getAccessibleData(context)) {
            accessibleObjects.add(ModelHelper.getEObjectID(d));
        }

        final AbstractProcess process = ModelHelper.getParentProcess(context);
        if (process != null) {
            for (final Parameter p : process.getParameters()) {
                accessibleObjects.add(ModelHelper.getEObjectID(p));
            }
        }
        globalScopeProvider.setAccessibleEObjects(accessibleObjects);
        xtextResourceChecker.validate(resource, CheckMode.FAST_ONLY, null);
        return (Operation_Compare) resource.getContents().get(0);
    }

    protected String getExpressionName(final Expression expression) {
        String name = expression.getName();
        if (name == null || name.isEmpty()) {
            name = "<empty-name>";
        }
        return name;
    }

}
