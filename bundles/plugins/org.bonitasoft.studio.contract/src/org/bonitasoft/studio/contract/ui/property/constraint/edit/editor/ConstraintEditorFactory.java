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
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

/**
 * @author Romain Bioteau
 *
 */
public class ConstraintEditorFactory {

    public int openConstraintEditor(final Shell shell,
            final ContractConstraint constraint,
            final IPropertySourceProvider propertySourceProvider) {
        final ContractConstraintExpressionWizard wizard = createWizard(constraint, propertySourceProvider);
        final WizardDialog wizardDialog = new ConstraintEditorWizardDialog(Display.getDefault().getActiveShell(), wizard);
        return openDialog(wizardDialog);
    }

    protected int openDialog(final WizardDialog wizardDialog) {
        return wizardDialog.open();
    }

    protected ContractConstraintExpressionWizard createWizard(final ContractConstraint constraint, final IPropertySourceProvider propertySourceProvider) {
        return new ContractConstraintExpressionWizard(constraint, propertySourceProvider);
    }

}
