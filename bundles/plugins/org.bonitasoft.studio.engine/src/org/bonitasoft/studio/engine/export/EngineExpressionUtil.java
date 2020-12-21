/**
 * Copyright (C) 2012-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.engine.export;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.engine.expression.Expression;
import org.bonitasoft.engine.expression.ExpressionBuilder;
import org.bonitasoft.engine.expression.ExpressionType;
import org.bonitasoft.engine.expression.InvalidExpressionException;
import org.bonitasoft.engine.operation.LeftOperand;
import org.bonitasoft.engine.operation.LeftOperandBuilder;
import org.bonitasoft.engine.operation.OperationBuilder;
import org.bonitasoft.engine.operation.OperatorType;
import org.bonitasoft.studio.common.DataUtil;
import org.bonitasoft.studio.common.DatasourceConstants;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.model.ModelSearch;
import org.bonitasoft.studio.condition.scoping.ConditionModelGlobalScopeProvider;
import org.bonitasoft.studio.condition.ui.expression.ProjectXtextResourceProvider;
import org.bonitasoft.studio.condition.ui.expression.XtextComparisonExpressionLoader;
import org.bonitasoft.studio.condition.ui.internal.ConditionModelActivator;
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.engine.export.expression.converter.IExpressionConverter;
import org.bonitasoft.studio.engine.export.expression.converter.comparison.ComparisonExpressionConverter;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Document;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.inject.Injector;

/**
 * @author Romain Bioteau
 */
public class EngineExpressionUtil {

    private static List<IExpressionConverter> converters;

    public static org.bonitasoft.engine.operation.Operation createOperation(final Operation operation) {
        return createOperation(operation, createLeftOperand(operation.getLeftOperand()));
    }

    public static void addConverter(IExpressionConverter converter) {
        if (converters == null) {
            converters = new ArrayList<>();
        }
        converters.add(converter);
    }

    public static boolean hasConverter(Class<?> converterType) {
        if (converters == null) {
            return false;
        }
        return converters.stream().anyMatch(converterType::isInstance);
    }

    public static org.bonitasoft.engine.operation.Operation createOperation(final Operation operation,
            final LeftOperand leftOperand) {
        final OperationBuilder builder = new OperationBuilder();
        builder.createNewInstance();
        builder.setType(getEngineOperator(operation));
        builder.setOperator(operation.getOperator().getExpression());
        final EList<String> operatorInputTypes = operation.getOperator().getInputTypes();
        if (!operatorInputTypes.isEmpty()) {
            builder.setOperatorInputType(operatorInputTypes.get(0));
        }
        builder.setRightOperand(createExpression(operation.getRightOperand()));
        builder.setLeftOperand(leftOperand);
        return builder.done();
    }

    public static OperatorType getEngineOperator(final Operation operation) {
        final String type = operation.getOperator().getType();
        return getEngineOperator(type);
    }

    /**
     * @param type in the Studio
     * @return Engine OperatorType
     */
    public static OperatorType getEngineOperator(final String type) {
        //it's the left operand that tell if it's a document to set
        if (OperatorType.DOCUMENT_CREATE_UPDATE.name().equals(type)) {
            return OperatorType.ASSIGNMENT;
        }
        if (ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR.equals(type)) {
            return OperatorType.ASSIGNMENT;//TODO: [BS-9610] remove it if specific operation is provided by engine
        }
        //it's the left operand that tell if it's a string index to set
        if (OperatorType.STRING_INDEX.name().equals(type)) {
            return OperatorType.ASSIGNMENT;
        }
        //it's the left operand that tell if it's a string index to set
        if (ExpressionConstants.CREATE_BUSINESS_DATA_OPERATOR.equals(type)) {
            return OperatorType.ASSIGNMENT;
        }
        if (ExpressionConstants.BUSINESS_DATA_JAVA_SETTER_OPERATOR.equals(type)) {
            return OperatorType.JAVA_METHOD;
        }
        if (ExpressionConstants.ATTACH_EXISTING_BUSINESS_DATA.equals(type)) {
            return OperatorType.ASSIGNMENT;
        }
        return OperatorType.valueOf(type);
    }

