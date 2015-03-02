package org.bonitasoft.studio.businessobject.ui.expression;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.wizard.AddBusinessObjectDataWizard;
import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.expression.editor.provider.IProposalListener;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/

/**
 * @author Romain Bioteau
 */
public class CreateBusinessDataProposalListener implements IProposalListener {

    public CreateBusinessDataProposalListener() {
    }

    @Override
    public String handleEvent(EObject context, final String fixedReturnType) {
        Assert.isNotNull(context);
        while (!(context instanceof Pool)) {
            context = context.eContainer();
        }

        final BusinessObjectModelRepositoryStore repositoryStore = RepositoryManager.getInstance().getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        final AddBusinessObjectDataWizard newWizard = new AddBusinessObjectDataWizard((DataAware) context, repositoryStore,
                TransactionUtil.getEditingDomain(context));
        Shell activeShell = Display
                .getDefault().getActiveShell();
        if (activeShell.getParent() != null) {
            activeShell = activeShell.getParent().getShell();
        }
        final CustomWizardDialog wizardDialog = new CustomWizardDialog(activeShell,
                newWizard, IDialogConstants.FINISH_LABEL);
        if (wizardDialog.open() == Dialog.OK) {
            final EObject obj = newWizard.getBusinessObjectData();
            if (obj instanceof Data) {
                final Data d = (Data) obj;
                if (d != null) {
                    return d.getName();
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return Messages.createBusinessDataVariable;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#isPageFlowContext()
     */
    @Override
    public boolean isPageFlowContext() {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#setIsPageFlowContext(boolean)
     */
    @Override
    public void setIsPageFlowContext(final boolean isPageFlowContext) {

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IProposalListener#setEStructuralFeature(org.eclipse.emf.ecore.EStructuralFeature)
     */
    @Override
    public void setEStructuralFeature(final EStructuralFeature feature) {
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#isOverViewContext()
     */
    @Override
    public boolean isOverViewContext() {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#setIsOverviewContext(boolean)
     */
    @Override
    public void setIsOverviewContext(final boolean isOverviewContext) {
    }

    @Override
    public boolean isRelevant(final EObject context) {
        return context instanceof Pool;
    }

}
