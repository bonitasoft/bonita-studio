/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

package org.bonitasoft.studio.connectors.ui.wizard.page;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesFileStore;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesRepositoryStore;
import org.bonitasoft.studio.connectors.ui.provider.DatabaseDriversContentProvider;
import org.bonitasoft.studio.connectors.ui.provider.DatabaseDriversLabelProvider;
import org.bonitasoft.studio.dependencies.ui.dialog.SelectJarsDialog;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;

/**
 * @author Aurelie Zara
 */
public class DatabaseConnectorDriversWizardPage extends WizardPage {

    private static final int DEFAULT_BUTTON_WIDTH_HINT = 85;

    private String connectorId;
    private TableViewer driverManagerViewer;
    private DatabaseDriversLabelProvider driversLabelProvider;
    private DatabaseConnectorPropertiesRepositoryStore store;
    private Button automaticallyAddDriver;
    private DataBindingContext context;


    public DatabaseConnectorDriversWizardPage(String connectorId) {
        super(DatabaseConnectorDriversWizardPage.class.getName());
        setTitle(Messages.databaseConnectorDriversWizardPageTitle);
        setDescription(Messages.databaseConnectorDriversWizardPageDescription);
        this.connectorId = connectorId;
        store = (DatabaseConnectorPropertiesRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(DatabaseConnectorPropertiesRepositoryStore.class);
        context = new DataBindingContext();
        driversLabelProvider = new DatabaseDriversLabelProvider();
        String defaultDriver = getDefaultDriver(connectorId);
        if (defaultDriver != null) {
            driversLabelProvider.setDefaultDriver(defaultDriver);
        }

    }


    @Override
    public void createControl(Composite parent) {
        setControl(createDriverManager(parent));

    }

    protected String getDBPrefFilename(String connectorId) {
        return connectorId + "." + DatabaseConnectorPropertiesRepositoryStore.CONF_EXT;
    }

    private Composite createDriverManager(Composite parent) {
        final Composite driverManagerComposite = new Composite(parent, SWT.NONE);
        driverManagerComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        driverManagerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        driverManagerViewer = new TableViewer(driverManagerComposite, SWT.BORDER | SWT.FULL_SELECTION);
        TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(1));
        driverManagerViewer.getTable().setLayout(tableLayout);
        driverManagerViewer.getTable().setLinesVisible(true);
        driverManagerViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        driverManagerViewer.setContentProvider(new DatabaseDriversContentProvider());
        TableViewerColumn column = new TableViewerColumn(driverManagerViewer, SWT.FILL);
        column.setLabelProvider(driversLabelProvider);
        driverManagerViewer.setInput(connectorId);
        createButtonsPart(driverManagerComposite);
        createAutomaticallyAddDriverButton(driverManagerComposite);

        return driverManagerComposite;
    }

