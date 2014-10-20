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
package org.bonitasoft.studio.groovy.ui.job;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.groovy.ui.viewer.TestGroovyScriptUtil;
import org.codehaus.groovy.ast.Variable;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.eclipse.codeassist.requestor.CompletionNodeFinder;
import org.codehaus.groovy.eclipse.codeassist.requestor.ContentAssistContext;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.text.Position;


/**
 * @author Romain Bioteau
 *
 */
public class UnknownElementsIndexer extends Job {

    private Set<String> variableScope = new HashSet<String>();
    private final GroovyCompilationUnit groovyCompilationUnit;
    private final Map<String, Position> overridenVariables = new HashMap<String, Position>();
    private final Set<String> unknownVaraibles = new HashSet<String>();

    public UnknownElementsIndexer(final Set<String> variableScope, final GroovyCompilationUnit groovyCompilationUnit) {
        super("Referenced elements indexer");
        setPriority(Job.BUILD);
        setSystem(true);
        setUser(false);
        this.variableScope = variableScope;
        this.groovyCompilationUnit = groovyCompilationUnit;
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    protected IStatus run(final IProgressMonitor monitor) {
        monitor.beginTask("Computing referenced element...", IProgressMonitor.UNKNOWN);
        overridenVariables.clear();
        unknownVaraibles.clear();
        final Map<String, Serializable> result = TestGroovyScriptUtil.createVariablesMap(groovyCompilationUnit, Collections.<ScriptVariable> emptyList());
        final Map<String, Position> declaredVariables = getAllDeclaredVariablesInScript();
        for (final Entry<String, Serializable> entry : result.entrySet()) {
            if (!variableScope.contains(entry.getKey())) {
                addUnknownVaraible(entry.getKey());
            }
        }
        for (final Entry<String, Position> declaredVariable : declaredVariables.entrySet()) {
            if (variableScope.contains(declaredVariable.getKey())) {
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

    protected Map<String, Position> getAllDeclaredVariablesInScript() {
        final Map<String, Position> declaredVariables = new HashMap<String, Position>();
        final CompletionNodeFinder finder = new CompletionNodeFinder(0, 0, 0, "", ""); //$NON-NLS-1$ //$NON-NLS-2$
        final ContentAssistContext assistContext = finder.findContentAssistContext(groovyCompilationUnit);

        org.codehaus.groovy.ast.ASTNode astNode = null;
        if (assistContext != null) {
            astNode = assistContext.containingCodeBlock;
        }

        if (astNode instanceof BlockStatement) {
            final Iterator<Variable> declaredVariablesIterator = ((BlockStatement) astNode).getVariableScope().getDeclaredVariablesIterator();
            while (declaredVariablesIterator.hasNext()) {
                final Variable variable = declaredVariablesIterator.next();
                declaredVariables.put(variable.getName(), new Position(variable.getType().getStart()));
            }
        }
        return declaredVariables;
    }


}
