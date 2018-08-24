/**
 * Copyright (C) 2017 BonitaSoft S.A.
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
package org.bonitasoft.studio.application.contribution;

import java.util.Objects;

import org.bonitasoft.studio.application.ApplicationPlugin;
import org.bonitasoft.studio.application.dialog.StartupMessageDialog;
import org.bonitasoft.studio.common.extension.IPostStartupContribution;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

public class StartupDialogContribution implements IPostStartupContribution {

    private static final String DO_NOT_OPEN_STARTUP_DIALOG = "DO_NOT_OPEN_STARTUP_DIALOG";

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.extension.IPostStartupContribution#execute()
     */
    @Override
    public void execute() {
        IPreferenceStore store = ApplicationPlugin.getDefault().getPreferenceStore();
        if (shouldOpenStartupDialog(store)) {
            Display.getDefault().asyncExec(() -> StartupMessageDialog.open(Display.getDefault().getActiveShell(), SWT.NONE,
                    store, DO_NOT_OPEN_STARTUP_DIALOG));
        }
    }

    private boolean shouldOpenStartupDialog(IPreferenceStore store) {
        return !Objects.equals(System.getProperty("bonita.noregister"), "1")
                && !store.getBoolean(DO_NOT_OPEN_STARTUP_DIALOG);
    }

}
