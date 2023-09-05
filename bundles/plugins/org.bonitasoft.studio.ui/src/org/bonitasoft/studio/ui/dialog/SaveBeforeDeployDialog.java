/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.ui.dialog;

import org.bonitasoft.studio.ui.i18n.Messages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

public class SaveBeforeDeployDialog {

    public static DeployStrategy open(String resourceName) {
        int choice = open(Messages.saveBeforeTitle,
                String.format(Messages.saveBeforeMessage, resourceName),
                IDialogConstants.CANCEL_LABEL, Messages.deployCurrent, Messages.saveAndDeploy);
        switch (choice) {
            case 2:
                return DeployStrategy.SAVE_AND_DEPLOY;
            case 1:
                return DeployStrategy.DEPLOY_CURRENT;
            default:
                return DeployStrategy.CANCEL;
        }
    }

    /**
     * According to the documentation of
     * {@link org.eclipse.jface.dialogs.Dialog#createButton(Composite parent, int id, String label, boolean defaultButton)}:
     * <p>
     * On some platforms, {@link org.eclipse.jface.dialogs.Dialog#initializeBounds()} will move the default button to the
     * right.
     * </p>
     * -> To avoid different behavior depending on the platform, the default index is the index of the last element of the
     * buttons arrays (i.e the button on the right), so buttons are always in the same order, no matter of the platform
     */
    public static int open(String title, String message, String... buttons) {
        return new MessageDialog(Display.getDefault().getActiveShell(),
                title,
                null,
                message,
                MessageDialog.QUESTION,
                buttons.length - 1,
                buttons).open();
    }

    public enum DeployStrategy {
        SAVE_AND_DEPLOY, DEPLOY_CURRENT, CANCEL
    }

}
