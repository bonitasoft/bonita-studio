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
package org.bonitasoft.studio.groovy.ui.viewer;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.security.auth.login.LoginException;

import org.bonitasoft.engine.bpm.flownode.ActivityInstance;
import org.bonitasoft.engine.bpm.process.ProcessDefinition;
import org.bonitasoft.engine.bpm.process.ProcessInstance;
import org.bonitasoft.engine.expression.ExpressionConstants;
import org.bonitasoft.engine.expression.ExpressionEvaluationException;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.groovy.GroovyDocumentUtil;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.bonitasoft.studio.groovy.ui.dialog.ScriptResultDialog;
import org.codehaus.groovy.GroovyException;
import org.codehaus.groovy.ast.Variable;
import org.codehaus.groovy.ast.VariableScope;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Romain Bioteau
 */
public class TestGroovyScriptUtil {

    public static final String NULL_RESULT = "null"; //$NON-NLS-1$

    public static final String FALSE = "false"; //$NON-NLS-1$

    public static final String TRUE = "true"; //$NON-NLS-1$

    public static final String LIST = "List"; //$NON-NLS-1$

    public static final String OBJECT = "Object"; //$NON-NLS-1$

    public static final String BOOLEAN = "Boolean"; //$NON-NLS-1$

    public static final String DATE = "Date"; //$NON-NLS-1$

    public static final String FLOAT = "Float"; //$NON-NLS-1$

    public static final String INTEGER = "Integer"; //$NON-NLS-1$

    public static final String STRING = "String"; //$NON-NLS-1$

    public static final String LONG = "Long"; //$NON-NLS-1$

    public static final Set<String> PROVIDED_VARIABLES;

    public static List<String> TYPES;

    static {
        TYPES = new ArrayList<String>();
        TYPES.add(STRING);
        TYPES.add(INTEGER);
        TYPES.add(LONG);
        TYPES.add(FLOAT);
        TYPES.add(DATE);
        TYPES.add(BOOLEAN);
        TYPES.add(OBJECT);

        PROVIDED_VARIABLES = new HashSet<String>();
        PROVIDED_VARIABLES.add(ExpressionConstants.API_ACCESSOR.getEngineConstantName());
        PROVIDED_VARIABLES.add(ExpressionConstants.ENGINE_EXECUTION_CONTEXT.getEngineConstantName());
        PROVIDED_VARIABLES.add(ExpressionConstants.ACTIVITY_INSTANCE_ID.getEngineConstantName());
        PROVIDED_VARIABLES.add(ExpressionConstants.PROCESS_DEFINITION_ID.getEngineConstantName());
        PROVIDED_VARIABLES.add(ExpressionConstants.PROCESS_INSTANCE_ID.getEngineConstantName());
        PROVIDED_VARIABLES.add(ExpressionConstants.ROOT_PROCESS_INSTANCE_ID.getEngineConstantName());
        PROVIDED_VARIABLES.add(ExpressionConstants.LOGGED_USER_ID.getEngineConstantName());
    }

    public static Map<String, Serializable> getVariableValues(final GroovyCompilationUnit unit, final List<ScriptVariable> nodes,
            final Map<Label, Control> widgetMap) {
        final Map<String, Serializable> variableMap = createVariablesMap(unit, nodes);

        final Iterator<Entry<String, Serializable>> itVar = variableMap.entrySet().iterator();
        while (itVar.hasNext()) {
            final Entry<String, Serializable> var = itVar.next();

            final Iterator<Entry<Label, Control>> it = widgetMap.entrySet().iterator();
            while (it.hasNext()) {
                final Entry<Label, Control> widget = it.next();

                final String varKey = var.getKey();
                if (varKey.equals(widget.getKey().getText())) {
                    String widgetValueText;
                    if (widget.getValue() instanceof Text) {
                        widgetValueText = ((Text) widget.getValue()).getText();
                    } else {// it is a combo
                        widgetValueText = ((Combo) widget.getValue()).getText();
                    }
                    final String varValueString = var.getValue().toString();
                    /*
                     * if(varKey.equals(GroovyUtil.USER_LOCALE)){
                     * variableMap.put(varKey,new Locale(widgetValueText));
                     * }else
                     */if (varValueString.equals(LONG)) {
                         variableMap.put(varKey, new Long(widgetValueText));
                     } else if (varValueString.equals(INTEGER)) {
                         variableMap.put(varKey, new Integer(widgetValueText));
                     } else if (varValueString.equals(STRING)) {
                         variableMap.put(varKey, widgetValueText);
                     }  else if (varValueString.equals(BOOLEAN)) {
                         variableMap.put(varKey, Boolean.valueOf(widgetValueText));
                     } else if (varValueString.equals(FLOAT)) {
                         variableMap.put(varKey, new Float(widgetValueText));
                     } else if (varValueString.equals(DATE)) {
                         try {
                             variableMap.put(varKey, DateFormat.getInstance().parse(widgetValueText));
                         } catch (final ParseException e) {
                             BonitaStudioLog.error(e);
                             variableMap.put(varKey, new Date());
                         }
                     } else if (varValueString.equals(OBJECT)) {
                         Object res = null;
                         try {
                             try {
                                 res = testScript(widgetValueText, Object.class.getName(), Collections.EMPTY_MAP, Collections.EMPTY_SET);
                             } catch (InvocationTargetException e) {
                                 BonitaStudioLog.error(e);
                             } catch (InterruptedException e) {
                                 BonitaStudioLog.error(e);
                             }
                             if (res instanceof Serializable) {
                                 if(res instanceof Exception){
                                     variableMap.put(varKey, widgetValueText); //Fallback : set string value
                                 }else{
                                     variableMap.put(varKey, (Serializable) res);
                                 }

                             } else {
                                 variableMap.put(varKey, "Not serializable");
                             }
                         } catch (final LoginException e) {
                             // TODO Auto-generated catch block
                             e.printStackTrace();
                         } catch (final ExpressionEvaluationException e) {
                             // TODO Auto-generated catch block
                             e.printStackTrace();
                         } catch (final GroovyException e) {
                             // TODO Auto-generated catch block
                             e.printStackTrace();
                         }

                     } else { // Custom Types which use value from combo
                         variableMap.put(varKey, widgetValueText);
                     }

                }
            }
        }
        return variableMap;
    }

