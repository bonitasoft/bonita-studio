/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.application.ui.control;

import java.util.Arrays;
import java.util.Collection;

import org.bonitasoft.studio.common.jface.dialog.ProblemsDialog;
import org.bonitasoft.studio.common.jface.dialog.TypedLabelProvider;
import org.bonitasoft.studio.la.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;

public class DeployApplicationStatusDialog extends ProblemsDialog<IStatus> {

    private final IStatus status;

    public DeployApplicationStatusDialog(Shell parentShell, IStatus status, String[] dialogButtons, String message) {
        super(parentShell, Messages.deployDoneTitle, message, MessageDialog.INFORMATION,
                dialogButtons);
        this.status = status;
    }

    @Override
    protected TypedLabelProvider<IStatus> getTypedLabelProvider() {
        return new TypedLabelProvider<IStatus>() {

            @Override
            public String getText(IStatus status) {
                return status.getMessage();
            }

            @Override
            public Image getImage(IStatus status) {
                switch (status.getSeverity()) {
                    case ERROR:
                        return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_ERROR);
                    case WARNING:
                        return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_WARNING);
                    default:
                        return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_INFO);
                }
            }
        };
    }

    @Override
    protected Collection<IStatus> getInput() {
        return Arrays.asList(status.getChildren());
    }

}