    private void createButtonsPart(Composite parent) {
        final Composite buttonsComposite = new Composite(parent, SWT.NONE);
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 3).create());

        createAddbutton(buttonsComposite);
        createRemoveButton(buttonsComposite);
        createActivateButton(buttonsComposite);
    }

    private void createAddbutton(Composite parent) {
        final Button add = new Button(parent, SWT.FLAT);
        add.setText(Messages.add);

        add.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create());

        add.addSelectionListener(new SelectionAdapter() {


            @Override
            public void widgetSelected(SelectionEvent e) {
                SelectJarsDialog dialog = new SelectJarsDialog(getShell());
                if (dialog.open() == Window.OK) {
                    List<String> jars = getJars();
                    int size = jars.size();
                    List<IRepositoryFileStore> selectedJars = dialog.getSelectedJars();
                    for (IRepositoryFileStore jar : selectedJars) {
                        jars.add(jar.getName());
                    }
                    if (size == 0 && !jars.isEmpty()) {
                        driversLabelProvider.setDefaultDriver(jars.get(0));
                        setDefaultDriver(connectorId, jars.get(0));
                    }
                    setJars(connectorId, jars);
                    if (jars.size() == 1) {
                        setDefaultDriver(connectorId, jars.get(0));
                    }
                    driverManagerViewer.setInput(connectorId);
                }

            }
        });
    }

    private void createRemoveButton(Composite parent) {
        final Button remove = new Button(parent, SWT.FLAT);
        remove.setText(Messages.remove);
        remove.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create());
        remove.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                removeDriver();
                driverManagerViewer.setInput(connectorId);
            }
        });
        bindButtonWithViewer(remove, driverManagerViewer);
    }

    private void createActivateButton(Composite parent) {
        final Button activate = new Button(parent, SWT.FLAT);
        activate.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create());
        activate.setText(Messages.activate);
        activate.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                driversLabelProvider.setDefaultDriver(getSelectedDriver());
                setDefaultDriver(connectorId, getSelectedDriver());
                driverManagerViewer.setInput(connectorId);
            }
        });
        bindButtonWithViewer(activate, driverManagerViewer);
    }

    private void createAutomaticallyAddDriverButton(Composite parent) {
        automaticallyAddDriver = new Button(parent, SWT.CHECK);
        automaticallyAddDriver.setText(Messages.automaticallyAddDriver);
        automaticallyAddDriver.setLayoutData(GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.CENTER).create());
        automaticallyAddDriver.setSelection(getAutoAddDriverProperty(connectorId));
        automaticallyAddDriver.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                setAutoAddDriverProperty(connectorId);
            }
        });
    }

    private void bindButtonWithViewer(Button button, TableViewer viewer) {
        UpdateValueStrategy modelToTarget = new UpdateValueStrategy();
        modelToTarget.setConverter(new Converter(Object.class, Boolean.class) {

            @Override
            public Object convert(Object fromObject) {
                return fromObject != null;
            }

        });
        context.bindValue(SWTObservables.observeEnabled(button), ViewersObservables.observeSingleSelection(viewer), null,
                modelToTarget);
    }

    private void removeDriver() {
        String driver = getSelectedDriver();
        if (driver.equals(getDefaultDriver(connectorId))) {
            setDefaultDriver(connectorId, null);
        }
        List<String> jars = getJars();
        jars.remove(driver);
        DatabaseConnectorPropertiesFileStore fileStore = (DatabaseConnectorPropertiesFileStore) store
                .createRepositoryFileStore(getDBPrefFilename(connectorId));
        fileStore.setJarList(jars);
    }

    private String getSelectedDriver() {
        IStructuredSelection driverSelection = (IStructuredSelection) driverManagerViewer.getSelection();
        return (String) driverSelection.getFirstElement();
    }

    private List<String> getJars() {
        List<String> jars = new ArrayList<String>();
        TableItem[] items = driverManagerViewer.getTable().getItems();
        for (TableItem item : items) {
            jars.add((String) item.getData());
        }
        return jars;
    }

    private void setJars(String connectorId, List<String> jars) {
        DatabaseConnectorPropertiesFileStore fileStore = (DatabaseConnectorPropertiesFileStore) store
                .getChild(getDBPrefFilename(connectorId));
        if (fileStore == null) {
            fileStore = (DatabaseConnectorPropertiesFileStore) store
                    .createRepositoryFileStore(getDBPrefFilename(connectorId));
        }
        fileStore.setJarList(jars);
    }

    public String getDefaultDriver(String connectorId) {
        DatabaseConnectorPropertiesFileStore fileStore = (DatabaseConnectorPropertiesFileStore) store
                .getChild(getDBPrefFilename(connectorId));
        if (fileStore != null) {
            return fileStore.getDefault();
        }
        return null;
    }

    private boolean getAutoAddDriverProperty(String connectorId) {
        DatabaseConnectorPropertiesFileStore fileStore = (DatabaseConnectorPropertiesFileStore) store
                .getChild(getDBPrefFilename(connectorId));
        if (fileStore != null) {
            return fileStore.getAutoAddDriver();
        }
        return true;

    }

    private void setDefaultDriver(String connectorId, String defaultDriver) {
        DatabaseConnectorPropertiesFileStore fileStore = (DatabaseConnectorPropertiesFileStore) store
                .getChild(getDBPrefFilename(connectorId));
        if (fileStore == null) {
            fileStore = (DatabaseConnectorPropertiesFileStore) store
                    .createRepositoryFileStore(getDBPrefFilename(connectorId));
        }
        fileStore.setDefault(defaultDriver);
    }

    private void setAutoAddDriverProperty(String connectorId) {
        DatabaseConnectorPropertiesFileStore fileStore = (DatabaseConnectorPropertiesFileStore) store
                .getChild(getDBPrefFilename(connectorId));
        if (fileStore != null) {
            fileStore.setAutoAddDriver(new Boolean(automaticallyAddDriver.getSelection()));
        } else {
            fileStore = (DatabaseConnectorPropertiesFileStore) store
                    .createRepositoryFileStore(getDBPrefFilename(connectorId));
        }
    }

}
