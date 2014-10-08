package org.bonitasoft.studio.contract.ui.property.table;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.contract.ui.property.table.MultipleInputCheckboxLabelProvider;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MultipleInputCheckboxLabelProviderTest {

    private MultipleInputCheckboxLabelProvider labelProviderUnderTest;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        labelProviderUnderTest = new MultipleInputCheckboxLabelProvider(null);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
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
