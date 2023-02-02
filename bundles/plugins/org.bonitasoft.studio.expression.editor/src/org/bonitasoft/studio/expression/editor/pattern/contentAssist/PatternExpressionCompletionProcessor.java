/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.expression.editor.pattern.contentAssist;

import static com.google.common.base.Predicates.not;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.expression.editor.pattern.GroovyExpressionPartitioner;
import org.bonitasoft.studio.expression.editor.provider.ExpressionLabelProvider;
import org.bonitasoft.studio.groovy.BonitaScriptGroovyCompilationUnit;
import org.bonitasoft.studio.groovy.GroovyCompilationUnitFactory;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.groovy.contentassist.ExtendedJavaContentAssistInvocationContext;
import org.bonitasoft.studio.model.expression.Expression;
import org.codehaus.groovy.eclipse.codeassist.completions.GroovyJavaFieldCompletionProposal;
import org.codehaus.groovy.eclipse.codeassist.completions.GroovyJavaMethodCompletionProposal;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.ui.JavaUIMessages;
import org.eclipse.jdt.internal.ui.text.java.AbstractJavaCompletionProposal;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.FileEditorInput;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

public class PatternExpressionCompletionProcessor implements IContentAssistProcessor {

    private final List<Expression> variableScope;
    private final PatternExpressionCompletionProposalComputer proposalComputer = new PatternExpressionCompletionProposalComputer();
    private char[] fCompletionAutoActivationCharacters;
    private final IEditorPart editorPart;
    private final ExpressionLabelProvider expressionLabelProvider = new ExpressionLabelProvider();

    public PatternExpressionCompletionProcessor(IEditorPart editorPart, List<Expression> variableScope) {
        this.variableScope = variableScope;
        this.editorPart = editorPart;
    }

    protected ExtendedJavaContentAssistInvocationContext createContext(ITextViewer viewer, int offset) {
        final RepositoryAccessor repositoryAccessor = RepositoryManager.getInstance().getAccessor();
        final GroovyCompilationUnitFactory gcuf = new GroovyCompilationUnitFactory(repositoryAccessor);
        ICompilationUnit newCompilationUnit;
        try {
            final IDocument document = viewer.getDocument();
            GroovyExpressionPartitioner groovyExpressionPartitioner = new GroovyExpressionPartitioner();
            groovyExpressionPartitioner.connect(document);
            ITypedRegion partition = groovyExpressionPartitioner.getPartition(offset);
            String script = document.get(partition.getOffset() + 2, partition.getLength() - 2);
            groovyExpressionPartitioner.disconnect();
            if (script.endsWith("}")) {
                script = script.substring(0, script.length() - 1);
            }
            newCompilationUnit = gcuf.newCompilationUnit(script, new NullProgressMonitor());
            Map<String, ScriptVariable> context = new HashMap<String, ScriptVariable>();
            variableScope.stream().forEach(exp -> context.put(exp.getName(), new ScriptVariable(exp.getName(), exp.getReturnType())));
            ((BonitaScriptGroovyCompilationUnit) newCompilationUnit).setContext(context);
            final Document tmpDocument = new Document(script);
            editorPart.init(null, new FileEditorInput((IFile) newCompilationUnit.getResource()));
            return new ExtendedJavaContentAssistInvocationContext(editorPart, viewer, offset, tmpDocument,
                    offset - partition.getOffset() - 2,
                    context);
        } catch (final JavaModelException | BadLocationException | PartInitException e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeCompletionProposals(org.eclipse.jface.text.ITextViewer, int)
     */
    @Override
    public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
        final IProgressMonitor monitor = new NullProgressMonitor();

        final ExtendedJavaContentAssistInvocationContext context = createContext(viewer, offset);

        final List<ICompletionProposal> proposals = proposalComputer.computeCompletionProposals(context, monitor);
        filterProposals(proposals);
        final ICompletionProposal[] result = proposals.toArray(new ICompletionProposal[proposals.size()]);

        try {
            context.getCompilationUnit().delete(true, monitor);
        } catch (final JavaModelException e) {
            BonitaStudioLog.error(e);
        }

        monitor.done();

        return result;
    }

    private void filterProposals(List<ICompletionProposal> proposals) {
        Iterables.removeIf(proposals, not(validProposal()));
    }

    private Predicate<? super ICompletionProposal> validProposal() {
        return new Predicate<ICompletionProposal>() {

            @Override
            public boolean apply(ICompletionProposal proposal) {
                return proposal instanceof GroovyJavaFieldCompletionProposal
                        || proposal instanceof GroovyJavaMethodCompletionProposal
                        ||  proposal instanceof AbstractJavaCompletionProposal
                                && isAnExpressionReference((AbstractJavaCompletionProposal) proposal);
            }

            private boolean isAnExpressionReference(AbstractJavaCompletionProposal proposal) {
                for (final Expression exp : variableScope) {
                    if (exp.getName().equals(proposal.getReplacementString())) {
                        proposal.setImage(expressionLabelProvider.getImage(exp));
                        return true;
                    }
                }
                return false;
            }
        };
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeContextInformation(org.eclipse.jface.text.ITextViewer, int)
     */
    @Override
    public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset) {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getCompletionProposalAutoActivationCharacters()
     */
    @Override
    public char[] getCompletionProposalAutoActivationCharacters() {
        return fCompletionAutoActivationCharacters;
    }

    public final void setCompletionProposalAutoActivationCharacters(char[] activationSet) {
        fCompletionAutoActivationCharacters = activationSet;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getContextInformationAutoActivationCharacters()
     */
    @Override
    public char[] getContextInformationAutoActivationCharacters() {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getErrorMessage()
     */
    @Override
    public String getErrorMessage() {
        return JavaUIMessages.JavaEditor_codeassist_noCompletions;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getContextInformationValidator()
     */
    @Override
    public IContextInformationValidator getContextInformationValidator() {
        return null;
    }

}
