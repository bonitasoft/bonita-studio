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
package org.bonitasoft.studio.contract.ui.property.constraint;

import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Sets.newHashSet;
import static org.bonitasoft.studio.common.emf.tools.ModelHelper.getAllElementOfTypeIn;

import java.util.List;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.jface.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.contract.ui.property.IViewerController;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import com.google.common.base.Function;
import com.google.common.base.Objects;

/**
 * @author Romain Bioteau
 */
public class ContractConstraintController implements IViewerController {

    private final IObservableValue contractObservableValue;

    public ContractConstraintController(final IObservableValue contractObservableValue) {
        this.contractObservableValue = contractObservableValue;
    }

    @Override
    public ContractConstraint add(final ColumnViewer viewer) {
        final IObservableList constraintsObservable = (IObservableList) viewer.getInput();
        final ContractConstraint defaultConstraint = createDefaultConstraint();
        constraintsObservable.add(defaultConstraint);
        viewer.editElement(defaultConstraint, 0);
        return defaultConstraint;
    }

    private ContractConstraint createDefaultConstraint() {
        final ContractConstraint contractConstraint = ProcessFactory.eINSTANCE.createContractConstraint();
        contractConstraint.setName(defaultConstraintName());
        contractConstraint.setExpression("return true;");
        return contractConstraint;
    }

    private String defaultConstraintName() {
        final Contract contract = (Contract) contractObservableValue.getValue();
        return NamingUtils.generateNewName(
                newHashSet(transform(getAllElementOfTypeIn(contract, ContractConstraint.class), toConstraintName())),
                "constraint", 1);
    }

    private Function<ContractConstraint, String> toConstraintName() {
        return new Function<ContractConstraint, String>() {

            @Override
            public String apply(final ContractConstraint input) {
                return input.getName();
            }
        };
    }

    @Override
    public void remove(final ColumnViewer viewer) {
        final IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
        final IObservableList constraintsObservable = (IObservableList) viewer.getInput();
        final List<?> selectedInput = selection.toList();
        if (openConfirmation(selectedInput)) {
            for (final Object constraint : selectedInput) {
                final ContractConstraint contractConstraint = (ContractConstraint) constraint;
                constraintsObservable.remove(contractConstraint);
            }
        }
    }

    @Override
    public void moveUp(final ColumnViewer viewer) {
        final ContractConstraint selectedConstraint = getSelectedConstraint(viewer);
        final Contract contract = ModelHelper.getFirstContainerOfType(selectedConstraint, Contract.class);
        final IObservableList list = CustomEMFEditObservables.observeList(contract,
                ProcessPackage.Literals.CONTRACT__CONSTRAINTS);
        final int index = list.indexOf(selectedConstraint);
        if (index > 0) {
            list.move(index, index - 1);
            //refresh button enablement
            viewer.setSelection(new StructuredSelection());
            viewer.setSelection(new StructuredSelection(selectedConstraint));

            // Necessary since the MacOS Big Sur update -> Seems that table with StyledCellLabelProvider aren't redraw automatically 
            // TODO Hopefully this could be removed on the futur (current date: 20/11/2020)
            if (isMacos()) {
                viewer.getControl().redraw();
            }
        }
    }

    protected boolean isMacos() {
        return Objects.equal(Platform.getOS(), Platform.OS_MACOSX);
    }

    @Override
    public void moveDown(final ColumnViewer viewer) {
        final ContractConstraint selectedConstraint = getSelectedConstraint(viewer);
        final Contract contract = ModelHelper.getFirstContainerOfType(selectedConstraint, Contract.class);
        final IObservableList list = CustomEMFEditObservables.observeList(contract,
                ProcessPackage.Literals.CONTRACT__CONSTRAINTS);
        final int index = list.indexOf(selectedConstraint);
        if (index < list.size() - 1) {
            list.move(index, index + 1);
            //refresh button enablement
            viewer.setSelection(new StructuredSelection());
            viewer.setSelection(new StructuredSelection(selectedConstraint));

            // Necessary since the MacOS Big Sur update -> Seems that table with StyledCellLabelProvider aren't redraw automatically 
            // TODO Hopefully this could be removed on the futur (current date: 20/11/2020)
            if (isMacos()) {
                viewer.getControl().redraw();
            }
        }
    }

    protected ContractConstraint getSelectedConstraint(final ColumnViewer viewer) {
        final IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
        return (ContractConstraint) selection.getFirstElement();
    }

    protected boolean openConfirmation(final List<?> selectedInput) {
        final StringBuilder message = new StringBuilder(Messages.removeConstraintConfirmationMessage);
        for (final Object constraint : selectedInput) {
            message.append(SWT.CR);
            message.append("- " + ((ContractConstraint) constraint).getName());
        }
        return FileActionDialog.getDisablePopup() ? true : MessageDialog.openConfirm(Display.getDefault().getActiveShell(),
                Messages.removeConstraintConfirmationTitle, message.toString());
    }
}
