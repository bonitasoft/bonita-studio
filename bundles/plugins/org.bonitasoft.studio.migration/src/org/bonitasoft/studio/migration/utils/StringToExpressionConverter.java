/**
 * Copyright (C) 2012-2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.migration.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Model;
import org.w3c.dom.Document;

/**
 * @author Romain Bioteau
 */
public class StringToExpressionConverter {

    private static final String GROOVY_SUFFIX = "}";

    private static final String GROOVY_PREFIX = "${";

    private static final String FIELD_PREFIX = "field_";

    private final Model model;

    private final Map<String, Instance> data = new HashMap<String, Instance>();

    private final Map<String, Instance> parameters = new HashMap<>();

    private final Map<String, Instance> widget = new HashMap<String, Instance>();

    private final Map<String, Instance> documents = new HashMap<String, Instance>();

    private String dataNameToIgnore;

    public StringToExpressionConverter(final Model model,
            final Instance container) {
        Assert.isNotNull(model);
        this.model = model;
        initDataWithProcessData(model, container);
        initParameters(model, container);
        for (final Instance widget : model.getAllInstances("form.Widget")) {
            if (isInScope(container, widget)) {
                this.widget.put(FIELD_PREFIX + widget.get("name"),
                        widget);
            }
        }
        for (final Instance document : model
                .getAllInstances("process.Document")) {
            if (isInScope(container, document)) {
                documents.put((String) document.get("name"), document);
            }
        }
    }

    private void initDataWithProcessData(final Model model,
            final Instance container) {
        for (final Instance data : model.getAllInstances("process.Data")) {
            if (isInScope(container, data) && data.get("dataType") != null) {
                this.data.put((String) data.get("name"), data);
            }
        }
    }

    private void initParameters(final Model model, Instance container) {
        for (final Instance parameter : model.getAllInstances("parameter.Parameter")) {
            if (isInScope(container, parameter)) {
                this.parameters.put((String) parameter.get("name"), parameter);
            }
        }
    }

    public static boolean isInScope(final Instance container,
            final Instance element) {
        Instance current = element;
        while (container != null && current != null && !container.equals(current.getContainer())) {
            current = current.getContainer();
        }
        return container != null && current != null && container.equals(current.getContainer());
    }

    public Instance parseOperation(final Instance groovyScriptInstance,
            final String returnType, final boolean fixedReturnType) {
        String expressionScript = groovyScriptInstance.get("exprScript");
        final String setVarScript = groovyScriptInstance.get("setVarScript");

        if (expressionScript == null || expressionScript.trim().isEmpty()) {
            if (setVarScript != null && !setVarScript.trim().isEmpty()) {
                final Instance widget = getParentWidget(groovyScriptInstance);
                String widgetName = setVarScript;
                if (widget != null) {
                    widgetName = widget.get("name");
                }
                expressionScript = "${" + FIELD_PREFIX
                        + widgetName + "}";
            }
        }

        return parseOperation(returnType, fixedReturnType, expressionScript,
                setVarScript);
    }

    public Instance parseOperation(final String returnType,
            final boolean fixedReturnType, final String expressionScript,
            final String setVarScript) {
        return parseOperation(returnType, fixedReturnType, expressionScript,
                setVarScript, null);
    }

