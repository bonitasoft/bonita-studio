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
package org.bonitasoft.studio.contract.ui.property.constraint.edit.editor;

import org.bonitasoft.studio.model.process.ContractConstraint;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.views.properties.IPropertySourceProvider;


/**
 * @author Romain Bioteau
 *
 */
public class ContractConstraintExpressionDialogCellEditor extends DialogCellEditor {

    private final ContractConstraint constraint;
    private final IPropertySourceProvider propertySourceProvider;

    public ContractConstraintExpressionDialogCellEditor(final Composite parent, final ContractConstraint constraint,
            final IPropertySourceProvider propertySourceProvider) {
        super(parent);
        this.constraint = constraint;
        this.propertySourceProvider = propertySourceProvider;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.DialogCellEditor#openDialogBox(org.eclipse.swt.widgets.Control)
     */
    @Override
    protected String openDialogBox(final Control parent) {
        final ContractConstraintExpressionWizard wizard = new ContractConstraintExpressionWizard(constraint, propertySourceProvider);
        final WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(), wizard) {

            @Override
            protected Control createHelpControl(final Composite parent) {
                final ToolBar helpControl = (ToolBar) super.createHelpControl(parent);
                final ToolItem item = helpControl.getItem(0);
                final Listener[] selectionListeners = item.getListeners(SWT.Selection);
                for (final Listener l : selectionListeners) {
                    item.removeListener(SWT.Selection, l);
                }
                item.addListener(SWT.Selection, new Listener() {

                    @Override
                    public void handleEvent(final Event event) {
                        helpPressed();
                    }
                });
                return helpControl;
            }


        };
        wizardDialog.setHelpAvailable(true);
        wizardDialog.open();
        return constraint.getExpression();
    }

}
