/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.businessobject.ui.wizard;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.refactoring.core.RefactorDataOperation;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * @author Romain Bioteau
 *
 */
public class EditBusinessObjectDataWizard extends AbstractBusinessObjectWizard {

    private final BusinessObjectModelRepositoryStore businessObjectDefinitionStore;

    private BusinessObjectDataWizardPage editBusinessObjectDataWizardPage;

    private final TransactionalEditingDomain editingDomain;

    private final Set<String> existingNames;

    private final BusinessObjectData data;

    private final DataAware container;

    private final BusinessObjectData dataWorkingCopy;

    public EditBusinessObjectDataWizard(final BusinessObjectData data,
            final BusinessObjectModelRepositoryStore businessObjectDefinitionStore,
            final TransactionalEditingDomain editingDomain) {
        this.data = data;
        dataWorkingCopy = EcoreUtil.copy(data);
        container = (DataAware) data.eContainer();
        this.businessObjectDefinitionStore = businessObjectDefinitionStore;
        this.editingDomain = editingDomain;
        existingNames = computeExistingNames(container);
        existingNames.remove(data.getName());
        setDefaultPageImageDescriptor(Pics.getWizban());
    }

    @Override
    public void addPages() {
        editBusinessObjectDataWizardPage = createEditBusinessObjectDataWizardPage();
        addPage(editBusinessObjectDataWizardPage);
    }

    protected BusinessObjectDataWizardPage createEditBusinessObjectDataWizardPage() {
        final BusinessObjectDataWizardPage page = new BusinessObjectDataWizardPage(dataWorkingCopy, businessObjectDefinitionStore, getExistingNames());
        page.setTitle(Messages.bind(Messages.editBusinessObjectDataTitle, ModelHelper.getParentProcess(container).getName()));
        page.setDescription(Messages.editBusinessObjectDataDescription);
        return page;
    }

    public Set<String> getExistingNames() {
        return existingNames;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        final Data updatedData = editBusinessObjectDataWizardPage.getBusinessObjectData();
        final AbstractProcess process = ModelHelper.getParentProcess(container);
        final RefactorDataOperation op = new RefactorDataOperation(RefactoringOperationType.UPDATE);
        op.setEditingDomain(editingDomain);
        op.setContainer(process);
        op.addItemToRefactor(updatedData, data);
        op.setDirectDataContainer(container);
        op.setAskConfirmation(true);
        op.setDataContainmentFeature(ProcessPackage.Literals.DATA_AWARE__DATA);
        if (op.canExecute()) {
            try {
                getContainer().run(true, false, op);
            } catch (final InvocationTargetException e) {
                BonitaStudioLog.error(e);
                return false;
            } catch (final InterruptedException e) {
                BonitaStudioLog.error(e);
                return false;
            }
        }
        if (op.isCancelled()) {
            return false;
        }
        final CompoundCommand cc = new CompoundCommand();

        for (final EStructuralFeature feature : data.eClass().getEAllStructuralFeatures()) {
            cc.append(SetCommand.create(editingDomain, data, feature, updatedData.eGet(feature)));
        }
        editingDomain.getCommandStack().execute(cc);
        return !cc.getResult().isEmpty();
    }

}
