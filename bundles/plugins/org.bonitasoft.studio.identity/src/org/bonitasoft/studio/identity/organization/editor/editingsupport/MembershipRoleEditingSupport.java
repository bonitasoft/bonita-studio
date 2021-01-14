/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.identity.organization.editor.editingsupport;

import java.util.Objects;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.identity.organization.editor.formpage.user.UserFormPage;
import org.bonitasoft.studio.identity.organization.model.organization.Membership;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationPackage;
import org.bonitasoft.studio.identity.organization.model.organization.Role;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class MembershipRoleEditingSupport extends EditingSupport {

    public static final String GROUP_COMBO_EDITOR_ID = "membershipGroupEditingSupport.Combo";

    private UserFormPage formPage;
    private DataBindingContext ctx;
    private IObservableList<Role> input;
    private IObservableValue<String> selectedMembershipRoleNameObservable;

    public MembershipRoleEditingSupport(ColumnViewer viewer,
            IObservableValue<String> selectedMembershipRoleNameObservable,
            UserFormPage formPage, DataBindingContext ctx) {
        super(viewer);
        this.selectedMembershipRoleNameObservable = selectedMembershipRoleNameObservable;
        this.formPage = formPage;
        this.ctx = ctx;
    }

    @Override
    protected CellEditor getCellEditor(Object element) {
        ComboBoxViewerCellEditor cellEditor = new ComboBoxViewerCellEditor((Composite) getViewer().getControl(),
                SWT.READ_ONLY);
        cellEditor.getControl().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, GROUP_COMBO_EDITOR_ID);
        cellEditor.setContentProvider(new ObservableListContentProvider());
        cellEditor.setActivationStyle(ComboBoxViewerCellEditor.DROP_DOWN_ON_MOUSE_ACTIVATION);
        cellEditor.getControl().addListener(SWT.Selection, e -> getViewer().applyEditorValue());
        cellEditor.setLabelProvider(new LabelProviderBuilder<Role>()
                .withTextProvider(Role::getDisplayName)
                .createLabelProvider());

        IObservableValue rolesObservable = EMFObservables.observeDetailValue(ctx.getValidationRealm(),
                formPage.observeWorkingCopy(), OrganizationPackage.Literals.ORGANIZATION__ROLES);
        input = EMFObservables.observeDetailList(ctx.getValidationRealm(),
                rolesObservable, OrganizationPackage.Literals.ROLES__ROLE);
        cellEditor.setInput(input);
        return cellEditor;
    }

    @Override
    protected boolean canEdit(Object element) {
        return true;
    }

    @Override
    protected Object getValue(Object element) {
        String roleName = ((Membership) element).getRoleName();
        return roleName != null
                ? input.stream()
                        .filter(role -> Objects.equals(role.getName(), roleName)).findFirst()
                        .orElse(null)
                : null;
    }

    @Override
    protected void setValue(Object element, Object value) {
        if (value != null) {
            selectedMembershipRoleNameObservable.setValue(((Role) value).getName());
        }
    }
}
