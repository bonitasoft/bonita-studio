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
package org.bonitasoft.studio.refactoring.core.emf;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.databinding.edit.EditingDomainEObjectObservableValue;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * @author Romain Bioteau
 *
 */
public class EditingDomainEObjectObservableValueWithRefactoring extends EditingDomainEObjectObservableValue {

    private Command refactoringCommand;

    public EditingDomainEObjectObservableValueWithRefactoring(final Realm realm, final EditingDomain domain, final EObject eObject,
            final EStructuralFeature eStructuralFeature) {
        super(realm, domain, eObject, eStructuralFeature);
    }

    @Override
    protected void doSetValue(final Object value) {
        final CompoundCommand cc = new CompoundCommand();
        cc.append(SetCommand.create(domain, eObject, eStructuralFeature, value));
        final Command refactoringCommand = getRefactoringCommand();
        if (refactoringCommand != null && refactoringCommand.canExecute()) {
            cc.append(refactoringCommand);
        }
        domain.getCommandStack().execute(cc);
    }

    public Command getRefactoringCommand() {
        return refactoringCommand;
    }

    public void setRefactoringCommand(final Command refactroingCommand) {
        refactoringCommand = refactroingCommand;
    }
}
