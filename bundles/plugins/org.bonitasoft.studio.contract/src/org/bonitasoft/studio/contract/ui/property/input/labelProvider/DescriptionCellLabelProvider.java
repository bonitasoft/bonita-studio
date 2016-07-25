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
package org.bonitasoft.studio.contract.ui.property.input.labelProvider;

import org.bonitasoft.studio.common.jface.databinding.CustomPropertyColumnLabelProvider;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

/**
 * @author Romain Bioteau
 */
public class DescriptionCellLabelProvider extends CustomPropertyColumnLabelProvider {

    public DescriptionCellLabelProvider(final IPropertySourceProvider propertySourceProvider, final IObservableSet knownElements) {
        super(propertySourceProvider, ProcessPackage.Literals.CONTRACT_INPUT__DESCRIPTION, knownElements);
    }

    @Override
    public Image getImage(final Object element) {
        return null;
    }

    @Override
    public String getToolTipText(final Object element) {
        return ((ContractInput) element).getDescription();
    }

}
