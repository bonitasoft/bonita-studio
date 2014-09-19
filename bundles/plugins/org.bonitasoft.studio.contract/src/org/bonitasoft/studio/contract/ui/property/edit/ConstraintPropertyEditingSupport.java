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
package org.bonitasoft.studio.contract.ui.property.edit;

import org.bonitasoft.studio.contract.core.ContractConstraintUtil;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 *
 */
public class ConstraintPropertyEditingSupport extends EditingSupport {

    private final ConstraintColumnLabelProvider constraintColumnLabelProvider;

    public ConstraintPropertyEditingSupport(final TableViewer viewer) {
        super(viewer);
        constraintColumnLabelProvider = new ConstraintColumnLabelProvider();
    }

    @Override
    protected Object getValue(final Object element) {
        return constraintColumnLabelProvider.getText(element);
    }

    @Override
    protected void setValue(final Object element, final Object value) {

    }

    @Override
    protected CellEditor getCellEditor(final Object element) {
        if (element instanceof ContractInput) {
            final ContractInput contractInput = (ContractInput) element;
            final Contract contract = (Contract) contractInput.eContainer();
            final DialogCellEditor cellEditor = new DialogCellEditor((Composite) getViewer().getControl()) {

                @Override
                protected Button createButton(final Composite parent) {
                    final Button editButton = super.createButton(parent);
                    if (ContractConstraintUtil.getConstraintsForInput(contract, contractInput).isEmpty()) {
                        editButton.setText(Messages.addConstraint);
                    } else {
                        editButton.setText(Messages.editConstraint);
                    }
                    return editButton;
                }

                @Override
                protected Object openDialogBox(final Control parent) {
                    MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Not Implemented", "Dialog not implemented");
                    return null;
                }
            };
            return cellEditor;
        }
        return null;
    }

    @Override
    protected boolean canEdit(final Object element) {
        return element instanceof ContractInput;
    }

}
