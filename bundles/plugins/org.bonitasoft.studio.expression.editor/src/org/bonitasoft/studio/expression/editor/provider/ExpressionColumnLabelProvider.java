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
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ViewerColumn;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 */
public class ExpressionColumnLabelProvider extends ColumnLabelProvider {

    private final int col;
    private Color bgColor;

    public ExpressionColumnLabelProvider(int col) {
        this.col = col;
    }

    @Override
    protected void initialize(ColumnViewer viewer, ViewerColumn column) {
        if (!Platform.getWS().equals(Platform.WS_GTK)) {
            bgColor = new Color(Display.getCurrent(), 232, 232, 232);
        }
    }

    @Override
    public Color getBackground(Object element) {
        final String text = getText(element);
        if (text == null || text.isEmpty()) {
            return bgColor;
        }
        return super.getBackground(element);
    }

    @Override
    public void dispose(ColumnViewer viewer, ViewerColumn column) {
        if (bgColor != null) {
            bgColor.dispose();
        }
        dispose();
    }

    @Override
    public Image getImage(Object expression) {
        if (expression instanceof ExpressionProposal) {
            expression = ((ExpressionProposal) expression).getExpression();
        }
        if (expression instanceof Expression) {
            if (((Expression) expression).getName() == null || ((Expression) expression).getName().isEmpty()) {
                return null;
            }
            final IExpressionProvider provider = ExpressionProviderService.getInstance()
                    .getExpressionProvider(((Expression) expression).getType());
            if (provider != null) {
                return provider.getIcon((Expression) expression);
            }
            if (ExpressionConstants.CONSTANT_TYPE.equals(((Expression) expression).getType())
                    && ((Expression) expression).getContent() != null && !((Expression) expression).getContent().isEmpty()) {
                return Pics.getImage(PicsConstants.constant);
            }

        }

        if (expression instanceof ListExpression) {
            if (!((ListExpression) expression).getExpressions().isEmpty()
                    && ((ListExpression) expression).getExpressions().size() > col) {
                return getImage(((ListExpression) expression).getExpressions().get(col));
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

        if (expression instanceof ListExpression) {
            if (!((ListExpression) expression).getExpressions().isEmpty()
                    && ((ListExpression) expression).getExpressions().size() > col) {
                return getText(((ListExpression) expression).getExpressions().get(col));
            }
        }
        return "";
    }
}
