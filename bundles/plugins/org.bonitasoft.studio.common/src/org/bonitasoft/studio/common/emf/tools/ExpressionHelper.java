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
package org.bonitasoft.studio.common.emf.tools;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import org.bonitasoft.engine.bpm.document.DocumentValue;
import org.bonitasoft.studio.common.DataUtil;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.BooleanType;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.BusinessObjectType;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.DateType;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.DoubleType;
import org.bonitasoft.studio.model.process.EnumType;
import org.bonitasoft.studio.model.process.IntegerType;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.bonitasoft.studio.model.process.JavaType;
import org.bonitasoft.studio.model.process.LongType;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.MultiInstantiable;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.SearchIndex;
import org.bonitasoft.studio.model.process.StringType;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * @author Romain Bioteau
 */
public class ExpressionHelper {

    protected static final String EMPTY_LIST_NAME = Messages.emptyListExpressionName;
    protected static final String EMPTY_LIST_CONTENT = "[]";

    private ExpressionHelper() {

    }

    public static Expression createExpressionFromEnumType(final EnumType type) {
        final Expression generatedExp = ExpressionFactory.eINSTANCE.createExpression();
        generatedExp.setInterpreter(ExpressionConstants.GROOVY);
        generatedExp.setType(ExpressionConstants.SCRIPT_TYPE);
        generatedExp.setName(type.getName() + " values");
        final StringBuilder script = new StringBuilder("[");
        for (final String lit : type.getLiterals()) {
            script.append("\"" + lit + "\"");
            script.append(",");
        }
        if (script.length() > 1) {
            script.delete(script.length() - 1, script.length());
        }
        script.append("]");
        generatedExp.setContent(script.toString());
        generatedExp.setReturnType(List.class.getName());
        return generatedExp;
    }

    public static Expression createGroovyScriptExpression(String expressionContent, String returnType) {
        return createGroovyScriptExpression(expressionContent, returnType, "ExpressionForEvaluation");
    }

    public static Expression createGroovyScriptExpression(String expressionContent, String returnType,
            String scriptName) {
        Expression exp = ExpressionFactory.eINSTANCE.createExpression();
        exp.setName(scriptName);
        exp.setInterpreter(ExpressionConstants.GROOVY);
        exp.setType(ExpressionConstants.SCRIPT_TYPE);
        exp.setContent(expressionContent);
        exp.setReturnType(returnType);
        return exp;
    }

    public static Expression createExpressionFromDocument(final Document document) {
        final Expression exp = ExpressionFactory.eINSTANCE.createExpression();
        exp.setName(document.getName());
        exp.setType(ExpressionConstants.CONSTANT_TYPE);
        exp.setContent(document.getName());
        exp.setReturnType(String.class.getName());
        return exp;
    }

    public static Expression createConstantExpression(final String content, final String returnClassName) {
        final Expression exp = ExpressionFactory.eINSTANCE.createExpression();
        exp.setType(ExpressionConstants.CONSTANT_TYPE);
        exp.setName(content);
        exp.setContent(content);
        exp.setReturnType(returnClassName);
        return exp;
    }

    public static Expression createFormReferenceExpression(final String formName, final String formId) {
        final Expression exp = ExpressionFactory.eINSTANCE.createExpression();
        exp.setType(ExpressionConstants.FORM_REFERENCE_TYPE);
        exp.setName(formName);
        exp.setContent(formId);
        exp.setReturnType(String.class.getName());
        exp.setReturnTypeFixed(true);
        return exp;
    }

    public static Expression createConstantExpression(final String name, final String content,
            final String returnClassName) {
        final Expression exp = createConstantExpression(content, returnClassName);
        exp.setName(name);
        return exp;
    }

    public static EObject createDependencyFromEObject(final EObject dependency) {
        if (dependency instanceof Data) {
            return createDataDependency(dependency);
        }
        if (dependency instanceof Document) {
            return createDocumentDependency(dependency);
        }
        if (dependency instanceof SearchIndex) {
            return createSearchIndexDependency(dependency);
        }
        if (dependency instanceof ContractInput) {
            return createContractInputDependency(dependency);
        }
        return EcoreUtil.copy(dependency);
    }

    private static EObject createContractInputDependency(EObject dependency) {
        final ContractInput contractInputDependency = (ContractInput) EcoreUtil.copy(dependency);
        if (contractInputDependency.getType() == ContractInputType.COMPLEX) {
            contractInputDependency.getInputs().clear();
        }
        contractInputDependency.setDataReference(null);
        return contractInputDependency;
    }

    private static EObject createSearchIndexDependency(final EObject dependency) {
        final SearchIndex searchIndexDependency = (SearchIndex) ProcessFactory.eINSTANCE.create(dependency.eClass());
        final Expression name = ((SearchIndex) dependency).getName();
        if (name != null) {
            final Expression nameExpression = EcoreUtil.copy(name);
            nameExpression.getReferencedElements().clear();
            searchIndexDependency.setName(nameExpression);
        }
        return searchIndexDependency;
    }

