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
package org.bonitasoft.studio.contract.ui.property.constraint.edit.editor.contentassist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.contract.ContractPlugin;
import org.bonitasoft.studio.contract.ui.expression.ContractInputLabelProvider;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.CodeVisitorSupport;
import org.codehaus.groovy.ast.ModuleNode;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.eclipse.codeassist.creators.MethodProposalCreator;
import org.codehaus.groovy.eclipse.codeassist.proposals.IGroovyProposal;
import org.codehaus.groovy.eclipse.codeassist.requestor.ContentAssistContext;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.core.CompletionProposal;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.groovy.search.VariableScope;
import org.eclipse.jdt.internal.ui.text.java.JavaCompletionProposal;
import org.eclipse.jdt.ui.text.java.ContentAssistInvocationContext;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposal;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposalComputer;
import org.eclipse.jdt.ui.text.java.JavaContentAssistInvocationContext;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.swt.graphics.Image;


/**
 * @author Romain Bioteau
 *
 */
public class ContractInputProposalsCodeVisitorSupport extends CodeVisitorSupport {

    private List<ICompletionProposal> proposals;
    private List<ContractInput> inputs = new ArrayList<ContractInput>();
    private final String prefix;
    private final JavaContentAssistInvocationContext context;
    private final ContentAssistContext contentAssistContext;
    private final ClassLoader classLoader;
    private final IProgressMonitor monitor;
    private final IJavaCompletionProposalComputer completionComputer;
    private final MethodProposalCreator methodProposalCreator;
    private final ModuleNode moduleNode;

    public ContractInputProposalsCodeVisitorSupport(final List<ContractInput> inputs,
            final CodeVisitorSupportContext codeVisitorSupportContext,
            final IProgressMonitor monitor) {
        this.inputs = inputs;
        prefix = codeVisitorSupportContext.getPrefix();
        context = codeVisitorSupportContext.getContext();
        contentAssistContext = codeVisitorSupportContext.getContenttAssistContext();
        completionComputer = codeVisitorSupportContext.getCompletionComputer();
        classLoader = codeVisitorSupportContext.getContextClassloader();
        methodProposalCreator = codeVisitorSupportContext.getMethodProposalCreator();
        moduleNode = codeVisitorSupportContext.getModuleNode();
        this.monitor = monitor;
    }

    @Override
    public void visitVariableExpression(final VariableExpression expression) {
        proposals = getInputProposals(context, inputs, prefix);
        final String fullyQualifiedType = getTypeFor(expression.getName(), inputs);
        if (Map.class.getName().equals(fullyQualifiedType)) { //COMPLEX
            final ContractInput complexInput = getInputWithName(expression.getName(), inputs);
            proposals = getInputProposals(context, complexInput.getInputs(), prefix);
        } else if (fullyQualifiedType != null) {
            proposals = getMethodProposals(contentAssistContext, context, inputs, prefix, expression.getName(), fullyQualifiedType);
        }
    }

    @Override
    public void visitConstantExpression(final ConstantExpression expression) {
        proposals = Collections.emptyList();
        final String fullyQualifiedType = getTypeFor(expression.getText(), inputs);
        if (fullyQualifiedType != null && prefix.isEmpty()) {
            proposals = getMethodProposals(contentAssistContext, context, inputs, prefix, expression.getText(), fullyQualifiedType);
        }
    }

    @Override
    public void visitBlockStatement(final BlockStatement block) {
        proposals = getInputProposals(context, inputs, prefix);
    }

    @Override
    public void visitBinaryExpression(final BinaryExpression expression) {
        proposals = completionComputer.computeCompletionProposals(context, monitor);
        final BinaryExpression binaryExpression = (BinaryExpression) contentAssistContext.getPerceivedCompletionNode();
        final Expression leftExpression = binaryExpression.getLeftExpression();
        String multipleInputName = null;
        if (leftExpression instanceof PropertyExpression) {
            final PropertyExpression propertyExpr = (PropertyExpression) leftExpression;
            final Expression objectExpression = propertyExpr.getProperty();
            multipleInputName = objectExpression.getText();
        } else if (leftExpression instanceof VariableExpression) {
            multipleInputName = ((VariableExpression) leftExpression).getName();
        }
        if (multipleInputName != null) {
            final ContractInput multipleInput = getInputWithName(multipleInputName, inputs);
            final ContractInput copy = EcoreUtil.copy(multipleInput);
            copy.setMultiple(false);
            final String fullyQualifiedType = ExpressionHelper.getContractInputReturnType(copy);
            if (fullyQualifiedType != null && prefix.isEmpty()) {
                proposals = getMethodProposals(contentAssistContext, context, inputs, prefix, multipleInputName, fullyQualifiedType);
            }
        }
    }

