/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.editor.editor.ui.provider;

import org.bonitasoft.studio.businessobject.editor.editor.ui.styler.DeprecatedTypeStyler;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.editor.model.FieldType;
import org.bonitasoft.studio.businessobject.editor.model.RelationField;
import org.bonitasoft.studio.businessobject.editor.model.SimpleField;
import org.bonitasoft.studio.businessobject.ui.DateTypeLabels;
import org.bonitasoft.studio.businessobject.validator.AttributeReferenceExitenceValidator;
import org.bonitasoft.studio.ui.ColorConstants;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerColumn;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class TypeLabelProvider extends StyledCellLabelProvider implements ILabelProvider {

    private DeprecatedTypeStyler deprecatedStyler = new DeprecatedTypeStyler();
    private AttributeReferenceExitenceValidator validator;
    private Color errorColor;

    public TypeLabelProvider(IObservableValue<BusinessObjectModel> workingCopy) {
        validator = new AttributeReferenceExitenceValidator(workingCopy);
        errorColor = new Color(Display.getDefault(), ColorConstants.ERROR_RGB);
    }

    @Override
    public void initialize(ColumnViewer viewer, ViewerColumn column) {
        super.initialize(viewer, column);
    }

    @Override
    public void update(ViewerCell cell) {
        Object element = cell.getElement();
        StyledString styledString = getStyledString(element);
        cell.setText(styledString.getString());

        IStatus status = validator.validate(element);
        if (status.getSeverity() == IStatus.ERROR) {
            cell.setImage(JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_ERROR));
            cell.setForeground(errorColor);
        } else {
            cell.setForeground(null);
            cell.setImage(null);
        }
        cell.setStyleRanges(styledString.getStyleRanges());
    }

    private StyledString getStyledString(Object element) {
        StyledString styledString = new StyledString();
        if (element instanceof SimpleField) {
            appendTypeLabel(((SimpleField) element).getType(), styledString);
        } else if (element instanceof FieldType) {
            appendTypeLabel((FieldType) element, styledString);
        } else if (element instanceof RelationField && ((RelationField) element).getReference() != null) {
            styledString.append(((RelationField) element).getReference().getSimpleName());
        } else if (element instanceof BusinessObject) {
            styledString.append(((BusinessObject) element).getSimpleName());
        }
        return styledString;
    }

    @Override
    public String getToolTipText(Object element) {
        IStatus status = validator.validate(element);
        if (status.getSeverity() == IStatus.ERROR) {
            return status.getMessage();
        }
        return super.getToolTipText(element);
    }

    private void appendTypeLabel(FieldType type, StyledString styledString) {
        switch (type) {
            case DATE:
                styledString.append(DateTypeLabels.DATE_DEPRECATED, deprecatedStyler);
                break;
            case LOCALDATE:
                styledString.append(DateTypeLabels.DATE_ONLY);
                break;
            case LOCALDATETIME:
                styledString.append(DateTypeLabels.DATE_AND_TIME);
                break;
            case OFFSETDATETIME:
                styledString.append(DateTypeLabels.DATE_TIME_WITH_TIMEZONE);
                break;
            default:
                styledString.append(type.name());
        }
    }

    @Override
    public Image getImage(Object element) {
        return null;
    }

    @Override
    public String getText(Object element) {
        return getStyledString(element).getString();
    }

    @Override
    public void dispose() {
        errorColor.dispose();
        super.dispose();
    }

}