    private static EObject createDocumentDependency(final EObject dependency) {
        final Document documentDependency = (Document) ProcessFactory.eINSTANCE.create(dependency.eClass());
        documentDependency.setName(((Document) dependency).getName());
        documentDependency.setMultiple(((Document) dependency).isMultiple());
        return documentDependency;
    }

    private static EObject createDataDependency(final EObject dependency) {
        final Data dataDependency = (Data) EcoreUtil.copy(dependency);
        dataDependency.setDefaultValue(null);
        return dataDependency;
    }

    public static void clearExpression(final Expression expr) {
        Assert.isLegal(expr != null, "Expression cannot be null.");
        expr.setName("");
        expr.setContent("");
        expr.setType(ExpressionConstants.CONSTANT_TYPE);
        expr.getReferencedElements().clear();
        expr.getConnectors().clear();
        if (!expr.isReturnTypeFixed() || expr.getReturnType() == null) {
            expr.setReturnType(String.class.getName());
        }
    }

    public static Expression createEmptyListGroovyScriptExpression() {
        final Expression expression = ExpressionFactory.eINSTANCE.createExpression();
        expression.setType(ExpressionConstants.SCRIPT_TYPE);
        expression.setInterpreter(ExpressionConstants.GROOVY);
        expression.setReturnType(Collection.class.getName());
        expression.setName(EMPTY_LIST_NAME);
        expression.setContent(EMPTY_LIST_CONTENT);
        return expression;
    }

    public static Expression createVariableExpression(final Data data) {
        final Expression expression = ExpressionFactory.eINSTANCE.createExpression();
        expression.setType(ExpressionConstants.VARIABLE_TYPE);
        expression.setReturnType(DataUtil.getTechnicalTypeFor(data));
        expression.setName(data.getName());
        expression.setContent(data.getName());
        expression.getReferencedElements().add(createDependencyFromEObject(data));
        return expression;
    }

    public static Expression createExpression(final String name, final String content, final String type,
            final String returnType, final boolean fixedReturnType) {
        final Expression expression = ExpressionFactory.eINSTANCE.createExpression();
        expression.setType(type);
        expression.setReturnType(returnType);
        expression.setReturnTypeFixed(true);
        expression.setName(name);
        expression.setContent(content);
        return expression;
    }

    public static Expression createExpressionFromEObject(final EObject element) {
        if (element instanceof Data) {
            return createVariableExpression((Data) element);
        } else if (element instanceof Output) {
            return createConnectorOutputExpression((Output) element);
        } else if (element instanceof Parameter) {
            return createParameterExpression((Parameter) element);
        } else if (element instanceof org.bonitasoft.studio.model.expression.Expression) {
            return (Expression) EcoreUtil.copy(element);
        } else if (element instanceof Document) {
            return createDocumentExpressionWithDependency((Document) element);
        } else if (element instanceof ContractInput) {
            return createContractInputExpression((ContractInput) element);
        }
        throw new IllegalArgumentException("element argument is not supported: " + element);
    }

