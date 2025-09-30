/**
 * Copyright (C) 2025 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.migration.ui;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import org.bonitasoft.studio.common.repository.Messages;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.operation.ModalContext;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Vincent Hemery
 */
public class ProjectMigrationWizardDialog extends WizardDialog {

    private static final int DEFAULT_WIZARD_WIDTH = 540;
    private static final int FINISH_BUTTON_WIDTH = 80;

    /**
     * Default Constructor.
     * 
     * @param parentShell the parent shell
     * @param migrationWizard the migration wizard
     */
    public ProjectMigrationWizardDialog(Shell parentShell, ProjectMigrationWizard migrationWizard) {
        super(parentShell, migrationWizard);
        setPageSize(DEFAULT_WIZARD_WIDTH, SWT.DEFAULT);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.WizardDialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        super.createButtonsForButtonBar(parent);
        // no back button (already disabled by wizard)
        getButton(IDialogConstants.BACK_ID).setVisible(false);
        getButton(IDialogConstants.FINISH_ID).setText(Messages.projectMigrationExecuteAllSteps);
    }

    @Override
    protected void setButtonLayoutData(Button button) {
        GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        int widthHint = convertHorizontalDLUsToPixels(IDialogConstants.FINISH_LABEL.equals(button.getText())
                ? FINISH_BUTTON_WIDTH : IDialogConstants.BUTTON_WIDTH);

        // On large fonts this can make this dialog huge
        widthHint = Math.min(widthHint,
                button.getDisplay().getBounds().width / 5);
        Point minSize = button.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
        data.widthHint = Math.max(widthHint, minSize.x);

        button.setLayoutData(data);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.WizardDialog#updateButtons()
     */
    @Override
    public void updateButtons() {
        super.updateButtons();
        var currentPage = getCurrentPage();
        boolean hasNextPage = currentPage != null && currentPage.getNextPage() != null;
        if (!hasNextPage) {
            // show Finish rather than Execute all steps
            getButton(IDialogConstants.FINISH_ID).setText(IDialogConstants.FINISH_LABEL);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.Dialog#getDialogBoundsStrategy()
     */
    @Override
    protected int getDialogBoundsStrategy() {
        // do not persist dialog location to always center it above parent wizard
        return DIALOG_PERSISTSIZE;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.WizardDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        var ctrl = super.createDialogArea(parent);
        // make progress monitor part visible as we execute step by step
        ((Control) getProgressMonitor()).setVisible(true);
        return ctrl;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.WizardDialog#run(boolean, boolean, org.eclipse.jface.operation.IRunnableWithProgress)
     */
    @Override
    public void run(boolean fork, boolean cancelable, IRunnableWithProgress runnable)
            throws InvocationTargetException, InterruptedException {
        ((Control) getProgressMonitor()).setVisible(true);

        IProgressMonitor progressMonitor = Optional.ofNullable(getProgressMonitor())
                .orElseGet(NullProgressMonitor::new);
        try {
            ModalContext.run(runnable, fork, progressMonitor, getShell().getDisplay());
        } finally {
            // explicitly invoke done() on our progress monitor so that its
            // label does not spill over to the next invocation, see bug 271530
            progressMonitor.setTaskName("");
            progressMonitor.done();
        }
    }

}
