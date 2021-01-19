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
package org.bonitasoft.studio.identity.organization.ui.wizard.page;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationFactory;
import org.bonitasoft.studio.identity.organization.ui.editingsupport.OrganizationDescriptionEditingSupport;
import org.bonitasoft.studio.identity.organization.ui.editingsupport.OrganizationNameEditingSupport;
import org.bonitasoft.studio.identity.organization.ui.provider.label.OrganizationLabelProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Romain Bioteau
 */
public class ManageOrganizationWizardPage extends WizardPage implements ISelectionChangedListener {

    private TableViewer viewer;
    private Button removeButton;
    private final List<Organization> organizations;

    public ManageOrganizationWizardPage(final List<Organization> organizationsWorkingCopy) {
        super(ManageOrganizationWizardPage.class.getName());
        setTitle(Messages.manageOrganizationTitle);
        setDescription(Messages.manageOrganizationDesc);
        organizations = organizationsWorkingCopy;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());

        createButtons(mainComposite);

        viewer = new TableViewer(mainComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).minSize(SWT.DEFAULT, 200).create());
        final TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(30));
        layout.addColumnData(new ColumnWeightData(70));
        viewer.getTable().setLayout(layout);
        viewer.getTable().setHeaderVisible(true);
        viewer.setContentProvider(new ArrayContentProvider());

        TableViewerColumn column = new TableViewerColumn(viewer, SWT.FILL);
        column.setEditingSupport(new OrganizationNameEditingSupport(viewer));
        column.getColumn().setText(Messages.name);
        column.setLabelProvider(new OrganizationLabelProvider());

        column = new TableViewerColumn(viewer, SWT.FILL);
        column.getColumn().setText(Messages.description);
        column.setEditingSupport(new OrganizationDescriptionEditingSupport(viewer));
        column.setLabelProvider(new ColumnLabelProvider() {

            @Override
            public String getText(final Object element) {
                return ((Organization) element).getDescription();
            }
        });

        viewer.setInput(organizations);
        viewer.addSelectionChangedListener(this);

        setControl(mainComposite);
    }

    protected void createButtons(final Composite parent) {
        final Composite buttonComposite = new Composite(parent, SWT.NONE);
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 3).create());
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).indent(0, 25).create());

        createAddButton(buttonComposite);
        createRemoveButton(buttonComposite);

        updateButtons();
    }

    private void createRemoveButton(final Composite buttonComposite) {
        removeButton = new Button(buttonComposite, SWT.FLAT);
        removeButton.setText(Messages.remove);
        removeButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        removeButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final List<?> list = ((IStructuredSelection) viewer.getSelection()).toList();
                if (list.size() > 1) {
                    FileActionDialog.activateYesNoToAll();
                }
                for (final Object sel : list) {
                    if (FileActionDialog.confirmDeletionQuestion(((Organization) sel).getName())) {
                        organizations.remove(sel);
                    }
                }
                FileActionDialog.deactivateYesNoToAll();
                viewer.setInput(organizations);
            }
        });
    }

    private void createAddButton(final Composite buttonComposite) {
        final Button addButton = new Button(buttonComposite, SWT.FLAT);
        addButton.setText(Messages.add);
        addButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        addButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                // TODO -> quand on fera un vrai handler pour crer des Orga (bientot), creer directement les list 
                // de custom info etc, sur l'orga et les users ! ca evitera de devoir les rajouter en live  
                // dans l'editeur et de proc un etat dirty pour rien.
                final Organization organization = OrganizationFactory.eINSTANCE.createOrganization();
                organization.setName(generateOrganizationName());
                organization.setGroups(OrganizationFactory.eINSTANCE.createGroups());
                organization.setUsers(OrganizationFactory.eINSTANCE.createUsers());
                organization.setRoles(OrganizationFactory.eINSTANCE.createRoles());
                organization.setMemberships(OrganizationFactory.eINSTANCE.createMemberships());
                organizations.add(organization);
                viewer.setInput(organizations);
                viewer.setSelection(new StructuredSelection(organization));
            }
        });
    }

    private String generateOrganizationName() {
        final Set<String> names = new HashSet<>();
        for (final Organization a : organizations) {
            names.add(a.getName());
        }
        return NamingUtils.generateNewName(names, Messages.defaultOrganizationName, 1);
    }

    private void updateButtons() {
        if (removeButton != null && !removeButton.isDisposed()) {
            removeButton.setEnabled(viewer != null && !viewer.getSelection().isEmpty());
        }
    }

    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        updateButtons();
        getContainer().updateButtons();
    }

    @Override
    public boolean canFlipToNextPage() {
        return !viewer.getSelection().isEmpty();
    }

    public Organization getSelectedOrganization() {
        return (Organization) ((IStructuredSelection) viewer.getSelection()).getFirstElement();
    }

}
