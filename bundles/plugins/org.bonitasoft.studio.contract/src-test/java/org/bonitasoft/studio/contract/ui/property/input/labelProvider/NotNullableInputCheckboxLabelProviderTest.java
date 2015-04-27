package org.bonitasoft.studio.contract.ui.property.input.labelProvider;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.set.WritableSet;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class NotNullableInputCheckboxLabelProviderTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    private NotNullableInputCheckboxLabelProvider labelProviderUnderTest;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        labelProviderUnderTest = new NotNullableInputCheckboxLabelProvider(new WritableSet());
    }

    @Test
    public void shouldIsSelected_ReturnsFalse() throws Exception {
        final ContractInput optionalInput = ProcessFactory.eINSTANCE.createContractInput();
        optionalInput.setMandatory(true);
        assertThat(labelProviderUnderTest.isSelected(optionalInput)).isFalse();
    }

    @Test
    public void shouldIsSelected_ReturnsTrue() throws Exception {
        assertThat(labelProviderUnderTest.isSelected(null)).isTrue();
        final ContractInput mandatoryInput = ProcessFactory.eINSTANCE.createContractInput();
        mandatoryInput.setMandatory(false);
        assertThat(labelProviderUnderTest.isSelected(mandatoryInput)).isTrue();
    }

}
