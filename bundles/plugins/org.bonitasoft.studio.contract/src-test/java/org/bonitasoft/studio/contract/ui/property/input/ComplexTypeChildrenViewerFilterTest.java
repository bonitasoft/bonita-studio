/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.contract.ui.property.input;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Romain Bioteau
 *
 */
public class ComplexTypeChildrenViewerFilterTest {

    private ComplexTypeChildrenViewerFilter complexTypeChildrenViewerFilter;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        complexTypeChildrenViewerFilter = new ComplexTypeChildrenViewerFilter();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_select_returns_false_for_contract_input_children_in_a_primitive_type() throws Exception {
        final ContractInput parentInput = ProcessFactory.eINSTANCE.createContractInput();
        parentInput.setType(ContractInputType.INTEGER);
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        parentInput.getInputs().add(contractInput);
        assertThat(complexTypeChildrenViewerFilter.select(null, null, contractInput)).isFalse();
    }

    @Test
    public void should_select_returns_true_for_contract_input_children_in_a_complex_type() throws Exception {
        final ContractInput parentInput = ProcessFactory.eINSTANCE.createContractInput();
        parentInput.setType(ContractInputType.COMPLEX);
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        parentInput.getInputs().add(contractInput);
        assertThat(complexTypeChildrenViewerFilter.select(null, null, contractInput)).isTrue();
    }

    @Test
    public void should_select_returns_true_for_contract_input_without_parent() throws Exception {
        final ContractInput parentInput = ProcessFactory.eINSTANCE.createContractInput();
        parentInput.setType(ContractInputType.COMPLEX);
        assertThat(complexTypeChildrenViewerFilter.select(null, null, parentInput)).isTrue();
    }

}
