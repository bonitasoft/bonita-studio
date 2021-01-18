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

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import org.bonitasoft.studio.identity.organization.editor.control.user.UserEditionControl;
import org.bonitasoft.studio.identity.organization.editor.control.user.UserList;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationFactory;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationPackage;
import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.bonitasoft.studio.identity.organization.model.organization.Users;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.bonitasoft.studio.ui.databinding.ComputedValueBuilder;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.AbstractFormPart;

public class UserFormPart extends AbstractFormPart {

    private DataBindingContext ctx = new DataBindingContext();
    private UserFormPage formPage;
    private UserList userList;
    private UserEditionControl userEditionControl;

    public UserFormPart(Composite parent, UserFormPage formPage) {
        this.formPage = formPage;

        parent.setLayout(GridLayoutFactory.fillDefaults().margins(5, 5).numColumns(2)
                .spacing(20, LayoutConstants.getSpacing().y).create());
        parent.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createUserList(parent);
        createUserDetailsControl(parent);
    }

    private void createUserDetailsControl(Composite parent) {
        IObservableValue<User> selectedUserObservable = userList.observeSelectedUser();
        userEditionControl = new UserEditionControl(parent, formPage, selectedUserObservable, ctx);

        ctx.bindValue(selectedUserObservable, userEditionControl.observeSectionTitle(),
                updateValueStrategy().withConverter(ConverterBuilder.<User, String> newConverter()
                        .fromType(User.class)
                        .toType(String.class)
                        .withConvertFunction(user -> user == null ? "" : formPage.toUserDisplayName(user))
                        .create()).create(),
                neverUpdateValueStrategy().create());

        ctx.bindValue(userEditionControl.observeSectionVisible(), new ComputedValueBuilder<Boolean>()
                .withSupplier(() -> selectedUserObservable.getValue() != null)
                .build());

    }

    private void createUserList(Composite parent) {
        Composite userListComposite = formPage.getToolkit().createComposite(parent);
        userListComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        userListComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).hint(300, SWT.DEFAULT).create());

        userList = new UserList(userListComposite, formPage, ctx);
        IObservableValue<Users> groupsObservable = EMFObservables.observeDetailValue(Realm.getDefault(),
                formPage.observeWorkingCopy(), OrganizationPackage.Literals.ORGANIZATION__USERS);
        IObservableList<User> userListObservable = EMFObservables.observeDetailList(Realm.getDefault(), groupsObservable,
                OrganizationPackage.Literals.USERS__USER);
        userListObservable.forEach(user -> {
            if (user.getPersonalData() == null) {
                user.setPersonalData(OrganizationFactory.eINSTANCE.createContactData());
            }
            if (user.getProfessionalData() == null) {
                user.setProfessionalData(OrganizationFactory.eINSTANCE.createContactData());
            }
        });
        ctx.bindList(userList.observeInput(), userListObservable);
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

    public void refreshSelectedUser() {
        userList.refreshSelectedUser();
    }

    public void refreshUserList() {
        userList.refreshUserList();
    }

    public void refreshMembershipTable() {
        userEditionControl.refreshMembershipTable();
    }

}
