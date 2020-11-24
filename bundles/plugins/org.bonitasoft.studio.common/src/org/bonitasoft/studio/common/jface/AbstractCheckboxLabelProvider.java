/**
 * Copyright (C) 2012-2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.jface;

import java.util.Objects;

import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TreeItem;

public abstract class AbstractCheckboxLabelProvider extends StyledCellLabelProvider
        implements ILabelProvider {

    public AbstractCheckboxLabelProvider(final ColumnViewer viewer) {
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(final Object element) {
        return checkboxImage(isSelected(element), isEnabled(element));
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(final Object element) {
        return ((Boolean) isSelected(element)).toString();
    }

    protected abstract boolean isSelected(Object element);

    protected boolean isEnabled(final Object element) {
        return true;
    }

    private Image checkboxImage(final boolean selected, final boolean enabled) {
        if (selected) {
            return enabled ? Pics.getImage("/checkboxes/checkbox_yes.png")
                    : Pics.getImage("/checkboxes/checkbox_yes_disabled.png");
        }
        return enabled ? Pics.getImage("/checkboxes/checkbox_no.png")
                : Pics.getImage("/checkboxes/checkbox_no_disabled.png");
    }

    @Override
    protected void paint(final Event event, final Object element) {
        final Image img = getImage(element);
        if (img != null) {
            final Rectangle bounds = event.item instanceof TableItem ? ((TableItem) event.item).getBounds(event.index)
                    : ((TreeItem) event.item).getBounds(event.index);
            final Rectangle imgBounds = img.getBounds();
            bounds.width /= 2;
            bounds.width -= imgBounds.width / 2;
            bounds.height /= 2;
            bounds.height -= imgBounds.height / 2;

            final int x = bounds.width > 0 ? bounds.x + bounds.width : bounds.x;
            final int y = bounds.height > 0 ? bounds.y + bounds.height : bounds.y;

            if (SWT.getPlatform().equals("carbon")) {
                event.gc.drawImage(img, x + 2, y - 1);
            } else {
                event.gc.drawImage(img, x, y);
            }
        }
    }

    @Override
    protected void measure(final Event event, final Object element) {
        final Image image = getImage(element);
        if (image != null) {
            event.height = image.getBounds().height;
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
                event.detail &= ~SWT.SELECTED;
            }
        }
    }

}
