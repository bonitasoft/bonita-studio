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
package org.bonitasoft.studio.identity.organization.editor.formpage.user;

import org.bonitasoft.studio.identity.organization.editor.OrganizationEditor;
import org.bonitasoft.studio.identity.organization.editor.formpage.AbstractOrganizationFormPage;
import org.bonitasoft.studio.identity.organization.model.organization.Group;
import org.bonitasoft.studio.identity.organization.model.organization.Membership;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationFactory;
import org.bonitasoft.studio.identity.organization.model.organization.Role;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.forms.AbstractFormPart;

public class UserFormPage extends AbstractOrganizationFormPage {

    private UserFormPart userFormPart;

    public UserFormPage(String id, String title, IEclipseContext context, OrganizationEditor editor) {
        super(id, title, context, editor);
    }

    @Override
    protected void createHeaderContent(ToolBar toolBar) {
        // TODO Auto-generated method stub
    }

    @Override
    protected void createForm() {
        super.createForm();
        userFormPart = new UserFormPart(scrolledForm.getBody(), this);
        getManagedForm().addPart(userFormPart);
    }

    @Override
    public AbstractFormPart getFormPart() {
        return userFormPart;
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
        if (userFormPart != null) {
            userFormPart.markStale();
        }
    }

    public Membership createDefaultMembership(String username) {
        Membership newMembership = OrganizationFactory.eINSTANCE.createMembership();
        newMembership.setUserName(username);
        newMembership.setGroupName(getFirstRootGroup());
        newMembership.setRoleName(getFirstRole());
        return newMembership;
    }

    private String getFirstRootGroup() {
        return observeWorkingCopy().getValue().getGroups().getGroup().stream()
                .filter(grp -> grp.getParentPath() == null)
                .findFirst()
                .map(Group::getName)
                .orElse(null);
    }

    private String getFirstRole() {
        return observeWorkingCopy().getValue().getRoles().getRole().stream()
                .findFirst().map(Role::getName).orElse(null);
    }

    public void refreshSelectedUser() {
        if (userFormPart != null) {
            userFormPart.refreshSelectedUser();
        }
    }

    @Override
    public void refreshList() {
        if (userFormPart != null) {
            userFormPart.refreshUserList();
        }
    }

    public void doRefreshMembershipTable() {
        if (userFormPart != null) {
            userFormPart.refreshMembershipTable();
        }
    }

    public void redrawCustomInfoTable() {
        if (userFormPart != null) {
            userFormPart.redrawCustomInfoTable();
        }
    }

}
