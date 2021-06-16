/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Romain Bioteau
 */
public class EMFEditWithRefactorObservables extends EMFEditObservables {

    public static DetailObservableValueWithRefactor observeDetailValueWithRefactor(
            final Realm realm,
            final IObservableValue value,
            final EStructuralFeature eStructuralFeature) {
        return new DetailObservableValueWithRefactor(value, valueWithRefactorFactory(realm, eStructuralFeature), eStructuralFeature);
    }

    public static ObservableValueWithRefactor observeValueWithRefactor(
            final EditingDomain editingDomain,
            final EObject target,
            final EStructuralFeature eStructuralFeature,
            final IRefactorOperationFactory refactorOperationFactory,
            final IProgressService progressService) {
        return new ObservableValueWithRefactor(editingDomain, target, eStructuralFeature, refactorOperationFactory, progressService);
    }

    public static IObservableFactory valueWithRefactorFactory(final Realm realm, final EStructuralFeature eStructuralFeature) {
        return new IObservableFactory()
        {

            @Override
            public IObservable createObservable(final Object target)
            {
                final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(target);
                return new EditingDomainEObjectObservableValueWithRefactoring(realm, editingDomain, (EObject) target, eStructuralFeature);
            }
        };
    }

}
