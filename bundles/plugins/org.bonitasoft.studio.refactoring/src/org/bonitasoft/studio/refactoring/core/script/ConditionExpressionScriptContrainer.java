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

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

public class ConditionExpressionScriptContrainer extends TextExpressionScriptContainer {

    public ConditionExpressionScriptContrainer(final Expression expression, final EAttribute dependencyNameFeature) {
        super(expression, dependencyNameFeature);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.refactoring.core.groovy.TextExpressionScriptContrainer#applyUpdate()
     */
    @Override
    public CompoundCommand applyUpdate(final EditingDomain editingDomain) {
        final CompoundCommand command = super.applyUpdate(editingDomain);
        if (scriptHasChanged()) {
            command.append(SetCommand.create(editingDomain, getModelElement(), ExpressionPackage.Literals.EXPRESSION__NAME,
                    getNewScript()));
        }
        return command;
    }

}
