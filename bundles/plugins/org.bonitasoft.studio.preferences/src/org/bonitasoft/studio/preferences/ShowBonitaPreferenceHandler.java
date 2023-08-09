/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.preferences;

import org.bonitasoft.studio.preferences.dialog.BonitaPreferenceDialog;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ShowBonitaPreferenceHandler extends AbstractHandler  {

    public Object execute(ExecutionEvent event) throws ExecutionException {
        var display = Display.getCurrent() != null ? Display.getCurrent() : Display.getDefault();
        Shell shell = null;
        var disposeShell = false;
        if(display.getActiveShell() != null) {
            shell = display.getActiveShell();
        } else {
            disposeShell = true;
            shell = new Shell(display);
        }
        new BonitaPreferenceDialog(shell).open();
        if(disposeShell) {
            shell.dispose();
        }
        return null;
    }

}
