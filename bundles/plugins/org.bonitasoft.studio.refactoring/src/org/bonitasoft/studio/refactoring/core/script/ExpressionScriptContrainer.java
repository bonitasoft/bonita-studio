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

import org.bonitasoft.studio.common.emf.tools.EMFModelUpdater;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.refactoring.core.RefactorPair;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

public abstract class ExpressionScriptContrainer extends ScriptContainer<Expression> {

    public ExpressionScriptContrainer(final Expression expression, final EAttribute dependencyNameAttribute) {
        super(expression, ExpressionPackage.Literals.EXPRESSION__CONTENT, dependencyNameAttribute);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.refactoring.core.groovy.ScriptContainer#getName()
     */
    @Override
    public String getName() {
        return getModelElement().getName();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.refactoring.core.groovy.ScriptContainer#updateDependencies(java.util.List)
     */
    @Override
    public CompoundCommand updateDependencies(final TransactionalEditingDomain editingDomain,
            final List<? extends RefactorPair<? extends EObject, ? extends EObject>> pairsToRefactor) {
        final CompoundCommand compoundCommand = new CompoundCommand();
        final Expression expression = getModelElement();
        for (final EObject dep : expression.getReferencedElements()) {
            for (final RefactorPair<? extends EObject, ? extends EObject> pair : pairsToRefactor) {
                final String oldValueName = pair.getOldValueName();
                final EClass eClass = pair.getOldValue().eClass();
                if (eClass.equals(dep.eClass()) && oldValueName.equals(dependencyName(dep))) {
                    EMFModelUpdater<EObject> updater = new EMFModelUpdater<>().from(dep);
                    updater.editWorkingCopy(ExpressionHelper.createDependencyFromEObject(pair.getNewValue()));
                    compoundCommand.append(updater.createUpdateCommand(editingDomain));
                }
            }
        }
        return compoundCommand;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.refactoring.core.groovy.ScriptContainer#removeDependencies(java.util.List)
     */
    @Override
    public CompoundCommand removeDependencies(final TransactionalEditingDomain editingDomain,
            final List<? extends RefactorPair<? extends EObject, ? extends EObject>> pairsToRefactor) {
        final CompoundCommand compoundCommand = new CompoundCommand();
        final Expression expression = getModelElement();
        for (final EObject dep : expression.getReferencedElements()) {
            for (final RefactorPair<?, ? extends EObject> pair : pairsToRefactor) {
                final String oldValueName = pair.getOldValueName();
                final EClass eClass = pair.getOldValue().eClass();
                if (eClass.equals(dep.eClass()) && oldValueName.equals(dependencyName(dep))) {
                    final Command removeCmd = RemoveCommand.create(editingDomain, expression,
                            ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS,
                            dep);
                    if (!compoundCommand.getCommandList().contains(removeCmd)) {
                        compoundCommand.append(removeCmd);
                    }
                }
            }
        }
        return compoundCommand;
    }

    private String dependencyName(final EObject dep) {
        return (String) dep.eGet(getDependencyNameFeature());
    }

}
