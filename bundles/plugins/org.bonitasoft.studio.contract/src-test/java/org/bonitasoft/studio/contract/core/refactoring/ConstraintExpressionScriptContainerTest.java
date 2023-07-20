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

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.ContractConstraintBuilder.aContractConstraint;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.bonitasoft.studio.refactoring.core.script.IScriptRefactoringOperation;
import org.bonitasoft.studio.refactoring.core.script.IScriptRefactoringOperationFactory;
import org.bonitasoft.studio.refactoring.core.script.ReferenceDiff;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConstraintExpressionScriptContainerTest {

    @Mock
    private IScriptRefactoringOperationFactory scriptRefactoringOperationFactory;
    @Mock
    private IScriptRefactoringOperation scriptRefactoringOperation;
    @Mock
    private IProgressMonitor monitor;

    @Before
    public void setUp() throws Exception {
        when(scriptRefactoringOperationFactory.createScriptOperationFactory(anyString(), anyList())).thenReturn(scriptRefactoringOperation);
    }

    @Test
    public void should_updateScript_call_run_scriptOperation_and_update_newScript() throws Exception {
        final ConstraintExpressionScriptContainer constraintExpressionScriptContainer = new ConstraintExpressionScriptContainer(aContractConstraint()
                .withExpression("a script content").build(), scriptRefactoringOperationFactory);
        when(scriptRefactoringOperation.getRefactoredScript()).thenReturn("new script content");

        constraintExpressionScriptContainer.updateScript(new ArrayList<ReferenceDiff>(), monitor);

        verify(scriptRefactoringOperation).run(monitor);
        assertThat(constraintExpressionScriptContainer.getNewScript()).isEqualTo("new script content");
    }

    @Test
    public void should_have_name_than_underlying_constraint() throws Exception {
        final ConstraintExpressionScriptContainer constraintExpressionScriptContainer = new ConstraintExpressionScriptContainer(aContractConstraint()
                .withName("c1").build(), scriptRefactoringOperationFactory);

        assertThat(constraintExpressionScriptContainer.getName()).isEqualTo("c1");
    }

    @Test
    public void should_updateDependencies_create_command_renaming_input_names() throws Exception {
        final ContractConstraint constraint = aContractConstraint()
                .withExpression("script == content").havingInput("content", "script").build();
        final ConstraintExpressionScriptContainer constraintExpressionScriptContainer = new ConstraintExpressionScriptContainer(constraint,
                scriptRefactoringOperationFactory);

        final CompoundCommand command = constraintExpressionScriptContainer.updateDependencies(editingDomain(), newArrayList(new ContractInputRefactorPair(
                aContractInput().withName("newContent")
                        .build(), aContractInput().withName("content")
                        .build())));
        command.execute();

        assertThat(constraint.getInputNames()).containsOnly("newContent", "script");
    }

    private TransactionalEditingDomainImpl editingDomain() {
        return new TransactionalEditingDomainImpl(new ProcessItemProviderAdapterFactory());
    }

    @Test
    public void should_removeDependencies_create_command_removing_input_names() throws Exception {
        final ContractConstraint constraint = aContractConstraint()
                .withExpression("script == content").havingInput("content", "script").build();
        final ConstraintExpressionScriptContainer constraintExpressionScriptContainer = new ConstraintExpressionScriptContainer(constraint,
                scriptRefactoringOperationFactory);

        final CompoundCommand command = constraintExpressionScriptContainer.removeDependencies(editingDomain(), newArrayList(new ContractInputRefactorPair(
                null,
                aContractInput().withName("content")
                        .build())));
        command.execute();

        assertThat(constraint.getInputNames()).containsOnly("script");
    }
}
