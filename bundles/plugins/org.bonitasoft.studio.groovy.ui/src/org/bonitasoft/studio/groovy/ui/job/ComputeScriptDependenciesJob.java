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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.expression.editor.ExpressionProviderService;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.groovy.BonitaScriptGroovyCompilationUnit;
import org.bonitasoft.studio.groovy.GroovyUtil;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.groovy.ui.Activator;
import org.bonitasoft.studio.model.expression.Expression;
import org.codehaus.groovy.ast.Variable;
import org.codehaus.groovy.ast.stmt.BlockStatement;
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

    private List<ScriptVariable> nodes = new ArrayList<ScriptVariable>();

    private EObject context;

    private final BonitaScriptGroovyCompilationUnit groovyCompilationUnit;

    public ComputeScriptDependenciesJob(final BonitaScriptGroovyCompilationUnit groovyCompilationUnit) {
        super(ComputeScriptDependenciesJob.class.getName());
        cache = new HashMap<String, List<EObject>>();
        this.groovyCompilationUnit = groovyCompilationUnit;
    }

    @Override
    protected IStatus run(final IProgressMonitor monitor) {
        if (groovyCompilationUnit == null || !groovyCompilationUnit.exists()) {
            return Status.CANCEL_STATUS;
        }
        try {
            findDependencies();
        } catch (final JavaModelException e) {
            return new Status(Status.ERROR, Activator.PLUGIN_ID, e.getMessage(), e);
        }
        return Status.OK_STATUS;
    }

    public List<EObject> findDependencies() throws JavaModelException {
        if (groovyCompilationUnit == null) {
            return Collections.<EObject> emptyList();
        }
        String expression = groovyCompilationUnit.getSource();
        computeDependencies(expression);
        return cache.containsKey(expression) ? cache.get(expression) : Collections.<EObject> emptyList();
    }

    protected List<EObject> computeDependencies(String expression) {
        final List<EObject> deps = new ArrayList<EObject>();
        if (expression != null && cache.get(expression) == null) {
            if (groovyCompilationUnit.getModuleNode() != null) {
                BlockStatement astNode = groovyCompilationUnit.getModuleNode().getStatementBlock();
                if (astNode != null) {
                    final BlockStatement blockStatement = (BlockStatement) astNode;
                    final Set<String> referencedVariable = new HashSet<String>();
                    final Iterator<Variable> referencedClassVariablesIterator = blockStatement.getVariableScope()
                            .getReferencedClassVariablesIterator();
                    while (referencedClassVariablesIterator.hasNext()) {
                        final Variable variable = referencedClassVariablesIterator.next();
                        for (final ScriptVariable f : nodes) {
                            if (f.getName().equals(variable.getName())) {
                                referencedVariable.add(variable.getName());
                            }
                        }

                    }

                    addDependenciesForFoundVariables(referencedVariable, deps);
                    cache.put(expression, deps);
                }
            }
        } else if (cache.get(expression) != null) {
            deps.addAll(cache.get(expression));
        }
        return deps;
    }

    protected void addDependenciesForFoundVariables(
            final Set<String> foundVariable, final List<EObject> deps) {
        variablesloop: for (final String name : foundVariable) {
            final Expression engineConstantExpression = GroovyUtil.getEngineConstantExpression(name);
            if (engineConstantExpression != null) {
                deps.add(EcoreUtil.copy(engineConstantExpression));
                continue variablesloop;
            }
            final Expression daoExpression = getDAOExpression(name);
            if (daoExpression != null) {
                deps.add(EcoreUtil.copy(daoExpression));
                continue variablesloop;
            }
            for (final IExpressionProvider provider : ExpressionProviderService.getInstance().getExpressionProviders()) {
                if (provider.isRelevantFor(context)) {
                    for (final Expression exp : provider.getExpressions(context)) {
                        if (exp.getName().equals(name)) {
                            if (!exp.getReferencedElements().isEmpty()) {
                                deps.add(ExpressionHelper.createDependencyFromEObject(exp.getReferencedElements().get(0)));
                            } else if (ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE.equals(exp.getType())) {
                                deps.add(EcoreUtil.copy(exp));
                            }

                            continue variablesloop;
                        }
                    }
                }
            }
        }
    }

    private Expression getDAOExpression(final String name) {
        final IExpressionProvider daoExpressionProvider = ExpressionProviderService.getInstance()
                .getExpressionProvider(ExpressionConstants.DAO_TYPE);
        if (daoExpressionProvider == null) {
            return null;
        }
        for (final Expression exp : daoExpressionProvider.getExpressions(context)) {
            if (exp.getName().equals(name)) {
                return exp;
            }
        }
        return null;
    }

    public List<EObject> getDependencies(final String expression) {
        if (cache.containsKey(expression)) {
            return cache.get(expression);
        } else {
            return computeDependencies(expression);
        }
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
