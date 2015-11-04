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
import static org.assertj.core.api.Assertions.tuple;
import static org.bonitasoft.studio.model.expression.assertions.ExpressionAssert.assertThat;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.expression.builders.OperationBuilder.anOperation;
import static org.bonitasoft.studio.model.process.builders.ContractBuilder.aContract;
import static org.bonitasoft.studio.model.process.builders.ContractConstraintBuilder.aContractConstraint;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;

import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.ContractContainer;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.bonitasoft.studio.refactoring.core.script.IScriptRefactoringOperationFactory;
import org.bonitasoft.studio.refactoring.core.script.ScriptContainer;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class RefactorContractInputOperationTest {

    private final IProgressMonitor monitor = new NullProgressMonitor();
    @Mock
    private IScriptRefactoringOperationFactory scriptRefactorOperationFactory;

    @Test
    public void should_update_contract_input_reference_in_contract_input_expressions() throws Exception {
        final Task aTaskWithContractAndOperations = aTaskWithContractAndOperations();
        final Expression contractInputExpression = aTaskWithContractAndOperations.getOperations().get(0).getRightOperand();
        final RefactorContractInputOperation refactorOperation = new RefactorContractInputOperation(aTaskWithContractAndOperations,
                scriptRefactorOperationFactory, RefactoringOperationType.UPDATE);

        final ContractInput oldItem = aTaskWithContractAndOperations.getContract().getInputs().get(0);
        final ContractInput newtem = EcoreUtil.copy(oldItem);
        newtem.setName("lastName");
        refactorOperation.addItemToRefactor(newtem, oldItem);
        refactorOperation.setEditingDomain(transactionalEditingDomain());

        refactorOperation.run(monitor);

        assertThat(contractInputExpression).hasName("lastName").hasContent("lastName");
    }

    @Test
    public void should_not_update_contract_input_reference_in_contract_input_expressions_in_task_when_container_is_a_pool() throws Exception {
        final Task aTaskWithContractAndOperations = aTaskWithContractAndOperations();
        final Pool pool = aPool().havingContract(aContract().havingInput(aContractInput().withName("firstName"))).build();
        pool.getElements().add(aTaskWithContractAndOperations);
        final Expression contractInputExpression = aTaskWithContractAndOperations.getOperations().get(0).getRightOperand();
        final RefactorContractInputOperation refactorOperation = new RefactorContractInputOperation(pool,
                scriptRefactorOperationFactory, RefactoringOperationType.UPDATE);
        final ContractInput oldItem = pool.getContract().getInputs().get(0);
        final ContractInput newtem = EcoreUtil.copy(oldItem);
        newtem.setName("lastName");
        refactorOperation.addItemToRefactor(newtem, oldItem);
        refactorOperation.setEditingDomain(transactionalEditingDomain());

        refactorOperation.run(monitor);
        assertThat(contractInputExpression).hasName("firstName").hasContent("firstName");
    }

    @Test
    public void should_add_a_script_expression_for_constraint_referencing_updated_input_and_some_others() throws Exception {
        final ContractContainer aTaskWithContract = aTaskWithContractAndGlobalConstraint();
        final RefactorContractInputOperation refactorOperation = new RefactorContractInputOperation(aTaskWithContract,
                scriptRefactorOperationFactory,
                RefactoringOperationType.UPDATE);

        final ContractInput contractInput = aTaskWithContract.getContract().getInputs()
                .get(0);
        final Set<ScriptContainer<?>> allScriptWithReferencedElement = refactorOperation.allScriptWithReferencedElement(new ContractInputRefactorPair(
                EcoreUtil.copy(contractInput),
                contractInput));

        assertThat(allScriptWithReferencedElement).extracting("script", "modelElement").contains(
                tuple("firstName.length > 0 && lastName.length > 0", aTaskWithContract.getContract().getConstraints().get(0)));
    }

    @Test
    public void should_add_a_script_expression_with_parent_for_constraint_referencing_updated_input() throws Exception {
        final ContractContainer aTaskWithContract = aTaskWithContractAndGlobalConstraint();
        final RefactorContractInputOperation refactorOperation = new RefactorContractInputOperation(aTaskWithContract,
                scriptRefactorOperationFactory,
                RefactoringOperationType.UPDATE);

        final ContractInput contractInput = aTaskWithContract.getContract().getInputs()
                .get(0);
        final Set<ScriptContainer<?>> allScriptWithReferencedElement = refactorOperation.allScriptWithReferencedElement(new ContractInputRefactorPair(
                EcoreUtil.copy(contractInput),
                contractInput));

        assertThat(allScriptWithReferencedElement.iterator().next().getModelElement().eContainer()).isNotNull();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_have_a_container() throws Exception {
        new RefactorContractInputOperation(null, scriptRefactorOperationFactory, RefactoringOperationType.UPDATE);
    }

    private TransactionalEditingDomainImpl transactionalEditingDomain() {
        return new TransactionalEditingDomainImpl(new ProcessItemProviderAdapterFactory());
    }

    private ContractContainer aTaskWithContract() {
        return aTask().havingContract(aContract()
                .havingInput(aContractInput().withName("firstName"))
                .havingConstraint(aContractConstraint().withExpression("firstName.length > 0").havingInput("firstName"))).build();
    }

    private ContractContainer aTaskWithContractAndGlobalConstraint() {
        return aTask().havingContract(aContract()
                .havingInput(aContractInput().withName("firstName"))
                .havingInput(aContractInput().withName("lastName"))
                .havingConstraint(aContractConstraint().withExpression("firstName.length > 0 && lastName.length > 0").havingInput("firstName", "lastName")))
                .build();
    }

    private Task aTaskWithContractAndOperations() {
        return aTask().havingContract(aContract()
                .havingInput(aContractInput().withName("firstName")))
                .havingOperations(anOperation()
                        .havingLeftOperand(anExpression().withExpressionType(ExpressionConstants.VARIABLE_TYPE).withName("storedData"))
                        .havingRightOperand(ExpressionHelper.createContractInputExpression(aContractInput().withName("firstName").build())))
                .build();
    }
}
