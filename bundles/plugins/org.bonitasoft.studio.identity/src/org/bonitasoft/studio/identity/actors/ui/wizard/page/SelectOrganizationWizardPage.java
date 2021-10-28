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

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.repository.OrganizationFileStore;
import org.bonitasoft.studio.identity.organization.repository.OrganizationRepositoryStore;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Romain Bioteau
 */
public abstract class SelectOrganizationWizardPage extends WizardPage implements ISelectionChangedListener {

    private final OrganizationRepositoryStore organizationStore;
    private final ActiveOrganizationProvider activeOrganizationProvider;

    public SelectOrganizationWizardPage() {
        super(SelectOrganizationWizardPage.class.getName());
        organizationStore = RepositoryManager.getInstance().getRepositoryStore(OrganizationRepositoryStore.class);
        activeOrganizationProvider = new ActiveOrganizationProvider();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.swtDefaults().numColumns(2).margins(5, 5).equalWidth(false).create());

        final Label organizationLabel = new Label(mainComposite, SWT.NONE);
        organizationLabel.setText(Messages.selectOrganization);

        final ComboViewer organizationCombo = new ComboViewer(mainComposite, SWT.READ_ONLY | SWT.BORDER);
        organizationCombo.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        organizationCombo.setContentProvider(new ArrayContentProvider());
        organizationCombo.setLabelProvider(new LabelProvider() {

            @Override
            public String getText(final Object element) {
                try {
                    return ((Organization) ((IRepositoryFileStore) element).getContent()).getName();
                } catch (final ReadFileStoreException e) {
                    BonitaStudioLog.error("Failed read organization content", e);
                }
                return null;
            }
        });

        organizationCombo.addSelectionChangedListener(this);
        organizationCombo.setInput(organizationStore.getChildren());

        IRepositoryFileStore defaultOrganization = organizationStore
                .getChild(activeOrganizationProvider.getActiveOrganization() + "." + OrganizationRepositoryStore.ORGANIZATION_EXT, true);
        if (defaultOrganization == null) {
            final List<OrganizationFileStore> orga = organizationStore.getChildren();
            if (!orga.isEmpty()) {
                defaultOrganization = orga.get(0);
            }
        }
        if (defaultOrganization != null) {
            organizationCombo.setSelection(new StructuredSelection(defaultOrganization));
            try {
                refreshOrganization((Organization) defaultOrganization.getContent());
            } catch (final ReadFileStoreException e) {
                BonitaStudioLog.error("Failed read organization content", e);
            }
        }
        final Label separator = new Label(mainComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());

        setControl(mainComposite);
    }

    protected abstract void refreshOrganization(Organization organization);

    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        final Object selectedRepositoryFileStore = ((IStructuredSelection) event.getSelection()).getFirstElement();
        if (selectedRepositoryFileStore != null) {
            final IRepositoryFileStore fstore = (IRepositoryFileStore) selectedRepositoryFileStore;
            try {
                refreshOrganization((Organization) fstore.getContent());
            } catch (final ReadFileStoreException e) {
                BonitaStudioLog.error("Failed read organization content", e);
            }
        }
    }

}
