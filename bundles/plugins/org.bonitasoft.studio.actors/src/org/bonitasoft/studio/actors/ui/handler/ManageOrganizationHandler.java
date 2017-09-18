/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.actors.ui.handler;

import org.bonitasoft.studio.actors.ui.wizard.ManageOrganizationWizard;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;

public class ManageOrganizationHandler {

    @Execute
    public void execute(ECommandService commmandService, EHandlerService handlerService) throws ExecutionException {
        final Wizard newWizard = new ManageOrganizationWizard(commmandService, handlerService);
        final WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(), newWizard) {

            /*
             * (non-Javadoc)
             * @see org.eclipse.jface.dialogs.TitleAreaDialog#getInitialSize()
             */
            @Override
            protected Point getInitialSize() {
                return new Point(1200, 800);
            }

        };
        dialog.open();
    }

}
