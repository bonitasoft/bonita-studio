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
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.wizard.ExportBusinessDataModelWizard;
import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.SWT;

/**
 * @author Romain Bioteau
 * 
 */
public class ExportBusinessDataModelHandler extends AbstractBusinessObjectHandler {

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {
        BusinessObjectModelRepositoryStore store = getStore();
        IWizard exportWizard = createWizard(store);
        CustomWizardDialog dialog = createWizardDialog(exportWizard, Messages.export);
        dialog.setPageSize(SWT.DEFAULT, 100);
        return dialog.open();
    }

    protected IWizard createWizard(
            BusinessObjectModelRepositoryStore store) {
        return new ExportBusinessDataModelWizard(store);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        BusinessObjectModelFileStore fileStore = getStore().getChild(BusinessObjectModelFileStore.DEFAULT_BDM_FILENAME);
        return fileStore != null && fileStore.getContent() != null && !fileStore.getContent().getBusinessObjects().isEmpty();
    }

}
