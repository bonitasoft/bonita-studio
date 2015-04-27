package org.bonitasoft.studio.contract.ui.property.input.labelProvider;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.set.WritableSet;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MultipleInputCheckboxLabelProviderTest {

    private MultipleInputCheckboxLabelProvider labelProviderUnderTest;

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        labelProviderUnderTest = new MultipleInputCheckboxLabelProvider(new WritableSet());
    }

    @Test
    public void shouldIsSelected_ReturnsFalseFor_Single_ContractInput() throws Exception {
        assertThat(labelProviderUnderTest.isSelected(null)).isFalse();
        final ContractInput optionalInput = ProcessFactory.eINSTANCE.createContractInput();
        optionalInput.setMultiple(false);
        assertThat(labelProviderUnderTest.isSelected(optionalInput)).isFalse();
    }

    @Test
    public void shouldIsSelected_ReturnsTrue_For_Multiple_ContractInput() throws Exception {
        final ContractInput optionalInput = ProcessFactory.eINSTANCE.createContractInput();
        optionalInput.setMultiple(true);
        assertThat(labelProviderUnderTest.isSelected(optionalInput)).isTrue();
    }

}
