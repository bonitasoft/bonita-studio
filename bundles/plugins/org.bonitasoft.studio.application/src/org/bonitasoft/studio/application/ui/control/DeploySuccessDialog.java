/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.ui.control;

import java.util.Arrays;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.ui.widget.ComboWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class DeploySuccessDialog extends MessageDialog {

    private static final String DEFAULT_APP_SELECTION = "DEFAULT_APP_SELECTION";

    public static int open(Shell parentShell, DeployedAppContentProvider contentProvider,
            IDialogSettings dialogSettings) {
        return new DeploySuccessDialog(parentShell, Messages.deployStatus, Messages.deploySuccessMsg,
                MessageDialog.INFORMATION, contentProvider, dialogSettings).open();
    }

    private DeployedAppContentProvider contentProvider;
    private DataBindingContext ctx;
    private IDialogSettings dialogSettings;

    public DeploySuccessDialog(Shell parentShell, String dialogTitle, String dialogMessage, int dialogImageType,
            DeployedAppContentProvider contentProvider, IDialogSettings dialogSettings) {
        super(parentShell, dialogTitle, null, dialogMessage, dialogImageType, 1,
                new String[] { IDialogConstants.CLOSE_LABEL, IDialogConstants.OPEN_LABEL });
        this.contentProvider = contentProvider;
        this.dialogSettings = dialogSettings;
    }

    @Override
    protected Control createCustomArea(Composite parent) {
        ctx = new DataBindingContext();

        Composite container = new Composite(parent, SWT.NONE);
        container.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        container.setLayout(GridLayoutFactory.fillDefaults().create());

        new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL)
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        contentProvider.setSelection(defaultSelection(dialogSettings));

        new ComboWidget.Builder()
                .withLabel(Messages.youCanOpenApp)
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .readOnly()
                .withMessage(String.format("%s : %s", org.bonitasoft.studio.actors.i18n.Messages.defaultUser,
                        new ActiveOrganizationProvider().getDefaultUser()))
                .withItems(contentProvider.getItems())
                .bindTo(PojoProperties.value("selection").observe(contentProvider))
                .inContext(ctx)
                .createIn(container);

        return container;
    }

    private String defaultSelection(IDialogSettings dialogSettings) {
        IDialogSettings section = dialogSettings
                .getSection(RepositoryManager.getInstance().getCurrentRepository().getName());
        if (section != null && section.get(DEFAULT_APP_SELECTION) != null) {
            String lastSelection = section.get(DEFAULT_APP_SELECTION);
            return Arrays.asList(contentProvider.getItems()).contains(lastSelection) ? lastSelection
                    : contentProvider.getSelection();
        }
        return contentProvider.getSelection();
    }

    private void saveSelectedApp(IDialogSettings dialogSettings) {
        String sectionName = RepositoryManager.getInstance().getCurrentRepository().getName();
        IDialogSettings section = dialogSettings.getSection(sectionName);
        if (section == null) {
            section = dialogSettings.addNewSection(sectionName);
        }
        section.put(DEFAULT_APP_SELECTION, contentProvider.getSelection());
    }

    @Override
    public boolean close() {
        ctx.dispose();
        return super.close();
    }

    @Override
    protected void buttonPressed(int buttonId) {
        super.buttonPressed(buttonId);
        switch (buttonId) {
            case 0:
                setReturnCode(IDialogConstants.CLOSE_ID);
                break;
            default:
                saveSelectedApp(dialogSettings);
                setReturnCode(IDialogConstants.OPEN_ID);
                break;
        }
    }

}
