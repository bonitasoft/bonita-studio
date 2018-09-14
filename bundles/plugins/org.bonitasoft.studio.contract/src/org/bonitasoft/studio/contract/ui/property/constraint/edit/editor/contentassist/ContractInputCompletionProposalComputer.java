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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.contract.ContractPlugin;
import org.bonitasoft.studio.model.process.ContractInput;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.ModuleNode;
import org.codehaus.groovy.eclipse.codeassist.creators.MethodProposalCreator;
import org.codehaus.groovy.eclipse.codeassist.requestor.ContentAssistContext;
import org.codehaus.groovy.eclipse.codeassist.requestor.GroovyCompletionProposalComputer;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.ui.text.java.ContentAssistInvocationContext;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposalComputer;
import org.eclipse.jdt.ui.text.java.JavaContentAssistInvocationContext;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;

/**
 * @author Romain Bioteau
 */
public class ContractInputCompletionProposalComputer extends GroovyCompletionProposalComputer
        implements IJavaCompletionProposalComputer {

    public static final String INPUTS = "INPUTS";

    /*
     * (non-Javadoc)
     * @see org.eclipse.jdt.ui.text.java.IJavaCompletionProposalComputer#computeCompletionProposals(org.eclipse.jdt.ui.text.java.ContentAssistInvocationContext,
     * org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public List<ICompletionProposal> computeCompletionProposals(final ContentAssistInvocationContext context,
            final IProgressMonitor monitor) {
        if (!(context instanceof JavaContentAssistInvocationContext)
                || context instanceof JavaContentAssistInvocationContext
                        && !(((JavaContentAssistInvocationContext) context)
                                .getCompilationUnit() instanceof GroovyCompilationUnit)) {
            return Collections.emptyList();
        }
        final List<ContractInput> inputs = getContractInputs(context);
        if (inputs.isEmpty()) {
            return Collections.emptyList();
        }
        final JavaContentAssistInvocationContext javaContext = (JavaContentAssistInvocationContext) context;
        GroovyCompilationUnit compilationUnit = (GroovyCompilationUnit) javaContext.getCompilationUnit();
        if (compilationUnit.getModuleNode() == null) {
            return Collections.emptyList();
        }
        final ContentAssistContext contentAssistContext = createContentAssistContext(
                compilationUnit,
                context.getInvocationOffset(), context.getDocument());
        if (contentAssistContext == null) {
            return Collections.emptyList();
        }
        CharSequence computeIdentifierPrefix = "";
        try {
            computeIdentifierPrefix = javaContext.computeIdentifierPrefix();
        } catch (final BadLocationException e) {
            BonitaStudioLog.error("Failed to compute identifier prefix in ContractConstraint expression editor", e,
                    ContractPlugin.PLUGIN_ID);
            return Collections.emptyList();
        }
        final CodeVisitorSupportContext codeVisitorSupportContext = new CodeVisitorSupportContext(
                computeIdentifierPrefix.toString(),
                (JavaContentAssistInvocationContext) context,
                contentAssistContext,
                getProjectClassloader(compilationUnit),
                new GroovyCompletionProposalComputer(),
                createMethodProposalCreator(),
                compilationUnit.getModuleNode());
        final ContractInputProposalsCodeVisitorSupport codeVistor = new ContractInputProposalsCodeVisitorSupport(inputs,
                codeVisitorSupportContext,
                monitor);
        final ASTNode completionNode = contentAssistContext.getPerceivedCompletionNode();
        if (completionNode != null) {
            completionNode.visit(codeVistor);
        }
        final List<ICompletionProposal> proposals = codeVistor.getProposals();
        if (proposals == null || proposals.isEmpty()) {
            return super.computeCompletionProposals(context, monitor);
        }
        return proposals;
    }

    protected ModuleNode getModuleNode(final ContentAssistContext contentAssistContext) {
        return contentAssistContext.unit.getModuleNode();
    }

    protected MethodProposalCreator createMethodProposalCreator() {
        return new MethodProposalCreator();
    }

    @SuppressWarnings("unchecked")
    protected List<ContractInput> getContractInputs(final ContentAssistInvocationContext context) {
        final ITextViewer viewer = context.getViewer();
        List<ContractInput> inputs = (List<ContractInput>) viewer.getTextWidget().getData(INPUTS);
        if (inputs == null) {
            inputs = new ArrayList<ContractInput>();
        }
        return inputs;
    }

    protected ClassLoader getProjectClassloader(GroovyCompilationUnit compilationUnit) {
        return compilationUnit.getModuleNode().getUnit().getClassLoader();
    }

}
