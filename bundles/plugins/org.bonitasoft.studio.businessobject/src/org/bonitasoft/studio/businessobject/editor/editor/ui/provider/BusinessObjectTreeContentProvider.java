/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.editor.editor.ui.provider;

import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.Package;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.viewers.ObservableListTreeContentProvider;
import org.eclipse.jface.databinding.viewers.TreeStructureAdvisor;

public class BusinessObjectTreeContentProvider extends ObservableListTreeContentProvider {

    public BusinessObjectTreeContentProvider() {
        super(BusinessObjectTreeContentProvider::listfactory, getTreeStructureAdvisor());
    }

    private static TreeStructureAdvisor getTreeStructureAdvisor() {
        return new TreeStructureAdvisor() {

            @Override
            public Object getParent(Object element) {
                return element instanceof BusinessObject
                        ? ((BusinessObject) element).eContainer()
                        : null;
            }

            @Override
            public Boolean hasChildren(Object element) {
                return element instanceof Package;
            }

        };
    }

    private static IObservable listfactory(Object target) {
        if (target instanceof IObservableValue) {
            return EMFObservables.observeDetailList(Realm.getDefault(), (IObservableValue) target,
                    BusinessDataModelPackage.Literals.BUSINESS_OBJECT_MODEL__PACKAGES);
        } else if (target instanceof Package) {
            return EMFObservables.observeList(((Package) target),
                    BusinessDataModelPackage.Literals.PACKAGE__BUSINESS_OBJECTS);
        }
        return new WritableList<>();
    }
}
