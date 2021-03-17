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

import java.util.Objects;

import org.bonitasoft.studio.common.repository.model.IDisplayable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Event;

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

    @Override
    protected void erase(Event event, Object element) {
        super.erase(event, element);

        // Necessary since the MacOS Big Sur update -> Seems that table with StyledCellLabelProvider aren't redraw automatically 
        // TODO Hopefully this could be removed on the futur (current date: 19/11/2020)
        if (Objects.equals(Platform.OS_MACOSX, Platform.getOS())) {
            Rectangle bounds = event.getBounds();
            if ((event.detail & SWT.SELECTED) != 0) {
                Color oldForeground = event.gc.getForeground();
                event.gc.setForeground(event.item.getDisplay().getSystemColor(
                        SWT.COLOR_LIST_SELECTION_TEXT));
                event.gc.fillRectangle(bounds);
                /* restore the old GC colors */
                event.gc.setForeground(oldForeground);
                event.detail &= ~SWT.SELECTED;
            }
        }
    }

}
