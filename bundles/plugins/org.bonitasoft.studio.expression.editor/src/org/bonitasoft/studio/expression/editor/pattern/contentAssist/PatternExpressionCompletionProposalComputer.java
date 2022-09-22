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
package org.bonitasoft.studio.expression.editor.pattern.contentAssist;

import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.groovy.contentassist.ExtendedJavaContentAssistInvocationContext;
import org.codehaus.groovy.eclipse.codeassist.requestor.GroovyCompletionProposalComputer;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.internal.ui.text.java.AbstractJavaCompletionProposal;
import org.eclipse.jdt.ui.text.java.ContentAssistInvocationContext;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposalComputer;
import org.eclipse.jdt.ui.text.java.JavaContentAssistInvocationContext;
import org.eclipse.jface.text.contentassist.ICompletionProposal;

public class PatternExpressionCompletionProposalComputer extends GroovyCompletionProposalComputer implements IJavaCompletionProposalComputer {

    
    /*
     * (non-Javadoc)
     * @see org.eclipse.jdt.ui.text.java.IJavaCompletionProposalComputer#computeCompletionProposals(org.eclipse.jdt.ui.text.java.ContentAssistInvocationContext,
     * org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public List<ICompletionProposal> computeCompletionProposals(final ContentAssistInvocationContext context, final IProgressMonitor monitor) {
        if (!(context instanceof JavaContentAssistInvocationContext)
                || context instanceof JavaContentAssistInvocationContext
                        && !(((JavaContentAssistInvocationContext) context).getCompilationUnit() instanceof GroovyCompilationUnit)) {
            return Collections.emptyList();
        }
        final ExtendedJavaContentAssistInvocationContext javaContext = (ExtendedJavaContentAssistInvocationContext) context;
        final List<ICompletionProposal> computeCompletionProposals = super.computeCompletionProposals(context, monitor);
        for (final ICompletionProposal p : computeCompletionProposals) {
            if (p instanceof AbstractJavaCompletionProposal) {
                final int replacementOffset = ((AbstractJavaCompletionProposal) p).getReplacementOffset();
                final int invocationOffset = javaContext.getInvocationOffset();
                ((AbstractJavaCompletionProposal) p).setReplacementOffset(javaContext.getOffset() + (replacementOffset - invocationOffset));
            }
        }
        return computeCompletionProposals;
    }

}
