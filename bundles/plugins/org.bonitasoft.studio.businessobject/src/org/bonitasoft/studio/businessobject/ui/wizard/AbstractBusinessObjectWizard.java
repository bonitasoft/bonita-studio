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
package org.bonitasoft.studio.businessobject.ui.wizard;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.Wizard;

public abstract class AbstractBusinessObjectWizard extends Wizard {

    protected Set<String> computeExistingNames(final DataAware container) {
        /* Search to check at the same level */
        final Set<String> existingNames = new HashSet<String>();

        /* Search above level */
        final List<Data> allData = getAllAccessibleData(container);
        for (final Data object : allData) {
            if (object instanceof Data && !(object.eContainer() instanceof Expression)) {
                final Data otherData = object;
                existingNames.add(otherData.getName());
            }
        }
        return existingNames;
    }

    protected List<Data> getAllAccessibleData(final EObject container) {
        final List<Data> allData = ModelHelper.getAccessibleData(container, true);
        for (final Object o : ModelHelper.getAllItemsOfType(container, ProcessPackage.Literals.DATA)) {
            if (o instanceof Data) {
                if (!allData.contains(o)) {
                    allData.add((Data) o);
                }
            }
        }
        return allData;
    }

}
