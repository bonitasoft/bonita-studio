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
package org.bonitasoft.studio.application.dialog;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.preferences.dialog.BonitaPreferenceDialog;
import org.eclipse.core.runtime.IProduct;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;

public class ExitDialog extends MessageDialogWithToggle {

    public static MessageDialogWithToggle openExitDialog(final Shell parentShell) {
        MessageDialogWithToggle dialog = null;
        if (deleteTenantOnExit()) {
            dialog = new ExitDialog(parentShell, IDEWorkbenchMessages.PromptOnExitDialog_shellTitle, null, null,
                    WARNING, new String[] {
                            IDialogConstants.OK_LABEL,
                            IDialogConstants.CANCEL_LABEL },
                    0, IDEWorkbenchMessages.PromptOnExitDialog_choice, false);
            dialog.open();
        } else {
            dialog = MessageDialogWithToggle
                    .openOkCancelConfirm(parentShell,
                            IDEWorkbenchMessages.PromptOnExitDialog_shellTitle,
                            exitMessage(),
                            IDEWorkbenchMessages.PromptOnExitDialog_choice,
                            false, null, null);
        }
        return dialog;
    }

    private static boolean deleteTenantOnExit() {
        return BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .getBoolean(BonitaPreferenceConstants.DELETE_TENANT_ON_EXIT);
    }

    protected static String exitMessage() {
        String productName = null;
        final IProduct product = Platform.getProduct();
        if (product != null) {
            productName = product.getName();
        }
        String message = null;
        if (productName == null) {
            message = IDEWorkbenchMessages.PromptOnExitDialog_message0;
        } else {
            message = NLS.bind(
                    IDEWorkbenchMessages.PromptOnExitDialog_message1,
                    productName);
        }
        return message;
    }

    public ExitDialog(final Shell parentShell, final String dialogTitle, final Image image, final String message,
            final int dialogImageType,
            final String[] dialogButtonLabels, final int defaultIndex,
            final String toggleMessage, final boolean toggleState) {
        super(parentShell, dialogTitle, image, message, dialogImageType, dialogButtonLabels, defaultIndex,
                toggleMessage, toggleState);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IconAndMessageDialog#createMessageArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createMessageArea(final Composite composite) {
        final Image image = getImage();
        if (image != null) {
            imageLabel = new Label(composite, SWT.NULL);
            image.setBackground(imageLabel.getBackground());
            imageLabel.setImage(image);
            GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.BEGINNING)
                    .applyTo(imageLabel);
        }
        final Link messageLabel = new Link(composite, getMessageLabelStyle());
        messageLabel.setText(NLS.bind(Messages.exitWarningMessage,
                new String[] { org.bonitasoft.studio.common.Messages.bonitaStudioModuleName,
                        org.bonitasoft.studio.common.Messages.uiDesignerModuleName }));
        messageLabel.addSelectionListener(new SelectionAdapter() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                final BonitaPreferenceDialog dialog = new BonitaPreferenceDialog(Display.getDefault().getActiveShell());
                dialog.create();
                dialog.setSelectedPreferencePage(BonitaPreferenceDialog.DATABASE_PAGE_ID);
                dialog.open();
            }
        });
        GridDataFactory
                .fillDefaults()
                .align(SWT.FILL, SWT.BEGINNING)
                .grab(true, false)
                .hint(
                        convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH),
                        SWT.DEFAULT)
                .applyTo(messageLabel);
        return composite;
    }

}
