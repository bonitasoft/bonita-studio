/**
 * Copyright (C) 2012-2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.data.ui.wizard;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.DataTypeLabels;
import org.bonitasoft.studio.common.DatasourceConstants;
import org.bonitasoft.studio.common.IBonitaVariableContext;
import org.bonitasoft.studio.common.emf.tools.EMFModelUpdater;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.refactoring.core.RefactorDataOperation;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Romain Bioteau
 */
public class DataWizard extends Wizard implements IBonitaVariableContext {

    private final EObject container;

    private Data dataWorkingCopy;

    private boolean editMode = false;

    private Data originalData;

    private final Set<EStructuralFeature> featureToCheckForUniqueID;

    private EStructuralFeature dataContainmentFeature;

    private boolean showAutogenerateForm;

    private DataWizardPage page;

    private String fixedReturnType;

    private final TransactionalEditingDomain editingDomain;

    private boolean isPageFlowContext = false;

    private boolean isOverviewContext = false;

    private EMFModelUpdater<Data> dataUpdater;

    public DataWizard(final TransactionalEditingDomain editingDomain, final EObject container, final EStructuralFeature dataContainmentFeature,
            final Set<EStructuralFeature> featureToCheckForUniqueID,
            final boolean showAutogenerateForm) {
        initDataWizard(dataContainmentFeature, showAutogenerateForm);
        this.editingDomain = editingDomain;
        this.container = container;
        dataWorkingCopy = createWorkingCopy(container);
        editMode = false;
        this.featureToCheckForUniqueID = new HashSet<EStructuralFeature>();
        this.featureToCheckForUniqueID.add(dataContainmentFeature);
        setWindowTitle(Messages.newVariable);
    }

    public DataWizard(final TransactionalEditingDomain editingDomain, final EObject container, final EStructuralFeature dataContainmentFeature,
            final Set<EStructuralFeature> featureToCheckForUniqueID,
            final boolean showAutogenerateForm, final String fixedReturnType) {
        initDataWizard(dataContainmentFeature, showAutogenerateForm);
        this.editingDomain = editingDomain;
        this.container = container;
        dataWorkingCopy = createWorkingCopy(container);
        editMode = false;
        this.featureToCheckForUniqueID = new HashSet<EStructuralFeature>();
        this.featureToCheckForUniqueID.add(dataContainmentFeature);
        this.fixedReturnType = fixedReturnType;
        setWindowTitle(Messages.newVariable);
    }

    private Data createWorkingCopy(final EObject container) {
        final Data dataWorkingCopy = ProcessFactory.eINSTANCE.createData();
        dataWorkingCopy.setDataType(ModelHelper.getDataTypeForID(container, DataTypeLabels.stringDataType));
        return dataWorkingCopy;
    }

    public DataWizard(final TransactionalEditingDomain editingDomain, final Data data, final EStructuralFeature dataContainmentFeature,
            final Set<EStructuralFeature> featureToCheckForUniqueID, final boolean showAutogenerateForm) {
        initDataWizard(dataContainmentFeature, showAutogenerateForm);
        Assert.isNotNull(data);
        this.editingDomain = editingDomain;
        setNeedsProgressMonitor(true);
        container = data.eContainer();
        originalData = data;
        this.dataUpdater = new EMFModelUpdater<Data>().from(data);
        dataWorkingCopy = dataUpdater.getWorkingCopy();
        editMode = true;
        this.featureToCheckForUniqueID = featureToCheckForUniqueID;
        setWindowTitle(Messages.editVariable);
    }

    public DataWizard(final TransactionalEditingDomain editingDomain, final EObject dataContainer, final Data wc,
            final EStructuralFeature dataContainmentFeature,
            final Set<EStructuralFeature> featureToCheckForUniqueID, final boolean showAutogenerate, final String fixedReturnType) {
        this(editingDomain, dataContainer, dataContainmentFeature, featureToCheckForUniqueID, showAutogenerate, fixedReturnType);
        dataWorkingCopy = wc;
    }

    private void initDataWizard(final EStructuralFeature dataContainmentFeature, final boolean showAutogenerateForm) {
        setDefaultPageImageDescriptor(Pics.getWizban());
        this.dataContainmentFeature = dataContainmentFeature;// the default add data on this feature
        this.showAutogenerateForm = showAutogenerateForm;
    }

