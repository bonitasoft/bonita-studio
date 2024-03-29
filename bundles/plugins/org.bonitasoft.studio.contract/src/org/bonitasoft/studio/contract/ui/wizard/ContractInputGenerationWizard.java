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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.ui.jface.BonitaErrorDialog;
import org.bonitasoft.studio.contract.core.mapping.ContractConstraintBuilder;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMappingFactory;
import org.bonitasoft.studio.contract.core.mapping.RootContractInputGenerator;
import org.bonitasoft.studio.contract.core.mapping.expression.FieldToContractInputMappingExpressionBuilder;
import org.bonitasoft.studio.contract.core.mapping.operation.DocumentUpdateOperationBuilder;
import org.bonitasoft.studio.contract.core.mapping.operation.FieldToContractInputMappingOperationBuilder;
import org.bonitasoft.studio.contract.core.mapping.operation.OperationCreationException;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.contract.ui.wizard.GenerationOptions.EditMode;
import org.bonitasoft.studio.groovy.ui.viewer.GroovySourceViewerFactory;
import org.bonitasoft.bpm.model.expression.Operation;
import org.bonitasoft.bpm.model.process.AbstractProcess;
import org.bonitasoft.bpm.model.process.BusinessObjectData;
import org.bonitasoft.bpm.model.process.ContractConstraint;
import org.bonitasoft.bpm.model.process.ContractContainer;
import org.bonitasoft.bpm.model.process.ContractInput;
import org.bonitasoft.bpm.model.process.ContractInputType;
import org.bonitasoft.bpm.model.process.Data;
import org.bonitasoft.bpm.model.process.Document;
import org.bonitasoft.bpm.model.process.DocumentType;
import org.bonitasoft.bpm.model.process.OperationContainer;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.process.ProcessFactory;
import org.bonitasoft.bpm.model.process.ProcessPackage;
import org.bonitasoft.bpm.model.process.Task;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.ISharedImages;

/**
 * @author aurelie
 */
public class ContractInputGenerationWizard extends Wizard {

    private final EditingDomain editingDomain;
    private final ContractContainer contractContainer;
    private CreateContractInputFromBusinessObjectWizardPage contractInputFromBusinessObjectWizardPage;
    private List<Data> availableBusinessData;
    private WritableValue selectedDataObservable;
    private WritableValue<String> rootNameObservable;
    private WritableList fieldToContractInputMappingsObservable;
    private final FieldToContractInputMappingFactory fieldToContractInputMappingFactory;
    private final RepositoryAccessor repositoryAccessor;
    private final FieldToContractInputMappingOperationBuilder operationBuilder;
    private final IPreferenceStore preferenceStore;
    private final FieldToContractInputMappingExpressionBuilder expressionBuilder;
    private final ContractInputGenerationInfoDialogFactory infoDialogFactory;
    private final GenerationOptions generationOptions;
    private GeneratedScriptPreviewPage generatedScriptPreviewPage;
    private final ContractInputGenerationWizardPagesFactory contractInputWizardPagesFactory;
    private final GroovySourceViewerFactory sourceViewerFactory;
    private SelectDataWizardPage selectBusinessDataWizardPage;
    private List<Document> availableDocuments;
    private ContractConstraintBuilder contractConstraintBuilder;

