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

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Objects;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.jface.MessageDialogWithPrompt;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
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
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.internal.WorkbenchWindow;

public class StartupMessageDialog extends MessageDialogWithPrompt {

    private Link startMessage;
    private Label title;
    public static int IMPORT_BUTTON_ID = 38;

    public StartupMessageDialog(Shell parentShell) {
        super(parentShell,
                "",
                null,
                "",
                MessageDialog.INFORMATION,
                getButtonLabelsFor(MessageDialog.INFORMATION),
                0,
                Messages.doNotShowMeAgain,
                false);
    }

    public static int open(Shell parent, int style, IPreferenceStore store, String key) {
        final StartupMessageDialog dialog = new StartupMessageDialog(parent);
        dialog.setShellStyle(dialog.getShellStyle() | style | SWT.NO_TRIM | SWT.ON_TOP);
        dialog.setPrefStore(store);
        dialog.setPrefKey(key);
        return dialog.open();
    }

    @Override
    protected Control createMessageArea(Composite composite) {
        if (message != null) {


            title = new Label(composite, SWT.CENTER);
            title.setText(String.format(Messages.startDialogTitle, ProductVersion.CURRENT_VERSION));
            GridDataFactory
                    .fillDefaults()
                    .span(2, 1)
                    .grab(true, false)
                    .applyTo(title);

            GridDataFactory
                    .fillDefaults()
                    .span(2, 1)
                    .grab(true, false)
                    .applyTo(new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL));

            startMessage = new Link(composite, SWT.WRAP | SWT.NO_FOCUS);
            startMessage.setText(String.format(Messages.startDialogMsg, Messages.releaseNote, Messages.documentedHere));
            GridDataFactory
                    .fillDefaults()
                    .span(2, 1)
                    .align(SWT.FILL, SWT.BEGINNING)
                    .grab(true, false)
                    .hint(
                            convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH),
                            SWT.DEFAULT)
                    .applyTo(startMessage);
            startMessage.addListener(SWT.Selection, event -> openBrowser(event.text));

            createDetailsSection(composite);
        }
        return composite;
    }

    private void openBrowser(String text) {
        try {
            java.awt.Desktop.getDesktop().browse(new URI(getLinkFor(text)));
        } catch (IOException | URISyntaxException e) {
            BonitaStudioLog.error(e);
        }
    }

    protected String getLinkFor(String text) {
        if (Objects.equals(Messages.releaseNote, text)) {
            return String.format(
                    "http://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=676&bos_redirect_product=bos&bos_redirect_major_version=%s",
                    ProductVersion.majorVersion());
        }
        if (Objects.equals(Messages.documentedHere, text)) {
            return String.format(
                    "http://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=675&bos_redirect_product=bos&bos_redirect_major_version=%s",
                    ProductVersion.majorVersion());
        }
        throw new IllegalArgumentException("Unsupported link");
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        Button importButton = createButton(parent, IMPORT_BUTTON_ID, Messages.importWorkspace, false);
        super.createButtonsForButtonBar(parent);
        getButton(IDialogConstants.OK_ID).setText(Messages.letsStart);
        importButton.addListener(SWT.Selection, e -> Display.getDefault().asyncExec(this::openImportWorkspaceDialog));
    }

    private void createDetailsSection(Composite parent) {

        Section detailsSection = new Section(parent, Section.TWISTIE | Section.NO_TITLE_FOCUS_BOX);
        detailsSection.setLayout(GridLayoutFactory.fillDefaults().create());
        detailsSection.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, true).create());
        detailsSection.setText(org.bonitasoft.studio.common.Messages.moreDetails);

        Composite detailsComposite = new Composite(detailsSection, SWT.NONE);
        detailsComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        detailsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        Label details = new Label(detailsComposite, SWT.NONE);
        details.setLayoutData(GridDataFactory.fillDefaults().create());
        details.setText(Messages.startDialogDetails);

        detailsSection.setClient(detailsComposite);

        detailsSection.addExpansionListener(new ExpansionAdapter() {

            @Override
            public void expansionStateChanged(ExpansionEvent e) {
                parent.getShell().pack();
            }
        });
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
