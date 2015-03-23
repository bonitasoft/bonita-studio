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
package org.bonitasoft.studio.actors.ui.wizard.page;

import static org.bonitasoft.studio.common.Messages.bonitaPortalModuleName;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.repository.OrganizationFileStore;
import org.bonitasoft.studio.actors.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;

/**
 * @author Romain Bioteau
 */
public class SynchronizeOrganizationWizardPage extends WizardPage implements ISelectionChangedListener {

    private OrganizationFileStore file;
    private TableViewer viewer;
    private final OrganizationRepositoryStore organizationStore;

    public SynchronizeOrganizationWizardPage() {
        super(SynchronizeOrganizationWizardPage.class.getName());
        setTitle(Messages.synchronizeOrganizationTitle);
        setDescription(Messages.bind(Messages.synchronizeOrganizationDesc, new Object[] { bonitaPortalModuleName }));
        organizationStore = RepositoryManager.getInstance().getRepositoryStore(OrganizationRepositoryStore.class);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(10, 10).create());

        viewer = new TableViewer(mainComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.SINGLE);
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).minSize(SWT.DEFAULT, 200).create());
        final TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(30));
        layout.addColumnData(new ColumnWeightData(70));
        viewer.getTable().setLayout(layout);
        viewer.getTable().setLinesVisible(true);
        viewer.getTable().setHeaderVisible(true);
        viewer.setContentProvider(new ArrayContentProvider());

        TableViewerColumn column = new TableViewerColumn(viewer, SWT.FILL);
        final TableColumn nameColumn = column.getColumn();
        column.getColumn().setText(Messages.name);
        column.setLabelProvider(new OrganizationLabelProvider());

        column = new TableViewerColumn(viewer, SWT.FILL);
        column.getColumn().setText(Messages.description);
        column.setLabelProvider(new ColumnLabelProvider() {

            @Override
            public String getText(final Object element) {
                try {
                    return ((Organization) ((IRepositoryFileStore) element).getContent()).getDescription();
                } catch (final ReadFileStoreException e) {
                    BonitaStudioLog.error("Failed read organization content", e);
                }
                return null;
            }
        });

        final TableColumnSorter sorter = new TableColumnSorter(viewer);
        sorter.setColumn(nameColumn);

        viewer.setInput(organizationStore.getChildren());
        viewer.addSelectionChangedListener(this);

        setControl(mainComposite);
    }

    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        if (!event.getSelection().isEmpty()) {
            file = (OrganizationFileStore) ((IStructuredSelection) event.getSelection()).getFirstElement();
            final DefaultUserOrganizationWizardPage nextPage = (DefaultUserOrganizationWizardPage) getNextPage();
            nextPage.setOrganization(file.getContent());
            nextPage.refreshBindings();
        }

        getContainer().updateButtons();
    }

    public OrganizationFileStore getFileStore() {
        return file;
    }

    @Override
    public boolean isPageComplete() {
        return !viewer.getSelection().isEmpty();
    }

}
