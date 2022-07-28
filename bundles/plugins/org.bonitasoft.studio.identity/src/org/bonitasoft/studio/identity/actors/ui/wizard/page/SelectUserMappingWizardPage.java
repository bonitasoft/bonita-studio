/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.identity.actors.ui.wizard.page;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationFactory;
import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.bonitasoft.studio.model.actormapping.ActorMapping;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;

/**
 * @author Romain Bioteau
 */
public class SelectUserMappingWizardPage extends SelectOrganizationWizardPage {

    private final ActorMapping mapping;
    private final SortedSet<String> availableUsers = new TreeSet<String>();
    private final SortedSet<String> selectedUsers;
    private List<User> users;
    private CheckboxTableViewer availableUserViewer;

    public SelectUserMappingWizardPage(final ActorMapping mapping) {
        super();
        setTitle(Messages.selectUserTitle);
        setDescription(Messages.selectUserDescription);
        this.mapping = mapping;
        selectedUsers = new TreeSet<String>(this.mapping.getUsers().getUser());
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        super.createControl(parent);

        final Composite mainComposite = (Composite) getControl();
        final Composite viewersComposite = new Composite(mainComposite, SWT.NONE);
        viewersComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).span(2, 1).hint(SWT.DEFAULT, 250).create());
        viewersComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(5, 5).extendedMargins(0, 0, 10, 0).create());

        availableUserViewer = CheckboxTableViewer.newCheckList(viewersComposite, SWT.BORDER | SWT.FULL_SELECTION);
        availableUserViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        availableUserViewer.getTable().setHeaderVisible(true);
        availableUserViewer.setContentProvider(new ArrayContentProvider());
        final TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(100));
        availableUserViewer.getTable().setLayout(layout);

        final TableViewerColumn columnViewer = new TableViewerColumn(availableUserViewer, SWT.NONE);
        final TableColumn usernameColumn = columnViewer.getColumn();
        usernameColumn.setText(Messages.userName);
        columnViewer.setLabelProvider(new ColumnLabelProvider());
        final TableColumnSorter sorter = new TableColumnSorter(availableUserViewer);
        sorter.setColumn(usernameColumn);

        availableUserViewer.setInput(availableUsers);
        availableUserViewer.setCheckedElements(selectedUsers.toArray());

    }

    protected void removedUsers(final List<String> removedUsers) {
        selectedUsers.removeAll(removedUsers);
        for (final User u : users) {
            if (removedUsers.contains(u.getUserName())
                    && !availableUsers.contains(u.getUserName())) {
                availableUsers.add(u.getUserName());
            }
        }
        mapping.getUsers().getUser().clear();
        mapping.getUsers().getUser().addAll(selectedUsers);

    }

    protected void selectedUsers(final List<String> selectedUsers) {
        availableUsers.removeAll(selectedUsers);
        this.selectedUsers.addAll(selectedUsers);
        mapping.getUsers().getUser().clear();
        mapping.getUsers().getUser().addAll(this.selectedUsers);

    }

    @Override
    protected void refreshOrganization(final Organization organization) {
        if (organization != null) {
            if (organization.getUsers() == null) {
                organization.setUsers(OrganizationFactory.eINSTANCE.createUsers());
            }

            users = organization.getUsers().getUser();

            availableUsers.clear();
            for (final User u : users) {
                availableUsers.add(u.getUserName());
            }
            availableUsers.addAll(selectedUsers);
            if (availableUserViewer != null) {
                availableUserViewer.setInput(availableUsers);
                availableUserViewer.setCheckedElements(selectedUsers.toArray());
            }
        }
    }

    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        final IRepositoryFileStore fstore = (IRepositoryFileStore) ((IStructuredSelection) event.getSelection()).getFirstElement();
        try {
            refreshOrganization((Organization) fstore.getContent());
        } catch (final ReadFileStoreException e) {
            BonitaStudioLog.error("Failed read organization content", e);
        }
    }

    public Object[] getSelectedUsers() {
        return availableUserViewer.getCheckedElements();
    }

}
