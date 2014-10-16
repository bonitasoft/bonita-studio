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
package org.bonitasoft.studio.contract.ui.property.constraint.edit;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.contract.core.validation.ContractDefinitionValidator;
import org.bonitasoft.studio.contract.ui.property.constraint.edit.editor.ContractConstraintExpressionDialogCellEditor;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyEditingSupport;

/**
 * @author Romain Bioteau
 *
 */
public class ConstraintExpressionPropertyEditingSupport extends PropertyEditingSupport {


    private final ContractDefinitionValidator contractDefinitionValidator;

    public ConstraintExpressionPropertyEditingSupport(final ColumnViewer viewer,
            final AdapterFactoryContentProvider propertySourceProvider,
            final ContractDefinitionValidator contractDefinitionValidator) {
        super(viewer, propertySourceProvider, ProcessPackage.Literals.CONTRACT_CONSTRAINT__EXPRESSION.getName());
        this.contractDefinitionValidator = contractDefinitionValidator;
    }

    @Override
    protected void setValue(final Object element, final Object value) {
        super.setValue(element, value);
        final Contract contract = ModelHelper.getFirstContainerOfType((EObject) element, Contract.class);
        contractDefinitionValidator.validate(contract);
        getViewer().update(element, null);
    }

    @Override
    protected CellEditor getCellEditor(final Object object) {
        return new ContractConstraintExpressionDialogCellEditor((Composite) getViewer().getControl(), (ContractConstraint) object, propertySourceProvider);
    }

    @Override
    protected boolean canEdit(final Object object) {
        return object instanceof ContractConstraint;
    }


}
