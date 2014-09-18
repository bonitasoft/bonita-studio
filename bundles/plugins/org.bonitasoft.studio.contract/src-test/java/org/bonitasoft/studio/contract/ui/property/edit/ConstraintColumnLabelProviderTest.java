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
package org.bonitasoft.studio.contract.ui.property.edit;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.contract.core.ContractConstraintUtil;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
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
public class ConstraintColumnLabelProviderTest {

    private ConstraintColumnLabelProvider labelProvider;
    private ContractInput contractInput;
    private Contract contract;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        labelProvider = new ConstraintColumnLabelProvider();
        contract = ProcessFactory.eINSTANCE.createContract();
        contractInput = addInput(contract, "name", ContractInputType.TEXT, null);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_getText_returns_null_for_invalid_element() throws Exception {
        assertThat(labelProvider.getText(null)).isNull();
    }

    @Test
    public void should_getText_returns_none_if_no_constraint_for_input() throws Exception {
        assertThat(labelProvider.getText(contractInput)).isEqualTo(Messages.none);
    }

    @Test
    public void should_getText_returns_number_of_constraint_involving_an_contract_input() throws Exception {
        final ContractConstraint c1 = ContractConstraintUtil.createConstraint("name.length > 55", "", contractInput);
        contract.getConstraints().add(c1);
        assertThat(labelProvider.getText(contractInput)).isEqualTo("1");
    }

    private ContractInput addInput(final Contract contract, final String inputName, final ContractInputType type, final String description) {
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setName(inputName);
        contractInput.setType(type);
        contractInput.setDescription(description);
        contract.getInputs().add(contractInput);
        return contractInput;
    }

}
