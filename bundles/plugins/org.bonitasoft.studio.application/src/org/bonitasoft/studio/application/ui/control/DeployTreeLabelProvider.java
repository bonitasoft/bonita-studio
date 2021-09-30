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
package org.bonitasoft.studio.application.ui.control;

import org.bonitasoft.studio.common.repository.model.IDisplayable;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;

public class DeployTreeLabelProvider extends StyledCellLabelProvider implements ILabelProvider {

    @Override
    public void update(ViewerCell cell) {
        if (cell.getElement() instanceof IDisplayable) {
            final IDisplayable displayable = (IDisplayable) cell.getElement();
            final StyledString styledString = displayable.getStyledString();
            cell.setText(styledString.getString());
            cell.setImage(displayable.getIcon());
            cell.setStyleRanges(styledString.getStyleRanges());
        }
    }

    @Override
    public String getToolTipText(Object element) {
        return super.getToolTipText(element);
    }

    @Override
    public Image getImage(Object element) {
        if (element instanceof IDisplayable) {
            return ((IDisplayable) element).getIcon();
        }
        return null;
    }

    @Override
    public String getText(Object element) {
        if (element instanceof IDisplayable) {
            return ((IDisplayable) element).getDisplayName();
        }
        return element.toString();
    }

}
