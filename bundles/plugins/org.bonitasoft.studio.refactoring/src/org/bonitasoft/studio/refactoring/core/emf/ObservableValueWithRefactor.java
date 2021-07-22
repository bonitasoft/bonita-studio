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
package org.bonitasoft.studio.refactoring.core.emf;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.refactoring.core.AbstractRefactorOperation;
import org.bonitasoft.studio.refactoring.core.RefactorPair;
import org.bonitasoft.studio.refactoring.i18n.Messages;
import org.eclipse.emf.databinding.edit.EditingDomainEObjectObservableValue;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Romain Bioteau
 */
public class ObservableValueWithRefactor extends EditingDomainEObjectObservableValue {

    private final IRefactorOperationFactory refactorOperationFactory;
    private final IProgressService progressService;

    public ObservableValueWithRefactor(final EditingDomain editingDomain, final EObject element, final EStructuralFeature feature,
            final IRefactorOperationFactory refactorOperationFactory, final IProgressService progressService) {
        super(editingDomain, element, feature);
        this.refactorOperationFactory = refactorOperationFactory;
        this.progressService = progressService;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.databinding.edit.EditingDomainEObjectObservableValue#doSetValue(java.lang.Object)
     */
    @Override
    protected void doSetValue(final Object value) {
        final Object oldValue = eObject.eGet(eStructuralFeature);
        if (needRefactor(oldValue, value)) {
            final AbstractRefactorOperation<? extends EObject, ? extends EObject, ? extends RefactorPair<? extends EObject, ? extends EObject>> refactorOperation = refactorOperationFactory
                    .createRefactorOperation((TransactionalEditingDomain) domain, eObject, value);
            refactorOperation.getCompoundCommand().append(SetCommand.create(domain, eObject, eStructuralFeature, value));
            try {
                progressService.busyCursorWhile(refactorOperation);
            } catch (InvocationTargetException | InterruptedException e) {
                BonitaStudioLog.error(String.format("Failed to refactor %s into %s", oldValue, value), e);
                openErrorDialog(oldValue, value, e);
            }
        } else {
            super.doSetValue(value);
        }

    }

    private boolean needRefactor(final Object oldValue, final Object newValue) {
        return refactorOperationFactory != null && !oldValue.equals(newValue);
    }

    protected void openErrorDialog(final Object oldValue, final Object newValue, final Exception e) {
        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
                new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.refactorFailedTitle, Messages.bind(Messages.refactorFailedMsg, oldValue,
                        newValue), e).open();
            }
        });
    }

}
