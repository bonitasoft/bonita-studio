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
package org.bonitasoft.studio.businessobject.ui.wizard;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Romain Bioteau
 * 
 */
public class ReadOnlyQueryWizardPage extends QueryWizardPage {

    public ReadOnlyQueryWizardPage() {
        super();
    }

    @Override
    protected StyledText createQueryText(Composite composite) {
        return new StyledText(composite, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.READ_ONLY);
    }

    @Override
    protected ComboViewer createReturnTypeComboViewer(Composite composite) {
        ComboViewer returnTypeComboViewer = super.createReturnTypeComboViewer(composite);
        returnTypeComboViewer.getControl().setEnabled(false);
        return returnTypeComboViewer;
    }

    @Override
    protected void bindDeleteParameterButtonEnablement(DataBindingContext ctx, Button deleteButton, TableViewer parametersTableViewer,
            UpdateValueStrategy enableStrategy) {

    }

    @Override
    protected Button createAddButton(Composite buttonsComposite) {
        Button button = super.createAddButton(buttonsComposite);
        button.setEnabled(false);
        return button;
    }

    @Override
    protected TableViewerColumn createNameColumn(DataBindingContext ctx, TableViewer tableViewer) {
        TableViewerColumn nameColumn = super.createNameColumn(ctx, tableViewer);
        nameColumn.setEditingSupport(null);
        return nameColumn;
    }

    @Override
    protected TableViewerColumn createTypeColumn(DataBindingContext ctx, TableViewer tableViewer) {
        TableViewerColumn typeColumn = super.createTypeColumn(ctx, tableViewer);
        typeColumn.setEditingSupport(null);
        return typeColumn;
    }
}
