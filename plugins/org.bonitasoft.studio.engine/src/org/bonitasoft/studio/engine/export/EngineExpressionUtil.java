/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.engine.export;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bonitasoft.engine.core.operation.LeftOperand;
import org.bonitasoft.engine.core.operation.LeftOperandBuilder;
import org.bonitasoft.engine.core.operation.OperationBuilder;
import org.bonitasoft.engine.core.operation.OperatorType;
import org.bonitasoft.engine.exception.InvalidExpressionException;
import org.bonitasoft.engine.expression.Expression;
import org.bonitasoft.engine.expression.ExpressionBuilder;
import org.bonitasoft.engine.expression.ExpressionType;
import org.bonitasoft.studio.common.DataUtil;
import org.bonitasoft.studio.common.DatasourceConstants;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.exporter.ExporterTools;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.Data;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * @author Romain Bioteau
 */
public class EngineExpressionUtil {

    public static org.bonitasoft.engine.core.operation.Operation createOperation(final Operation operation) {
        final OperationBuilder builder = new OperationBuilder();
        builder.createNewInstance();
        builder.setType(OperatorType.valueOf(operation.getOperator().getType()));
        builder.setOperator(operation.getOperator().getExpression());
        if (!operation.getOperator().getInputTypes().isEmpty()) {
            builder.setOperatorInputType(operation.getOperator().getInputTypes().get(0));
        }
        builder.setRightOperand(createExpression(operation.getRightOperand()));
        builder.setVariableToSet(createLeftOperand(operation.getLeftOperand()));
        return builder.done();
    }

    /**
     * Hack function because we want only constant in UI but we need a Variable type for the engine
     * 
     * @param operation
     * @return
     */
    @Deprecated
    public static org.bonitasoft.engine.core.operation.Operation createOperationForMessageContent(final Operation operation) {
        final OperationBuilder builder = new OperationBuilder();
        builder.createNewInstance();
        builder.setType(OperatorType.valueOf(operation.getOperator().getType()));
        builder.setOperator(operation.getOperator().getExpression());
        if (!operation.getOperator().getInputTypes().isEmpty()) {
            builder.setOperatorInputType(operation.getOperator().getInputTypes().get(0));
        }
        final org.bonitasoft.studio.model.expression.Expression rightOperand = EcoreUtil.copy(operation.getRightOperand());
        rightOperand.setType(ExpressionConstants.VARIABLE_TYPE);

        builder.setRightOperand(createExpression(rightOperand));
        builder.setVariableToSet(createLeftOperand(operation.getLeftOperand()));
        return builder.done();
    }

    public static List<Expression> createDependenciesList(final org.bonitasoft.studio.model.expression.Expression expression) {
        final List<Expression> result = new ArrayList<Expression>();
        if (expression.getType().equals(ExpressionConstants.SCRIPT_TYPE) || expression.getType().equals(ExpressionConstants.PATTERN_TYPE)) {
            for (final EObject element : expression.getReferencedElements()) {
                if (element instanceof Data) {
                    result.add(createVariableExpression((Data) element));
                } else if (element instanceof Output) {
                    result.add(createConnectorOutputExpression((Output) element));
                } else if (element instanceof Parameter) {
                    result.add(createParameterExpression((Parameter) element));
                } else if (element instanceof org.bonitasoft.studio.model.expression.Expression) {
                    result.add(createExpression((org.bonitasoft.studio.model.expression.Expression) element));
                } else if (element instanceof Widget) {
                    result.add(createWidgetExpression((Widget) element));
                }
            }
        }

        return result;
    }

    /**
     * @param element
     * @return
     * @throws InvalidExpressionException
     */
    private static Expression createWidgetExpression(final Widget element) {
        final ExpressionBuilder exp = new ExpressionBuilder();
        exp.createNewInstance(ExporterTools.FIELD_IDENTIFIER + element.getName());
        exp.setContent(ExporterTools.FIELD_IDENTIFIER + element.getName());
        exp.setExpressionType(ExpressionConstants.FORM_FIELD_TYPE);
        exp.setReturnType(element.getAssociatedReturnType());
        try {
            return exp.done();
        } catch (final InvalidExpressionException e) {// TODO should throw the exception and show it in UI?
            BonitaStudioLog.error(e);
            return null;
        }
    }

    public static Expression createParameterExpression(final Parameter parameter) {
        final ExpressionBuilder exp = new ExpressionBuilder();
        exp.createNewInstance(parameter.getName());
        exp.setContent(parameter.getName());
        exp.setExpressionType(ExpressionType.TYPE_PARAMETER.toString());
        exp.setReturnType(parameter.getTypeClassname());
        try {
            return exp.done();
        } catch (final InvalidExpressionException e) {
            BonitaStudioLog.error(e);
            return null;
        }
    }

    public static LeftOperand createLeftOperand(final org.bonitasoft.studio.model.expression.Expression leftOperand) {
        final LeftOperandBuilder builder = new LeftOperandBuilder();
        builder.createNewInstance();
        builder.setName(leftOperand.getContent());
        return builder.done();
    }

    public static Expression createExpression(final org.bonitasoft.studio.model.expression.AbstractExpression expression) {
        if (expression instanceof org.bonitasoft.studio.model.expression.Expression) {
            return buildSimpleEngineExpression(expression);
        } else if (expression instanceof ListExpression) {
            return buildListEngineExpression(expression);
        } else if (expression instanceof TableExpression) {
            return buildTableEngineExpression(expression);
        } else {
            return null;
        }
    }

