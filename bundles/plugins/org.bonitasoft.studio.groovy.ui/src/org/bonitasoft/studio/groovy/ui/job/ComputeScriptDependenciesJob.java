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
package org.bonitasoft.studio.groovy.ui.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.expression.editor.ExpressionEditorService;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.groovy.GroovyUtil;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.groovy.ui.Activator;
import org.bonitasoft.studio.model.expression.Expression;
import org.codehaus.groovy.ast.Variable;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.eclipse.codeassist.requestor.CompletionNodeFinder;
import org.codehaus.groovy.eclipse.codeassist.requestor.ContentAssistContext;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.core.JavaModelException;

/**
 * @author Romain Bioteau
 */
public class ComputeScriptDependenciesJob extends Job {

    private final Map<String, List<EObject>> cache;

    private List<ScriptVariable> nodes;

    private EObject context;

    private GroovyCompilationUnit groovyCompilationUnit;

    public ComputeScriptDependenciesJob(final GroovyCompilationUnit groovyCompilationUnit) {
        super(ComputeScriptDependenciesJob.class.getName());
        cache = new HashMap<String, List<EObject>>();
        this.groovyCompilationUnit = groovyCompilationUnit;
    }

    @Override
    protected IStatus run(final IProgressMonitor monitor) {
        String expression = "";
        try {
            expression = groovyCompilationUnit.getSource();
        } catch (JavaModelException e) {
            return new Status(Status.ERROR, Activator.PLUGIN_ID, e.getMessage(), e);
        }
        if (cache.get(expression) == null) {
            CompletionNodeFinder finder = new CompletionNodeFinder(0, 0, 0, "", ""); //$NON-NLS-1$ //$NON-NLS-2$
            ContentAssistContext assistContext = finder.findContentAssistContext(groovyCompilationUnit);

            org.codehaus.groovy.ast.ASTNode astNode = null;
            if (assistContext != null) {
                astNode = assistContext.containingCodeBlock;
            }
            if (astNode instanceof BlockStatement) {
                BlockStatement blockStatement = (BlockStatement) astNode;
                Set<String> referencedVariable = new HashSet<String>();
                Iterator<Variable> referencedClassVariablesIterator = blockStatement.getVariableScope().getReferencedClassVariablesIterator();
                while (referencedClassVariablesIterator.hasNext()) {
                    Variable variable = (Variable) referencedClassVariablesIterator.next();
                    for (final ScriptVariable f : nodes) {
                        if (f.getName().equals(variable.getName())) {
                            referencedVariable.add(variable.getName());
                        }
                    }

                }
                final List<EObject> deps = new ArrayList<EObject>();
                addDependenciesForFoundVariables(referencedVariable, deps);
                cache.put(expression, deps);
            }

        }
        return Status.OK_STATUS;
    }

    protected void addDependenciesForFoundVariables(
            final Set<String> foundVariable, final List<EObject> deps) {
        variablesloop: for (final String name : foundVariable) {
            final Expression engineConstantExpression = GroovyUtil.getEngineConstantExpression(name);
            if (engineConstantExpression != null) {
                deps.add(EcoreUtil.copy(engineConstantExpression));
                continue variablesloop;
            }
            for (IExpressionProvider provider : ExpressionEditorService.getInstance().getExpressionProviders()) {
                if (provider.isRelevantFor(context)) {
                    for (Expression exp : provider.getExpressions(context)) {
                        if (exp.getName().equals(name) && !exp.getReferencedElements().isEmpty()) {
                            deps.add(ExpressionHelper.createDependencyFromEObject(exp.getReferencedElements().get(0)));
                            continue variablesloop;
                        }
                    }
                }
            }
        }
    }

    public List<EObject> getDependencies(final String expression) {
        return cache.get(expression);
    }

    public List<ScriptVariable> getNodes() {
        return nodes;
    }

    public void setNodes(final List<ScriptVariable> nodes) {
        this.nodes = new ArrayList<ScriptVariable>(nodes);
    }

    public EObject getContext() {
        return context;
    }

    public void setContext(final EObject context) {
        this.context = context;
    }

}
