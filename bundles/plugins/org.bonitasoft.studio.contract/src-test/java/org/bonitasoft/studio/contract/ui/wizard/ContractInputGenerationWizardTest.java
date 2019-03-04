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

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.aGroovyScriptExpression;
import static org.bonitasoft.studio.model.expression.builders.OperationBuilder.anOperation;
import static org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder.aBusinessData;
import static org.bonitasoft.studio.model.process.builders.ContractBuilder.aContract;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.expression.FieldToContractInputMappingExpressionBuilder;
import org.bonitasoft.studio.contract.core.mapping.operation.FieldToContractInputMappingOperationBuilder;
import org.bonitasoft.studio.groovy.ui.viewer.GroovySourceViewerFactory;
import org.bonitasoft.studio.groovy.ui.viewer.GroovyViewer;
import org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.SimpleFieldBuilder;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.DocumentType;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ContractInputGenerationWizardTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Mock
    private RepositoryAccessor repositoryAccessor;

    @Mock
    private FieldToContractInputMappingOperationBuilder operationBuilder;

    @Mock
    private IPreferenceStore preferenceStore;

    @Mock
    private ISharedImages sharedImages;

    @Mock
    private FieldToContractInputMappingExpressionBuilder expressionBuilder;

    @Mock
    private ContractInputGenerationInfoDialogFactory dialogFactory;

    @Mock
    private GroovySourceViewerFactory sourceViewerFactory;

    @Mock
    private GroovyViewer groovyViewer;

    @Mock
    private SourceViewer sourceViewer;

    @Mock
    private IDocument document;

    @Test
    public void should_first_wizard_page_be_selectBusinessDataWizardPage() {
        final BusinessObjectData data = aBusinessData().build();
        final Pool process = aPool().build();
        process.getData().add(data);

        final ContractInputGenerationWizard wizard = new ContractInputGenerationWizard(process, editingDomain(),
                repositoryAccessor, operationBuilder,
                expressionBuilder, preferenceStore, sharedImages, dialogFactory,
                new ContractInputGenerationWizardPagesFactory(), sourceViewerFactory);
        wizard.addPages();

        assertThat(wizard.getPages()[0]).isInstanceOf(SelectDataWizardPage.class);
    }

    @Test
    public void should_first_wizard_page_be_CreateContractInputFromBusinessObjectWizardPage() {
        final BusinessObjectData data = aBusinessData().build();
        final Pool process = aPool().build();
        process.getData().add(data);

        final ContractInputGenerationWizard wizard = new ContractInputGenerationWizard(process, editingDomain(),
                repositoryAccessor, operationBuilder,
                expressionBuilder, preferenceStore, sharedImages, dialogFactory,
                new ContractInputGenerationWizardPagesFactory(), sourceViewerFactory);
        wizard.addPages();

        assertThat(wizard.getPages()[0]).isInstanceOf(SelectDataWizardPage.class);
    }

    @Test
    public void should_add_a_contract_input_with_selected_mappings_on_finish() throws Exception {
        final BusinessObjectData data = aBusinessData().withName("employee").withClassname("org.company.Employee").build();
        final Pool process = aPool().havingContract(aContract()).build();
        process.getData().add(data);

        final BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> store = getBusinessObjectModelRepositoryStore();
        when(repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)).thenReturn(store);
        when(preferenceStore.getString(ContractInputGenerationInfoDialogFactory.SHOW_GENERATION_SUCCESS_DIALOG))
                .thenReturn("always");
        when(sourceViewerFactory.createSourceViewer(any(Composite.class), any(Boolean.class))).thenReturn(groovyViewer);
        when(groovyViewer.getSourceViewer()).thenReturn(sourceViewer);
        when(groovyViewer.getDocument()).thenReturn(document);

        final ContractInputGenerationWizard wizard = new ContractInputGenerationWizard(process, editingDomain(),
                repositoryAccessor, operationBuilder,
                expressionBuilder,
                preferenceStore, sharedImages, dialogFactory, new ContractInputGenerationWizardPagesFactory(),
                sourceViewerFactory);
        wizard.addPages();
        wizard.setContainer(new TestWizardContainer(realmWithDisplay.getShell()));
        wizard.createPageControls(realmWithDisplay.createComposite());
        wizard.performFinish();
        assertThat(process.getContract().getInputs()).extracting("name").contains("employeeInput");
        assertThat(process.getContract().getInputs().get(0).getInputs()).extracting("name").contains("firstName");
    }

    @Test
    public void should_not_add_script_on_finish() throws Exception {
        final BusinessObjectData data = aBusinessData().withName("employee").withClassname("org.company.Employee").build();
        final Pool process = aPool().havingContract(aContract()).build();
        process.getData().add(data);

        final BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> store = getBusinessObjectModelRepositoryStore();
        when(repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)).thenReturn(store);
        when(preferenceStore.getString(ContractInputGenerationInfoDialogFactory.SHOW_GENERATION_SUCCESS_DIALOG))
                .thenReturn("always");
        when(sourceViewerFactory.createSourceViewer(any(Composite.class), any(Boolean.class))).thenReturn(groovyViewer);
        when(groovyViewer.getSourceViewer()).thenReturn(sourceViewer);
        when(groovyViewer.getDocument()).thenReturn(document);

        final ContractInputGenerationWizard wizard = new ContractInputGenerationWizard(process, editingDomain(),
                repositoryAccessor, operationBuilder,
                expressionBuilder,
                preferenceStore, sharedImages, dialogFactory, new ContractInputGenerationWizardPagesFactory(),
                sourceViewerFactory);
        wizard.addPages();

        wizard.setContainer(new TestWizardContainer(realmWithDisplay.getShell()));
        wizard.createPageControls(realmWithDisplay.createComposite());
        wizard.getContractInputFromBusinessObjectWizardPage().disableAutoGeneration();
        wizard.performFinish();

        assertThat(process.getData().get(0).getDefaultValue()).isNull();
    }

    @Test
    public void should_add_script_on_finish() throws Exception {
        final BusinessObjectData data = aBusinessData().withName("employee").withClassname("org.company.Employee").build();
        final Pool process = aPool().havingContract(aContract()).build();
        process.getData().add(data);

        final BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> store = getBusinessObjectModelRepositoryStore();
        when(repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)).thenReturn(store);
        when(preferenceStore.getString(ContractInputGenerationInfoDialogFactory.SHOW_GENERATION_SUCCESS_DIALOG))
                .thenReturn("always");
        when(expressionBuilder.toExpression(any(BusinessObjectData.class), any(FieldToContractInputMapping.class),
                anyBoolean())).thenReturn(
                        aGroovyScriptExpression().build());
        when(sourceViewerFactory.createSourceViewer(any(Composite.class), any(Boolean.class))).thenReturn(groovyViewer);
        when(groovyViewer.getSourceViewer()).thenReturn(sourceViewer);
        when(groovyViewer.getDocument()).thenReturn(document);

        final ContractInputGenerationWizard wizard = new ContractInputGenerationWizard(process, editingDomain(),
                repositoryAccessor, operationBuilder,
                expressionBuilder,
                preferenceStore, sharedImages, dialogFactory, new ContractInputGenerationWizardPagesFactory(),
                sourceViewerFactory);
        wizard.addPages();

        wizard.setContainer(new TestWizardContainer(realmWithDisplay.getShell()));
        wizard.createPageControls(realmWithDisplay.createComposite());
        wizard.performFinish();

        assertThat(process.getData().get(0).getDefaultValue()).isNotNull();
    }

    @Test
    public void should_not_add_operations_on_finish() throws Exception {
        final BusinessObjectData data = aBusinessData().withName("employee").withClassname("org.company.Employee").build();
        final Task task = aTask().havingContract(aContract()).build();
        final Pool process = aPool().havingContract(aContract()).build();
        process.getElements().add(task);
        process.getData().add(data);

        final BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> store = getBusinessObjectModelRepositoryStore();
        when(repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)).thenReturn(store);
        when(preferenceStore.getString(ContractInputGenerationInfoDialogFactory.SHOW_GENERATION_SUCCESS_DIALOG))
                .thenReturn("always");

        final ContractInputGenerationWizard wizard = new ContractInputGenerationWizard(task, editingDomain(),
                repositoryAccessor, operationBuilder,
                expressionBuilder,
                preferenceStore, sharedImages, dialogFactory, new ContractInputGenerationWizardPagesFactory(),
                sourceViewerFactory);
        wizard.addPages();

        wizard.setContainer(new TestWizardContainer(realmWithDisplay.getShell()));
        wizard.createPageControls(realmWithDisplay.createComposite());
        wizard.getContractInputFromBusinessObjectWizardPage().disableAutoGeneration();
        wizard.performFinish();

        assertThat(task.getOperations().isEmpty()).isTrue();
    }

    @Test
    public void should_add_script_operations_on_finish() throws Exception {
        final BusinessObjectData data = aBusinessData().withName("employee").withClassname("org.company.Employee").build();
        final Task task = aTask().havingContract(aContract()).build();
        final Pool process = aPool().havingContract(aContract()).build();
        process.getElements().add(task);
        process.getData().add(data);

        final BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> store = getBusinessObjectModelRepositoryStore();
        when(repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)).thenReturn(store);
        when(preferenceStore.getString(ContractInputGenerationInfoDialogFactory.SHOW_GENERATION_SUCCESS_DIALOG))
                .thenReturn("always");
        when(operationBuilder.toOperation(any(BusinessObjectData.class), any(FieldToContractInputMapping.class),
                any(IProgressMonitor.class))).thenReturn(anOperation().build());

        final ContractInputGenerationWizard wizard = new ContractInputGenerationWizard(task, editingDomain(),
                repositoryAccessor, operationBuilder,
                expressionBuilder,
                preferenceStore, sharedImages, dialogFactory, new ContractInputGenerationWizardPagesFactory(),
                sourceViewerFactory);
        wizard.addPages();

        wizard.setContainer(new TestWizardContainer(realmWithDisplay.getShell()));
        wizard.createPageControls(realmWithDisplay.createComposite());
        wizard.performFinish();

        assertThat(task.getOperations().isEmpty()).isFalse();
    }

    @Test
    public void should_add_DocumentUpdateOperation_whenDataIsDocument_onFinish() {
        final Task task = aTask().havingContract(aContract()).build();
        final Pool process = aPool().havingContract(aContract()).build();
        final Document document1 = ProcessFactory.eINSTANCE.createDocument();
        document1.setName("myDocument");
        process.getDocuments().add(document1);
        process.getElements().add(task);

        final ContractInputGenerationWizard wizard = new ContractInputGenerationWizard(task, editingDomain(),
                repositoryAccessor, operationBuilder,
                expressionBuilder,
                preferenceStore, sharedImages, dialogFactory, new ContractInputGenerationWizardPagesFactory(),
                sourceViewerFactory);
        wizard.addPages();
        wizard.setContainer(new TestWizardContainer(realmWithDisplay.getShell()));
        wizard.createPageControls(realmWithDisplay.createComposite());
        wizard.performFinish();

        assertThat(task.getContract().getInputs().get(0).getType()).isEqualTo(ContractInputType.FILE);
        assertThat(task.getOperations().get(0).getLeftOperand().getName()).isEqualTo("myDocument");
        assertThat(task.getOperations().get(0).getRightOperand().getType())
                .isEqualTo(ExpressionConstants.CONTRACT_INPUT_TYPE);
        assertThat(task.getOperations().get(0).getRightOperand().getName()).isEqualTo("myDocumentDocumentInput");
    }

    @Test
    public void should_add_FileInput_whenDataIsDocument_onFinish() {
        final Pool process = aPool().havingContract(aContract()).build();
        process.getDocuments().add(ProcessFactory.eINSTANCE.createDocument());
        when(sourceViewerFactory.createSourceViewer(any(Composite.class), any(Boolean.class))).thenReturn(groovyViewer);
        when(groovyViewer.getSourceViewer()).thenReturn(sourceViewer);
        when(groovyViewer.getDocument()).thenReturn(document);

        final ContractInputGenerationWizard wizard = new ContractInputGenerationWizard(process, editingDomain(),
                repositoryAccessor, operationBuilder,
                expressionBuilder,
                preferenceStore, sharedImages, dialogFactory, new ContractInputGenerationWizardPagesFactory(),
                sourceViewerFactory);
        wizard.addPages();

        wizard.setContainer(new TestWizardContainer(realmWithDisplay.getShell()));
        wizard.createPageControls(realmWithDisplay.createComposite());
        wizard.performFinish();

        assertThat(process.getContract().getInputs().get(0).getType()).isEqualTo(ContractInputType.FILE);
        assertThat(process.getDocuments().get(0).getDocumentType()).isEqualTo(DocumentType.CONTRACT);
    }

    @Test
    public void should_canFinish_return_false_when_no_data_is_defined() {
        final Pool process = aPool().havingContract(aContract()).build();
        when(sourceViewerFactory.createSourceViewer(any(Composite.class), any(Boolean.class))).thenReturn(groovyViewer);
        when(groovyViewer.getSourceViewer()).thenReturn(sourceViewer);
        when(groovyViewer.getDocument()).thenReturn(document);

        final ContractInputGenerationWizard wizard = new ContractInputGenerationWizard(process, editingDomain(),
                repositoryAccessor, operationBuilder,
                expressionBuilder, preferenceStore, sharedImages, dialogFactory,
                new ContractInputGenerationWizardPagesFactory(), sourceViewerFactory);
        wizard.addPages();

        final IWizardContainer wizardContainer = Mockito.mock(IWizardContainer.class);
        when(wizardContainer.getShell()).thenReturn(realmWithDisplay.getShell());
        wizard.setContainer(wizardContainer);
        wizard.createPageControls(realmWithDisplay.createComposite());

        assertThat(wizard.canFinish()).isFalse();
    }

    @Test
    public void should_canFinish_return_true_when_data_is_selected() {
        final Pool process = aPool().havingContract(aContract()).build();
        final BusinessObjectData data = aBusinessData().withName("employee").withClassname("com.company.Employee").build();
        process.getData().add(data);

        final BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> store = mock(
                BusinessObjectModelRepositoryStore.class);
        Mockito.doReturn(Optional.of(BusinessObjectBuilder.aBO("com.company.Employee")
                .withField(SimpleFieldBuilder.aTextField("name").build()).build())).when(store)
                .getBusinessObjectByQualifiedName("com.company.Employee");
        when(store.getChildByQualifiedName(anyString())).thenReturn(Optional.empty());
        when(repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)).thenReturn(store);
        when(sourceViewerFactory.createSourceViewer(any(Composite.class), any(Boolean.class))).thenReturn(groovyViewer);
        when(groovyViewer.getSourceViewer()).thenReturn(sourceViewer);
        when(groovyViewer.getDocument()).thenReturn(document);

        final ContractInputGenerationWizard wizard = new ContractInputGenerationWizard(process, editingDomain(),
                repositoryAccessor, operationBuilder,
                expressionBuilder, preferenceStore, sharedImages, dialogFactory,
                new ContractInputGenerationWizardPagesFactory(), sourceViewerFactory);
        wizard.addPages();
        wizard.setContainer(new TestWizardContainer(realmWithDisplay.getShell()));
        wizard.createPageControls(realmWithDisplay.createComposite());

        assertThat(wizard.canFinish()).isTrue();
    }

    @Test
    public void should_add_data_reference_for_document_only_on_task() {
        Task task = aTask().havingContract(aContract()).build();
        Pool process = aPool().havingContract(aContract()).build();
        Document document1 = ProcessFactory.eINSTANCE.createDocument();
        document1.setName("myDocument");
        process.getDocuments().add(document1);
        process.getElements().add(task);

        ContractInputGenerationWizard wizard = new ContractInputGenerationWizard(process, editingDomain(),
                repositoryAccessor, operationBuilder,
                expressionBuilder,
                preferenceStore, sharedImages, dialogFactory, new ContractInputGenerationWizardPagesFactory(),
                sourceViewerFactory);
        wizard.addPages();

        assertThat(wizard.createDocumentContractInput(document1).getDataReference()).isNull();

        wizard = new ContractInputGenerationWizard(task, editingDomain(),
                repositoryAccessor, operationBuilder,
                expressionBuilder,
                preferenceStore, sharedImages, dialogFactory, new ContractInputGenerationWizardPagesFactory(),
                sourceViewerFactory);
        wizard.addPages();

        assertThat(wizard.createDocumentContractInput(document1).getDataReference()).isEqualTo("myDocument");
    }

    private EditingDomain editingDomain() {
        return new TransactionalEditingDomainImpl(new ProcessItemProviderAdapterFactory());
    }

    private BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> getBusinessObjectModelRepositoryStore() {
        final BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> store = mock(
                BusinessObjectModelRepositoryStore.class);
        when(store.getBusinessObjectByQualifiedName("org.company.Employee")).thenReturn(
                Optional.of(BusinessObjectBuilder.aBO("org.company.Employee")
                        .withField(SimpleFieldBuilder.aStringField("firstName").build()).build()));
        when(store.getChildByQualifiedName(anyString())).thenReturn(Optional.empty());
        return store;
    }

}
