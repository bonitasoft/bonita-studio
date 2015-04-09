/**
 * Copyright (C) 2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.contract.core.refactoring;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.assertions.ExpressionAssert.assertThat;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.expression.builders.OperationBuilder.anOperation;
import static org.bonitasoft.studio.model.process.assertions.ContractConstraintAssert.assertThat;
import static org.bonitasoft.studio.model.process.builders.ContractBuilder.aContract;
import static org.bonitasoft.studio.model.process.builders.ContractConstraintBuilder.aContractConstraint;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractContainer;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.bonitasoft.studio.refactoring.core.AbstractScriptExpressionRefactoringAction;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.bonitasoft.studio.refactoring.core.groovy.GroovyScriptRefactoringOperation;
import org.bonitasoft.studio.refactoring.core.groovy.ReferenceDiff;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class RefactorContractInputOperationTest {

    private final IProgressMonitor monitor = new NullProgressMonitor();
    private TransactionalEditingDomain domain;
    @Mock
    private GroovyScriptRefactoringOperation groovyRefactoringOperation;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        domain = new TransactionalEditingDomainImpl(new ProcessItemProviderAdapterFactory());
    }

    @Test
    public void update_contract_constraint_input_reference_in_constraint() throws Exception {
        final ContractContainer aTaskWithContract = aTaskWithContract();
        final ContractConstraint contractConstraint = aTaskWithContract.getContract().getConstraints().get(0);
        final RefactorContractInputOperation refactorOperation = spy(new RefactorContractInputOperation(aTaskWithContract, RefactoringOperationType.UPDATE));
        doReturn(groovyRefactoringOperation).when(refactorOperation).createGroovyScriptRefactoringOperation(eq(contractConstraint),
                notNull(ContractInputRefactorPair.class));
        when(groovyRefactoringOperation.getDiffs()).thenReturn(Lists.newArrayList(ReferenceDiff.newReferenceDiff("firstName", "lastName")));
        when(groovyRefactoringOperation.getScript()).thenReturn("lastName.length > 0");

        refactorOperation.addItemToRefactor(aContractInput().withName("lastName").build(), aTaskWithContract.getContract().getInputs().get(0));
        refactorOperation.setEditingDomain(domain);

        refactorOperation.run(monitor);

        assertThat(contractConstraint).hasExpression("lastName.length > 0");
        assertThat(contractConstraint).hasInputNames("lastName");
    }

    @Test
    public void update_contract_constraint_input_reference_in_contract_input_expressions() throws Exception {
        final Task aTaskWithContractAndOperations = aTaskWithContractAndOperations();
        final Expression contractInputExpression = aTaskWithContractAndOperations.getOperations().get(0).getRightOperand();
        final RefactorContractInputOperation refactorOperation = new RefactorContractInputOperation(aTaskWithContractAndOperations,
                RefactoringOperationType.UPDATE);

        refactorOperation.addItemToRefactor(aContractInput().withName("lastName").build(), aTaskWithContractAndOperations.getContract().getInputs().get(0));
        refactorOperation.setEditingDomain(domain);

        refactorOperation.run(monitor);

        assertThat(contractInputExpression).hasName("lastName").hasContent("lastName");
    }

    @Test
    public void scriptExpressionRefactoringAction_is_a_ContractInputScriptExpressionRefactoringAction() throws Exception {
        final RefactorContractInputOperation refactorOperation = new RefactorContractInputOperation(aTaskWithContract(),
                RefactoringOperationType.UPDATE);

        final AbstractScriptExpressionRefactoringAction<ContractInputRefactorPair> scriptExpressionRefactoringAction = refactorOperation
                .getScriptExpressionRefactoringAction(null, null, null, null, domain, RefactoringOperationType.UPDATE);

        assertThat(scriptExpressionRefactoringAction).isInstanceOf(ContractInputScriptExpressionRefactoringAction.class);
    }

    private ContractContainer aTaskWithContract() {
        return aTask().havingContract(aContract()
                .havingInput(aContractInput().withName("firstName"))
                .havingConstraint(aContractConstraint().withExpression("firstName.length > 0").havingInput("firstName"))).build();
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
