/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.actors.ui.editingsupport;

import org.bonitasoft.studio.actors.model.organization.CustomUserInfoValue;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.User;
import org.bonitasoft.studio.actors.validator.CustomerUserInformationDefinitionNameValidator;
import org.bonitasoft.studio.common.jface.ColumnViewerUpdateListener;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.DisposeEvent;
import org.eclipse.core.databinding.observable.IDisposeListener;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ObservableValueEditingSupport;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * @author Florine Boudin
 *
 */
public class CustomUserInformationDefinitionNameEditingSupport extends ObservableValueEditingSupport {

    private static final int CUSTOM_USER_DEFINITION_NAME_SIZE = 75;
    private final DataBindingContext dbc;
    private Organization organization;
    final ColumnViewer viewer;

    /**
     * @param viewer
     * @param dbc
     */
    public CustomUserInformationDefinitionNameEditingSupport(final ColumnViewer viewer, final DataBindingContext dbc) {
        super(viewer, dbc);
        this.viewer = viewer;
        this.dbc = dbc;
    }



    public void setOrganization(final Organization organization) {
        this.organization = organization;
    }



    @Override
    protected IObservableValue doCreateCellEditorObservable(final CellEditor cellEditor) {
        return SWTObservables.observeText(cellEditor.getControl(), SWT.Modify);
    }

    @Override
    protected IObservableValue doCreateElementObservable(final Object element, final ViewerCell viewerCell) {
        final IObservableValue observeValue = PojoObservables.observeValue(element, "name");
        observeValue.addValueChangeListener(new ColumnViewerUpdateListener(getViewer(), element));
        observeValue.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                final String oldInformationName = (String) event.diff.getOldValue();
                final String newInformationName = (String) event.diff.getNewValue();
                for(final User user : organization.getUsers().getUser()){
                    updateCustomUserInformationName(user, oldInformationName, newInformationName);
                }
            }

        });

        //sort the table after a modification
        observeValue.addDisposeListener(new IDisposeListener() {

            @Override
            public void handleDispose(final DisposeEvent arg0) {
                viewer.refresh();

            }
        });
        return observeValue;
    }

    @Override
    protected CellEditor getCellEditor(final Object element) {
        final TextCellEditor textCellEditor = new TextCellEditor((Composite) getViewer().getControl());
        final Text textControl = (Text) textCellEditor.getControl();
        textControl.setTextLimit(CUSTOM_USER_DEFINITION_NAME_SIZE);
        return textCellEditor;
    }

    @Override
    protected Binding createBinding(final IObservableValue target, final IObservableValue model) {
        final UpdateValueStrategy targetToModel = new UpdateValueStrategy();
        targetToModel.setAfterGetValidator(new CustomerUserInformationDefinitionNameValidator(organization, viewer));
        return dbc.bindValue(target, model, targetToModel, null);
    }


    private void updateCustomUserInformationName(final User user, final String oldInformationName, final String newInformationName) {
        for (final CustomUserInfoValue infoValue : user.getCustomUserInfoValues().getCustomUserInfoValue()) {
            if (infoValue.getName().equals(oldInformationName)) {
                infoValue.setName(newInformationName);
            }
        }
    }

}
