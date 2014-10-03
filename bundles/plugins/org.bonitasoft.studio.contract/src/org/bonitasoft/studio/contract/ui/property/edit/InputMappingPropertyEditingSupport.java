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
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * @author Romain Bioteau
 *
 */
public class InputMappingPropertyEditingSupport extends EditingSupport {

    private final AdapterFactoryContentProvider propertySourceProvider;

    public InputMappingPropertyEditingSupport(final AdapterFactoryContentProvider propertySourceProvider, final TableViewer viewer) {
        super(viewer);
        this.propertySourceProvider = propertySourceProvider;
    }

    @Override
    protected Object getValue(final Object element) {
        if (element instanceof ContractInput) {
            final ContractInputMapping mapping = ((ContractInput) element).getMapping();
            if (mapping != null) {
                return new InputMappingProposal(mapping).getContent();
            }
        }
        return null;
    }

    @Override
    protected void setValue(final Object element, final Object value) {
        if (element instanceof ContractInput) {
            final ContractInputMapping mapping = ((ContractInput) element).getMapping();
            if (value instanceof ContractInputMapping) {
                final IPropertySource propertySource = propertySourceProvider.getPropertySource(mapping);
                propertySource.setPropertyValue(ProcessPackage.Literals.CONTRACT_INPUT_MAPPING__DATA.getName(),
                        ((ContractInputMapping) value).getData());
                propertySource.setPropertyValue(ProcessPackage.Literals.CONTRACT_INPUT_MAPPING__SETTER_NAME.getName(),
                        ((ContractInputMapping) value).getSetterName());
                propertySource.setPropertyValue(ProcessPackage.Literals.CONTRACT_INPUT_MAPPING__SETTER_PARAM_TYPE.getName(),
                        ((ContractInputMapping) value).getSetterParamType());
            }
            getViewer().update(element, null);
        }
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

    @Override
    protected boolean canEdit(final Object element) {
        return element instanceof ContractInput;
    }

}
