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
package org.bonitasoft.studio.contract.ui.property.constraint.edit.editor.contentassist;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.codehaus.groovy.ast.ModuleNode;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.eclipse.codeassist.creators.MethodProposalCreator;
import org.codehaus.groovy.eclipse.codeassist.requestor.ContentAssistContext;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.ui.text.java.JavaContentAssistInvocationContext;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class ContractInputCompletionProposalComputerTest {

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();

    @Spy
    private ContractInputCompletionProposalComputer proposalComputer;

    @Mock
    private JavaContentAssistInvocationContext context;

    @Mock
    private ContentAssistContext contentAssistContext;

    @Mock
    private MethodProposalCreator methodProposalCreator;

    @Mock
    private ModuleNode moduleNode;

    @Mock
    private GroovyCompilationUnit groovyCompilationUnit;

    @Mock
    private IProgressMonitor monitor;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        contract.getInputs().add(buildInput(null, "name", ContractInputType.TEXT, false));
        final ContractInput employeeInput = buildInput(null, "employee", ContractInputType.COMPLEX, false);
        buildInput(employeeInput, "firstName", ContractInputType.TEXT, false);
        buildInput(employeeInput, "lastName", ContractInputType.TEXT, false);
        buildInput(employeeInput, "skills", ContractInputType.TEXT, true);
        contract.getInputs().add(employeeInput);
        contract.getInputs().add(buildInput(null, "days", ContractInputType.TEXT, true));
        doReturn(contract.getInputs()).when(proposalComputer).getContractInputs(context);
        doReturn(contentAssistContext).when(proposalComputer).createContentAssistContext(any(),
                anyInt(), any());
        doReturn(ContractInputCompletionProposalComputerTest.class.getClassLoader()).when(proposalComputer)
                .getProjectClassloader(any());
        doReturn(methodProposalCreator).when(proposalComputer).createMethodProposalCreator();
        when(context.computeIdentifierPrefix()).thenReturn("");
        when(contentAssistContext.getPerceivedCompletionNode()).thenReturn(new VariableExpression(""));
        when(context.getCompilationUnit()).thenReturn(groovyCompilationUnit);
        when(groovyCompilationUnit.getModuleNode()).thenReturn(moduleNode);
    }

    @Test
    public void should_computeCompletionProposals_return_emptyList_if_context_is_not_a_JavaContentAssistInvocationContext()
            throws Exception {
        assertThat(proposalComputer.computeCompletionProposals(null, monitor)).isEmpty();
    }

    @Test
    public void should_computeCompletionProposals_log_BadLocationException_and_return_emptyList() throws Exception {
        when(context.computeIdentifierPrefix()).thenThrow(new BadLocationException());
        assertThat(proposalComputer.computeCompletionProposals(context, monitor)).isEmpty();
    }

    @Test
    public void should_computeCompletionProposals_return_empty_list_when_inputs_is_empty() throws Exception {
        doReturn(Collections.emptyList()).when(proposalComputer).getContractInputs(context);
        assertThat(proposalComputer.computeCompletionProposals(context, monitor)).isEmpty();
    }

    @Test
    public void should_computeCompletionProposals_returns_inputs_proposal() throws Exception {
        final List<ICompletionProposal> proposals = proposalComputer.computeCompletionProposals(context, monitor);
        assertThat(proposals).extracting("displayString").contains("name -- TEXT", "employee -- COMPLEX", "days -- TEXT");
    }

    @Test
    public void should_computeCompletionProposals_returns_no_proposals_for_varaible_expression() throws Exception {
        doReturn(Collections.emptyList()).when(proposalComputer).getContractInputs(context);
        final List<ICompletionProposal> proposals = proposalComputer.computeCompletionProposals(context, null);
        assertThat(proposals).isEmpty();
    }

    @Test
    public void should_computeCompletionProposals_returns_no_proposals_for_unsupported_expression() throws Exception {
        doReturn(Collections.emptyList()).when(proposalComputer).getContractInputs(context);
        final List<ICompletionProposal> proposals = proposalComputer.computeCompletionProposals(context, null);
        assertThat(proposals).isEmpty();
    }

    @Test
    public void should_computeCompletionProposals_returns_complex_input_children() throws Exception {
        when(contentAssistContext.getPerceivedCompletionNode()).thenReturn(new VariableExpression("employee"));
        final List<ICompletionProposal> proposals = proposalComputer.computeCompletionProposals(context, monitor);
        assertThat(proposals).extracting("displayString").contains("firstName -- TEXT", "lastName -- TEXT",
                "skills -- TEXT");
    }

    @Test
    public void should_computeCompletionProposals_call_java_methods_proposal_creator() throws Exception {
        when(contentAssistContext.getPerceivedCompletionNode()).thenReturn(new VariableExpression("days"));
        proposalComputer.computeCompletionProposals(context, monitor);
        verify(methodProposalCreator).findAllProposals(any(), anySet(), eq(""), eq(false), eq(false));
    }

    @Test
    public void should_computeCompletionProposals_returns_complex_and_multiple_input_children_for_variable_expression()
            throws Exception {
        final BinaryExpression binaryExpression = new BinaryExpression(new VariableExpression("employee"), null, null);
        when(contentAssistContext.getPerceivedCompletionNode()).thenReturn(binaryExpression);
        assertThat(proposalComputer.computeCompletionProposals(context, monitor)).extracting("displayString").contains(
                "firstName -- TEXT", "lastName -- TEXT",
                "skills -- TEXT");
    }

    @Test
    public void should_computeCompletionProposals_returns_complex_and_multiple_input_children_for_property_expression()
            throws Exception {
        final BinaryExpression binaryExpression = new BinaryExpression(
                new PropertyExpression(new ConstantExpression("employee"), new ConstantExpression(
                        "employee")),
                null, null);
        when(contentAssistContext.getPerceivedCompletionNode()).thenReturn(binaryExpression);
        assertThat(proposalComputer.computeCompletionProposals(context, monitor)).extracting("displayString").contains(
                "firstName -- TEXT", "lastName -- TEXT",
                "skills -- TEXT");
    }

    private ContractInput buildInput(final ContractInput parent, final String name, final ContractInputType type,
            final boolean multiple) {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName(name);
        input.setType(type);
        input.setMultiple(multiple);
        if (parent != null) {
            parent.getInputs().add(input);
        }
        return input;
    }
}
