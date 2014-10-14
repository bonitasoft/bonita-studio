/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.contract.ui.property.constraint;

import org.bonitasoft.studio.contract.core.ContractDefinitionValidator;
import org.bonitasoft.studio.model.process.ContractConstraint;
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
 *
 */
public class ConstraintNameCellLabelProvider extends PropertyColumnLabelProvider {

    private final ContractDefinitionValidator validator;
    private final ColumnViewer viewer;

    public ConstraintNameCellLabelProvider(final ColumnViewer viewer, final IPropertySourceProvider propertySourceProvider) {
        super(propertySourceProvider, ProcessPackage.Literals.CONTRACT_CONSTRAINT__NAME.getName());
        validator = new ContractDefinitionValidator();
        this.viewer = viewer;
    }

    @Override
    public Image getImage(final Object element) {
        final String name = ((ContractConstraint) element).getName();
        final IStatus status = validator.validateConstraintName((ContractConstraint) element, name);
        if (!status.isOK()) {
            return getErrorImage();
        }
        return null;
    }

    @Override
    public String getToolTipText(final Object element) {
        final String name = ((ContractConstraint) element).getName();
        final IStatus status = validator.validateConstraintName((ContractConstraint) element, name);
        if (!viewer.isCellEditorActive() && !status.isOK()) {
            return status.getMessage();
        }
        return null;
    }

    protected Image getErrorImage() {
        return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_ERROR);
    }

}
