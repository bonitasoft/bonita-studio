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

import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.identity.organization.editor.OrganizationEditor;
import org.bonitasoft.studio.identity.organization.editor.formpage.AbstractOrganizationFormPage;
import org.bonitasoft.studio.identity.organization.model.organization.Membership;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.forms.AbstractFormPart;

public class RoleFormPage extends AbstractOrganizationFormPage {

    private RoleFormPart roleFormPart;

    public RoleFormPage(String id, String title, IEclipseContext context, OrganizationEditor editor) {
        super(id, title, context, editor);
    }

    @Override
    protected void createHeaderContent(ToolBar toolBar) {
        // TODO Auto-generated method stub
    }

    @Override
    protected void createForm() {
        super.createForm();
        roleFormPart = new RoleFormPart(scrolledForm.getBody(), this);
        getManagedForm().addPart(roleFormPart);
    }

    @Override
    public AbstractFormPart getFormPart() {
        return roleFormPart;
    }

    @Override
    public void documentAboutToBeChanged(DocumentEvent event) {
    }

    @Override
    public void documentChanged(DocumentEvent event) {
        if (!isActive()) {
            makeStale();
        }
    }

    @Override
    public void makeStale() {
        if (roleFormPart != null) {
            roleFormPart.markStale();
        }
    }

    public void refreshSelectedRole() {
        roleFormPart.refreshSelectedRole();
    }

    @Override
    public void refreshList() {
        roleFormPart.refreshRoleList();
    }

    public void refactorMemberships(List<Membership> memberships, String oldName, String newName) {
        memberships.stream()
                .filter(m -> Objects.equals(m.getRoleName(), oldName))
                .forEach(m -> m.setRoleName(newName));
        refreshMembershipTable();
        refreshUserList();
    }

}
