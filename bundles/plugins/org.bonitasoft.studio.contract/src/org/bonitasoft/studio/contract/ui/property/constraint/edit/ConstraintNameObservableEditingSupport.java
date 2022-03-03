/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.contract.ui.property.constraint.edit;

import static com.google.common.base.Predicates.equalTo;
import static com.google.common.collect.Iterables.removeIf;
import static org.bonitasoft.studio.common.emf.tools.ModelHelper.getAllElementOfTypeIn;
import static org.bonitasoft.studio.common.emf.tools.ModelHelper.getFirstContainerOfType;
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.convertUpdateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.mandatoryValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.maxLengthValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.multiValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.uniqueValidator;

import java.util.List;

import org.bonitasoft.studio.common.jface.databinding.CustomTextEMFObservableValueEditingSupport;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.ui.forms.IMessageManager;

public class ConstraintNameObservableEditingSupport extends CustomTextEMFObservableValueEditingSupport {

    private static final int CONSTRAINT_NAME_MAX_LENGTH = 50;

    public ConstraintNameObservableEditingSupport(final ColumnViewer viewer,
            final IMessageManager messageManager,
            final DataBindingContext dbc) {
        super(viewer, ProcessPackage.Literals.CONTRACT_CONSTRAINT__NAME, messageManager, dbc);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.jface.databinding.CustomTextEMFObservableValueEditingSupport#taregtToModelConvertStrategy()
     */
    @Override
    protected UpdateValueStrategy targetToModelConvertStrategy(final EObject element) {
        return convertUpdateValueStrategy()
                .withValidator(
                        multiValidator()
                                .addValidator(mandatoryValidator(Messages.name))
                                .addValidator(maxLengthValidator(Messages.name, CONSTRAINT_NAME_MAX_LENGTH))
                                .addValidator(uniqueValidator().in(allContractConstraint(element)).onProperty("name"))).create();
    }

    private Iterable<ContractConstraint> allContractConstraint(final EObject element) {
        final List<ContractConstraint> result = getAllElementOfTypeIn(getFirstContainerOfType(element, Contract.class), ContractConstraint.class);
        removeIf(result, equalTo(element));
        return result;
    }

}
