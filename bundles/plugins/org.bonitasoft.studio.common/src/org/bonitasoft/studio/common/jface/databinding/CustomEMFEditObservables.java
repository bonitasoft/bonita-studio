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
package org.bonitasoft.studio.common.jface.databinding;

import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;
import org.eclipse.core.databinding.observable.masterdetail.MasterDetailObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EObjectObservableList;
import org.eclipse.emf.databinding.EObjectObservableValue;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EditingDomainEObjectObservableList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;


/**
 * @author Romain Bioteau
 *
 */
public class CustomEMFEditObservables extends EMFEditObservables {

    /**
     * Same as default behavior, excepted that the editing domain is retrieved on time with the target EObject
     *
     * @param realm
     * @param value
     * @param eStructuralFeature
     * @return
     */
    public static IObservableValue observeDetailValue(
            final Realm realm,
            final IObservableValue value,
            final EStructuralFeature eStructuralFeature) {
        return MasterDetailObservables.detailValue(value, valueFactory(realm, eStructuralFeature), eStructuralFeature);
    }


    public static IObservableFactory valueFactory(final Realm realm, final EStructuralFeature eStructuralFeature) {
        return new IObservableFactory()
        {

            @Override
            public IObservable createObservable(final Object target)
            {
                if (((EObject) target).eResource() != null) {
                    final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(target);
                    if (editingDomain != null) {
                        return observeValue(realm, editingDomain, (EObject) target, eStructuralFeature);
                    }
                }
                return new EObjectObservableValue(realm, (EObject) target, eStructuralFeature);

            }
        };
    }

    public static IObservableList observeDetailList(
            final Realm realm,
            final IObservableValue value,
            final EStructuralFeature eStructuralFeature) {
        return MasterDetailObservables.detailList(value, listFactory(realm, eStructuralFeature), eStructuralFeature);
    }

    public static IObservableFactory listFactory(final Realm realm, final EStructuralFeature eStructuralFeature) {
        return new IObservableFactory()
        {

            @Override
            public IObservable createObservable(final Object target)
            {
                if (((EObject) target).eResource() != null) {
                    final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(target);
                    if (domain != null) {
                        return observeList(realm, domain, (EObject) target, eStructuralFeature);
                    }
                }

                return observeList(realm, (EObject) target, eStructuralFeature);

            }
        };
    }

    public static IObservableList observeList(final EObject eObject, final EStructuralFeature eStructuralFeature)
    {
        if (eObject.eResource() != null) {
            final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(eObject);
            if (domain != null) {
                return new EditingDomainEObjectObservableList(domain, eObject, eStructuralFeature);
            }
        }
        return new EObjectObservableList(eObject, eStructuralFeature);

    }

    public static IObservableList observeList(final Realm realm, final EObject eObject, final EStructuralFeature eStructuralFeature)
    {
        if (eObject.eResource() != null) {
            final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(eObject);
            if (domain != null) {
                return new EditingDomainEObjectObservableList(realm, domain, eObject, eStructuralFeature);

            }
        }
        return new EObjectObservableList(realm, eObject, eStructuralFeature);


    }

}
