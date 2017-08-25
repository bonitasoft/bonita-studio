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
package org.bonitasoft.studio.expression.editor.provider;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.ExpressionProviderService;
import org.bonitasoft.studio.expression.editor.autocompletion.ExpressionProposal;
import org.bonitasoft.studio.expression.editor.autocompletion.IExpressionProposalLabelProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 */
public class ExpressionLabelProvider extends LabelProvider implements IExpressionProposalLabelProvider {

    @Override
    public Image getImage(Object expression) {
        if (expression instanceof ExpressionProposal) {
            expression = ((ExpressionProposal) expression).getExpression();
        }
        if (expression instanceof Expression) {
            final IExpressionProvider provider = ExpressionProviderService.getInstance()
                    .getExpressionProvider(((Expression) expression).getType());
            if (provider != null) {
                return provider.getIcon((Expression) expression);
            }
            if (ExpressionConstants.CONSTANT_TYPE.equals(((Expression) expression).getType())
                    && ((Expression) expression).getContent() != null
                    && !((Expression) expression).getContent().isEmpty()) {
                return Pics.getImage(PicsConstants.constant);
            }
            if (ExpressionConstants.ENGINE_CONSTANT_TYPE.equals(((Expression) expression).getType())) {
                return Pics.getImage(PicsConstants.engineConstant);
            }
        }
        return super.getImage(expression);
    }

    @Override
    public String getText(Object expression) {
        if (expression instanceof ExpressionProposal) {
            expression = ((ExpressionProposal) expression).getExpression();
        }
        if (expression instanceof Expression) {
            final IExpressionProvider provider = ExpressionProviderService.getInstance()
                    .getExpressionProvider(((Expression) expression).getType());
            if (provider != null) {
                return provider.getProposalLabel((Expression) expression);
            }
            if (ExpressionConstants.CONSTANT_TYPE.equals(((Expression) expression).getType())
                    && !((Expression) expression).getContent().isEmpty()) {
                return ((Expression) expression).getName();
            }

        }
        return super.getText(expression);
    }

    @Override
    public String getDescription(final Expression expression) {
        return expression.getReturnType();
    }

    @Override
    public String getContent(final Expression expression) {
        return expression.getContent();
    }

    public StyledString getStyledString(org.bonitasoft.studio.model.expression.Expression expression) {
        final StyledString styledString = new StyledString(expression.getName());
        styledString.append(" -- ");
        styledString.append(new StyledString(expression.getReturnType(), StyledString.DECORATIONS_STYLER));
        return styledString;
    }

}
