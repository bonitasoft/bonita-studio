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
package org.bonitasoft.studio.importer.handler;

import org.bonitasoft.studio.importer.i18n.Messages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;


/**
 * @author Romain Bioteau
 *
 */
public class ImportErrorMessageDialog extends MessageDialog {

    public ImportErrorMessageDialog(final Shell parentShell, final String dialogMessage, final boolean canOpen) {
        super(parentShell, org.bonitasoft.studio.importer.i18n.Messages.importResultTitle, null, dialogMessage, WARNING,
                getLabels(canOpen), 0);
    }

    protected static String[] getLabels(final boolean canOpen) {
        if (canOpen) {
            return new String[] { Messages.openDiagram, IDialogConstants.OK_LABEL };
        }
        return new String[] { IDialogConstants.OK_LABEL };
    }

    @Override
    protected Button createButton(final Composite parent, final int id, final String label, final boolean defaultButton) {
        if (Messages.openDiagram.equals(label)) {
            return super.createButton(parent, IDialogConstants.OPEN_ID, label, defaultButton);
        }
        return super.createButton(parent, id, label, defaultButton);
    }

}
