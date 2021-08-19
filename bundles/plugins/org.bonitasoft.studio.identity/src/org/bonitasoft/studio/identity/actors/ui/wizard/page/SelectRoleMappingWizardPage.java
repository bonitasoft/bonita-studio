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
import org.bonitasoft.studio.identity.organization.model.organization.Role;
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
public class SelectRoleMappingWizardPage extends SelectOrganizationWizardPage {

    private final ActorMapping mapping;
    private final SortedSet<String> availableRoles = new TreeSet<String>();
    private final SortedSet<String> selectedRoles;
    private List<Role> roles;
    private CheckboxTableViewer availableRoleViewer;

    public SelectRoleMappingWizardPage(final ActorMapping mapping) {
        super();
        setTitle(Messages.selectRoleTitle);
        setDescription(Messages.selectRoleDescription);
        this.mapping = mapping;
        selectedRoles = new TreeSet<String>(this.mapping.getRoles().getRole());
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
        viewersComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).hint(SWT.DEFAULT, 250).create());
        viewersComposite.setLayout(GridLayoutFactory.swtDefaults().numColumns(1).margins(0, 0).extendedMargins(0, 0, 10, 0).equalWidth(false).create());

        availableRoleViewer = CheckboxTableViewer.newCheckList(viewersComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL);
        availableRoleViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        availableRoleViewer.getTable().setHeaderVisible(true);
        availableRoleViewer.setContentProvider(new ArrayContentProvider());
        final TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(100));
        availableRoleViewer.getTable().setLayout(layout);

        final TableViewerColumn columnViewer = new TableViewerColumn(availableRoleViewer, SWT.NONE);
        final TableColumn usernameColumn = columnViewer.getColumn();
        usernameColumn.setText(Messages.roleName);
        columnViewer.setLabelProvider(new ColumnLabelProvider());
        final TableColumnSorter sorter = new TableColumnSorter(availableRoleViewer);
        sorter.setColumn(usernameColumn);

        availableRoleViewer.setInput(availableRoles);
        availableRoleViewer.setCheckedElements(selectedRoles.toArray());

    }

    @Override
    protected void refreshOrganization(final Organization organization) {
        if (organization != null) {
            if (organization.getRoles() == null) {
                organization.setRoles(OrganizationFactory.eINSTANCE.createRoles());
            }

            roles = organization.getRoles().getRole();

            availableRoles.clear();
            for (final Role r : roles) {
                availableRoles.add(r.getName());
            }
            availableRoles.addAll(selectedRoles);
            if (availableRoleViewer != null) {
                availableRoleViewer.setInput(availableRoles);
                availableRoleViewer.setCheckedElements(selectedRoles.toArray());
            }
        }
    }

    protected void selectedRoles(final List<String> addedRoles) {
        availableRoles.removeAll(addedRoles);
        selectedRoles.addAll(addedRoles);
        mapping.getRoles().getRole().clear();
        mapping.getRoles().getRole().addAll(selectedRoles);
    }

    protected void removedRoles(final List<String> removedRoles) {
        selectedRoles.removeAll(removedRoles);
        for (final Role r : roles) {
            if (removedRoles.contains(r.getName())
                    && !availableRoles.contains(r.getName())) {
                availableRoles.add(r.getName());
            }
        }
        mapping.getRoles().getRole().clear();
        mapping.getRoles().getRole().addAll(selectedRoles);

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

    public Object[] getSelectedRoles() {
        return availableRoleViewer.getCheckedElements();
    }

}
