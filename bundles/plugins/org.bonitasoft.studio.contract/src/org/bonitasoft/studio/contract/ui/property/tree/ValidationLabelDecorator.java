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
package org.bonitasoft.studio.contract.ui.property.tree;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.contract.core.ContractDefinitionValidator;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;


/**
 * @author Romain Bioteau
 *
 */
public class ValidationLabelDecorator implements ILabelDecorator {

    private final ContractDefinitionValidator validator;

    public ValidationLabelDecorator() {
        validator = new ContractDefinitionValidator();
    }

    @Override
    public void addListener(final ILabelProviderListener listener) {
        //Listener not supported
    }

    @Override
    public void dispose() {
        //Nothing to dispose
    }

    @Override
    public boolean isLabelProperty(final Object element, final String property) {
        return true;
    }

    @Override
    public void removeListener(final ILabelProviderListener listener) {
        //Listener not supported
    }

    @Override
    public Image decorateImage(final Image image, final Object element) {
        final ImageDescriptor decorator = getDecoratorImageDescriptor(element);
        if(decorator != null){
            return new DecorationOverlayIcon(image, decorator, IDecoration.TOP_RIGHT).createImage();
        }
        return null;
    }

    protected ImageDescriptor getDecoratorImageDescriptor(final Object element) {
        final ContractInput contractInput = (ContractInput) element;
        if (contractInput.getName() == null) {
            return null;
        }
        final String name = contractInput.getName();
        final IStatus status = validator.validateInputName((ContractInput) element, name);
        final IStatus duplicateStatus = validator.validateDuplicatedInputs(ModelHelper.getFirstContainerOfType((EObject) element, Contract.class));
        if (!status.isOK()) {
            final FieldDecoration decoration = getErrorDecorator();
            decoration.setDescription(status.getMessage());
            return ImageDescriptor.createFromImage(decoration.getImage());
        } else if (!duplicateStatus.isOK()) {
            for (final IStatus c : duplicateStatus.getChildren()) {
                if (c.getMessage().equals(name)) {
                    final FieldDecoration decoration = getErrorDecorator();
                    decoration.setDescription(Messages.duplicatedInputNames + status.getMessage());
                    return ImageDescriptor.createFromImage(decoration.getImage());
                }
            }
        }
        return null;
    }

    protected FieldDecoration getErrorDecorator() {
        return FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);
    }

    @Override
    public String decorateText(final String text, final Object element) {
        final String name = ((ContractInput) element).getName();
        final IStatus status = validator.validateInputName((ContractInput) element, name);
        if (!status.isOK()) {
            return status.getMessage();
        }
        return null;
    }
}