    @Override
    public void addPages() {
        if (page == null) {
            page = getWizardPage();
            addPage(page);
        }
    }

    protected DataWizardPage getWizardPage() {
        DataWizardPage page = null;
        if (!dataContainmentFeature.equals(ProcessPackage.Literals.DATA_AWARE__DATA)) {
            page = new DataWizardPage(dataWorkingCopy, container, false, false, false, showAutogenerateForm, featureToCheckForUniqueID, fixedReturnType);
            page.setIsPageFlowContext(isPageFlowContext);
            page.setIsOverviewContext(isOverviewContext);
            if (editMode) {
                page.setTitle(Messages.editVariableTitle);
                page.setDescription(Messages.editVariableDescription);
            }
            return page;
        } else {
            final boolean isOnActivity = container instanceof Activity;
            page = new DataWizardPage(dataWorkingCopy, container, true, true, isOnActivity, showAutogenerateForm, featureToCheckForUniqueID, fixedReturnType);
            page.setIsPageFlowContext(isPageFlowContext);
            page.setIsOverviewContext(isOverviewContext);
        }
        if (editMode) {
            page.setTitle(Messages.editVariableTitle);
            page.setDescription(Messages.editVariableDescription);
        }
        return page;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        final Data workingCopy = getWorkingCopy();
        setDatasourceId(workingCopy, dataContainmentFeature);
        if (editMode) {
            final RefactorDataOperation op = createRefactorOperation(editingDomain, workingCopy);
            final boolean switchingDataEClass = !originalData.eClass().equals(workingCopy.eClass());
            op.setUpdateDataReferences(switchingDataEClass);
            op.setAskConfirmation(isEdited(workingCopy));
            if (op.canExecute()) {
                try {
                    getContainer().run(false, true, op);
                } catch (final InvocationTargetException e) {
                    BonitaStudioLog.error(e);
                    return false;
                } catch (final InterruptedException e) {
                    BonitaStudioLog.error(e);
                    return false;
                }
                if (op.isCancelled()) {
                    return false;
                }
            }
        } else {
            editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, container, dataContainmentFeature, workingCopy));
        }
        if (page != null) {
            page.setWorkingCopy(createWorkingCopy(container));
        }
        return true;
    }

    /**
     * @param workingCopy
     * @return
     */
    protected boolean isEdited(final Data workingCopy) {
        return !(originalData.eClass().equals(workingCopy.eClass()) && originalData.getName().equals(workingCopy.getName()) && originalData.getDataType()
                .equals(workingCopy.getDataType()));
    }

    protected RefactorDataOperation createRefactorOperation(final TransactionalEditingDomain editingDomain, final Data workingCopy) {
        final RefactorDataOperation op = new RefactorDataOperation(RefactoringOperationType.UPDATE);
        op.setEditingDomain(editingDomain);
        dataUpdater.editWorkingCopy(workingCopy);
        op.setUpdater(dataUpdater);
        op.addItemToRefactor(workingCopy, originalData);
        op.setDataContainer((DataAware) container);
        op.setDataContainmentFeature(dataContainmentFeature);
        op.setAskConfirmation(true);
        return op;
    }

    public Data getNewData() {
        return dataWorkingCopy;
    }

    public Data getWorkingCopy() {
        return page.getWorkingCopy();
    }

    private void setDatasourceId(final Data workingCopy, final EStructuralFeature feature) {
        if (workingCopy.isTransient()) {
            workingCopy.setDatasourceId(DatasourceConstants.IN_MEMORY_DATASOURCE);
        } else {
            workingCopy.setDatasourceId(DatasourceConstants.BOS_DATASOURCE);
        }
    }

    public Data getOriginalData() {
        return originalData;
    }

    @Override
    public boolean isPageFlowContext() {
        return isPageFlowContext;
    }

    @Override
    public void setIsPageFlowContext(final boolean isPageFlowContext) {
        this.isPageFlowContext = isPageFlowContext;

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#isOverViewContext()
     */
    @Override
    public boolean isOverViewContext() {
        return isOverviewContext;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#setIsOverviewContext(boolean)
     */
    @Override
    public void setIsOverviewContext(final boolean isOverviewContext) {
        this.isOverviewContext = isOverviewContext;

    }

}
