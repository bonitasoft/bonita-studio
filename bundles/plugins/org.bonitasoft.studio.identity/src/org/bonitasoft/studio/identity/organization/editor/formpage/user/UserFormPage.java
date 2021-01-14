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
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationPackage;
import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.bonitasoft.studio.identity.organization.model.organization.Users;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.databinding.EMFObservables;
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

    public String toUserDisplayName(User user) {
        if (user.getFirstName() != null && user.getLastName() != null) {
            return String.format("%s %s", user.getFirstName(), user.getLastName());
        }
        return user.getUserName();
    }

    public IObservableList<User> observeUsers() {
        IObservableValue<Users> groupsObservable = EMFObservables.observeDetailValue(Realm.getDefault(),
                observeWorkingCopy(), OrganizationPackage.Literals.ORGANIZATION__USERS);
        return EMFObservables.observeDetailList(Realm.getDefault(), groupsObservable,
                OrganizationPackage.Literals.USERS__USER);
    }

    public void refreshSelectedUser() {
        userFormPart.refreshSelectedUser();
    }

    @Override
    public void refreshList() {
        userFormPart.refreshUserList();
    }

    public void doRefreshMembershipTable() {
        if (userFormPart != null) {
            userFormPart.refreshMembershipTable();
        }
    }

}
