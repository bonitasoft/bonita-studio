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
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

/**
 * @author Romain Bioteau
 */
public class ExpressionTypeLabelProvider extends CellLabelProvider {

    public String getText(final Object element) {
        if (element instanceof IExpressionProvider) {
            return ((IExpressionProvider) element).getTypeLabel();
        } else if (element instanceof String) {
            final IExpressionProvider provider = ExpressionProviderService.getInstance().getExpressionProvider((String) element);
            if (provider == null) {
                if (element.toString().equals("process" + ExpressionConstants.VARIABLE_TYPE)) {
                    return Messages.processVariable;
                } else if (element.toString().equals("step" + ExpressionConstants.VARIABLE_TYPE)) {
                    return Messages.stepVariable;
                } else if (element.toString().equals("form" + ExpressionConstants.VARIABLE_TYPE)) {
                    return Messages.formVariable;
                } else {
                    return element.toString();
                }

            } else {
                return provider.getTypeLabel();
            }
        }
        return null;
    }

    public Image getImage(final Object element) {
        if (element instanceof IExpressionProvider) {
            return ((IExpressionProvider) element).getTypeIcon();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.CellLabelProvider#update(org.eclipse.jface.viewers.ViewerCell)
     */
    @Override
    public void update(final ViewerCell cell) {
        cell.setText(getText(cell.getElement()));
        cell.setImage(getImage(cell.getElement()));
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipText(java.lang.Object)
     */
    @Override
    public String getToolTipText(final Object element) {
        if (element instanceof IExpressionProvider) {
            return Messages.bind(Messages.expressionTypeToolTip, getText(element));
        }
        return super.getToolTipText(element);
    }

    @Override
    public Point getToolTipShift(final Object object) {
        return new Point(5, 5);
    }

    @Override
    public int getToolTipDisplayDelayTime(final Object object) {
        return 50; //msec
    }

    @Override
    public int getToolTipTimeDisplayed(final Object object) {
        return 5000; //msec
    }

}
