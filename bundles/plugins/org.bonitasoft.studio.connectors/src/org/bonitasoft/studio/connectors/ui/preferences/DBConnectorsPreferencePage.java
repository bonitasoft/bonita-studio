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

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.jface.BonitaStudioFontRegistry;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IFileStoreContribution;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesFileStore;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesRepositoryStore;
import org.bonitasoft.studio.connectors.ui.provider.DabaBaseConnectorDefinitionLabelProvider;
import org.bonitasoft.studio.connectors.ui.provider.DatabaseConnectorDefinitionContentProvider;
import org.bonitasoft.studio.connectors.ui.provider.DatabaseDriversContentProvider;
import org.bonitasoft.studio.connectors.ui.provider.DatabaseDriversLabelProvider;
import org.bonitasoft.studio.dependencies.ui.dialog.SelectJarsDialog;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.preferences.pages.AbstractBonitaPreferencePage;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.gmf.runtime.common.ui.preferences.CheckBoxFieldEditor;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * @author aurelie zara
 */
public class DBConnectorsPreferencePage extends AbstractBonitaPreferencePage implements IWorkbenchPreferencePage {

    private static final int DEFAULT_BUTTON_WIDTH_HINT = 85;
    private final String DATABASE = "database";
    private TableViewer viewer;
    private TableViewer driverManagerViewer;
    private DbConnectorsPreferenceFilter connectorFilter;
    private DatabaseConnectorPropertiesRepositoryStore store;
    private DataBindingContext context;
    private DatabaseDriversLabelProvider driversLabelProvider;
    private Button automaticallyAddDriver;
    private static final String CLASS = "class";

    @Override
    protected Control createContents(Composite parent) {
        setPreferenceStore(BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore());
        final Composite titleComposite = new Composite(parent, SWT.NONE);
        titleComposite.setLayout(new GridLayout(2, false));
        titleComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        final Label imageLabel = new Label(titleComposite, SWT.NONE);
        imageLabel.setImage(Pics.getImage(PicsConstants.preferenceAdvanced));

        final Label title = new Label(titleComposite, SWT.NONE);
        title.setText(Messages.BonitaPreferenceDialog_DBConnectors);
        title.setFont(BonitaStudioFontRegistry.getPreferenceTitleFont());

        final Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout(2, false);
        layout.marginHeight = layout.marginWidth = 0;
        layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
        layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
        composite.setLayout(layout);
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final Composite booleanEditorComposite = new Composite(composite, SWT.NONE);
        booleanEditorComposite.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, false).create());
        booleanEditorComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());
        BooleanFieldEditor editor = new CheckBoxFieldEditor(BonitaPreferenceConstants.ALWAYS_USE_SCRIPTING_MODE,
                Messages.alwaysUseScriptingModeOutputPref, booleanEditorComposite);
        addField(editor);

        final Label label = new Label(composite, SWT.WRAP);
        label.setText(Messages.BonitaPreferencePage_DBConnectors_Description);
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        data.widthHint = 150;
        data.horizontalSpan = 2;
        label.setLayoutData(data);
        label.setLayoutData(data);

        createDBConnectorsList(composite);
        createDriverManager(composite);

        initialize();
        checkState();

        return composite;
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
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(false, true).hint(200, SWT.DEFAULT).create());
        viewer.setLabelProvider(new DabaBaseConnectorDefinitionLabelProvider());
        viewer.setContentProvider(new DatabaseConnectorDefinitionContentProvider());
        viewer.setInput(getCategory());
        connectorFilter = new DbConnectorsPreferenceFilter();
        viewer.addFilter(connectorFilter);
        searchField.addKeyListener(new KeyAdapter() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.swt.events.KeyAdapter#keyReleased(org.eclipse.swt.events.KeyEvent)
             */
            @Override
            public void keyReleased(KeyEvent e) {
                connectorFilter.setSearchText(searchField.getText());
                viewer.refresh();
            }
        });
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

    private void createDriverManager(Composite parent) {

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
        createButtonsPart(driverManagerComposite);
        createAutomaticallyAddDriverButton(driverManagerComposite);
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

            /*
             * (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                SelectJarsDialog dialog = new SelectJarsDialog(getShell());
                final ConnectorDefinition def = getSelectedConnector();
                if (dialog.open() == Window.OK) {
                    List<String> jars = getJars();
                    int size = jars.size();
                    List<IRepositoryFileStore> selectedJars = dialog.getSelectedJars();
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
        bindButtonWithViewer(add, viewer);
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
                driverManagerViewer.setInput(getSelectedConnector().getId());
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
                final ConnectorDefinition def = getSelectedConnector();
                setDefaultDriver(def.getId(), getSelectedDriver());
                driverManagerViewer.setInput(def.getId());
            }
        });
        bindButtonWithViewer(activate, driverManagerViewer);
    }

    private void createAutomaticallyAddDriverButton(Composite parent) {
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
        bindButtonWithViewer(automaticallyAddDriver, viewer);
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

    @Override
    public void init(IWorkbench workbench) {
        driversLabelProvider = new DatabaseDriversLabelProvider();
        store = (DatabaseConnectorPropertiesRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(DatabaseConnectorPropertiesRepositoryStore.class);
        context = new DataBindingContext();
    }

    @Override
    protected void createFieldEditors() {

    }

    private Category getCategory() {
        ConnectorDefRepositoryStore connectorDefStore = (ConnectorDefRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(ConnectorDefRepositoryStore.class);
        DefinitionResourceProvider messageProvider = DefinitionResourceProvider.getInstance(connectorDefStore,
                ConnectorPlugin.getDefault().getBundle());
        List<Category> categories = messageProvider.getAllCategories();
        for (Category category : categories) {
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
        return connectorId + "." + DatabaseConnectorPropertiesRepositoryStore.CONF_EXT;
    }

    private boolean getAutoAddDriverProperty(String connectorId) {
        DatabaseConnectorPropertiesFileStore fileStore = store
                .getChild(connectorId + "." + DatabaseConnectorPropertiesRepositoryStore.CONF_EXT, true);
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

    @Override
    protected void performDefaults() {
        super.performDefaults();
        reinitDriverAssociation();
        driverManagerViewer.refresh();
    }

    private void reinitDriverAssociation() {
        IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance()
                .getConfigurationElements("org.bonitasoft.studio.repository.fileContribution");
        for (IConfigurationElement element : elements) {
            IFileStoreContribution contribution;
            try {
                contribution = (IFileStoreContribution) element.createExecutableExtension(CLASS);
                if (contribution.appliesTo(store)) {
                    contribution.execute(store);
                }
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
    }
}
