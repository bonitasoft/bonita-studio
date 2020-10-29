/**
 * Copyright (C) 2009 BonitaSoft S.A.
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

package org.bonitasoft.studio.groovy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.bonitasoft.engine.expression.ExpressionConstants;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.bonitasoft.studio.model.process.MultiInstanceType;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.SourceElement;
import org.bonitasoft.studio.model.process.StartTimerEvent;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * @author Romain Bioteau
 */
public class GroovyUtil {

    public static final String GROOVY_PREFIX = "${"; //$NON-NLS-1$

    public static final String GROOVY_SUFFIX = "}"; //$NON-NLS-1$

    public static final String GROOVY_CONSTANT_SEPARATOR = "\'";

    private static Map<String, Expression> expressions;

    private static final ViewerFilter NO_FILTER = new ViewerFilter() {

        @Override
        public boolean select(Viewer viewer, Object parentElement, Object element) {
            return true;
        }
    };

    public static List<ExpressionConstants> getBonitaConstantsFor(
            EObject context, final ViewerFilter[] filters, final boolean isPageFlowContext) {
        final List<ExpressionConstants> result = new ArrayList<>();
        ViewerFilter engineFilter = filters != null ? Stream.of(filters)
                .filter(DisplayEngineExpressionWithName.class::isInstance)
                .findFirst()
                .orElse(NO_FILTER) : NO_FILTER;

        result.add(ExpressionConstants.API_ACCESSOR);
        result.add(ExpressionConstants.PROCESS_DEFINITION_ID);
        result.add(ExpressionConstants.ROOT_PROCESS_INSTANCE_ID);
        result.add(ExpressionConstants.PROCESS_INSTANCE_ID);
        result.add(ExpressionConstants.ACTIVITY_INSTANCE_ID);

        if (context instanceof Expression || context instanceof Operation) {
            context = context.eContainer();
        }
        if (isPageFlowContext) {
            result.add(ExpressionConstants.LOGGED_USER_ID);
        }
        if (context instanceof Activity) {
            if (((Activity) context).getType() == MultiInstanceType.PARALLEL
                    || ((Activity) context).getType() == MultiInstanceType.SEQUENTIAL) {
                if (engineFilter != null) {
                    if (engineFilter.select(null, null,
                            ExpressionConstants.NUMBER_OF_ACTIVE_INSTANCES.getEngineConstantName())) {
                        result.add(ExpressionConstants.NUMBER_OF_ACTIVE_INSTANCES);
                    }
                    if (engineFilter.select(null, null,
                            ExpressionConstants.NUMBER_OF_TERMINATED_INSTANCES.getEngineConstantName())) {
                        result.add(ExpressionConstants.NUMBER_OF_TERMINATED_INSTANCES);
                    }
                    if (engineFilter.select(null, null,
                            ExpressionConstants.NUMBER_OF_COMPLETED_INSTANCES.getEngineConstantName())) {
                        result.add(ExpressionConstants.NUMBER_OF_COMPLETED_INSTANCES);
                    }
                    if (engineFilter.select(null, null, ExpressionConstants.NUMBER_OF_INSTANCES.getEngineConstantName())) {
                        result.add(ExpressionConstants.NUMBER_OF_INSTANCES);
                    }
                }
            } else if (((Activity) context).getType() == MultiInstanceType.STANDARD) {
                if (engineFilter != null
                        && engineFilter.select(null, null, ExpressionConstants.LOOP_COUNTER.getEngineConstantName())) {
                    result.add(ExpressionConstants.LOOP_COUNTER);
                }
            }
        }

        if (context instanceof Task) {
            result.add(ExpressionConstants.TASK_ASSIGNEE_ID);
        }

        return result;
    }

    public static List<String> getBonitaKeyWords(final EObject context, final ViewerFilter[] filters,
            final boolean isPageFlowContext) {
        final List<ExpressionConstants> bonitaConstantsFor = getBonitaConstantsFor(context, filters, isPageFlowContext);
        final ArrayList<String> result = new ArrayList<>(
                bonitaConstantsFor.size());
        for (final ExpressionConstants expressionConstants : bonitaConstantsFor) {
            result.add(expressionConstants.getEngineConstantName());
        }
        return result;
    }

