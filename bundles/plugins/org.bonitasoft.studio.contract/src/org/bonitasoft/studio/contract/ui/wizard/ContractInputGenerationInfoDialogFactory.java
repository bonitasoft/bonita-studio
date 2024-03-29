/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.contract.ui.wizard;

import static com.google.common.base.Strings.isNullOrEmpty;

import org.bonitasoft.studio.common.ui.jface.MessageDialogWithPrompt;
import org.bonitasoft.studio.contract.ContractPlugin;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.bpm.model.process.ContractContainer;
import org.bonitasoft.bpm.model.process.OperationContainer;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

/**
 * @author aurelie
 */
public class ContractInputGenerationInfoDialogFactory {

    public static final int NOT_OPENED = -2;
    public static final String SHOW_GENERATION_SUCCESS_DIALOG = "SHOW_GENERATION_SUCCESS_DIALOG";
    public static final String HIDE_DOCUMENT_OPERATION_INFO = "hideDocumentOperationInfo";
    public static final String HIDE_DOCUMENT_INIT_INFO = "hideDocumentInitInfo";

    public int openInfoDialog(final IPreferenceStore preferenceStore, final Shell shell,
            final ContractContainer contractContainer,
            final boolean isAutogeneratedScript) {

        if (isNullOrEmpty(preferenceStore.getString(SHOW_GENERATION_SUCCESS_DIALOG))
                && isAutogeneratedScript) {
            return MessageDialogWithToggle.openOkCancelConfirm(shell,
                    Messages.contractGenerationTitle,
                    getInfoMessage(contractContainer),
                    Messages.doNotShowMeAgain,
                    false,
                    preferenceStore,
                    SHOW_GENERATION_SUCCESS_DIALOG).getReturnCode();
        }
        return NOT_OPENED;
    }

    public void openUpdateDocumentOperationWarning(String documentName, Shell shell) {
        shell.getDisplay().asyncExec(() -> {
            IPreferenceStore preferenceStore = ContractPlugin.getDefault().getPreferenceStore();
            if (!preferenceStore.getBoolean(HIDE_DOCUMENT_OPERATION_INFO)) {
                MessageDialogWithPrompt.open(MessageDialog.INFORMATION,
                        shell,
                        Messages.openUpdateDocumentOperationWarningTitle,
                        String.format(Messages.openUpdateDocumentOperationWarningMessages, documentName),
                        Messages.doNotShowMeAgain,
                        false,
                        preferenceStore,
                        HIDE_DOCUMENT_OPERATION_INFO,
                        SWT.NONE);
            }
        });
    }

    public void openUpdateDocumentInitalContentWarning(String documentName, Shell shell) {
        shell.getDisplay().asyncExec(() -> {
            IPreferenceStore preferenceStore = ContractPlugin.getDefault().getPreferenceStore();
            if (!preferenceStore.getBoolean(HIDE_DOCUMENT_INIT_INFO)) {
                MessageDialogWithPrompt.open(MessageDialog.INFORMATION,
                        shell,
                        Messages.updateInitialDocumentContentWarningTitle,
                        String.format(Messages.updateInitialDocumentContentWarningMsg, documentName),
                        Messages.doNotShowMeAgain,
                        false,
                        preferenceStore,
                        HIDE_DOCUMENT_INIT_INFO,
                        SWT.NONE);
            }
        });
    }

    private String getInfoMessage(final ContractContainer contractContainer) {
        if (contractContainer instanceof OperationContainer) {
            return Messages.contractOperationGenerationMsg;
        } else {
            return Messages.contractStrictGenerationMsg;
        }
    }

}
