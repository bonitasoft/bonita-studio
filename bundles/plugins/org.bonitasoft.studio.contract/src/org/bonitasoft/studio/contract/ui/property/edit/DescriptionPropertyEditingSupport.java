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

import org.bonitasoft.studio.contract.core.ContractDefinitionValidator;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertyEditingSupport;


/**
 * @author Romain Bioteau
 *
 */
public class DescriptionPropertyEditingSupport extends PropertyEditingSupport implements ICellEditorValidator {

    private final ContractDefinitionValidator contractDefinitionValidator;
    private ContractInput currentElement;


    public DescriptionPropertyEditingSupport(final ColumnViewer viewer, final IPropertySourceProvider propertySourceProvider,
            final ContractDefinitionValidator contractDefinitionValidator) {
        super(viewer, propertySourceProvider, ProcessPackage.Literals.CONTRACT_INPUT__DESCRIPTION.getName());
        this.contractDefinitionValidator = contractDefinitionValidator;
    }

    @Override
    protected void setValue(final Object element, final Object value) {
        super.setValue(element, value);
        getViewer().update(element, null);
    }

    @Override
    protected void initializeCellEditorValue(final CellEditor cellEditor, final ViewerCell cell) {
        super.initializeCellEditorValue(cellEditor, cell);
        setCurrentElement(cell.getElement());
        cellEditor.setValidator(this);
    }

    @Override
    public String isValid(final Object value) {
        contractDefinitionValidator.validateInputDescription(getCurrentElement(), (String) value);
        return null;
    }

    public ContractInput getCurrentElement() {
        return currentElement;
    }

    public void setCurrentElement(final Object currentElement) {
        Assert.isLegal(currentElement instanceof ContractInput);
        this.currentElement = (ContractInput) currentElement;
    }

}
