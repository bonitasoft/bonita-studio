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

import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.contract.ContractPlugin;
import org.bonitasoft.studio.contract.ui.expression.ContractInputLabelProvider;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.PropertyExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.eclipse.codeassist.creators.MethodProposalCreator;
import org.codehaus.groovy.eclipse.codeassist.proposals.IGroovyProposal;
import org.codehaus.groovy.eclipse.codeassist.requestor.ContentAssistContext;
import org.codehaus.groovy.eclipse.codeassist.requestor.GroovyCompletionProposalComputer;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
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
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.swt.graphics.Image;


/**
 * @author Romain Bioteau
 *
 */
public class ContractInputCompletionProposalComputer extends GroovyCompletionProposalComputer implements IJavaCompletionProposalComputer {

    public static final String INPUTS = "INPUTS";
    private IRepository repository;

    /* (non-Javadoc)
     * @see org.eclipse.jdt.ui.text.java.IJavaCompletionProposalComputer#computeCompletionProposals(org.eclipse.jdt.ui.text.java.ContentAssistInvocationContext, org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public List<ICompletionProposal> computeCompletionProposals(final ContentAssistInvocationContext context, final IProgressMonitor monitor) {
        repository = RepositoryManager.getInstance().getCurrentRepository();
        final ITextViewer viewer = context.getViewer();
        final JavaContentAssistInvocationContext javaContext = (JavaContentAssistInvocationContext) context;
        final ContentAssistContext contentAssistContext = createContentAssistContext(
                (GroovyCompilationUnit) javaContext.getCompilationUnit(),
                context.getInvocationOffset(), context.getDocument());
        final List<ContractInput> inputs = (List<ContractInput>) viewer.getTextWidget().getData(INPUTS);
        CharSequence computeIdentifierPrefix = "";
        try {
            computeIdentifierPrefix = javaContext.computeIdentifierPrefix();
        } catch (final BadLocationException e) {
            BonitaStudioLog.error("Failed to compute identifier prefix in ContractConstraint expression editor", e, ContractPlugin.PLUGIN_ID);
        }
        if (contentAssistContext.completionNode instanceof VariableExpression) {
            final VariableExpression expression = (VariableExpression) contentAssistContext.completionNode;
            final String fullyQualifiedType = getTypeFor(expression.getName(), inputs);
            if (fullyQualifiedType != null && Map.class.getName().equals(fullyQualifiedType)) { //COMPLEX
                final ContractInput complexInput = getInputWithName(expression.getName(), inputs);
                return getInputProposals(context, complexInput.getInputs(), computeIdentifierPrefix);
            } else if (fullyQualifiedType != null) {
                return getMethodProposals(contentAssistContext, javaContext, inputs, computeIdentifierPrefix, expression.getName(), fullyQualifiedType);
            }
            return getInputProposals(context, inputs, computeIdentifierPrefix);
        } else if (contentAssistContext.completionNode instanceof ConstantExpression) {
            final ConstantExpression expression = (ConstantExpression) contentAssistContext.completionNode;
            final String fullyQualifiedType = getTypeFor(expression.getText(), inputs);
            if (fullyQualifiedType != null && computeIdentifierPrefix.toString().isEmpty()) {
                return getMethodProposals(contentAssistContext, javaContext, inputs, computeIdentifierPrefix, expression.getText(), fullyQualifiedType);
            }
            return Collections.emptyList();
        } else if (contentAssistContext.completionNode instanceof BlockStatement) {
            return getInputProposals(context, inputs, computeIdentifierPrefix);
        } else if (contentAssistContext.completionNode instanceof BinaryExpression) {
            final BinaryExpression binaryExpression = (BinaryExpression) contentAssistContext.completionNode;
            final Expression leftExpression = binaryExpression.getLeftExpression();
            String multipleInputName = null;
            if (leftExpression instanceof PropertyExpression) {
                final PropertyExpression propertyExpr = (PropertyExpression) leftExpression;
                final Expression objectExpression = propertyExpr.getProperty();
                multipleInputName = objectExpression.getText();
            } else if (leftExpression instanceof VariableExpression) {
                multipleInputName = ((VariableExpression) leftExpression).getName();
            }
            if(multipleInputName != null){
                final ContractInput multipleInput = getInputWithName(multipleInputName, inputs);
                final ContractInput copy = EcoreUtil.copy(multipleInput);
                copy.setMultiple(false);
                final String fullyQualifiedType = ExpressionHelper.getContractInputReturnType(copy);
                if (fullyQualifiedType != null && computeIdentifierPrefix.toString().isEmpty()) {
                    return getMethodProposals(contentAssistContext, javaContext, inputs, computeIdentifierPrefix, multipleInputName, fullyQualifiedType);
                }
            }
            return super.computeCompletionProposals(context, monitor);
        } else {
            return super.computeCompletionProposals(context, monitor);
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
        //        if (typeName != null) {
        //            proposal.setSignature(Signature.createTypeSignature(typeName, true).toCharArray());
        //        }
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
        final MethodProposalCreator methodProposalCreator = new MethodProposalCreator();
        methodProposalCreator.setCurrentScope(new VariableScope(null, contentAssistContext.unit.getModuleNode(), false));
        final List<IGroovyProposal> allProposals = methodProposalCreator.findAllProposals(new ClassNode(getClassForQualifiedName(fullyQualifiedType)),
                Collections.<ClassNode> emptySet(), prefix.toString(),
                false, false);
        for (final IGroovyProposal p : allProposals) {
            final IJavaCompletionProposal javaProposal = p.createJavaProposal(contentAssistContext, javaContext);
            result.add(javaProposal);
        }

        return result;
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
        return null;

    }

    private Class<?> getClassForQualifiedName(final String fullyQualifiedType) {
        final URLClassLoader classloader = repository.createProjectClassloader();
        try {
            return classloader.loadClass(fullyQualifiedType);
        } catch (final ClassNotFoundException e) {
            return null;
        }
    }


    private String getTypeFor(final String inputName, final List<ContractInput> inputs) {
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


}
