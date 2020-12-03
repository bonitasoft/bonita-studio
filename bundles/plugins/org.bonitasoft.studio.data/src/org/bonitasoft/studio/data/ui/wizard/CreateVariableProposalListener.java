/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.data.ui.wizard;

import java.util.Collections;

import org.bonitasoft.studio.common.DataTypeLabels;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.IDataProposalListener;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ReceiveTask;
import org.bonitasoft.studio.model.process.SendTask;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * @author Maxence Raoux
 */
public class CreateVariableProposalListener implements IDataProposalListener {

    private boolean isPageFlowContext = true;

    private EStructuralFeature feature = ProcessPackage.Literals.DATA_AWARE__DATA;

    private boolean multipleData = false;

    @Override
    public String handleEvent(final EObject context, final String fixedReturnType) {
        Assert.isNotNull(context);
        final EObject dataContainer = getDataContainer(context);
        final Data dataWorkingCopy = ProcessFactory.eINSTANCE.createData();
        dataWorkingCopy.setMultiple(multipleData);
        dataWorkingCopy.setDataType(ModelHelper.getDataTypeForID(dataContainer, DataTypeLabels.stringDataType));
        final DataWizard newWizard = new DataWizard(TransactionUtil.getEditingDomain(context), dataContainer, dataWorkingCopy, feature,
                Collections.singleton(feature), true,
                fixedReturnType);
        newWizard.setIsPageFlowContext(isPageFlowContext);
        final CustomWizardDialog wizardDialog = new CustomWizardDialog(activeShell(), newWizard, IDialogConstants.FINISH_LABEL);
        if (wizardDialog.open() == Dialog.OK) {
            RepositoryManager.getInstance().getCurrentRepository().buildXtext();
            final Data newData = newWizard.getNewData();
            if (newData != null) {
                return newData.getName();
            }
        }
        return null;
    }

    protected Shell activeShell() {
        Shell activeShell = Display
                .getDefault().getActiveShell();
        if (activeShell != null && activeShell.getParent() != null) {
            activeShell = activeShell.getParent().getShell();
        }
        if(activeShell == null) {
            activeShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        }
        return activeShell;
    }

    protected EObject getDataContainer(EObject context) {
        while (!isValidContainer(context)) {
            context = context.eContainer();
        }
        return context;
    }

    private boolean isValidContainer(final EObject context) {
        return (context instanceof AbstractProcess
                || context instanceof Activity)
                && !(context instanceof SendTask)
                && !(context instanceof ReceiveTask);
    }

    @Override
    public String toString() {
        return Messages.createVariable;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#isPageFlowContext()
     */
    @Override
    public boolean isPageFlowContext() {
        return isPageFlowContext;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#setIsPageFlowContext(boolean)
     */
    @Override
    public void setIsPageFlowContext(final boolean isPageFlowContext) {
        this.isPageFlowContext = isPageFlowContext;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IProposalListener#setEStructuralFeature(org.eclipse.emf.ecore.EStructuralFeature)
     */
    @Override
    public void setEStructuralFeature(final EStructuralFeature feature) {
        this.feature = feature;

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
    public boolean isRelevant(final EObject context, final ISelection selection) {
        return true;
    }

    @Override
    public void setMultipleData(final boolean multipleData) {
        this.multipleData = multipleData;
    }
}
