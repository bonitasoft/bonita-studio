/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.ui.expression;

import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.wizard.AddBusinessObjectDataWizard;
import org.bonitasoft.studio.common.DataTypeLabels;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.expression.editor.provider.IDataProposalListener;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Romain Bioteau
 */
public class CreateBusinessDataProposalListener implements IDataProposalListener {

    private boolean multipleData;

    @Override
    public String handleEvent(EObject context, final String fixedReturnType) {
        Assert.isNotNull(context);
        while (!(context instanceof Pool)) {
            context = context.eContainer();
        }
        final BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore = RepositoryManager
                .getInstance().getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        final AddBusinessObjectDataWizard newWizard = new AddBusinessObjectDataWizard((DataAware) context,
                newMultipleBusinessData(context), repositoryStore,
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

    private BusinessObjectData newMultipleBusinessData(final EObject context) {
        final BusinessObjectData businessObjectData = ProcessFactory.eINSTANCE.createBusinessObjectData();
        businessObjectData.setDataType(ModelHelper.getDataTypeForID(context, DataTypeLabels.businessObjectType));
        businessObjectData.setMultiple(multipleData);
        return businessObjectData;
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
    public boolean isRelevant(final EObject context, final ISelection selection) {
        if (selection == null || selection.isEmpty()) {
            return context instanceof Pool;
        }
        final Expression selected = (Expression) ((IStructuredSelection) selection).getFirstElement();
        return !selected.isReturnTypeFixed() || isSupportedType(selected.getReturnType());
    }

    private boolean isSupportedType(String returnType) {
        final BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore = RepositoryManager
                .getInstance().getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        final BusinessObjectModelFileStore child = repositoryStore.getChild(BusinessObjectModelFileStore.BOM_FILENAME, true);
        if (child != null) {
            BusinessObjectModel content;
            try {
                content = child.getContent();
                return content.getBusinessObjectsClassNames().contains(returnType) || Object.class.getName().equals(returnType);
            } catch (ReadFileStoreException e) {
              BonitaStudioLog.warning(e.getMessage(), BusinessObjectPlugin.PLUGIN_ID);
            }
        }
        return Object.class.getName().equals(returnType);

    }

    @Override
    public void setMultipleData(final boolean multipleData) {
        this.multipleData = multipleData;
    }

}