    public ContractInputGenerationWizard(final ContractContainer contractContainer,
            final EditingDomain editingDomain,
            final RepositoryAccessor repositoryAccessor,
            final FieldToContractInputMappingOperationBuilder operationBuilder,
            final FieldToContractInputMappingExpressionBuilder expressionBuilder,
            final ContractConstraintBuilder contractConstraintBuilder,
            final IPreferenceStore preferenceStore,
            final ISharedImages sharedImagesService,
            final ContractInputGenerationInfoDialogFactory infoDialogFactory,
            final ContractInputGenerationWizardPagesFactory contractInputWizardPagesFactory,
            final GroovySourceViewerFactory sourceViewerFactory) {
        setWindowTitle(Messages.contractInputGenerationTitle);
        setDefaultPageImageDescriptor(Pics.getWizban());
        setNeedsProgressMonitor(true);
        this.contractContainer = contractContainer;
        generationOptions = new GenerationOptions();
        this.editingDomain = editingDomain;
        this.repositoryAccessor = repositoryAccessor;
        fieldToContractInputMappingFactory = new FieldToContractInputMappingFactory(
                repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class));
        this.operationBuilder = operationBuilder;
        this.expressionBuilder = expressionBuilder;
        this.contractConstraintBuilder = contractConstraintBuilder;
        this.preferenceStore = preferenceStore;
        this.infoDialogFactory = infoDialogFactory;
        this.contractInputWizardPagesFactory = contractInputWizardPagesFactory;
        this.sourceViewerFactory = sourceViewerFactory;

    }

    @Override
    public void addPages() {
        selectedDataObservable = new WritableValue<>();
        rootNameObservable = new WritableValue<>();
        fieldToContractInputMappingsObservable = new WritableList(new ArrayList<FieldToContractInputMapping>(),
                FieldToContractInputMapping.class);
        availableBusinessData = availableBusinessData();
        if (!availableBusinessData.isEmpty()) {
            selectedDataObservable.setValue(availableBusinessData.get(0));
        } else {
            final Pool pool = ModelHelper.getParentPool(contractContainer);
            if (!pool.getDocuments().isEmpty()) {
                selectedDataObservable.setValue(pool.getDocuments().get(0));
            }
        }
        availableDocuments = ModelHelper.getParentPool(contractContainer).getDocuments();
        selectBusinessDataWizardPage = contractInputWizardPagesFactory.createSelectBusinessDataWizardPage(
                contractContainer.getContract(),
                availableBusinessData,
                availableDocuments,
                selectedDataObservable,
                rootNameObservable,
                generationOptions,
                repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class));
        addPage(selectBusinessDataWizardPage);
        contractInputFromBusinessObjectWizardPage = contractInputWizardPagesFactory
                .createCreateContratInputFromBusinessObjectWizardPage(
                        contractContainer,
                        generationOptions,
                        selectedDataObservable,
                        rootNameObservable,
                        fieldToContractInputMappingFactory,
                        fieldToContractInputMappingsObservable,
                        repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class));
        contractInputFromBusinessObjectWizardPage.setTitle();
        addPage(contractInputFromBusinessObjectWizardPage);
        if (contractContainer instanceof Pool) {
            generatedScriptPreviewPage = contractInputWizardPagesFactory.createGeneratedScriptPreviewPage(
                    rootNameObservable,
                    fieldToContractInputMappingsObservable, selectedDataObservable, repositoryAccessor,
                    operationBuilder,
                    expressionBuilder,
                    sourceViewerFactory);
            generatedScriptPreviewPage.setDescription();
            addPage(generatedScriptPreviewPage);
        }
    }

    protected List<Data> availableBusinessData() {
        final AbstractProcess pool = ModelHelper.getParentProcess(contractContainer);
        return newArrayList(filter(pool.getData(), instanceOf(BusinessObjectData.class)));
    }

    @Override
    public boolean canFinish() {
        if (availableBusinessData.isEmpty() && availableDocuments.isEmpty()) {
            return false;
        }
        return super.canFinish();
    }

    @Override
    public boolean performFinish() {
        final Object selectedData = selectedDataObservable.getValue();
        if (selectedData instanceof BusinessObjectData) {
            return performFinishForBusinessObjectData();
        }
        if (selectedData instanceof Document) {
            return performFinishForDocument();
        }
        return true;
    }

    private boolean performFinishForBusinessObjectData() {
        final BusinessObjectData data = (BusinessObjectData) selectedDataObservable.getValue();
        final RootContractInputGenerator contractInputGenerator = getRootContractGenerator();
        final int returnCode = openInfoDialog();
        if (returnCode == MessageDialogWithToggle.OK
                || returnCode == ContractInputGenerationInfoDialogFactory.NOT_OPENED) {
            try {
                getContainer().run(true, false, buildContractOperationFromData(data, contractInputGenerator));
            } catch (InvocationTargetException | InterruptedException e) {
                BonitaStudioLog.error("Failed to create Operations from contract", e);
                new BonitaErrorDialog(getShell(), Messages.errorTitle, Messages.contractFromDataCreationErrorMessage,
                        e.getCause())
                                .open();
                return false;
            }
            openWarningDialog(contractInputGenerator.isAllAttributesGenerated());
            return true;
        }
        contractInputFromBusinessObjectWizardPage.disableAutoGeneration();
        getContainer().showPage(contractInputFromBusinessObjectWizardPage);
        contractInputFromBusinessObjectWizardPage.setPreviousPage(selectBusinessDataWizardPage);
        return false;
    }

    protected IRunnableWithProgress buildContractOperationFromData(BusinessObjectData data,
            RootContractInputGenerator contractInputGenerator) {
        return monitor -> {
            try {
                if (contractContainer instanceof Pool) {
                    contractInputGenerator.buildForInstanciation(data, monitor);
                } else {
                    contractInputGenerator.build(data, generationOptions.getEditMode(), monitor);
                }
                monitor.beginTask("Generating contract constraints...", IProgressMonitor.UNKNOWN);
                List<ContractConstraint> constraints = contractConstraintBuilder.buildConstraints(
                        contractInputGenerator.getRootContractInput(), ModelHelper.getParentPool(contractContainer));
                editingDomain.getCommandStack().execute(createCommand(contractInputGenerator, data, constraints));
            } catch (final OperationCreationException e) {
                throw new InvocationTargetException(e);
            }
        };
    }

    private boolean performFinishForDocument() {
        final Document document = (Document) selectedDataObservable.getValue();
        if (!(contractContainer instanceof Task)) {
            infoDialogFactory.openUpdateDocumentInitalContentWarning(document.getName(), getShell());
        }
        try {
            getContainer().run(true, false, buildContractOperationFromDocument(document));
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error("Failed to create operation for document.", e);
            return false;
        }
        return true;
    }

    private IRunnableWithProgress buildContractOperationFromDocument(Document document) {
        return monitor -> {
            monitor.beginTask("", IProgressMonitor.UNKNOWN);
            ContractInput input = createDocumentContractInput(document);
            CompoundCommand cc = new CompoundCommand();
            cc.append(AddCommand.create(editingDomain, contractContainer.getContract(),
                    ProcessPackage.Literals.CONTRACT__INPUTS,
                    input));
            if (contractContainer instanceof Task) {
                createDocumentUpdateOperation(document, input, cc);
            } else {
                cc.append(SetCommand.create(editingDomain, document, ProcessPackage.Literals.DOCUMENT__DOCUMENT_TYPE,
                        DocumentType.CONTRACT));
                cc.append(
                        SetCommand.create(editingDomain, document, ProcessPackage.Literals.DOCUMENT__CONTRACT_INPUT,
                                input));
            }
            editingDomain.getCommandStack().execute(cc);
        };
    }

    protected ContractInput createDocumentContractInput(Document document) {
        ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setType(ContractInputType.FILE);
        input.setMultiple(document.isMultiple());
        input.setName(selectBusinessDataWizardPage.getRootName());
        input.setDataReference(document.getName());
        if (contractContainer instanceof Task) {
            input.setCreateMode(generationOptions.getEditMode() == EditMode.CREATE);
        }
        return input;
    }

    private void createDocumentUpdateOperation(Document document, ContractInput input,
            CompoundCommand cc) {
        Operation operation = new DocumentUpdateOperationBuilder(input, document).toOperation();
        cc.append(AddCommand.create(editingDomain, contractContainer,
                ProcessPackage.Literals.OPERATION_CONTAINER__OPERATIONS, operation));
        infoDialogFactory.openUpdateDocumentOperationWarning(document.getName(), getShell());
    }

    protected RootContractInputGenerator getRootContractGenerator() {
        RootContractInputGenerator contractInputGenerator;
        if (generatedScriptPreviewPage != null && generatedScriptPreviewPage.getRootContractInputGenerator() != null) {
            contractInputGenerator = generatedScriptPreviewPage.getRootContractInputGenerator();
        } else {
            contractInputGenerator = new RootContractInputGenerator(rootNameObservable.getValue(),
                    contractInputFromBusinessObjectWizardPage.getMappings(), repositoryAccessor, operationBuilder,
                    expressionBuilder);
        }
        return contractInputGenerator;
    }

    private int openInfoDialog() {
        return infoDialogFactory.openInfoDialog(preferenceStore, getShell(), contractContainer,
                generationOptions.isAutoGeneratedScript());
    }

    protected CompoundCommand createCommand(final RootContractInputGenerator contractInputGenerator,
            final BusinessObjectData data, List<ContractConstraint> constraints) {
        final CompoundCommand cc = new CompoundCommand();
        cc.append(AddCommand.create(editingDomain, contractContainer.getContract(),
                ProcessPackage.Literals.CONTRACT__INPUTS,
                contractInputGenerator.getRootContractInput()));
        cc.append(SetCommand.create(editingDomain, contractContainer.getContract(),
                ProcessPackage.Literals.CONTRACT__CONSTRAINTS,
                constraints));

        if (contractContainer instanceof OperationContainer
                && generationOptions.isAutoGeneratedScript()) {
            cc.appendIfCanExecute(AddCommand.create(editingDomain, contractContainer,
                    ProcessPackage.Literals.OPERATION_CONTAINER__OPERATIONS,
                    contractInputGenerator.getMappingOperations()));
        }
        if (contractContainer instanceof Pool && generationOptions.isAutoGeneratedScript()) {
            cc.appendIfCanExecute(SetCommand.create(editingDomain, data, ProcessPackage.Literals.DATA__DEFAULT_VALUE,
                    contractInputGenerator.getInitialValueExpression()));

        }
        return cc;
    }

    protected void openWarningDialog(final boolean allAttributesGenerated) {
        if (!allAttributesGenerated) {
            final String message = contractContainer instanceof Task ? Messages.notAllAttributesGeneratedTaskMsg
                    : Messages.notAllAttributesGeneratedMsg;
            final String title = contractContainer instanceof Task ? Messages.notAllAttributesGeneratedTaskTitle
                    : Messages.notAllAttributesGeneratedTitle;
            MessageDialog.openWarning(getShell(), title, message);
        }
    }

    public CreateContractInputFromBusinessObjectWizardPage getContractInputFromBusinessObjectWizardPage() {
        return contractInputFromBusinessObjectWizardPage;
    }

    protected GenerationOptions getGenerationOptions() {
        return generationOptions;
    }

}
