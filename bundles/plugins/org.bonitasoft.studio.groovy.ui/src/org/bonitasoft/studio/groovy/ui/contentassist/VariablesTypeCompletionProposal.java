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
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.groovy.ui.viewer.GroovyViewer;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.eclipse.codeassist.ProposalUtils;
import org.codehaus.groovy.eclipse.codeassist.completions.GroovyJavaGuessingCompletionProposal;
import org.codehaus.groovy.eclipse.codeassist.processors.GroovyCompletionProposal;
import org.codehaus.groovy.eclipse.codeassist.proposals.ProposalFormattingOptions;
import org.codehaus.groovy.eclipse.codeassist.requestor.ContentAssistContext;
import org.codehaus.groovy.eclipse.codeassist.requestor.GroovyCompletionProposalComputer;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.CompletionContext;
import org.eclipse.jdt.core.CompletionProposal;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.groovy.core.util.ReflectionUtils;
import org.eclipse.jdt.internal.codeassist.InternalCompletionContext;
import org.eclipse.jdt.ui.text.java.ContentAssistInvocationContext;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposalComputer;
import org.eclipse.jdt.ui.text.java.JavaContentAssistInvocationContext;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;

/**
 * @author Romain Bioteau
 * @author Baptiste Mesta
 */
public class VariablesTypeCompletionProposal implements IJavaCompletionProposalComputer {

    private IJavaProject javaProject;

    @Override
    public void sessionStarted() {
        javaProject = RepositoryManager.getInstance().getCurrentRepository().getJavaProject();
    }

    @Override
    public List<ICompletionProposal> computeCompletionProposals(final ContentAssistInvocationContext context, final IProgressMonitor monitor) {
        final List<ICompletionProposal> list = new ArrayList<ICompletionProposal>();
        if (context instanceof JavaContentAssistInvocationContext) {
            final CompletionContext coreContext = ((JavaContentAssistInvocationContext) context).getCoreContext();
            if (coreContext != null && !coreContext.isExtended()) {
                // must use reflection to set the fields
                ReflectionUtils.setPrivateField(InternalCompletionContext.class, "isExtended", coreContext, true);
            }
            final ICompilationUnit unit = ((JavaContentAssistInvocationContext) context).getCompilationUnit();
            if (unit instanceof GroovyCompilationUnit) {
                final ITextViewer viewer = context.getViewer();
                final List<ScriptVariable> scriptVariables = getScriptVariables(viewer);
                if (scriptVariables.isEmpty()) {
                    return list;
                }
                if (((GroovyCompilationUnit) unit).getModuleNode() == null) {
                    return Collections.emptyList();
                }
                final ContentAssistContext assistContext = new GroovyCompletionProposalComputer().createContentAssistContext((GroovyCompilationUnit) unit,
                        context.getInvocationOffset(), context.getDocument());
                CharSequence prefix = null;
                try {
                    prefix = context.computeIdentifierPrefix();
                } catch (final BadLocationException e) {
                    BonitaStudioLog.error(e);
                }

                if (assistContext != null && assistContext.completionNode instanceof VariableExpression) {
                    try {
                        final VariableExpression expr = (VariableExpression) assistContext.completionNode;
                        if (scriptVariables != null) {
                            for (final ScriptVariable f : scriptVariables) {
                                if (expr.getName().equals(f.getName())) {
                                    final IType type = javaProject.findType(f.getType());
                                    if (type == null) {
                                        return list;
                                    }
                                    for (final IMethod m : type.getMethods()) {
                                        if (m.getElementName().startsWith(prefix.toString())) {
                                            final GroovyCompletionProposal proposal = new GroovyCompletionProposal(CompletionProposal.METHOD_REF,
                                                    context.getInvocationOffset());
                                            proposal.setName(m.getElementName().toCharArray());
                                            proposal.setCompletion(m.getElementName().substring(prefix.length()).toCharArray());
                                            proposal.setFlags(m.getFlags());

                                            if (prefix.length() == m.getElementName().length()) {
                                                proposal.setReplaceRange(context.getInvocationOffset(), context.getInvocationOffset());
                                                proposal.setReceiverRange(0, 0);
                                            } else {
                                                proposal.setReplaceRange(context.getInvocationOffset() - prefix.length(), context.getInvocationOffset());
                                                proposal.setReceiverRange(prefix.length(), prefix.length());
                                            }

                                            final char[][] parametersArray = new char[m.getParameterNames().length][256];
                                            final List<Parameter> parameters = new ArrayList<Parameter>();
                                            for (int i = 0; i < m.getParameterNames().length; i++) {
                                                parametersArray[i] = m.getParameterNames()[i].toCharArray();
                                                parameters.add(new Parameter(ClassHelper.make(Signature
                                                        .getSignatureSimpleName(m.getParameterTypes()[i])), m.getParameterNames()[i]));
                                            }

                                            final ClassNode classNode = ClassHelper.make(m.getDeclaringType()
                                                    .getFullyQualifiedName());
                                            proposal.setDeclarationSignature(ProposalUtils.createTypeSignature(classNode));
                                            proposal.setParameterNames(parametersArray);
                                            if (m.getDeclaringType().getFullyQualifiedName().equals(f.getType())) {
                                                proposal.setRelevance(100);
                                            }

                                            final MethodNode methodNode = new MethodNode(m.getElementName(), m.getFlags(), ClassHelper.make(Signature
                                                    .getSignatureSimpleName(m.getReturnType())),
                                                    parameters.toArray(new Parameter[parameters.size()]), new ClassNode[0], null);
                                            final char[] methodSignature = ProposalUtils.createMethodSignature(methodNode);
                                            proposal.setSignature(methodSignature);

                                            final GroovyJavaGuessingCompletionProposal groovyProposal = GroovyJavaGuessingCompletionProposal.createProposal(
                                                    proposal,
                                                    (JavaContentAssistInvocationContext) context, true, "Groovy", ProposalFormattingOptions.newFromOptions());
                                            if (groovyProposal != null) {
                                                list.add(groovyProposal);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } catch (final JavaModelException e) {
                        BonitaStudioLog.error(e);
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
