package org.bonitasoft.studio.contract.ui.property.input;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.contract.ui.property.input.MandatoryInputCheckboxLabelProvider;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MandatoryInputCheckboxLabelProviderTest {

    private MandatoryInputCheckboxLabelProvider labelProviderUnderTest;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        labelProviderUnderTest = new MandatoryInputCheckboxLabelProvider(null);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void shouldIsSelected_ReturnsFalse() throws Exception {
        assertThat(labelProviderUnderTest.isSelected(null)).isFalse();
        final ContractInput optionalInput = ProcessFactory.eINSTANCE.createContractInput();
        optionalInput.setMandatory(false);
        assertThat(labelProviderUnderTest.isSelected(optionalInput)).isFalse();
    }

    @Test
    public void shouldIsSelected_ReturnsTrue() throws Exception {
        final ContractInput mandatoryInput = ProcessFactory.eINSTANCE.createContractInput();
        mandatoryInput.setMandatory(true);
        assertThat(labelProviderUnderTest.isSelected(mandatoryInput)).isTrue();
    }

}
