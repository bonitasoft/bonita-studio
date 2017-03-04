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
package org.bonitasoft.studio.contract.ui.property.input.labelProvider;

import org.bonitasoft.studio.businessobject.ui.DateTypeLabels;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextStyle;

/**
 * @author Romain Bioteau
 */
public class ContractInputTypeCellLabelProvider extends StyledCellLabelProvider implements ILabelProvider {

    @Override
    public Image getImage(final Object element) {
        return null;
    }

    @Override
    public void update(ViewerCell cell) {
        super.update(cell);
        final ContractInput element = (ContractInput) cell.getElement();
        final String text = getText(element);
        final StyledString styledString = new StyledString(text, new StyledString.Styler() {

            @Override
            public void applyStyles(TextStyle textStyle) {
                textStyle.strikeout = element.getType() == ContractInputType.DATE;
            }
        });
        cell.setText(styledString.getString());
        cell.setStyleRanges(styledString.getStyleRanges());
    }

    @Override
    public String getText(Object element) {
        final ContractInputType type = ((ContractInput) element).getType();
        switch (type) {
            case LOCALDATE:
                return DateTypeLabels.DATE_ONLY;
            case LOCALDATETIME:
                return DateTypeLabels.DATE_AND_TIME;
            default:
                return type.name();
        }
    }

}
