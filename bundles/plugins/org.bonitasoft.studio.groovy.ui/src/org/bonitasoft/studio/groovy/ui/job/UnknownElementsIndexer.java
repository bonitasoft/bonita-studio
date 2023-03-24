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
package org.bonitasoft.studio.groovy.ui.job;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bonitasoft.studio.groovy.BonitaScriptGroovyCompilationUnit;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.text.Position;

/**
 * @author Romain Bioteau
 *         Job responsible to detect unknown variables in a Groovy script
 */
public class UnknownElementsIndexer extends Job {

    private final BonitaScriptGroovyCompilationUnit groovyCompilationUnit;
    private final Map<String, Position> overridenVariables = new HashMap<String, Position>();
    private final Set<String> unknownVaraibles = new HashSet<String>();

    public UnknownElementsIndexer(final BonitaScriptGroovyCompilationUnit groovyCompilationUnit) {
        super("Unknown elements indexer");
        setPriority(Job.BUILD);
        setSystem(true);
        setUser(false);
        this.groovyCompilationUnit = groovyCompilationUnit;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    protected IStatus run(final IProgressMonitor monitor) {
        monitor.beginTask("Computing unknonwn element...", IProgressMonitor.UNKNOWN);
        overridenVariables.clear();
        unknownVaraibles.clear();

        final BlockStatement statementBlock = groovyCompilationUnit.getModuleNode().getStatementBlock();
        final VariablesVisitor variablesVisitor = new VariablesVisitor(statementBlock.getVariableScope());
        statementBlock.visit(variablesVisitor);
        final Map<String, Position> declaredExpressions = variablesVisitor.getDeclaredExpressions();
        Map<String, ScriptVariable> context = groovyCompilationUnit.getContext();
        for (final String variable : variablesVisitor.getVariableExpressions()) {
            if (!context.containsKey(variable) && !declaredExpressions.keySet().contains(variable)) {
                addUnknownVaraible(variable);
            }
        }
        for (final Entry<String, Position> declaredVariable : declaredExpressions.entrySet()) {
            if (context.containsKey(declaredVariable.getKey())) {
                addOverridenVariable(declaredVariable);
            }
        }
        return Status.OK_STATUS;
    }

    private void addOverridenVariable(final Entry<String, Position> variable) {
        overridenVariables.put(variable.getKey(), variable.getValue());
    }

    private void addUnknownVaraible(final String variable) {
        unknownVaraibles.add(variable);
    }

    public Map<String, Position> getOverridenVariables() {
        return overridenVariables;
    }

    public Set<String> getUnknownVaraibles() {
        return unknownVaraibles;
    }

}
