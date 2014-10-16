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
package org.bonitasoft.studio.contract.ui.expression;

import org.bonitasoft.studio.model.process.ContractInput;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;


/**
 * @author Romain Bioteau
 *
 */
public class ContractInputLabelProvider extends StyledCellLabelProvider implements ILabelProvider {

    private final AdapterFactoryLabelProvider adapterLabelProvider;

    public ContractInputLabelProvider() {
        final ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        adapterLabelProvider = new AdapterFactoryLabelProvider(adapterFactory);
    }

    @Override
    public void update(final ViewerCell cell) {
        super.update(cell);
        final ContractInput element = (ContractInput) cell.getElement();
        final StyledString styledString = getStyledString(element);
        cell.setText(styledString.toString());
        cell.setStyleRanges(styledString.getStyleRanges());
        cell.setImage(getImage(element));
    }

    public StyledString getStyledString(final ContractInput element) {
        final StyledString styledString = new StyledString(element.getName());
        styledString.append(" -- ");
        styledString.append(new StyledString(element.getType().name(), StyledString.DECORATIONS_STYLER));
        return styledString;
    }

    @Override
    public Image getImage(final Object element) {
        return adapterLabelProvider.getImage(element);
    }

    @Override
    public String getText(final Object element) {
        return ((ContractInput) element).getName();
    }

    @Override
    public void dispose() {
        super.dispose();
        adapterLabelProvider.dispose();
    }

}
