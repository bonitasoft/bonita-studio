/**
 * Copyright (C) 2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.contract.core.constraint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractInput;
import org.codehaus.groovy.ast.Variable;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * @author Romain Bioteau
 */
public class ConstraintInputIndexer extends Job {

    private List<ContractInput> inputs = new ArrayList<ContractInput>();
    private final GroovyCompilationUnit groovyCompilationUnit;
    private final Set<String> referencedInputs = new HashSet<String>();
    private final ContractConstraint constraint;

    public ConstraintInputIndexer(final ContractConstraint constraint, final List<ContractInput> availableInputs,
            final GroovyCompilationUnit groovyCompilationUnit) {
        super("Constraint inputs indexer");
        setPriority(Job.BUILD);
        setSystem(true);
        setUser(false);
        inputs = availableInputs;
        this.constraint = constraint;
        this.groovyCompilationUnit = groovyCompilationUnit;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    protected IStatus run(final IProgressMonitor monitor) {
        if (monitor != null) {
            monitor.beginTask("Computing referenced inputs...", IProgressMonitor.UNKNOWN);
        }
        referencedInputs.clear();
        if (groovyCompilationUnit.getModuleNode() != null) {
            BlockStatement astNode = groovyCompilationUnit.getModuleNode().getStatementBlock();
            if (astNode instanceof BlockStatement) {
                final BlockStatement blockStatement = (BlockStatement) astNode;
                addRefrencedInputs(blockStatement);
            }
        }
        constraint.getInputNames().clear();
        constraint.getInputNames().addAll(getReferencedInputs());
        return Status.OK_STATUS;
    }

    protected void addRefrencedInputs(final BlockStatement blockStatement) {
        final Iterator<Variable> referencedClassVariablesIterator = blockStatement.getVariableScope().getReferencedClassVariablesIterator();
        while (referencedClassVariablesIterator.hasNext()) {
            final Variable variable = referencedClassVariablesIterator.next();
            for (final ContractInput in : inputs) {
                if (in.getName().equals(variable.getName())) {
                    referencedInputs.add(variable.getName());
                }
            }
        }
    }

    public Set<String> getReferencedInputs() {
        return referencedInputs;
    }

}
