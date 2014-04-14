/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.jface;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Romain Bioteau
 * 
 */
public class MessageDialogWithPrompt extends MessageDialogWithToggle {

    public MessageDialogWithPrompt(Shell parentShell, String dialogTitle,
            Image image, String message, int dialogImageType,
            String[] dialogButtonLabels, int defaultIndex,
            String toggleMessage, boolean toggleState) {
        super(parentShell, dialogTitle, image, message, dialogImageType,
                dialogButtonLabels, defaultIndex, toggleMessage, toggleState);
    }

    public static MessageDialogWithPrompt openOkCancelConfirm(Shell parent,
            String title, String message, String toggleMessage,
            boolean toggleState, IPreferenceStore store, String key) {
        return open(CONFIRM, parent, title, message, toggleMessage, toggleState, store, key, SWT.NONE);
    }

    public static MessageDialogWithPrompt open(int kind, Shell parent, String title,
            String message, String toggleMessage, boolean toggleState,
            IPreferenceStore store, String key, int style) {
        MessageDialogWithPrompt dialog = new MessageDialogWithPrompt(parent,
                title, null, // accept the default window icon
                message, kind, getButtonLabelsFor(kind), 0,
                toggleMessage, toggleState);
        style &= SWT.SHEET;
        dialog.setShellStyle(dialog.getShellStyle() | style);
        dialog.setPrefStore(store);
        dialog.setPrefKey(key);
        dialog.open();
        return dialog;
    }

    private static String[] getButtonLabelsFor(int kind) {
        String[] dialogButtonLabels;
        switch (kind) {
            case ERROR:
            case INFORMATION:
            case WARNING: {
                dialogButtonLabels = new String[] { IDialogConstants.OK_LABEL };
                break;
            }
            case CONFIRM: {
                dialogButtonLabels = new String[] { IDialogConstants.OK_LABEL,
                        IDialogConstants.CANCEL_LABEL };
                break;
            }
            case QUESTION: {
                dialogButtonLabels = new String[] { IDialogConstants.YES_LABEL,
                        IDialogConstants.NO_LABEL };
                break;
            }
            case QUESTION_WITH_CANCEL: {
                dialogButtonLabels = new String[] { IDialogConstants.YES_LABEL,
                        IDialogConstants.NO_LABEL,
                        IDialogConstants.CANCEL_LABEL };
                break;
            }
            default: {
                throw new IllegalArgumentException("Illegal value for kind in MessageDialog.open()"); //$NON-NLS-1$
            }
        }
        return dialogButtonLabels;
    }

    protected void buttonPressed(int buttonId) {
        super.buttonPressed(buttonId);

        boolean toggleState = getToggleState();
        IPreferenceStore prefStore = getPrefStore();
        String prefKey = getPrefKey();
        if (buttonId != IDialogConstants.CANCEL_ID
                && prefStore != null && prefKey != null) {
            switch (buttonId) {
                case IDialogConstants.YES_ID:
                case IDialogConstants.YES_TO_ALL_ID:
                case IDialogConstants.PROCEED_ID:
                case IDialogConstants.OK_ID:
                    prefStore.setValue(prefKey, toggleState);
                    break;
                case IDialogConstants.NO_ID:
                case IDialogConstants.NO_TO_ALL_ID:
                    break;
            }
        }
    }

}
