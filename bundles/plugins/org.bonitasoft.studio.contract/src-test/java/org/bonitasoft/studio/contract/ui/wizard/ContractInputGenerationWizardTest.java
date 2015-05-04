package org.bonitasoft.studio.contract.ui.wizard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder.aBusinessData;
import static org.bonitasoft.studio.model.process.builders.ContractBuilder.aContract;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.SimpleFieldBuilder;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.jface.wizard.IWizardContainer;
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
    private BusinessObjectModelRepositoryStore store;

    @Test
    public void should_first_wizard_page_be_selectBusinessDataWizardPage() {
        final BusinessObjectData data = aBusinessData().build();
        final Pool process = aPool().build();
        process.getData().add(data);

        final ContractInputGenerationWizard wizard = new ContractInputGenerationWizard(process, editingDomain(), store);
        wizard.addPages();

        assertThat(wizard.getPages()[0]).isInstanceOf(SelectBusinessDataWizardPage.class);
    }

    @Test
    public void should_first_wizard_page_be_CreateContractInputFromBusinessObjectWizardPage() {
        final BusinessObjectData data = aBusinessData().build();
        final Pool process = aPool().build();
        process.getData().add(data);

        final ContractInputGenerationWizard wizard = new ContractInputGenerationWizard(process, editingDomain(), store);
        wizard.addPages();

        assertThat(wizard.getPages()[0]).isInstanceOf(SelectBusinessDataWizardPage.class);
    }

    @Test
    public void should_add_a_contract_input_with_selected_mappings_on_finish() throws Exception {
        final BusinessObjectData data = aBusinessData().withName("employee").withClassname("org.company.Employee").build();
        final Pool process = aPool().havingContract(aContract()).build();
        process.getData().add(data);
        when(store.getBusinessObjectByQualifiedName("org.company.Employee")).thenReturn(
                BusinessObjectBuilder.aBO("org.company.Employee").withField(SimpleFieldBuilder.aStringField("firstName").build()).build());

        final ContractInputGenerationWizard wizard = new ContractInputGenerationWizard(process, editingDomain(), store);
        wizard.addPages();
        final IWizardContainer wizardContainer = Mockito.mock(IWizardContainer.class);
        when(wizardContainer.getShell()).thenReturn(realmWithDisplay.getShell());
        wizard.setContainer(wizardContainer);
        wizard.createPageControls(realmWithDisplay.createComposite());
        wizard.performFinish();

        assertThat(process.getContract().getInputs()).extracting("name").contains("employeeEmployee");
        assertThat(process.getContract().getInputs().get(0).getInputs()).extracting("name").contains("firstName");
    }

    private EditingDomain editingDomain() {
        return new TransactionalEditingDomainImpl(new ProcessItemProviderAdapterFactory());
    }
}