    protected List<ICompletionProposal> getInputProposals(final ContentAssistInvocationContext context, final List<ContractInput> inputs,
            final CharSequence computeIdentifierPrefix) {
        final List<ICompletionProposal> result = new ArrayList<ICompletionProposal>();
        for (final ContractInput input : inputs) {
            if (computeIdentifierPrefix.toString().isEmpty() || input.getName().startsWith(computeIdentifierPrefix.toString())) {
                result.add(createProposalFor(context, computeIdentifierPrefix, input));
            }
        }
        return result;
    }

    private ICompletionProposal createProposalFor(final ContentAssistInvocationContext context,
            final CharSequence prefix,
            final ContractInput input) {
        final String name = input.getName();
        final CompletionProposal proposal = CompletionProposal.create(CompletionProposal.FIELD_REF, context.getInvocationOffset());
        proposal.setCompletion(name.substring(prefix.length()).toCharArray());
        proposal.setName(name.toCharArray());
        proposal.setFlags(Flags.AccPublic);
        proposal.setReplaceRange(context.getInvocationOffset(), context.getInvocationOffset());
        final String completion = String.valueOf(proposal.getCompletion());
        final int start = proposal.getReplaceStart();
        final int end = proposal.getReplaceEnd();
        final int length = end - start;

        final ContractInputLabelProvider labelProvider = new ContractInputLabelProvider();
        final Image image = labelProvider.getImage(input);
        return new JavaCompletionProposal(completion, start, length, image, labelProvider.getStyledString(input), 100, false);
    }

    private List<ICompletionProposal> getMethodProposals(final ContentAssistContext contentAssistContext, final JavaContentAssistInvocationContext javaContext,
            final List<ContractInput> inputs, final CharSequence prefix, final String inputName, final String fullyQualifiedType) {
        final List<ICompletionProposal> result = new ArrayList<ICompletionProposal>();
        //COMPLEX input
        if (Map.class.getName().equals(fullyQualifiedType)) {
            final ContractInput complexInput = getInputWithName(inputName, inputs);
            result.addAll(getInputProposals(javaContext, complexInput.getInputs(), prefix));
        }

        methodProposalCreator.setCurrentScope(new VariableScope(null, moduleNode, false));
        final List<IGroovyProposal> allProposals = methodProposalCreator.findAllProposals(new ClassNode(getClassForQualifiedName(fullyQualifiedType)),
                Collections.<ClassNode> emptySet(), prefix.toString(),
                false, false);
        for (final IGroovyProposal p : allProposals) {
            try {
                final IJavaCompletionProposal javaProposal = p.createJavaProposal(contentAssistContext, javaContext);
                result.add(javaProposal);
            }catch (NullPointerException  e) {
                // No CompletionEngine available ?
            }
        }
        return result;
    }

    private String getTypeFor(final String inputName, final List<ContractInput> inputs) {
        if (inputs.isEmpty()) {
            return null;
        }
        final ContractInput contractInput = inputs.get(0);
        final Contract contract = ModelHelper.getFirstContainerOfType(contractInput, Contract.class);
        final TreeIterator<EObject> treeIterator = contract.eAllContents();
        while (treeIterator.hasNext()) {
            final EObject eObject = treeIterator.next();
            if (eObject instanceof ContractInput && ((ContractInput) eObject).getName().equals(inputName)) {
                return ExpressionHelper.getContractInputReturnType((ContractInput) eObject);
            }
        }
        return null;
    }


    private ContractInput getInputWithName(final String name, final List<ContractInput> inputs) {
        final ContractInput contractInput = inputs.get(0);
        final Contract contract = ModelHelper.getFirstContainerOfType(contractInput, Contract.class);
        final TreeIterator<EObject> treeIterator = contract.eAllContents();
        while (treeIterator.hasNext()) {
            final EObject eObject = treeIterator.next();
            if (eObject instanceof ContractInput && ((ContractInput) eObject).getName().equals(name)) {
                return (ContractInput) eObject;
            }
        }
        throw new IllegalArgumentException("Input with name \"" + name + "\" does not exists");
    }

    private Class<?> getClassForQualifiedName(final String fullyQualifiedType) {
        try {
            return classLoader.loadClass(fullyQualifiedType);
        } catch (final ClassNotFoundException e) {
            BonitaStudioLog.debug("Failed to retrieve class for type:" + fullyQualifiedType, ContractPlugin.PLUGIN_ID);
            return null;
        }
    }

    public List<ICompletionProposal> getProposals() {
        return proposals;
    }

}
