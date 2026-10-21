/**
 * Copyright (C) 2026 Bonitasoft S.A.
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

package org.bonitasoft.studio.engine;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.ui.PlatformUtil;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * Puts a local-runtime failure in front of the user, or nowhere at all when there is no user: the
 * recovery instructions these messages carry are worth nothing if the dialog lands behind the
 * splash screen or throws in a headless run.
 */
class RuntimeErrorReporter {

    /** The message carries the recovery instruction, so it is shown verbatim. */
    void report(final CoreException e) {
        BonitaStudioLog.error(e, EnginePlugin.PLUGIN_ID);
        openErrorDialog(Messages.cannotStartTomcatTitle, e.getMessage());
    }

    void openErrorDialog(String title, String message) {
        if (headless()) {
            // never try to open a dialog in headless/CI runs
            return;
        }
        dispatch(title, message);
    }

    /** Separated from the decision to show a dialog so a test can assert that decision. */
    void dispatch(String title, String message) {
        Display.getDefault().asyncExec(() -> {
            var shell = Display.getDefault().getActiveShell();
            if (shell == null && PlatformUI.isWorkbenchRunning()) {
                // no active shell during startup: a parentless dialog lands behind the splash.
                // getWorkbench() throws before the workbench exists, and an exception escaping
                // this runnable would drop the dialog silently, hence the isWorkbenchRunning guard
                var window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                shell = window != null ? window.getShell() : null;
            }
            MessageDialog.openError(shell, title, message);
        });
    }

    boolean headless() {
        return PlatformUtil.isHeadless();
    }
}
