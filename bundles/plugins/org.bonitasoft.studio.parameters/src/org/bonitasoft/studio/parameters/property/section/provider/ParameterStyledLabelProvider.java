/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.parameters.property.section.provider;

import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

/**
 * @author aurelie Zara
 *
 */
public class ParameterStyledLabelProvider extends StyledCellLabelProvider implements ILabelProvider {

    private ParameterTypeLabelProvider typeProvider ;

    public ParameterStyledLabelProvider() {
        super();
        typeProvider = new ParameterTypeLabelProvider();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipText(java.lang.Object)
     */
    @Override
    public String getToolTipText(Object element) {
        Parameter p = (Parameter)element;
        return p.getDescription();
    }

    @Override
    public Point getToolTipShift(Object object) {
        return new Point(5, 5);

    }

    @Override
    public int getToolTipDisplayDelayTime(Object object) {
        return 100;
    }


    @Override
    public int getToolTipTimeDisplayed(Object object) {
        return 5000;
    }


    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(Object element) {

        return Pics.getImage(PicsConstants.parameter);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.CellLabelProvider#useNativeToolTip(java.lang.Object)
     */
    @Override
    public boolean useNativeToolTip(Object object) {
        return true;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object element) {
        return ((Parameter) element).getName();
    }

    @Override
    public void update(ViewerCell cell) {
        if (cell.getElement() instanceof Parameter) {
            Parameter p = (Parameter) cell.getElement();
            StyledString styledString = new StyledString();

            String decoration = " -- " +typeProvider.getText(p);
            styledString.append(p.getName(), null);

            styledString.append(decoration, StyledString.DECORATIONS_STYLER);
            cell.setText(styledString.getString());
            cell.setImage(getImage(p)) ;
            cell.setStyleRanges(styledString.getStyleRanges());
        }
    }



}
