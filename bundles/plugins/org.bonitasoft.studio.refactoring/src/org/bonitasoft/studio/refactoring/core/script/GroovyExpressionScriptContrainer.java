/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.refactoring.core.script;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EAttribute;

public class GroovyExpressionScriptContrainer extends ExpressionScriptContrainer {

    private final IScriptRefactoringOperationFactory scriptRefactoringOperationFactory;

    public GroovyExpressionScriptContrainer(final Expression expression, final EAttribute dependencyNameAttribute,
            final IScriptRefactoringOperationFactory scriptRefactoringOperationFactory) {
        super(expression, dependencyNameAttribute);
        this.scriptRefactoringOperationFactory = scriptRefactoringOperationFactory;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.refactoring.core.groovy.ScriptContainer#updateScript(java.util.List)
     */
    @Override
    public void updateScript(final List<ReferenceDiff> referenceDiffs, final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        final IScriptRefactoringOperation groovyScriptRefactoringOperation = scriptRefactoringOperationFactory.createScriptOperationFactory(getScript(),
                referenceDiffs);
        groovyScriptRefactoringOperation.run(monitor);
        setNewScript(groovyScriptRefactoringOperation.getRefactoredScript());
    }

}
