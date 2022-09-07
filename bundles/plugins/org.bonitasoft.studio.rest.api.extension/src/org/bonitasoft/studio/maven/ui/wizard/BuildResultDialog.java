/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.ui.wizard;

import org.bonitasoft.studio.maven.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class BuildResultDialog extends MessageDialog {

    public BuildResultDialog(final Shell parentShell, final IStatus status) {
        super(parentShell, status.isOK() ? Messages.buildSuccess : Messages.buildFailure, null, status.getMessage(), MessageDialog.INFORMATION,
                new String[] { IDialogConstants.CLOSE_LABEL }, 0);
        setShellStyle(SWT.RESIZE | SWT.SHELL_TRIM);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IconAndMessageDialog#createMessageArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createMessageArea(final Composite composite) {
        if (message != null) {
            final StyledText styledText = new StyledText(composite, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
            styledText.setText(message);
            styledText.setEditable(false);
            GridDataFactory
                    .fillDefaults()
                    .align(SWT.FILL, SWT.BEGINNING)
                    .grab(true, true)
                    .hint(
                            convertHorizontalDLUsToPixels(400),
                            convertHorizontalDLUsToPixels(400))
                    .applyTo(styledText);
        }
        return composite;
    }

}
