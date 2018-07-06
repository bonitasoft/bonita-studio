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
package org.bonitasoft.studio.expression.editor.pattern.contentAssist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.provider.ExpressionComparator;
import org.bonitasoft.studio.expression.editor.provider.ExpressionLabelProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.jdt.internal.ui.text.java.JavaCompletionProposal;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

public class ExpressionContentAssistProcessor implements IContentAssistProcessor {

    private static final String DEL_PREFIX = "${";
    private static final String DEL_SUFFIX = "}";
    private Set<Expression> expressions;
    private final ExpressionLabelProvider labelProvider;

    public ExpressionContentAssistProcessor() {
        super();
        labelProvider = new ExpressionLabelProvider();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeCompletionProposals(org.eclipse.jface.text.ITextViewer, int)
     */
    @Override
    public ICompletionProposal[] computeCompletionProposals(final ITextViewer textViewer, final int documentOffset) {
        if (expressions == null) {
            return new ICompletionProposal[0];
        }
        ICompletionProposal[] proposals = null;

        proposals = buildProposals(expressions, documentOffset, textViewer);
        return proposals;

    }

    private ICompletionProposal[] buildProposals(final Set<Expression> expressions, final int offset,
            final ITextViewer textViewer) {
        final List<ICompletionProposal> proposals = new ArrayList<>();
        final List<Expression> sortedExpressions = new ArrayList<>(expressions);
        Collections.sort(sortedExpressions, new ExpressionComparator());
        final String content = textViewer.getDocument().get();
        boolean showAllProposals = false;
        if (offset == 0 || !Character.isLetterOrDigit(content.charAt(offset - 1))) {
            showAllProposals = true;
        }
        final StringBuilder previousString = new StringBuilder();
        if (!showAllProposals) {
            int index = offset - 1;
            while (index > 0 && Character.isLetterOrDigit(content.charAt(index - 1))) {
                index--;
            }
            for (int i = index; i < offset; i++) {
                previousString.append(content.charAt(i));
            }
        }
        for (final Expression expression : sortedExpressions) {
            if (isSupportedType(expression.getType())) {
                if (!showAllProposals && expression.getName().startsWith(previousString.toString())) {
                    final String replacementString = addDelimiters(expression);
                    proposals.add(new JavaCompletionProposal(replacementString, offset - previousString.length(),
                            replacementString.length(), labelProvider.getImage(expression),
                            labelProvider.getStyledString(expression), 10000));
                } else if (showAllProposals) {
                    final String replacementString = addDelimiters(expression);
                    proposals.add(new JavaCompletionProposal(replacementString, offset, replacementString.length(),
                            labelProvider.getImage(expression), labelProvider.getStyledString(expression), 10000));
                }
            }
        }
        return proposals.toArray(new ICompletionProposal[proposals.size()]);
    }

    protected String addDelimiters(final Expression expression) {
        return DEL_PREFIX + expression.getName() + DEL_SUFFIX;
    }

    private boolean isSupportedType(final String type) {
        return ExpressionConstants.VARIABLE_TYPE.equals(type) || ExpressionConstants.PARAMETER_TYPE.equals(type)
                || ExpressionConstants.CONTRACT_INPUT_TYPE.equals(type)
                || ExpressionConstants.ENGINE_CONSTANT_TYPE.equals(type);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeContextInformation(org.eclipse.jface.text.ITextViewer, int)
     */
    @Override
    public IContextInformation[] computeContextInformation(final ITextViewer arg0, final int arg1) {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getCompletionProposalAutoActivationCharacters()
     */
    @Override
    public char[] getCompletionProposalAutoActivationCharacters() {
        return null;
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
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getContextInformationValidator()
     */
    @Override
    public IContextInformationValidator getContextInformationValidator() {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getErrorMessage()
     */
    @Override
    public String getErrorMessage() {
        return null;
    }

    public void setExpressions(final Set<Expression> expressions) {
        this.expressions = expressions;
    }

}
