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

import java.util.List;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.groovy.GroovyCompilationUnitFactory;

public class GroovyScriptRefactoringOperationFactory implements IScriptRefactoringOperationFactory {

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.refactoring.core.script.IScriptRefactoringOperationFactory#createScriptOperationFactory(java.util.List)
     */
    @Override
    public IScriptRefactoringOperation createScriptOperationFactory(final String script, final List<ReferenceDiff> referenceDiffs) {
        return new GroovyScriptRefactoringOperation(script, referenceDiffs, new GroovyCompilationUnitFactory(repositoryAccessor()));
    }

    private RepositoryAccessor repositoryAccessor() {
        return RepositoryManager.getInstance().getAccessor();
    }

}
