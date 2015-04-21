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
package org.bonitasoft.studio.contract.ui.property.input;

import org.bonitasoft.studio.contract.core.validation.ContractInputDescriptionValidationRule;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertyColumnLabelProvider;

/**
 * @author Romain Bioteau
 */
public class DescriptionCellLabelProvider extends PropertyColumnLabelProvider {

    private final ColumnViewer viewer;
    private final ContractInputDescriptionValidationRule inputDescriptionValidationRule;

    public DescriptionCellLabelProvider(final ColumnViewer viewer, final IPropertySourceProvider propertySourceProvider) {
        super(propertySourceProvider, ProcessPackage.Literals.CONTRACT_INPUT__DESCRIPTION.getName());
        inputDescriptionValidationRule = new ContractInputDescriptionValidationRule();
        this.viewer = viewer;
    }

    @Override
    public Image getImage(final Object element) {
        final IStatus status = inputDescriptionValidationRule.validate((ContractInput) element);
        if (!status.isOK()) {
            return getErrorImage();
        }
        return null;
    }

    @Override
    public String getToolTipText(final Object element) {
        final String description = ((ContractInput) element).getDescription();
        final IStatus status = inputDescriptionValidationRule.validate((ContractInput) element);
        if (viewer.isCellEditorActive() || status.isOK()) {
            return description;
        } else {
            return status.getMessage();
        }
    }

    protected Image getErrorImage() {
        return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_ERROR);
    }

}
