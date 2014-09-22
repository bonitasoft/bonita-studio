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
package org.bonitasoft.studio.contract.core;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.engine.bpm.contract.Type;
import org.bonitasoft.engine.bpm.process.impl.ContractDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.UserTaskDefinitionBuilder;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.core.runtime.AssertionFailedException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Romain Bioteau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class EngineContractBuilderTest {

    @Mock
    private UserTaskDefinitionBuilder taskBuilder;

    private EngineContractBuilder engineContractBuilder;

    private Contract aContract;

    @Mock
    private ContractDefinitionBuilder contractDefBuilder;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        when(taskBuilder.addContract()).thenReturn(contractDefBuilder);
        aContract = ProcessFactory.eINSTANCE.createContract();
        engineContractBuilder = new EngineContractBuilder();
        engineContractBuilder.setEngineBuilder(taskBuilder);
        engineContractBuilder.setContract(aContract);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_build_build_an_empty_contract() throws Exception {
        engineContractBuilder.build();
        verify(taskBuilder).addContract();
    }

    @Test
    public void should_build_create_a_contract_with_simple_input() throws Exception {
        addInput(aContract, "name", ContractInputType.TEXT, "name of an employee");
        addInput(aContract, "birthDate", ContractInputType.DATE, "Birth date of an employee");
        addInput(aContract, "age", ContractInputType.INTEGER, null);
        addInput(aContract, "salary", ContractInputType.DECIMAL, null);
        addInput(aContract, "isMarried", ContractInputType.BOOLEAN, null);
        engineContractBuilder.setContract(aContract);
        engineContractBuilder.build();
        verify(taskBuilder).addContract();
        verify(contractDefBuilder).addSimpleInput("name", Type.TEXT, "name of an employee");
        verify(contractDefBuilder).addSimpleInput("birthDate", Type.DATE, "Birth date of an employee");
        verify(contractDefBuilder).addSimpleInput("age", Type.INTEGER, null);
        verify(contractDefBuilder).addSimpleInput("salary", Type.DECIMAL, null);
        verify(contractDefBuilder).addSimpleInput("isMarried", Type.BOOLEAN, null);
    }

    private ContractInput addInput(final Contract contract, final String inputName, final ContractInputType type, final String description) {
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setName(inputName);
        contractInput.setType(type);
        contractInput.setDescription(description);
        contract.getInputs().add(contractInput);
        return contractInput;
    }

    @Test(expected = AssertionFailedException.class)
    public void should_build_throw_an_AssertionFailedException_if_no_builder_is_set() throws Exception {
        engineContractBuilder.setEngineBuilder(null);
        engineContractBuilder.build();
    }

    @Test(expected = AssertionFailedException.class)
    public void should_build_throw_an_AssertionFailedException_if_no_contract_is_set() throws Exception {
        engineContractBuilder.setContract(null);
        engineContractBuilder.build();
    }

    @Test
    public void should_build_create_a_contract_with_constraint() throws Exception {
        final ContractInput nameInput = addInput(aContract, "name", ContractInputType.TEXT, "name of an employee");
        nameInput.setMandatory(false);
        aContract.getConstraints().add(ContractConstraintUtil.createConstraint("name.length < 50", "name is too long", nameInput));
        engineContractBuilder.setContract(aContract);
        engineContractBuilder.build();
        verify(taskBuilder).addContract();
        verify(contractDefBuilder).addSimpleInput("name", Type.TEXT, "name of an employee");
        verify(contractDefBuilder).addConstraint("constraint0", "name.length < 50", "name is too long", nameInput.getName());
    }

    @Test
    public void should_build_create_a_contract_with_mandatory_constraint() throws Exception {
        final ContractInput nameInput = addInput(aContract, "name", ContractInputType.TEXT, "name of an employee");
        nameInput.setMandatory(true);
        engineContractBuilder.setContract(aContract);
        engineContractBuilder.build();
        verify(taskBuilder).addContract();
        verify(contractDefBuilder).addSimpleInput("name", Type.TEXT, "name of an employee");
        verify(contractDefBuilder).addMandatoryConstraint("name");
    }

}
