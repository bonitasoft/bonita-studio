/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.contract.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.bonitasoft.studio.model.process.builders.ContractConstraintBuilder.aContractConstraint;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.engine.bpm.contract.InputDefinition;
import org.bonitasoft.engine.bpm.contract.Type;
import org.bonitasoft.engine.bpm.process.impl.ContractDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.UserTaskDefinitionBuilder;
import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputMapping;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.core.runtime.AssertionFailedException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class ContractEngineDefinitionBuilderTest {

    @Mock
    private UserTaskDefinitionBuilder taskBuilder;

    @Mock
    private ProcessDefinitionBuilder processBuilder;

    private ContractEngineDefinitionBuilder<UserTaskDefinitionBuilder> userTaskengineContractBuilder;
    private ContractEngineDefinitionBuilder<ProcessDefinitionBuilder> processengineContractBuilder;

    private Contract aContract;

    @Mock
    private ContractDefinitionBuilder contractDefBuilder;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        when(processBuilder.addContract()).thenReturn(contractDefBuilder);
        aContract = ProcessFactory.eINSTANCE.createContract();
        processengineContractBuilder = new ProcessContractEngineBuilder();
        processengineContractBuilder.setEngineBuilder(processBuilder);

        when(taskBuilder.addContract()).thenReturn(contractDefBuilder);
        aContract = ProcessFactory.eINSTANCE.createContract();
        userTaskengineContractBuilder = new TaskContractEngineDefinitionBuilder();
        userTaskengineContractBuilder.setEngineBuilder(taskBuilder);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_appliesTo_a_contract() throws Exception {
        assertThat(new TaskContractEngineDefinitionBuilder().appliesTo(ProcessFactory.eINSTANCE.createTask(), aContract)).isTrue();
        assertThat(new TaskContractEngineDefinitionBuilder().appliesTo(ProcessFactory.eINSTANCE.createPool(), aContract)).isFalse();
        assertThat(new TaskContractEngineDefinitionBuilder().appliesTo(ProcessFactory.eINSTANCE.createTask(), null)).isFalse();
        assertThat(userTaskengineContractBuilder.appliesTo(null, null)).isFalse();
        assertThat(new ProcessContractEngineBuilder().appliesTo(ProcessFactory.eINSTANCE.createTask(), aContract)).isFalse();
        assertThat(new ProcessContractEngineBuilder().appliesTo(ProcessFactory.eINSTANCE.createPool(), aContract)).isTrue();
        assertThat(new ProcessContractEngineBuilder().appliesTo(ProcessFactory.eINSTANCE.createPool(), null)).isFalse();
        assertThat(userTaskengineContractBuilder.appliesTo(null, null)).isFalse();
    }

    @Test
    public void should_build_build_an_empty_contract() throws Exception {
        userTaskengineContractBuilder.build(aContract);
        verify(taskBuilder).addContract();
    }

    @Test
    public void should_build_build_an_empty_contract_for_process() throws Exception {
        processengineContractBuilder.build(aContract);
        verify(processBuilder).addContract();
    }

    @Test
    public void should_build_create_a_contract_with_simple_input() throws Exception {
        addInput(aContract, "name", ContractInputType.TEXT, "name of an employee");
        addInput(aContract, "birthDate", ContractInputType.DATE, "Birth date of an employee");
        addInput(aContract, "age", ContractInputType.INTEGER, null);
        addInput(aContract, "salary", ContractInputType.DECIMAL, null);
        addInput(aContract, "isMarried", ContractInputType.BOOLEAN, null);
        userTaskengineContractBuilder.build(aContract);
        verify(taskBuilder).addContract();
        verify(contractDefBuilder).addInput("name", Type.TEXT, "name of an employee", false);
        verify(contractDefBuilder).addInput("birthDate", Type.DATE, "Birth date of an employee", false);
        verify(contractDefBuilder).addInput("age", Type.INTEGER, null, false);
        verify(contractDefBuilder).addInput("salary", Type.DECIMAL, null, false);
        verify(contractDefBuilder).addInput("isMarried", Type.BOOLEAN, null, false);
    }

    @Test
    public void should_build_create_a_contract_with_simple_input_for_process() throws Exception {
        addInput(aContract, "name", ContractInputType.TEXT, "name of an employee");
        addInput(aContract, "birthDate", ContractInputType.DATE, "Birth date of an employee");
        addInput(aContract, "age", ContractInputType.INTEGER, null);
        addInput(aContract, "salary", ContractInputType.DECIMAL, null);
        addInput(aContract, "isMarried", ContractInputType.BOOLEAN, null);
        processengineContractBuilder.build(aContract);
        verify(processBuilder).addContract();
        verify(contractDefBuilder).addInput("name", Type.TEXT, "name of an employee", false);
        verify(contractDefBuilder).addInput("birthDate", Type.DATE, "Birth date of an employee", false);
        verify(contractDefBuilder).addInput("age", Type.INTEGER, null, false);
        verify(contractDefBuilder).addInput("salary", Type.DECIMAL, null, false);
        verify(contractDefBuilder).addInput("isMarried", Type.BOOLEAN, null, false);
    }

    private ContractInput addInput(final Contract contract, final String inputName, final ContractInputType type, final String description) {
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setName(inputName);
        contractInput.setType(type);
        contractInput.setDescription(description);
        contract.getInputs().add(contractInput);
        return contractInput;
    }

    private ContractInput addInput(final ContractInput parentInput, final String inputName, final ContractInputType type, final String description) {
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setName(inputName);
        contractInput.setType(type);
        contractInput.setDescription(description);
        parentInput.getInputs().add(contractInput);
        return contractInput;
    }

    @Test(expected = AssertionFailedException.class)
    public void should_build_throw_an_AssertionFailedException_if_no_builder_is_set() throws Exception {
        userTaskengineContractBuilder = new TaskContractEngineDefinitionBuilder();
        userTaskengineContractBuilder.build(aContract);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_build_throw_an_IllegalArgumentException_if_no_contract_is_set() throws Exception {
        userTaskengineContractBuilder.build(null);
    }

    @Test
    public void should_build_create_a_contract_with_constraint() throws Exception {
        final ContractInput nameInput = addInput(aContract, "name", ContractInputType.TEXT, "name of an employee");
        aContract.getConstraints().add(
                aContractConstraint().withName("myConstraint").withExpression("name.length < 50").withErrorMessage("name is too long")
                        .havingInput(nameInput.getName()).build());
        userTaskengineContractBuilder.build(aContract);
        verify(taskBuilder).addContract();
        verify(contractDefBuilder).addInput("name", Type.TEXT, "name of an employee", nameInput.isMultiple());
        verify(contractDefBuilder).addConstraint("myConstraint", "name.length < 50", "name is too long", nameInput.getName());
    }

    @Test
    public void should_build_create_a_contract_with_mandatory_constraint() throws Exception {
        final ContractInput nameInput = addInput(aContract, "name", ContractInputType.TEXT, "name of an employee");
        userTaskengineContractBuilder.build(aContract);
        verify(taskBuilder).addContract();
        verify(contractDefBuilder).addInput("name", Type.TEXT, "name of an employee", nameInput.isMultiple());
    }

    @Test
    public void should_build_create_operation_for_input_mapping() throws Exception {
        final ContractInput nameInput = addInput(aContract, "name", ContractInputType.TEXT, "name of an employee");
        final ContractInputMapping mapping = ProcessFactory.eINSTANCE.createContractInputMapping();
        final Data textData = ProcessFactory.eINSTANCE.createData();
        final DataType textDt = ProcessFactory.eINSTANCE.createStringType();
        textDt.setName(Messages.StringType);
        textData.setDataType(textDt);
        textData.setName("employeeName");
        mapping.setData(textData);
        nameInput.setMapping(mapping);
        userTaskengineContractBuilder.build(aContract);
        verify(taskBuilder).addContract();
        verify(contractDefBuilder).addInput("name", Type.TEXT, "name of an employee", nameInput.isMultiple());
    }

    @Test
    public void should_build_add_a_complex_input() throws Exception {
        final ContractInput employeeInput = addInput(aContract, "employee", ContractInputType.COMPLEX, "employee complex type");
        addInput(employeeInput, "firstName", ContractInputType.TEXT, null);

        addInput(employeeInput, "lastName", ContractInputType.TEXT, null);
        addInput(employeeInput, "birthDate", ContractInputType.DATE, null);
        final ContractInput skillsInput = addInput(employeeInput, "skills", ContractInputType.COMPLEX, null);
        skillsInput.setMultiple(true);
        addInput(skillsInput, "name", ContractInputType.TEXT, "name of the skills");
        addInput(skillsInput, "rate", ContractInputType.INTEGER, "rate of the skill");

        userTaskengineContractBuilder.build(aContract);
        verify(taskBuilder).addContract();

        verify(contractDefBuilder).addInput(eq(employeeInput.getName()), eq(employeeInput.getDescription()), eq(employeeInput.isMultiple()), anyList());
    }

    @Test
    public void should_buildComplexInput_create_a_complex_input_recursively() throws Exception {
        final ContractInput employeeInput = addInput(aContract, "employee", ContractInputType.COMPLEX, "employee complex type");
        addInput(employeeInput, "firstName", ContractInputType.TEXT, null);
        addInput(employeeInput, "lastName", ContractInputType.TEXT, null);
        addInput(employeeInput, "birthDate", ContractInputType.DATE, null);
        final ContractInput skillsInput = addInput(employeeInput, "skills", ContractInputType.COMPLEX, null);
        skillsInput.setMultiple(true);
        addInput(skillsInput, "name", ContractInputType.TEXT, "name of the skills");
        addInput(skillsInput, "rate", ContractInputType.INTEGER, "rate of the skill");

        final InputDefinition complexInput = userTaskengineContractBuilder.buildComplexInput(employeeInput, contractDefBuilder);
        assertThat(complexInput.getName()).isEqualTo(employeeInput.getName());
        assertThat(complexInput.getDescription()).isEqualTo(employeeInput.getDescription());
        assertThat(complexInput.getInputs()).extracting("name", "type").contains(
                tuple("firstName", Type.TEXT),
                tuple("lastName", Type.TEXT),
                tuple("birthDate", Type.DATE));
        assertThat(complexInput.getInputs()).extracting("name").contains("skills");
        final InputDefinition complexInputDefinition = Iterables.find(complexInput.getInputs(), new Predicate<InputDefinition>() {

            @Override
            public boolean apply(final InputDefinition input) {
                return input.hasChildren();
            }
        }, null);
        assertThat(complexInputDefinition.getInputs()).extracting("name", "type").contains(
                tuple("name", Type.TEXT),
                tuple("rate", Type.INTEGER));
    }

}
