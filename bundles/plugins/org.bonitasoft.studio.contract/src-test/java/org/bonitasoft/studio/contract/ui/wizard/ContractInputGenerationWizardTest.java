package org.bonitasoft.studio.contract.ui.wizard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder.aBusinessData;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;

import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.junit.Rule;
import org.junit.Test;

public class ContractInputGenerationWizardTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Test
    public void should_first_wizard_page_be_selectBusinessDataWizardPage() {
        final BusinessObjectData data = aBusinessData().build();
        final Pool process = aPool().build();
        process.getData().add(data);

        final ContractInputGenerationWizard wizard = new ContractInputGenerationWizard(process);
        wizard.addPages();

        assertThat(wizard.getPages()[0]).isInstanceOf(SelectBusinessDataWizardPage.class);
    }

    @Test
    public void should_first_wizard_page_be_CreateContractInputFromBusinessObjectWizardPage() {
        final BusinessObjectData data = aBusinessData().build();
        final Pool process = aPool().build();
        process.getData().add(data);

        final ContractInputGenerationWizard wizard = new ContractInputGenerationWizard(process);
        wizard.addPages();

        assertThat(wizard.getPages()[0]).isInstanceOf(SelectBusinessDataWizardPage.class);
    }
}