    public Instance parseOperation(final String returnType,
            final boolean fixedReturnType, final String expressionScript,
            final String setVarScript, final String outputType) {
        final Instance operation = model.newInstance("expression.Operation");
        final Instance actionExpression = parse(expressionScript, returnType,
                fixedReturnType, outputType);
        operation.set("rightOperand", actionExpression);

        Instance leftOperand = null;
        boolean isJavaSetter = false;
        String methodCalled = null;
        if (setVarScript != null) {
            String varName = setVarScript;
            Instance dataInstance = null;
            for (final String dataName : data.keySet()) {
                if (varName.equals(dataName)) {
                    dataInstance = data.get(dataName);
                    break;
                }
            }
            if (dataInstance == null && varName.contains("#")) {
                varName = varName.substring(0, varName.indexOf("#"));
            }
            for (final String dataName : data.keySet()) {
                if (varName.equals(dataName)) {
                    dataInstance = data.get(dataName);
                    isJavaSetter = true;
                    break;
                }
            }
            if (dataInstance != null) {
                final String dataReturnType = getDataReturnType(dataInstance);
                leftOperand = parse("${" + varName + "}", dataReturnType, false);
                if (!isJavaSetter) {
                    actionExpression.set("returnType", dataReturnType);
                }
            }

            if (isJavaSetter) {
                final int lastIndexOf = setVarScript.lastIndexOf("#");
                if (lastIndexOf != -1
                        && lastIndexOf + 1 < setVarScript.length()) {
                    methodCalled = setVarScript.substring(lastIndexOf + 1,
                            setVarScript.length());
                }
            }
        }

        if (leftOperand == null) {
            leftOperand = createExpressionInstance(model, "", "",
                    String.class.getName(), String.class.getName(), false);
        }
        operation.set("leftOperand", leftOperand);

        final Instance operator = model.newInstance("expression.Operator");
        if (methodCalled != null) {
            operator.set("type", ExpressionConstants.JAVA_METHOD_OPERATOR);
            operator.set("expression", methodCalled);
            operator.set("inputTypes",
                    getInputTypes((String) actionExpression.get("returnType")));
        } else {
            operator.set("type", ExpressionConstants.ASSIGNMENT_OPERATOR);
        }
        operation.set("operator", operator);

        return operation;
    }

    private List<String> getInputTypes(final String returnType) {
        final List<String> result = new ArrayList<String>();
        result.add(returnType);
        return result;
    }

    public static Instance createOperation(final Model model,
            final Instance leftOperand, final Instance operator,
            final Instance rightOperand) {
        final Instance op = model.newInstance("expression.Operation");
        op.set("rightOperand", rightOperand);
        op.set("operator", operator);
        op.set("leftOperand", leftOperand);
        return op;
    }

    private Instance getParentWidget(final Instance groovyScriptInstance) {
        Instance current = groovyScriptInstance;
        while (current != null && !current.instanceOf("form.Widget")) {
            current = current.getContainer();
        }
        return current;
    }

    public Instance parse(final String stringToParse, final String returnType,
            final boolean fixedReturnType) {
        return parse(stringToParse, returnType, fixedReturnType, null);
    }

    public Instance parse(String stringToParse, String returnType,
            final boolean fixedReturnType, String expressionType) {

        final String originalReturnType = returnType;
        if (returnType == null || returnType.isEmpty()) {// Default return type
            // is String
            returnType = String.class.getName();
        }
        if (stringToParse == null || stringToParse.isEmpty()) { // Returns an
            // empty
            // expression
            return createExpressionInstance(model, null, null, returnType,
                    ExpressionConstants.CONSTANT_TYPE, fixedReturnType);
        }
        stringToParse = stringToParse.trim();
        if (expressionType == null) {
            expressionType = guessExpressionType(stringToParse);
        }
        String content = stringToParse;
        if (isAGroovyString(content)) {
            content = content.substring(2, content.length() - 1);
        }
        if (ExpressionConstants.SCRIPT_TYPE.equals(expressionType)) {
            if (originalReturnType == null) {
                returnType = Object.class.getName();
            }
            final Instance expression = createExpressionInstance(model,
                    "migratedScript", content, returnType,
                    ExpressionConstants.SCRIPT_TYPE, fixedReturnType);
            resolveScriptDependencies(expression);
            return expression;
        } else if (ExpressionConstants.PATTERN_TYPE.equals(expressionType)) {
            final Instance expression = createExpressionInstance(model,
                    content, content, returnType,
                    ExpressionConstants.PATTERN_TYPE, fixedReturnType);
            resolvePatternDependencies(expression);
            return expression;
        } else if (ExpressionConstants.CONNECTOR_OUTPUT_TYPE
                .equals(expressionType)) {

            final Instance expression = createExpressionInstance(model,
                    content, content, returnType,
                    ExpressionConstants.CONNECTOR_OUTPUT_TYPE, fixedReturnType);
            resolveOutputDependencies(expression, content, returnType);
            return expression;
        } else {
            final Instance exp = createExpressionInstance(model, content,
                    content, returnType, expressionType, fixedReturnType);
            if (ExpressionConstants.VARIABLE_TYPE.equals(expressionType)) {
                resolveDataDependencies(exp);
            } else if (ExpressionConstants.DOCUMENT_REF_TYPE
                    .equals(expressionType)) {
                resolveDocumentDependencies(exp);
            }
            return exp;
        }
    }

