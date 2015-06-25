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
package org.bonitasoft.studio.contract.ui.wizard;

import static com.google.common.base.Predicates.instanceOf;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMappingFactory;
import org.bonitasoft.studio.contract.core.mapping.RootContractInputGenerator;
import org.bonitasoft.studio.contract.core.mapping.operation.OperationCreationException;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ContractContainer;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.OperationContainer;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author aurelie
 */
public class ContractInputGenerationWizard extends Wizard {

    private final BusinessObjectModelRepositoryStore businessObjectStore;
    private final EditingDomain editingDomain;
    private final ContractContainer contractContainer;
    private CreateContractInputFromBusinessObjectWizardPage contractInputFromBusinessObjectWizardPage;
    private List<Data> availableBusinessData;
    private WritableValue selectedDataObservable;

    public ContractInputGenerationWizard(final ContractContainer contractContainer, final EditingDomain editingDomain,
            final BusinessObjectModelRepositoryStore businessObjectStore) {
        setWindowTitle(Messages.contractInputGenerationTitle);
        setDefaultPageImageDescriptor(Pics.getWizban());
        this.contractContainer = contractContainer;
        this.editingDomain = editingDomain;
        this.businessObjectStore = businessObjectStore;
    }

    @Override
    public void addPages() {
        selectedDataObservable = new WritableValue();
        availableBusinessData = availableBusinessData();
        if (!availableBusinessData.isEmpty()) {
            selectedDataObservable.setValue(availableBusinessData.get(0));
        }
        addPage(new SelectBusinessDataWizardPage(availableBusinessData, selectedDataObservable, businessObjectStore));
        contractInputFromBusinessObjectWizardPage = new CreateContractInputFromBusinessObjectWizardPage(contractContainer.getContract(),
                selectedDataObservable, new FieldToContractInputMappingFactory(businessObjectStore));
        contractInputFromBusinessObjectWizardPage.setTitle();
        addPage(contractInputFromBusinessObjectWizardPage);
    }

    protected List<Data> availableBusinessData() {
        final AbstractProcess pool = ModelHelper.getParentProcess(contractContainer);
        return newArrayList(filter(pool.getData(), instanceOf(BusinessObjectData.class)));
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#canFinish()
     */
    @Override
    public boolean canFinish() {
        if (availableBusinessData.isEmpty()) {
            return false;
        }
        return super.canFinish();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        final RootContractInputGenerator contractInputGenerator = new RootContractInputGenerator(contractInputFromBusinessObjectWizardPage.getRootName(),
                contractInputFromBusinessObjectWizardPage.getMappings());
        try {
            contractInputGenerator.build((BusinessObjectData) selectedDataObservable.getValue());
        } catch (final OperationCreationException e) {
            BonitaStudioLog.error("Failed to create Operations from contract", e);
            new BonitaErrorDialog(getShell(), Messages.errorTitle, Messages.contractFromDataCreationErrorMessage, e).open();
            return false;
        }

        final CompoundCommand cc = new CompoundCommand();
        cc.append(AddCommand.create(editingDomain, contractContainer.getContract(), ProcessPackage.Literals.CONTRACT__INPUTS,
                contractInputGenerator.getRootContractInput()));
        if (contractContainer instanceof OperationContainer) {
            cc.appendIfCanExecute(AddCommand.create(editingDomain, contractContainer, ProcessPackage.Literals.OPERATION_CONTAINER__OPERATIONS,
                    contractInputGenerator.getMappingOperations()));

        }
        editingDomain.getCommandStack().execute(cc);
        return true;
    }
}
