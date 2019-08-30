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
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

public class SaveBeforeDeployDialog {

    public static DeployStrategy open(String resourceName) {
        int choice = MessageDialog.open(MessageDialog.QUESTION, Display.getDefault().getActiveShell(),
                Messages.saveBeforeTitle,
                String.format(Messages.saveBeforeMessage, resourceName),
                SWT.NONE, Messages.saveAndDeploy, IDialogConstants.CANCEL_LABEL, Messages.deployCurrent);
        switch (choice) {
            case 2:
                return DeployStrategy.DEPLOY_CURRENT;
            case 1:
                return DeployStrategy.CANCEL;
            default:
                return DeployStrategy.SAVE_AND_DEPLOY;
        }
    }

    public enum DeployStrategy {
        SAVE_AND_DEPLOY, DEPLOY_CURRENT, CANCEL
    }

}