    private void resolveOutputDependencies(final Instance expression,
            final String name, final String returnType) {
        final Instance instance = model
                .newInstance(ConnectorDefinitionPackage.Literals.OUTPUT);
        instance.set("name", name);
        instance.set("type", returnType);
        expression.add("referencedElements", instance);
    }

    public void resolveDocumentDependencies(final Instance expression) {
        final String content = expression.get("content");
        for (final String documentName : documents.keySet()) {
            if (content.contains(documentName)) {
                final int index = content.indexOf(documentName);
                final boolean validPrefix = isValidPrefix(content, index);
                final boolean validSuffix = isValidSuffix(content,
                        documentName, index);
                if (validPrefix && validSuffix) {
                    final Instance dependencyInstance = documents.get(documentName).copy();
                    final List<Instance> instList = expression
                            .get("referencedElements");
                    if (!dependancyAlreadyExists(instList, dependencyInstance)) {
                        expression.add("referencedElements", dependencyInstance);
                    }
                }
            }
        }
    }

    public void resolvePatternDependencies(final Instance expression) {
        resolvePatternDataDependencies(expression);
        resolvePatternWidgetDependencies(expression);
    }

    private void resolvePatternWidgetDependencies(final Instance expression) {
        final String content = expression.get("content");
        for (final String widgetName : widget.keySet()) {
            if (content.contains("${" + widgetName + "}")) {
                final Instance dependencyInstance = createFormFieldDependencyInstance(widget
                        .get(widgetName));
                expression.add("referencedElements", dependencyInstance);
            }
        }
    }

    private void resolvePatternDataDependencies(final Instance expression) {
        final String content = expression.get("content");
        for (final String dataName : data.keySet()) {
            if (content.contains("${" + dataName + "}")) {
                final Instance dependencyInstance = createVariableDependencyInstance(data
                        .get(dataName));
                final List<Instance> instList = expression
                        .get("referencedElements");
                if (!dependancyAlreadyExists(instList, dependencyInstance)) {
                    expression.add("referencedElements", dependencyInstance);
                }
            }
        }
    }

    public void resolveScriptDependencies(final Instance expression) {
        resolveDataDependencies(expression);
        resolveWidgetDependencies(expression);
    }

    public void resolveWidgetDependencies(final Instance expression) {
        final String content = expression.get("content");
        for (final String widgetName : widget.keySet()) {
            if (content.contains(widgetName)) {
                final int index = content.indexOf(widgetName);
                final boolean validPrefix = isValidWidgetPrefix(content, index);
                final boolean validSuffix = isValidSuffix(content, widgetName,
                        index);
                if (validPrefix && validSuffix) {
                    final Instance dependencyInstance = createFormFieldDependencyInstance(widget
                            .get(widgetName));
                    expression.add("referencedElements", dependencyInstance);
                }
            }
        }
    }

    private boolean isValidWidgetPrefix(final String content, final int index) {
        boolean validPrefix = false;
        if (index > 0) {
            final String prefix = content.substring(index - 1, index);
            if (!Character.isLetter(prefix.toCharArray()[0])) {
                validPrefix = true;
            }
        } else if (index == 0) {
            validPrefix = true;
        }
        return validPrefix;
    }