    public static Expression createContractInputExpression(final ContractInput input) {
        final Expression exp = ExpressionFactory.eINSTANCE.createExpression();
        exp.setType(ExpressionConstants.CONTRACT_INPUT_TYPE);
        exp.setContent(input.getName());
        exp.setName(input.getName());
        exp.setReturnType(getContractInputReturnType(input));
        exp.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(input));
        return exp;
    }

    public static String getContractInputReturnType(final ContractInput input) {
        if (input == null) {
            return null;
        }
        String returnType = input.getJavaType();
        if (input.isMultiple()) {
            returnType = List.class.getName();
        }
        return returnType;
    }

    public static Expression createDocumentReferenceExpression(final Document d) {
        final Expression exp = ExpressionFactory.eINSTANCE.createExpression();
        exp.setType(ExpressionConstants.DOCUMENT_REF_TYPE);
        exp.setContent(d.getName());
        exp.setName(d.getName());
        if (d.isMultiple()) {
            exp.setReturnType(List.class.getName());
        } else {
            exp.setReturnType(String.class.getName());
        }
        exp.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(d));
        return exp;
    }

    public static Expression createConnectorOutputExpression(final Output output) {
        final Expression exp = ExpressionFactory.eINSTANCE.createExpression();
        exp.setType(ExpressionConstants.CONNECTOR_OUTPUT_TYPE);
        exp.setContent(output.getName());
        exp.setName(output.getName());
        exp.setReturnType(output.getType());
        exp.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(output));
        return exp;
    }

    public static Expression createParameterExpression(final Parameter p) {
        final Expression exp = ExpressionFactory.eINSTANCE.createExpression();
        exp.setType(ExpressionConstants.PARAMETER_TYPE);
        exp.setContent(p.getName());
        exp.setName(p.getName());
        exp.setReturnType(p.getTypeClassname());
        exp.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(p));
        return exp;
    }

    public static Expression createListDocumentExpressionWithDependency(final String targetDocName) {
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setName(targetDocName);
        document.setMultiple(true);
        final Expression storageExpression = ExpressionFactory.eINSTANCE.createExpression();
        storageExpression.setContent(targetDocName);
        storageExpression.setName(targetDocName);
        storageExpression.setType(ExpressionConstants.DOCUMENT_LIST_TYPE);
        storageExpression.setReturnType(List.class.getName());
        storageExpression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(document));
        return storageExpression;
    }

    public static Expression createDocumentExpressionWithDependency(final Document document) {
        final Expression expression = ExpressionFactory.eINSTANCE.createExpression();
        expression.setContent(document.getName());
        expression.setName(document.getName());
        expression.setType(ExpressionConstants.DOCUMENT_TYPE);
        expression.setReturnType(String.class.getName());
        if (document.isMultiple()) {
            expression.setReturnType(List.class.getName());
        } else {
            expression.setReturnType(org.bonitasoft.engine.bpm.document.Document.class.getName());
        }
        expression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(document));
        return expression;
    }

    public static Operation createDefaultConnectorOutputOperation(final Output output) {
        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        final Operator assignment = ExpressionFactory.eINSTANCE.createOperator();
        assignment.setType(isDocumentValue(output) ? ExpressionConstants.SET_DOCUMENT_OPERATOR
                : ExpressionConstants.ASSIGNMENT_OPERATOR);
        operation.setOperator(assignment);

        final Expression rightOperand = ExpressionFactory.eINSTANCE.createExpression();
        rightOperand.setName(output.getName());
        rightOperand.setContent(output.getName());
        rightOperand.setReturnType(output.getType());
        rightOperand.setType(ExpressionConstants.CONNECTOR_OUTPUT_TYPE);
        rightOperand.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(output));
        operation.setRightOperand(rightOperand);

        final Expression leftOperand = ExpressionFactory.eINSTANCE.createExpression();
        operation.setLeftOperand(leftOperand);
        return operation;
    }

    private static boolean isDocumentValue(Output output) {
        return Objects.equals(DocumentValue.class.getName(), output.getType());
    }

    public static Data dataFromIteratorExpression(final MultiInstantiable parentFlowElement,
            final Expression iteratorExpression, final MainProcess mainProcess) {
        final String returnType = iteratorExpression.getReturnType();
        Data d = null;
        if (returnType != null) {
            final DataType dt = getDataTypeFrom(returnType, mainProcess, parentFlowElement);
            if (dt instanceof BusinessObjectType) {
                d = ProcessFactory.eINSTANCE.createBusinessObjectData();
                ((JavaObjectData) d).setClassName(returnType);
            } else if (dt instanceof JavaType) {
                d = ProcessFactory.eINSTANCE.createJavaObjectData();
                ((JavaObjectData) d).setClassName(returnType);
            } else {
                d = ProcessFactory.eINSTANCE.createData();
            }
            d.setName(iteratorExpression.getName());
            d.setDataType(dt);
        }
        return d;
    }

    private static DataType getDataTypeFrom(final String returnType, final MainProcess mainProcess,
            final MultiInstantiable parentFlowElement) {
        if (parentFlowElement.getCollectionDataToMultiInstantiate() instanceof BusinessObjectData) {
            return mainProcess.getDatatypes().stream()
                    .filter(BusinessObjectType.class::isInstance)
                    .findFirst().orElse(null);
        } else {
            return getDataTypeByClassName(mainProcess, returnType);
        }
    }

    static DataType getDataTypeByClassName(final MainProcess dataTypeContainer, final String returnTypeClassname) {
        Objects.requireNonNull(dataTypeContainer);
        Objects.requireNonNull(returnTypeClassname);
        if (returnTypeClassname.equals(Boolean.class.getName())) {
            return dataTypeContainer.getDatatypes().stream()
                    .filter(BooleanType.class::isInstance)
                    .findFirst()
                    .orElse(null);
        } else if (returnTypeClassname.equals(String.class.getName())) {
            return dataTypeContainer.getDatatypes().stream()
                    .filter(StringType.class::isInstance)
                    .filter(Predicate.not(DateType.class::isInstance))
                    .findFirst()
                    .orElse(null);
        } else if (returnTypeClassname.equals(Double.class.getName())) {
            return dataTypeContainer.getDatatypes().stream()
                    .filter(DoubleType.class::isInstance)
                    .findFirst()
                    .orElse(null);
        } else if (returnTypeClassname.equals(Long.class.getName())) {
            return dataTypeContainer.getDatatypes().stream()
                    .filter(LongType.class::isInstance)
                    .findFirst()
                    .orElse(null);
        } else if (returnTypeClassname.equals(Integer.class.getName())) {
            return dataTypeContainer.getDatatypes().stream()
                    .filter(IntegerType.class::isInstance)
                    .findFirst()
                    .orElse(null);
        } else if (returnTypeClassname.equals(Date.class.getName())) {
            return dataTypeContainer.getDatatypes().stream()
                    .filter(DateType.class::isInstance)
                    .findFirst()
                    .orElse(null);
        } else {
            return dataTypeContainer.getDatatypes().stream()
                    .filter(JavaType.class::isInstance)
                    .findFirst()
                    .orElse(null);
        }
    }

}
