/**
 * Copyright (C) 2009-2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.connectors.ui.preferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.common.repository.provider.ExtendedCategory;
import org.bonitasoft.studio.common.repository.provider.ExtendedConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesFileStore;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesRepositoryStore;
import org.bonitasoft.studio.connectors.ui.provider.DabaBaseConnectorDefinitionLabelProvider;
import org.bonitasoft.studio.connectors.ui.provider.DatabaseConnectorDefinitionContentProvider;
import org.bonitasoft.studio.connectors.ui.provider.DatabaseDriversContentProvider;
import org.bonitasoft.studio.connectors.ui.provider.DatabaseDriversLabelProvider;
import org.bonitasoft.studio.dependencies.repository.DependencyFileStore;
import org.bonitasoft.studio.dependencies.ui.dialog.SelectJarsDialog;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class DriverAssociationPage implements ControlSupplier {

    private final String DATABASE = "database";
    private TableViewer viewer;
    private TableViewer driverManagerViewer;
    private DatabaseConnectorPropertiesRepositoryStore store;
    private Button automaticallyAddDriver;
    private DatabaseDriversLabelProvider driversLabelProvider;
    private ConnectorDefRepositoryStore connectorDefRepositoryStore;
    
    public DriverAssociationPage(RepositoryAccessor respositoryAccessor) {
        store = respositoryAccessor.getRepositoryStore(DatabaseConnectorPropertiesRepositoryStore.class);
        connectorDefRepositoryStore = respositoryAccessor.getRepositoryStore(ConnectorDefRepositoryStore.class);
        driversLabelProvider = new DatabaseDriversLabelProvider();
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(GridLayoutFactory.swtDefaults().numColumns(2).create());
        container.setLayoutData(GridDataFactory.swtDefaults().grab(true, true).create());


        final Label label = new Label(container, SWT.WRAP);
        label.setText(Messages.BonitaPreferencePage_DBConnectors_Description);
        label.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, false).create());

        createDBConnectorsList(container);
        createDriverManager(container, ctx);

        return container;
    }
    

    private void createDBConnectorsList(Composite parent) {
        final Composite connectorListComposite = new Composite(parent, SWT.NONE);
        connectorListComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
        connectorListComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        final Text searchField = new Text(connectorListComposite,
                SWT.BORDER | SWT.SEARCH | SWT.ICON_SEARCH | SWT.ICON_CANCEL);
        searchField.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        searchField.setMessage(Messages.search);
        viewer = new TableViewer(connectorListComposite, SWT.BORDER | SWT.FULL_SELECTION);
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(false, true).hint(250, SWT.DEFAULT).create());
        viewer.setLabelProvider(new DabaBaseConnectorDefinitionLabelProvider());
        viewer.setContentProvider(new DatabaseConnectorDefinitionContentProvider(connectorDefRepositoryStore));
        viewer.setInput(getCategory());
        viewer.addFilter(new ViewerFilter() {
            
            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                ExtendedConnectorDefinition def = (ExtendedConnectorDefinition) element;
                String searchTerm = searchField.getText();
                return searchTerm.isBlank() 
                        || def.getConnectorDefinitionLabel().toLowerCase().contains(searchTerm.toLowerCase()) 
                        || def.getId().toLowerCase().contains(searchTerm.toLowerCase());
            }
        });
        WidgetProperties.text(SWT.Modify).observeDelayed(250, searchField)
            .addValueChangeListener(event -> Display.getDefault().asyncExec(() -> viewer.refresh()));
        viewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                final ConnectorDefinition def = getSelectedConnector();
                if (def != null) {
                    final String defId = def.getId();
                    automaticallyAddDriver.setSelection(getAutoAddDriverProperty(defId));
                    driversLabelProvider.setDefaultDriver(getDefaultDriver(defId));
                    driverManagerViewer.setInput(defId);
                }
            }
        });
    }

    private void createDriverManager(Composite parent, DataBindingContext ctx) {
        final Composite driverManagerComposite = new Composite(parent, SWT.NONE);
        driverManagerComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        driverManagerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        driverManagerViewer = new TableViewer(driverManagerComposite, SWT.BORDER | SWT.FULL_SELECTION);
        TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(1));
        driverManagerViewer.getTable().setLayout(tableLayout);
        driverManagerViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        driverManagerViewer.setContentProvider(new DatabaseDriversContentProvider());
        TableViewerColumn column = new TableViewerColumn(driverManagerViewer, SWT.FILL);
        column.setLabelProvider(driversLabelProvider);
        createButtonsPart(driverManagerComposite, ctx);
        createAutomaticallyAddDriverButton(driverManagerComposite, ctx);
    }

    private void createButtonsPart(Composite parent, DataBindingContext ctx) {
        final Composite buttonsComposite = new Composite(parent, SWT.NONE);
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 3).create());

        createAddbutton(buttonsComposite, ctx);
        createRemoveButton(buttonsComposite, ctx);
        createActivateButton(buttonsComposite, ctx);
    }

    private void createAddbutton(Composite parent, DataBindingContext ctx) {
        final Button add = new Button(parent, SWT.FLAT);
        add.setText(Messages.add);

        add.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).create());

        add.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                SelectJarsDialog dialog = new SelectJarsDialog(parent.getShell());
                final ConnectorDefinition def = getSelectedConnector();
                if (dialog.open() == Window.OK) {
                    List<String> jars = getJars();
                    int size = jars.size();
                    List<DependencyFileStore> selectedJars = dialog.getSelectedJars();
                    for (IRepositoryFileStore jar : selectedJars) {
                        if (!jars.contains(jar.getName())) {
                            jars.add(jar.getName());
                        }
                    }
                    if (size == 0 && !jars.isEmpty()) {
                        driversLabelProvider.setDefaultDriver(jars.get(0));
                        setDefaultDriver(def.getId(), jars.get(0));
                    }
                    setJars(def.getId(), jars);
                    if (jars.size() == 1) {
                        setDefaultDriver(def.getId(), jars.get(0));
                    }
                    driverManagerViewer.setInput(def.getId());
                }

            }
        });
        bindButtonWithViewer(add, viewer, ctx);
    }

    private void createRemoveButton(Composite parent, DataBindingContext ctx) {
        final Button remove = new Button(parent, SWT.FLAT);
        remove.setText(Messages.remove);
        remove.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).create());
        remove.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                removeDriver();
                driverManagerViewer.setInput(getSelectedConnector().getId());
            }
        });
        bindButtonWithViewer(remove, driverManagerViewer, ctx);
    }

    private void createActivateButton(Composite parent, DataBindingContext ctx) {
        final Button activate = new Button(parent, SWT.FLAT);
        activate.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).create());
        activate.setText(Messages.activate);
        activate.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                driversLabelProvider.setDefaultDriver(getSelectedDriver());
                final ConnectorDefinition def = getSelectedConnector();
                setDefaultDriver(def.getId(), getSelectedDriver());
                driverManagerViewer.setInput(def.getId());
            }
        });
        bindButtonWithViewer(activate, driverManagerViewer, ctx);
    }

    private void createAutomaticallyAddDriverButton(Composite parent, DataBindingContext ctx) {
        automaticallyAddDriver = new Button(parent, SWT.CHECK);
        automaticallyAddDriver.setText(Messages.automaticallyAddDriver);
        automaticallyAddDriver.setLayoutData(GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.CENTER).create());
        automaticallyAddDriver.setSelection(true);
        automaticallyAddDriver.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                setAutoAddDriverProperty(getSelectedConnector().getId());
            }
        });
        bindButtonWithViewer(automaticallyAddDriver, viewer, ctx);
    }

    private void bindButtonWithViewer(Button button, TableViewer viewer, DataBindingContext context) {
        context.bindValue(WidgetProperties.enabled().observe(button), 
                ViewerProperties.singleSelection().observe(viewer), 
                null,
                UpdateStrategyFactory.updateValueStrategy()
                .withConverter(ConverterBuilder.<Object, Boolean>newConverter()
                        .fromType(Object.class)
                        .toType(Boolean.class)
                        .withConvertFunction(Objects::nonNull)
                        .create())
                .create());
    }

    private void removeDriver() {
        String driver = getSelectedDriver();
        String connectorId = getSelectedConnector().getId();
        if (driver.equals(getDefaultDriver(connectorId))) {
            setDefaultDriver(connectorId, null);
        }
        List<String> jars = getJars();
        jars.remove(driver);
        DatabaseConnectorPropertiesFileStore fileStore = store.createRepositoryFileStore(getDBPrefFilename(connectorId));
        fileStore.setJarList(jars);
    }

    private ConnectorDefinition getSelectedConnector() {
        IStructuredSelection connectorSelection = (IStructuredSelection) viewer.getSelection();
        return (ConnectorDefinition) (connectorSelection.getFirstElement());
    }

    private String getSelectedDriver() {
        IStructuredSelection driverSelection = (IStructuredSelection) driverManagerViewer.getSelection();
        return (String) driverSelection.getFirstElement();
    }



    private Category getCategory() {
        ConnectorDefRepositoryStore connectorDefStore = (ConnectorDefRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(ConnectorDefRepositoryStore.class);
        DefinitionResourceProvider messageProvider = DefinitionResourceProvider.getInstance(connectorDefStore,
                ConnectorPlugin.getDefault().getBundle());
        List<ExtendedCategory> categories = messageProvider.getAllCategories();
        for (ExtendedCategory category : categories) {
            if (DATABASE.equals(category.getId())) {
                return category;
            }
        }
        return null;
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
        DatabaseConnectorPropertiesFileStore fileStore = store.getChild(getDBPrefFilename(connectorId), true);
        if (fileStore == null) {
            fileStore = store.createRepositoryFileStore(getDBPrefFilename(connectorId));
        }
        fileStore.setJarList(jars);
    }

    private String getDefaultDriver(String connectorId) {
        DatabaseConnectorPropertiesFileStore fileStore = store.getChild(getDBPrefFilename(connectorId), true);
        if (fileStore != null) {
            return fileStore.getDefault();
        }
        return null;
    }

    protected String getDBPrefFilename(String connectorId) {
        return connectorId + "." + DatabaseConnectorPropertiesRepositoryStore.PROPERTIES_EXT;
    }

    private boolean getAutoAddDriverProperty(String connectorId) {
        DatabaseConnectorPropertiesFileStore fileStore = store
                .getChild(connectorId + "." + DatabaseConnectorPropertiesRepositoryStore.PROPERTIES_EXT, true);
        return fileStore != null && fileStore.getAutoAddDriver();
    }

    private void setDefaultDriver(String connectorId, String defaultDriver) {
        final String dbPrefFilename = getDBPrefFilename(connectorId);
        DatabaseConnectorPropertiesFileStore fileStore = store.getChild(dbPrefFilename, true);
        if (fileStore == null) {
            fileStore = store.createRepositoryFileStore(dbPrefFilename);
        }
        fileStore.setDefault(defaultDriver);
    }

    private void setAutoAddDriverProperty(String connectorId) {
        final String dbPrefFilename = getDBPrefFilename(connectorId);
        DatabaseConnectorPropertiesFileStore fileStore = store.getChild(dbPrefFilename, true);
        if (fileStore != null) {
            fileStore.setAutoAddDriver(Boolean.valueOf(automaticallyAddDriver.getSelection()));
        } else {
            fileStore = store.createRepositoryFileStore(dbPrefFilename);
        }
    }

}