    private static void addBonitaVariables(final List<ScriptVariable> result,
            final EObject element, final ViewerFilter[] filters, final boolean isPageFlowContext) {
        final List<ExpressionConstants> bonitaConstantsFor = getBonitaConstantsFor(element, filters, isPageFlowContext);
        for (final ExpressionConstants expressionConstants : bonitaConstantsFor) {
            final ScriptVariable scriptVariable = new ScriptVariable(expressionConstants.getEngineConstantName(),
                    getEngineExpressionReturnType(expressionConstants.getEngineConstantName()));
            scriptVariable.setCategory(org.bonitasoft.studio.common.ExpressionConstants.ENGINE_CONSTANT_TYPE);
            result.add(scriptVariable);
        }
    }

    public static Expression getEngineConstantExpression(final String name) {
        if (expressions == null) {
            final ExpressionConstants[] values = ExpressionConstants.values();
            expressions = new HashMap<>(values.length);
            for (final ExpressionConstants constant : values) {
                expressions.put(constant.getEngineConstantName(),
                        createExpression(constant));
            }
        }
        return expressions.get(name);
    }

    private static Expression createExpression(
            final ExpressionConstants engineConstant) {
        final Expression exp = ExpressionFactory.eINSTANCE.createExpression();
        exp.setType(org.bonitasoft.studio.common.ExpressionConstants.ENGINE_CONSTANT_TYPE);
        exp.setContent(engineConstant.getEngineConstantName());
        exp.setName(engineConstant.getEngineConstantName());
        exp.setReturnType(getEngineExpressionReturnType(engineConstant.getEngineConstantName()));
        return exp;
    }

    public static boolean isGroovyExpression(final String text) {
        return text.startsWith(GROOVY_PREFIX) && text.endsWith(GROOVY_SUFFIX);
    }

    public static String toSimpleExpression(String expression) {
        if (isGroovyExpression(expression)) {
            expression = expression.substring(expression.indexOf(GROOVY_PREFIX)
                    + GROOVY_PREFIX.length(),
                    expression.lastIndexOf(GROOVY_SUFFIX));
        }
        return expression;
    }

    /**
     * @param expression
     * @return
     */
    public static String toGroovyExpression(final String expression) {
        if (isGroovyExpression(expression)) {
            return expression;
        } else {
            return GROOVY_PREFIX + expression + GROOVY_SUFFIX;
        }
    }

    public static List<ScriptVariable> getBonitaVariables(final EObject element, final ViewerFilter[] filters,
            final boolean isPageFlowContext) {
        final List<ScriptVariable> result = new ArrayList<>();
        addBonitaVariables(result, element, filters, isPageFlowContext);
        return result;
    }

