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
package org.bonitasoft.studio.contract.ui.property.input;


import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;


/**
 * @author Romain Bioteau
 *
 */
public class CellEditorControlAdapter extends ControlAdapter {

    protected static final int OFFSET = 8;
    private final Control control;

    public CellEditorControlAdapter(final Control control) {
        this.control = control;
    }

    @Override
    public void controlMoved(final ControlEvent event) {
        if (control.equals(event.widget)) {
            final Control movedControl = (Control) event.widget;
            final Point location = movedControl.getLocation();
            if (location.x != OFFSET) {
                final int offset = OFFSET - location.x;
                movedControl.setLocation(OFFSET, location.y);
                movedControl.setSize(movedControl.getSize().x - offset, movedControl.getSize().y);
            }
        }

    }

}
