/**
 * Copyright (C) 2017 BonitaSoft S.A.
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
package org.bonitasoft.studio.application.dialog;

import java.util.HashMap;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.jface.MessageDialogWithPrompt;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchWindow;

public class StartupMessageDialog extends MessageDialogWithPrompt {

    private Link messageLink;

    public StartupMessageDialog(Shell parentShell) {
        super(parentShell,
                Messages.startDialogTitle,
                null,
                "",
                MessageDialog.INFORMATION,
                getButtonLabelsFor(MessageDialog.INFORMATION),
                0,
                Messages.doNotShowMeAgain,
                false);
    }

    public static StartupMessageDialog open(Shell parent, int style, IPreferenceStore store, String key) {
        final StartupMessageDialog dialog = new StartupMessageDialog(parent);
        style &= SWT.SHEET;
        dialog.setShellStyle(dialog.getShellStyle() | style);
        dialog.setPrefStore(store);
        dialog.setPrefKey(key);
        dialog.open();
        return dialog;
    }

    @Override
    protected Control createMessageArea(Composite composite) {
        // create composite
        // create image
        Image image = getImage();
        if (image != null) {
            imageLabel = new Label(composite, SWT.NULL);
            image.setBackground(imageLabel.getBackground());
            imageLabel.setImage(image);
            addAccessibleListeners(imageLabel, image);
            GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.BEGINNING)
                    .applyTo(imageLabel);
        }
        // create message
        if (message != null) {
            messageLink = new Link(composite, getMessageLabelStyle());
            messageLink.setText(String.format(Messages.startDialogMsg, Messages.importWorkFromAnotherWorkspace));
            GridDataFactory
                    .fillDefaults()
                    .align(SWT.FILL, SWT.BEGINNING)
                    .grab(true, false)
                    .hint(
                            convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH),
                            SWT.DEFAULT)
                    .applyTo(messageLink);
            messageLink.addListener(SWT.Selection, e -> {
                if (Messages.importWorkFromAnotherWorkspace.equals(e.text)) {
                    Display.getDefault().asyncExec(this::openImportWorkspaceDialog);
                }
            });
        }
        return composite;
    }

    private void openImportWorkspaceDialog() {
        IEclipseContext context = ((WorkbenchWindow) PlatformUI.getWorkbench().getActiveWorkbenchWindow()).getModel()
                .getContext();
        ECommandService commandService = context.get(ECommandService.class);
        Command importWorksapceCommand = commandService.getCommand("org.bonitasoft.studio.importer.workspace.command");
        try {
            close();
            importWorksapceCommand
                    .executeWithChecks(new ExecutionEvent(importWorksapceCommand, new HashMap<>(), null, context));
        } catch (ExecutionException | NotHandledException | NotDefinedException | NotEnabledException e1) {
            throw new RuntimeException("Failed to execute import workspace command", e1);
        }
    }

    private void addAccessibleListeners(Label label, final Image image) {
        label.getAccessible().addAccessibleListener(new AccessibleAdapter() {

            @Override
            public void getName(AccessibleEvent event) {
                final String accessibleMessage = getAccessibleMessageFor(image);
                if (accessibleMessage == null) {
                    return;
                }
                event.result = accessibleMessage;
            }
        });
    }

    private String getAccessibleMessageFor(Image image) {
        if (image.equals(getErrorImage())) {
            return JFaceResources.getString("error");//$NON-NLS-1$
        }

        if (image.equals(getWarningImage())) {
            return JFaceResources.getString("warning");//$NON-NLS-1$
        }

        if (image.equals(getInfoImage())) {
            return JFaceResources.getString("info");//$NON-NLS-1$
        }

        if (image.equals(getQuestionImage())) {
            return JFaceResources.getString("question"); //$NON-NLS-1$
        }

        return null;
    }
}