    protected static Expression buildTableEngineExpression(final org.bonitasoft.studio.model.expression.AbstractExpression expression) {
        final ExpressionBuilder exp = new ExpressionBuilder();
        final List<List<Expression>> expressions = new ArrayList<List<Expression>>();
        final StringBuilder expressionNames = new StringBuilder("Table of expression containing the following expressions: [");
        for (final ListExpression listExpression : ((TableExpression) expression).getExpressions()) {
            final List<Expression> engineExpressionList = new ArrayList<Expression>();
            expressionNames.append("(");
            for (final org.bonitasoft.studio.model.expression.Expression simpleExpression : listExpression.getExpressions()) {
                final Expression createExpression = createExpression(simpleExpression);
                engineExpressionList.add(createExpression);
                expressionNames.append(createExpression.getName());
                expressionNames.append(",");
            }
            expressionNames.append(")");
            expressions.add(engineExpressionList);
        }
        expressionNames.append("].");
        try {
            return exp.createListOfListExpression(expressionNames.toString(), expressions);
        } catch (final InvalidExpressionException e) {
            BonitaStudioLog.error(e);
            return null;
        }
    }

    protected static Expression buildListEngineExpression(final org.bonitasoft.studio.model.expression.AbstractExpression expression) {
        final ExpressionBuilder exp = new ExpressionBuilder();
        final List<Expression> expressions = new ArrayList<Expression>();
        final StringBuilder expressionNames = new StringBuilder("List of expression containing the following expressions: (");
        for (final org.bonitasoft.studio.model.expression.Expression simpleExpression : ((ListExpression) expression).getExpressions()) {
            final Expression createExpression = createExpression(simpleExpression);
            expressions.add(createExpression);
            expressionNames.append(createExpression.getName());
            expressionNames.append(",");
        }
        expressionNames.append(").");
        try {
            return exp.createListExpression(expressionNames.toString(), expressions);
        } catch (final InvalidExpressionException e) {
            BonitaStudioLog.error(e);
            return null;
        }
    }

    protected static Expression buildSimpleEngineExpression(final org.bonitasoft.studio.model.expression.AbstractExpression expression) {
        final org.bonitasoft.studio.model.expression.Expression simpleExpression = (org.bonitasoft.studio.model.expression.Expression) expression;
        if (simpleExpression.getContent() != null && !simpleExpression.getContent().isEmpty()) {
            final ExpressionBuilder exp = new ExpressionBuilder();
            String interpreter = simpleExpression.getInterpreter();
            String name = simpleExpression.getName();
            if(name == null || name.isEmpty()){
                name = "<empty-name>";
            }
            exp.createNewInstance(name);
            exp.setContent(simpleExpression.getContent());
            exp.setExpressionType(toEngineExpressionType(simpleExpression));
            exp.setInterpreter(interpreter);
            exp.setReturnType(simpleExpression.getReturnType());
            exp.setDependencies(createDependenciesList(simpleExpression));
            try {
                return exp.done();
            } catch (final InvalidExpressionException e) {
                BonitaStudioLog.error(e);
                return null;
            }
        } else {
            return null;
        }
    }

    private static String toEngineExpressionType(final org.bonitasoft.studio.model.expression.Expression expression) {
        String type = expression.getType();
        if (ExpressionConstants.CONNECTOR_OUTPUT_TYPE.equals(type)) {
            return ExpressionType.TYPE_INPUT.name();
        }
        if (ExpressionConstants.VARIABLE_TYPE.equals(type) &&  !expression.getReferencedElements().isEmpty()) {
            final Data data = (Data) expression.getReferencedElements().get(0);
            final String ds = data.getDatasourceId();
            if (DatasourceConstants.PAGEFLOW_DATASOURCE.equals(ds)) {
                type = ExpressionConstants.FORM_FIELD_TYPE;
            }
        }
        return type;
    }

    public static Expression createConnectorOutputExpression(final Output element) {
        final ExpressionBuilder exp = new ExpressionBuilder();
        exp.createNewInstance(element.getName());
        exp.setContent(element.getName());
        exp.setExpressionType(ExpressionType.TYPE_INPUT.toString());
        exp.setReturnType(element.getType());
        try {
            return exp.done();
        } catch (final InvalidExpressionException e) {
            BonitaStudioLog.error(e);
            return null;
        }
    }

    public static Expression createVariableExpression(final Data element) {
        final String datasourceId = element.getDatasourceId();
        String type = ExpressionConstants.VARIABLE_TYPE;
        if (DatasourceConstants.PAGEFLOW_DATASOURCE.equals(datasourceId)) {
            type = ExpressionConstants.FORM_FIELD_TYPE;
        }
        final ExpressionBuilder exp = new ExpressionBuilder();
        exp.createNewInstance(element.getName());
        exp.setContent(element.getName());
        exp.setExpressionType(type);
        exp.setReturnType(DataUtil.getTechnicalTypeFor(element));
        try {
            return exp.done();
        } catch (final InvalidExpressionException e) {
            BonitaStudioLog.error(e);
            return null;
        }
    }

    public static Expression createEmptyListExpression() {
        final ExpressionBuilder exp = new ExpressionBuilder();
        exp.createNewInstance("<empty-name>");
        exp.setContent("[]");
        exp.setExpressionType(ExpressionConstants.SCRIPT_TYPE);
        exp.setInterpreter(ExpressionConstants.GROOVY);
        exp.setReturnType(Collection.class.getName());
        exp.setDependencies(new ArrayList<Expression>());
        try {
            return exp.done();
        } catch (final InvalidExpressionException e) {
            BonitaStudioLog.error(e);
            return null;
        }
    }

}