    /**
     * @param expression
     * @return if a referenced elements has been added
     */
    public boolean resolveDataDependencies(final Instance expression) {
        boolean hasAdded = false;
        String currentDataName = expression.get("content");
        if (currentDataName == null) {
            currentDataName = expression.get("name");
        }
        if (currentDataName != null) {
            for (final String dataName : data.keySet()) {
                if (currentDataName.contains(dataName) && !dataName.equals(dataNameToIgnore)) {
                    final int index = currentDataName.indexOf(dataName);
                    final boolean validPrefix = isValidPrefix(currentDataName, index);
                    final boolean validSuffix = isValidSuffix(currentDataName, dataName, index);
                    if (validPrefix && validSuffix) {
                        final Instance dependencyInstance = createVariableDependencyInstance(data.get(dataName));
                        final List<Instance> instList = expression.get("referencedElements");
                        if (!dependancyAlreadyExists(instList, dependencyInstance)) {
                            expression.add("referencedElements", dependencyInstance);
                            hasAdded = true;
                        }
                    }
                }
            }
        }
        return hasAdded;
    }

    public boolean resolveParameterDependencies(final Instance expression) {
        boolean hasAdded = false;
        String currentParamName = expression.get("content");
        if (currentParamName == null) {
            currentParamName = expression.get("name");
        }
        if (currentParamName != null) {
            for (final String paramName : parameters.keySet()) {
                if (currentParamName.contains(paramName)) {
                    final int index = currentParamName.indexOf(paramName);
                    final boolean validPrefix = isValidPrefix(currentParamName, index);
                    final boolean validSuffix = isValidSuffix(currentParamName, paramName, index);
                    if (validPrefix && validSuffix) {
                        final Instance dependencyInstance = parameters.get(paramName).copy();
                        final List<Instance> instList = expression.get("referencedElements");
                        if (!dependancyAlreadyExists(instList, dependencyInstance)) {
                            expression.add("referencedElements", dependencyInstance);
                            hasAdded = true;
                        }
                    }
                }
            }
        }
        return hasAdded;
    }


    private boolean isValidSuffix(final String currentDataName,
            final String dataName, final int index) {
        boolean validSuffix = false;
        if (index + dataName.length() < currentDataName.length()) {
            final String suffix = currentDataName.substring(
                    index + dataName.length(), index + dataName.length() + 1);
            if (!Character.isLetter(suffix.toCharArray()[0])) {
                validSuffix = true;
            }
        } else if (index + dataName.length() == currentDataName.trim().length()) {
            validSuffix = true;
        }
        return validSuffix;
    }

    private boolean isValidPrefix(final String currentDataName, final int index) {
        boolean validPrefix = false;
        if (index > 0) {
            final String prefix = currentDataName.substring(index - 1, index);
            final char previousChar = prefix.toCharArray()[0];
            if (!Character.isLetter(previousChar) && '_' != previousChar) {
                validPrefix = true;
            }
        } else if (index == 0) {
            validPrefix = true;
        }
        return validPrefix;
    }

    /**
     * Check an Instance already exist in a Instance list
     *
     * @param instList
     * @param dependencyInstance
     * @return
     */
    private boolean dependancyAlreadyExists(final List<Instance> instList,
            final Instance dependencyInstance) {
        final String dataName = dependencyInstance.get("name");
        for (final Instance instance : instList) {
            if (instance.get("name").equals(dataName)) {
                return true;
            }
        }
        return false;
    }

    private Instance createFormFieldDependencyInstance(
            final Instance widgetInstance) {
        final Instance widget = widgetInstance.copy();
        //clean nested content
        for (final Instance content : widget.getContents()) {
            model.delete(content);
        }
        widget.set("dependOn", Collections.emptyList());
        return widget;
    }

