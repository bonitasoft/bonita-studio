/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.handler;

import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.team.ui.dialog.DynamicLabelWizardDialog;
import org.bonitasoft.studio.team.ui.wizard.ResotrePointWizard;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * @author Baptiste Mesta
 */
public class CreateRestorePointHandler extends AbstractConnectedHandler {

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final boolean closed = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeAllEditors(true);
        Display.getDefault().syncExec(PlatformUtil::showIntroPart);
        if (closed) {
            final Shell activeShell = Display.getDefault().getActiveShell();
            final Wizard wizard = new ResotrePointWizard();
            new DynamicLabelWizardDialog(activeShell, wizard, IDialogConstants.FINISH_LABEL).open();
        }
        return null;
    }

}
