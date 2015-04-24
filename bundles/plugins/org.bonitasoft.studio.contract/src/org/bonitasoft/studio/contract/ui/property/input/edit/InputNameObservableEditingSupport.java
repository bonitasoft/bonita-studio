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

import static com.google.common.base.Predicates.equalTo;
import static com.google.common.collect.Iterables.removeIf;
import static org.bonitasoft.studio.common.emf.tools.ModelHelper.getAllElementOfTypeIn;
import static org.bonitasoft.studio.common.emf.tools.ModelHelper.getFirstContainerOfType;
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.convertUpdateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.groovyReferenceValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.mandatoryValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.maxLengthValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.multiValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.uniqueValidator;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.jface.databinding.CustomTextEMFObservableValueEditingSupport;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.contract.core.refactoring.RefactorContractInputOperation;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractContainer;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.emf.databinding.edit.EditingDomainEObjectObservableValue;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.IMessageManager;
import org.eclipse.ui.progress.IProgressService;

public class InputNameObservableEditingSupport extends CustomTextEMFObservableValueEditingSupport {

    private static final int INPUT_NAME_MAX_LENGTH = 50;
    private final IProgressService progressService;

    public InputNameObservableEditingSupport(final ColumnViewer viewer,
            final IMessageManager messageManager,
            final DataBindingContext dbc,
            final IProgressService progressService) {
        super(viewer, ProcessPackage.Literals.CONTRACT_INPUT__NAME, messageManager, dbc);
        this.progressService = progressService;
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
                                .addValidator(uniqueValidator().in(allContractInput(element)).onProperty("name"))).create();
    }

    private Iterable<ContractInput> allContractInput(final EObject element) {
        final List<ContractInput> result = getAllElementOfTypeIn(getFirstContainerOfType(element, Contract.class), ContractInput.class);
        removeIf(result, equalTo(element));
        return result;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.bonitasoft.studio.common.jface.databinding.CustomTextEMFObservableValueEditingSupport#modelValueChanged(org.eclipse.core.databinding.observable.value
     * .ValueChangeEvent)
     */
    @Override
    protected void modelValueChanged(final ValueChangeEvent event) {
        if (shouldRefactorInput(event)) {
            final EditingDomainEObjectObservableValue observable = (EditingDomainEObjectObservableValue) event.getObservable();
            refactorInput((String) event.diff.getOldValue(), (String) event.diff.getNewValue(), (ContractInput) observable.getObserved());
        }
    }

    private void refactorInput(final String oldName, final String newName, final ContractInput input) {
        final RefactorContractInputOperation refactorContractInputOperation = newRefactorOperation(oldName, input);
        refactorContractInputOperation.setEditingDomain(TransactionUtil.getEditingDomain(input));
        refactorContractInputOperation.setAskConfirmation(true);
        final ContractInput oldItem = EcoreUtil.copy(input);
        oldItem.setName(oldName);
        refactorContractInputOperation.addItemToRefactor(input, oldItem);
        Realm.getDefault().asyncExec(new Runnable() {

            @Override
            public void run() {
                try {
                    progressService.busyCursorWhile(refactorContractInputOperation);
                } catch (InvocationTargetException | InterruptedException e) {
                    BonitaStudioLog.error(String.format("Failed to refactor contract input %s into %s", oldName, newName), e);
                    openErrorDialog(oldName, newName, e);
                }
            }
        });
    }

    private boolean shouldRefactorInput(final ValueChangeEvent event) {
        return !event.diff.getNewValue().equals(event.diff.getOldValue());
    }

    protected void openErrorDialog(final String oldName, final String newName, final Exception e) {
        new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.refactorFailedTitle, Messages.bind(Messages.refactorFailedMsg, oldName,
                newName), e).open();
    }

    protected RefactorContractInputOperation newRefactorOperation(final String oldName, final ContractInput input) {
        final RefactorContractInputOperation refactorContractInputOperation = new RefactorContractInputOperation(ModelHelper.getFirstContainerOfType(input,
                ContractContainer.class), RefactoringOperationType.UPDATE);
        return refactorContractInputOperation;
    }

}
