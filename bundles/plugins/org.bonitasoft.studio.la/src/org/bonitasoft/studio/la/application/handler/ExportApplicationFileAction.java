/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.handler;

import org.bonitasoft.studio.common.jface.MessageDialogWithPrompt;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.ui.editors.core.AbstractExportAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

public class ExportApplicationFileAction extends AbstractExportAction {

    public static final String DO_NOT_SHOW_EXPORT_MESSAGE_DIALOG = "DO_NOT_SHOW_EXPORT_MESSAGE_DIALOG";

    @Override
    protected void openExportMessageDialog(Shell shell) {
        final IPreferenceStore preferenceStore = LivingApplicationPlugin.getDefault().getPreferenceStore();
        if (!preferenceStore.getBoolean(DO_NOT_SHOW_EXPORT_MESSAGE_DIALOG)) {
            MessageDialogWithPrompt.open(MessageDialog.WARNING,
                    shell,
                    Messages.exportApplicationDescriptorTitle,
                    Messages.exportApplicationDescriptorMessage,
                    org.bonitasoft.studio.ui.i18n.Messages.doNotShowMeAgain,
                    false,
                    preferenceStore,
                    DO_NOT_SHOW_EXPORT_MESSAGE_DIALOG,
                    SWT.NONE);
        }
    }

    @Override
    protected String getExportDoneMessage() {
        return Messages.exportDoneMessage;
    }

    @Override
    protected String getExportTitle() {
        return Messages.exportApplicationDescriptor;
    }

}
