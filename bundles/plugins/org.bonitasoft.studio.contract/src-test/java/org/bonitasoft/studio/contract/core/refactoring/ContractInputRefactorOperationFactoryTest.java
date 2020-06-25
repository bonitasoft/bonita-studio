/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.contract.core.refactoring;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.ContractBuilder.aContract;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ContractInputRefactorOperationFactoryTest {

    @Mock
    private TransactionalEditingDomain domain;

    @Test
    public void should_create_a_RefactorContractInpuyOperation() throws Exception {
        final ContractInputRefactorOperationFactory contractInputRefactorOperationFactory = new ContractInputRefactorOperationFactory();

        final RefactorContractInputOperation refactorOperation = contractInputRefactorOperationFactory.createRefactorOperation(domain,
                aContract().in(aTask()).havingInput(aContractInput()).build().getInputs().get(0), "newName");

        assertThat(refactorOperation).isInstanceOf(RefactorContractInputOperation.class);
    }

    @Test
    public void should_operation_have_an_editingDomain() throws Exception {
        final ContractInputRefactorOperationFactory contractInputRefactorOperationFactory = new ContractInputRefactorOperationFactory();

        final RefactorContractInputOperation refactorOperation = contractInputRefactorOperationFactory.createRefactorOperation(domain,
                aContract().in(aTask()).havingInput(aContractInput()).build().getInputs().get(0), "newName");

        assertThat(refactorOperation.getEditingDomain()).isEqualTo(domain);
    }

    @Test
    public void should_ask_confirmation_before_apply() throws Exception {
        final ContractInputRefactorOperationFactory contractInputRefactorOperationFactory = new ContractInputRefactorOperationFactory();

        final RefactorContractInputOperation refactorOperation = contractInputRefactorOperationFactory.createRefactorOperation(domain,
                aContract().in(aTask()).havingInput(aContractInput()).build().getInputs().get(0), "newName");

        assertThat(refactorOperation.askConfirmation()).isTrue();
    }

}
