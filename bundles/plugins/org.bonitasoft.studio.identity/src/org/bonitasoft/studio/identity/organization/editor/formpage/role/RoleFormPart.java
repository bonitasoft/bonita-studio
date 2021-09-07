/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.identity.organization.editor.formpage.role;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import org.bonitasoft.studio.identity.organization.editor.control.role.RoleEditionControl;
import org.bonitasoft.studio.identity.organization.editor.control.role.RoleList;
import org.bonitasoft.studio.identity.organization.model.organization.Role;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.bonitasoft.studio.ui.databinding.ComputedValueBuilder;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.AbstractFormPart;

public class RoleFormPart extends AbstractFormPart {

    private DataBindingContext ctx = new DataBindingContext();
    private RoleFormPage formPage;
    private RoleList roleList;
    private RoleEditionControl roleEditionControl;

    public RoleFormPart(Composite parent, RoleFormPage formPage) {
        this.formPage = formPage;

        parent.setLayout(GridLayoutFactory.fillDefaults().margins(5, 5).numColumns(2)
                .spacing(20, LayoutConstants.getSpacing().y).create());
        parent.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 600).create());

        createRoleList(parent);
        createRoleDetailsControl(parent);
    }

    private void createRoleDetailsControl(Composite parent) {
        IObservableValue<Role> selectedRoleObservable = roleList.observeSelectedRole();
        roleEditionControl = new RoleEditionControl(parent, formPage, selectedRoleObservable, ctx);

        ctx.bindValue(selectedRoleObservable, roleEditionControl.observeSectionTitle(),
                updateValueStrategy().withConverter(ConverterBuilder.<Role, String> newConverter()
                        .fromType(Role.class)
                        .toType(String.class)
                        .withConvertFunction(rle -> rle == null ? "" : rle.getDisplayName())
                        .create()).create(),
                neverUpdateValueStrategy().create());

        ctx.bindValue(roleEditionControl.observeSectionVisible(), new ComputedValueBuilder<Boolean>()
                .withSupplier(() -> selectedRoleObservable.getValue() != null)
                .build());
    }

    private void createRoleList(Composite parent) {
        Composite roleListComposite = formPage.getToolkit().createComposite(parent);
        roleListComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        roleListComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).hint(400, SWT.DEFAULT).create());

        roleList = new RoleList(roleListComposite, formPage, ctx);
    }

    public void refreshSelectedRole() {
        roleList.refreshSelectedRole();
    }

    @Override
    public void commit(boolean onSave) {
        if (!onSave) { // onSave, commit is performed only once, on the overviewFormPart
            formPage.commit();
        }
        super.commit(onSave);
        if (onSave) {
            getManagedForm().dirtyStateChanged();
        }
    }

    public void refreshRoleList() {
        roleList.refreshRoleList();
    }

}
