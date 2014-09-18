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

import org.bonitasoft.studio.contract.ui.property.edit.proposal.InputMappingProposal;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputMapping;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyEditingSupport;

/**
 * @author Romain Bioteau
 *
 */
public class InputMappingPropertyEditingSupport extends PropertyEditingSupport {

    public InputMappingPropertyEditingSupport(final AdapterFactoryContentProvider propertySourceProvider, final TableViewer viewer) {
        super(viewer, propertySourceProvider, "name");
    }

    @Override
    protected Object getValue(final Object element) {
        if (element instanceof ContractInput) {
            return new InputMappingProposal(((ContractInput) element).getMapping()).getContent();
        }
        return null;
    }

    @Override
    protected void setValue(final Object element, final Object value) {
        if (element instanceof ContractInput) {
            final ContractInputMapping mapping = ((ContractInput) element).getMapping();
            final IPropertySource propertySource = propertySourceProvider.getPropertySource(mapping);
            if (value instanceof ContractInputMapping) {
                propertySource.setPropertyValue("data", ((ContractInputMapping) value).getData());
                propertySource.setPropertyValue("setterName", ((ContractInputMapping) value).getSetterName());
                propertySource.setPropertyValue("setterParamType", ((ContractInputMapping) value).getSetterParamType());
            }
        }
        getViewer().update(element, null);
    }

    @Override
    protected CellEditor getCellEditor(final Object element) {
        if (element instanceof ContractInput) {
            final DialogCellEditor cellEditor = new DialogCellEditor((Composite) getViewer().getControl()) {

                @Override
                protected ContractInputMapping openDialogBox(final Control parent) {
                    MessageDialog.openWarning(Display.getDefault().getActiveShell(), "Not Implemented", "Dialog not implemented");
                    return null;
                }
            };
            return cellEditor;
        }
        return null;
    }

}