    public static org.bonitasoft.engine.operation.Operation createOperation(final Operation operation,
            final boolean isExternal) {
        return createOperation(operation, createLeftOperand(operation.getLeftOperand(), isExternal));
    }

    public static String getOperatorType(final Operation operation) {
        if (ExpressionConstants.JAVA_METHOD_OPERATOR.equals(operation.getOperator().getType())
                && operation.getLeftOperand() != null
                && !operation.getLeftOperand().getReferencedElements().isEmpty()
                && operation.getLeftOperand().getReferencedElements().get(0) instanceof BusinessObjectData) {
            return ExpressionConstants.BUSINESS_DATA_JAVA_SETTER_OPERATOR;
        }
        if (ExpressionConstants.ASSIGNMENT_OPERATOR.equals(operation.getOperator().getType())
                && operation.getLeftOperand() != null
                && !operation.getLeftOperand().getReferencedElements().isEmpty()
                && operation.getLeftOperand().getReferencedElements().get(0) instanceof BusinessObjectData) {
            return ExpressionConstants.ATTACH_EXISTING_BUSINESS_DATA;
        }
        return operation.getOperator().getType();
    }

    public static org.bonitasoft.engine.operation.Operation createOperationForMessageContent(final Operation operation) {
        final OperationBuilder builder = new OperationBuilder();
        builder.createNewInstance();
        builder.setType(getEngineOperator(operation));
        builder.setOperator(operation.getOperator().getExpression());
        final EList<String> operatorInputTypes = operation.getOperator().getInputTypes();
        if (!operatorInputTypes.isEmpty()) {
            builder.setOperatorInputType(operatorInputTypes.get(0));
        }
        final org.bonitasoft.studio.model.expression.Expression rightOperand = EcoreUtil.copy(operation.getRightOperand());
        rightOperand.setType(ExpressionConstants.VARIABLE_TYPE);
        if (!operatorInputTypes.isEmpty()) {
            rightOperand.setReturnType(operatorInputTypes.get(0));
        } else {
            rightOperand.setReturnType(operation.getLeftOperand().getReturnType());//use return type of the left operand
        }

        builder.setRightOperand(createExpression(rightOperand));
        builder.setLeftOperand(createLeftOperand(operation.getLeftOperand()));
        return builder.done();
    }

    public static List<Expression> createDependenciesList(final org.bonitasoft.studio.model.expression.Expression expression)
            throws InvalidExpressionException {
        final List<Expression> result = new ArrayList<Expression>();
        if (expression.getType().equals(ExpressionConstants.SCRIPT_TYPE)
                || expression.getType().equals(ExpressionConstants.PATTERN_TYPE)
                || expression.getType().equals(ExpressionConstants.JAVA_TYPE)
                || expression.getType().equals(ExpressionConstants.XPATH_TYPE)) {
            final ExpressionBuilder expBuilder = new ExpressionBuilder();
            for (final EObject element : expression.getReferencedElements()) {
                if (element instanceof Data) {
                    result.add(createVariableExpression((Data) element));
                } else if (element instanceof Output) {
                    result.add(createConnectorOutputExpression((Output) element));
                } else if (element instanceof Parameter) {
                    result.add(createParameterExpression((Parameter) element));
                } else if (element instanceof org.bonitasoft.studio.model.expression.Expression) {
                    result.add(createExpression((org.bonitasoft.studio.model.expression.Expression) element));
                } else if (element instanceof Document) {
                    if (((Document) element).isMultiple()) {
                        result.add(expBuilder.createDocumentListExpression(((Document) element).getName()));
                    } else {
                        result.add(expBuilder.createDocumentReferenceExpression(((Document) element).getName()));
                    }
                } else if (element instanceof ContractInput) {
                    result.add(expBuilder.createContractInputExpression(((ContractInput) element).getName(),
                            ExpressionHelper.getContractInputReturnType((ContractInput) element)));
                }
            }
        }

        return result;
    }

