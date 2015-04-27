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
package org.bonitasoft.studio.contract.core.refactoring;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @author Romain Bioteau
 */
public class UpdateExpressionCommand extends CompoundCommand implements ExpressionPackage.Literals {

    /**
     * @param editingDomain
     * @param owner the expression to update
     * @param target the expression from which values will be updated
     */
    public UpdateExpressionCommand(final EditingDomain editingDomain, final Expression owner, final Expression target) {
        for (final EStructuralFeature feature : ExpressionPackage.Literals.EXPRESSION.getEAllStructuralFeatures()) {
            append(SetCommand.create(editingDomain, owner, feature, target.eGet(feature)));
        }
    }
}
