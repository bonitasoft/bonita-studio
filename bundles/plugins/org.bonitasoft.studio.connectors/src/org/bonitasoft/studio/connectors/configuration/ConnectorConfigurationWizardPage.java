/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.connectors.configuration;

import java.util.HashMap;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IDefinitionRepositoryStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.IImplementationRepositoryStore;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.DefinitionMapping;
import org.bonitasoft.studio.model.configuration.util.ConfigurationAdapterFactory;
import org.bonitasoft.studio.model.configuration.util.ConfigurationResourceFactoryImpl;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.util.ProcessAdapterFactory;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;

/**
 * @author Romain Bioteau
 */
public class ConnectorConfigurationWizardPage extends WizardPage
        implements IProcessConfigurationWizardPage, ISelectionChangedListener {

    private static final ConnectorsConfigurationSynchronizer CONNECTORS_CONFIGURATION_SYNCHRONIZER = new ConnectorsConfigurationSynchronizer();
    protected TableViewer viewer;
    private Button selectImplementationButton;
    private Button clearImplementationButton;
    protected Configuration configuration;

    public ConnectorConfigurationWizardPage() {
        super(ConnectorConfigurationWizardPage.class.getName());
        setTitle(Messages.connectors);
        setDescription(Messages.connectorsConfigurationDescription);
    }

    protected DefinitionResourceProvider getResourceProvider() {
        return DefinitionResourceProvider.getInstance(
                (IRepositoryStore<? extends IRepositoryFileStore>) getDefinitionStore(),
                ConnectorPlugin.getDefault().getBundle());
    }

    protected IImplementationRepositoryStore getImplStore() {
        return RepositoryManager.getInstance().getRepositoryStore(ConnectorImplRepositoryStore.class);
    }

    protected IDefinitionRepositoryStore getDefinitionStore() {
        return RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        final Label descriptionLabel = new Label(mainComposite, SWT.WRAP);
        descriptionLabel.setText(getDescription());
        descriptionLabel.setLayoutData(GridDataFactory.swtDefaults().grab(true, false).span(2, 1).create());

        final Composite buttonComposite = new Composite(mainComposite, SWT.NONE);
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).indent(0, 25).create());
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).spacing(0, 3).create());

        selectImplementationButton = new Button(buttonComposite, SWT.FLAT);
        selectImplementationButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        selectImplementationButton.setText(Messages.selectImplementation);
        selectImplementationButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                openImplementationSelection();
            }
        });

        clearImplementationButton = new Button(buttonComposite, SWT.FLAT);
        clearImplementationButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        clearImplementationButton.setText(Messages.clear);
        clearImplementationButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                clearImplementation();
            }
        });

        viewer = new TableViewer(mainComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.SINGLE);
        viewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        viewer.setContentProvider(getContentProvider());
        viewer.getTable().setHeaderVisible(true);
        viewer.getTable().setLinesVisible(true);
        viewer.addSelectionChangedListener(this);
        final TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(50));
        tableLayout.addColumnData(new ColumnWeightData(50));
        viewer.getTable().setLayout(tableLayout);

        final TableViewerColumn columnDefViewer = new TableViewerColumn(viewer, SWT.NONE);
        columnDefViewer.setLabelProvider(new DefinitionLabelProvider(getResourceProvider(), getDefinitionStore()));
        TableColumn column = columnDefViewer.getColumn();
        column.setText(Messages.definition);

        final TableViewerColumn columnImplIdViewer = new TableViewerColumn(viewer, SWT.NONE);
        columnImplIdViewer.setLabelProvider(new ImplementationLabelProvider(getImplStore()));
        column = columnImplIdViewer.getColumn();
        column.setText(Messages.implementation);

        setControl(mainComposite);
    }

    protected IStructuredContentProvider getContentProvider() {
        return new DefinitionMappingsContentProvider();
    }

    protected void openImplementationSelection() {
        final DefinitionMapping connectorAssociation = (DefinitionMapping) ((IStructuredSelection) viewer.getSelection())
                .getFirstElement();
        final SelectConnectorImplementationWizard wizard = new SelectConnectorImplementationWizard(connectorAssociation);
        final WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(), wizard);
        if (dialog.open() == Dialog.OK) {
            final ConnectorImplementation impl = wizard.getConnectorImplementation();
            final DefinitionMapping association = (DefinitionMapping) ((IStructuredSelection) viewer.getSelection())
                    .getFirstElement();
            association.setImplementationId(impl.getImplementationId());
            association.setImplementationVersion(impl.getImplementationVersion());

            EditingDomain editingDomain = TransactionUtil.getEditingDomain(configuration);
            boolean dispose = false;
            ComposedAdapterFactory adapterFactory = null;
            if (editingDomain == null) {
                dispose = true;
                // Create an adapter factory that yields item providers.
                adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
                adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
                adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
                adapterFactory.addAdapterFactory(new ConfigurationAdapterFactory());
                adapterFactory.addAdapterFactory(new ProcessAdapterFactory());

                // command stack that will notify this editor as commands are executed
                final BasicCommandStack commandStack = new BasicCommandStack();

                // Create the editing domain with our adapterFactory and command stack.
                editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack,
                        new HashMap<Resource, Boolean>());
                editingDomain.getResourceSet().getResourceFactoryRegistry().getExtensionToFactoryMap().put("conf",
                        new ConfigurationResourceFactoryImpl());
            }
            final CompoundCommand cc = new CompoundCommand();
            CONNECTORS_CONFIGURATION_SYNCHRONIZER.updateConnectorDependencies(configuration, association, impl, cc,
                    editingDomain, false);
            editingDomain.getCommandStack().execute(cc);
            if (dispose) {
                adapterFactory.dispose();
            }
            viewer.refresh();
            getContainer().updateMessage();
        }
    }

    protected void clearImplementation() {
        final DefinitionMapping connectorAssociation = (DefinitionMapping) ((IStructuredSelection) viewer.getSelection())
                .getFirstElement();
        final String implId = NamingUtils.toConnectorImplementationFilename(connectorAssociation.getImplementationId(),
                connectorAssociation.getImplementationVersion(), false);
        connectorAssociation.setImplementationId(null);
        connectorAssociation.setImplementationVersion(null);

        EditingDomain editingDomain = TransactionUtil.getEditingDomain(configuration);
        boolean dispose = false;
        ComposedAdapterFactory adapterFactory = null;
        if (editingDomain == null) {
            dispose = true;
            // Create an adapter factory that yields item providers.
            adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
            adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
            adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
            adapterFactory.addAdapterFactory(new ConfigurationAdapterFactory());
            adapterFactory.addAdapterFactory(new ProcessAdapterFactory());

            // command stack that will notify this editor as commands are executed
            final BasicCommandStack commandStack = new BasicCommandStack();

            // Create the editing domain with our adapterFactory and command stack.
            editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack, new HashMap<Resource, Boolean>());
            editingDomain.getResourceSet().getResourceFactoryRegistry().getExtensionToFactoryMap().put("conf",
                    new ConfigurationResourceFactoryImpl());
        }
        final CompoundCommand cc = new CompoundCommand();
        CONNECTORS_CONFIGURATION_SYNCHRONIZER.removeConnectorDependencies(configuration, implId, cc, editingDomain);
        editingDomain.getCommandStack().execute(cc);
        if (dispose) {
            adapterFactory.dispose();
        }

        viewer.refresh();
        getContainer().updateMessage();
    }

    /*
     * (non-Javadoc)
     * @see
     * org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage#updatePage(org.bonitasoft.studio.model.
     * process.AbstractProcess, org.bonitasoft.studio.model.configuration.Configuration)
     */
    @Override
    public void updatePage(final AbstractProcess process, final Configuration configuration) {
        if (process != null && configuration != null && viewer != null && !viewer.getControl().isDisposed()) {
            this.configuration = configuration;
            viewer.setInput(configuration);
            selectImplementationButton.setEnabled(false);
            clearImplementationButton.setEnabled(false);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage#isConfigurationPageValid()
     */
    @Override
    public String isConfigurationPageValid(final Configuration conf) {
        if (viewer != null && viewer.getInput() != null) {
            for (final Object element : ((IStructuredContentProvider) viewer.getContentProvider())
                    .getElements(viewer.getInput())) {
                final DefinitionMapping association = (DefinitionMapping) element;
                final String implementationId = association.getImplementationId();
                final String implementationVersion = association.getImplementationVersion();
                if (implementationId == null || implementationVersion == null) {
                    return NLS.bind(Messages.invalidImplementationFor, association.getDefinitionId());
                }
                final ConnectorImplementation impl = getImplStore().getImplementation(implementationId,
                        implementationVersion);
                if (impl == null) {
                    return NLS.bind(Messages.implementationNotFound, implementationId + " (" + implementationVersion + ")");
                }

            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage#getConfigurationImage()
     */
    @Override
    public Image getConfigurationImage() {
        return Pics.getImage(PicsConstants.connectorDef);
    }

    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        if (selectImplementationButton != null && !selectImplementationButton.isDisposed()) {
            selectImplementationButton.setEnabled(!viewer.getSelection().isEmpty());
        }
        if (clearImplementationButton != null && !clearImplementationButton.isDisposed()) {
            clearImplementationButton.setEnabled(!viewer.getSelection().isEmpty());
        }
    }

    @Override
    public boolean isDefault() {
        return false;
    }

}
