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
package org.bonitasoft.studio.contract.ui.property.input.edit;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Predicates.equalTo;
import static com.google.common.collect.Iterables.removeIf;
import static com.google.common.collect.Lists.newArrayList;
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.convertUpdateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.groovyReferenceValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.mandatoryValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.maxLengthValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.multiValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.uniqueValidator;

import java.util.List;

import org.bonitasoft.studio.common.jface.ColumnViewerUpdateListener;
import org.bonitasoft.studio.common.jface.databinding.CustomTextEMFObservableValueEditingSupport;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.refactoring.core.emf.EMFEditWithRefactorObservables;
import org.bonitasoft.studio.refactoring.core.emf.IRefactorOperationFactory;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.ui.forms.IMessageManager;
import org.eclipse.ui.progress.IProgressService;

public class InputNameObservableEditingSupport extends CustomTextEMFObservableValueEditingSupport {

    private static final int INPUT_NAME_MAX_LENGTH = 50;
    private final IProgressService progressService;
    private final IRefactorOperationFactory contractInputRefactorOperationFactory;

    public InputNameObservableEditingSupport(final ColumnViewer viewer,
            final IMessageManager messageManager,
            final DataBindingContext dbc,
            final IRefactorOperationFactory contractInputRefactorOperationFactory,
            final IProgressService progressService) {
        super(viewer, ProcessPackage.Literals.CONTRACT_INPUT__NAME, messageManager, dbc);
        this.progressService = progressService;
        this.contractInputRefactorOperationFactory = contractInputRefactorOperationFactory;
    }

    @Override
    protected IObservableValue doCreateElementObservable(final Object element, final ViewerCell cell) {
        checkArgument(element instanceof ContractInput);
        final IObservableValue observableValue = ((ContractInput) element).eContainer() instanceof Contract ? EMFEditWithRefactorObservables
                .observeValueWithRefactor(
                        TransactionUtil.getEditingDomain(element),
                        (EObject) element,
                        ProcessPackage.Literals.CONTRACT_INPUT__NAME,
                        contractInputRefactorOperationFactory,
                        progressService) : EMFEditObservables.observeValue(TransactionUtil.getEditingDomain(element), (EObject) element,
                ProcessPackage.Literals.CONTRACT_INPUT__NAME);
        observableValue.addValueChangeListener(new ColumnViewerUpdateListener(getViewer(), element));
        return observableValue;
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
                                .addValidator(maxLengthValidator(Messages.name, INPUT_NAME_MAX_LENGTH))
                                .addValidator(groovyReferenceValidator(Messages.name).startsWithLowerCase())
                                .addValidator(uniqueValidator().in(siblingContractInput(element)).onProperty("name"))).create();
    }

    private Iterable<ContractInput> siblingContractInput(final EObject element) {
        final EObject inputContainer = element.eContainer();
        if (inputContainer == null) {
            return newArrayList();
        }
        final List<ContractInput> result = inputContainer instanceof ContractInput ? newArrayList(((ContractInput) element).getInputs())
                : newArrayList(((Contract) inputContainer).getInputs());
        removeIf(result, equalTo(element));
        return result;
    }

}
