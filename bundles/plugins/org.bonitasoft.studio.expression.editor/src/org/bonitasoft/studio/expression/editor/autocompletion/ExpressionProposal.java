/**
 * Copyright (C) 2014-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.expression.editor.autocompletion;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.fieldassist.IContentProposal;

/**
 * @author Romain Bioteau
 */
public class ExpressionProposal implements IContentProposal {

    private final String content;
    private final String label;
    private final String description;
    private final Expression expression;

    public ExpressionProposal(final Expression expression, final IExpressionProposalLabelProvider labelProvider) {
        Assert.isNotNull(expression);
        Assert.isNotNull(labelProvider);
        this.expression = expression;
        content = labelProvider.getContent(expression);
        description = labelProvider.getDescription(expression);
        label = description == null || description.isEmpty() ?
                labelProvider.getText(expression)
                : String.format("%s -- %s", labelProvider.getText(expression), description);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.fieldassist.IContentProposal#getContent()
     */
    @Override
    public String getContent() {
        if (expression.getType() == ExpressionConstants.FORM_REFERENCE_TYPE) {
            return expression.getName();
        }
        return content;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.fieldassist.IContentProposal#getCursorPosition()
     */
    @Override
    public int getCursorPosition() {
        return label.length();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.fieldassist.IContentProposal#getLabel()
     */
    @Override
    public String getLabel() {
        return label;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.fieldassist.IContentProposal#getDescription()
     */
    @Override
    public String getDescription() {
        return null;
    }

    public Object getExpression() {
        return expression;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return content;
    }

}
