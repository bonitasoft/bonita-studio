package org.bonitasoft.studio.contract.ui.property.input;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.swt.AbstractSWTTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class NullableInputCheckboxLabelProviderTest extends AbstractSWTTestCase {

    private NotNullableInputCheckboxLabelProvider labelProviderUnderTest;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        createDisplayAndRealm();
        labelProviderUnderTest = new NotNullableInputCheckboxLabelProvider();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        dispose();
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
