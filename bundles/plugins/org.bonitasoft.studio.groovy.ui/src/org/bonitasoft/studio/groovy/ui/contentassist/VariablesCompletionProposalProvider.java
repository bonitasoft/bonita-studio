/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.groovy.ui.contentassist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.groovy.ui.viewer.GroovyViewer;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.eclipse.codeassist.requestor.ContentAssistContext;
import org.codehaus.groovy.eclipse.codeassist.requestor.GroovyCompletionProposalComputer;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.CompletionProposal;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.text.java.JavaCompletionProposal;
import org.eclipse.jdt.ui.text.java.CompletionProposalLabelProvider;
import org.eclipse.jdt.ui.text.java.ContentAssistInvocationContext;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposalComputer;
import org.eclipse.jdt.ui.text.java.JavaContentAssistInvocationContext;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

public class VariablesCompletionProposalProvider implements IJavaCompletionProposalComputer {

    @Override
    public void sessionStarted() {
    }

    @Override
    public List<ICompletionProposal> computeCompletionProposals(final ContentAssistInvocationContext context, final IProgressMonitor monitor) {
        final List<ICompletionProposal> list = new ArrayList<ICompletionProposal>();
        if (context instanceof JavaContentAssistInvocationContext) {
            final ICompilationUnit unit = ((JavaContentAssistInvocationContext) context).getCompilationUnit();
            if (unit instanceof GroovyCompilationUnit) {
                final ITextViewer viewer = context.getViewer();
                final List<ScriptVariable> variables = getScriptVariables(viewer);
                if (variables.isEmpty()) {
                    return list;
                }
                if (((GroovyCompilationUnit) unit).getModuleNode() == null) {
                    return Collections.emptyList();
                }
                final ContentAssistContext assistContext = new GroovyCompletionProposalComputer().createContentAssistContext((GroovyCompilationUnit) unit,
                        context.getInvocationOffset(), context.getDocument());

                final CompletionProposalLabelProvider fLabelProvider = new CompletionProposalLabelProvider();
                CharSequence prefix = null;
                try {
                    prefix = context.computeIdentifierPrefix();
                    if (prefix.toString().isEmpty()) {
                        return list;
                    }
                } catch (final BadLocationException e) {
                    BonitaStudioLog.error(e);
                }
                final Set<String> addedProposal = new HashSet<String>();
                if (variables != null) {
                    for (final ScriptVariable f : variables) {
                        final String name = f.getName();
                        final String typeName = f.getType();
                        if (assistContext != null && assistContext.completionNode instanceof VariableExpression) {
                            if (assistContext.completionNode.getText() != null && assistContext.completionNode.getText().equals(name)) {
                                return Collections.emptyList();
                            }
                        }
                        if (name.startsWith(prefix.toString())) {
                            addedProposal.add(name);
                            list.add(getProposalFor(context, fLabelProvider, prefix, name, typeName));
                        }
                    }
                }
            }
            return list;
        }
        return Collections.emptyList();
    }

    @SuppressWarnings("unchecked")
    private List<ScriptVariable> getScriptVariables(final ITextViewer viewer) {
        final List<ScriptVariable> result = new ArrayList<ScriptVariable>();
        final List<ScriptVariable> nodes = (List<ScriptVariable>) viewer.getTextWidget().getData(GroovyViewer.PROCESS_VARIABLES_DATA_KEY);
        if (nodes != null) {
            result.addAll(nodes);
        }
        final List<ScriptVariable> providedScriptVariables = (List<ScriptVariable>) viewer.getTextWidget().getData(
                GroovyViewer.BONITA_KEYWORDS_DATA_KEY);
        if (providedScriptVariables != null) {
            result.addAll(providedScriptVariables);
        }
        return result;
    }

    private ICompletionProposal getProposalFor(final ContentAssistInvocationContext context,
            final CompletionProposalLabelProvider fLabelProvider, final CharSequence prefix, final String name, final String typeName) {
        final CompletionProposal proposal = CompletionProposal.create(CompletionProposal.FIELD_REF, context.getInvocationOffset());
        proposal.setCompletion(name.substring(prefix.length()).toCharArray());
        proposal.setName(name.toCharArray());
        proposal.setFlags(Flags.AccPublic);
        proposal.setReplaceRange(context.getInvocationOffset(), context.getInvocationOffset());
        if (typeName != null) {
            proposal.setSignature(Signature.createTypeSignature(typeName, true).toCharArray());
        }
        final String completion = String.valueOf(proposal.getCompletion());
        final int start = proposal.getReplaceStart();
        final int end = proposal.getReplaceEnd();
        final int length = end - start;

        final StyledString label = fLabelProvider.createStyledLabel(proposal);
        final Image image = JavaPlugin.getImageDescriptorRegistry().get(fLabelProvider.createImageDescriptor(proposal));
        return new JavaCompletionProposal(completion, start, length, image, label, 100, false);
    }

    @Override
    public List<IContextInformation> computeContextInformation(final ContentAssistInvocationContext context, final IProgressMonitor monitor) {
        return null;
    }

    @Override
    public String getErrorMessage() {
        return null;
    }

    @Override
    public void sessionEnded() {
    }

}
