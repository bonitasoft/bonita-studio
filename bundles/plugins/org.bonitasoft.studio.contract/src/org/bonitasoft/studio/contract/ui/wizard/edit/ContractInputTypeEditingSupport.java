/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.contract.ui.wizard.edit;

import org.bonitasoft.studio.contract.core.mapping.SimpleFieldToContractInputMapping;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.swt.widgets.Composite;


public class ContractInputTypeEditingSupport extends EditingSupport {

    private static final ContractInputType[] SELECTABLE_TYPES = new ContractInputType[] { ContractInputType.LONG, ContractInputType.TEXT };
    private final Contract contract;

    public ContractInputTypeEditingSupport(ColumnViewer viewer, Contract contract) {
        super(viewer);
        this.contract = contract;
    }

    @Override
    protected void setValue(Object element, Object value) {
        final SimpleFieldToContractInputMapping mapping = (SimpleFieldToContractInputMapping) element;
        mapping.setContractInputType((ContractInputType) value);
        getViewer().refresh(mapping, true);
    }

    @Override
    protected Object getValue(Object element) {
        final SimpleFieldToContractInputMapping mapping = (SimpleFieldToContractInputMapping) element;
        return mapping.getContractInputType();
    }

    @Override
    protected CellEditor getCellEditor(Object element) {
        final ComboBoxViewerCellEditor comboBoxViewerCellEditor = new ComboBoxViewerCellEditor((Composite) getViewer().getControl());
        comboBoxViewerCellEditor.setContentProvider(ArrayContentProvider.getInstance());
        comboBoxViewerCellEditor.setInput(SELECTABLE_TYPES);
        return comboBoxViewerCellEditor;
    }

    @Override
    protected boolean canEdit(Object element) {
        if (!(contract.eContainer() instanceof Task) && element instanceof SimpleFieldToContractInputMapping) {
            final SimpleFieldToContractInputMapping mapping = (SimpleFieldToContractInputMapping) element;
            return Long.class.getName().equals(mapping.getFieldType());
        }
        return false;
    }
}