    /**
     * Helper method to retrieve the constant field of Custom Groovy Type
     *
     * @param className
     * @return list of String (the values)
     */
    public static List<String> getTypeValues(final String className) {

        final List<String> result = new ArrayList<>();
        try {
            final IType t = getType(className);
            if (t == null) {
                return result;
            }
            for (final IField f : t.getFields()) {
                final String fieldSource = f.getSource();
                if (fieldSource != null
                        && fieldSource.indexOf(GROOVY_CONSTANT_SEPARATOR) != -1) {
                    result.add(fieldSource.substring(
                            fieldSource.indexOf(GROOVY_CONSTANT_SEPARATOR) + 1,
                            fieldSource.lastIndexOf(GROOVY_CONSTANT_SEPARATOR))); // )
                }
            }
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
        return result;
    }

    public static IType getType(final String className)
            throws JavaModelException {
        final IJavaProject project = RepositoryManager.getInstance()
                .getCurrentRepository().getJavaProject();
        final IType t = project.findType(className);
        return t;
    }

    public static boolean isMultipleData(final Element container,
            String inputScript) {
        if (inputScript != null && inputScript.trim().length() > 0) {
            if (inputScript.startsWith(GROOVY_PREFIX)) {
                inputScript = inputScript
                        .substring(2, inputScript.length() - 1);
            }

            for (final Data d : ModelHelper.getAccessibleData(container)) {
                if (d.getName().equals(inputScript)) {
                    if (d.isMultiple()
                            || d instanceof JavaObjectData
                                    && ((JavaObjectData) d).getClassName().equals(
                                            "java.util.List")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean containsAGroovyExpression(final String expression) {
        final int begin = expression.indexOf(GROOVY_PREFIX);
        int end = -1;
        if (begin >= 0) {
            end = begin
                    + getGroovyExpressionEndIndex(expression.substring(begin));
        }
        return begin < end;
    }

    public static int getGroovyExpressionEndIndex(final String expression) {
        int open = 0;
        final char[] characters = expression.toCharArray();
        for (int i = 1; i < characters.length; i++) {
            if (characters[i] == '{') {
                open++;
            } else if (characters[i] == '}') {
                open--;
            }
            if (open == 0) {
                return i + 1;
            }
        }
        return -1;
    }

    public static ScriptVariable createScriptVariable(final Data d) {
        return new ScriptVariable(d.getName(),
                org.bonitasoft.studio.common.DataUtil.getTechnicalTypeFor(d), 
                null, d.getDocumentation());
    }

    public static ScriptVariable createScriptVariable(final Output output) {
        return new ScriptVariable(output.getName(), output.getType());
    }

    public static List<ScriptVariable> createScriptVariablesFromFormElement(
            final Element elem) {
        if (elem == null || elem instanceof StartTimerEvent) {
            return new ArrayList<>();
        }

        final List<Data> datas = new ArrayList<>();

        final AbstractProcess process = ModelHelper.getParentProcess(elem);
        final List<ScriptVariable> result = new ArrayList<>();

        if (elem instanceof Connection) {
            final SourceElement flowElem = ((SequenceFlow) elem).getSource();
            if (flowElem instanceof DataAware) {
                datas.addAll(((DataAware) flowElem).getData());
            }

        } else if (elem instanceof DataAware) {
            datas.addAll(((DataAware) elem).getData());
        }

        final List<String> dataNames = new ArrayList<>();

        for (final Data d : datas) {
            dataNames.add(d.getName());
        }

        for (final Data d : process.getData()) {
            if (!dataNames.contains(d.getName())) {
                datas.add(d);
            }
        }

        for (final Data d : datas) {
            final ScriptVariable field = createScriptVariable(d);
            if (field != null) {
                result.add(field);
            }
        }

        return result;
    }

    public static ScriptVariable createScriptVariable(final Parameter p) {
        return new ScriptVariable(p.getName(), p.getTypeClassname(), null, p.getDescription());
    }

    public static ScriptVariable createScriptVariable(final Expression e, final EObject context) {
        if (org.bonitasoft.studio.common.ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE.equals(e.getType())) {
            final ScriptVariable scriptVariable = new ScriptVariable(e.getName(), e.getReturnType(),null, Messages.multiInstanceIteratorDescription);
            scriptVariable.setCategory("step" + org.bonitasoft.studio.common.ExpressionConstants.VARIABLE_TYPE);
            return scriptVariable;
        } else if (org.bonitasoft.studio.common.ExpressionConstants.CONTRACT_INPUT_TYPE
                .equals(e.getType())) {
            final ContractInput input = (ContractInput) e.getReferencedElements().get(0);
            final ScriptVariable scriptVariable = new ScriptVariable(input.getName(), e.getReturnType(), null, input.getDescription());
            scriptVariable.setCategory(org.bonitasoft.studio.common.ExpressionConstants.CONTRACT_INPUT_TYPE);
            return scriptVariable;
        } else if (org.bonitasoft.studio.common.ExpressionConstants.VARIABLE_TYPE.equals(e.getType())) {
            final Data data = (Data) e.getReferencedElements().get(0);
            final ScriptVariable scriptVariable = createScriptVariable(data);
            final AbstractProcess parentProcess = ModelHelper.getParentProcess(context);
            boolean isProcessData = false;
            String type = data instanceof BusinessObjectData
                    ? org.bonitasoft.studio.common.ExpressionConstants.BUSINESS_DATA_TYPE
                    : org.bonitasoft.studio.common.ExpressionConstants.VARIABLE_TYPE;
            if (parentProcess != null) {
                for (final Data d : parentProcess.getData()) {
                    if (d.getName().equals(data.getName())) {
                        isProcessData = true;
                        break;
                    }
                }
                if (isProcessData) {
                    scriptVariable.setCategory("process" + type);
                } else {
                    scriptVariable.setCategory("step" + type);
                }
            } else {
                scriptVariable.setCategory(type);
            }

            return scriptVariable;
        } else if (org.bonitasoft.studio.common.ExpressionConstants.PARAMETER_TYPE
                .equals(e.getType())) {
            final Parameter parameter = (Parameter) e.getReferencedElements()
                    .get(0);
            final ScriptVariable scriptVariable = createScriptVariable(parameter);
            scriptVariable.setCategory(org.bonitasoft.studio.common.ExpressionConstants.PARAMETER_TYPE);
            return scriptVariable;
        } else if (org.bonitasoft.studio.common.ExpressionConstants.CONNECTOR_OUTPUT_TYPE
                .equals(e.getType())) {
            final Output output = (Output) e.getReferencedElements().get(0);
            final ScriptVariable scriptVariable = createScriptVariable(output);
            scriptVariable.setCategory(org.bonitasoft.studio.common.ExpressionConstants.CONNECTOR_OUTPUT_TYPE);
            return scriptVariable;
        } else if (org.bonitasoft.studio.common.ExpressionConstants.ENGINE_CONSTANT_TYPE
                .equals(e.getType())) {
            final ScriptVariable scriptVariable = new ScriptVariable(e.getContent(),
                    getEngineExpressionReturnType(e.getName()));
            scriptVariable.setCategory(org.bonitasoft.studio.common.ExpressionConstants.ENGINE_CONSTANT_TYPE);
            return scriptVariable;
        } else if (org.bonitasoft.studio.common.ExpressionConstants.DOCUMENT_TYPE.equals(e.getType())) {
            final ScriptVariable scriptVariable = new ScriptVariable(e.getContent(), e.getReturnType(), null, expressionDocumentation(e, context));
            scriptVariable.setCategory(org.bonitasoft.studio.common.ExpressionConstants.DOCUMENT_TYPE);
            return scriptVariable;
        } else if (org.bonitasoft.studio.common.ExpressionConstants.DOCUMENT_REF_TYPE.equals(e.getType())) {
            final ScriptVariable scriptVariable = new ScriptVariable(e.getContent(), e.getReturnType(), null, expressionDocumentation(e, context));
            scriptVariable.setCategory(org.bonitasoft.studio.common.ExpressionConstants.DOCUMENT_REF_TYPE);
            return scriptVariable;
        } else if (org.bonitasoft.studio.common.ExpressionConstants.DAO_TYPE.equals(e.getType())) {
            final ScriptVariable scriptVariable = new ScriptVariable(e.getContent(), e.getReturnType());
            scriptVariable.setCategory(org.bonitasoft.studio.common.ExpressionConstants.DAO_TYPE);
            return scriptVariable;
        }
        return null;
    }

    private static String expressionDocumentation(final Expression e, EObject context) {
        if(!e.getReferencedElements().isEmpty()) {
            EObject document = e.getReferencedElements().get(0);
            if(document instanceof Element) {
                Pool process = (Pool) ModelHelper.getParentProcess(context);
               return  process.getDocuments().stream()
                .filter(doc -> e.getName().equals(doc.getName()))
                .map(Document::getDocumentation)
                .findAny()
                .orElse(null);
            }
        }
        return null;
    }

    private static String getEngineExpressionReturnType(final String name) {
        for (final ExpressionConstants exp : ExpressionConstants.values()) {
            if (name.equals(exp.getEngineConstantName())) {
                if ("apiAccessor".equals(name)) {
                    try {
                        final String apiAccessorExtClassName = "com.bonitasoft.engine.api.APIAccessor";
                        ProjectUtil.getConsoleLibsBundle().loadClass(apiAccessorExtClassName);
                        return apiAccessorExtClassName;
                    } catch (final Exception e) {
                        return exp.getReturnType();
                    }
                } else {
                    return exp.getReturnType();
                }
            }
        }
        return null;
    }
}
