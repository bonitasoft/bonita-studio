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
package org.bonitasoft.studio.contract.ui.property.constraint.edit.editor;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.contract.i18n.Messages;
import org.eclipse.jdt.core.IProblemRequestor;
import org.eclipse.jdt.core.compiler.IProblem;

public class CompilationProblemRequestor implements IProblemRequestor {

    /*
     * (non-Javadoc)
     * @see org.eclipse.jdt.core.IProblemRequestor#acceptProblem(org.eclipse.jdt.core.compiler.IProblem)
     */
    private final List<IProblem> problems = new ArrayList<IProblem>();

    @Override
    public void acceptProblem(final IProblem problem) {
        problems.add(problem);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jdt.core.IProblemRequestor#beginReporting()
     */
    @Override
    public void beginReporting() {
        problems.clear();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jdt.core.IProblemRequestor#endReporting()
     */
    @Override
    public void endReporting() {

    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jdt.core.IProblemRequestor#isActive()
     */
    @Override
    public boolean isActive() {
        return true;
    }

    public boolean isEmpty() {
        return problems.isEmpty();
    }

    @Override
    public String toString() {
        if (problems.isEmpty()) {
            return super.toString();
        }
        return problems.size() == 1 ? problems.get(0).getMessage() : Messages.bind(Messages.severalCompilationErrors, problems.size());
    }

}
