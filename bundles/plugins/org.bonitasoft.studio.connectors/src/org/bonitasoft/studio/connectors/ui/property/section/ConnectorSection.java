/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * Bonitasoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connectors.ui.property.section;

import static org.bonitasoft.studio.common.Messages.bosProductName;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.inject.Inject;

import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.bpm.model.process.Connector;
import org.bonitasoft.bpm.model.process.Lane;
import org.bonitasoft.bpm.model.process.ProcessPackage;
import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.ProjectDependenciesStore;
import org.bonitasoft.studio.common.ui.jface.EMFListFeatureTreeContentProvider;
import org.bonitasoft.studio.common.ui.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.common.ui.widgets.GTKStyleHandler;
import org.bonitasoft.studio.connector.model.definition.migration.ConnectorConfigurationMigrator;
import org.bonitasoft.studio.connector.model.definition.migration.ConnectorConfigurationMigratorFactory;
import org.bonitasoft.studio.connector.model.definition.migration.ConnectorConfigurationToConnectorDefinitionConverter;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.ui.provider.StyledConnectorLabelProvider;
import org.bonitasoft.studio.connectors.ui.wizard.ConnectorDefinitionWizardDialog;
import org.bonitasoft.studio.connectors.ui.wizard.ConnectorWizard;
import org.bonitasoft.studio.connectors.ui.wizard.MoveConnectorWizard;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.widget.DynamicButtonWidget;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.databinding.edit.IEMFEditListProperty;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 */
public abstract class ConnectorSection extends AbstractBonitaDescriptionSection
        implements IDoubleClickListener, ISelectionChangedListener {

    public static final String OPEN_MARKETPLACE_COMMAND = "org.bonitasoft.studio.application.marketplace.command";

    private Button removeConnectorButton;
    private Button updateConnectorButton;
    private Button upConnectorButton;
    private Button downConnectorButton;
    private TableViewer tableViewer;
    private Button moveButton;
    private CommandExecutor commandExecutor = new CommandExecutor();

    private ConnectorConfigurationMigratorFactory migrationFactory;
    private ConnectorConfigurationToConnectorDefinitionConverter configurationToDefinitionConverter;
    private RepositoryAccessor repositoryAccessor;

    @Inject
    public ConnectorSection(ConnectorConfigurationMigratorFactory migrationFactory,
            ConnectorConfigurationToConnectorDefinitionConverter configurationToDefinitionConverter,
            RepositoryAccessor repositoryAccessor) {
        this.migrationFactory = migrationFactory;
        this.configurationToDefinitionConverter = configurationToDefinitionConverter;
        this.repositoryAccessor = repositoryAccessor;
    }

    @Override
    protected void createContent(final Composite parent) {
        var viewerComposite = getWidgetFactory().createComposite(
                parent);
        viewerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        viewerComposite.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(2)
                .margins(20,10)
                .spacing(LayoutConstants.getSpacing().x, 2)
                .create());
        createToolbar(viewerComposite);
        createConnectorComposite(viewerComposite);
    }

    private void createToolbar(Composite parent) {
        new DynamicButtonWidget.Builder()
                .withLabel(Messages.openMarketplace)
                .withTooltipText(Messages.openMarketplaceTooltip)
                .withImage(Pics.getImage(PicsConstants.openMarketplace))
                .withHotImage(Pics.getImage(PicsConstants.openMarketplaceHot))
                .withLayoutData(
                        GridDataFactory.fillDefaults().grab(true, false).align(SWT.END, SWT.FILL).span(2, 1).create())
                .withToolkit(getWidgetFactory())
                .onClick(e -> openMarketplace())
                .createIn(parent);
    }

    private void openMarketplace() {
        commandExecutor.executeCommand(OPEN_MARKETPLACE_COMMAND, Map.of("types", Messages.connectorType, "analyzeInWizard", "true"));
        openConnectorWizard();
    }

    private void createConnectorComposite(final Composite parent) {
        Composite buttonsComposite = getWidgetFactory().createComposite(parent);
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(false, true).create());
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults()
                .numColumns(1).margins(5, 0).spacing(0, 3).create());

        createAddConnectorButton(buttonsComposite);
        updateConnectorButton = createUpdateConnectorButton(buttonsComposite);
        removeConnectorButton = createRemoveConnectorButton(buttonsComposite);
        upConnectorButton = createUpConnectorButton(buttonsComposite);
        downConnectorButton = createDownConnectorButton(buttonsComposite);
        moveButton = createMoveConnectorButton(buttonsComposite);

        createViewerComposite(parent);

    }

    private void createViewerComposite(final Composite parent) {
        tableViewer = new TableViewer(parent, GTKStyleHandler.removeBorderFlag(SWT.BORDER | SWT.MULTI
                | SWT.NO_FOCUS));
        getWidgetFactory().adapt(tableViewer.getTable(), false, false);
        tableViewer.getTable().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true)
                        .create());

        tableViewer.addDoubleClickListener(this);
        tableViewer.addSelectionChangedListener(this);
        tableViewer.getTable().addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.keyCode == SWT.DEL) {
                    e.doit = false;
                    removeSelectedConnectors();
                }
            }
        });
        tableViewer.setContentProvider(new EMFListFeatureTreeContentProvider(
                getConnectorFeature()));
        tableViewer.setLabelProvider(
                new StyledConnectorLabelProvider(
                        repositoryAccessor.getRepositoryStore(ConnectorDefRepositoryStore.class)));
        tableViewer.addFilter(getViewerFilter());
    }

    private void updateButtons() {
        if (tableViewer != null) {
            final IStructuredSelection selection = (IStructuredSelection) tableViewer
                    .getSelection();

            if (!removeConnectorButton.isDisposed()) {
                removeConnectorButton.setEnabled(!selection.isEmpty());
            }

            final boolean isAnElementSelected = selection.size() == 1;
            final boolean hasMoreThanOneItemInTheTable = tableViewer.getTable()
                    .getItemCount() > 1;
            if (!downConnectorButton.isDisposed()) {
                downConnectorButton.setEnabled(isAnElementSelected
                        && hasMoreThanOneItemInTheTable);
            }

            if (!upConnectorButton.isDisposed()) {
                upConnectorButton.setEnabled(isAnElementSelected
                        && hasMoreThanOneItemInTheTable);
            }

            if (!updateConnectorButton.isDisposed()) {
                if (isAnElementSelected) {
                    final Connector connector = (Connector) selection
                            .getFirstElement();
                    final ConnectorDefRepositoryStore connectorDefStore = RepositoryManager
                            .getInstance().getRepositoryStore(
                                    ConnectorDefRepositoryStore.class);
                    final ConnectorDefinition def = connectorDefStore
                            .getDefinition(connector.getDefinitionId(),
                                    connector.getDefinitionVersion());
                    long nbOfDefinitions = connectorDefStore.getDefinitions().stream()
                            .filter(definition -> Objects.equals(definition.getId(), connector.getDefinitionId()))
                            .count();
                    updateConnectorButton.setEnabled(def != null || nbOfDefinitions > 0);
                    updateConnectorButton.setText(def == null && nbOfDefinitions > 0 ? Messages.update : Messages.Edit);
                } else {
                    updateConnectorButton.setEnabled(false);
                }
            }
            if (moveButton != null && !moveButton.isDisposed()) {
                moveButton.setEnabled(!selection.isEmpty());
            }
        }
    }

    private Button createRemoveConnectorButton(final Composite buttonComposite) {
        final Button removeButton = getWidgetFactory().createButton(
                buttonComposite, Messages.removeData, SWT.FLAT);
        removeButton.setLayoutData(GridDataFactory.fillDefaults()
                .minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        removeButton.addListener(SWT.Selection, e -> removeSelectedConnectors());
        return removeButton;
    }

    protected void removeSelectedConnectors() {
        if (tableViewer != null
                && ((IStructuredSelection) tableViewer.getSelection())
                        .size() > 0) {
            final List<?> selection = ((IStructuredSelection) tableViewer
                    .getSelection()).toList();
            if (MessageDialog.openConfirm(Display.getDefault().getActiveShell(),
                    Messages.deleteDialogTitle, createMessage())) {
                getEditingDomain().getCommandStack().execute(
                        new RemoveCommand(getEditingDomain(),
                                getEObject(), getConnectorFeature(),
                                selection));
                tableViewer.refresh();
            }
        }
    }

    public String createMessage() {
        final Object[] selection = ((IStructuredSelection) tableViewer
                .getSelection()).toArray();
        final StringBuilder res = new StringBuilder(
                Messages.deleteDialogConfirmMessage);
        res.append(' ');
        res.append(((Connector) selection[0]).getName());
        for (int i = 1; i < selection.length; i++) {
            res.append(", "); //$NON-NLS-1$
            res.append(((Connector) selection[i]).getName());
        }
        res.append(" ?"); //$NON-NLS-1$
        return res.toString();
    }

    protected Button createMoveConnectorButton(final Composite buttonsComposite) {
        final Button moveButton = getWidgetFactory().createButton(
                buttonsComposite, Messages.copyMove, SWT.FLAT);
        moveButton.setLayoutData(GridDataFactory.fillDefaults()
                .minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        moveButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                final WizardDialog dialog = new WizardDialog(Display.getDefault()
                        .getActiveShell(),
                        new MoveConnectorWizard(OperationHistoryFactory.getOperationHistory(),
                                getEditingDomain(),
                                ((IStructuredSelection) tableViewer.getSelection()).toList()));
                if (dialog.open() == Dialog.OK) {
                    tableViewer.refresh();
                }
            }
        });
        return moveButton;
    }

    private Button createAddConnectorButton(final Composite parent) {
        final Button addData = getWidgetFactory().createButton(parent,
                Messages.add, SWT.FLAT);
        addData.setLayoutData(GridDataFactory.fillDefaults()
                .minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        addData.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent event) {
                var registry = repositoryAccessor.getRepositoryStore(ConnectorDefRepositoryStore.class)
                        .getResourceProvider().getConnectorDefinitionRegistry();
                if (registry.getDefinitions().isEmpty()) {
                    MessageDialog.openInformation(Display.getDefault().getActiveShell(),
                            Messages.noConnectorInstalled,
                            Messages.installConnectorExtensionMsg);
                    openMarketplace();
                } else {
                    openConnectorWizard();
                }
            }

        });
        return addData;
    }

    private void openConnectorWizard() {
        try {
            PlatformUI.getWorkbench().getProgressService().busyCursorWhile(monitor -> {
                monitor.beginTask(org.bonitasoft.studio.common.repository.Messages.analyzeProjectDependencies, IProgressMonitor.UNKNOWN);
                Job.getJobManager()
                        .join(ProjectDependenciesStore.ANALYZE_PPROJECT_DEPENDENCIES_FAMILY, new NullProgressMonitor());
            });
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
        }
        final WizardDialog wizardDialog = new ConnectorDefinitionWizardDialog(
                Display.getCurrent().getActiveShell(),
                createAddConnectorWizard());
        if (wizardDialog.open() == Window.OK) {
            tableViewer.refresh();
        }
    }

    protected ConnectorWizard createAddConnectorWizard() {
        return new ConnectorWizard(getEObject(), getConnectorFeature(),
                getConnectorFeatureToCheckUniqueID());
    }

    private Button createUpdateConnectorButton(final Composite parent) {
        final Button updateButton = getWidgetFactory().createButton(parent,
                Messages.Edit, SWT.FLAT);
        updateButton.setLayoutData(GridDataFactory.fillDefaults()
                .minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        updateButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                updateConnectorAction();
            }
        });
        return updateButton;
    }

    private void updateConnectorAction() {
        final IStructuredSelection selection = (IStructuredSelection) tableViewer
                .getSelection();
        if (selection.size() != 1) {
            MessageDialog.openInformation(
                    Display.getCurrent().getActiveShell(),
                    Messages.selectOnlyOneElementTitle,
                    Messages.selectOnlyOneElementMessage);
        } else {
            final Connector connector = (Connector) selection.getFirstElement();
            final ConnectorDefRepositoryStore connectorDefStore = RepositoryManager
                    .getInstance().getRepositoryStore(
                            ConnectorDefRepositoryStore.class);
            ConnectorDefinition def = connectorDefStore.getDefinition(
                    connector.getDefinitionId(),
                    connector.getDefinitionVersion());
            ConnectorConfigurationMigrator migrator = null;
            if (def == null) {
                migrator = updateConnectorDefinition(connector, connectorDefStore);
            }
            if (def != null || migrator != null) {
                final WizardDialog wizardDialog = new ConnectorDefinitionWizardDialog(
                        Display.getCurrent().getActiveShell(),
                        createEditConnectorWizard(connector, migrator));
                if (wizardDialog.open() == Window.OK) {
                    tableViewer.refresh();
                }
            }
        }
    }

    private ConnectorConfigurationMigrator updateConnectorDefinition(final Connector connector,
            final ConnectorDefRepositoryStore connectorDefStore) {
        List<ConnectorDefinition> otherDefinitions = connectorDefStore.getDefinitions().stream()
                .filter(definition -> Objects.equals(definition.getId(), connector.getDefinitionId()))
                .collect(Collectors.toList());
        ConnectorDefinition targetDefinition = null;
        if (otherDefinitions.size() > 1) {
            // Choose definitions
            SelectDefinitionVersionDialog selectDefinitionVersionDialog = new SelectDefinitionVersionDialog(
                    Display.getDefault().getActiveShell(), otherDefinitions);
            if (selectDefinitionVersionDialog.open() == IDialogConstants.NEXT_ID) {
                String targetVersion = selectDefinitionVersionDialog.getVersion();
                targetDefinition = otherDefinitions.stream()
                        .filter(def -> Objects.equals(def.getVersion(), targetVersion))
                        .findFirst()
                        .orElseThrow();
            }
        } else if (!otherDefinitions.isEmpty()) {
            targetDefinition = otherDefinitions.get(0);
        }
        if (targetDefinition != null) {
            return migrationFactory.create(configurationToDefinitionConverter.convert(connector), targetDefinition);
        }
        return null;
    }

    protected ConnectorWizard createEditConnectorWizard(
            final Connector connector, ConnectorConfigurationMigrator migrator) {
        return new ConnectorWizard(connector, getConnectorFeature(),
                getConnectorFeatureToCheckUniqueID(), migrator);
    }

    protected boolean getShowAutoGenerateForm() {
        return true;
    }

    protected EStructuralFeature getConnectorFeature() {
        return ProcessPackage.Literals.CONNECTABLE_ELEMENT__CONNECTORS;
    }

    protected Set<EStructuralFeature> getConnectorFeatureToCheckUniqueID() {
        final Set<EStructuralFeature> res = new HashSet<>();
        res.add(ProcessPackage.Literals.CONNECTABLE_ELEMENT__CONNECTORS);
        return res;
    }

    protected void refreshBindings() {
        if (tableViewer != null && getEObject() != null) {
            bindTree();
        }
    }

    private void bindTree() {
        final IEMFEditListProperty list = EMFEditProperties.list(
                getEditingDomain(), getConnectorFeature());
        final IObservableList observeConnectorList = list.observe(getEObject());
        observeConnectorList.addChangeListener(new IChangeListener() {

            @Override
            public void handleChange(final ChangeEvent event) {
                if (!tableViewer.getTable().isDisposed()) {
                    tableViewer.setInput(getEObject());
                }
            }
        });
        tableViewer.setInput(getEObject());
        updateButtons();
    }

    protected abstract ViewerFilter getViewerFilter();

    protected TableViewer getTree() {
        return tableViewer;
    }

    protected void refreshTree() {
        if (!tableViewer.getTable().isDisposed()) {
            tableViewer.setInput(getEObject());
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.diagram.ui.properties.sections.
     * AbstractModelerPropertySection#setEObject(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public void setEObject(final EObject object) {
        super.setEObject(object);
        refreshBindings();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.diagram.ui.properties.sections.
     * AbstractModelerPropertySection#getEObject()
     */
    @Override
    protected EObject getEObject() {
        final EObject eObject = super.getEObject();
        if (eObject instanceof Lane) {
            return ModelHelper.getParentProcess(eObject);
        }
        return eObject;
    }

    @Override
    public void doubleClick(final DoubleClickEvent event) {
        updateConnectorAction();
    }

    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        updateButtons();
    }

    /**
     * @param buttonsComposite
     * @return
     */
    protected Button createUpConnectorButton(final Composite buttonsComposite) {
        final Button addConnectorButton = getWidgetFactory().createButton(
                buttonsComposite, Messages.up, SWT.FLAT);
        addConnectorButton.setLayoutData(GridDataFactory.fillDefaults()
                .minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        addConnectorButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                moveSelectedConnector(-1);
            }
        });
        return addConnectorButton;
    }

    /**
     * @param buttonsComposite
     * @return
     */
    protected Button createDownConnectorButton(final Composite buttonsComposite) {
        final Button addConnectorButton = getWidgetFactory().createButton(
                buttonsComposite, Messages.down, SWT.FLAT);
        addConnectorButton.setLayoutData(GridDataFactory.fillDefaults()
                .minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        addConnectorButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                moveSelectedConnector(+1);
            }
        });
        return addConnectorButton;
    }

    private void moveSelectedConnector(final int diff) {
        final EObject selectConnector = (EObject) ((IStructuredSelection) tableViewer
                .getSelection()).getFirstElement();
        @SuppressWarnings("unchecked")
        final EList<Connector> connectors = (EList<Connector>) getEObject()
                .eGet(getConnectorFeature());
        final int destIndex = connectors.indexOf(selectConnector) + diff;
        final Command c = new MoveCommand(getEditingDomain(), connectors,
                selectConnector, destIndex);
        getEditingDomain().getCommandStack().execute(c);
        refresh();
    }

    @Override
    public String getSectionDescription() {
        return NLS.bind(Messages.connectorSectionDescription,
                bosProductName);
    }

    @Override
    public void refresh() {
        super.refresh();
        refreshTree();
    }

    protected ConnectorWizard createConnectorWizard(final String connectorEvent) {
        return new ConnectorWizard(getEObject(), getConnectorFeature(),
                getConnectorFeatureToCheckUniqueID(), connectorEvent);
    }

}
