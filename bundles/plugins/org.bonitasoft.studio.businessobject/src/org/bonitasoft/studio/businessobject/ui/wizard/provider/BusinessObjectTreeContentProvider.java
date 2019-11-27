/**
 * Copyright (C) 2019 BonitaSoft S.A.
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
package org.bonitasoft.studio.businessobject.ui.wizard.provider;

import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.helper.PackageHelper;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class BusinessObjectTreeContentProvider implements ITreeContentProvider {

    private BusinessObjectModel input;

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        input = (BusinessObjectModel) newInput;
    }

    @Override
    public Object[] getElements(Object inputElement) {
        if (inputElement instanceof BusinessObjectModel) {
            return PackageHelper.getAllPackages((BusinessObjectModel) inputElement).toArray();
        }
        return null;
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof String) {
            return input.getBusinessObjects().stream()
                    .filter(bo -> Objects.equals(PackageHelper.getPackageName(bo), parentElement))
                    .collect(Collectors.toList()).toArray();
        }
        return null;
    }

    @Override
    public Object getParent(Object element) {
        if (element instanceof BusinessObject) {
            return PackageHelper.getPackageName((BusinessObject) element);
        }
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        return element instanceof String;
    }

}
