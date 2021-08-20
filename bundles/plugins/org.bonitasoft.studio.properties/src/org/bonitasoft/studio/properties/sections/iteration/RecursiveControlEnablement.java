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
package org.bonitasoft.studio.properties.sections.iteration;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @author Romain Bioteau
 *
 */
public class RecursiveControlEnablement {

    private final Control topControl;
    private boolean enabled;

    public RecursiveControlEnablement(final Control topControl) {
        this.topControl = topControl;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
        if (topControl != null && !topControl.isDisposed()) {
            updateControl(topControl);
        }

    }

    private void updateControl(final Control control) {
        control.setEnabled(isEnabled());
        if (control instanceof Composite) {
            for (final Control c : ((Composite) control).getChildren()) {
                updateControl(c);
            }
        }
    }


}
