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
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMappingFactory;
import org.bonitasoft.studio.contract.core.mapping.RootContractInputGenerator;
import org.bonitasoft.studio.contract.core.mapping.expression.FieldToContractInputMappingExpressionBuilder;
import org.bonitasoft.studio.contract.core.mapping.operation.FieldToContractInputMappingOperationBuilder;
import org.bonitasoft.studio.contract.core.mapping.operation.OperationCreationException;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ContractContainer;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.OperationContainer;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.ISharedImages;

/**
 * @author aurelie
 */
public class ContractInputGenerationWizard extends Wizard {

    static final String HIDE_GENERATION_SUCCESS_DIALOG = "SHOW_GENERATION_SUCCESS_DIALOG";
    private final EditingDomain editingDomain;
    private final ContractContainer contractContainer;
    private CreateContractInputFromBusinessObjectWizardPage contractInputFromBusinessObjectWizardPage;
    private List<Data> availableBusinessData;
    private WritableValue selectedDataObservable;
    private final FieldToContractInputMappingFactory fieldToContractInputMappingFactory;
    private final RepositoryAccessor repositoryAccessor;
    private final FieldToContractInputMappingOperationBuilder operationBuilder;
    private final IPreferenceStore preferenceStore;
    private final ISharedImages sharedImagesService;
    private final FieldToContractInputMappingExpressionBuilder expressionBuilder;

    public ContractInputGenerationWizard(final ContractContainer contractContainer,
            final EditingDomain editingDomain,
            final RepositoryAccessor repositoryAccessor,
            final FieldToContractInputMappingOperationBuilder operationBuilder,
            final FieldToContractInputMappingExpressionBuilder expressionBuilder,
            final IPreferenceStore preferenceStore,
            final ISharedImages sharedImagesService) {
        setWindowTitle(Messages.contractInputGenerationTitle);
        setDefaultPageImageDescriptor(Pics.getWizban());
        this.contractContainer = contractContainer;
        this.editingDomain = editingDomain;
        this.repositoryAccessor = repositoryAccessor;
        fieldToContractInputMappingFactory = new FieldToContractInputMappingFactory();
        this.operationBuilder = operationBuilder;
        this.expressionBuilder = expressionBuilder;
        this.preferenceStore = preferenceStore;
        this.sharedImagesService = sharedImagesService;
    }

    @Override
    public void addPages() {
        selectedDataObservable = new WritableValue();
        availableBusinessData = availableBusinessData();
        if (!availableBusinessData.isEmpty()) {
            selectedDataObservable.setValue(availableBusinessData.get(0));
        }
        addPage(new SelectBusinessDataWizardPage(availableBusinessData, selectedDataObservable,
                repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)));
        contractInputFromBusinessObjectWizardPage = new CreateContractInputFromBusinessObjectWizardPage(contractContainer.getContract(),
                selectedDataObservable, fieldToContractInputMappingFactory, repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class),
                sharedImagesService);
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
        final BusinessObjectData data = (BusinessObjectData) selectedDataObservable.getValue();
        final RootContractInputGenerator contractInputGenerator = new RootContractInputGenerator(contractInputFromBusinessObjectWizardPage.getRootName(),
                contractInputFromBusinessObjectWizardPage.getMappings(), repositoryAccessor, operationBuilder, expressionBuilder);
        try {
            contractInputGenerator.build(data);
        } catch (final OperationCreationException e) {
            BonitaStudioLog.error("Failed to create Operations from contract", e);
            new BonitaErrorDialog(getShell(), Messages.errorTitle, Messages.contractFromDataCreationErrorMessage, e).open();
            return false;
        }
        editingDomain.getCommandStack().execute(createCommand(contractInputGenerator, data));
        openWarningDialog(contractInputGenerator.isAllAttributesGenerated());
        openInfoDialog();
        return true;
    }

    protected CompoundCommand createCommand(final RootContractInputGenerator contractInputGenerator, final BusinessObjectData data) {
        final CompoundCommand cc = new CompoundCommand();
        cc.append(AddCommand.create(editingDomain, contractContainer.getContract(), ProcessPackage.Literals.CONTRACT__INPUTS,
                contractInputGenerator.getRootContractInput()));
        if (contractContainer instanceof OperationContainer && contractInputFromBusinessObjectWizardPage.getIsAutogeneratedOperation()) {
            cc.appendIfCanExecute(AddCommand.create(editingDomain, contractContainer, ProcessPackage.Literals.OPERATION_CONTAINER__OPERATIONS,
                    contractInputGenerator.getMappingOperations()));
        }
        if (contractContainer instanceof Pool && contractInputFromBusinessObjectWizardPage.getIsAutogeneratedScript()) {
            cc.appendIfCanExecute(SetCommand.create(editingDomain, data, ProcessPackage.Literals.DATA__DEFAULT_VALUE,
                    contractInputGenerator.getInitialValueExpression()));

        }
        return cc;
    }

    protected void openInfoDialog() {
        if (contractContainer instanceof OperationContainer && isNullOrEmpty(preferenceStore.getString(HIDE_GENERATION_SUCCESS_DIALOG))) {
            MessageDialogWithToggle.openInformation(getShell(),
                    Messages.contractGenerationTitle,
                    Messages.contractGenerationMsg,
                    Messages.doNotShowMeAgain,
                    false,
                    preferenceStore,
                    HIDE_GENERATION_SUCCESS_DIALOG);
        }
    }

    protected void openWarningDialog(final boolean allAttributesGenerated) {
        if (!allAttributesGenerated) {
            MessageDialog.openWarning(getShell(), Messages.notAllAttributesGeneratedTitle, Messages.notAllAttributesGeneratedMsg);
        }
    }
    
     /**
     * @return
     */
    public CreateContractInputFromBusinessObjectWizardPage getContractInputFromBusinessObjectWizardPage() {
        return contractInputFromBusinessObjectWizardPage;
    }
}