    public static Expression createParameterExpression(final Parameter parameter) {
        final ExpressionBuilder exp = new ExpressionBuilder();
        exp.createNewInstance(parameter.getName());
        exp.setContent(parameter.getName());
        exp.setExpressionType(ExpressionType.TYPE_PARAMETER);
        exp.setReturnType(parameter.getTypeClassname());
        try {
            return exp.done();
        } catch (final InvalidExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    public static LeftOperand createLeftOperand(final org.bonitasoft.studio.model.expression.Expression leftOperand) {
        return createLeftOperand(leftOperand, false);
    }

    public static LeftOperand createLeftOperand(final org.bonitasoft.studio.model.expression.Expression leftOperand,
            final boolean isExternal) {
        final LeftOperandBuilder builder = new LeftOperandBuilder();
        builder.createNewInstance();
        builder.setName(leftOperand.getName());
        builder.setType(getLeftOperandType(leftOperand, isExternal));
        return builder.done();
    }

    public static String getLeftOperandType(final org.bonitasoft.studio.model.expression.Expression leftOperand,
            final boolean external) {
        if (external) {
            return ExpressionConstants.LEFT_OPERAND_EXTERNAL_DATA;
        }
        final String leftOperandType = leftOperand.getType();
        if (ExpressionConstants.DOCUMENT_TYPE.equals(leftOperandType)
                || ExpressionConstants.DOCUMENT_REF_TYPE.equals(leftOperandType)) {
            return getLeftOperandDocumentType(leftOperand);
        }
        if (ExpressionConstants.VARIABLE_TYPE.equals(leftOperandType)) {
            return getLeftOperandVariableType(leftOperand, external);
        }
        if (ExpressionConstants.SEARCH_INDEX_TYPE.equals(leftOperandType)) {
            return ExpressionConstants.LEFT_OPERAND_SEARCH_INDEX;
        }
        return leftOperand.getType();
    }

    private static String getLeftOperandDocumentType(final org.bonitasoft.studio.model.expression.Expression leftOperand) {
        if (!leftOperand.getReferencedElements().isEmpty()) {
            final EObject referencedElement = leftOperand.getReferencedElements().get(0);
            if (referencedElement instanceof Document) {
                if (((Document) referencedElement).isMultiple()) {
                    return ExpressionConstants.LEFT_OPERAND_DOCUMENT_LIST;
                } else {
                    return ExpressionConstants.LEFT_OPERAND_DOCUMENT;
                }
            }
        }
        return ExpressionConstants.LEFT_OPERAND_DOCUMENT;
    }

    private static String getLeftOperandVariableType(final org.bonitasoft.studio.model.expression.Expression leftOperand,
            final boolean external) {
        final EList<EObject> referencedElements = leftOperand.getReferencedElements();
        if (referencedElements != null && !referencedElements.isEmpty()) {
            final EObject referencedElement = referencedElements.get(0);
            if (referencedElement instanceof Data) {
                final Data data = (Data) referencedElement;
                if (data.isTransient()) {
                    return ExpressionConstants.LEFT_OPERAND_TRANSIENT_DATA;
                } else if (data instanceof BusinessObjectData) {
                    return ExpressionConstants.LEFT_OPERAND_BUSINESS_DATA;
                } else if (external || DatasourceConstants.PAGEFLOW_DATASOURCE.equals(data.getDatasourceId())) {
                    return ExpressionConstants.LEFT_OPERAND_EXTERNAL_DATA;
                }
            }
        }
        return ExpressionConstants.LEFT_OPERAND_DATA;
    }

    public static LeftOperand createLeftOperandIndex(final int i) {
        final LeftOperandBuilder builder = new LeftOperandBuilder();
        builder.createNewInstance();
        builder.setName(String.valueOf(i));
        builder.setType(ExpressionConstants.LEFT_OPERAND_SEARCH_INDEX);
        return builder.done();
    }

    public static Expression createExpression(final org.bonitasoft.studio.model.expression.AbstractExpression expression) {
        if (expression == null) {
            return null;
        }
        if (expression instanceof org.bonitasoft.studio.model.expression.Expression) {
            return buildSimpleEngineExpressionWithName(
                    ((org.bonitasoft.studio.model.expression.Expression) expression).getName(),
                    (org.bonitasoft.studio.model.expression.Expression) expression);
        } else if (expression instanceof ListExpression) {
            return buildListEngineExpression(expression);
        } else if (expression instanceof TableExpression) {
            return buildTableEngineExpression(expression);
        }
        throw new IllegalArgumentException("Unsupported expression convertion");
    }

    protected static Expression buildTableEngineExpression(
            final org.bonitasoft.studio.model.expression.AbstractExpression expression) {
        final ExpressionBuilder exp = new ExpressionBuilder();
        final List<List<Expression>> expressions = new ArrayList<List<Expression>>();
        final StringBuilder expressionNames = new StringBuilder(
                "Table of expression containing the following expressions: [");
        for (final ListExpression listExpression : ((TableExpression) expression).getExpressions()) {
            final List<Expression> engineExpressionList = new ArrayList<Expression>();
            expressionNames.append("(");
            for (final org.bonitasoft.studio.model.expression.Expression simpleExpression : listExpression
                    .getExpressions()) {
                final Expression createExpression = createExpression(simpleExpression);
                if (createExpression != null) {
                    engineExpressionList.add(createExpression);
                    expressionNames.append(createExpression.getName());
                    expressionNames.append(",");
                }
            }
            expressionNames.append(")");
            expressions.add(engineExpressionList);
        }
        expressionNames.append("].");
        try {
            return exp.createListOfListExpression(expressionNames.toString(), expressions);
        } catch (final InvalidExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    protected static Expression buildListEngineExpression(
            final org.bonitasoft.studio.model.expression.AbstractExpression expression) {
        final ExpressionBuilder exp = new ExpressionBuilder();
        final List<Expression> expressions = new ArrayList<Expression>();
        final StringBuilder expressionNames = new StringBuilder(
                "List of expression containing the following expressions: (");
        for (final org.bonitasoft.studio.model.expression.Expression simpleExpression : ((ListExpression) expression)
                .getExpressions()) {
            final Expression createExpression = createExpression(simpleExpression);
            if (createExpression != null) {
                expressions.add(createExpression);
                expressionNames.append(createExpression.getName());
                expressionNames.append(",");
            } else {
                final Expression nullExpression = createNullExpression();
                expressions.add(nullExpression);
                expressionNames.append(nullExpression.getName());
                expressionNames.append(",");
            }
        }
        expressionNames.append(").");
        try {
            return exp.createListExpression(expressionNames.toString(), expressions);
        } catch (final InvalidExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    protected static Expression buildSimpleEngineExpressionWithName(String name,
            final org.bonitasoft.studio.model.expression.Expression expression) {
        final ExpressionBuilder expressionBuilder = new ExpressionBuilder();
        if (name == null || name.isEmpty()) {
            name = expression.getName();
            if (name == null || name.isEmpty()) {
                name = "<empty-name>";
            }
        }
        return buildSimpleEngineExpression(expressionBuilder.createNewInstance(name), expression);
    }

    protected static Expression buildSimpleEngineExpression(final ExpressionBuilder expressionBuilder,
            final org.bonitasoft.studio.model.expression.Expression expression) {
        if (!expression.hasContent()) {
            return null;
        }
        final IExpressionConverter converter = getConverter(expression);
        if (converter != null) {
            try {
                return converter.convert(expression);
            } catch (final InvalidExpressionException e) {
                throw new RuntimeException(e);
            }
        }
        final String type = expression.getType();
        if (ExpressionConstants.PATTERN_TYPE.equals(type)) {
            return createPatternExpression(expressionBuilder, expression);
        }
        if (ExpressionConstants.DOCUMENT_TYPE.equals(type)) {
            final EList<EObject> referencedElements = expression.getReferencedElements();
            if (!referencedElements.isEmpty()) {
                final Document document = (Document) referencedElements.get(0);
                if (document.isMultiple()) {
                    return createDocumentListExpression(expressionBuilder, expression);
                }
            }
            return createDocumentExpression(expressionBuilder, expression);
        }
        if (ExpressionConstants.DOCUMENT_LIST_TYPE.equals(type)) {
            return createDocumentListExpression(expressionBuilder, expression);
        }
        if (ExpressionConstants.DOCUMENT_REF_TYPE.equals(type)) {
            return createEngineExpressionForDocumentRef(expressionBuilder, expression);
        }
        if (ExpressionConstants.XPATH_TYPE.equals(type)) {
            return createXPATHExpression(expressionBuilder, expression);
        }
        if (ExpressionConstants.QUERY_TYPE.equals(type)) {
            return createQueryExpression(expressionBuilder, expression);
        } else {
            expressionBuilder.setContent(expression.getContent().replace("\r", "\n"));
            final ExpressionType engineExpressionType = toEngineExpressionType(expression);
            expressionBuilder.setExpressionType(engineExpressionType);
            expressionBuilder.setInterpreter(
                    ExpressionType.TYPE_READ_ONLY_SCRIPT.equals(engineExpressionType) ? expression.getInterpreter() : "");
            expressionBuilder.setReturnType(expression.getReturnType());
            try {
                expressionBuilder.setDependencies(createDependenciesList(expression));
                return expressionBuilder.done();
            } catch (final InvalidExpressionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static IExpressionConverter getConverter(final org.bonitasoft.studio.model.expression.Expression expression) {
        if (converters == null) {
            converters = new ArrayList<IExpressionConverter>();
            if (ConditionModelActivator.getInstance() != null) {
                Injector injector = ConditionModelActivator.getInstance().getInjector(
                        ConditionModelActivator.ORG_BONITASOFT_STUDIO_CONDITION_CONDITIONMODEL);
                converters.add(new ComparisonExpressionConverter(
                        new XtextComparisonExpressionLoader(injector.getInstance(ConditionModelGlobalScopeProvider.class),
                                new ModelSearch(Collections::emptyList), new ProjectXtextResourceProvider(injector))));
            }
        }
        for (final IExpressionConverter converter : converters) {
            if (converter.appliesTo(expression)) {
                return converter;
            }
        }
        return null;
    }

    private static Expression createEngineExpressionForDocumentRef(final ExpressionBuilder expressionBuilder,
            final org.bonitasoft.studio.model.expression.Expression expression) {
        final String content = expression.getContent();
        final EList<EObject> referencedElements = expression.getReferencedElements();
        if (!referencedElements.isEmpty()) {
            final EObject referencedElement = referencedElements.get(0);
            if (referencedElement instanceof Document) {
                if (((Document) referencedElement).isMultiple()) {
                    return createDocumentListExpression(expressionBuilder, expression);
                }
            }
        }
        return createConstantExpression(content, content, String.class.getName());
    }

    private static Expression createQueryExpression(final ExpressionBuilder expressionBuilder,
            final org.bonitasoft.studio.model.expression.Expression simpleExpression) {
        final List<Expression> dependencies = new ArrayList<Expression>();
        for (final EObject param : simpleExpression.getReferencedElements()) {
            if (param instanceof org.bonitasoft.studio.model.expression.Expression) {
                final EList<EObject> referencedElements = ((org.bonitasoft.studio.model.expression.Expression) param)
                        .getReferencedElements();
                if (!referencedElements.isEmpty()) {
                    final Expression paramExpression = buildSimpleEngineExpressionWithName(
                            ((org.bonitasoft.studio.model.expression.Expression) param).getName(),
                            (org.bonitasoft.studio.model.expression.Expression) referencedElements.get(0));
                    if (paramExpression != null) {
                        dependencies.add(paramExpression);
                    }
                }
            }
        }
        try {
            return expressionBuilder.createQueryBusinessDataExpression(simpleExpression.getName(),
                    simpleExpression.getName(),
                    simpleExpression.getReturnType(),
                    dependencies.toArray(new Expression[dependencies.size()]));
        } catch (final InvalidExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    private static Expression createNullExpression() {
        try {
            return new ExpressionBuilder().createGroovyScriptExpression("ExpressionNotDefinedSetAsNull", "null",
                    Object.class.getName());
        } catch (final InvalidExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    public static Expression createDocumentExpression(final ExpressionBuilder exp,
            final org.bonitasoft.studio.model.expression.Expression simpleExpression) {
        Expression expression = null;
        try {
            expression = exp.createDocumentReferenceExpression(simpleExpression.getName());
        } catch (final InvalidExpressionException e) {
            throw new RuntimeException(e);
        }
        return expression;
    }

    private static Expression createDocumentListExpression(final ExpressionBuilder exp,
            final org.bonitasoft.studio.model.expression.Expression simpleExpression) {
        Expression expression = null;
        try {
            expression = exp.createDocumentListExpression(simpleExpression.getName());
        } catch (final InvalidExpressionException e) {
            throw new RuntimeException(e);
        }
        return expression;
    }

    public static Expression createPatternExpression(final ExpressionBuilder exp,
            final org.bonitasoft.studio.model.expression.Expression simpleExpression) {
        exp.createNewInstance("<pattern-expression>");
        exp.setContent(simpleExpression.getContent());
        final ExpressionType engineExpressionType = toEngineExpressionType(simpleExpression);
        exp.setExpressionType(engineExpressionType);
        if (ExpressionType.TYPE_READ_ONLY_SCRIPT == engineExpressionType) {
            exp.setInterpreter(simpleExpression.getInterpreter());
        } else {
            exp.setInterpreter("");
        }
        exp.setReturnType(simpleExpression.getReturnType());
        try {
            final List<Expression> dependenciesList = createDependenciesList(simpleExpression);
            final List<Expression> toRemove = new ArrayList<Expression>();
            for (final Expression expression : dependenciesList) {
                if (!simpleExpression.getContent().contains("${" + expression.getName() + "}")) {
                    toRemove.add(expression);
                }
            }
            dependenciesList.removeAll(toRemove);
            exp.setDependencies(dependenciesList);
            return exp.done();
        } catch (final InvalidExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    static ExpressionType toEngineExpressionType(final org.bonitasoft.studio.model.expression.Expression expression) {
        final String type = expression.getType();
        if (ExpressionConstants.CONNECTOR_OUTPUT_TYPE.equals(type)) {
            return ExpressionType.TYPE_INPUT;
        }
        if (ExpressionConstants.DOCUMENT_REF_TYPE.equals(type)) {
            return toEngineExpressionTypeFoDocumentRef(expression);
        }
        if (ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE.equals(type)) {
            return ExpressionType.TYPE_VARIABLE;
        }
        if (ExpressionConstants.VARIABLE_TYPE.equals(type) && !expression.getReferencedElements().isEmpty()) {
            EObject reference = expression.getReferencedElements().get(0);
            if(!(reference instanceof Data)) {
                throw new RuntimeException(String.format("Incompatible expression type found. Expecting a %s but found a %s for expression %s",
                        Data.class.getSimpleName(),
                        reference.getClass().getSimpleName(),
                        expression.getName()));
            }
            final Data data = (Data) reference;
            final String ds = data.getDatasourceId();
            if (DatasourceConstants.PAGEFLOW_DATASOURCE.equals(ds)) {
                return ExpressionType.TYPE_INPUT;
            }
            if (data.isTransient()) {
                return ExpressionType.TYPE_TRANSIENT_VARIABLE;
            }
            if (data instanceof BusinessObjectData) {
                return ExpressionType.TYPE_BUSINESS_DATA;
            }

        }
        return ExpressionType.valueOf(expression.getType());
    }

    private static ExpressionType toEngineExpressionTypeFoDocumentRef(
            final org.bonitasoft.studio.model.expression.Expression expression) {
        if (!expression.getReferencedElements().isEmpty()) {
            final EObject referencedElement = expression.getReferencedElements().get(0);
            if (referencedElement instanceof Document) {
                if (((Document) referencedElement).isMultiple()) {
                    return ExpressionType.TYPE_DOCUMENT_LIST;
                }
            }
        }
        //Return CONSTANT_TYPE for ensuring backward-compatibility even if Engine has introduced the DOCUMENT_TYPE,
        return ExpressionType.TYPE_CONSTANT;
    }

    public static Expression createConnectorOutputExpression(final Output element) {
        final ExpressionBuilder exp = new ExpressionBuilder();
        exp.createNewInstance(element.getName());
        exp.setContent(element.getName());
        exp.setExpressionType(ExpressionType.TYPE_INPUT);
        exp.setReturnType(element.getType());
        try {
            return exp.done();
        } catch (final InvalidExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    public static Expression createXPATHExpression(final ExpressionBuilder exp,
            final org.bonitasoft.studio.model.expression.Expression expression) {
        try {
            exp.createNewInstance(expression.getName()).setExpressionType(ExpressionType.TYPE_XPATH_READ)
                    .setContent(expression.getContent());
            exp.setReturnType(expression.getReturnType());
            exp.setDependencies(createDependenciesList(expression));
            return exp.done();
        } catch (final InvalidExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    public static Expression createVariableExpression(final Data element) {
        final String datasourceId = element.getDatasourceId();
        ExpressionType type = ExpressionType.TYPE_VARIABLE;
        if (element instanceof BusinessObjectData) {
            type = ExpressionType.TYPE_BUSINESS_DATA;
        }
        if (DatasourceConstants.PAGEFLOW_DATASOURCE.equals(datasourceId)) {
            type = ExpressionType.TYPE_INPUT;
        }
        if (element.isTransient()) {
            type = ExpressionType.TYPE_TRANSIENT_VARIABLE;
        }
        final ExpressionBuilder exp = new ExpressionBuilder();
        exp.createNewInstance(element.getName());
        exp.setContent(element.getName());
        exp.setExpressionType(type);
        exp.setReturnType(DataUtil.getTechnicalTypeFor(element));
        try {
            return exp.done();
        } catch (final InvalidExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    public static Expression createEmptyListExpression() {
        final ExpressionBuilder exp = new ExpressionBuilder();
        exp.createNewInstance("<empty-name>");
        exp.setContent("[]");
        exp.setExpressionType(ExpressionType.TYPE_READ_ONLY_SCRIPT);
        exp.setInterpreter(ExpressionConstants.GROOVY);
        exp.setReturnType(List.class.getName());
        exp.setDependencies(new ArrayList<Expression>());
        try {
            return exp.done();
        } catch (final InvalidExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    public static Expression createConstantExpression(final String name,
            final String content, final String returnType) {
        final ExpressionBuilder exp = new ExpressionBuilder();
        exp.createNewInstance(name);
        exp.setContent(content);
        exp.setExpressionType(ExpressionType.TYPE_CONSTANT);
        exp.setReturnType(returnType);
        try {
            return exp.done();
        } catch (final InvalidExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    protected static boolean isNotEscapeWord(final String content, final int indexOf) {
        if (indexOf - 1 > -1) {
            return content.charAt(indexOf - 1) != '\\';
        }
        return true;
    }

    public static Expression createBusinessObjectDataReferenceExpression(final BusinessObjectData data) {
        try {
            return new ExpressionBuilder().createBusinessDataReferenceExpression(data.getName());
        } catch (final InvalidExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    public static Expression createDocumentExpression(final Document document) {
        Expression documentReferenceExpression = null;
        try {
            if (document.isMultiple()) {
                documentReferenceExpression = new ExpressionBuilder().createDocumentListExpression(document.getName());
            } else {
                documentReferenceExpression = new ExpressionBuilder().createDocumentReferenceExpression(document.getName());
            }
        } catch (final InvalidExpressionException e) {
            throw new RuntimeException(e);
        }
        return documentReferenceExpression;
    }

}
