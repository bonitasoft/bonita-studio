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

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Sets.newHashSet;
import static org.bonitasoft.studio.common.emf.tools.ModelHelper.getAllElementOfTypeIn;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.jface.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.contract.core.refactoring.RefactorContractInputOperation;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.contract.ui.property.IViewerController;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractContainer;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.bonitasoft.studio.refactoring.core.script.GroovyScriptRefactoringOperationFactory;
import org.bonitasoft.studio.refactoring.core.script.IScriptRefactoringOperationFactory;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.progress.IProgressService;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

/**
 * @author Romain Bioteau
 */
public class ContractInputController implements IViewerController {

    private static final int NAME_COLUMN_INDEX = 0;
    private final IProgressService progressService;

    class EditNameRunnable implements Runnable {

        private final ColumnViewer viewer;
        private final ContractInput input;

        public EditNameRunnable(final ColumnViewer viewer, final ContractInput input) {
            this.viewer = viewer;
            this.input = input;
        }

        /*
         * (non-Javadoc)
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {
            if (viewer != null && viewer.getControl() != null && !viewer.getControl().isDisposed()) {
                viewer.setSelection(new StructuredSelection(input), false);
                final Widget item = viewer.testFindItem(input);
            	if(item != null && !item.isDisposed()){
                    viewer.reveal(input);
            		viewer.editElement(input, NAME_COLUMN_INDEX);
            	}
            }
        }

    }

    public ContractInputController(final IProgressService progressService) {
        this.progressService = progressService;
    }

    @Override
    public ContractInput add(final ColumnViewer viewer) {
        final IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
        final IObservableValue contractObservable = (IObservableValue) viewer.getInput();
        final ContractInput parentInput = (ContractInput) selection.getFirstElement();
        final Contract contract = (Contract) contractObservable.getValue();
        final ContractInput defaultInput = createDefaultInput(contract);
        final EObject targetContainer = targetContainer(parentInput, contract);
        CustomEMFEditObservables.observeList(targetContainer, inputContainerFeature(targetContainer)).add(defaultInput);
        viewer.getControl().getDisplay().asyncExec(new EditNameRunnable(viewer, defaultInput));
        return defaultInput;
    }

    private EObject targetContainer(final ContractInput parentInput, final Contract contract) {
        return parentInput != null ? parentInput.eContainer() : contract;
    }

    public ContractInput addChildInput(final ColumnViewer viewer) {
        final IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
        if (selection.isEmpty()) {
            return null;
        }

        final ContractInput parentInput = (ContractInput) selection.getFirstElement();
        final ContractInput defaultInput = createDefaultInput(ModelHelper.getFirstContainerOfType(parentInput, Contract.class));
        CustomEMFEditObservables.observeList(parentInput, ProcessPackage.Literals.CONTRACT_INPUT__INPUTS).add(defaultInput);
        viewer.getControl().getDisplay().asyncExec(new EditNameRunnable(viewer, defaultInput));
        return defaultInput;
    }

    private ContractInput createDefaultInput(final Contract contract) {
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setName(defaultContractInputName(contract));
        contractInput.setType(ContractInputType.TEXT);
        contractInput.setMapping(ProcessFactory.eINSTANCE.createContractInputMapping());
        return contractInput;
    }

    private String defaultContractInputName(final Contract contract) {
        return NamingUtils.generateNewName(
                newHashSet(transform(getAllElementOfTypeIn(contract, ContractInput.class), toInputName())), "input", 1);
    }

    private Function<ContractInput, String> toInputName() {
        return new Function<ContractInput, String>() {

            @Override
            public String apply(final ContractInput input) {
                return input.getName();
            }
        };
    }

    @Override
    public void remove(final ColumnViewer viewer) {
        final IObservableValue contractObservable = (IObservableValue) viewer.getInput();
        final IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
        final List<?> selectedInput = selection.toList();
        Contract contract = (Contract) contractObservable.getValue();
        if (openConfirmation(selectedInput)) {
            final RefactorContractInputOperation refactorOperation = newRefactorOperation(contract);
            final TransactionalEditingDomain editingDomain = editingDomain(contract);
            refactorOperation.setEditingDomain(editingDomain);
            refactorOperation.setAskConfirmation(shouldAskConfirmation());
            final CompoundCommand compoundCommand = refactorOperation.getCompoundCommand();
            for (final Object input : selectedInput) {
                final ContractInput contractInput = (ContractInput) input;
                contract = ModelHelper.getFirstContainerOfType(contractInput, Contract.class);
                //Parent input has been removed in current selection
                if (contract == null) {
                    continue;
                }
                refactorOperation.addItemToRefactor(null, contractInput);
                compoundCommand.append(DeleteCommand.create(editingDomain, contractInput));
                final Collection<ContractConstraint> constraintsReferencingInput = constraintsReferencingSingleInput(contract, contractInput);
                if (!constraintsReferencingInput.isEmpty()) {
                    compoundCommand.append(DeleteCommand.create(editingDomain, constraintsReferencingInput));
                }
            }
            try {
                if (refactorOperation.canExecute()) {
                    progressService.run(true, true, refactorOperation);
                }
            } catch (final InvocationTargetException | InterruptedException e) {
                BonitaStudioLog.error("Failed to remove contract input.", e);
                openErrorDialog(e);
            }
        }
    }

    protected boolean shouldAskConfirmation() {
        return true;
    }

    private Collection<ContractConstraint> constraintsReferencingSingleInput(final Contract contract, final ContractInput contractInput) {
        return newHashSet(filter(contract.getConstraints(), havingInputReference(contractInput.getName())));
    }

    private Predicate<ContractConstraint> havingInputReference(final String inputName) {
        return new Predicate<ContractConstraint>() {

            @Override
            public boolean apply(final ContractConstraint constraint) {
                return constraint.getInputNames().contains(inputName) && constraint.getInputNames().size() == 1;
            }
        };
    }

    protected TransactionalEditingDomain editingDomain(final Contract contract) {
        return TransactionUtil.getEditingDomain(contract);
    }

    protected RefactorContractInputOperation newRefactorOperation(final Contract contract) {
        return new RefactorContractInputOperation(ModelHelper.getFirstContainerOfType(
                contract, ContractContainer.class), scriptRefactoringOperationFactory(), RefactoringOperationType.REMOVE);
    }

    protected IScriptRefactoringOperationFactory scriptRefactoringOperationFactory() {
        return new GroovyScriptRefactoringOperationFactory();
    }

    private EReference inputContainerFeature(final EObject eContainer) {
        return eContainer instanceof ContractInput ? ProcessPackage.Literals.CONTRACT_INPUT__INPUTS : ProcessPackage.Literals.CONTRACT__INPUTS;
    }

    protected void openErrorDialog(final Throwable e) {
        if (!FileActionDialog.getDisablePopup()) {
            new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.removeInputErrorTitle, Messages.removeInputErrorMsg, e).open();
        }
    }

    protected boolean openConfirmation(final List<?> selectedInput) {
        final StringBuilder message = new StringBuilder(Messages.removeInputConfirmationMessagePart1);
        for (final Object input : selectedInput) {
            message.append(SWT.CR);
            message.append("- " + ((ContractInput) input).getName());
            message.append(SWT.CR);
            message.append(Messages.removeInputConfirmationMessagesPart2);
        }
        return FileActionDialog.getDisablePopup() ? true : MessageDialog.openConfirm(Display.getDefault().getActiveShell(),
                Messages.removeInputConfirmationTitle, message.toString());
    }

    @Override
    public void moveUp(final ColumnViewer viewer) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public void moveDown(final ColumnViewer viewer) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

}