    public static Map<String, Serializable> createVariablesMap(final GroovyCompilationUnit unit, final List<ScriptVariable> nodes) {
        Map<String, Serializable> result = new HashMap<String, Serializable>();
        final VariableScope scope = GroovyDocumentUtil.getVariableScope(unit);
        initializeVariableTypes(unit, scope, result, nodes);// Add process and untyped variable to scope

        final Set<String> keys = result.keySet();
        final List<String> orderedKeys = new ArrayList<String>(keys);

        Collections.sort(orderedKeys);

        final Map<String, Serializable> orderedVariableMap = new LinkedHashMap<String, Serializable>();
        for (final String key : orderedKeys) {
            orderedVariableMap.put(key, result.get(key));
        }

        result = orderedVariableMap;
        return result;
    }

    private static void initializeVariableTypes(final GroovyCompilationUnit unit, VariableScope scope, final Map<String, Serializable> variableMap,
            final List<ScriptVariable> nodes) {
        while (scope != null) {
            for (final Iterator<Variable> varIter = scope.getReferencedClassVariablesIterator(); varIter.hasNext();) {
                final Variable var = varIter.next();
                final ScriptVariable processVariable = getProcessVariable(var.getName(), nodes);
                if (processVariable != null) {
                    String typeName = processVariable.getType();
                    typeName = typeName.substring(typeName.lastIndexOf('.') + 1, typeName.length());
                    if (typeName.equals(ActivityInstance.class.getSimpleName()) || typeName.equals(ProcessInstance.class.getSimpleName())
                            || typeName.equals(ProcessDefinition.class.getSimpleName())) {

                    } else if (TYPES.contains(typeName)) {
                        variableMap.put(var.getName(), typeName);
                    } else {
                        final List<String> typeValues = org.bonitasoft.studio.groovy.GroovyUtil.getTypeValues(typeName);
						if (typeValues != null
                                && !typeValues.isEmpty()) {
                            variableMap.put(var.getName(), LIST);
                        } else {
                            variableMap.put(var.getName(), OBJECT);
                        }
                    }
                } else {// Not a process variable (set default type as Object)
                    final List<String> declaredMethods = GroovyDocumentUtil.getDeclaredMethodsName(unit);
                    if (!declaredMethods.contains(var.getName())
                            && !var.getName().equals("DefaultGroovyMethods") && !GroovyDocumentUtil.getDefaultMethodsName().contains(var.getName())) { //$NON-NLS-1$
                        if (!PROVIDED_VARIABLES.contains(var.getName())) {
                            variableMap.put(var.getName(), OBJECT);
                        }
                    }

                }
            }

            scope = scope.getParent();
        }
    }

    public static ScriptVariable getProcessVariable(final String name, final List<ScriptVariable> nodes) {
        for (final ScriptVariable n : nodes) {
            if (n.getName().equals(name)) {
                return n;
            }
        }
        return null;
    }

    public static Object testScript(final String expression, final String returnType, final Map<String, Serializable> variableMap, Set<IRepositoryFileStore> additionalJars) throws GroovyException,  LoginException, ExpressionEvaluationException, InvocationTargetException, InterruptedException {
        final TestExpressionOperation operation = new TestExpressionOperation();
        operation.setExpression(ExpressionHelper.createGroovyScriptExpression(expression, returnType));
        operation.setContextMap(variableMap);
        operation.setAdditionalJars(additionalJars);
        operation.run(Repository.NULL_PROGRESS_MONITOR);
        return operation.getResult();
    }

    public static void evaluateExpression(final String expressionContent, final String returnType, final Map<String, Serializable> context, final Set<IRepositoryFileStore> additionalJars) {
        final IProgressService service = PlatformUI.getWorkbench().getProgressService();
        final IRunnableWithProgress testRunnable = new IRunnableWithProgress() {

            @Override
            public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                monitor.beginTask(Messages.scriptEvaluation, IProgressMonitor.UNKNOWN);
                Object result = null;
                try {
                    result = TestGroovyScriptUtil.testScript(expressionContent, returnType, context, additionalJars);
                    monitor.done();
                } catch (final Exception e1) {
                    result = e1;
                }

                new ScriptResultDialog(Display.getDefault().getActiveShell(), result).open();
            }
        };
        try {
            service.run(false, false, testRunnable);
        } catch (final Exception e1) {
            BonitaStudioLog.error(e1);
        }
    }
}
