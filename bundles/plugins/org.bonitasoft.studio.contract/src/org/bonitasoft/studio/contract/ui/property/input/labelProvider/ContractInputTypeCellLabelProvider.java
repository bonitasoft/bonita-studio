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

import java.util.Objects;

import org.bonitasoft.studio.businessobject.ui.DateTypeLabels;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;

/**
 * @author Romain Bioteau
 */
public class ContractInputTypeCellLabelProvider extends StyledCellLabelProvider implements ILabelProvider {

    @Override
    public Image getImage(final Object element) {
        return null;
    }

    @Override
    public void update(ViewerCell cell) {
        super.update(cell);
        final ContractInput element = (ContractInput) cell.getElement();
        final String text = getText(element);
        final StyledString styledString = new StyledString(text, new StyledString.Styler() {

            @Override
            public void applyStyles(TextStyle textStyle) {
                if (element.getType() == ContractInputType.DATE) {
                    textStyle.foreground = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY);
                }
            }
        });
        cell.setText(styledString.getString());
        cell.setStyleRanges(styledString.getStyleRanges());
    }

    @Override
    public String getText(Object element) {
        final ContractInputType type = ((ContractInput) element).getType();
        switch (type) {
            case DATE:
                return DateTypeLabels.DATE_DEPRECATED;
            case LOCALDATE:
                return DateTypeLabels.DATE_ONLY;
            case LOCALDATETIME:
                return DateTypeLabels.DATE_AND_TIME;
            case OFFSETDATETIME:
                return DateTypeLabels.DATE_TIME_WITH_TIMEZONE;
            default:
                return type.name();
        }
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
                /* ensure that default selection is not drawn */
                event.detail &= ~SWT.SELECTED;
            }
        }
    }

}
