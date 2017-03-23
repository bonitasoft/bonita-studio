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
package org.bonitasoft.studio.businessobject.ui.wizard.provider;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.businessobject.ui.DateTypeLabels;
import org.bonitasoft.studio.common.NamingUtils;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 */
public class FieldTypeLabelProvider extends StyledCellLabelProvider implements ILabelProvider {

    @Override
    public String getText(Object element) {
        if (element instanceof FieldType) {
            if (element == FieldType.DATE) {
                return DateTypeLabels.DATE_DEPRECATED;
            }
            if (element == FieldType.LOCALDATE) {
                return DateTypeLabels.DATE_ONLY;
            }
            if (element == FieldType.LOCALDATETIME) {
                return DateTypeLabels.DATE_AND_TIME;
            }
            if (element == FieldType.OFFSETDATETIME) {
                return DateTypeLabels.DATE_TIME_WITH_TIMEZONE;
            }
            return ((FieldType) element).name();
        }
        if (element instanceof SimpleField) {
            return getText(((SimpleField) element).getType());
        }
        if (element instanceof BusinessObject) {
            return NamingUtils.getSimpleName(((BusinessObject) element).getQualifiedName());
        }
        if (element instanceof RelationField && ((RelationField) element).getReference() != null) {
            return NamingUtils.getSimpleName(((RelationField) element).getReference().getQualifiedName());
        } else if (((RelationField) element).getReference() == null) {
            return "";
        }
        return "";
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.StyledCellLabelProvider#update(org.eclipse.jface.viewers.ViewerCell)
     */
    @Override
    public void update(ViewerCell cell) {
        final Object cellElement = cell.getElement();
        final StyledString styledString = new StyledString();
        styledString.append(getText(cellElement), new Styler() {

            @Override
            public void applyStyles(TextStyle textStyle) {
                if (cellElement instanceof SimpleField
                        && ((SimpleField) cellElement).getType() == FieldType.DATE) {
                    textStyle.foreground = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY);
                }
            }
        });
        cell.setText(styledString.getString());
        cell.setStyleRanges(styledString.getStyleRanges());
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(Object element) {
        return null;
    }

}
