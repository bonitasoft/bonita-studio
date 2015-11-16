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
package org.bonitasoft.studio.contract.ui.property.input.edit;

import org.bonitasoft.studio.contract.ui.property.input.ContractInputController;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertyEditingSupport;

/**
 * @author Romain Bioteau
 */
public class ContractInputTypeEditingSupport extends PropertyEditingSupport implements ICellEditorListener {

    private final ContractInputController controller;
    private ContractInput contractInput;

    public ContractInputTypeEditingSupport(final ColumnViewer viewer, final IPropertySourceProvider propertySourceProvider,
            final ContractInputController controller) {
        super(viewer, propertySourceProvider, ProcessPackage.Literals.CONTRACT_INPUT__TYPE.getName());
        this.controller = controller;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.views.properties.PropertyEditingSupport#canEdit(java.lang.Object)
     */
    @Override
    protected boolean canEdit(Object object) {
        if (getViewer().isCellEditorActive()) {
            return false;
        }
        return super.canEdit(object);
    }

    @Override
    protected void initializeCellEditorValue(final CellEditor cellEditor, final ViewerCell cell) {
        super.initializeCellEditorValue(cellEditor, cell);
        cellEditor.addListener(this);
    }

    @Override
    protected void setValue(final Object object, final Object value) {
        setContractInput((ContractInput) object);
        super.setValue(object, value);
        getViewer().refresh(true);
    }

    @Override
    public void applyEditorValue() {
        final ContractInput input = getContractInput();
        if (input != null && input.getType() == ContractInputType.COMPLEX && input.getInputs().isEmpty()) {
            controller.addChildInput(getViewer());
        }
    }

    @Override
    public void cancelEditor() {
        //Nothing to do
    }

    @Override
    public void editorValueChanged(final boolean oldValidState, final boolean newValidState) {
        //Nothing to do
    }

    public ContractInput getContractInput() {
        return contractInput;
    }

    public void setContractInput(final ContractInput contractInput) {
        this.contractInput = contractInput;
    }

}
