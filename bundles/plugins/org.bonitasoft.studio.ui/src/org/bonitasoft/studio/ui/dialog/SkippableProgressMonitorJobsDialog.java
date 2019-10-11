/**
 * Copyright (C) 2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.ui.dialog;

import org.bonitasoft.studio.ui.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.internal.progress.ProgressMonitorJobsDialog;


public class SkippableProgressMonitorJobsDialog extends ProgressMonitorJobsDialog {

    private Button skipButton;

    public SkippableProgressMonitorJobsDialog(final Shell parent) {
        super(parent);
    }

    @Override
    protected void createDetailsButton(final Composite parent) {
        skipButton = createButton(parent, IDialogConstants.DETAILS_ID,
                Messages.skipValidation,
                false);
        skipButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                cancelPressed();
            }
            
        });
        skipButton.setCursor(arrowCursor);
        skipButton.setVisible(enableDetailsButton);
    }

    @Override
    protected void createCancelButton(final Composite parent) {
        //Cancel not supported
    }

    @Override
    protected void cancelPressed() {
        getProgressMonitor().setCanceled(true);
        setReturnCode(CANCEL);
        close();
    }

    public SkippableProgressMonitorJobsDialog canBeSkipped() {
        enableDetails(true);
        return this;
    }

    @Override
    protected void updateForSetBlocked(final IStatus reason) {

    }

    @Override
    protected void enableDetails(final boolean enableState) {
        if (skipButton == null) {
            enableDetailsButton = enableState;
        } else {
            Display.getDefault().syncExec(new Runnable() {

                @Override
                public void run() {
                    skipButton.setVisible(enableState);
                }
            });

        }
    }
}
