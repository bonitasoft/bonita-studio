/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.businessobject.ui.handler;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.wizard.ManageBusinessDataModelWizard;
import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

/**
 * @author Romain Bioteau
 * 
 */
public class ManageBusinessObjectHandler extends AbstractBusinessObjectHandler {

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ManageBusinessDataModelWizard newBusinessDataModelWizard = createWizard();
        CustomWizardDialog dialog = createWizardDialog(newBusinessDataModelWizard, IDialogConstants.FINISH_LABEL);

        return dialog.open() == IDialogConstants.OK_ID;
    }

    protected CustomWizardDialog createWizardDialog(IWizard wizard, String finishLabel) {
        CustomWizardDialog dialog = new CustomWizardDialog(getShell(), wizard, finishLabel) {

            @Override
            protected Control createHelpControl(Composite parent) {
                Control helpControl = super.createHelpControl(parent);
                if (helpControl instanceof ToolBar) {
                    ToolItem toolItem = ((ToolBar) helpControl).getItem(0);
                    toolItem.setToolTipText(Messages.howToUseBusinessObjects);
                }
                return helpControl;

            }
        };
        dialog.setHelpAvailable(true);
        return dialog;
    }

    protected ManageBusinessDataModelWizard createWizard() {
        BusinessObjectModelFileStore fileStore = getStore().getChild(BusinessObjectModelFileStore.DEFAULT_BDM_FILENAME);
        if (fileStore == null) {
            fileStore = getStore().createRepositoryFileStore(BusinessObjectModelFileStore.DEFAULT_BDM_FILENAME);
        }
        return new ManageBusinessDataModelWizard(fileStore);
    }

}
