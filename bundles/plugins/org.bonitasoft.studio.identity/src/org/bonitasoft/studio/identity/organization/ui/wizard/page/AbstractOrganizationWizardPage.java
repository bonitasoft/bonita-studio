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

import java.util.List;

import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.model.organization.Membership;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.Role;
import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.bonitasoft.studio.ui.widget.NativeTabFolderWidget;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 */
public abstract class AbstractOrganizationWizardPage extends WizardPage implements ISelectionChangedListener {

    final int NAME_SIZE = 50;
    final int PASSWORD_SIZE = 60;

    private StructuredViewer viewer;
    protected Group infoGroup;
    private String searchQuery;
    protected Organization organization;
    protected List<Membership> membershipList;
    protected List<org.bonitasoft.studio.identity.organization.model.organization.Group> groupList;
    protected List<User> userList;
    protected List<Role> roleList;
    protected EMFDataBindingContext context;
    protected WizardPageSupport pageSupport;
    protected NativeTabFolderWidget tabFolder;
    protected Composite mainComposite;

    protected AbstractOrganizationWizardPage(final String pageName) {
        super(pageName);

    }

    @Override
    public void dispose() {
        super.dispose();
        if (pageSupport != null) {
            pageSupport.dispose();
        }
        if (context != null) {
            context.dispose();
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        context = new EMFDataBindingContext();
        mainComposite = doCreateControl(parent);
        setControl(mainComposite);
    }

    protected Composite doCreateControl(final Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).equalWidth(true).create());

        final Composite leftComposite = new Composite(mainComposite, SWT.NONE);
        leftComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        leftComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).equalWidth(false).create());

        final Composite rightComposite = new Composite(mainComposite, SWT.NONE);
        rightComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        rightComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());

        final Composite buttonComposite = new Composite(leftComposite, SWT.NONE);
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().indent(0, 30).create());
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).equalWidth(true).spacing(0, 2).create());

        final Button removeButton = createButtons(buttonComposite);

        viewer = createViewer(leftComposite);

        viewer.addSelectionChangedListener(this);
        viewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                removeButton.setEnabled(!event.getSelection().isEmpty());
            }
        });

        configureViewer(viewer);

        infoGroup = new Group(rightComposite, SWT.NONE);
        infoGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        infoGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());

        configureInfoGroup(infoGroup);

        pageSupport = WizardPageSupport.create(this, context);
        return mainComposite;
    }

    /**
     * Returns the remove button
     * 
     * @param buttonComposite
     * @return
     */
    protected Button createButtons(final Composite buttonComposite) {
        final Button addButton = new Button(buttonComposite, SWT.FLAT);
        addButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        addButton.setText(Messages.add);
        addButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                addButtonSelected();
            }
        });

        final Button removeButton = new Button(buttonComposite, SWT.FLAT);
        removeButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        removeButton.setText(Messages.remove);
        removeButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                removeButtonSelected();
            }
        });
        removeButton.setEnabled(false);
        return removeButton;
    }

    protected StructuredViewer createViewer(final Composite parent) {
        final Composite viewerComposite = new Composite(parent, SWT.NONE);
        viewerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        viewerComposite.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(1).equalWidth(true).margins(0, 0).spacing(0, 5).create());

        final Text searchBox = new Text(viewerComposite, SWT.SEARCH | SWT.ICON_SEARCH | SWT.BORDER | SWT.CANCEL);
        searchBox.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        searchBox.setMessage(Messages.search);

        final Composite tableViewerComposite = new Composite(viewerComposite, SWT.NONE);
        tableViewerComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
        tableViewerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final TableViewer tableViewer = new TableViewer(tableViewerComposite,
                SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
        final Table table = tableViewer.getTable();
        table.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 270).create());
        table.setHeaderVisible(true);
        tableViewer.setContentProvider(new ArrayContentProvider());
        tableViewer.addFilter(new ViewerFilter() {

            @Override
            public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
                return viewerSelect(element, searchQuery);
            }
        });

        searchBox.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(final ModifyEvent e) {
                searchQuery = searchBox.getText();
                tableViewer.refresh();
            }

        });

        return tableViewer;
    }

    protected abstract boolean viewerSelect(Object element, String searchQuery);

    protected abstract void addButtonSelected();

    protected abstract void removeButtonSelected();

    protected abstract void configureInfoGroup(Group group);

    protected abstract void configureViewer(StructuredViewer viewer);

    protected StructuredViewer getViewer() {
        return viewer;
    }

    protected Group getInfoGroup() {
        return infoGroup;
    }

    protected void setControlEnabled(final Control control, final boolean enabled) {
        if (control instanceof TabFolder) {
            for (final TabItem item : ((TabFolder) control).getItems()) {
                final Control tabControl = item.getControl();
                if (tabControl != null) {
                    tabControl.setEnabled(enabled);
                    if (tabControl instanceof Composite) {
                        setControlEnabled(tabControl, enabled);
                    }
                }
            }
            return;
        }

        if (control instanceof Composite) {
            for (final Control c : ((Composite) control).getChildren()) {
                c.setEnabled(enabled);
                if (c instanceof Composite) {
                    setControlEnabled(c, enabled);
                }
            }
            return;
        }

        control.setEnabled(enabled);

    }

    public void setOrganization(final Organization organization) {
        this.organization = organization;
        if (organization != null) {
            membershipList = organization.getMemberships().getMembership();
            groupList = organization.getGroups().getGroup();
            roleList = organization.getRoles().getRole();
            userList = organization.getUsers().getUser();
        }
    }

    public Organization getOrganization() {
        return organization;
    }

    protected void addTableColumLayout(final Table table) {
        final TableColumnLayout tcLayout = new TableColumnLayout();
        for (final TableColumn col : table.getColumns()) {
            tcLayout.setColumnData(col, new ColumnWeightData(1));
        }
        table.getParent().setLayout(tcLayout);
    }

}