    private Instance createVariableDependencyInstance(
            final Instance dataInstance) {
        final Instance copy = dataInstance.copy();
        if (copy.instanceOf("process.Data")) {
            final Object defaultValue = copy.get("defaultValue");
            if (defaultValue != null
                    && defaultValue instanceof Instance
                    && ((Instance) defaultValue)
                    .instanceOf("expression.Expression")) {
                model.delete((Instance) defaultValue);
                copy.set("defaultValue", null);
            }
        }
        return copy;
    }

    private String guessExpressionType(final String stringToParse) {
        if (isAGroovyString(stringToParse)) {
            final String groovyScript = stringToParse.substring(2,
                    stringToParse.length() - 1);
            if (data.containsKey(groovyScript)) {
                return ExpressionConstants.VARIABLE_TYPE;
            } else if (documents.containsKey(groovyScript)) {
                return ExpressionConstants.DOCUMENT_REF_TYPE;
            }
            return ExpressionConstants.SCRIPT_TYPE;
        } else {
            return ExpressionConstants.CONSTANT_TYPE;
        }
    }

    private boolean isAGroovyString(final String stringToParse) {
        return stringToParse.startsWith(GROOVY_PREFIX)
                && stringToParse.endsWith(GROOVY_SUFFIX);
    }

    public static Instance createExpressionInstance(final Model model,
            final String name, final String content, final String returnType,
            final String expresisonType, final boolean fixedReturnType) {
        final Instance instance = model.newInstance("expression.Expression");
        instance.set("name", name);
        instance.set("content", content);
        instance.set("returnType", returnType);
        instance.set("returnTypeFixed", fixedReturnType);
        instance.set("type", expresisonType);
        if (ExpressionConstants.SCRIPT_TYPE.equals(expresisonType)) {
            instance.set("interpreter", ExpressionConstants.GROOVY);
        }
        return instance;
    }

    public static Instance createExpressionInstanceWithDependency(
            final Model model, final String name, final String content,
            final String returnType, final String expresisonType,
            final boolean fixedReturnType, final Instance dependency) {
        final Instance instance = model.newInstance("expression.Expression");
        instance.set("name", name);
        instance.set("content", content);
        instance.set("returnType", returnType);
        instance.set("returnTypeFixed", fixedReturnType);
        instance.set("type", expresisonType);
        if (ExpressionConstants.SCRIPT_TYPE.equals(expresisonType)) {
            instance.set("interpreter", ExpressionConstants.GROOVY);
        }
        if (dependency != null) {
            instance.add("referencedElements", dependency.copy());
        }
        return instance;
    }

    public static String getDataReturnType(final Instance data) {
        if ((Boolean) data.get("multiple")) {
            return List.class.getName();
        }
        final Instance dataype = data.get("dataType");
        if (dataype == null) {
            return String.class.getName();
        }
        if (dataype.instanceOf("process.IntegerType")) {
            return Integer.class.getName();
        } else if (dataype.instanceOf("process.BooleanType")) {
            return Boolean.class.getName();
        } else if (dataype.instanceOf("process.DoubleType")) {
            return Double.class.getName();
        } else if (dataype.instanceOf("process.FloatType")) {
            return Float.class.getName();
        } else if (dataype.instanceOf("process.EnumType")) {
            return String.class.getName();
        } else if (dataype.instanceOf("process.JavaType")) {
            final String returnType = data.get("className");
            if (returnType != null && !returnType.isEmpty()) {
                return returnType;
            }
        } else if (dataype.instanceOf("process.LongType")) {
            return Long.class.getName();
        } else if (dataype.instanceOf("process.XMLType")) {
            return Document.class.getName();
        } else if (dataype.instanceOf("process.DateType")) {
            return Date.class.getName();
        }
        return String.class.getName();
    }

    public static int getStatusForExpression(final Instance expression) {
        return ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type")) ? IStatus.WARNING
                : IStatus.OK;
    }


    public void setDataToIgnore(final String dataName) {
        dataNameToIgnore = dataName;
    }
    
}
