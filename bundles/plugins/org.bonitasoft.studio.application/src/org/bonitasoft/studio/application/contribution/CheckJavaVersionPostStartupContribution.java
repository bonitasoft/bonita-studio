/**
 * Copyright (C) 2012 BonitaSoft S.A.
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

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.extension.IPostStartupContribution;
import org.bonitasoft.studio.preferences.dialog.BonitaPreferenceDialog;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.IVMInstallType;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.VMStandin;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

/**
 * @author Aurelie Zara
 */
public class CheckJavaVersionPostStartupContribution implements IPostStartupContribution {

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.extension.IPostStartupContribution#execute()
     */
    @Override
    public void execute() {
        final List standins = new ArrayList();
        final IVMInstallType[] types = JavaRuntime.getVMInstallTypes();
        for (int i = 0; i < types.length; i++) {
            final IVMInstallType type = types[i];
            final IVMInstall[] installs = type.getVMInstalls();
            for (int j = 0; j < installs.length; j++) {
                final IVMInstall install = installs[j];
                standins.add(new VMStandin(install));
            }
        }
        if (standins.isEmpty()) {
        	Display.getDefault().syncExec(new Runnable() {
				
				@Override
				public void run() {
					 if (MessageDialog.openQuestion(Display.getDefault().getActiveShell(), Messages.jreNotFoundTitle, Messages.jreNotFoundMessage)) {
			                final BonitaPreferenceDialog dialog = new BonitaPreferenceDialog(Display.getDefault().getActiveShell());
			                dialog.open();
			            }
				}
			});
           
        }
    }
}
